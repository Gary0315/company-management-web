package tw.com.ispan.eeit48.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import tw.com.ispan.eeit48.domain.RestaurantBean;

public interface RestaurantRepository extends JpaRepository<RestaurantBean, Integer>{
	@Query("select r from RestaurantBean r where r.todayorder = 1")
	RestaurantBean changeRestaurant();
}
