package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Transient;
import javax.validation.Valid;

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
@Table(name = "sliders")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Slider implements Comparable, FileEntity, Model.Entity, Serializable{
	private Integer id;
	private String name;
	private String folderpath;
	
	private List<SliderImage> images = new ArrayList<SliderImage>();

	
	private Boolean isonmain;
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
	
	@Column(name = "name")
	@NotEmpty(message = "*Имя слайдера - обязателен для заполнения!")
	public String getName() {
		return name;
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
		Slider other = (Slider) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "slider", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@Override
	@Valid
	public List<SliderImage> getImages() {
		return images;
	}
	public void setImages(List<SliderImage> images) {
		this.images = images;
	}
	
	@Column(name = "isonmain")
	@Type(type = "boolean")
	public Boolean getIsonmain() {
		return isonmain;
	}
	public void setIsonmain(Boolean isonmain) {
		this.isonmain = isonmain;
	}
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "folderpath")
	public String getFolderpath() {
		return folderpath;
	}
	public void setFolderpath(String folderpath) {
		this.folderpath = folderpath;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void fillfrombean(SliderBean slider){
		id = slider.getId();
		name = slider.getName();
		folderpath = slider.getFolderpath();
		isonmain = slider.getIsonmain();
	}
	@Override
	public String toString() {
		return name;
	}
	@Transient
	public String getThumb() {
			return null;
	}
	@Transient
	public void setThumb(String thumb) {
				
	}
	@Transient
	public void addImage(Image im) {
		if(im instanceof SliderImage)
		images.add((SliderImage)im);
	}
	@Transient
	public void removeImage(Image im) {
		if(im instanceof SliderImage)
			images.remove((SliderImage)im);
		
	}
	@Transient
	public void addVideo(Video im) {
				
	}
	@Transient
	public List<Video> getVideos() {
		
		return null;
	}
	@Transient
	public void removeVideo(Video vid) {
			
	}
	
	
	
}
