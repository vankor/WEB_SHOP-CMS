package Model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;

import Model.others.AdressType;
import Model.dto_beans.TownBean;
import Model.others.Town_Delivery;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "city")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 4)

public class Town implements Comparable, Model.entity.Entity, Serializable{
	
	private Integer id;
	private String name;
	private Region region;
	private Integer phonecode;
	private Set<Order> orders = new TreeSet<Order>();
	private Set<User> users = new TreeSet<User>();
	private List<Adress> residents = new ArrayList<Adress>();
	private transient List<Adress> shopresidents = new ArrayList<Adress>();
	private transient List<Adress> deliveryservices = new ArrayList<Adress>();



	private transient List<Adress> userresidents = new ArrayList<Adress>();
	private List<Town_Delivery> towndeliveries = new ArrayList<Town_Delivery>();
	private Boolean isdefault = false;
	private Boolean isdeliverytown = false;


	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	@AssertFalse
	public Boolean getIsdeleted() {
	
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public void constructFromBean(TownBean twnbn){
		this.name = twnbn.getName();
		this.region = twnbn.getRegion();
		this.phonecode = twnbn.getPhonecode();
		this.residents = twnbn.getResidents();
		this.isdeliverytown = (!twnbn.getResidents().isEmpty())?true:false;
		System.out.println("isdelivery  "+this.isdeliverytown);
		

		
		
		

	}
	
	@Transient
	public List<Adress> getShopresidents() {
		List<Adress> shopaddr = new ArrayList<Adress>();
		
		for(Adress a:residents){
			if(a.getAdresstype()!=null){
			if(a.getAdresstype().equals(AdressType.RESIDENT)){
				shopaddr.add(a);
			}
			
		}
		}
		return shopaddr;
	}
	public void setShopresidents(List<Adress> shopresidents) {
		this.shopresidents = shopresidents;
	}
	
	@Column(name = "isdeliverytown")
	@Type(type = "boolean")
	public Boolean getIsdeliverytown() {
		return isdeliverytown;
	}
	public void setIsdeliverytown(Boolean isdeliverytown) {
		this.isdeliverytown = isdeliverytown;
	}

	
	@Transient
	public List<Adress> getDeliveryservices() {
		List<Adress> shopaddr = new ArrayList<Adress>();
		
		for(Adress a:residents){
			if(a.getAdresstype()!=null){
			if(a.getAdresstype().equals(AdressType.DELIVERYSERVICE)){
				shopaddr.add(a);
			}
			
		}
		}
		return shopaddr;
	}
	public void setDeliveryservices(List<Adress> deliveryservices) {
		this.deliveryservices = deliveryservices;
	}
	
	@Transient
	public List<Adress> getUserresidents() {
		List<Adress> useraddr = new ArrayList<Adress>();
		for(Adress a:residents){
			if(a.getAdresstype()!=null){
			if(a.getAdresstype().equals(AdressType.CLIENT)){
				useraddr.add(a);
			}
			}
		}
		return useraddr;
	}
	public void setUserresidents(List<Adress> userresidents) {
		this.userresidents = userresidents;
	}
	@OneToMany(mappedBy="clienttown", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JsonManagedReference
	@Fetch(FetchMode.SELECT)

	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	
	@OneToMany(mappedBy="town", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@Fetch(FetchMode.SUBSELECT)
		public List<Adress> getResidents() {

		return residents;
	}
	public void setResidents(List<Adress> residents) {
		this.residents = residents;
		for(Adress a:residents){
			a.setTown(this);
		}
	}
	
	@Column(name = "isdefault")
	@Type(type = "boolean")
	public Boolean getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	@JsonBackReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	@OneToMany(mappedBy="town", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JsonManagedReference
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "phone_code")
	public Integer getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(Integer phonecode) {
		this.phonecode = phonecode;
	}
	

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.town", cascade = CascadeType.ALL)
	public List<Town_Delivery> getTowndeliveries() {
		return towndeliveries;
	}
	public void setTowndeliveries(List<Town_Delivery> towndeliveries) {
		this.towndeliveries = towndeliveries;
	}
	
	public void addTowndelivery(Town_Delivery g) {
		this.towndeliveries.add(g);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Town other = (Town) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object arg0) {
		Town othertown = null;
		if(arg0 instanceof Town){	
			othertown = (Town)arg0;
		}
		else{throw new ClassCastException("���������� ���������� ������� ������ ������!");}
		return this.name.compareTo(othertown.name);
	}
	
	public void addAdress(Adress adress) {
		residents.add(adress);
	}
	
	

	
}
