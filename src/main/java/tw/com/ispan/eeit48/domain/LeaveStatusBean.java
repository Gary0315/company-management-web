package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leavestatus")
public class LeaveStatusBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusid;
	private String status;
	
	
	@Override
	public String toString() {
		return "LeaveStatusBean [statusid=" + statusid + ", status=" + status + "]";
	}
	
	
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
