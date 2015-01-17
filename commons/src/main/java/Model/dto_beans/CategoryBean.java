package Model.dto_beans;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Category;
import Model.entity.PropSegment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CategoryBean {
	

	private Integer id;
	private String name;

	private Category parent_category;
	private Boolean isFinalCategory;
	private String thumb;
	private Boolean istop;
	private Integer pageid;
	
	private String pageurl;
	private String pagetitle;
	private String pageh1;
	private String pagehtml;
	private String pagemeta;
	private String alias;
	private CommonsMultipartFile newthumb;

	
	public Integer getPageid() {
		return pageid;
	}


	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Category getParent_category() {
		return parent_category;
	}


	public void setParent_category(Category parent_category) {
		this.parent_category = parent_category;
	}


	public Boolean getIsFinalCategory() {
		return isFinalCategory;
	}


	public void setIsFinalCategory(Boolean isFinalCategory) {
		this.isFinalCategory = isFinalCategory;
	}


	public String getThumb() {
		return thumb;
	}


	public void setThumb(String thumb) {
		this.thumb = thumb;
	}


	public Boolean getIstop() {
		return istop;
	}


	public void setIstop(Boolean istop) {
		this.istop = istop;
	}


	public String getPageurl() {
		return pageurl;
	}


	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}


	public String getPagetitle() {
		return pagetitle;
	}


	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}


	public String getPageh1() {
		return pageh1;
	}


	public void setPageh1(String pageh1) {
		this.pageh1 = pageh1;
	}


	public String getPagehtml() {
		return pagehtml;
	}


	public void setPagehtml(String pagehtml) {
		this.pagehtml = pagehtml;
	}


	public String getPagemeta() {
		return pagemeta;
	}


	public void setPagemeta(String pagemeta) {
		this.pagemeta = pagemeta;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public CommonsMultipartFile getNewthumb() {
		return newthumb;
	}


	public void setNewthumb(CommonsMultipartFile newthumb) {
		this.newthumb = newthumb;
	}


	public List<PropSegment> getPropsegments() {
		return propsegments;
	}


	public void setPropsegments(List<PropSegment> propsegments) {
		this.propsegments = propsegments;
	}


	private List<PropSegment> propsegments = new ArrayList<PropSegment>();


	
	
	

}
