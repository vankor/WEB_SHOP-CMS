package Model.filters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Model.EntityField;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "fieldpriorities")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class FieldPriority implements Model.entity.Entity {
	private Integer id;
	private EntityField fld;
	private Boolean isdeleted; 
	private Integer priority;
	
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
	
	@ManyToOne
	@JoinColumn(name = "fld_id")
	public EntityField getFld() {
		return fld;
	}
	public void setFld(EntityField fld) {
		this.fld = fld;
	}
	
	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Type(type = "boolean")
	@Column(name="isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

}
