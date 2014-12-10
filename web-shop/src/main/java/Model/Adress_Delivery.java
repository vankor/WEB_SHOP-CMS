package Model;

import java.io.Serializable;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name="adress_delivery")
@AssociationOverrides({
	@AssociationOverride(name="pk.adress", joinColumns = @JoinColumn(name="adress_id")),
	@AssociationOverride(name="pk.delivery", joinColumns = @JoinColumn(name="delivery_id"))
})
public class Adress_Delivery implements Model.Entity, Serializable{
	private AdressDeliveryId pk = new AdressDeliveryId();
	private Double mindelprice;
	private Double maxdelprice;
	private Integer mindelterm;
	private Integer maxdelterm;
	private Boolean iscostless;
	private Boolean isdeleted;
	
	
	@Column(name = "iscostless")
	@Type(type = "boolean")
	public Boolean getIscostless() {
		return iscostless;
	}
	public void setIscostless(Boolean iscostless) {
		this.iscostless = iscostless;
	}
	@EmbeddedId
	public AdressDeliveryId getPk() {
		return pk;
	}
	public void setPk(AdressDeliveryId pk) {
		this.pk = pk;
	}
	
	@Column(name="mindelprice")
	public Double getMindelprice() {
		return mindelprice;
	}
	public void setMindelprice(Double mindelprice) {
		this.mindelprice = mindelprice;
	}
	
	
	@Transient
	public Adress getAdress() {
		return getPk().getAdress();
	}
 
	
	public void setAdress(Adress stock) {
		getPk().setAdress(stock);
	}
 
	@Transient
	public DeliveryType getDeliveryType() {
		return getPk().getDelivery();
	}
 
	public void setDeliveryType(DeliveryType deltype) {
		getPk().setDelivery(deltype);
	}
	
	@Column(name="maxdelprice")
	public Double getMaxdelprice() {
		return maxdelprice;
	}
	public void setMaxdelprice(Double maxdelprice) {
		this.maxdelprice = maxdelprice;
	}
	
	@Column(name="mindelterm")
	public Integer getMindelterm() {
		return mindelterm;
	}
	public void setMindelterm(Integer mindelterm) {
		this.mindelterm = mindelterm;
	}
	
	@Column(name="maxdelterm")
	public Integer getMaxdelterm() {
		return maxdelterm;
	}
	public void setMaxdelterm(Integer maxdelterm) {
		this.maxdelterm = maxdelterm;
	}
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	@Transient
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
