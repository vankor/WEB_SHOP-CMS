package Model.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import Model.ConfigurationBean;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "mainconfigs")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Configuration  implements Comparable, Model.Entity, Serializable{

private Integer id;
private String name;
private Boolean hasActionsOnMain;
private Integer actionscount;
private Boolean hasNewsOnMain;
private Integer newscount;
private Boolean hasTopGoodsListOnMain;
private Boolean hasDayAndWeekTopOnMain;
private Boolean hasSlider;
private Boolean commentsModeration;
private Boolean isactive;


private Boolean hasMainInfoOnMain;
private String startpagetitle;

@Column(name = "startpagetitle")
public String getStartpagetitle() {
	return startpagetitle;
}

public void setStartpagetitle(String startpagetitle) {
	this.startpagetitle = startpagetitle;
}
@Column(name = "startpagemeta")
public String getStartpagemeta() {
	return startpagemeta;
}

public void setStartpagemeta(String startpagemeta) {
	this.startpagemeta = startpagemeta;
}

private String startpagemeta;
private Slider slider;


@ManyToOne
@JoinColumn(name="slider_id")
@Valid
public Slider getSlider() {
	return slider;
}

public void setSlider(Slider slider) {
	this.slider = slider;
}

@Type(type="boolean")
@Column(name = "hasMainInfoOnMain")
public Boolean getHasMainInfoOnMain() {
	return hasMainInfoOnMain;
}

public void setHasMainInfoOnMain(Boolean hasMainInfoOnMain) {
	this.hasMainInfoOnMain = hasMainInfoOnMain;
}

@Type(type="boolean")
@Column(name = "isactive")
public Boolean getIsactive() {
	return isactive;
}

public void setIsactive(Boolean isactive) {
	this.isactive = isactive;
}

@Type(type="boolean")
@Column(name = "commentsModeration")
public Boolean getCommentsModeration() {
	return commentsModeration;
}

public void setCommentsModeration(Boolean commentsModeration) {
	this.commentsModeration = commentsModeration;
}

@Type(type="boolean")
@Column(name = "hasSlider")
public Boolean getHasSlider() {
	return hasSlider;
}

public void setHasSlider(Boolean hasSlider) {
	this.hasSlider = hasSlider;
}

@Column(name = "name")
@NotEmpty(message = "*Имя шаблона - обязательно для заполнения!")
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Type(type="boolean")
@Column(name = "hasActionsOnMain")
public Boolean getHasActionsOnMain() {
	return hasActionsOnMain;
}

public void setHasActionsOnMain(Boolean hasActionsOnMain) {
	this.hasActionsOnMain = hasActionsOnMain;
}

@Column(name = "actionscount")
public Integer getActionscount() {
	return actionscount;
}

public void setActionscount(Integer actionscount) {
	this.actionscount = actionscount;
}

@Type(type="boolean")
@Column(name = "hasNewsOnMain")
public Boolean getHasNewsOnMain() {
	return hasNewsOnMain;
}

public void setHasNewsOnMain(Boolean hasNewsOnMain) {
	this.hasNewsOnMain = hasNewsOnMain;
}

@Column(name = "newscount")
public Integer getNewscount() {
	return newscount;
}

public void setNewscount(Integer newscount) {
	this.newscount = newscount;
}

@Type(type="boolean")
@Column(name = "hasTopGoodsListOnMain")
public Boolean getHasTopGoodsListOnMain() {
	return hasTopGoodsListOnMain;
}

public void setHasTopGoodsListOnMain(Boolean hasTopGoodsListOnMain) {
	this.hasTopGoodsListOnMain = hasTopGoodsListOnMain;
}

@Type(type="boolean")
@Column(name = "hasDayAndWeekTopOnMain")
public Boolean getHasDayAndWeekTopOnMain() {
	return hasDayAndWeekTopOnMain;
}

public void setHasDayAndWeekTopOnMain(Boolean hasDayAndWeekTopOnMain) {
	this.hasDayAndWeekTopOnMain = hasDayAndWeekTopOnMain;
}

private Boolean isdeleted = false;



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

@Override
public int compareTo(Object arg0) {
	Configuration othergood = null;
	if(arg0 instanceof Configuration){	
		othergood = (Configuration)arg0;
	}
	else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
	return this.name.compareTo(othergood.name);
}

public void fillfrombean(ConfigurationBean conf) {
	id = conf.getId();
	name = conf.getName();
	hasActionsOnMain = conf.getHasActionsOnMain();
	actionscount = conf.getActionscount();
	hasNewsOnMain = conf.getHasNewsOnMain();
	newscount = conf.getNewscount();
	hasTopGoodsListOnMain = conf.getHasTopGoodsListOnMain();
	hasDayAndWeekTopOnMain = conf.getHasDayAndWeekTopOnMain();
	hasSlider = conf.getHasSlider();
	commentsModeration = conf.getCommentsModeration();
	isactive = conf.getIsactive();
	startpagemeta = conf.getStartpagemeta();
	startpagetitle = conf.getStartpagetitle();
}

@Override
public String toString() {
	return "Configuration [id=" + id + ", name=" + name + ", hasActionsOnMain="
			+ hasActionsOnMain + ", actionscount=" + actionscount
			+ ", hasNewsOnMain=" + hasNewsOnMain + ", newscount=" + newscount
			+ ", hasTopGoodsListOnMain=" + hasTopGoodsListOnMain
			+ ", hasDayAndWeekTopOnMain=" + hasDayAndWeekTopOnMain
			+ ", hasSlider=" + hasSlider + ", commentsModeration="
			+ commentsModeration + ", isactive=" + isactive + ", slider="
			+ slider + ", isdeleted=" + isdeleted + "]";
}


}
