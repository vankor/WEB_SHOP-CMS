package Model.dto_beans;

import javax.validation.constraints.NotNull;

import Model.entity.NewsType;
import org.hibernate.validator.constraints.NotEmpty;

public class NewsTypeBean {
	private Integer id;
	private String name;
	private String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull(message = "*»м€ типа новостей должно быть заполнено!")
	@NotEmpty(message = "*»м€ типа новостей должно быть заполнено!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message = "*«аголовок типа новостей должен быть заполнен!")
	@NotEmpty(message = "*«аголовок типа новостей должен быть заполнен!")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void constructbean(NewsType ntp){
		this.id = ntp.getId();
		this.name = ntp.getName();
		this.title = ntp.getTitle();
	}
}
