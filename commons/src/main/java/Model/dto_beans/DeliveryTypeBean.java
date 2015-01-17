package Model.dto_beans;

import Model.others.AdressType;
import Model.entity.DeliveryType;

public class DeliveryTypeBean {
	
	private DeliveryType deltype;
	private Double price;
	private Double minprice;
	private Double maxprice;
	private Integer days;
	private AdressType type;
	
	public AdressType getType() {
		return type;
	}
	public void setType(AdressType type) {
		this.type = type;
	}
	
	public Double getMinprice() {
		return minprice;
	}
	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}
	public Double getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}

	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getMaxdays() {
		return maxdays;
	}
	public void setMaxdays(Integer maxdays) {
		this.maxdays = maxdays;
	}
	public Integer getMindays() {
		return mindays;
	}
	public void setMindays(Integer mindays) {
		this.mindays = mindays;
	}

	private Integer maxdays;
	private Integer mindays;
	private Boolean iscostless;
	public DeliveryType getDeltype() {
		return deltype;
	}
	public void setDeltype(DeliveryType deltype) {
		this.deltype = deltype;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Boolean getIscostless() {
		return iscostless;
	}
	public void setIscostless(Boolean iscostless) {
		this.iscostless = iscostless;
	}

}
