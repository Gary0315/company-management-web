package tw.com.ispan.eeit48;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import tw.com.ispan.eeit48.dao.EmpOrderRepository;
import tw.com.ispan.eeit48.domain.EmpOrderBean;
import tw.com.ispan.eeit48.service.EmpOrderService;

@SpringBootTest
public class EmpOrderTests {

	@Autowired
	private EmpOrderRepository empOrderRepository;
	
	@Autowired
	private EmpOrderService empOrderService;
	
	//@Test
	public void method () {
		
	Page<EmpOrderBean> record =	empOrderRepository.history(3,PageRequest.of(1, 5));
	System.out.println(record.toList());
	}
	
	
	@Test
	public void method1 () {
	Integer total = empOrderService.mealCost(3,"salary",2022,10);
	System.out.println(total);
	}
	
	//@Test
	public void method2 () throws ParseException {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdFormat.format(new Date());
		List<Object[]> today = empOrderRepository.todayRecord(sdFormat.parse(now));
		for(int i=0;i<today.size();i++) {
		Object[] array = today.get(i);
		String meal = (String)array[0];
		System.out.println("OK:"+meal+":"+array[1]+":"+array[2]);
	}
	
	}
	
	//@Test
		public void method3() {
		Page<EmpOrderBean> record =	empOrderRepository.allHistory(PageRequest.of(1, 5));
		System.out.println(record.toList());
		}
}
