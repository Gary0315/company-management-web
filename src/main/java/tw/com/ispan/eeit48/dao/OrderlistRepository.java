package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import tw.com.ispan.eeit48.domain.OrderlistBean;

public interface OrderlistRepository extends JpaRepository<OrderlistBean, Integer> {
	
	@Query("select o from OrderlistBean o where restaurant.restaurantid = :restaurantid")
	List<OrderlistBean> chooseRestaurant(@Param("restaurantid") Integer restaurantid);
	
	@Query("select o from OrderlistBean o where restaurant.todayorder = 1")
	List<OrderlistBean> chooseTodayRestaurant();
	
	
	
}
