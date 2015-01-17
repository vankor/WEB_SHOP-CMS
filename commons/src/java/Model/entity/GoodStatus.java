package Model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import Model.GoodStatusBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "goodstatuses")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodStatus implements Comparable, Model.Entity, Serializable{

	
	private Integer id;
	private String text;
	private Set<GoodItem> goods = new TreeSet<GoodItem>();
	private String thumb;
	


	@Override
	public String toString() {
		return text;
	}

	private Boolean isdeleted = false;

	@Column(name = "thumb")
	@NotNull(message = "*Необходимо задать иконку для статуса!")
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
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

	
	
	@Column(name = "text")
	@NotNull(message = "*Текст статуса обязателен для заполнения!")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	
	@ManyToMany
	@JoinTable(name = "good_status",
	joinColumns = {@JoinColumn(name="status_id", nullable = false, updatable=false)},
	inverseJoinColumns = {@JoinColumn(name="good_id", nullable = false, updatable=false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<GoodItem> getGoods() {
		return goods;
	}

	public void setGoods(Set<GoodItem> goods) {
		this.goods = goods;
	}

	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public void fillgoodstatus(GoodStatusBean gstbean) {
		this.id = gstbean.getId();
		this.text = gstbean.getText();
		this.thumb = gstbean.getThumb();
		
	}

	@Override
	public int compareTo(Object o) {
		GoodStatus otherstatus = null;
		if(o instanceof GoodItem){	
			otherstatus = (GoodStatus)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.text.compareTo(otherstatus.text);
	}
	
	

}
