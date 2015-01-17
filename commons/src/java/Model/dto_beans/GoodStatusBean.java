package Model.dto_beans;

import javax.validation.constraints.NotNull;

import Model.entity.GoodStatus;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GoodStatusBean {
	private Integer id;
	private String text;
	private CommonsMultipartFile newthumb;

	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}

	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}

	public GoodStatusBean(GoodStatus gst){
		this.id = gst.getId();
		this.text = gst.getText();
		this.thumb = gst.getThumb();
	}
	
	public GoodStatusBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull(message = "*Текст статуса обязателен для заполнения!")
	@NotEmpty(message = "*Текст статуса обязателен для заполнения!")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotNull(message = "*Необходимо задать иконку для статуса!")
	@NotEmpty(message = "*Необходимо задать иконку для статуса!")
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	private String thumb;
}
