package Model.others;

import java.io.Serializable;

import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.AssociationOverride;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import Model.entity.DeliveryType;
import Model.entity.Town;
import Model.entity.TownDeliveryId;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "town_delivery")
@AssociationOverrides({
		@AssociationOverride(name = "pk.town", 
			joinColumns = @JoinColumn(name = "town_id")),
		@AssociationOverride(name = "pk.delivery", 
			joinColumns = @JoinColumn(name = "delivery_id")) })
public class Town_Delivery implements Model.entity.Entity, Serializable {
	
	private TownDeliveryId pk = new TownDeliveryId();
	private Double price;
	private Boolean iscostless = false;
	private Boolean isdeleted;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@EmbeddedId
	public TownDeliveryId getPk() {
		return pk;
	}
	public void setPk(TownDeliveryId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Town getTown() {
		return getPk().getTown();
	}
 
	
	public void setTown(Town stock) {
		getPk().setTown(stock);
	}
 
	@Transient
	public DeliveryType getDeliveryType() {
		return getPk().getDelivery();
	}
 
	public void setDeliveryType(DeliveryType deltype) {
		getPk().setDelivery(deltype);
	}
	
	@Column(name = "price", length = 10)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	@Type(type = "boolean")
	@Column(name = "iscostless")
	public Boolean getIscostless() {
		return iscostless;
	}
	public void setIscostless(Boolean iscostless) {
		this.iscostless = iscostless;
	}
	@Transient
	public Integer getId() {

		return null;
	}
	
	public void setId(Integer id) {

		
	}
	
	
	
}
