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
import tw.com.ispan.eeit48.dao.EquipmentApplyDetailRepository;
import tw.com.ispan.eeit48.dao.EquipmentApplyRepository;
import tw.com.ispan.eeit48.dao.EquipmentListRepository;
import tw.com.ispan.eeit48.domain.ApplyDetailDoublePK;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.EquipmentApplyBean;
import tw.com.ispan.eeit48.domain.EquipmentApplyDetailBean;
import tw.com.ispan.eeit48.domain.EquipmentListBean;

@Service
@Transactional
public class EquipmentApplyService {
	@Autowired
	private EquipmentListRepository equipmentListRepository; // ---器材是什麼
	@Autowired
	private EquipmentApplyRepository equipmentApplyRepository; // ---器材單據
	@Autowired
	private EquipmentApplyDetailRepository equipmentApplyDetailRepository; // ---單據詳細資訊
	@Autowired
	private EmpRepository empRepository;// ---員工詳細資訊
	@Autowired
	private JavaMailSender javaMailSender;// ---寄信用

	// ok，查詢所有清單
	public List<Map<String, String>> select(String condition, Integer page, String locationHtml) {
		List<EquipmentApplyBean> result = null;
		Integer equipmentType = null;
		switch (condition) {
		case "a":
			equipmentType = 1;// ---搜尋所有未歸還的租借
			result = equipmentApplyRepository.findByApplyfinishtime(equipmentType, null);
			break;
		case "a1":
			equipmentType = 1;// ---搜尋所有租借1
			result = equipmentApplyRepository.findByApplytype(equipmentType);
			break;
		case "a2":
			equipmentType = 1;// ---搜尋租借
			// ---審查中的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyaccepttime(equipmentType, null);
			break;
		case "a3":
			equipmentType = 1;// ---搜尋租借
			// ---維修中的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyaccepttimeAndApplyfinishtime(equipmentType, null,
					null);
			break;
		case "a4":
			equipmentType = 1;// ---搜尋租借
			// ---已結案的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyfinishtime(equipmentType, null);
			break;
		case "b":
			equipmentType = 0;// ---搜尋所有未結案的維修
			result = equipmentApplyRepository.findByApplyfinishtime(equipmentType, null);
			break;
		case "b1":
			equipmentType = 0;// ---搜尋所有維修
			result = equipmentApplyRepository.findByApplytype(equipmentType);
			break;
		case "b2":
			equipmentType = 0;// ---搜尋維修
			// ---審查中的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyaccepttime(equipmentType, null);
			break;
		case "b3":
			equipmentType = 0;// ---搜尋維修
			// ---維修中的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyaccepttimeAndApplyfinishtime(equipmentType, null,
					null);
			break;
		case "b4":
			equipmentType = 0;// ---搜尋維修
			// ---已結案的清單
			result = equipmentApplyRepository.findByApplytypeAndApplyfinishtime(equipmentType, null);
			break;
		case "noFinish":
			result = equipmentApplyRepository.findByApplyaccepttimeAndApplyfinishtime(null, null);
			break;
		default:
			result = equipmentApplyRepository.findAll();
			break;
		}
		if(locationHtml.equals("list")) {
			Collections.reverse(result);
		}
		List<Map<String, String>> dataRequest = beanToMap(result, page, locationHtml);
		return dataRequest;
	}

	//ok，輸出資料組合
	public List<Map<String, String>> beanToMap(List<EquipmentApplyBean> beans, Integer page,
			String locationHtml) {
		List<Map<String, String>> maps = new ArrayList<>();
		int rangeEnd = (int)page*10; //只抓指定幾筆，同時設定終點
		int rangeStart = rangeEnd-9; //設定起始點
		int mark = 0;// 計數器，只有在範圍內的資料才會紀錄
		String listPage = beans.size()%10==0?(beans.size()/10)+"":(beans.size()/10+1)+"";
		Map<String, String> pages = new HashMap<String, String>();
		pages.put("page", listPage);
		maps.add(pages);
		for (EquipmentApplyBean bean : beans) {
			mark++;
			//---符合範圍才寫入資料
			if(rangeStart <= mark && mark <=rangeEnd) {
				Map<String, String> map = new HashMap<String, String>();
				// -------------給編號
				map.put("applyid", bean.getApplyid().toString());
				// -------------定義類型
				map.put("applytype", bean.getApplytype().equals(1) ? "租借" : "維修");
				// -------------定義申請人
				map.put("empname", empRepository.findById(bean.getEmpid()).get().getName());
				// -------------定義申請人部門
				map.put("empdept", empRepository.findById(bean.getEmpid()).get().getDept().getDept());
				// -------------定義申請時間
				map.put("applytime", bean.getApplytime().toString().substring(0, 10));
				// -------------定義目前狀態
				map.put("status",
						bean.getApplyfinishtime() == null
						? bean.getApplyaccepttime() == null ? "審查中"
								: bean.getApplytype().equals(1) ? "出借中" : "維修中"
									: bean.getApplytype().equals(1) ? "已歸還" : "已結案");
				// -------------定義狀態顏色
				map.put("textcolor",
						bean.getApplyfinishtime() == null ? bean.getApplyaccepttime() == null ? "orange" : "red":"green");
				// -------------定義通過時間
				map.put("accepttime",
						bean.getApplyaccepttime() == null ? "-" : bean.getApplyaccepttime().toString().substring(0, 10));
				// -------------定義結束時間
				if (locationHtml.equals("list")) {
					map.put("finish",
							bean.getApplyfinishtime() == null ? "-" : bean.getApplyfinishtime().toString().substring(0, 10));
				}
				maps.add(map);
			}
			if(rangeEnd <= mark) {break;}

		}
		return maps;
	}
	

	// ok，查詢單筆清單詳情
	public Map<String, String> selectEAD(String id) {
		List<EquipmentApplyDetailBean> beans = equipmentApplyDetailRepository.findAll();// 找出所有明細
		List<EquipmentListBean> nameGift = equipmentListRepository.findAll();
		EquipmentApplyBean equipmentApplyBean = equipmentApplyRepository.findById(Integer.parseInt(id)).get();
		EmpBean empBean = empRepository.findById(equipmentApplyBean.getEmpid()).get();
		Map<String, String> DetailList = new HashMap<String, String>();
		DetailList.put("applyid", id);//---加入單號
		DetailList.put("empname", empBean.getName());//---申請人
		DetailList.put("empdept", empBean.getDept().getDept());//---申請部門
		DetailList.put("applyreason", equipmentApplyBean.getApplyreason());//---申請原因
		// ---抓出所有相符id的資料
		int checkKey = 0;
		for (EquipmentApplyDetailBean bean : beans) {
			if (bean.getApplyDetailDoublePK().getApplyid().equals(Integer.parseInt(id))) {
				// ---把代號對應的器材名稱找出來
				checkKey++;
				String theName = "";
				for (EquipmentListBean equipmentName : nameGift) {
					if (bean.getApplyDetailDoublePK().getEquipmentid() == equipmentName.getEquipmentid()) {
						theName = equipmentName.getEquipment();
					}
				}
				DetailList.put("equipmentName" + checkKey, theName);
				DetailList.put("quantity" + checkKey, bean.getQuantity());
			}
		}
		return DetailList;
	}

	// ok新增租/借維修單
	public EquipmentApplyBean insert(Map<String, String> map) {
		EquipmentApplyBean insert = null;
		if (map != null) {
			EquipmentApplyBean bean = new EquipmentApplyBean();
			// ------------將map資訊轉化成bean-------------
			bean.setApplytype(Integer.parseInt(map.get("eqType")));// --維修還是租借
			bean.setEmpid(Integer.parseInt(map.get("eqName")));// --申請人是誰
			bean.setApplyreason(map.get("applyReason"));// --申請原因
			bean.setApplytime(new Date());// --申請時間
			insert = equipmentApplyRepository.save(bean);
			insertEAD(insert.getApplyid(), map);
//			sendEmail(empRepository.findById(insert.getEmpid()).get().getEmail()
//					, insert
//					, selectEAD(insert.getApplyid().toString())
//					, "new");
		}
		return insert;
	}

	// ok新增租借單詳細資訊
	public EquipmentApplyDetailBean insertEAD(Integer applyID, Map<String, String> map) {
		EquipmentApplyDetailBean insert = new EquipmentApplyDetailBean();
		int detailSize = map.size() - 3;// --取得資料量
		List<EquipmentListBean> ELbeans = equipmentListRepository.findAll();// --取得器材清單進行比對
		for (int i = 1; i <= detailSize; i++) {
			Integer equipmentID = null;
			String equipment = "equipment" + i;// --取得key名稱
			String quantity = "quantity" + i;// --取得key名稱
			for (EquipmentListBean elbean : ELbeans) {
				// -----找出器材
				if (elbean.getEquipment().equals(map.get(equipment))) {
					// ----找到器材後
					equipmentID = elbean.getEquipmentid();
					// ------------將map資訊轉化成bean-------------
					EquipmentApplyDetailBean bean = new EquipmentApplyDetailBean();
					ApplyDetailDoublePK pk = new ApplyDetailDoublePK();// --設定好雙外鍵
					pk.setApplyid(applyID);
					pk.setEquipmentid(equipmentID);
					bean.setApplyDetailDoublePK(pk);// --輸入資料
					bean.setQuantity(map.get(quantity));
					insert = equipmentApplyDetailRepository.save(bean);
				}
			}
		}

		return insert;
	}

	// ok，更新單筆資料進度
	public EquipmentApplyBean update(String formID, String yesOrNo) {
		EquipmentApplyBean noAns = null;
		Optional<EquipmentApplyBean> result = equipmentApplyRepository.findById(Integer.parseInt(formID));
		if (result.isPresent()) {
			EquipmentApplyBean update = result.get();
			if (update != null && update.getApplyid() != null) {
				if (equipmentApplyRepository.existsById(update.getApplyid())) {
					if (yesOrNo.equals("pass")) {
						if (update.getApplyaccepttime() == null) {
							update.setApplyaccepttime(new Date());
						} else {
							update.setApplyfinishtime(new Date());
						}
					} else {
						Integer byeBye = 10;
						update.setApplytype(byeBye);
					}
					EquipmentApplyBean success = equipmentApplyRepository.save(update);
//					sendEmail(empRepository.findById(update.getEmpid()).get().getEmail()
//							, success
//							, selectEAD(update.getApplyid().toString())
//							, "old");
					return success;
				}
			}
		}
		return noAns;
	}
	
	// ---電子郵件
	private void sendEmail(String mail, EquipmentApplyBean bean,Map<String, String> ead,String newOrOld) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		//----新的單子
		String subjectNew = "您的"+
						(bean.getApplytype().equals(1)? "租借":"維修")+
						"單號："+
						(bean.getApplyid().toString())+
						"，"+
						"已經完成申請";
		//----更新的單子
		String subject = "您的"+
						(bean.getApplytype().equals(1)? "租借":"維修")+
						"單號："+
						(bean.getApplyid().toString())+
						"，"+
						"已經"+
						(bean.getApplytype().equals(10)? "退件":
							bean.getApplyfinishtime()==null?"通過審核":
							bean.getApplytype().equals(1)?"完成歸還":"維修完成");
		String content = "<p>您好，您的案件進度已經更新</p>"+
						"<br>"+
						"<p>詳細資訊：</p>"+
						"<p>申請人："+ead.get("empname")+"</p>"+
						"<p>所屬部門："+ead.get("empdept")+"</p>"+
						"<p>申請事由："+ead.get("applyreason")+"</p>"+"<br>";
		for(int i = 1; i<= (ead.size()-5);i++) {
			content = content+"<p>器材："+ead.get("equipmentName"+i)+"，數量："+ead.get("quantity"+i)+"</p>";
		}
		content = content+"<br>"+"<br>"+
				"<p>如您未對本案件沒有印象，請儘速聯繫總務部。</p>";
		try {
			helper = new MimeMessageHelper(message,true,"UTF-8");
			helper.setFrom("howardwang904@gmail.com", "IBoss");
			helper.setTo(mail);
			helper.setSubject(newOrOld.equals("new")? subjectNew:subject);
			helper.setText(content,true);
			javaMailSender.send(message);
		} catch (Exception e) {e.toString();}
		
		
	}
	

}
