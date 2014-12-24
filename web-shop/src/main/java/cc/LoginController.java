package cc;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import Model.AnonimBuck;
import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Bucket;
import Model.Category;
import Model.CategoryService;
import Model.Comment;
import Model.CommentService;
import Model.GoodItem;
import Model.GoodItemService;
import Model.LoginBean;
import Model.Order;
import Model.OrderSumCalculator;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.Town;
import Model.TownService;
import Model.User;
import Model.UserService;

@Controller
@Scope("session")
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;

	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private TownService twnServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	

	@RequestMapping("/adminlogin")
	public String logAdmin(Map<String, Object> map){
			map.put("section", "adminlogin");
			return "login";
	}
	
	@RequestMapping("/login")
	public String logUsr(Map<String, Object> map){
			map.put("section", "userlogin");
			return "login";
	}
	
	@RequestMapping(value = "/loginfail",method = RequestMethod.GET)
    public String logindialog(@RequestParam (value = "type") String section, Map <String, Object> map, HttpServletRequest req) {
	    map.put("login", new LoginBean());
        map.put("section", section);
        return "login";
    }
	
      
      @RequestMapping(value = "/reg",method = RequestMethod.GET)
      public String addUser(@RequestParam(value = "logresult", required = false) String logresult, Map <String, Object> map, HttpServletRequest req, HttpSession sess) {
         
    	AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
  		if(bucket==null){bucket = new AnonimBuck();}
  		map.put("bucketsize", bucket.getSize());
  		
  		Set<Page> headerpages = pageServ.getHeaderPages();
  		map.put("headerpages", headerpages);
  		
  		Set<PageGroup> pagegroups = pgrServ.getFooterPagegroups();
  		map.put("pagegroups", pagegroups);
  		
  		Integer currenttownid = (Integer) sess.getAttribute("cityid");
  		if(currenttownid==null){currenttownid = 908;}
  		Town currenttown = new Town();
  		currenttown = twnServ.getById(currenttownid);
  		
  		List<BasicConfiguration> bcfgs = bcfServ.getAll();
  		BasicConfiguration basic = bcfgs.get(0);
  		map.put("basic", basic);
  		
  		List<Category> roots = catServ.getRootCategories();
  		map.put("currentCatList", roots);
    	
  		if(logresult!=null && !logresult.equals(""))
  		map.put("logresult", logresult);
    	  System.out.println("here");
          Random r = new Random();
          map.put("user", new User());
          map.put("rand1", r.nextInt(10));
          map.put("rand2", r.nextInt(10));
          return "regpage";
      }
      
      
      @RequestMapping(value = "/emailchecker",method = RequestMethod.POST)
      public @ResponseBody String checkEmail(@RequestParam (value = "log") String email, Map <String, Object> map, HttpServletRequest req) {
    	
    	  if(usrServ.getUsersByEmail(email).isEmpty()){
    		  return "no";
    		  
    	  }
          return "yes";
      }
      
      @RequestMapping(value = "/loginchecker",method = RequestMethod.POST)
      public String checkLogin(@RequestParam (value = "section") String section, Map <String, Object> map, HttpServletRequest req) {
          return "commsection";
      }
      
      
      
      @RequestMapping(value = "/adduser",method = RequestMethod.POST)
      public  String adduser(@RequestParam (value = "section") String section, 
    		  @RequestParam (value = "email") String email,
    		  @RequestParam (value = "name") String name, 
    		  @RequestParam (value = "pass") String pass, 
    		  @RequestParam (value = "city") Integer city,
    		  @RequestParam (value = "cityname") String cityname, 
    		  @RequestParam (value = "mailer") Boolean mailer, 
    		  Map <String, Object> map, HttpServletRequest req) {
    	  
    	 
    	  User user = new User();
    	  if(city!=null){
    		  Town town = twnServ.getById(city);
    		  if(town!=null)
    		  user.setTown(town);
    		  else{
    			  if(cityname!=null && !cityname.equals(""))
    				  user.setStringtown(cityname); 
    			  
    		  }
    	  }
    	  if(city==null && cityname!=null && !cityname.equals("")){
    		  user.setStringtown(cityname);
    	  }
    	  
    	  user.setUsername(email);
    	  user.setName(name);
    	  
    	  map.put("section", "succesreg");
 //   	  MD5 md5 = new MD5();   
    	  
    	  Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	  String hashedPass = encoder.encodePassword(pass, null);

    //	   System.out.println("a564de63c2d0da68cf47586ee05984d7   and    " + hashedPass);
    	   	
	      user.setPassword(hashedPass);
	      user.setIsSubscribed(mailer);
 //   	  System.out.println(mailer);
    	  user.setIsSubscribed(mailer);
    	  
    	  user.setAccountNonExpired(true);
    	  user.setAccountNonLocked(true);
    	  user.setCredentialsNonExpired(true);
    	  user.setEnabled(true);
    	  
    	  System.out.println(user);
    	  
    	  usrServ.add(user);
    	  
    
		return "commsection";
      }
      
      public class MD5 {

    	    
    	    public String getHash(String str) {
    	        
    	        MessageDigest md5 ;        
    	        StringBuffer  hexString = new StringBuffer();
    	        
    	        try {
    	                                    
    	            md5 = MessageDigest.getInstance("md5");
    	            
    	            md5.reset();
    	            md5.update(str.getBytes()); 
    	                        
    	                        
    	            byte messageDigest[] = md5.digest();
    	                        
    	            for (int i = 0; i < messageDigest.length; i++) {
    	                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
    	            }
    	                                                                                        
    	        } 
    	        catch (NoSuchAlgorithmException e) {                        
    	            return e.toString();
    	        }
    	        
    	        return hexString.toString();
    	    }
    	  
    	}
     
     
	
}
