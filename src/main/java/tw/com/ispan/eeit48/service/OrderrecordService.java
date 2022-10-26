package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.OrderrecordRepository;
import tw.com.ispan.eeit48.domain.OrderrecordBean;

@Service
@Transactional
public class OrderrecordService {
	@Autowired
	private OrderrecordRepository orderrecordRepository;

	public List<OrderrecordBean> select(OrderrecordBean bean) {
		List<OrderrecordBean> result = null;
		if (bean != null && bean.getOrderid() != null && !bean.getOrderid().equals(0)) {
			Optional<OrderrecordBean> data = orderrecordRepository.findById(bean.getOrderid());
			if (data.isPresent()) {
				result = new ArrayList<OrderrecordBean>();
				result.add(data.get());
			}
		} else {
			result = orderrecordRepository.findAll();
		}
		return result;
	}

	public OrderrecordBean insert(OrderrecordBean bean) {
		OrderrecordBean result = null;
		if (bean != null && bean.getOrderid() != null) {
			if (!orderrecordRepository.existsById(bean.getOrderid())) {
				result = orderrecordRepository.save(bean);
			}
		}
		return result;
	}
}
