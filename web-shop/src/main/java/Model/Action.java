package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "actions")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Action implements  FileEntity, Model.Entity, Comparable, Serializable{
	
	private Integer id;
	private String name;
	private Set<GoodItem> giftgoods = new TreeSet<GoodItem>();
	private Set<GoodItem> actiongoods = new TreeSet<GoodItem>();
	private Date endtime;
	private Boolean isactive;
	private String thumb;
	private String text;
	private Page page;

	private Boolean istop;
	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="action_page", joinColumns = @JoinColumn(name="action_id"),
	inverseJoinColumns = @JoinColumn(name="page_id"))
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	@ManyToMany 
	@JoinTable(name = "gift_goods", 
	joinColumns = {@JoinColumn(name = "action_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<GoodItem> getGiftgoods() {
		return giftgoods;
	}
	public void setGiftgoods(Set<GoodItem> giftgoods) {
		this.giftgoods = giftgoods;
	}
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
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public  void setFromActionBean(ActionBean action){
		name = action.getName();
		thumb = action.getThumb();
		isactive = action.getIsactive();
		istop = action.getIstop();
		text = action.getText();
		try {
			Date enddate = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH).parse(action.getEnddate()+" "+action.getEndtime());
			System.out.println(enddate);
			this.setEndtime(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
		
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(name = "action_goods", 
	joinColumns = {@JoinColumn(name = "action_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public Set<GoodItem> getActiongoods() {
		return actiongoods;
	}
	public void setActiongoods(Set<GoodItem> actiongoods) {
		this.actiongoods = actiongoods;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endtime")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Type(type="boolean")
	@Column(name = "istop")
	public Boolean getIstop() {
		return istop;
	}
	public void setIstop(Boolean istop) {
		this.istop = istop;
	}
	
	@Type(type = "boolean")
	@Column(name = "isactive")
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	@Column(name = "thumb")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public void addActionGood(GoodItem good) {
		this.actiongoods.add(good);
	}
	
	public void addGiftGood(GoodItem good) {
		this.giftgoods.add(good);
	}
	
	@Override
	public int compareTo(Object arg0) {
		Action otherord = null;
		if(arg0 instanceof Action){	
			otherord = (Action)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(otherord.name);
	}
	
public enum OrderingRules {
		
		NAME(3), ENDTIME(4);
		
		OrderingRules(int y){}

		
		
	}

@Transient
public void addImage(Image im) {
	// TODO Auto-generated method stub
	
}
@Transient
public void removeImage(Image im) {
	// TODO Auto-generated method stub
	
}
@Transient
public List<Image> getImages() {
	// TODO Auto-generated method stub
	return null;
}
@Transient
public void setImages(List<Image> images) {
	// TODO Auto-generated method stub
	
}
@Transient
public void addVideo(Video im) {
	// TODO Auto-generated method stub
	
}
@Transient
public List<Video> getVideos() {
	// TODO Auto-generated method stub
	return null;
}
@Transient
public void setVideos(List<Video> images) {
	// TODO Auto-generated method stub
	
}
@Override
public void removeVideo(Video vid) {
	// TODO Auto-generated method stub
	
}
	
}
