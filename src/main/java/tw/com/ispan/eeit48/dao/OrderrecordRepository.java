package tw.com.ispan.eeit48.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ispan.eeit48.domain.OrderrecordBean;

public interface OrderrecordRepository extends JpaRepository<OrderrecordBean, Integer>{
	
}
