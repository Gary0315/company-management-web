package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.OrderlistRepository;
import tw.com.ispan.eeit48.domain.OrderlistBean;

@Service
@Transactional
public class OrderlistService {
	@Autowired
	private OrderlistRepository orderlistRepository;

	public List<OrderlistBean> select(OrderlistBean bean) {
		List<OrderlistBean> result = null;
		if (bean != null && bean.getOrderitemid() != null && !bean.getOrderitemid().equals(0)) {
			Optional<OrderlistBean> data = orderlistRepository.findById(bean.getOrderitemid());
			if (data.isPresent()) {
				result = new ArrayList<OrderlistBean>();
				result.add(data.get());
			}
		} else {
			result = orderlistRepository.findAll();
		}
		return result;
	}

	public List<OrderlistBean> selectRestaurant(Integer id){
		if(id!=null && id!=0) {
			List<OrderlistBean> data = orderlistRepository.chooseRestaurant(id);
			return data;
		}else{
		return null;
		}
	}
	
	public List<OrderlistBean> selectToday(){
		List<OrderlistBean>	todayMenu =orderlistRepository.chooseTodayRestaurant();
		if(todayMenu != null) {
			return todayMenu;
		}
		return null;
	}


	public OrderlistBean insert(OrderlistBean bean) {
		OrderlistBean result = null;
		if (bean != null && bean.getOrderitemid() != null) {
			if (!orderlistRepository.existsById(bean.getOrderitemid())) {
				result = orderlistRepository.save(bean);
			}
		}
		return result;
	}

	public List<OrderlistBean> inserta(List<OrderlistBean> bean) {
		List<OrderlistBean> list = null;
		if (bean != null) {
			list = orderlistRepository.saveAll(bean);
		}
		return list;
	}

	public OrderlistBean update(OrderlistBean bean) {
		OrderlistBean result = null;
		if (bean != null && bean.getOrderitemid() != null) {
			if (orderlistRepository.existsById(bean.getOrderitemid())) {
				return orderlistRepository.save(bean);
			}
		}
		return result;
	}

	public boolean delete(OrderlistBean bean) {
		boolean result = false;
		if (bean != null && bean.getOrderitemid() != null) {
			if (orderlistRepository.existsById(bean.getOrderitemid())) {
				orderlistRepository.deleteById(bean.getOrderitemid());
				return true;
			}
		}
		return result;
	}
}
