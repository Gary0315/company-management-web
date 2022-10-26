package tw.com.ispan.eeit48;

import java.io.Serializable;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.PunchListBean;

@SpringBootTest
public class PunchListBeanTests {
	
	@PersistenceContext
	private Session session;
	
	@Test
	public void testSave() {
		PunchListBean insert = new PunchListBean();
		insert.setPunchid(4);
		insert.setEmpid(1);
		insert.setPunchtime(new java.util.Date());
		insert.setPunchtype("上班");
		
		Serializable pk = session.save(insert);
		System.out.println("PK="+pk);
	}
	
	
}
