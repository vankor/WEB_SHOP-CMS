package Model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
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
@Table(name = "properties")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Property implements Comparable, Model.Entity, Serializable {
	
	private Integer id;
	private String name;
//	private String value;
	private Category cat;
	private Set<Value> vals = new TreeSet<Value>();
	private PropSegment propsegment;
	
	private Boolean isdeleted = false;
	private Integer mainvalscount;
	private Integer priority;
	
	@Column(name = "priority")
	@NotNull(message = "*Необходимо задать приоритет свойства")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "mainvalscount")
	@NotNull(message = "*Необходимо задать количесво основных значений")
	public Integer getMainvalscount() {
		return mainvalscount;
	}
	public void setMainvalscount(Integer mainpropscount) {
		this.mainvalscount = mainpropscount;
	}
	
	@Id
	@GenericGenerator(name = "id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column (name = "name")
//	@NotNull(message = "*Параметр должен иметь имя!")
//	@NotEmpty(message = "*Параметр должен иметь имя!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	@Column (name = "value")
//	public String getValue() {
//		return value;
//	}
//	public void setValue(String value) {
//		this.value = value;
//	}
	
	@OneToMany (mappedBy = "prop", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Value> getVal() {
		return vals;
	}
	public void setVal(Set<Value> vals) {
		this.vals = vals;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "cat_id")
	@JsonBackReference
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
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
		Property other = (Property) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object arg0) {
		Property otherprop = null;
		if(arg0 instanceof Property){	
			otherprop = (Property)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(otherprop.name);
	}
	@ManyToOne
	@JoinColumn(name = "propsegment_id")
	@JsonBackReference
	public PropSegment getPropsegment() {
		return propsegment;
	}
	public void setPropsegment(PropSegment propsegment) {
		this.propsegment = propsegment;
	}
	
	
}
