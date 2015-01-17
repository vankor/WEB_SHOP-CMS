package Model.dto_beans;

import javax.validation.constraints.NotNull;

import Model.entity.Country;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CountryBean {
	private Integer id;
	private String name;
	private String phonecode;
	private String thumb;
	private CommonsMultipartFile newthumb;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull(message = "*Имя страны должно быть заполнено!")
	@NotEmpty(message = "*Имя страны должно быть заполнено!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message = "*Код страны должен быть заполнен!")
	@NotEmpty(message = "*Код страны должен быть заполнен!")
	public String getPhonecode() {
		return phonecode;
	}
	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
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
	
	public void constructFromEntity(Country cntr){
		this.id = cntr.getId();
		this.name = cntr.getName();
		this.phonecode = cntr.getPhonecode();
		this.thumb = cntr.getFlagicon();
		
	}
}
