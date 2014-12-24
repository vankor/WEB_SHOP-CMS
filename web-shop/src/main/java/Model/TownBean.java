package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class TownBean {
	
	private Integer id;
	private String name;
	private Region region;
	private Integer phonecode;
	private List<Adress> residents = new ArrayList<Adress>();
	private List<DeliveryTypeBean> deltypes = new ArrayList<DeliveryTypeBean>();
	private List<Adress> shopresidents = new ArrayList<Adress>();
	private List<Adress> shopdelresidents = new ArrayList<Adress>();
	private Boolean isdefault;
	private Boolean isdeliverytown;
	
	
	public Boolean getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}
	public List<Adress> getShopdelresidents() {
		return shopdelresidents;
	}
	public void setShopdelresidents(List<Adress> shopdelresidents) {
		this.shopdelresidents = shopdelresidents;
	}
	public List<DeliveryTypeBean> getDeltypes() {
		return deltypes;
	}
	public List<Adress> getShopresidents() {
		return shopresidents;
	}
	public void setShopresidents(List<Adress> shopresidents) {
		this.shopresidents = shopresidents;
	}
	public void setDeltypes(List<DeliveryTypeBean> deltypes) {
		this.deltypes = deltypes;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPhonecode() {
		return phonecode;
	}
	public void setPhonecode(Integer phonecode) {
		this.phonecode = phonecode;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsdeliverytown() {
		return isdeliverytown;
	}
	public void setIsdeliverytown(Boolean isdeliverytown) {
		this.isdeliverytown = isdeliverytown;
	}
	
	public List<Adress> getResidents() {
		return residents;
	}
	public void setResidents(List<Adress> residents) {
		this.residents = residents;
	}
	public void addAdress(Adress adress) {
		residents.add(adress);
	}
	
	public Integer getId() {
		return id;
	}
	
	
	public void constructFromTown(Town g){
		this.id = g.getId();
		this.name = g.getName();
		this.region = g.getRegion();
		this.phonecode = g.getPhonecode();
		this.residents = g.getResidents();
		for(Town_Delivery tdlw: g.getTowndeliveries()){
			DeliveryTypeBean bn = new DeliveryTypeBean();
			bn.setDeltype(tdlw.getDeliveryType());
			bn.setIscostless(tdlw.getIscostless());
			bn.setPrice(tdlw.getPrice());
			deltypes.add(bn);
		}
		this.shopresidents = g.getShopresidents();
		this.isdefault = g.getIsdefault();
		
		
	}
}
