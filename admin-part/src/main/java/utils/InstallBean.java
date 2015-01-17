package utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import utils.Installator;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class InstallBean{
	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Value("${db.url}")
	private String dburl;

	private String dbname;
	private Integer dbprovider;
	private Integer dbport;
	private String dbhost;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Value("${admin.login}")
	private String admin_login;

	@Value("${admin.password}")
	private String admin_password;

	@Value("${hibernate.cache.use_second_level_cache}")
	private String use_second_level_cache;

	@Value("${hibernate.cache.use_query_cache}")
	private String use_query_cache;

	@Value("${admin.shopname}")
	private String shopname;


	@PostConstruct
	public void initIt() throws Exception {
		try {
			Installator installer = new Installator(this);
			installer.install();
		}
		catch(Throwable e){

		}
	}


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


	public String getUse_second_level_cache() {
		return use_second_level_cache;
	}
	public void setUse_second_level_cache(String use_second_level_cache) {
		this.use_second_level_cache = use_second_level_cache;
	}

	public LocalSessionFactoryBean getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}


	public String getUse_query_cache() {
		return use_query_cache;
	}
	public void setUse_query_cache(String use_query_cache) {
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
