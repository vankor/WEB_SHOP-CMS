package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.RegEx;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class NewsBean implements FileEntityBean{

	private Integer id;
	private String title;
	private String thumb;
	private CommonsMultipartFile newthumb;
	private Boolean istop;
	private String promotext;

	
	private TextPart textpart;
	
	private List<Video> videos = new ArrayList<Video>();
	private List<Image> images = new ArrayList<Image>();
	
	private List<CommonsMultipartFile> newimages = new ArrayList<CommonsMultipartFile>();
	private List<CommonsMultipartFile> newvideos= new ArrayList<CommonsMultipartFile>();
	private String creation_date;
	private String creation_time;
	
	@NotNull (message = "*Для новости обязателен текст")
	@Valid
	public TextPart getTextpart() {
		return textpart;
	}
	public void setTextpart(TextPart textpart) {
		this.textpart = textpart;
	}
	
	@NotNull (message = "*Время создания - обязательно!")
	@NotEmpty (message = "*Время создания - обязательно!")
	@Pattern(regexp= "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "*Время создания в формате hh:mm!")
	public String getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}
	private Category category;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotNull (message = "*Для новости обязателен заголовок")
	@NotEmpty (message = "*Для новости обязателен заголовок")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Valid
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	@Valid
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	@NotNull (message = "*Дата создания - обязательно!")
	@NotEmpty (message = "*Дата создания - обязательно!")
	@Pattern(regexp= "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "*Дата создания в формате yyyy-MM-dd!")
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	@CategoryFilled
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<CommonsMultipartFile> getNewimages() {
		return newimages;
	}
	public void setNewimages(List<CommonsMultipartFile> newimages) {
		this.newimages = newimages;
	}
	
	public void addImage(Image image) {
		this.images.add(image);
	}
	
	@NotNull (message = "*Аватарка новости - обязательна!")
	@NotEmpty (message = "*Аватарка новости - обязательна!")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}
	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}
	

	public Boolean getIstop() {
		return istop;
	}
	public void setIstop(Boolean istop) {
		this.istop = istop;
	}
	
	@NotNull (message = "*Короткий текст новости - обязателен!")
	@NotEmpty (message = "*Короткий текст новости - обязателен!")
	public String getPromotext() {
		return promotext;
	}
	public void setPromotext(String promotext) {
		this.promotext = promotext;
	}
	
	public void setFromNews(News act) {
		this.creation_time = new SimpleDateFormat("hh:mm").format(act.getCreation());
		this.creation_date = new SimpleDateFormat("yyyy-MM-dd").format(act.getCreation());
		this.id=act.getId();
		this.title=act.getTitle();
		this.category=act.getCategory();
		this.images=act.getImages();
		this.videos=act.getVideos();
		this.textpart=act.getTextpart();
		this.thumb = act.getThumb();
		this.istop = act.getIstop();
		this.promotext = act.getPromotext();
	}
	
	public List<CommonsMultipartFile> getNewvideos() {
		return newvideos;
	}
	public void setNewvideos(List<CommonsMultipartFile> newvideos) {
		this.newvideos = newvideos;
	}
	
	
	
}
