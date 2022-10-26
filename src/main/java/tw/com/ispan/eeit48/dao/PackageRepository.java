package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.PackageBean;

@Repository
public interface PackageRepository extends JpaRepository<PackageBean , Integer> {
	
	//ok，搜尋特定類型的表單
	@Query("select p from PackageBean p "+"where p.packagetype = ?1")
	List<PackageBean> findByPackagetype(Integer packagetype);
	
	//ok，搜尋特定類型特定狀態的表單
	@Query("select p from PackageBean p "+"where p.packagetype = ?1 "+"and p.packagestatus = ?2")
	List<PackageBean> findByPackagetypeAndPackagestatus(Integer packagetype, Integer packagestatus);
	
	//---搜尋特定狀態的表單
	@Query("select p from PackageBean p "+"where p.packagestatus = ?1")
	List<PackageBean> findByPackagestatus(Integer packagestatus);
}
