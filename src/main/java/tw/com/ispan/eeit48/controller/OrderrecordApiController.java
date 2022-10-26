package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.OrderlistBean;
import tw.com.ispan.eeit48.domain.OrderrecordBean;
import tw.com.ispan.eeit48.domain.RestaurantBean;
import tw.com.ispan.eeit48.service.OrderlistService;
import tw.com.ispan.eeit48.service.OrderrecordService;


@RestController
@RequestMapping(path = {"/api/orderrecords"})
public class OrderrecordApiController {
	@Autowired
	private OrderrecordService orderrecordService;
	
	@GetMapping
	public ResponseEntity<List<OrderrecordBean>> findAll() {
		List<OrderrecordBean> result = orderrecordService.select(null);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrderrecordBean bean) {
		OrderrecordBean result = orderrecordService.insert(bean);
		if(result!=null) {
			URI uri = URI.create("/api/orderrecords/"+result.getOrderid());
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id) {
		OrderrecordBean bean = new OrderrecordBean();
		bean.setOrderid(id);
		List<OrderrecordBean> result = orderrecordService.select(bean);
		if(result!=null && !result.isEmpty()) {
			System.out.println(result);
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
