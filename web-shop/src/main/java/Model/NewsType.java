package Model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "newstypes")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class NewsType  implements Comparable, Model.Entity, Serializable{

private Integer id;


private String name;
private String title;


private Boolean isdeleted;
private Set<News> news = new TreeSet<News>();

private transient Map<Category,Long> categnews = new TreeMap<Category, Long>();

@Transient
public Map<Category, Long> getCategnews() {
	return categnews;
}

public void setCategnews(Map<Category, Long> categnews) {
	this.categnews = categnews;
}

@Column(name = "title")
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

@Override
@Id
@GenericGenerator(name="increment", strategy = "increment")
@GeneratedValue (strategy = GenerationType.AUTO)
@Column(name = "id")
public Integer getId() {
	// TODO Auto-generated method stub
	return id;
}

@Override
public void setId(Integer id) {
	this.id = id;
	
}

@Override
@Type(type="boolean")
@Column(name = "isdeleted")
public Boolean getIsdeleted() {
	// TODO Auto-generated method stub
	return isdeleted;
}

@Override
public void setIsdeleted(Boolean b) {
	this.isdeleted = b;
	
}

@Column(name = "name")
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SUBSELECT)
@JsonManagedReference
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public Set<News> getNews() {
	return news;
}

public void setNews(Set<News> news) {
	this.news = news;
}

@Override
public int compareTo(Object arg0) {
	NewsType othergood = null;
	if(arg0 instanceof NewsType){	
		othergood = (NewsType)arg0;
	}
	else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
	return this.name.compareTo(othergood.name);
}
}