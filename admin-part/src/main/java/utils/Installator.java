package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import Model.entity.BasicConfiguration;

import Model.entity.Role;
import Model.entity.User;

public class Installator {

	private String dbtype;
	private InstallBean config;
	private String driverclass;
	private String urlprefix;
	private String separator;
	private String dialect;
	
	private String dbhosturl;
	private String dburl;
	private String use_query_cache;
	private String use_second_level_cache;
		
	private Integer dbport;
	private String dbhost;
	private String dbname;
	public static final String filename = "params.properties";
	public static final String configname = "database.properties";
	public static final String importsqlfile = "import.sql";
	
	public Installator(InstallBean config){
		this.config = config;
		loadDatabaseParameters();
	}
	
	public void install(){
		createUserDatabase();
		loadInitDatabaseItems();
		saveDatabaseConfig();
	}
	
	public void loadDatabaseParameters(){
		InputStream input = null;

		try{

				Properties prop = new Properties();

				input = getClass().getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					System.out.println("Sorry, unable to find " + filename);

				}
				prop.load(input);

			if(config.getDburl() == null || config.getDburl() == ""){
				dbtype = (config.getDbprovider().equals(2))?"oracle":"mysql";
				dbport = config.getDbport();
				dbhost = config.getDbhost();
				dbname = config.getDbname();

				driverclass = prop.getProperty(dbtype + ".driverClassName");
				urlprefix = prop.getProperty(dbtype + ".urlprefix");
				separator = prop.getProperty(dbtype + ".dbnameseparator");
				dialect = prop.getProperty(dbtype + ".hibernate.dialect");

				dbhosturl = urlprefix + dbhost + ":" + dbport;
				dburl = urlprefix + dbhost + ":" + dbport + separator + dbname;
			}
			else{
				dburl = config.getDburl();
				dbtype = dburl.split(":")[1];

				driverclass = prop.getProperty(dbtype + ".driverClassName");
				urlprefix = prop.getProperty(dbtype + ".urlprefix");
				separator = prop.getProperty(dbtype + ".dbnameseparator");
				dialect = prop.getProperty(dbtype + ".hibernate.dialect");

				String [] urlParts = dburl.split(separator);
				StringBuilder urlBuilder = new StringBuilder();
				Integer i = 0;
				for(String part:urlParts){
					if(i < urlParts.length -1) {
						urlBuilder.append(part + separator);
					}
					else{
						dbname = part;
					}
					i++;
				}
				dbhosturl = urlBuilder.toString();
			}
		use_query_cache = (config.getUse_query_cache().equals("true"))?"true":"false";
		use_second_level_cache = (config.getUse_second_level_cache().equals("true"))?"true":"false";
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		finally{
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void saveDatabaseConfig(){
		
		Properties configprops = new Properties();
		configprops.setProperty("init", "false");
		configprops.setProperty("db.driverClassName", driverclass);
		configprops.setProperty("db.url", dburl);
		configprops.setProperty("db.username", config.getUsername());
		configprops.setProperty("db.password", config.getPassword());
		configprops.setProperty("hibernate.dialect", dialect);
		configprops.setProperty("hibernate.show_sql", "true");
		configprops.setProperty("hibernate.auto", "update");
		configprops.setProperty("hibernate.cache.use_second_level_cache", use_query_cache);
		configprops.setProperty("hibernate.cache.use_query_cache", use_second_level_cache);
		
		FileOutputStream out = null;
		try {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(configname);
		File file = new File(url.toURI().getPath());
		out = new FileOutputStream(file);
		
		configprops.store(out, null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void createUserDatabase(){
		Connection conn = null;
		try {
		Class.forName(driverclass);
		conn = DriverManager.getConnection(dbhosturl, config.getUsername(), config.getPassword());
		Statement stat = conn.createStatement();
		stat.executeUpdate("CREATE DATABASE "+dbname);
		System.out.println("database "+dbname+" created!");
		}
		catch (SQLException sqlException) {
			if (sqlException.getErrorCode() == 1007) {
	            System.out.println(sqlException.getMessage());
	        } else {
	            // Some other problems, e.g. Server down, no permission, etc
	            sqlException.printStackTrace();
	            				
				try {
					if(conn!=null){
					Statement stat = conn.createStatement();
					stat.executeUpdate("DROP DATABASE "+dbname);
					System.out.println("database "+dbname+" deleted!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if (conn != null && !conn.isClosed()) {
					conn.close();
			}
			}  catch (SQLException e) {
					e.printStackTrace();
			}
			
			}
		
	}
	
	public void loadInitDatabaseItems(){
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String hashedAdminPass = encoder.encodePassword(config.getAdmin_password(), null);
		Transaction trans = null;
		Session hibsession = null;
		try {
		hibsession = HibernateUtil.getInstance(dburl, config.getUsername(), config.getPassword(), dialect, driverclass).getSession();
		trans = hibsession.beginTransaction();
		Role roleadmin = new Role();
		roleadmin.setAuthority("ROLE_ADMIN");
		roleadmin.setDescription("Administraion role");
		Role roleuser = new Role();
		roleuser.setAuthority("ROLE_USER");
		roleuser.setDescription("User role");
		Role roleanonim = new Role();
		roleanonim.setAuthority("ROLE_ANONIM");
		roleanonim.setDescription("Anonim role");
		hibsession.merge(roleadmin);
		hibsession.merge(roleuser);
		hibsession.merge(roleanonim);
		User admin = new User();
		admin.setUsername(config.getAdmin_login());
		admin.setPassword(hashedAdminPass);
		admin.setProved(true);
		admin.setEnabled(true);
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.getRoles().add(roleadmin);
		BasicConfiguration baseconfig = new BasicConfiguration();
		baseconfig.setShopname(config.getShopname());
		hibsession.merge(admin);
		hibsession.merge(baseconfig);
		trans.commit();
		System.out.println("���������!");
		}
		catch (Exception e) {

			trans.rollback();
			System.out.println("���������� ����������!");
		}
		Connection conn = null;
		try {
		conn = DriverManager.getConnection(dbhosturl, config.getUsername(), config.getPassword());
		conn.createStatement().executeUpdate("use "+dbname);
		ScriptRunner runner = new ScriptRunner(conn, false, true);
		runner.runScript(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(importsqlfile))));
		}
		catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("do nothing");
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if (conn != null && !conn.isClosed()) {
					conn.close();
			}
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if (hibsession != null && hibsession.isOpen()) {
					hibsession.close();
				}
			}



	}
	
	
}
