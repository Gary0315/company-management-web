package tw.com.ispan.eeit48;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.DeptlistBean;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.PositionlistBean;
import tw.com.ispan.eeit48.service.ProfileService;

@SpringBootTest
public class ProfileServiceTests {

	@Autowired
	private ProfileService profileService;

	@Test
	public void testSelect() {
		List<EmpBean> selects = profileService.select(null);
		System.out.println("==================");
		System.out.println("selects=" + selects);
		System.out.println("==================");
	}

//	@Test
	public void testInsert() {
		EmpBean insert = new EmpBean();
		DeptlistBean dept = new DeptlistBean();
		PositionlistBean posi = new PositionlistBean();
		
		insert.setEmpid(0);
		insert.setName("test0928");
		insert.setPasswd("456");
		insert.setPasswd2("456");
		insert.setIdnumber("B987654321");
		insert.setBirthday(new java.util.Date());
		insert.setSex("男");
		insert.setOnboarddate(new java.util.Date());
		dept.setDeptid(1);
		insert.setDept(dept);;
		posi.setPositionid(2);
		insert.setPosition(posi);
		insert.setPhone("091234567");
		insert.setEmail("678@gmail.com");
		insert.setAddr("哈哈哈");
		insert.setEmergencycontact("嘿嘿嘿");
		insert.setEmergencyphone("0987654321");
		EmpBean result = profileService.insert(insert);
		System.out.println("==================");
		System.out.println("insertResult=" + result);
		System.out.println("==================");
	}

//	@Test
	public void testUpdate() {
		EmpBean update = new EmpBean();
		DeptlistBean deptUpdate = new DeptlistBean();
		PositionlistBean posiUpdate = new PositionlistBean();		
		
		update.setEmpid(3);
		update.setName("test0927-1");
		update.setPasswd("456");
		update.setPasswd2("456");
		update.setIdnumber("B987654321");
		update.setBirthday(new java.util.Date());
		update.setSex("男");
		update.setOnboarddate(new java.util.Date());
		deptUpdate.setDeptid(1);
		update.setDept(deptUpdate);;
		posiUpdate.setPositionid(2);
		update.setPosition(posiUpdate);
		update.setPhone("091234567");
		update.setEmail("678@gmail.com");
		update.setAddr("哈哈哈");
		update.setEmergencycontact("嘿嘿嘿");
		update.setEmergencyphone("0987654321");
		EmpBean result = profileService.update(update);
		System.out.println("==================");
		System.out.println("updateResult=" + result);
		System.out.println("==================");
	}

//	@Test
	public void testDelet() {
		EmpBean delete = new EmpBean();
		delete.setEmpid(3);
		boolean result = profileService.delete(delete);
		System.out.println("==================");
		System.out.println("deleteResult=" + result);
		System.out.println("==================");

	}
}
