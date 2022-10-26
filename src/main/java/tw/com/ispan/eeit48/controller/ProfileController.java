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

import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
@Autowired
private ProfileService profileService;

@GetMapping("/select")
public ResponseEntity<List<EmpBean>> findAll() {
	List<EmpBean> result = profileService.select(null);

	return ResponseEntity.ok(result);
}

@GetMapping("/select/{id}")
public ResponseEntity<?> findByPrimaryKey(@PathVariable("id") Integer id ) {
	EmpBean bean= new EmpBean();
	bean.setEmpid(id);
	List<EmpBean> result = profileService.select(bean);
	if (result!=null && !result.isEmpty()) {
		return ResponseEntity.ok(result.get(0));
	}else {
		return ResponseEntity.notFound().build();
	}
}

@PostMapping("/create")
public ResponseEntity<?> create(@RequestBody EmpBean bean) {
	EmpBean result = profileService.insert(bean);
	if (result!=null) {
		URI uri= URI.create("/profiles/create"+result.getEmpid());
		return ResponseEntity.created(uri).body(result);
	}else {
		return ResponseEntity.noContent().build();
	}
}


@DeleteMapping("/remove/{id}")
public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
	EmpBean bean= new EmpBean();
	bean.setEmpid(id);
	boolean result = profileService.delete(bean);
	if (result) {
		return ResponseEntity.noContent().build(); //204
	} else {
		return ResponseEntity.notFound().build();  //404
	}
}

@PutMapping("/update/{id}")
public ResponseEntity<?> update(@PathVariable("id") Integer id,@RequestBody EmpBean bean) {
	EmpBean result = profileService.update(bean);
	System.out.println("update: " + result);
	if (result!=null) {
		return ResponseEntity.ok(result); //200
	} else {
		return ResponseEntity.notFound().build();  //404
	}
}
	
}
