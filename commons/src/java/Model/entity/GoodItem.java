package Model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import Model.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "goods")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class GoodItem implements Comparable, FileEntity, Item, Serializable{
	
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Double discount;
	private Double oldprice;
	private Guarantie prodguarantie;
	private Guarantie exchangeguarantie;
	private Integer mindeliverydays;
	private Integer maxdeliverydays;
	private Integer bonusscores;

	

	private Page page;
	private Set<GoodStatus> statuses = new TreeSet<GoodStatus>();
//	private Set<GoodStatus> statuses = new TreeSet<GoodStatus>();
	private Producer producer;
	
	private List<OrderRow> orderrows = new ArrayList<OrderRow>();
//	private Map<String, String> properties = new HashMap<String, String>();
//	private List<Property> props = new ArrayList<Property>();
	private Set<Value> vals = new TreeSet<Value>();
	private String thumb;
	private Category category;
	private GoodState state;
	private List<Vote> votes = new ArrayList<Vote>();
	private Boolean isTopGood = false;
	private Boolean isSeasonGood = false;

	private Set<Comment> comments = new TreeSet<Comment>();
	private Date creation_date;
	private Date updating_date;
	
	private Boolean isdeleted = false;
	private Set<Good_Guarantie> goodguaranties = new TreeSet<Good_Guarantie>();
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.good")
	public Set<Good_Guarantie> getGoodguaranties() {
		return goodguaranties;
	}
	public void setGoodguaranties(Set<Good_Guarantie> goodguaranties) {
		this.goodguaranties = goodguaranties;
	}
	@Override
	public String toString() {
		return name;
	}
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Column(name = "discount")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Column(name = "bonusscores")
	public Integer getBonusscores() {
		return bonusscores;
	}
	public void setBonusscores(Integer bonusscores) {
		this.bonusscores = bonusscores;
	}
	
	@Column(name = "oldprice")
//	@Positive
	public Double getOldprice() {
		return oldprice;
	}
	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	@Column(name = "mindeliverydays")
	public Integer getMindeliverydays() {
		return mindeliverydays;
	}
	public void setMindeliverydays(Integer mindeliverydays) {
		this.mindeliverydays = mindeliverydays;
	}
	
	@Column(name = "maxdeliverydays")
	public Integer getMaxdeliverydays() {
		return maxdeliverydays;
	}
	public void setMaxdeliverydays(Integer maxdeliverydays) {
		this.maxdeliverydays = maxdeliverydays;
	}
	

	@ManyToMany
	@JoinTable(name = "good_status",
	joinColumns = {@JoinColumn(name="good_id", nullable = false, updatable=false)},
	inverseJoinColumns = {@JoinColumn(name="status_id", nullable = false, updatable=false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<GoodStatus> getStatuses() {
		return statuses;
	}
	
	
	@ManyToOne
	@JoinColumn(name="prodguarantie_id")
	public Guarantie getProdguarantie() {
		return prodguarantie;
	}
	public void setProdguarantie(Guarantie prodguarantie) {
		this.prodguarantie = prodguarantie;
	}
	public void setStatuses(Set<GoodStatus> statuses) {
		this.statuses = statuses;
	}
	@ManyToOne
	@JoinColumn(name = "producer_id")
	@JsonBackReference
	@JsonIgnore
	@NotNull (message = "*Для товара необходимо задать производителя!")
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}


	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updating_date")
	public Date getUpdating_date() {
		return updating_date;
	}

	public void setUpdating_date(Date updating_date) {
		this.updating_date = updating_date;
	}

	@OneToMany (mappedBy = "good", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	public List<OrderRow> getOrderrows() {
		return orderrows;
	}

	public void setOrderrows(List<OrderRow> orderrows) {
		this.orderrows = orderrows;
	}

	private List<Video> videos = new ArrayList<Video>();
	

	private List<Image> images = new ArrayList<Image>();
	private transient int votes_count;
	private transient double average_rating;
	

	private transient Long round_rating;
	
//	private List<Bucket> buckets = new ArrayList<Bucket>(); 
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="good_page", joinColumns = @JoinColumn(name="good_id"),
	inverseJoinColumns = @JoinColumn(name="page_id"))
	@NotNull(message = "*Поля страницы html обязательны для заполнения!")
	@Valid
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	GoodItem(String name, String description, double price){
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	@Type(type="boolean")
	@Column(name = "isseason")
	public Boolean getIsSeasonGood() {
		return isSeasonGood;
	}

	public void setIsSeasonGood(Boolean isSeasonGood) {
		this.isSeasonGood = isSeasonGood;
	}
	
	@ManyToOne
	@JoinColumn(name="exguar_id")
	public Guarantie getExchangeguarantie() {
		return exchangeguarantie;
	}
	public void setExchangeguarantie(Guarantie exchangeguarantie) {
		this.exchangeguarantie = exchangeguarantie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(id!=null)
		result = prime * result + id;
		else{
			result = prime * result
					+ ((category == null) ? 0 : category.hashCode());
			result = prime * result
					+ ((creation_date == null) ? 0 : creation_date.hashCode());
			result = prime * result
					+ ((description == null) ? 0 : description.hashCode());
			result = prime * result
					+ ((discount == null) ? 0 : discount.hashCode());
		
			result = prime * result
					+ ((isSeasonGood == null) ? 0 : isSeasonGood.hashCode());
			result = prime * result
					+ ((isTopGood == null) ? 0 : isTopGood.hashCode());
			result = prime * result
					+ ((isdeleted == null) ? 0 : isdeleted.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((oldprice == null) ? 0 : oldprice.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			result = prime * result + ((thumb == null) ? 0 : thumb.hashCode());
		}
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
		GoodItem other = (GoodItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public GoodItem(){}
	
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
	
	@Column(name = "istop")
	@Type(type = "boolean")
	public Boolean getIsTopGood() {
		return isTopGood;
	}
	public void setIsTopGood(Boolean isTopGood) {
		this.isTopGood = isTopGood;
	}
	
	@Column(name = "name")
	@NotEmpty(message = "*Необходимо задать имя товара!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "descr")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy = "good", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	@JsonIgnore
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	@ManyToOne
	@JoinColumn(name="state_id")
	@JsonBackReference
	@JsonIgnore
	@NotNull(message = "*Необходимо задать статус товара!")
	@Valid
	public GoodState getState() {
		return state;
	}

	public void setState(GoodState state) {
		this.state = state;
	}
	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "good_votes", 
	joinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "vote_id", nullable = false, updatable = false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	
	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@Column(name = "price")
	@Positive(message = "*Цена товара должна быть позитивным значением!")
	@NotNull (message = "*Цена товара обязательна к заполнению!")
	public Double getPrice() {
		return price;
	}
	

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void addComment(Comment c){
		comments.add(c);
		c.setGood(this);
	}
	
	public void addImage(Image im){
		images.add(im);
		im.setGood(this);
	}
	
	public void removeComment(Comment l){
		comments.remove(l);
		l.setGood(null);
	}
	
	@Column(name = "thumb")
	@NotNull (message = "*Аватар товара обязателен к заполнению!")
	@NotEmpty (message = "*Аватар товара обязателен к заполнению!")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	@ManyToOne
	@JoinColumn(name = "cat_id")
	@JsonBackReference
	@JsonIgnore
	@NotNull (message = "*Товар необходимо присвоить категории!")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
//	@OneToMany (mappedBy = "good", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT)
//	@JsonManagedReference
	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "good_values", 
	joinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "value_id", updatable = false)})
	@JsonManagedReference
	@JsonIgnore
	public Set<Value> getVals() {
		return vals;
	}
	public void setVals(Set<Value> vals) {
		this.vals = vals;
	}
	public void addProperty(Property pr){
		
			Value val = new Value();
//			val.setGood(this);
			val.setProp(pr);
			vals.add(val);
		
	}
	
	public void addValue(Value val){
		vals.add(val);
//		val.setGood(this);
	}
	
	public void addVote(Vote v){
		votes.add(v);
	}
		
		
	
	@OneToMany (mappedBy = "good", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonIgnore
	public List<Image> getImages() {
		return images ;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	

	@OneToMany (mappedBy = "good", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonIgnore
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Transient
	@JsonManagedReference
	@JsonIgnore
	public Set<Comment> getRootComments(){
		Set<Comment> rootcomments = new TreeSet<Comment>(new Comparator<Comment>(){

			@Override
			public int compare(Comment o1, Comment o2) {
				// TODO Auto-generated method stub
				return o2.getDatetime().compareTo(o1.getDatetime());
			}

			
			
		});
		if(!comments.isEmpty()){
			for(Comment com: comments){
				if(com.getParent_comment()==null || com.getParent_comment().getId()<=0  ){
					rootcomments.add(com);
				}
			}
		}
		return rootcomments;
	}

	@Override
	public int compareTo(Object arg0) {
		GoodItem othergood = null;
		if(arg0 instanceof GoodItem){	
			othergood = (GoodItem)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othergood.name);
	}
	
	
	@Transient
	public int getVotes_count() {
		return getVotes().size();
	}


	@Transient
	public double getAverage_rating() {
		if(getVotes_count()==0){
			return 0;
		}
		double total_rating = 0.0;
		for(Vote vote: getVotes()){
			total_rating+=vote.getId();
			
		}
		return total_rating/getVotes_count();
//		return average_rating;
	}
	
	@Transient
	public Long getRound_rating() {
		return (Long)Math.round(getAverage_rating());
	}
	
	public void setAverage_rating(double average_rating) {
		this.average_rating = average_rating;
	}
	
	public static List<Value> getPropertyValues(GoodItem good, Property p) {
		if(good == null) return null;
		List<Value> goodvals = new ArrayList<Value>();
		System.out.println(p.getId()+" "+p.getName());
		System.out.println("ЗНАЧЕНИЯ:");
		for(Value val: good.vals){
			System.out.println(val.getValue()+"  "+val.getProp().getId());
			if(val.getProp().equals(p)){
				System.out.println("ВЫБРАННОЕ");
				goodvals.add(val);
			} 
		}
		return goodvals;
	}
	
	
	public static List<GoodPropSegmentBean> fillSegments(GoodItem good, List<PropSegment> prsgList) {
	List<GoodPropSegmentBean> segments = new ArrayList<GoodPropSegmentBean>();
	for(PropSegment prsg: prsgList){
		GoodPropSegmentBean goodprosegment = new GoodPropSegmentBean();
		goodprosegment.setId(prsg.getId());
		goodprosegment.setSegment_name(prsg.getName());
		List<Property> str = prsg.getProperties();
		
		for(Property prop: str){
			PropValueBean prbn = new PropValueBean();
		
			
			List<String> titles =new ArrayList<String>();
			titles.add(prop.getName());
			List<Value> values = getPropertyValues(good, prop);
			goodprosegment.addProperty(prop, values);
//			if (value!=null){
//			titles.add(value.getValue());
//			}
//			else{
//				titles.add(null);
//			}
//			goodprosegment.getProperties().put(prop.getId(), titles);
		}
		segments.add(goodprosegment);	
	}
	return segments;
	}

	public void deleteValue(Value val) {
		vals.remove(val);
	}

	@Override
	public void addVideo(Video im) {
		videos.add(im);
		
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
	
	public enum OrderingRules {
		
		PRICEUP(3), PRICEDOWN(4), RATING(2), NAME(1), POPULARITY(0);
		
		OrderingRules(int y){}

		
		
	}
	
	
//	@ElementCollection(fetch = FetchType.LAZY)
//	@JoinTable(name = "good_params",
//	    joinColumns = @JoinColumn(name = "good_id"))
//	@MapKeyColumn(name = "param")
//	@Column(name = "value")
//	public Map<String, String> getProperties() {
//		return properties;
//	}
//	public void setProperties(Map<String, String> properties) {
//		this.properties = properties;
//	}
	

//	public void addBucket(Bucket k){
//		buckets.add(k);
		
//	}
	
//	public void removeBucket(Bucket k){
//		buckets.remove(k);
//	}
	
//	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "bucket_good", 
//	joinColumns = {@JoinColumn(name = "good_id", nullable = false, updatable = false)}, 
//	inverseJoinColumns = {@JoinColumn(name = "bucket_id", nullable = false, updatable = false)})
	
	
//	@Fetch(value = FetchMode.SUBSELECT)
//	public List<Bucket> getBuckets() {
//		return buckets;
//	}
//	public void setBuckets(List<Bucket> buckets) {
//		this.buckets = buckets;
//	}
}

