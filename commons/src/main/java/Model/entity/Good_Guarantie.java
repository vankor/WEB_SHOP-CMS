package Model.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.AssociationOverride;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import Model.others.Positive;
import org.hibernate.annotations.Type;

@Entity
@Table(name="good_guarantie")
@AssociationOverrides({
	@AssociationOverride(name="pk.good", joinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)}),
	@AssociationOverride(name="pk.guarantie", joinColumns = {@JoinColumn(name = "guarantie_id", nullable = false, updatable = false)})
})
public class Good_Guarantie implements Model.entity.Entity, Serializable{
	
	private GoodGuarantieId pk= new GoodGuarantieId();
	private Double price;
	private Boolean iscostless = false;
	private Boolean isdeleted;
	
	@EmbeddedId 
	public GoodGuarantieId getPk() {
		return pk;
	}
	public void setPk(GoodGuarantieId pk) {
		this.pk = pk;
	}
	
	@Column(name="price")
	@NotNull(message = "*Цена гарантии должны бать указана!")
	@Positive(message = "*Цена должна быть позитивным числовым значением!")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Type(type="boolean")
	@Column(name="iscostless")
	public Boolean getIscostless() {
		return iscostless;
	}
	public void setIscostless(Boolean iscostless) {
		this.iscostless = iscostless;
	}
	
	@Type(type="boolean")
	@Column(name="isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Transient
	public GoodItem getGood() {
		return getPk().getGood();
	}
 
	
	public void setGood(GoodItem good) {
		getPk().setGood(good);
	}
 
	@Transient
	public Guarantie getGuarantie() {
		return getPk().getGuarantie();
	}
 
	public void setGuarantie(Guarantie guar) {
		getPk().setGuarantie(guar);
	}
	
	@Transient
	public Integer getId() {

		return null;
	}
	@Transient
	public void setId(Integer id) {

		
	}
	
}
