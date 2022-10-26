package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leavetype")
public class LeaveTypeBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leavetypeid;
	private String leavetype;
	
	
	@Override
	public String toString() {
		return "LeaveTypeBean [leavetypeid=" + leavetypeid + ", leavetype=" + leavetype + "]";
	}
	
	
	public Integer getLeavetypeid() {
		return leavetypeid;
	}
	public void setLeavetypeid(Integer leavetypeid) {
		this.leavetypeid = leavetypeid;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	
}
