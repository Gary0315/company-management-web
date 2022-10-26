package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.PackageBean;
import tw.com.ispan.eeit48.service.PackageService;

@RestController
@RequestMapping(path = { "/api/receive" })
public class ReceiveController {
	@Autowired
	private PackageService packageService;

	//-------------------主要功能------------------
	//ok，填寫收貨新單、寄貨新單
	@PostMapping("/newForm")
	public ResponseEntity<?> getNew(@RequestBody PackageBean bean) {
		PackageBean result = packageService.insert(bean);
		if(result!=null) {
			URI uri = URI.create("/api/receive/"+result.getPackageid());
			return ResponseEntity.created(uri).body(result);
		}else {return ResponseEntity.noContent().build();}
	}
	
	//ok，以各種條件搜尋單據
	@GetMapping("/select/{condition}/{page}/{fromWhere}")
	public ResponseEntity<?> select(@PathVariable("condition")String condition,
			@PathVariable("page")Integer page , @PathVariable("fromWhere") String locationHtml) {
		List<Map<String, String>> result = packageService.select(condition,page,locationHtml);
		if(result!=null) {return ResponseEntity.ok(result);
		}else {return ResponseEntity.notFound().build();}
	}
	
	//找出單筆單據詳細內容
	@GetMapping("/formCondition/{id}")
	public ResponseEntity<?> formSelect(@PathVariable("id")String id){
		Map<String, String> result = packageService.formCondition(id);
		if(result!=null) {return ResponseEntity.ok(result);
		}else {return ResponseEntity.notFound().build();}
	}
	
	
	//ok，更新多筆單據的進度
	@PutMapping("/formUpdate/{formID}")
	public ResponseEntity<?> update(@PathVariable("formID")String formID) {
		PackageBean result = packageService.update(formID);
		//成功200，錯誤:404
		if(result!=null) {return ResponseEntity.ok(result);
		}else {return ResponseEntity.notFound().build();}
	}
	
	
}
