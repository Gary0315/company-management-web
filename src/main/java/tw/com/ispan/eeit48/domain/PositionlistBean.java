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
@Table(name = "positionlist")
public class PositionlistBean {
	
	
//	@OneToMany(mappedBy = "position"
//			, cascade = { CascadeType.ALL
//	})
//	private Set<EmpBean> emps;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer positionid;
	
	private String position;
	
	
	@Override
	public String toString() {
		return "PositionlistBean [positionid=" + positionid + ", position=" + position + "]";
	}
	public Integer getPositionid() {
		return positionid;
	}
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
