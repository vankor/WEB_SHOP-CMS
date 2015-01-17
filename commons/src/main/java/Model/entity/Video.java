package Model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Model.others.MediaFile;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "videos")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Video implements MediaFile, Model.entity.Entity, Comparable{
	
	private String url;
	private GoodItem good;
	
	private Integer id;
	
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
	@GenericGenerator(name="id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Video(){}
	
	
	public Video(String url, GoodItem good) {
		this.url = url;
		this.good = good;
	}

	

	public Video(String databasepath) {
		this.url = databasepath;
	}


	@Column(name = "url")
	@NotEmpty(message = "*Необходимо задать статус товара!")
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


	@Override
	public int compareTo(Object o) {

		return 0;
	}
	
	

}
