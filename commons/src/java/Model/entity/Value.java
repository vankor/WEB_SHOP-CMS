package Model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="good_param_val")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Value implements Comparable, Model.Entity{
	private Integer id;
	private String value;
	private Property prop;
//	private GoodItem good;
	private List<GoodItem> goods = new ArrayList<GoodItem>();
	private Integer priority;

	@Column(name="priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
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
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne
	@JoinColumn(name="prop_id")
	@JsonBackReference
	@JsonIgnore
	public Property getProp() {
		return prop;
	}
	public void setProp(Property prop) {
		this.prop = prop;
	}
	
//	@ManyToOne
//	@JoinColumn(name="good_id")
//	@JsonBackReference
//	public GoodItem getGood() {
//		return good;
//	}
//	public void setGood(GoodItem good) {
//		this.good = good;
//	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "good_values", 
	joinColumns = {@JoinColumn(name = "value_id", updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonIgnore
	public List<GoodItem> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodItem> goods) {
		this.goods = goods;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public int compareTo(Object o) {
		Value othergood = null;
		if(o instanceof Value){	
			othergood = (Value)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.value.compareTo(othergood.value);
	}

}
