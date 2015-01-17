package Model.dto_beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import Model.dto_beans.FileEntityBean;
import Model.entity.Action;
import Model.entity.GoodItem;
import Model.entity.Image;
import Model.entity.Video;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ActionBean implements FileEntityBean {
	
	private Integer id;
	private String name;
	private List<GoodItem> giftgoods = new ArrayList<GoodItem>();
	private List<GoodItem> actiongoods = new ArrayList<GoodItem>();
	private Boolean isactive;
	private String thumb;
	private CommonsMultipartFile newthumb;
	private Boolean istop;
	private String enddate;
	private String endtime;
	private String text;
	
	@NotNull(message = "*Текст акции должен быть заполнен!")
	@NotEmpty(message = "*Текст акции должен быть заполнен!")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull(message = "*Название акции обязательно к заполнению!")
	@NotEmpty(message = "*Название акции обязательно к заполнению!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GoodItem> getGiftgoods() {
		return giftgoods;
	}
	public void setGiftgoods(List<GoodItem> giftgoods) {
		this.giftgoods = giftgoods;
	}
	public List<GoodItem> getActiongoods() {
		return actiongoods;
	}
	public void setActiongoods(List<GoodItem> actiongoods) {
		this.actiongoods = actiongoods;
	}
	
	@NotNull (message = "*Время окончания - обязательно!")
	@NotEmpty (message = "*Время окончания - обязательно!")
	@Pattern(regexp= "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "*Время окончания в формате hh:mm!")
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	@NotNull(message = "*Аватарка акции должна быть задана!")
	@NotEmpty(message = "*Аватарка акции должна быть задана!")
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	@NotNull (message = "*Дата окончания - обязательно!")
	@NotEmpty (message = "*Дата окончания - обязательно!")
	@Pattern(regexp= "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "*Дата окончания в формате yyyy-MM-dd!")
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public void setFromAction(Action act) {
		this.endtime = new SimpleDateFormat("hh:mm").format(act.getEndtime());
		this.enddate = new SimpleDateFormat("yyyy-MM-dd").format(act.getEndtime());
		this.id=act.getId();
		this.name=act.getName();
		this.isactive=act.getIsactive();
		this.istop=act.getIstop();
		this.actiongoods=new ArrayList<GoodItem>(act.getActiongoods());
		this.giftgoods=new ArrayList<GoodItem>(act.getGiftgoods());
		this.thumb=act.getThumb();
		this.text = act.getText();
		
	}
	@Override
	
	public List<Video> getVideos() {

		return null;
	}
	
	public void setVideos(List<Video> videos) {

		
	}
	@Override
	public List<CommonsMultipartFile> getNewimages() {

		return null;
	}
	@Override
	public void setNewimages(List<CommonsMultipartFile> newimages) {

		
	}
	@Override
	public List<CommonsMultipartFile> getNewvideos() {

		return null;
	}
	@Override
	public void setNewvideos(List<CommonsMultipartFile> newvideos) {

		
	}
	@Override
	public List<Image> getImages() {

		return null;
	}

	public void setImages(List<Image> images) {

		
	}
	
	

}
