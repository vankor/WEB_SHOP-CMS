package Model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SliderImageBean extends Image{

	private Integer id;
	private CommonsMultipartFile newimage;
	private String href;
	private String text;
	private String url;
	private String alt;
	private Integer priority;
	
	@NotEmpty(message = "*Значение url должно быть определено!")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	@NotNull(message = "*Значение приоритета должно быть определено!")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public SliderImageBean(){}
	public SliderImageBean(SliderImage sim){
		super(sim);
		id =sim.getId();
		href = sim.getHref();
		text = sim.getText();	
		url = sim.getUrl();
		alt = sim.getAlt();
		priority = sim.getPriority();
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public CommonsMultipartFile getNewimage() {
		return newimage;
	}
	public void setNewimage(CommonsMultipartFile newimage) {
		this.newimage = newimage;
	}
	
	@NotEmpty(message = "*Значение href ссылки для фото должно быть определено!")
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
