package Model;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class PageBean {
	private Integer id;
	private String name;
	private String url;
	private String title;
	private String html;
	private String meta;
	private String h1;

	private Page parent_page;
	private Boolean isinfopage;
	private PageGroup pagegroup;
	
	private Boolean isinfooter;
	
	private Boolean isinheader;
	private Boolean isinmainmenu;
	
	public PageBean(){}
	public PageBean(Page p){
		id = p.getId();
		name = p.getName();
		url = p.getUrl();
		title = p.getTitle();
		html = p.getHtml();
		meta = p.getMeta();
		h1 = p.getH1();

		parent_page = p.getParent_page();
		isinfopage = p.getIsinfopage();
		pagegroup = p.getPagegroup();
		
		isinfooter = p.getIsinfooter();
		
		isinheader = p.getIsinheader();
		isinmainmenu = p.getIsinmainmenu();
	}
	
	
	public Boolean getIsinfooter() {
		return isinfooter;
	}
	public void setIsinfooter(Boolean isinfooter) {
		this.isinfooter = isinfooter;
	}
	public Boolean getIsinheader() {
		return isinheader;
	}
	public void setIsinheader(Boolean isinheader) {
		this.isinheader = isinheader;
	}
	public Boolean getIsinmainmenu() {
		return isinmainmenu;
	}
	public void setIsinmainmenu(Boolean isinmainmenu) {
		this.isinmainmenu = isinmainmenu;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "*Необходимо задать имя-анкор страницы!")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty(message = "*Необходимо задать url страницы!")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@NotEmpty(message = "*Необходимо задать title страницы!")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
	
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getH1() {
		return h1;
	}
	public void setH1(String h1) {
		this.h1 = h1;
	}
	
	public Page getParent_page() {
		return parent_page;
	}
	public void setParent_page(Page parent_page) {
		this.parent_page = parent_page;
	}
	
	
	public Boolean getIsinfopage() {
		return isinfopage;
	}
	public void setIsinfopage(Boolean isinfopage) {
		this.isinfopage = isinfopage;
	}
	
	public PageGroup getPagegroup() {
		return pagegroup;
	}
	public void setPagegroup(PageGroup pagegroup) {
		this.pagegroup = pagegroup;
	}
	
	
}
