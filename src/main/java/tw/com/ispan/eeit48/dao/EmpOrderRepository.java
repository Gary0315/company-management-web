package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tw.com.ispan.eeit48.domain.EmpOrderBean;

@Repository
public interface EmpOrderRepository extends JpaRepository<EmpOrderBean , Integer> {
	
	@Query("SELECT e FROM EmpOrderBean e where e.empid = :empid order by e.orderdate DESC")
    Page<EmpOrderBean> history(@Param("empid") Integer empid , Pageable pageable);
	
	@Query("SELECT e FROM EmpOrderBean e where e.empid = :empid AND e.orderdate between :orderdatefirst AND :orderdatelast order by e.orderdate DESC")
    Page<EmpOrderBean> empDateHistory(@Param("orderdatefirst") java.util.Date orderdatefirst,@Param("orderdatelast") java.util.Date orderdatelast ,@Param("empid") Integer empid , Pageable pageable);
	
	@Query("SELECT e FROM EmpOrderBean e order by e.orderdate DESC")
    Page<EmpOrderBean> allHistory(Pageable pageable);
	
	@Query("SELECT e FROM EmpOrderBean e where e.orderdate between :orderdatefirst AND :orderdatelast order by e.orderdate DESC")
    Page<EmpOrderBean> dateHistory(@Param("orderdatefirst") java.util.Date orderdatefirst,@Param("orderdatelast") java.util.Date orderdatelast ,Pageable pageable);

	//SELECT SUM(orderprice) FROM emp_order e WHERE e.empid=3 AND e.paymethod='salary' AND Year(e.orderdate)=2022 AND Month(e.orderdate)=10
	@Query("SELECT sum(orderprice) FROM EmpOrderBean e WHERE e.empid=:empid AND e.paymethod=:paymethod AND Year(e.orderdate) = :year AND Month(e.orderdate)= :month")
	Integer monthTotal(@Param("empid") Integer empid,@Param("paymethod") String paymethod,@Param("year") Integer year,@Param("month") Integer month);
		
	@Query("SELECT e.orderitem , e.orderprice ,count(*) FROM EmpOrderBean e where e.orderdate=:orderdate GROUP BY e.orderitem , e.orderprice")
	List<Object[]> todayRecord(@Param("orderdate") java.util.Date orderdate);
}
