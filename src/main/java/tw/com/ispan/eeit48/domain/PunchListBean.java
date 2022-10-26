package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="punchlist")
public class PunchListBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer punchid;
	private Integer empid;
	private java.util.Date punchtime;
	private String punchtype;
	
	
	
	@Override
	public String toString() {
		return "PunchListBean [punchid=" + punchid + ", empid=" + empid + ", punchtime=" + punchtime + ", punchtype="
				+ punchtype + "]";
	}
	
	public Integer getPunchid() {
		return punchid;
	}
	public void setPunchid(Integer punchid) {
		this.punchid = punchid;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public java.util.Date getPunchtime() {
		return punchtime;
	}
	public void setPunchtime(java.util.Date punchtime) {
		this.punchtime = punchtime;
	}
	public String getPunchtype() {
		return punchtype;
	}
	public void setPunchtype(String punchtype) {
		this.punchtype = punchtype;
	}

}
