package tw.com.ispan.eeit48.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.EmpOrderBean;
import tw.com.ispan.eeit48.service.EmpOrderService;

@RestController
@RequestMapping(path = {"/api/EmpOrders"})
public class EmpOrderController {
	@Autowired
	private EmpOrderService empOrderService;
	
	@GetMapping("/history")
	public ResponseEntity<Page<EmpOrderBean>> orderRecord(@RequestParam Integer empid,Integer start , Integer end ) {
		Page<EmpOrderBean> record = empOrderService.orderRecord(empid,start, end);
		return ResponseEntity.ok(record);
	}
	
	@GetMapping("/allhistory")
	public ResponseEntity<Page<EmpOrderBean>> allHistory(@RequestParam Integer start , Integer end) {
		Page<EmpOrderBean> record = empOrderService.allHistory(start, end);
		return ResponseEntity.ok(record);
	}
	
	@GetMapping("/empdatehistory")
	public ResponseEntity<Page<EmpOrderBean>> empDateHistory(@RequestParam String orderdatefirst,String orderdatelast,Integer empid,Integer start , Integer end) throws ParseException  {
		Page<EmpOrderBean> record = empOrderService.orderRecord(orderdatefirst,orderdatelast,empid,start, end);
		return ResponseEntity.ok(record);
	}
	
	@GetMapping("/alldatehistory")
	public ResponseEntity<Page<EmpOrderBean>> allDateHistory(@RequestParam String orderdatefirst,String orderdatelast,Integer empid,Integer start , Integer end) throws ParseException  {
		Page<EmpOrderBean> record = empOrderService.allOrderRecord(orderdatefirst,orderdatelast,start, end);
		return ResponseEntity.ok(record);
	}
	
	@GetMapping("/today")
	public ResponseEntity<List<Object[]>> todayRecord() throws ParseException {
		List<Object[]> record = empOrderService.today();
		return ResponseEntity.ok(record);
	}

}
