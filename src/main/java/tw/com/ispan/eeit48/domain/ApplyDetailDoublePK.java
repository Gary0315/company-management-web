package tw.com.ispan.eeit48.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ApplyDetailDoublePK  implements Serializable{
	private Integer applyid;
	private Integer equipmentid;
	public Integer getApplyid() {
		return applyid;
	}
	public void setApplyid(Integer applyid) {
		this.applyid = applyid;
	}
	public Integer getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApplyDetailDoublePK other = (ApplyDetailDoublePK) obj;
        if ((this.applyid == null) ?
            (other.applyid != null) : !this.applyid.equals(other.applyid)) {
            return false;
        }
        if ((this.equipmentid == null) ?
            (other.equipmentid != null) : !this.equipmentid.equals(other.equipmentid)) {
            return false;
        }
        return true;
    }
	
	@Override
	public int hashCode() {
		 int hash = 5;
	        hash = 73 * hash + (this.applyid != null ? this.applyid.hashCode() : 0);
	        hash = 73 * hash + (this.equipmentid != null ? this.equipmentid.hashCode() : 0);
	        return hash;
	}
	
	
}
