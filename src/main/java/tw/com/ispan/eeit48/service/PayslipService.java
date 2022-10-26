package tw.com.ispan.eeit48.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.PayslipRepository;
import tw.com.ispan.eeit48.domain.PayslipBean;

@Service
@Transactional
public class PayslipService {

	@Autowired
	private PayslipRepository payslipRepository;
	
	@Autowired
	private EmpOrderService empOrderService;
	
	//透過id查詢
	public PayslipBean select(Integer empid) {
		
		
		if(empid!=null) {
			PayslipBean payslipBean = payslipRepository.getPayslipBean(empid);
			return payslipBean;
		}
		
		return null;
		
		
	}
	
	//透過id/年/月查詢
	public PayslipBean selectByYearMonth(Integer empid,Integer year,Integer month) {
		
		
		if(empid!=null) {
			PayslipBean payslipBean = payslipRepository.getPayslipBeanByYearMonth(empid,year,month);
			return payslipBean;
		}
		
		return null;
		
		
	}
	
	//修改
		public PayslipBean update(PayslipBean bean) {
			PayslipBean result = null;
			if(bean!=null && bean.getSalaryid()!=null) {
				if(payslipRepository.existsById(bean.getSalaryid())) {
					return payslipRepository.save(bean);
				}
			}
			return result;
		}
		
		

	//修改伙食費
		public PayslipBean updateMealCost(PayslipBean bean,Integer year,Integer month) {
			PayslipBean result = null;
			if(bean!=null && bean.getSalaryid()!=null) {
				if(payslipRepository.existsById(bean.getSalaryid())) {
					String paymethod = "salary";
					Integer mealCost = empOrderService.mealCost(bean.getSalaryid(), paymethod, year, month);
					bean.setMealcost(mealCost);
					return payslipRepository.save(bean);
				}
			}
			return result;
		}
	
			
		
}
