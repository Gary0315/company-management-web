package tw.com.ispan.eeit48.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.EquipmentApplyBean;

@Repository
public interface EquipmentApplyRepository extends JpaRepository<EquipmentApplyBean , Integer> {
	
	//ok，找出特定型態的所有清單
	@Query("select e from EquipmentApplyBean e "+"where e.applytype = ?1")
	List<EquipmentApplyBean> findByApplytype(Integer applytype);

	//ok，找出維修或租借中，未通過審查的單子
	@Query(value = "select * from equipmentapply "+"where equipmentapply.applytype = ?1 "+
	"and if(?2 is null ,equipmentapply.applyaccepttime is null,1=1)",nativeQuery = true)
	List<EquipmentApplyBean> findByApplytypeAndApplyaccepttime(Integer applytype, Date date);

	//ok，找出通過審查，但是還沒結案的單子，過審時間不是空值，完成時間是空值
	@Query(value = "select * from equipmentapply "+"where equipmentapply.applytype = ?1 "+
	"and if(?2 is null,equipmentapply.applyaccepttime is not null,1=1) "+
	"and if(?3 is null,equipmentapply.applyfinishtime is null,1=1)",nativeQuery = true)
	List<EquipmentApplyBean> findByApplytypeAndApplyaccepttimeAndApplyfinishtime(Integer applytype, Date date1, Date date2);
	
	//ok，找出結案，完成時間不是空值
	@Query(value = "select * from equipmentapply "+"where equipmentapply.applytype = ?1 "+
	"and if(?2 is null,equipmentapply.applyfinishtime is not null,1=1)",nativeQuery = true)
	List<EquipmentApplyBean> findByApplytypeAndApplyfinishtime(Integer applytype, Date date);
	
	//ok，找出指定類型所有還沒結案的單子
	@Query(value = "select * from equipmentapply "+"where equipmentapply.applytype = ?1 "+
	"and if(?2 is null,equipmentapply.applyfinishtime is null,1=1)",nativeQuery = true)
	List<EquipmentApplyBean> findByApplyfinishtime(Integer applytype, Date date);
	
	//ok，找出不分類型還沒結案單子
	@Query(value = "select * from equipmentapply "+"where if(?1 is null,1=1,1=1) "+
	"and if(?2 is null,equipmentapply.applyfinishtime is null,1=1)",nativeQuery = true)
	List<EquipmentApplyBean> findByApplyaccepttimeAndApplyfinishtime(Date date1, Date date2);
	
}
