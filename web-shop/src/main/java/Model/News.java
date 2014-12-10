package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
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
@Table (name = "news")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class News implements Comparable, FileEntity, Item, Serializable{

	private Integer id;
	private String title;


	private String thumb;
	private Boolean istop;
	private String promotext;
	private TextPart textpart;
	private List<Video> videos = new ArrayList<Video>();
	private List<Image> images = new ArrayList<Image>();
	private Date creation;
	private Date updating;
	private Category category;
	private NewsType type;
	private Page page;

	private Boolean isdeleted = false;
	
	
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@ManyToOne
	@JoinColumn(name="newstype")
	public NewsType getType() {
		return type;
	}
	public void setType(NewsType type) {
		this.type = type;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="news_page", joinColumns = @JoinColumn(name="news_id"),
	inverseJoinColumns = @JoinColumn(name="page_id"))
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updating_date")
	public Date getUpdating() {
		return updating;
	}
	public void setUpdating(Date updating) {
		this.updating = updating;
	}
	
	@ManyToOne
	@JoinColumn(name="textpart_id")
	public TextPart getTextpart() {
		return textpart;
	}
	public void setTextpart(TextPart textpart) {
		this.textpart = textpart;
	
	}
	
	@Type(type="boolean")
	@Column(name = "istop")
	public Boolean getIstop() {
		return istop;
	}
	public void setIstop(Boolean istop) {
		this.istop = istop;
	}
	
	@Column(name = "promotext", length = 1000)
	public String getPromotext() {
		return promotext;
	}
	public void setPromotext(String promotext) {
		this.promotext = promotext;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "news_videos", joinColumns = {@JoinColumn(name = "news_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "video_id", nullable = false, updatable = false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	@Column(name = "thumb")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "news_images", joinColumns = {@JoinColumn(name = "news_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Image> getImages() {
		return images;
	}


	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonBackReference
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void addImage(Image image) {
		this.images.add(image);
	}
	
	public void setFromNewsBean(NewsBean n){
		
		title = n.getTitle();
		thumb = n.getThumb();
		istop = n.getIstop();
		promotext = n.getPromotext();
//		isactive = action.getIsactive();
		try {
			Date crdate = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH).parse(n.getCreation_date()+" "+n.getCreation_time());
			System.out.println(crdate);
			this.setCreation(crdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void addVideo(Video im) {
		videos.add(im);
		
	}
	@Override
	public int compareTo(Object arg0) {
		News othergood = null;
		if(arg0 instanceof News){	
			othergood = (News)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.title.compareTo(othergood.title);
	}
	@Override
	public void removeImage(Image im) {
		images.remove(im);
		
	}
	
	@Override
	public void removeVideo(Video vid) {
		videos.remove(vid);
		
	}
	@Override
	@Transient
	public Iterator createiterator(List<Item> list, List<Item> list2) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Transient
	public List<Item> dogetNodeList() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Transient
	public List<Item> dogetListOfLists() {
		throw new UnsupportedOperationException();
	}
	
	public enum OrderingRules{
		PROMOTEXT(1), CREATION(2), UPDATING(3);
		
		OrderingRules(int y){}
	}
	
}
