package tw.com.ispan.eeit48;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.LeaveListBean;
import tw.com.ispan.eeit48.domain.LeaveStatusBean;
import tw.com.ispan.eeit48.domain.LeaveTypeBean;
import tw.com.ispan.eeit48.service.LeaveListService;

@SpringBootTest
public class LeaveListServiceTests {

	@Autowired
	private LeaveListService leaveListService;
	
//	@Test
	public void testSelect() {
		List<LeaveListBean> result = leaveListService.select(1);
		System.out.println("單一職員請假紀錄 result ="+result);
	}
	
//	@Test
	public void testSelectDept() {
		List<LeaveListBean> result = leaveListService.selectDeptList(1);
		System.out.println("部門職員請假紀錄 result ="+result);
	}
	
	@Test
	public void testSelectAll() {
		List<LeaveListBean> result = leaveListService.select(null);
		System.out.println("所有職員請假紀錄 result ="+result);
	}
	
//	@Test
	public void testInsert() {
		LeaveListBean insert = new LeaveListBean();
		insert.setLeaveid(100);
		EmpBean emp = new EmpBean();
		emp.setEmpid(1);
		insert.setEmp(emp);
		insert.setAgentid(2);
		insert.setLeavestartdate(new java.util.Date());
		insert.setLeaveenddate(new java.util.Date());
		insert.setLeavehour(8);
		LeaveTypeBean ltb = new LeaveTypeBean();
		ltb.setLeavetypeid(1);
		insert.setLeavetype(ltb);
		insert.setLeavereason("感冒");
		LeaveStatusBean lsb =new LeaveStatusBean();
		lsb.setStatusid(1);
		insert.setStatus(lsb);
		insert.setLeaveapplydate(new java.util.Date());
		
		LeaveListBean result = leaveListService.insert(insert);
		System.out.println("insertResult="+result);
		
	}
	
}
