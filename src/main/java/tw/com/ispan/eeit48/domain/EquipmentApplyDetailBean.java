package tw.com.ispan.eeit48.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "equipmentapplydetail")
public class EquipmentApplyDetailBean {
	
	@EmbeddedId
	private ApplyDetailDoublePK applyDetailDoublePK;
	private String quantity;
	
	
	@Override
	public String toString() {
		return "EquipmentApplyDetailBean [applyDetailDoublePK=" + applyDetailDoublePK + ", quantity=" + quantity + "]";
	}


	public ApplyDetailDoublePK getApplyDetailDoublePK() {
		return applyDetailDoublePK;
	}


	public void setApplyDetailDoublePK(ApplyDetailDoublePK applyDetailDoublePK) {
		this.applyDetailDoublePK = applyDetailDoublePK;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	

}
