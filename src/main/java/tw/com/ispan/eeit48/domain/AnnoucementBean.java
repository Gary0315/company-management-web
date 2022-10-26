package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annoucement")
public class AnnoucementBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer announcementid;
	private java.util.Date announcementtime;
	private String announcementtitle;
	private String context;
	private Integer empid;
	private Integer deptid;
	private Integer announcementtype;
	
	
	
	@Override
	public String toString() {
		return "annoucementBean [announcementid=" + announcementid + ", announcementtime=" + announcementtime
				+ ", announcementtitle=" + announcementtitle + ", context=" + context + ", empid=" + empid + ", deptid="
				+ deptid + ", announcementtype=" + announcementtype + "]";
	}
	public Integer getAnnouncementid() {
		return announcementid;
	}
	public void setAnnouncementid(Integer announcementid) {
		this.announcementid = announcementid;
	}
	public java.util.Date getAnnouncementtime() {
		return announcementtime;
	}
	public void setAnnouncementtime(java.util.Date announcementtime) {
		this.announcementtime = announcementtime;
	}
	public String getAnnouncementtitle() {
		return announcementtitle;
	}
	public void setAnnouncementtitle(String announcementtitle) {
		this.announcementtitle = announcementtitle;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public Integer getAnnouncementtype() {
		return announcementtype;
	}
	public void setAnnouncementtype(Integer announcementtype) {
		this.announcementtype = announcementtype;
	}
	
	
	
	

}
