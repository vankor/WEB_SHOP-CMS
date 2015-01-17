package Model.filters;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import Model.EntityField;
import Model.FilterType;

@Entity
@Table(name = "rangefilters")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class PropRangeFilter implements Model.Entity, AdminFilter{
	private Integer id;
	private EntityField fld;
	
	
	
	private String min;
	private String max;
	
	private Boolean isdeleted; 
	
	
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
	
	
	@Type(type = "boolean")
	@Column(name="isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@ManyToOne
	@JoinColumn(name = "fld_id")
	public EntityField getFld() {
		return fld;
	}
	public void setFld(EntityField fld) {
		this.fld = fld;
	}
	
		
	@Column(name="min")
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	
	@Column(name="max")
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	

	
	@Transient
	public List<String> getValues() {
		List<String> ff = new ArrayList<String>();
		ff.add(min);
		ff.add(max);
		return ff;
	}
	
}
