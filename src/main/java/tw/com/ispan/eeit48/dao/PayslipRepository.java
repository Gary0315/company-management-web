package tw.com.ispan.eeit48.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.PayslipBean;

@Repository
public interface PayslipRepository extends JpaRepository<PayslipBean, Integer>{

	@Query("select p from PayslipBean p where p.emp.empid=:empid")
	PayslipBean getPayslipBean(@Param("empid") Integer empid);
	
	@Query("select p from PayslipBean p where p.emp.empid=:empid and Year(p.salarydate)=:year and Month(p.salarydate)=:month")
	PayslipBean getPayslipBeanByYearMonth(@Param("empid") Integer empid,@Param("year") Integer year,@Param("month") Integer month);
	
}
