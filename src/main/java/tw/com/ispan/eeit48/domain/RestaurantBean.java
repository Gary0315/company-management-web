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
@Table(name = "restaurant")
public class RestaurantBean {
	
	@OneToMany(mappedBy = "restaurant"
			, cascade = { CascadeType.ALL
	})
	private Set<OrderlistBean> orderlist;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurantid;
	
	private String restaurantname;

	private String applicant;
	
	private java.util.Date applicationdate;
	
	private Integer todayorder;
	
	
	
	public Integer getTodayorder() {
		return todayorder;
	}


	public void setTodayorder(Integer todayorder) {
		this.todayorder = todayorder;
	}


	public String getApplicant() {
		return applicant;
	}
	

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public java.util.Date getApplicationdate() {
		return applicationdate;
	}

	public void setApplicationdate(java.util.Date applicationdate) {
		this.applicationdate = applicationdate;
	}

	public Integer getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(Integer restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}


	@Override
	public String toString() {
		return "RestaurantBean [restaurantid=" + restaurantid + ", restaurantname=" + restaurantname + ", applicant="
				+ applicant + ", applicationdate=" + applicationdate + ", todayorder=" + todayorder + "]";
	}

	
	
	
	
}
