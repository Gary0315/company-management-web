package tw.com.ispan.eeit48.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.LeaveListRepository;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.LeaveListBean;

@Service
@Transactional
public class LeaveListService {

	@Autowired
	private LeaveListRepository leaveListRepository;
	
	//查詢單一職員請假紀錄
	public List<LeaveListBean> select(Integer empid) {
		List<LeaveListBean> result = null;
		if(empid != null) {
			result = leaveListRepository.getLists(empid);
		}else{
			//null時,查詢全部
			result = leaveListRepository.findAll();
		}
		return result;
		
	}
	
	//查詢部門職員請假紀錄
		public List<LeaveListBean> selectDeptList(Integer bossempid) {
			if(bossempid != null) {
				List<LeaveListBean> result = leaveListRepository.getDeptLists(bossempid);
				return result;
			}
			return null;
			
		}
	
	//新增
	public LeaveListBean insert(LeaveListBean bean) {
		LeaveListBean result = null;
		if(bean!=null && bean.getLeaveid()!=null) {
			
			if(!leaveListRepository.existsById(bean.getLeaveid())) {
				result = leaveListRepository.save(bean);
			}
						
		}
		return result;
	}
	
	//修改
	public LeaveListBean update(LeaveListBean bean) {
		LeaveListBean result = null;
		if(bean!=null && bean.getLeaveid()!=null) {
			if(leaveListRepository.existsById(bean.getLeaveid())) {
				return leaveListRepository.save(bean);
			}
		}
		return result;
	}
	
}
