package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.PunchListBean;

@Repository
public interface PunchListRepository extends JpaRepository<PunchListBean, Integer> {
	
//	@Query("insert into PunchListBean (empid ,punchtype) values"
//			+ "(:empid,:punchtype)")
//	PunchListBean punch(@Param("empid") Integer empid,@Param("punchtype") Integer punchtype);

	//查詢單一員工打卡紀錄
	@Query("select p from PunchListBean p where p.empid=:empid ORDER BY punchTime DESC")
	List<PunchListBean> select(@Param("empid") Integer empid);
	
	//查詢全部員工打卡紀錄-打卡搜punchid用
	@Query("select p from PunchListBean p ORDER BY punchTime DESC")
	List<PunchListBean> selectAllData();
	
	//查詢單一員工打卡紀錄
	@Query("select p from PunchListBean p where p.empid=:empid and DateDiff(p.punchtime,Now())=0 ORDER BY punchTime DESC")
	List<PunchListBean> selectToday(@Param("empid") Integer empid);
}
