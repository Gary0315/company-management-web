package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.EmpRepository;
import tw.com.ispan.eeit48.dao.PackageRepository;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.PackageBean;

@Service
@Transactional
public class PackageService {
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private EmpRepository empRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	// ok，查詢所有清單，需搜尋的指定項目：等待領件、領件完成、尚未寄出、已寄出
	public List<Map<String, String>> select(String condition, Integer page, String locationHtml) {
		List<PackageBean> result = null;
		Integer packageType = null;
		Integer packageStatus = null;
		switch (condition) {
		case "a1":
			packageType = 1;// ---搜尋所有收件
			result = packageRepository.findByPackagetype(packageType);
			break;
		case "a2":
			packageType = 1;// ---搜尋收件
			packageStatus = 0;// ---還未結案的清單
			result = packageRepository.findByPackagetypeAndPackagestatus(packageType, packageStatus);
			break;
		case "a3":
			packageType = 1;// ---搜尋收件
			packageStatus = 1;// ---已結案的清單
			result = packageRepository.findByPackagetypeAndPackagestatus(packageType, packageStatus);
			break;
		case "b1":
			packageType = 0;// ---搜尋所有寄件
			result = packageRepository.findByPackagetype(packageType);
			break;
		case "b2":
			packageType = 0;// ---搜尋收件
			packageStatus = 0;// ---還未結案的清單
			result = packageRepository.findByPackagetypeAndPackagestatus(packageType, packageStatus);
			break;
		case "b3":
			packageType = 0;// ---搜尋收件
			packageStatus = 1;// ---已結案的清單
			result = packageRepository.findByPackagetypeAndPackagestatus(packageType, packageStatus);
			break;
		case "noFinish":
			packageStatus = 0;// ---未結案
			result = packageRepository.findByPackagestatus(packageStatus);
			break;
		default:
			result = packageRepository.findAll();
			break;
		}
		if(locationHtml.equals("list")) {
			Collections.reverse(result);
		}
		List<Map<String, String>> dataRequest = beanToMap(result, page, locationHtml);
		return dataRequest;
	}

	// 輸出資料組合
	public List<Map<String, String>> beanToMap(List<PackageBean> beans, Integer page, String locationHtml) {
		List<Map<String, String>> maps = new ArrayList<>();
		int rangeEnd = (int) page * 10; // 只抓指定幾筆，同時設定終點
		int rangeStart = rangeEnd - 9; // 設定起始點
		int mark = 0;// 計數器，只有在範圍內的資料才會紀錄
		String listPage = beans.size()%10==0?(beans.size()/10)+"":(beans.size()/10+1)+"";
		Map<String, String> pages = new HashMap<String, String>();
		pages.put("page", listPage);
		maps.add(pages);
		for (PackageBean bean : beans) {
			mark++;
			if (rangeStart <= mark && mark <= rangeEnd) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("packageid", bean.getPackageid().toString());
				map.put("packagetype", bean.getPackagetype().equals(1) ? "收貨" : "寄貨");
				map.put("empname", empRepository.findById(bean.getEmpid()).get().getName());
				map.put("packagestatus",
						bean.getPackagefinishtime() == null ? bean.getPackagetype().equals(1) ? "未轉交" : "未寄出"
								: bean.getPackagetype().equals(1) ? "已轉交" : "已寄出");
				map.put("textcolor", bean.getPackagefinishtime() == null ? "red" : "green");
				map.put("packagetime", bean.getScheduleddate().toString().substring(0, 10));
				if (locationHtml.equals("list")) {
					map.put("finish", bean.getPackagefinishtime() == null ? "-"
							: bean.getPackagefinishtime().toString().substring(0, 10));
				}
				maps.add(map);
			}
			if(rangeEnd <= mark) {break;}
		}

