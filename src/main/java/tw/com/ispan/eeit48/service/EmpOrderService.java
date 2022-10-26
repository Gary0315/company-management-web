package tw.com.ispan.eeit48.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.EmpOrderRepository;
import tw.com.ispan.eeit48.domain.EmpOrderBean;

@Service
@Transactional
public class EmpOrderService {
	@Autowired EmpOrderRepository empOrderRepository;
	
	public Page<EmpOrderBean> orderRecord(Integer empid,Integer start , Integer end){
		Page<EmpOrderBean> record = empOrderRepository.history(empid,PageRequest.of(start, end));
		if(record.isEmpty()) {
			return null;
		}else{
			return record;
		}
	}
	
	public Page<EmpOrderBean> orderRecord(String orderdatefirst ,String orderdatelast  ,Integer empid,Integer start , Integer end) throws ParseException{
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date first = sdFormat.parse(orderdatefirst);
		java.util.Date last = sdFormat.parse(orderdatelast);
		Page<EmpOrderBean> record = empOrderRepository.empDateHistory(first,last ,empid,PageRequest.of(start, end));
		if(record.isEmpty()) {
			return null;
		}else{
			return record;
		}
	}
	
	public Page<EmpOrderBean> allOrderRecord(String orderdatefirst ,String orderdatelast,Integer start , Integer end) throws ParseException{
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date first = sdFormat.parse(orderdatefirst);
		java.util.Date last = sdFormat.parse(orderdatelast);
		Page<EmpOrderBean> record = empOrderRepository.dateHistory(first,last,PageRequest.of(start, end));
		if(record.isEmpty()) {
			return null;
		}else{
			return record;
		}
	}
	
	public Page<EmpOrderBean> allHistory(Integer start , Integer end){
		Page<EmpOrderBean> record = empOrderRepository.allHistory(PageRequest.of(start, end));
		if(record.isEmpty()) {
			return null;
		}else{
			return record;
		}
	}
	
	
	
	public Page<EmpOrderBean> empDateOrderRecord(Integer empid,Integer start , Integer end){
		Page<EmpOrderBean> record = empOrderRepository.history(empid,PageRequest.of(start, end));
		if(record.isEmpty()) {
			return null;
		}else{
			return record;
		}
	}
	
	public List<Object[]> today() throws ParseException{
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdFormat.format(new Date());
		List<Object[]> today = empOrderRepository.todayRecord(sdFormat.parse(now));	
		if(today.isEmpty()) {
			return null;
		}else{
			return today;
		}
	}
	
	public Integer mealCost(Integer empid,String paymethod,Integer year,Integer month) {
		Integer mealCost = empOrderRepository.monthTotal(empid, paymethod, year, month);
		return mealCost;
	}
	
}
