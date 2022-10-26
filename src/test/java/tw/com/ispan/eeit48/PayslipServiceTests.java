package tw.com.ispan.eeit48;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.eeit48.domain.PayslipBean;
import tw.com.ispan.eeit48.service.PayslipService;

@SpringBootTest
public class PayslipServiceTests {
	
	@Autowired
	private PayslipService payslipService;

	
	@Test
	public void testSelect() {
		PayslipBean payslipBean = payslipService.select(1);
		System.out.println(payslipBean);
	}
	
	@Test
	public void testSelectByYearMonth() {
		PayslipBean payslipBean = payslipService.selectByYearMonth(3, 2022, 10);
		System.out.println(payslipBean);
	}
	
	
}
