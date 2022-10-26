package tw.com.ispan.eeit48.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.PayslipBean;
import tw.com.ispan.eeit48.service.EmpOrderService;
import tw.com.ispan.eeit48.service.PayslipService;

@RestController
@RequestMapping(path= {"/api/payslips"})
public class PayslipController {

	@Autowired
	private PayslipService payslipService;
	
	@Autowired
	private EmpOrderService empOrderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id) {
		
		PayslipBean payslipBean	= payslipService.select(id);
		System.out.println(payslipBean);
		if(payslipBean!=null) {
			//成功:200
			return ResponseEntity.ok(payslipBean);
		}else {
			//錯誤:404
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/{year}/{month}")
	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id,@PathVariable("year") Integer year,@PathVariable("month") Integer month) {
		
		PayslipBean payslipBean	= payslipService.selectByYearMonth(id, year, month);
		System.out.println(payslipBean);
		if(payslipBean!=null) {
			//成功:200
			return ResponseEntity.ok(payslipBean);
		}else {
			//錯誤:404
			return ResponseEntity.notFound().build();
		}
	}
	
//	@PutMapping("/{id}/{year}/{month}")
//	public ResponseEntity<?> changeMealCost(@PathVariable("id") Integer id,@PathVariable("year") Integer year,@PathVariable("month") Integer month,@RequestBody PayslipBean bean) {
//		String paymethod = "salary";
//		Integer mealCost = empOrderService.mealCost(id, paymethod, year, month);
//		PayslipBean payslipBean	= payslipService.select(id);
//		
//		if(payslipBean!=null) {
//			payslipBean.setMealcost(mealCost);
//			PayslipBean result = payslipService.update(payslipBean);
//			System.out.println(mealCost);
//			return ResponseEntity.ok(result);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//		
//	}
		
	
	@PutMapping("/{id}/{year}/{month}")
	public ResponseEntity<?> changeMealCost(@PathVariable("id") Integer id,@PathVariable("year") Integer year,@PathVariable("month") Integer month,@RequestBody PayslipBean bean) {
		
		PayslipBean payslipBean	= payslipService.select(id);
		
		if(payslipBean!=null) {
			PayslipBean result = payslipService.updateMealCost(payslipBean,2022,10);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
