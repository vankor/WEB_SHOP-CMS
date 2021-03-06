package Model.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

import Model.dto_beans.CountryBean;
import org.hibernate.annotations.BatchSize;
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
@Table(name = "country")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 4)
public class Country implements Serializable,  Comparable, Model.entity.Entity {
	
	private Integer id;
	private String name;
	private String phonecode;
	private String flagicon;
	
	private List<Region> regions = new ArrayList<Region>();
	
	
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
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "phonecode", unique = true)
	@NotNull(message = "*��� ������ ������ ���� ��������!")
	@NotEmpty(message = "*��� ������ ������ ���� ��������!")
	public String getPhonecode() {
		return phonecode;
	}
	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
	
	@Column(name = "flagicon")
	public String getFlagicon() {
		return flagicon;
	}
	public void setFlagicon(String flagicon) {
		this.flagicon = flagicon;
	}
	
	@Column(name = "name")
	@NotNull(message = "*��� ������ ������ ���� ���������!")
	@NotEmpty(message = "*��� ������ ������ ���� ���������!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	
		
	}
	@Override
	public int compareTo(Object o) {

		return 0;
	}
	
	public void constructFromBean(CountryBean cntr){
		this.name = cntr.getName();
		this.phonecode = cntr.getPhonecode();
	}

}
