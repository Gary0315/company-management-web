package tw.com.ispan.eeit48.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_order")
public class EmpOrderBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID", updatable = false, nullable = false)
	private Integer orderid;
	private Integer empid;
	private String name;
	private String orderitem;
	private Integer orderprice;
	private java.util.Date orderdate;
	private String paymethod;
	
	@Override
	public String toString() {
		return "EmpOrderBean [orderid=" + orderid + ", empid=" + empid + ", name=" + name + ", orderitem=" + orderitem
				+ ", orderprice=" + orderprice + ", orderdate=" + orderdate + ", paymethod=" + paymethod + "]";
	}
	public Integer getEmpid() {
		return empid;
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
	public String getOrderitem() {
		return orderitem;
	}
	public void setOrderitem(String orderitem) {
		this.orderitem = orderitem;
	}
	public Integer getOrderprice() {
		return orderprice;
	}
	public void setOrderprice(Integer orderprice) {
		this.orderprice = orderprice;
	}
	public java.util.Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(java.util.Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	
	
}
