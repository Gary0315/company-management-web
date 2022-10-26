package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.LeaveListBean;

@Repository
public interface LeaveListRepository extends JpaRepository<LeaveListBean, Integer>{

	//查詢單一職員的請假紀錄
	@Query("select ll from LeaveListBean ll "
			+ "where ll.emp.empid = :empid")
	List<LeaveListBean> getLists(@Param("empid") Integer empid);
	
	//主管登入後,查詢該部門所有職員的請假紀錄
	//SELECT * FROM leavelist ll WHERE ll.empID IN ( SELECT e.empID FROM emp e WHERE e.deptID IN (SELECT e.deptID FROM emp e WHERE e.empID = 1) AND e.positionID != 1 )
	@Query("SELECT ll FROM LeaveListBean ll WHERE ll.status = 1 AND ll.emp IN ( SELECT e.empid FROM EmpBean e WHERE e.dept IN (SELECT e.dept FROM EmpBean e WHERE e.empid = :bossempid) AND e.position != 1 )")
	List<LeaveListBean> getDeptLists(@Param("bossempid") Integer bossempid);
	
}
