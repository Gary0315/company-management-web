package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payslip")
public class PayslipBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salaryid;
	private java.util.Date salarydate;
	
	@ManyToOne
	@JoinColumn(name="empid")
	private EmpBean emp;
	private Integer basicpay;
	private Integer workpay;
	private Integer attendancebonus;
	private Integer mealpay;
	private Integer laborinsurance;
	private Integer healthinsurance;	
	private Integer laborpension;
	private Integer mealcost;
	private Integer leavecost;
	
		
//	@Override
//	public String toString() {
//		return "PayslipBean [salaryid=" + salaryid + ", salarydate=" + salarydate + ", empid=" + empid + ", basicpay="
//				+ basicpay + ", workpay=" + workpay + ", attendancebonus=" + attendancebonus + ", mealpay=" + mealpay
//				+ ", laborinsurance=" + laborinsurance + ", healthinsurance=" + healthinsurance + ", laborpension="
//				+ laborpension + ", mealcost=" + mealcost + ", leavecost=" + leavecost + "]";
//	}
	
	@Override
	public String toString() {
		return "PayslipBean [salaryid=" + salaryid + ", salarydate=" + salarydate + ", emp=" + emp + ", basicpay="
				+ basicpay + ", workpay=" + workpay + ", attendancebonus=" + attendancebonus + ", mealpay=" + mealpay
				+ ", laborinsurance=" + laborinsurance + ", healthinsurance=" + healthinsurance + ", laborpension="
				+ laborpension + ", mealcost=" + mealcost + ", leavecost=" + leavecost + "]";
	}


	
	
	public EmpBean getEmp() {
		return emp;
	}


	public void setEmp(EmpBean emp) {
		this.emp = emp;
	}




	public Integer getSalaryid() {
		return salaryid;
	}
	public void setSalaryid(Integer salaryid) {
		this.salaryid = salaryid;
	}
	public java.util.Date getSalarydate() {
		return salarydate;
	}
	public void setSalarydate(java.util.Date salarydate) {
		this.salarydate = salarydate;
	}
//	public Integer getEmpid() {
//		return empid;
//	}
//	public void setEmpid(Integer empid) {
//		this.empid = empid;
//	}
	public Integer getBasicpay() {
		return basicpay;
	}
	public void setBasicpay(Integer basicpay) {
		this.basicpay = basicpay;
	}
	public Integer getWorkpay() {
		return workpay;
	}
	public void setWorkpay(Integer workpay) {
		this.workpay = workpay;
	}
	public Integer getAttendancebonus() {
		return attendancebonus;
	}
	public void setAttendancebonus(Integer attendancebonus) {
		this.attendancebonus = attendancebonus;
	}
	public Integer getMealpay() {
		return mealpay;
	}
	public void setMealpay(Integer mealpay) {
		this.mealpay = mealpay;
	}
	public Integer getLaborinsurance() {
		return laborinsurance;
	}
	public void setLaborinsurance(Integer laborinsurance) {
		this.laborinsurance = laborinsurance;
	}
	public Integer getHealthinsurance() {
		return healthinsurance;
	}
	public void setHealthinsurance(Integer healthinsurance) {
		this.healthinsurance = healthinsurance;
	}
	public Integer getLaborpension() {
		return laborpension;
	}
	public void setLaborpension(Integer laborpension) {
		this.laborpension = laborpension;
	}
	public Integer getMealcost() {
		return mealcost;
	}
	public void setMealcost(Integer mealcost) {
		this.mealcost = mealcost;
	}
	public Integer getLeavecost() {
		return leavecost;
	}
	public void setLeavecost(Integer leavecost) {
		this.leavecost = leavecost;
	}

}
