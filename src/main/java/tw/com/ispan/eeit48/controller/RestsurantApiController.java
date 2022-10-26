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
import tw.com.ispan.eeit48.domain.RestaurantBean;
import tw.com.ispan.eeit48.service.OrderlistService;
import tw.com.ispan.eeit48.service.RestaurantService;


@RestController
@RequestMapping(path = {"/api/restaurants"})
public class RestsurantApiController {
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping
	public ResponseEntity<List<RestaurantBean>> findAll() {
		List<RestaurantBean> result = restaurantService.select(null);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody RestaurantBean bean) {
		RestaurantBean result = restaurantService.insert(bean);
		if(result!=null) {
			URI uri = URI.create("/api/restaurants/"+result.getRestaurantid());
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id) {
		RestaurantBean bean = new RestaurantBean();
		bean.setRestaurantid(id);
		List<RestaurantBean> result = restaurantService.select(bean);
		if(result!=null && !result.isEmpty()) {
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
		RestaurantBean bean = new RestaurantBean();
		bean.setRestaurantid(id);
		boolean result = restaurantService.delete(bean);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody RestaurantBean bean) {
		RestaurantBean result = restaurantService.update(bean);
		if(result!=null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
