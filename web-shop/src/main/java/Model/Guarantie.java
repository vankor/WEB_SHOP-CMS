package Model;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "guaranties")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Guarantie implements Comparable, Model.Entity, Serializable{
	private Integer id;
	private String periodtype;
	private Integer count;
	private String info;

	
	private Boolean isdeleted = false;
	
	
	@Override
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return count+" "+periodtype;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "info",columnDefinition="TEXT", length = 100000)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Type(type = "boolean")
	@Column(name = "isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public Guarantie(){}
	
	@Column(name = "periodtype")
	@NotEmpty(message = "*Временной период должен быть обязательно заполнен!")
	public String getPeriodtype() {
		return periodtype;
	}
	public void setPeriodtype(String periodtype) {
		this.periodtype = periodtype;
	}
	
	@Column(name = "count")
	@NotNull(message = "*Количество периодов должно быть обязательно заполнено!")
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
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
		Guarantie other = (Guarantie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object arg0) {
		Guarantie othergood = null;
		if(arg0 instanceof Guarantie){	
			othergood = (Guarantie)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.id.compareTo(othergood.id);
	}
	public void fillpagegroup(GuarantieBean guarantiebean) {
		this.id = guarantiebean.getId();
		this.periodtype = guarantiebean.getPeriodtype();
		this.count = guarantiebean.getCount();
		
	}
}
