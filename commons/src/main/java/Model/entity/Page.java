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

import Model.dto_beans.PageBean;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "pages")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class Page  implements Comparable, Model.entity.Entity, Serializable{

private Integer id;
private String name;
private String url;
private String title;
private String html;
@Override
public String toString() {
	return fullurl;
}

private String meta;
private String h1;


private Boolean isdeleted = false;
private Page parent_page;
private String fullurl;
private Boolean isinfopage;
private PageGroup pagegroup;

private Boolean isinfooter = false;
private Boolean isinheader = false;
private Boolean isinmainmenu = false;


@Type (type = "boolean")
@Column(name = "isinheader")
public Boolean getIsinheader() {
	return isinheader;
}

public void setIsinheader(Boolean isinheader) {
	this.isinheader = isinheader;
}

@Type (type = "boolean")
@Column(name = "isinmainmenu")
public Boolean getIsinmainmenu() {
	return isinmainmenu;
}

public void setIsinmainmenu(Boolean isinmainmenu) {
	this.isinmainmenu = isinmainmenu;
}




@Column(name = "h1")
public String getH1() {
	return h1;
}

@Type (type = "boolean")
@Column(name = "isinfooter")
public Boolean getIsinfooter() {
	return isinfooter;
}


public void setIsinfooter(Boolean isinfooter) {
	this.isinfooter = isinfooter;
}

public void setH1(String h1) {
	this.h1 = h1;
}



@Column(name = "fullurl", unique=true)
public String getFullurl() {

	if(parent_page!=null)return parent_page.getFullurl()+"/"+getUrl();
	else return (url!=null)?url.trim():url;
}

public void setFullurl(String fullurl) {
	this.fullurl = fullurl;
}



@ManyToOne
@JoinColumn(name="parent_id")
public Page getParent_page() {
	return parent_page;
}

public void setParent_page(Page parent_page) {
	this.parent_page = parent_page;
}

@Override
@Id
@GenericGenerator(name="increment", strategy = "increment")
@GeneratedValue (strategy = GenerationType.AUTO)
@Column(name = "id")
public Integer getId() {

	return id;
}


@Type(type="boolean")
@Column(name = "isinfopage")
public Boolean getIsinfopage() {
	return isinfopage;
}

public void setIsinfopage(Boolean isinfopage) {
	this.isinfopage = isinfopage;
}

@ManyToOne
@JoinColumn(name="pagegroup_id")
public PageGroup getPagegroup() {
	return pagegroup;
}

public void setPagegroup(PageGroup pagegroup) {
	this.pagegroup = pagegroup;
}

@Override
public void setId(Integer id) {
	this.id = id;
	
}

@Override
@Type(type="boolean")
@Column(name = "isdeleted")
public Boolean getIsdeleted() {

	return isdeleted;
}

@Override
public void setIsdeleted(Boolean b) {
	this.isdeleted = b;
	
}

@Override
public int compareTo(Object arg0) {
	Page othergood = null;
	if(arg0 instanceof Page){	
		othergood = (Page)arg0;
	}
	else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
	return this.name.compareTo(othergood.name);
}

@Column(name = "name")
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@NotEmpty (message = "*Необходимо задать url страницы!")
@Column(name = "url")
public String getUrl() {
	if(url!=null)
	return url.trim();
	else return url;
		
	
}

public void setUrl(String url) {
	this.url = (url!=null)?url.trim():url;
}

@NotEmpty (message = "*Должен быть задан заголовок html страницы!")
@Column(name = "title")
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

@Column(name = "html",columnDefinition="TEXT", length = 100000)
public String getHtml() {
	return html;
}

public void setHtml(String html) {
	this.html = html;
}

@Column(name = "meta",columnDefinition="TEXT", length = 100000)
public String getMeta() {
	return meta;
}

public void setMeta(String meta) {
	this.meta = meta;
}

public void fillfrombean (PageBean p){
	id = p.getId();
	name = p.getName();
	url = p.getUrl();
	title = p.getTitle();
	html = p.getHtml();
	meta = p.getMeta();
	h1 = p.getH1();

	isinfopage = p.getIsinfopage();
	
	
	isinfooter = p.getIsinfooter();
	isinheader = p.getIsinheader();
	isinmainmenu = p.getIsinmainmenu();
}

}
