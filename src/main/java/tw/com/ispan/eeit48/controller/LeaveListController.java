package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.domain.LeaveListBean;
import tw.com.ispan.eeit48.service.LeaveListService;

@RestController
@RequestMapping(path= {"/api/LeaveLists"})
public class LeaveListController {

	@Autowired
	private LeaveListService leaveListService;
	
	//透過empid查詢該職員所有請假紀錄
		@GetMapping
		public ResponseEntity<?> findAll() {
			
			
			List<LeaveListBean> Beans = leaveListService.select(null);
			System.out.println(Beans);
			if(Beans!=null) {
				//成功:200
				return ResponseEntity.ok(Beans);
			}
			else {
				//錯誤:404
				return ResponseEntity.notFound().build();
			}
			
		}
	
	//透過empid查詢該職員所有請假紀錄
	@GetMapping("/{empid}")
	public ResponseEntity<?> findByEmpId(@PathVariable("empid") Integer empid) {
		
		
		List<LeaveListBean> Beans = leaveListService.select(empid);
		System.out.println(Beans);
		if(Beans!=null) {
			//成功:200
			return ResponseEntity.ok(Beans);
		}
		else {
			//錯誤:404
			return ResponseEntity.notFound().build();
		}
		
	}
	
	//透過bossempid查詢該部門職員的所有請假紀錄
		@GetMapping("/{bossempid}/{positionid}")
		public ResponseEntity<?> findByBossEmpId(@PathVariable("bossempid") Integer bossempid,@PathVariable("positionid") Integer positionid) {
			
			List<LeaveListBean> Beans = leaveListService.selectDeptList(bossempid);
			System.out.println(Beans);
			if(Beans!=null) {
				//成功:200
				return ResponseEntity.ok(Beans);
			}
			else {
				//錯誤:404
				return ResponseEntity.notFound().build();
			}
			
		}	
	
	//新增請假紀錄
	@PostMapping
	public ResponseEntity<?> create(@RequestBody LeaveListBean bean) {
		LeaveListBean result = leaveListService.insert(bean);
		if(result!=null) {
			//成功:201
			URI uri = URI.create("/api/LeaveLists/"+result.getLeaveid());
			return ResponseEntity.created(uri).body(result);
		}else {
			//錯誤:204
			return ResponseEntity.noContent().build();
		}
		
	}
	
	//修改:取得前端選擇的第幾筆項目
	@PutMapping("/{checkboxNum}")
	public ResponseEntity<?> update(@PathVariable("checkboxNum") Integer checkboxNum, @RequestBody LeaveListBean bean) {
		LeaveListBean result = leaveListService.update(bean);
		if(result!=null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
