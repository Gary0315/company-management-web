package tw.com.ispan.eeit48.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipmentapply")
public class EquipmentApplyBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer applyid;
	private String applyreason;
	private Integer empid;
	private Integer applytype;
	private Date applytime;
	private Date applyaccepttime;
	private Date applyfinishtime;
	@Override
	public String toString() {
		return "EquipmentApplyBean [applyid=" + applyid + ", applyreason=" + applyreason + ", empid=" + empid
				+ ", applytype=" + applytype + ", applytime=" + applytime + ", applyaccepttime=" + applyaccepttime
				+ ", applyfinishtime=" + applyfinishtime + "]";
	}
	public Integer getApplyid() {
		return applyid;
	}
	public void setApplyid(Integer applyid) {
		this.applyid = applyid;
	}
	public String getApplyreason() {
		return applyreason;
	}
	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Integer getApplytype() {
		return applytype;
	}
	public void setApplytype(Integer applytype) {
		this.applytype = applytype;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public Date getApplyaccepttime() {
		return applyaccepttime;
	}
	public void setApplyaccepttime(Date applyaccepttime) {
		this.applyaccepttime = applyaccepttime;
	}
	public Date getApplyfinishtime() {
		return applyfinishtime;
	}
	public void setApplyfinishtime(Date applyfinishtime) {
		this.applyfinishtime = applyfinishtime;
	}
	
	
	
	

}
