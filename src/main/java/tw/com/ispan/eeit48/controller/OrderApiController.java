package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.OrderlistBean;
import tw.com.ispan.eeit48.domain.RestaurantBean;
import tw.com.ispan.eeit48.service.OrderlistService;
import tw.com.ispan.eeit48.utils.JwtToken;


@RestController
@RequestMapping(path = {"/api/orders"})
public class OrderApiController {
	@Autowired
	private OrderlistService orderlistService;
	
	@GetMapping
	public ResponseEntity<List<OrderlistBean>> findAll() {
		List<OrderlistBean> result = orderlistService.select(null);
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/menu")
	public ResponseEntity<?> selectToday(@RequestHeader("Authorization") String au) {
		    String token = au.substring(6);
		    JwtToken jwtToken = new JwtToken();

		    try {
		        jwtToken.validateToken(token);
		    } catch (AuthException e) {
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		    }
		    List<OrderlistBean> result = orderlistService.selectToday();
		    return ResponseEntity.status(HttpStatus.OK).body(result);
		// return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrderlistBean bean) {
		OrderlistBean result = orderlistService.insert(bean);
		if(result!=null) {
			URI uri = URI.create("/api/orders/"+result.getOrderitemid());
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/a")
	public ResponseEntity<?> createa(@RequestBody List<OrderlistBean> bean) {
		 List<OrderlistBean> result = orderlistService.inserta(bean);
		if(result!=null) {
			URI uri = URI.create("/api/orders/a/1");
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id) {
		OrderlistBean bean = new OrderlistBean();
		bean.setOrderitemid(id);
		List<OrderlistBean> result = orderlistService.select(bean);
		if(result!=null && !result.isEmpty()) {
			System.out.println(result);
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/choose/{id}")
	public ResponseEntity<?> findRestaurantid(@PathVariable("id") Integer id) {
		List<OrderlistBean> result = orderlistService.selectRestaurant(id);
		if(result!=null && !result.isEmpty()) {
			System.out.println(result);
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
		OrderlistBean bean = new OrderlistBean();
		bean.setOrderitemid(id);
		boolean result = orderlistService.delete(bean);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody OrderlistBean bean) {
		OrderlistBean result = orderlistService.update(bean);
		if(result!=null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
