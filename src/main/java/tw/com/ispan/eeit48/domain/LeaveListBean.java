package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leavelist")
public class LeaveListBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveid;
	
	@ManyToOne
	@JoinColumn(name="empid")
	private EmpBean emp;
	private Integer agentid;
	private java.util.Date leavestartdate;
	private java.util.Date leaveenddate;
	private Integer leavehour;
	
	@ManyToOne
	@JoinColumn(name="leavetypeid")
	private LeaveTypeBean leavetype;
	private String leavereason;
	
	@ManyToOne
	@JoinColumn(name="statusid")
	private LeaveStatusBean status;
	private java.util.Date leaveapplydate;
	
//	@Override
//	public String toString() {
//		return "LeaveListBean [leaveid=" + leaveid + ", empid=" + empid + ", agentid=" + agentid + ", leavestartdate="
//				+ leavestartdate + ", leaveenddate=" + leaveenddate + ", leavehour=" + leavehour + ", leavetypeid="
//				+ leavetypeid + ", leavereason=" + leavereason + ", statusid=" + statusid + ", leaveapplydate="
//				+ leaveapplydate + "]";
//	}
	
	@Override
	public String toString() {
		return "LeaveListBean [leaveid=" + leaveid + ", emp=" + emp + ", agentid=" + agentid + ", leavestartdate="
				+ leavestartdate + ", leaveenddate=" + leaveenddate + ", leavehour=" + leavehour + ", leavetype="
				+ leavetype + ", leavereason=" + leavereason + ", status=" + status + ", leaveapplydate="
				+ leaveapplydate + "]";
	}



	public EmpBean getEmp() {
		return emp;
	}



	public void setEmp(EmpBean emp) {
		this.emp = emp;
	}



	public LeaveTypeBean getLeavetype() {
		return leavetype;
	}



	public void setLeavetype(LeaveTypeBean leavetype) {
		this.leavetype = leavetype;
	}




	public LeaveStatusBean getStatus() {
		return status;
	}




	public void setStatus(LeaveStatusBean status) {
		this.status = status;
	}




	public Integer getLeaveid() {
		return leaveid;
	}
	
	public void setLeaveid(Integer leaveid) {
		this.leaveid = leaveid;
	}
//	public Integer getEmpid() {
//		return empid;
//	}
//	public void setEmpid(Integer empid) {
//		this.empid = empid;
//	}
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public java.util.Date getLeavestartdate() {
		return leavestartdate;
	}
	public void setLeavestartdate(java.util.Date leavestartdate) {
		this.leavestartdate = leavestartdate;
	}
	public java.util.Date getLeaveenddate() {
		return leaveenddate;
	}
	public void setLeaveenddate(java.util.Date leaveenddate) {
		this.leaveenddate = leaveenddate;
	}
	public Integer getLeavehour() {
		return leavehour;
	}
	public void setLeavehour(Integer leavehour) {
		this.leavehour = leavehour;
	}
//	public Integer getLeavetypeid() {
//		return leavetypeid;
//	}
//	public void setLeavetypeid(Integer leavetypeid) {
//		this.leavetypeid = leavetypeid;
//	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
//	public Integer getStatusid() {
//		return statusid;
//	}
//	public void setStatusid(Integer statusid) {
//		this.statusid = statusid;
//	}
	public java.util.Date getLeaveapplydate() {
		return leaveapplydate;
	}
	public void setLeaveapplydate(java.util.Date leaveapplydate) {
		this.leaveapplydate = leaveapplydate;
	}
	
	
}
