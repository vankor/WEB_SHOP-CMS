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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Model.PageGroupBean;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "pagegroups")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class PageGroup  implements Comparable, Model.Entity, Serializable{
	private Integer id;
	private String name;
	private String thumb;
	private Set<Page> pages = new TreeSet<Page>();
	private Boolean isdeleted = false;
	private Boolean isinfooter = false;
	
	
	@Override
	@Id
	@GenericGenerator(name="increment", strategy = "increment")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@OneToMany(mappedBy="pagegroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	public Set<Page> getPages() {
		return pages;
	}
	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}
	
	@Column(name = "name")
	@NotEmpty(message = "*Имя должно быть обязательно заполнено!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "thumb")
	@NotEmpty(message = "*Необходимо загрузить логотип!")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	@Type(type = "boolean")
	@Column(name = "isdeleted")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public void fillpagegroup(PageGroupBean prodbean){
		this.id = prodbean.getId();
		this.name = prodbean.getName();
		this.thumb = prodbean.getThumb();
		this.isinfooter = prodbean.getIsinfooter();
	}
	
	@Type(type = "boolean")
	@Column(name = "isinfooter")
	public Boolean getIsinfooter() {
		return isinfooter;
	}
	public void setIsinfooter(Boolean isinfooter) {
		this.isinfooter = isinfooter;
	}
	
	@Override
	public int compareTo(Object arg0) {
		PageGroup othergood = null;
		if(arg0 instanceof PageGroup){	
			othergood = (PageGroup)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othergood.name);
	}
}
