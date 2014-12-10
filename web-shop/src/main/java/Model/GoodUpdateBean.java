package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GoodUpdateBean implements FileEntityBean{
	
	private Integer id;
	private String name;
	private String description;
	private double price;
	
	private Double discount;
	private Double oldprice;
	private Guarantie prodguarantie;
	private Guarantie exchangeguarantie;
	private Integer mindeliverydays;
	private Integer maxdeliverydays;
	private Integer bonusscores;
	
	
	//	private List<Comment> comments = new ArrayList<Comment>();
//	private Map<String, String> properties = new HashMap<String, String>();
//	private List<Property> props = new ArrayList<Property>();
//	private List<Value> vals = new ArrayList<Value>();
	private List<GoodPropSegmentBean> segments = new ArrayList<GoodPropSegmentBean>();
	private String thumb;

	private CommonsMultipartFile newthumb;
	private Integer category_id;
	private String state_name;
	private Boolean isTopGood = false;
	private Boolean isSeasonGood = false;
	private Producer producer;
	
	
	private Date creation_date;
	private Date updating_date;
	private Page page;

	
	private List<Image> images = new ArrayList<Image>();
	private List<CommonsMultipartFile> newimages = new ArrayList<CommonsMultipartFile>();
	
	
	private List<Video> videos = new ArrayList<Video>();
	private List<CommonsMultipartFile> newvideos = new ArrayList<CommonsMultipartFile>();
	private List<Good_Guarantie> goodguaranties = new ArrayList<Good_Guarantie>();
	private List<GoodStatus> statuses = new ArrayList<GoodStatus>();
	
	
	
	public List<Good_Guarantie> getGoodguaranties() {
		return goodguaranties;
	}
	public void setGoodguaranties(List<Good_Guarantie> goodguaranties) {
		this.goodguaranties = goodguaranties;
	}
	public List<GoodStatus> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<GoodStatus> statuses) {
		this.statuses = statuses;
	}
	public GoodUpdateBean()	{}
	public GoodUpdateBean(GoodItem g)	{
		if(g!=null){
		if(g.getCategory()!=null)
		this.category_id = g.getCategory().getId();
		this.name = g.getName();
		this.id = g.getId();
		this.description = g.getDescription();
		this.price = g.getPrice();
//		this.vals = g.getVals();
		this.thumb = g.getThumb();
		this.state_name = g.getState().getText();
		this.isTopGood = g.getIsTopGood();
		this.isSeasonGood = g.getIsSeasonGood();
		this.images = g.getImages();
		this.videos = new ArrayList<Video>(g.getVideos());
		this.page = g.getPage();
		this.producer = g.getProducer();
		this.statuses = new ArrayList<GoodStatus>(g.getStatuses());
		this.goodguaranties = new ArrayList<Good_Guarantie>(g.getGoodguaranties());
		this.bonusscores = g.getBonusscores();
		this.maxdeliverydays = g.getMaxdeliverydays();
		this.mindeliverydays = g.getMindeliverydays();
		this.exchangeguarantie = g.getExchangeguarantie();
		this.prodguarantie = g.getProdguarantie();
		this.oldprice = g.getOldprice();
		this.discount = g.getDiscount();
		}
	}
	
	
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getUpdating_date() {
		return updating_date;
	}
	public void setUpdating_date(Date updating_date) {
		this.updating_date = updating_date;
	}
	
	@NotNull(message = "*Поля страницы html обязательны для заполнения!")
	@Valid
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	public List<GoodPropSegmentBean> getSegments() {
		return segments;
	}
	public void setSegments(List<GoodPropSegmentBean> segments) {
		this.segments = segments;
	}
	public void updateGood(GoodItem g)
	{
		g.setName(this.name);
		g.setDescription(this.description);
		g.setPrice(this.price);
	    g.setThumb(this.thumb);
		g.setIsTopGood(this.isTopGood);
		g.setIsSeasonGood(this.isSeasonGood);
		
		
	}
	
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public Boolean getIsSeasonGood() {
		return isSeasonGood;
	}
	public void setIsSeasonGood(Boolean isSeasonGood) {
		this.isSeasonGood = isSeasonGood;
	}
	public List<CommonsMultipartFile> getNewimages() {
		return newimages;
	}
	public void setNewimages(List<CommonsMultipartFile> newimages) {
		this.newimages = newimages;
	}
	
	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}
	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}
	
	public List<CommonsMultipartFile> getNewvideos() {
		return newvideos;
	}
	public void setNewvideos(List<CommonsMultipartFile> newvideos) {
		this.newvideos = newvideos;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "*Необходимо задать имя товара!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Positive (message = "*Цена товара должна быть позитивным значением!")
	@NotNull (message = "*Цена товара обязательна к заполнению!")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	public List<Value> getVals() {
//		return vals;
//	}
//	public void setVals(List<Value> vals) {
//		this.vals = vals;
//	}
	@NotNull (message = "*Аватар товара обязателен к заполнению!")
	@NotEmpty (message = "*Аватар товара обязателен к заполнению!")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@NotNull (message = "*Категория товара обязателена к заполнению!")
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	
	public Boolean getIsTopGood() {
		return isTopGood;
	}
	public void setIsTopGood(Boolean isTopGood) {
		this.isTopGood = isTopGood;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	@NotNull(message = "*Необходимо задать статус товара!")
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
	
	
	
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getOldprice() {
		return oldprice;
	}
	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}
	public Guarantie getProdguarantie() {
		return prodguarantie;
	}
	public void setProdguarantie(Guarantie prodguarantie) {
		this.prodguarantie = prodguarantie;
	}
	public Guarantie getExchangeguarantie() {
		return exchangeguarantie;
	}
	public void setExchangeguarantie(Guarantie exchangeguarantie) {
		this.exchangeguarantie = exchangeguarantie;
	}
	public Integer getMindeliverydays() {
		return mindeliverydays;
	}
	public void setMindeliverydays(Integer mindeliverydays) {
		this.mindeliverydays = mindeliverydays;
	}
	public Integer getMaxdeliverydays() {
		return maxdeliverydays;
	}
	public void setMaxdeliverydays(Integer maxdeliverydays) {
		this.maxdeliverydays = maxdeliverydays;
	}
	public Integer getBonusscores() {
		return bonusscores;
	}
	public void setBonusscores(Integer bonusscores) {
		this.bonusscores = bonusscores;
	}
	
	

}