		return maps;
	}
	
	
	//尋找單筆案件詳情
	public Map<String, String> formCondition(String id){
		Map<String, String> requestData = new HashMap<>();
		PackageBean bean = packageRepository.findById(Integer.parseInt(id)).get();
		EmpBean ebean = empRepository.findById(bean.getEmpid()).get();
		requestData.put("packageid", bean.getPackageid().toString());
		requestData.put("giver", bean.getPackagetype().equals(1)? bean.getPackagename():ebean.getName());
		requestData.put("getter", bean.getPackagetype().equals(0)? bean.getPackagename():ebean.getName());
		requestData.put("packagecontent", bean.getPackagecontent()==null? "-":bean.getPackagecontent());
		requestData.put("packagephone", bean.getPackagephone()==null?"-":bean.getPackagephone());
		requestData.put("packageaddr", bean.getPackageaddr()==null?"-":bean.getPackageaddr());
		requestData.put("addr", bean.getPackagetype().equals(1)? "寄件人地址":"收件人地址");
		requestData.put("phone", bean.getPackagetype().equals(1)? "寄件人電話":"收件人電話");
		return requestData;
	}
	
	

	// ok，包裹登錄，代號1，寄件申請，代號0
	public PackageBean insert(PackageBean bean) {
		bean.setActualdate(new Date());
		bean.setScheduleddate(new Date());
		bean.setPackagestatus(0);
		System.out.println(bean.toString());
		Integer id = packageRepository.findAll().size() + 1;
		bean.setPackageid(id); // ---主鍵因為不明原因無法自動產生，因此取列表長度
		if (bean != null && bean.getPackageid() != null) {
			if (!packageRepository.existsById(bean.getPackageid())) {
				PackageBean success = packageRepository.save(bean);
//				sendEmail(empRepository.findById(success.getEmpid()).get().getEmail()
//						, success
//						, "new");
				return success;
			}
		}
		return null;
	}

	// ok，更新表單狀態
	public PackageBean update(String formID) {
		PackageBean noAns = null;
		Optional<PackageBean> result = packageRepository.findById(Integer.parseInt(formID));
		if (result.isPresent()) {
			PackageBean update = result.get();
			if (update != null && update.getPackageid() != null) {
				if (packageRepository.existsById(update.getPackageid())) {
					Integer status = 1;
					update.setPackagestatus(status);
					update.setPackagefinishtime(new Date());
					PackageBean success = packageRepository.save(update);
//					sendEmail(empRepository.findById(success.getEmpid()).get().getEmail()
//							, success
//							, "old");
					return success;
				}
			}
		}
		return noAns;
	}

	// 電子郵件
	private void sendEmail(String mail,PackageBean bean,String oldOrNew) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String subjectNew = (bean.getPackagetype().equals(1)?"您的包裹":"您的寄件申請")+
						"，編號："+
						(bean.getPackageid().toString())+"，"+
						(bean.getPackagetype().equals(1)?"已在收發室等待領取":"已由收發室等候寄出");
		String subject = (bean.getPackagetype().equals(1)?"您的包裹":"您的寄件申請")+
						"，編號："+
						(bean.getPackageid().toString())+"，"+
						(bean.getPackagetype().equals(1)?"已完成領取":"已由收發室寄出");
		String outPeoloe = bean.getPackagetype().equals(1)? "寄件人":"收件人";
		String content = "<p>您好，您的案件進度已經更新</p>"+
						"<br>"+
						"<p>詳細資訊：</p>"+
						"<p>"+outPeoloe+"："+(bean.getPackagename())+"</p>"+
						"<p>內容物："+(bean.getPackagecontent())+"</p>"+
						"<p>"+outPeoloe+"電話："+(bean.getPackagephone()==null?"-":bean.getPackagephone())+"</p>"+
						"<p>"+outPeoloe+"地址："+(bean.getPackageaddr()==null?"-":bean.getPackageaddr())+"</p>"+"<br>"+"<br>"+
						"<p>如您未對本案件沒有印象，請儘速聯繫總務部。</p>";
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("howardwang904@gmail.com", "Iboss");
			helper.setTo(mail);
			helper.setSubject(oldOrNew.equals("new")?subjectNew:subject);
			helper.setText(content);
			javaMailSender.send(message);
		}catch(Exception e){e.toString();}
	}
	
	
}
