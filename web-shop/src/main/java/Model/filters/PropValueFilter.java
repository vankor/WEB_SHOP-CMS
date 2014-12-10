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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import Model.EntityField;
import Model.FilterType;

@Entity
@Table(name = "valuefilters")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class PropValueFilter implements Model.Entity, AdminFilter{

	private Integer id;
	private EntityField fld;
	private Boolean isdeleted;
	
	@Type(type = "boolean")
	@Column(name="isdeleted")
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
	private List<String> values = new ArrayList<String>(2);
	
	
	@ManyToOne
	@JoinColumn(name = "fld_id")
	public EntityField getFld() {
		return fld;
	}
	public void setFld(EntityField fld) {
		this.fld = fld;
	}
	
	
	@ElementCollection
	@CollectionTable(name="valuefilter_vals", joinColumns=@JoinColumn(name="valuefilter_id"))
	@Column(name="vals")
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}

}
