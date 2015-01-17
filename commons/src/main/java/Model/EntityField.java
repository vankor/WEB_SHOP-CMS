package Model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import Model.service.CommonUtilsService;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "entityfields")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class EntityField implements Comparable, Model.entity.Entity, Serializable{
	private Integer id;
	private String propname;
	private String colname;
	private Integer priority;
	private String classname;
	private Boolean ismainpageprop;
	private transient Boolean isdate = false;
	private transient Boolean isobject = false;;
	private transient String propclassname;
	private transient Class propclass;
	
	private Boolean hidden = false;
	
	
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Transient
	public String getPropclassname() {
		return getPropclass().getCanonicalName();
	}
	public void setPropclassname(String propclasname) {
		this.propclassname = propclasname;
	}
	
	@Transient
	public Class getPropclass() {
		if(propclass!=null)return propclass;
		else{
			try {
				return CommonUtilsService.getFieldClassByClass(Class.forName("Model."+classname), propname);
			} catch (IllegalArgumentException | IllegalAccessException
					| InvocationTargetException | NoSuchMethodException
					| ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		return null;
	}
	public void setPropclass(Class propclass) {
		this.propclass = propclass;
	}
	
	@Transient
	public Boolean getIsobject() {
		return isobject;
	}
	public void setIsobject(Boolean isobject) {
		this.isobject = isobject;
	}
	
	@Transient
	public Boolean getIsdate() {
		return isdate;
	}
	public void setIsdate(Boolean isdate) {
		this.isdate = isdate;
	}
	
	@Type(type = "boolean")
	@Column(name = "ismainpageprop")
	public Boolean getIsmainpageprop() {
		return ismainpageprop;
	}
	public void setIsmainpageprop(Boolean ismainpageprop) {
		this.ismainpageprop = ismainpageprop;
	}
	@Column(name = "propname")
	public String getPropname() {
		return propname;
	}
	public void setPropname(String propname) {
		this.propname = propname;
	}
	
	@Column(name = "colname")
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	
	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Column(name = "classname")
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "hidden")
	@Type(type = "boolean")
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
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
	@Override
	public int compareTo(Object arg0) {
		EntityField othergood = null;
		if(arg0 instanceof EntityField){	
			othergood = (EntityField)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.colname.compareTo(othergood.colname);
	}
}
