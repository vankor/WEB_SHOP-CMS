package Model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Model.PhoneNumberType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="phonenumbers")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneNumber implements Comparable, Model.Entity, Serializable{
	
	private Integer id;
	private String numb;
	private Country country;

	//	private User user;
	private Set<User> users = new TreeSet<User>();
	private Town town;
	
	private Adress resident;
	private PhoneNumberType phtype;


	private Boolean isMainResidentNumber;
	
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
		PhoneNumber other = (PhoneNumber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	private Boolean isMainCommonNumber;
	private Boolean isCostlessLine;
	
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@ManyToOne
	@JoinColumn(name="country_id")
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Type(type = "boolean")
	@Column(name = "iscostlessline")
	public Boolean getIsCostlessLine() {
		return isCostlessLine;
	}
	public void setIsCostlessLine(Boolean isCostlessLine) {
		this.isCostlessLine = isCostlessLine;
	}
	
	@Type(type = "boolean")
	@Column(name = "iscommonmain")
	public Boolean getIsMainCommonNumber() {
		return isMainCommonNumber;
	}
	public void setIsMainCommonNumber(Boolean isMainCommonNumber) {
		this.isMainCommonNumber = isMainCommonNumber;
	}
	
	@Type(type = "boolean")
	@Column(name = "ismain")
	public Boolean getIsMainResidentNumber() {
		return isMainResidentNumber;
	}
	public void setIsMainResidentNumber(Boolean isMainResidentNumber) {
		this.isMainResidentNumber = isMainResidentNumber;
	}
	
	@ManyToOne
	@JoinColumn(name = "resident_id")
	@JsonBackReference
	public Adress getResident() {

			return resident;
	}
	public void setResident(Adress res) {
	//	if(res.getAdresstype().equals(AdressType.RESIDENT)){
			this.resident = res;
	//	}
	}
	@Id
	@GenericGenerator(name = "id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "number_type")
	public PhoneNumberType getPhtype() {
		return phtype;
	}
	public void setPhtype(PhoneNumberType phtype) {
		this.phtype = phtype;
	}
	
	@Column(name="numb", unique=true)
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	
	@ManyToOne
	@JoinColumn(name = "town_id")
	@JsonBackReference
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	
	@ManyToMany
	@JoinTable(name = "user_phones", joinColumns = {@JoinColumn(name = "phone_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void setNumTypeId(Integer numbtypeid){
		PhoneNumberType numtype = (numbtypeid==1)?PhoneNumberType.MOBILE:PhoneNumberType.STATIONAR;
		setPhtype(numtype);
	}
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	@JsonBackReference
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
