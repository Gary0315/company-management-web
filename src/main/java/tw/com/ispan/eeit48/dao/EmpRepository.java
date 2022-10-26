package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.EmpBean;

@Repository
public interface EmpRepository extends JpaRepository<EmpBean, Integer> {
	
	@Query("select e from EmpBean e where e.empid=:empid")
	EmpBean login(@Param("empid") Integer empid);
	
	@Query("from EmpBean ")
	List<EmpBean> loginjoin();
	
	@Query("select e from EmpBean e where e.email=:email")
	public EmpBean findByEmail(String email);
	
	public EmpBean findByResetPasswordToken(String token);
}
