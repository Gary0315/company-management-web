package tw.com.ispan.eeit48.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.PunchListRepository;
import tw.com.ispan.eeit48.domain.PunchListBean;

@Service
@Transactional
public class PunchListService {
	
	@Autowired
	private PunchListRepository punchListRepository;
	

	//員工編號為1
	public List<PunchListBean> select1(Integer empid) {
//		List<PunchListBean> punchListBean = punchListRepository.findAll();
//	return punchListBean;
		
		if(empid==null) {
			List<PunchListBean> punchListBean = punchListRepository.select(1);
			return punchListBean;

		}
		return null;
		
	}
	
	//員工編號為2
	public List<PunchListBean> select2(Integer empid) {

		if(empid==null) {
			List<PunchListBean> punchListBean = punchListRepository.select(2);
			return punchListBean;
		}
		return null;
	}
	
	//員工編號為3
	public List<PunchListBean> select3(Integer empid) {

		if(empid==null) {
			List<PunchListBean> punchListBean = punchListRepository.select(3);
			return punchListBean;
		}
		return null;
	}
	
	//找某員工編號-今日資料
	public List<PunchListBean> selectToday(Integer empid){
		List<PunchListBean> result = null;
		if(empid!=null) {
			result = punchListRepository.selectToday(empid);
		}else {
			result = punchListRepository.findAll();
		}
		return result;
	}
	
	//找全部
	public List<PunchListBean> selectAll(){
		List<PunchListBean> punchListBean = punchListRepository.selectAllData();
		return punchListBean;
	}
	
	//新增
	public PunchListBean insert(PunchListBean bean) {
		PunchListBean result = null;
		if(bean!=null && bean.getPunchid()!=null) {
			if(!punchListRepository.existsById(bean.getPunchid())) {
				result=punchListRepository.save(bean);
			}
		}

		return result;
	}
}
