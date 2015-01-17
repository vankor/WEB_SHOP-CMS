package Model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import Model.CategoryCreateBean;
import Model.Item;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Comparable, Model.Entity, Item, Serializable{
	

	private Integer id;
	private String name;
//	private Integer parent_id;
	private Category parent_category;
	private Boolean isFinalCategory;
	private String thumb;
	private Boolean istop;
	private Page page;
	private String alias;
	private transient CommonsMultipartFile newthumb;

	private List<GoodItem> goods = new ArrayList<GoodItem>();
	private transient List<Property> props = new ArrayList<Property>();
	private List<Category> subcategs = new ArrayList<Category>();
	private List<PropSegment> propsegments = new ArrayList<PropSegment>();
	private List<News> catnews = new ArrayList<News>();
	
	private transient Set<Category> finalsubcategs = new TreeSet<Category>();
	private transient Set<GoodItem> topgoods = new TreeSet<GoodItem>();
	private transient Map<Category, Long> actionschild = new TreeMap<Category, Long>();
	
	private transient List<Producer> subcategsproducers = new ArrayList<Producer>();
	


	private List<Producer> producers = new ArrayList<Producer>();
	
	@Transient
	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}

	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}

	@Transient
	public String getAlias() {
		return null;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@Transient
	public List<Producer> getSubcategsproducers() {
		List<Producer> prods = new ArrayList<Producer>();
		
		for(Category cat: this.getFinalsubcategs()){
			System.out.println("КАТЕГОРИЯ "+cat.getName());
			prods.addAll(cat.getProducers());
		}
		return prods;
	}

	public void setSubcategsproducers(List<Producer> subcategsproducers) {
		this.subcategsproducers = subcategsproducers;
	}

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "producer_category", 
	joinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "producer_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}



	private Boolean isdeleted = false;
		
	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Column(name = "finalcat")
	@Type(type = "boolean")
	public Boolean getIsFinalCategory() {
		return isFinalCategory;
	}

	public void setIsFinalCategory(Boolean isFinalCategory) {
		this.isFinalCategory = isFinalCategory;
	}

	
	@NotNull(message = "*Должны быть заданы параметры html страницы!")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="category_page", joinColumns = @JoinColumn(name="category_id"),
	inverseJoinColumns = @JoinColumn(name="page_id"))
	@Valid
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Transient
	public Set<GoodItem> getTopgoods() {
		return topgoods;
	}

	public void setTopgoods(Set<GoodItem> topgoods) {
		this.topgoods = topgoods;
	}

	@OneToMany(mappedBy = "parent_category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 10)
	public List<Category> getSubcategs() {
		return subcategs;
	}

	public void setSubcategs(List<Category> subcategs) {
		this.subcategs = subcategs;
	}

	@Type(type="boolean")
	@Column(name = "istop")
	public Boolean getIstop() {
		return istop;
	}

	public void setIstop(Boolean istop) {
		this.istop = istop;
	}
	
//	private List<String> parameters = new ArrayList<>();
//	private Category parentcat;
	
	public Category(){}
	
	public Category(CategoryCreateBean b){
		this.name = b.getName();
//		this.parent_id = b.getParent_id();
//		this.parent_category = b.getParent_id();
		for(String str: b.getParameters()){
			Property prop = new Property();
			prop.setName(str);
			addProp(prop);
		}
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
	
	@NotNull(message = "*Категория должна иметь аватар!")
	@NotEmpty(message = "*Категория должна иметь аватар!")
	@Column(name = "thumb")
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public Category getParent_category() {
		return parent_category;
	}

	public void setParent_category(Category parent_category) {
		this.parent_category = parent_category;
	}


	public void getSubcategsWithActions() {
		
		for(Category c: subcategs){
			if(c.isFinalCategory){
				c.getGoods();
			}
			else{
				c.getSubcategsWithActions();
			}
		}

	}
	

	@Transient
	public Map<Category, Long> getActionschild() {
		return actionschild;
	}

	public void setActionschild(Map<Category, Long> actionschild) {
		this.actionschild = actionschild;
	}

	@NotNull(message = "*Имя категории не должно быть пустым!")
	@NotEmpty(message = "*Имя категории не должно быть пустым!")
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(value = FetchMode.SELECT)
	@JsonManagedReference
	@JsonIgnore
	@BatchSize(size = 10)
	public List<GoodItem> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodItem> goods) {
		this.goods = goods;
	}

	
	@Transient
	public List<Property> getProps() {
		List<Property> props = new ArrayList<Property>();
		for(PropSegment prs: getPropsegments()){
			props.addAll(prs.getProperties());
		}
		return props;
	}

	public void setProps(List<Property> props) {
		this.props = props;
	}
	

//	@ElementCollection(fetch = FetchType.LAZY)
//	@JoinTable(
///	        name="cat_params",
//	        joinColumns = @JoinColumn(name="cat_id") 
//	)
//	@Column(name="param_name", nullable=false)
//	public List<String> getParameters() {
//		return parameters;
//	}

//	public void setParameters(List<String> parameters) {
//		this.parameters = parameters;
//	}
	
	public void addGood(GoodItem g){
		goods.add(g);
		g.setCategory(this);
	}
	
	public void deleteGood(GoodItem g){
		goods.remove(g);
		g.setCategory(null);
	}

	
/*	@Column(name = "parent_id")
	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}*/
	
	
	
	public static Boolean isChildOf(Category child, Category checkcat){
		if(checkcat.equals(child.parent_category))return true;
		if(child.parent_category==null)return false;
		return isChildOf(child.parent_category, checkcat);
		
		
	}
	
	public void setItems(List<Item> goods) {}
	
	public void addProp(Property prop){
		props.add(prop);
		prop.setCat(this);
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		Category othercat = null;
		if(o instanceof Category){	
			othercat = (Category)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.name.compareTo(othercat.name);
	}

	@Override
	public String toString() {
		return  name ;
	}
	
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cat_segments", 
	joinColumns = {@JoinColumn(name = "cat_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "propsegment_id", nullable = false, updatable = false)})
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonIgnore
	@BatchSize(size = 10)
//	@Valid
	public List<PropSegment> getPropsegments() {
		return propsegments;
	}

	public void setPropsegments(List<PropSegment> propsegments) {
		this.propsegments = propsegments;
	}
	
	public void addPropSegment(PropSegment pr){
		this.propsegments.add(pr);
	}
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(value = FetchMode.SELECT)
	@JsonManagedReference
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JsonIgnore
	@BatchSize(size = 10)
	public List<News> getCatnews() {
		return catnews;
	}

	public void setCatnews(List<News> catnews) {
		this.catnews = catnews;
	}
	
	
	@Transient
	public Set<Category> getFinalsubcategs() {
		Set<Category> finals = new TreeSet<Category>();
		for(Category c: subcategs){
			if(c.isFinalCategory){
				finals.add(c);
			}
		}
		return finals;
		
	}

	public void setFinalsubcategs(Set<Category> finalsubcategs) {
		this.finalsubcategs = finalsubcategs;
	}

	@Override
	@Transient
	public Iterator createiterator(List<Item> list, List<Item> list2) {
		System.out.println("criter for subcats");
		list.addAll(list2);
		return list.iterator();
	}

	@Override
	@Transient
	public List<Item> dogetNodeList() {
		List<Item> l = new ArrayList<Item>();
		for(News n: catnews)
		{
			l.add(n);
		}
		return l;
	}

	@Override
	@Transient
	public List<Item> dogetListOfLists() {
		List<Item> l = new ArrayList<Item>();
		for(Category n: subcategs)
		{
			l.add(n);
		}
		return l;
		
	}
	
	public enum OrderingRules {
		
		NAME(1), GOODSCOUNT(2), PROPSCOUNT(3), NEWSCOUNT(4);
		
		OrderingRules(int y){}
	}


		
}
