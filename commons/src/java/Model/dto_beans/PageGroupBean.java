package Model.dto_beans;

import Model.entity.PageGroup;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PageGroupBean{
	private Integer id;
	private String name;
	private String thumb;
	private Boolean isinfooter;
	

	private CommonsMultipartFile newthumb;
	
	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}
	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Boolean getIsinfooter() {
		return isinfooter;
	}
	public void setIsinfooter(Boolean isinfooter) {
		this.isinfooter = isinfooter;
	}
	
	@NotEmpty(message = "*Имя должно быть обязательно заполнено!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public void fillPageGroupBean(PageGroup prodbean){
		this.id = prodbean.getId();
		this.name = prodbean.getName();
		this.thumb = prodbean.getThumb();
		this.isinfooter = prodbean.getIsinfooter();
	}
}
