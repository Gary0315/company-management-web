package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderrecord")
public class OrderrecordBean {

	@ManyToOne
	@JoinColumn(name="orderitemid")
	private OrderlistBean orderlist;
	

	public OrderlistBean getOrderlist() {
		return orderlist;
	}


	public void setOrderlist(OrderlistBean orderlist) {
		this.orderlist = orderlist;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;
	
	private Integer empid ;
	
	private java.util.Date orderdate ;
	
	private String paymethod;

	@Override
	public String toString() {
		return "OrderrecordBean [orderid=" + orderid + ", empid=" + empid + ", orderdate=" + orderdate + ", paymethod="
				+ paymethod +"]";
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public java.util.Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(java.util.Date orderdate) {
		this.orderdate = orderdate;
	}

	
	
}
