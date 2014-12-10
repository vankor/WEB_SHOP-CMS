package Model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BasicConfigurationBean {
	private Integer id;
	private String shopname;
	private String thumb;
	private CommonsMultipartFile newthumb;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
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
	public void setFromBasicConfiguration(BasicConfiguration b){
		this.id = b.getId();
		this.thumb = b.getLogo();
		this.shopname = b.getShopname();
	}
}
