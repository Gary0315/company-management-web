package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.PunchListBean;
import tw.com.ispan.eeit48.service.PunchListService;

@RestController
@RequestMapping(path= {"/api/punchrecord"})
public class PunchListController {
	
	@Autowired
	private PunchListService punchListService;
	
	//查詢1號員工的打卡紀錄
	@GetMapping("/1")
	public ResponseEntity<List<PunchListBean>> findAll1() {
		List<PunchListBean> result = punchListService.select1(null);

		return ResponseEntity.ok(result);
	
	}
	
	//查詢2號員工打卡紀錄
	@GetMapping("/2")
	public ResponseEntity<List<PunchListBean>> findAll2() {
		List<PunchListBean> result = punchListService.select2(null);

		return ResponseEntity.ok(result);
	
	}
	
	//查詢3號員工打卡紀錄
	@GetMapping("/3")
	public ResponseEntity<List<PunchListBean>> findAll3() {
		List<PunchListBean> result = punchListService.select3(null);

		return ResponseEntity.ok(result);
	
	}
	
	//查詢某位員工當天打卡紀錄
	@GetMapping("/today/{empid}")
	public ResponseEntity<List<PunchListBean>> findToday(@PathVariable("empid") Integer empid){
		List<PunchListBean> result = punchListService.selectToday(empid);
		return ResponseEntity.ok(result);
	}
	
	//查詢所有員工打卡紀錄
	@GetMapping("/all")
	public ResponseEntity<List<PunchListBean>> findAll() {
		List<PunchListBean> result = punchListService.selectAll();

		return ResponseEntity.ok(result);
	
	}
	
	//新增打卡紀錄
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PunchListBean bean){
		PunchListBean result = punchListService.insert(bean);
		
		if(result!=null) {
			//成功201
			URI uri=URI.create("/api/punchrecord/"+result.getPunchid());
			return ResponseEntity.created(uri).body(result);
			
		}else {
			//錯誤204
			return ResponseEntity.noContent().build();
		}
	}
	
	
}
