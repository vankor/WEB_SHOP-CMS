package Model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "goodstates")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodState implements Comparable, Model.entity.Entity, Serializable{
	private Integer id;
	private String text;
	private Set<GoodItem> goods = new TreeSet<GoodItem>();
	
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
	@GenericGenerator(name="id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "text", unique = true)
	public String getText() {
		return text;
	}
	@Override
	public String toString() {
		return  text;
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
		GoodState other = (GoodState) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	public Set<GoodItem> getGoods() {
		return goods;
	}
	public void setGoods(Set<GoodItem> goods) {
		this.goods = goods;
	}
	@Override
	public int compareTo(Object o) {

		return 0;
	}
			
}
