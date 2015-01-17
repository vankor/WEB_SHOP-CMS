package Model.dto_beans;

import Model.entity.Category;
import Model.entity.Producer;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ProducerBean {
	private CommonsMultipartFile newthumb;
	private Integer id;
	private String name;
	private String thumb;	
	private Category category;

	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "*Имя должно быть обязательно заполнено!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty(message = "*Необходимо загрузить логотип!")
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
	

	public void fillProducerBean(Producer prodbean){
		this.id = prodbean.getId();
		this.name = prodbean.getName();
		this.thumb = prodbean.getThumb();
	}
}
