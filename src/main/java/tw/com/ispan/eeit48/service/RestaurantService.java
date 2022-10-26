package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.OrderlistRepository;
import tw.com.ispan.eeit48.dao.RestaurantRepository;
import tw.com.ispan.eeit48.domain.OrderlistBean;
import tw.com.ispan.eeit48.domain.RestaurantBean;

@Service
@Transactional
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<RestaurantBean> select(RestaurantBean bean) {
		List<RestaurantBean> result = null;
		if (bean != null && bean.getRestaurantid() != null && !bean.getRestaurantid().equals(0)) {
			Optional<RestaurantBean> data = restaurantRepository.findById(bean.getRestaurantid());
			if (data.isPresent()) {
				result = new ArrayList<RestaurantBean>();
				result.add(data.get());
			}
		} else {
			result = restaurantRepository.findAll();
		}
		return result;
	}

	public RestaurantBean insert(RestaurantBean bean) {
		RestaurantBean result = null;
		if (bean != null && bean.getRestaurantid() != null) {
			if (!restaurantRepository.existsById(bean.getRestaurantid())) {
				result = restaurantRepository.save(bean);
			}
		}
		return result;
	}


	public RestaurantBean update(RestaurantBean bean) {
		RestaurantBean result = null;
		if (bean != null && bean.getRestaurantid() != null) {
			if (restaurantRepository.existsById(bean.getRestaurantid())) {
				return restaurantRepository.save(bean);
			}
		}
		return result;
	}
	

	
	

	public boolean delete(RestaurantBean bean) {
		boolean result = false;
		if (bean != null && bean.getRestaurantid() != null) {
			if (restaurantRepository.existsById(bean.getRestaurantid())) {
				restaurantRepository.deleteById(bean.getRestaurantid());
				return true;
			}
		}
		return result;
	}
}
