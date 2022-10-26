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

import tw.com.ispan.eeit48.domain.EquipmentApplyBean;
import tw.com.ispan.eeit48.service.EquipmentApplyService;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentApplyService equipmentApplyService;
	
	//-------------------主要功能------------------
	//ok，依照條件搜尋所有單據
	@GetMapping("/select/{condition}/{page}/{fromWhere}")
	public ResponseEntity<?> select(@PathVariable("condition")String condition,
			@PathVariable("page")Integer page,@PathVariable("fromWhere")String locationHtml) {
		List<Map<String, String>> resuList = equipmentApplyService.select(condition,page,locationHtml);
		if(resuList!=null) {return ResponseEntity.ok(resuList);
		}else {return ResponseEntity.notFound().build();}
	}
	
	//ok，填寫租借單、維修單
	@PostMapping("/formNew")
	public ResponseEntity<?> equipmentNewForm(@RequestBody Map<String, String> map) {
		EquipmentApplyBean result = equipmentApplyService.insert(map);
		if(result!=null) {
			URI uri = URI.create("/api/equipment/"+result.getApplyid());
			return ResponseEntity.created(uri).body(result);
		}else {return ResponseEntity.noContent().build();}
	}
	
	//ok，取得單筆單據詳細資料
	@GetMapping("/formContent/{applyid}")
	public ResponseEntity<?> findOneContent(@PathVariable("applyid")String id) {
		Map<String, String> map = equipmentApplyService.selectEAD(id);
		if(map!=null) {return ResponseEntity.ok(map);
		}else {return ResponseEntity.notFound().build();}
	}
	
	//ok，更新單筆單據進度
	@PutMapping("/formUpdate/{formID}/{yesOrNo}")
	public ResponseEntity<?> updateForm(@PathVariable("formID")String formID ,@PathVariable("yesOrNo")String yesOrNo) {
		//更新指定的單筆資料
		EquipmentApplyBean result = equipmentApplyService.update(formID,yesOrNo);
		if(result!=null) {return ResponseEntity.ok(result);
		}else {return ResponseEntity.notFound().build();}
	}
	

}
