package Model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class InstallBean {
	private String dbname;
	private Integer dbprovider;
	private Integer dbport;
	private String dbhost;
	private String username;
	private String password;
	private String admin_login;
	private String admin_password;
	
	private Boolean use_second_level_cache;
	private Boolean use_query_cache;
	
	private String shopname;

	
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public Integer getDbport() {
		return dbport;
	}
	public void setDbport(Integer dbport) {
		this.dbport = dbport;
	}

	
	public String getDbhost() {
		return dbhost;
	}
	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}
	public String getAdmin_login() {
		return admin_login;
	}
	public void setAdmin_login(String admin_login) {
		this.admin_login = admin_login;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	
	public Boolean getUse_second_level_cache() {
		return use_second_level_cache;
	}
	public void setUse_second_level_cache(Boolean use_second_level_cache) {
		this.use_second_level_cache = use_second_level_cache;
	}
	public Boolean getUse_query_cache() {
		return use_query_cache;
	}
	public void setUse_query_cache(Boolean use_query_cache) {
		this.use_query_cache = use_query_cache;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public Integer getDbprovider() {
		return dbprovider;
	}
	public void setDbprovider(Integer dbprovider) {
		this.dbprovider = dbprovider;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
