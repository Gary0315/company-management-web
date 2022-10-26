package tw.com.ispan.eeit48.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipmentlist")
public class EquipmentListBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer equipmentid;
	private String equipment;
	
	
	@Override
	public String toString() {
		return "EquipmentListBean [equipmentid=" + equipmentid + ", equipment=" + equipment + "]";
	}


	public Integer getEquipmentid() {
		return equipmentid;
	}


	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}


	public String getEquipment() {
		return equipment;
	}


	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	

}
