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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Model.dto_beans.ProducerBean;
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
@Table(name = "producers")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Producer implements Comparable, Model.entity.Entity, Serializable {
	
	private Integer id;
	private String name;
	private String thumb;
	private Set<Category> categories = new TreeSet<Category>();
	private Set<GoodItem> goods = new TreeSet<GoodItem>();
	private Boolean isdeleted = false;
	
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
	@NotEmpty(message = "*Имя должно быть обязательно заполнено!")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column (name = "thumb")
	@NotEmpty(message = "*Необходимо загрузить логотип!")
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "producer_category", 
	joinColumns = {@JoinColumn(name = "producer_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
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
	
	
	@Override
	public String toString() {
		return name;
	}

	public void fillProducer(ProducerBean prod) {
		
		this.setName(prod.getName());
		this.setThumb(prod.getThumb());
	}

	@Override
	public int compareTo(Object o) {
		Producer othergood = null;
		if(o instanceof Producer){	
			othergood = (Producer)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othergood.name);
	}
	
}
