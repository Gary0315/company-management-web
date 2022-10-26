package tw.com.ispan.eeit48.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "package")
public class PackageBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer packageid;
	private Integer empid;
	private Integer packagetype;
	private String packagecontent;
	private String packagename;
	private String packagephone;
	private String packageaddr;
	private Date scheduleddate;
	private Date actualdate;
	private Integer packagestatus;
	private Date packagefinishtime;

	@Override
	public String toString() {
		return "PackageBean [packageid=" + packageid + ", empid=" + empid + ", packagetype=" + packagetype
				+ ", packagecontent=" + packagecontent + ", packagename=" + packagename + ", packagephone="
				+ packagephone + ", packageaddr=" + packageaddr + ", scheduleddate=" + scheduleddate + ", actualdate="
				+ actualdate + ", packagestatus=" + packagestatus + ", packagefinishtime=" + packagefinishtime + "]";
	}

	public Integer getPackagestatus() {
		return packagestatus;
	}

	public void setPackagestatus(Integer packagestatus) {
		this.packagestatus = packagestatus;
	}

	public Date getPackagefinishtime() {
		return packagefinishtime;
	}

	public void setPackagefinishtime(Date packagefinishtime) {
		this.packagefinishtime = packagefinishtime;
	}

	public Integer getPackageid() {
		return packageid;
	}

	public void setPackageid(Integer packageid) {
		this.packageid = packageid;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public Integer getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(Integer packagetype) {
		this.packagetype = packagetype;
	}

	public String getPackagecontent() {
		return packagecontent;
	}

	public void setPackagecontent(String packagecontent) {
		this.packagecontent = packagecontent;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getPackagephone() {
		return packagephone;
	}

	public void setPackagephone(String packagephone) {
		this.packagephone = packagephone;
	}

	public String getPackageaddr() {
		return packageaddr;
	}

	public void setPackageaddr(String packageaddr) {
		this.packageaddr = packageaddr;
	}

	public Date getScheduleddate() {
		return scheduleddate;
	}

	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	public Date getActualdate() {
		return actualdate;
	}

	public void setActualdate(Date actualdate) {
		this.actualdate = actualdate;
	}

}
