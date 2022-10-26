package tw.com.ispan.eeit48.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.AnnoucementRepository;
import tw.com.ispan.eeit48.domain.AnnoucementBean;

@Service
@Transactional
public class AnnouncementService {
	
	@Autowired
	private AnnoucementRepository annoucementRepository;
	
	//草稿公告-查詢狀態為0的資料
	public List<AnnoucementBean> select(Integer announcementtype) {
		if(announcementtype==null) {
			List<AnnoucementBean> annoucementBean = annoucementRepository.select(0);
			return annoucementBean;

		}
		return null;
		
	}
	
	//已發布公告
//	public List<AnnoucementBean> selectlist(Integer announcementtype) {
//		
//		if(announcementtype==null) {
//			List<AnnoucementBean> annoucementBean = annoucementRepository.select(1);
//			return annoucementBean;
//
//		}
//		return null;
//		
//	}
	
	//公告列表-查詢狀態為1的資料
	public List<AnnoucementBean> selectdesc(Integer announcementtype) {
		
		if(announcementtype==null) {
			List<AnnoucementBean> annoucementBean = annoucementRepository.selectTitle(1);
			return annoucementBean;

		}
		return null;
		
	}
	
	//修改公告(在前端是顯示刪除)
	public AnnoucementBean updel(AnnoucementBean bean) {
		AnnoucementBean result = null;
		if(bean!=null&&bean.getAnnouncementid()!=null) {
			if(annoucementRepository.existsById(bean.getAnnouncementid())) {
				return annoucementRepository.save(bean);
			}
		}
		return result;
	}
	
	//查詢所有公告
	public List<AnnoucementBean> selectAll(){
		List<AnnoucementBean> result = null;
		result=annoucementRepository.selectAll();
		return result;
		
	}
	
	//新增公告
	public AnnoucementBean insert(AnnoucementBean bean) {
		AnnoucementBean result = null;
		if(bean!=null&&bean.getAnnouncementid()!=null) {
			if(!annoucementRepository.existsById(bean.getAnnouncementid())) {
				result = annoucementRepository.save(bean);
			}
		}
		return result;
	}

}
