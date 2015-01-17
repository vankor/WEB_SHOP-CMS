package Model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import Model.MediaFile;
import Model.NewsPart;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "images")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Image implements MediaFile, Comparable, Model.Entity, Serializable, NewsPart {
	
	protected String url;
	private GoodItem good;
	
	protected Integer id;
	protected Integer priority;
	
	protected String alt;
	
	protected Boolean isdeleted = false;
	private transient String value;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name="priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
		Image other = (Image) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Image(){}
	
	
	public Image(String url, GoodItem good) {
		this.url = url;
		this.good = good;
	}
	
	public Image(String url) {
		this.url = url;
		
	}

	

	public Image(Image sim) {
		url = sim.url;
		good = sim.good;
		
		id = sim.id;
		priority = sim.priority;
		
		alt = sim.alt;

	}
	
	
	@Column(name = "url")
	@NotEmpty(message = "*Путь к картинке не указан!")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@ManyToOne
	@JoinColumn(name = "good_id")
	@JsonBackReference
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}

	@Column(name = "alt")
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}

	@Override
	public int compareTo(Object o) {
		Image othergood = null;
		if(o instanceof Image){	
			othergood = (Image)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return (this.priority !=null && othergood.priority !=null)?this.priority.compareTo(othergood.priority):this.url.compareTo(othergood.url);
	}
	
	@Transient
	public String getValue() {
		return url;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
