package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.AnnoucementBean;
import tw.com.ispan.eeit48.service.AnnouncementService;

@RestController
@RequestMapping(path = {"/api/annoucement"})
public class AnnoucementController {
	
	@Autowired
	private AnnouncementService announcementService;
	
	//公告草稿
	@GetMapping("/draft")
	public ResponseEntity<List<AnnoucementBean>> findAll(){
		List<AnnoucementBean> result = announcementService.select(null);

		return ResponseEntity.ok(result);
	}
	
//	@GetMapping
//	@RequestMapping(path = {"/api/annoucementlist/"})
//	public ResponseEntity<List<AnnoucementBean>> findList(){
//		List<AnnoucementBean> result = announcementService.selectlist(null);
//
//		return ResponseEntity.ok(result);
//	}
	
	//公告列表
	@GetMapping("/title")
	public ResponseEntity<List<AnnoucementBean>> findTitle(){
		List<AnnoucementBean> result = announcementService.selectdesc(null);

		return ResponseEntity.ok(result);
	}
	
	
	//更改公告狀態
	@PutMapping("/{announcementid}")
	public ResponseEntity<?> update(@PathVariable(name="announcementid")Integer id,@RequestBody AnnoucementBean bean){
		AnnoucementBean result = announcementService.updel(bean);
		if(result!=null) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	//索取公告最後的ID
	@GetMapping("/all")
	public ResponseEntity<List<AnnoucementBean>> findAllid(){
		List<AnnoucementBean> result = announcementService.selectAll();
		return ResponseEntity.ok(result);
	}
	
	//新增公告
	@PostMapping
	public ResponseEntity<?> create(@RequestBody AnnoucementBean bean){
		AnnoucementBean result= announcementService.insert(bean);
		if(result!=null) {
			//成功:201
			URI uri = URI.create("/api/annoucement/"+result.getAnnouncementid());
			return ResponseEntity.created(uri).body(result);
		}else {
			//錯誤:204
			return ResponseEntity.noContent().build();
		}
		
	}
	
	
	

}
