package controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.InstallBean;
import utils.Installator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/install")
public class InstallController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ServletContext context;

/*	@RequestMapping(method = RequestMethod.GET)
    public String install(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, HttpSession sess) {
	
		map.put("config", new InstallBean());
		return "install";
	}*/
	
	@RequestMapping(method = RequestMethod.POST)
    public String installResult(@ModelAttribute(value = "config") InstallBean config, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, HttpSession sess) {

	/*	String dbtype = (config.getDbprovider().equals(2))?"oracle":"mysql";
		
		Properties prop = new Properties();
		Properties configprops = new Properties();
		InputStream input = null;
		Connection conn = null;
		FileOutputStream out = null;
		Session hibsession = null;
		Transaction trans = null;
		Integer dbport = config.getDbport();
		String dbhost = config.getDbhost();
		String dbname = config.getDbname();
		try {
	 
			String filename = "params.properties";
			String configname = "database.properties";
			String importsqlfile = "import.sql";
    		input = getClass().getClassLoader().getResourceAsStream(filename);
    		if(input==null){
	            System.out.println("Sorry, unable to find " + filename);
	            return null;
    		}
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
						
			String driverclass = prop.getProperty(dbtype+".driverClassName");
			String urlprefix = prop.getProperty(dbtype+".urlprefix");
			String separator = prop.getProperty(dbtype+".dbnameseparator");
			String dialect = prop.getProperty(dbtype+".hibernate.dialect");
			
			String dbhosturl = urlprefix+dbhost+":"+dbport;
			String dburl = urlprefix+dbhost+":"+dbport+separator+dbname;
			String use_query_cache = (config.getUse_query_cache())?"true":"false";
			String use_second_level_cache = (config.getUse_second_level_cache())?"true":"false";
			
			configprops.setProperty("db.driverClassName", driverclass);
			configprops.setProperty("db.url", dburl);
			configprops.setProperty("db.username", config.getUsername());
			configprops.setProperty("db.password", config.getPassword());
			configprops.setProperty("hibernate.dialect", dialect);
			configprops.setProperty("hibernate.show_sql", "true");
			configprops.setProperty("hibernate.auto", "update");
			configprops.setProperty("hibernate.cache.use_second_level_cache", use_query_cache);
			configprops.setProperty("hibernate.cache.use_query_cache", use_second_level_cache);
	
			
			
			Class.forName(driverclass);
			conn = DriverManager.getConnection(dbhosturl, config.getUsername(), config.getPassword());
			Statement stat = conn.createStatement();
			stat.executeUpdate("CREATE DATABASE "+dbname);
			System.out.println("database "+dbname+" created!");
					
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	    	  String hashedAdminPass = encoder.encodePassword(config.getPassword(), null);
			
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
			hibsession.save(roleadmin);
			hibsession.save(roleuser);
			hibsession.save(roleanonim);
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
			hibsession.save(admin);
			hibsession.save(baseconfig);
			trans.commit();		
			System.out.println("���������!");
			
			conn.createStatement().executeUpdate("use "+dbname);
			ScriptRunner runner = new ScriptRunner(conn, false, true);
			runner.runScript(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(importsqlfile))));
			
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL url = classLoader.getResource(configname);
			File file = new File(url.toURI().getPath());
			out = new FileOutputStream(file);
			
			configprops.store(out, null);
			System.out.println("���� ������!");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqlException) {
			if (sqlException.getErrorCode() == 1007) {
	            // Database already exists error
	            System.out.println(sqlException.getMessage());
	        } else {
	            // Some other problems, e.g. Server down, no permission, etc
	            sqlException.printStackTrace();
	            trans.rollback();
				
				try {
					Statement stat = conn.createStatement();
					stat.executeUpdate("DROP DATABASE "+dbname);
					System.out.println("database "+dbname+" deleted!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		} 
		catch (Exception e) {
			
			
		}
		
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
				 
		}*/
		
		Installator inst = new Installator(config);
		inst.install();
		System.out.println("successssss!");
		return "install";
	}
	
	
	
	
	
}






