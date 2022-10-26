package tw.com.ispan.eeit48.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "deptlist")

public class DeptlistBean {
//	@OneToMany(mappedBy = "dept"
//			, cascade = { CascadeType.ALL
//	})
//	private Set<EmpBean> emps;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptid;
	private String dept;

	@Override
	public String toString() {
		return "DeptlistBean [deptid=" + deptid + ", dept=" + dept + "]";
	}

	public Integer getDeptid() {
		return deptid;
	}

//	public Set<EmpBean> getEmps() {
//		return emps;
//	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
