package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Category;
import Model.entity.Page;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GoodItemCreationBean {
	private Integer category_id;
	private String state_name;
	
	private String name;
	private String description;
	private double price;
//	private Map<Integer, List<String>> properties = new HashMap<Integer, List<String>>();
	private List<GoodPropSegmentBean> segments = new ArrayList<GoodPropSegmentBean>();
	
	private String thumbname;
	private CommonsMultipartFile thumb;
	private Category category;
	private List<CommonsMultipartFile> images = new ArrayList<CommonsMultipartFile>();
	private String folderurl;

	private Page page;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getFolderurl() {
		return folderurl;
	}
	public void setFolderurl(String folderurl) {
		this.folderurl = folderurl;
	}
	public List<CommonsMultipartFile> getImages() {
		return images;
	}
	public void setImages(List<CommonsMultipartFile> images) {
		this.images = images;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	public Map<Integer, List<String>> getProperties() {
//		return properties;
//	}
//	public void setProperties(Map<Integer, List<String>> properties) {
//		this.properties = properties;
//	}

	public String getThumbname() {
		return thumbname;
	}
	public List<GoodPropSegmentBean> getSegments() {
		return segments;
	}
	public void setSegments(List<GoodPropSegmentBean> segments) {
		this.segments = segments;
	}
	public void setThumbname(String thumbname) {
		this.thumbname = thumbname;
	}
	public CommonsMultipartFile getThumb() {
		return thumb;
	}
	public void setThumb(CommonsMultipartFile thumb) {
		this.thumb = thumb;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
}
