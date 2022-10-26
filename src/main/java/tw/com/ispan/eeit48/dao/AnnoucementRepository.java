package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.domain.AnnoucementBean;

@Repository
public interface AnnoucementRepository extends JpaRepository<AnnoucementBean, Integer> {
	
	@Query("select a from AnnoucementBean a where a.announcementtype=:announcementtype ORDER BY announcementTime DESC")
	List<AnnoucementBean> select(@Param("announcementtype") Integer announcementtype);
	
	@Query("select a FROM AnnoucementBean a WHERE a.announcementtype=:announcementtype ORDER BY announcementTime DESC")
	List<AnnoucementBean> selectTitle(@Param("announcementtype") Integer announcementtype);
	
	@Query("select a FROM AnnoucementBean a ORDER BY announcementTime DESC")
	List<AnnoucementBean> selectAll();
}
