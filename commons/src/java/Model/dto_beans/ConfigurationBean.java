package Model.dto_beans;

import javax.validation.Valid;

import Model.entity.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ConfigurationBean {

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
	private SliderBean slider;
	private String startpagetitle;
	private String startpagemeta;
	
	
	
	public String getStartpagemeta() {
		return startpagemeta;
	}

	public void setStartpagemeta(String startpagemeta) {
		this.startpagemeta = startpagemeta;
	}

	public ConfigurationBean(Configuration conf) {
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
		slider = new SliderBean(conf.getSlider());
		startpagemeta = conf.getStartpagemeta();
		startpagetitle = conf.getStartpagetitle();
	}
	
	
	@NotEmpty(message = "*Title главной страницы - обязателен для заполнения!")
	public String getStartpagetitle() {
		return startpagetitle;
	}

	public void setStartpagetitle(String startpagetitle) {
		this.startpagetitle = startpagetitle;
	}

	public ConfigurationBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	@NotEmpty(message = "*Имя шаблона - обязателен для заполнения!")
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Boolean getHasActionsOnMain() {
		return hasActionsOnMain;
	}



	public void setHasActionsOnMain(Boolean hasActionsOnMain) {
		this.hasActionsOnMain = hasActionsOnMain;
	}



	public Integer getActionscount() {
		return actionscount;
	}



	public void setActionscount(Integer actionscount) {
		this.actionscount = actionscount;
	}



	public Boolean getHasNewsOnMain() {
		return hasNewsOnMain;
	}



	public void setHasNewsOnMain(Boolean hasNewsOnMain) {
		this.hasNewsOnMain = hasNewsOnMain;
	}



	public Integer getNewscount() {
		return newscount;
	}



	public void setNewscount(Integer newscount) {
		this.newscount = newscount;
	}



	public Boolean getHasTopGoodsListOnMain() {
		return hasTopGoodsListOnMain;
	}



	public void setHasTopGoodsListOnMain(Boolean hasTopGoodsListOnMain) {
		this.hasTopGoodsListOnMain = hasTopGoodsListOnMain;
	}



	public Boolean getHasDayAndWeekTopOnMain() {
		return hasDayAndWeekTopOnMain;
	}



	public void setHasDayAndWeekTopOnMain(Boolean hasDayAndWeekTopOnMain) {
		this.hasDayAndWeekTopOnMain = hasDayAndWeekTopOnMain;
	}



	public Boolean getHasSlider() {
		return hasSlider;
	}



	public void setHasSlider(Boolean hasSlider) {
		this.hasSlider = hasSlider;
	}



	public Boolean getCommentsModeration() {
		return commentsModeration;
	}



	public void setCommentsModeration(Boolean commentsModeration) {
		this.commentsModeration = commentsModeration;
	}



	public Boolean getIsactive() {
		return isactive;
	}



	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}


	@Valid
	public SliderBean getSlider() {
		return slider;
	}



	public void setSlider(SliderBean slider) {
		this.slider = slider;
	}



	

}
