package Model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name="propsegments")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PropSegment implements Comparable, Model.entity.Entity {
	private Integer id;
	

	private String name;
	private List<Property> properties = new ArrayList<Property>();
	private Integer priority;


	
	@Column(name = "priority")
	@NotNull(message = "*Необходимо задать приоритет сегмента параметров!")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	private Boolean isdeleted = false;
	
	
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
		PropSegment other = (PropSegment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	
	@OneToMany(mappedBy = "propsegment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@Valid
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
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

	@Column(name = "name")
	@NotNull(message = "*Сегмент параметров должен иметь имя!")
	@NotEmpty(message = "*Сегмент параметров должен иметь имя!")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {

		return 0;
	}
	
	public void addProperty(Property prop){
		properties.add(prop);
		prop.setPropsegment(this);
	}
	











	

}
