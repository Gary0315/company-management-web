package tw.com.ispan.eeit48;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.dao.EmpRepository;
import tw.com.ispan.eeit48.dao.OrderlistRepository;
import tw.com.ispan.eeit48.domain.DeptlistBean;
import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.OrderlistBean;
import tw.com.ispan.eeit48.domain.PositionlistBean;

@SpringBootTest
public class joinTests {

	@Autowired
	EmpRepository empRepository;
	@Autowired
	OrderlistRepository orderlistRepository;

	@Test
	public void method() {

		EmpBean e = empRepository.login(2);
		System.out.println(e);

		List<EmpBean> x = empRepository.loginjoin();
		System.out.println(x.get(0).getDept().getDept() + ":" + x.get(0).getPosition().getPosition());

		EmpBean emp = new EmpBean();
		DeptlistBean dept = new DeptlistBean();
		
		PositionlistBean posi = new PositionlistBean();
		dept.setDeptid(1);
		posi.setPositionid(1);
		emp.setEmpid(6);
		emp.setName("哈哈哈");
		emp.setPasswd("456");
		emp.setPasswd2("456");
		emp.setIdnumber("sss");
		emp.setBirthday(new java.util.Date(0));
		emp.setSex("男");
		emp.setOnboarddate(new java.util.Date(0));
		emp.setDept(dept);
		emp.setPosition(posi);
		emp.setPhone("5445");
		emp.setEmail("sdsad");
		emp.setAddr("45664");
		emp.setEmergencycontact("sss");
		emp.setEmergencyphone("45646");
		
		
		EmpBean eee = empRepository.save(emp);
		System.out.println(eee);
	}

}
