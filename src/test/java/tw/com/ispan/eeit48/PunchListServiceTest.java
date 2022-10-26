package tw.com.ispan.eeit48;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.PunchListBean;
import tw.com.ispan.eeit48.service.PunchListService;

@SpringBootTest
public class PunchListServiceTest {
	@Autowired
	private PunchListService punchListService;
	
	//@Test
	public void testSelect() {
		List<PunchListBean> punchListBean = punchListService.select1(null);
		System.out.println(punchListBean);
	}
	
	//@Test
	public void testSelectAll() {
		List<PunchListBean> punchListBean = punchListService.selectAll();
		System.out.println(punchListBean);
	}
	
	@Test
	public void testSelectToday() {
		List<PunchListBean> punchListBean = punchListService.selectToday(1);
		System.out.println(punchListBean);
	}
	
	//@Test
	public void testInsert() {
		PunchListBean insert = new PunchListBean();
		insert.setPunchid(1200);
		insert.setEmpid(1);
		insert.setPunchtime(new java.util.Date());
		insert.setPunchtype("上班");
		
		PunchListBean result = punchListService.insert(insert);
		System.out.println("insertResult="+result);
	}

	
}
