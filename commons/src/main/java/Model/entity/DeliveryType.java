package Model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Model.others.AdressType;
import Model.others.Town_Delivery;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="deliverytypes")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class DeliveryType implements Comparable, Model.entity.Entity {
	
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
		DeliveryType other = (DeliveryType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return name;
	}
	private Integer id;
	private String name;
	private String shortname;
	private String info;
	private AdressType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name="del_type")
	public AdressType getType() {
		return type;
	}
	public void setType(AdressType type) {
		this.type = type;
	}
	private Set<Order> orders = new TreeSet<Order>();
	private List<Town_Delivery> towndeliveries = new ArrayList<Town_Delivery>();
	
	
	private Boolean isdeleted = false;
	
	@Column(name = "textinfo",columnDefinition="TEXT", length = 100000)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.delivery")
	public List<Town_Delivery> getTowndeliveries() {
		return towndeliveries;
	}
	public void setTowndeliveries(List<Town_Delivery> towndeliveries) {
		this.towndeliveries = towndeliveries;
	}
	@OneToMany(mappedBy="deliverytype", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	@Id
	@GenericGenerator(name = "increment", strategy = "increment") 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column (name = "shortname", nullable = false)
	public String getShortname() {
		return shortname;
	}
	
	@Override
	public int compareTo(Object arg0) {
		DeliveryType othergood = null;
		if(arg0 instanceof DeliveryType){	
			othergood = (DeliveryType)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othergood.name);
	}
	
	public void addTowndeliverytype(Town_Delivery tdlv){
		towndeliveries.add(tdlv);
	}
	

}
