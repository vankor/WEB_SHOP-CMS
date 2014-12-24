package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "adress")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 4)
public class Adress implements Comparable, Model.Entity{
	
	private Integer id;
    private String street_type;
	private String street_name;
	private String house_num;
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
		Adress other = (Adress) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	private String level;
	private String room_num;
	private AdressType adresstype;
	private List<Order> orders = new ArrayList<Order>();
	private Set<User> users = new TreeSet<User>();
	private List<PhoneNumber> phnnumbers = new ArrayList<PhoneNumber>();
	private List<Adress_Delivery> addrdel = new ArrayList<Adress_Delivery>();
	private transient List<DeliveryType> delivtypes = new ArrayList<DeliveryType>();
	private transient List<DeliveryTypeBean> deltypes = new ArrayList<DeliveryTypeBean>();
	private Town town;
	
	@Transient
	public List<DeliveryType> getDelivtypes() {
		List<DeliveryType> dtps = new ArrayList<DeliveryType>();
		for(Adress_Delivery adlv: addrdel){
			dtps.add(adlv.getDeliveryType());
		}
		return dtps;
	}
	
	public void setDelivtypes(List<DeliveryType> delivtypes) {
		this.delivtypes = delivtypes;
	}
	
	@Transient
	@Valid
	public List<DeliveryTypeBean> getDeltypes() {
				return deltypes;
		
		
	}
	public void setDeltypes(List<DeliveryTypeBean> deltypes) {
		this.deltypes = deltypes;
	}
	
	public void filldeltypes(){
		for(Adress_Delivery adlv: addrdel){
			DeliveryTypeBean dtbb = new DeliveryTypeBean();
			dtbb.setDeltype(adlv.getDeliveryType());
			dtbb.setMaxdays(adlv.getMaxdelterm());
			dtbb.setMindays(adlv.getMindelterm());
			dtbb.setMinprice(adlv.getMindelprice());
			dtbb.setMaxprice(adlv.getMaxdelprice());
			dtbb.setIscostless(adlv.getIscostless());
			deltypes.add(dtbb);
		}
	}
	
//	private Resident resident;
	
	
	
//	@OneToOne
//    @PrimaryKeyJoinColumn
//	public Resident getResident() {
//		return resident;
//	}
//	public void setResident(Resident resident) {
//		this.resident = resident;
//	}
	
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted", nullable = false, columnDefinition = "bit(1) default 0")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@OneToMany(mappedBy = "pk.adress", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Adress_Delivery> getAddrdel() {
		return addrdel;
	}
	public void setAddrdel(List<Adress_Delivery> addrdel) {
		this.addrdel = addrdel;
	}
	
	@OneToMany(mappedBy = "resident", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@Valid
	public List<PhoneNumber> getPhnnumbers() {
		return phnnumbers;
	}
	public void setPhnnumbers(List<PhoneNumber> phnnumbers) {
		this.phnnumbers = phnnumbers;
		for(PhoneNumber phn: phnnumbers){
			phn.setResident(this);
		}
	}
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="street_type")
	@NotNull(message = "*Тип улицы обязателен для заполнения!")
	@NotEmpty(message = "*Тип улицы обязателен для заполнения!")
	public String getStreet_type() {
		return street_type;
	}
	public void setStreet_type(String street_type) {
		this.street_type = street_type;
	}
	
	@Column(name="street_name")
	@NotNull(message = "*Название улицы обязательно для заполнения!")
	@NotEmpty(message = "*Название улицы обязательно для заполнения!")
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	
	@Column(name="house_num")
	@NotNull(message = "*Номер дома обязателен для заполнения!")
	@NotEmpty(message = "*Номер дома обязателен для заполнения!")
	public String getHouse_num() {
		return house_num;
	}
	public void setHouse_num(String house_num) {
		this.house_num = house_num;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="room_num")
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	
	@ManyToOne
	@JoinColumn(name="town_id")
	@JsonBackReference
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	
	@OneToMany(mappedBy = "adress", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="adress_type")
	public AdressType getAdresstype() {
		return adresstype;
	}
	public void setAdresstype(AdressType adresstype) {
		this.adresstype = adresstype;
	}

	@OneToMany(mappedBy = "adress", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@Fetch(value = FetchMode.SELECT)
	@JsonManagedReference
	public Set<User> getUsers() {
		return users;
	}
	@Override
	public String toString() {
		StringBuilder strbld = new StringBuilder();
		if(street_type!=null){
			strbld.append(street_type);
		}
		if(street_name!=null){
			strbld.append(" "+street_name);
		}
		
		if(house_num!=null){
			strbld.append(" дом "+house_num);
		}
		
		if(level!=null){
			strbld.append(" этаж "+level);
		}
		
		if(room_num!=null){
			strbld.append(" кв. "+room_num);
		}
		
		return strbld.toString();
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addNumber(PhoneNumber n) {
		phnnumbers.add(n);
		n.setResident(this);
	}
	@Override
	public int compareTo(Object o) {
		Adress otherord = null;
		if(o instanceof Adress){	
			otherord = (Adress)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.street_name.compareTo(otherord.street_name);
	}
}


