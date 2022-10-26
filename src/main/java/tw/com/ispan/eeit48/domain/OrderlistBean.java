package tw.com.ispan.eeit48.domain;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderlist")
public class OrderlistBean {

	@ManyToOne
	@JoinColumn(
			name="RESTAURANTID",
			referencedColumnName = "RESTAURANTID"
			)
	private RestaurantBean restaurant;
	
	public RestaurantBean getRestaurant() {
		return restaurant;
	}



	public void setRestaurant(RestaurantBean restaurant) {
		this.restaurant = restaurant;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderitemid;
	
	private String orderitem;
	
	private Integer orderprice;
	
	private String image;
	
	
	@Override
	public String toString() {
		return "OrderlistBean [orderitemid=" + orderitemid + ", orderitem=" + orderitem + ", orderprice=" + orderprice
				+ ", image=" + image +  "]";
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Integer getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(Integer orderitemid) {
		this.orderitemid = orderitemid;
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


	
	
}
