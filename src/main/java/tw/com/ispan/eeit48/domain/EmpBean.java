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
@Table(name = "emp")

public class EmpBean {
	public DeptlistBean getDept() {
		return dept;
	}

	public void setDept(DeptlistBean dept) {
		this.dept = dept;
	}
	@ManyToOne
	@JoinColumn(
			name="DEPTID",
			referencedColumnName = "DEPTID"
			)
	private DeptlistBean dept;
	
	@ManyToOne
	@JoinColumn(
			name="POSITIONID",
			referencedColumnName = "POSITIONID"
			)
	private PositionlistBean position;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empid;
	private String name;
	private String passwd;
	private String passwd2;
	private String idnumber;
	private java.util.Date birthday;
	private String sex ;
	private java.util.Date onboarddate;
	private String phone;
	private String email;
	private String addr;
	private String emergencycontact;
	private String emergencyphone;
	private String resetPasswordToken;  

//	private Integer deptid;
//	private Integer positionid;

	
	
	
//	@Override
//	public String toString() {
//		return "EmpBean [empid=" + empid + ", name=" + name + ", passwd=" + passwd + ", passwd2=" + passwd2
//				+ ", idnumber=" + idnumber + ", birthday=" + birthday + ", sex=" + sex + ", onboarddate=" + onboarddate
//				+ ", phone=" + phone + ", email=" + email
//				+ ", addr=" + addr + ", emergencycontact=" + emergencycontact + ", emergencyphone=" + emergencyphone
//				+ "]";
//	}
	
	

    public Integer getEmpid() {
		return empid;
	}
	@Override
	public String toString() {
		return "EmpBean [dept=" + dept + ", position=" + position + ", empid=" + empid + ", name=" + name + ", passwd="
				+ passwd + ", passwd2=" + passwd2 + ", idnumber=" + idnumber + ", birthday=" + birthday + ", sex=" + sex
				+ ", onboarddate=" + onboarddate + ", phone=" + phone + ", email=" + email + ", addr=" + addr
				+ ", emergencycontact=" + emergencycontact + ", emergencyphone=" + emergencyphone + "]";
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public java.util.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public java.util.Date getOnboarddate() {
		return onboarddate;
	}
	public void setOnboarddate(java.util.Date onboarddate) {
		this.onboarddate = onboarddate;
	}


	public PositionlistBean getPosition() {
		return position;
	}

	public void setPosition(PositionlistBean position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmergencycontact() {
		return emergencycontact;
	}
	public void setEmergencycontact(String emergencycontact) {
		this.emergencycontact = emergencycontact;
	}
	public String getEmergencyphone() {
		return emergencyphone;
	}
	public void setEmergencyphone(String emergencyphone) {
		this.emergencyphone = emergencyphone;
	}
	public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
	
}
