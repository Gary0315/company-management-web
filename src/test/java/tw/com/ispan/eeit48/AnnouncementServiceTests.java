package tw.com.ispan.eeit48;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.AnnoucementBean;
import tw.com.ispan.eeit48.service.AnnouncementService;

@SpringBootTest
public class AnnouncementServiceTests {

	@Autowired
	private AnnouncementService announcementService;
	
	//@Test
	public void testUpdel() {
		AnnoucementBean insert = new AnnoucementBean();
		insert.setAnnouncementid(4);
		insert.setAnnouncementtime(new java.util.Date());
		insert.setAnnouncementtitle("xxx");
		insert.setContext("OXOXO");
		insert.setEmpid(1);
		insert.setDeptid(1);
		insert.setAnnouncementtype(2);
		
		AnnoucementBean result= announcementService.updel(insert);
		System.out.println("updal="+result);
	}
	
	//@Test
	public void testSelectAll() {
		List<AnnoucementBean> result = announcementService.selectAll();
		System.out.println("ALL="+result);
	}
	
	@Test
	public void insert() {
		AnnoucementBean insert = new AnnoucementBean();
		insert.setAnnouncementid(7);
		insert.setAnnouncementtime(new java.util.Date());
		insert.setAnnouncementtitle("xxx");
		insert.setContext("OXOXO");
		insert.setEmpid(1);
		insert.setDeptid(1);
		insert.setAnnouncementtype(1);
		
		AnnoucementBean result = announcementService.insert(insert);
		System.out.println("insertResult="+result);
	}
	
}
