package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ispan.eeit48.dao.EmpRepository;
import tw.com.ispan.eeit48.domain.EmpBean;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private EmpRepository empRepository;

	public List<EmpBean> select(tw.com.ispan.eeit48.domain.EmpBean bean) {
		List<EmpBean> result = null;
		if (bean != null && bean.getEmpid() != null && !bean.getEmpid().equals(0)) {

			Optional<EmpBean> data = empRepository.findById(bean.getEmpid());
			if (data.isPresent()) {
				result = new ArrayList<EmpBean>();
				result.add(data.get());
			}
		} else {
			result = empRepository.findAll();
		}
		return result;
	}

	public EmpBean insert(EmpBean bean) {
		EmpBean result = null;
		if (bean != null && bean.getEmpid() != null) {
			if (!empRepository.existsById(bean.getEmpid())) {
				result = empRepository.save(bean);
			}
		}
		return result;
	}

	public EmpBean update(EmpBean bean) {
		EmpBean result = null;
		if (bean != null && bean.getEmpid() != null) {
			if (empRepository.existsById(bean.getEmpid())) {
				return empRepository.save(bean);
			}
		}
		return result;
	}

	public boolean delete(EmpBean bean) {
		boolean result = false;
		if (bean != null && bean.getEmpid() != null) {
			if (empRepository.existsById(bean.getEmpid())) {
				empRepository.deleteById(bean.getEmpid());
				return true;
			}
		}
		return result;
	}

}
