package cc;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.servlet.ModelAndView;


import Model.Adress;
import Model.AdressService;
import Model.AdressType;
import Model.AnonimBuck;
import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Bucket;
import Model.Category;
import Model.CategoryService;
import Model.Comment;
import Model.CommentService;
import Model.GoodCollection;
import Model.GoodItem;
import Model.GoodItemService;
import Model.Order;
import Model.OrderService;
import Model.OrderSumCalculator;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.PhoneNumber;
import Model.PhoneNumberType;
import Model.PhoneService;
import Model.Town;
import Model.TownService;
import Model.User;
import Model.UserService;

@Controller
public class usrServ {

	@Autowired
	private UserService usrServ;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private PhoneService phnServ;
	
	@Autowired
	private OrderService ordServ;
	
	@Autowired
	private AdressService adrServ;
	
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
	
	 @RequestMapping(value="/usrmenu")  
     public String processPerson() {  
		 return "usrmenu";
     } 
     
	 
	 @RequestMapping(value="/cabinet", method = RequestMethod.GET)  
     public String getCabinet(@RequestParam (value = "section") String section,  Map <String, Object> map, 
			 HttpSession sess, HttpServletRequest request) {  
		 User user = (User) request.getAttribute("user");
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
	  	
		 if(user!=null){
			 System.out.println("Товары желанные: ");
		 for(GoodItem g: user.getWishgoods()){
			 System.out.println(g.getName()+"   "+g.getPrice());
		 }
		 	System.out.println("Товары отслеживаемые: ");
		 for(GoodItem g: user.getWatchgoods()){
			 System.out.println(g.getName()+"   "+g.getPrice());
		 }
		 
		 }
		 map.put("sessid", sess.getId());
		 map.put("section", section);
		 map.put("user", user);
		 if(section.equals("personal_data")||section == null||section.equals("")){
			 
		 }
			 
		 else if(section.equals("new_order")){
			 Set <Order> ords = user.getOrders();
			map.put("orders", ords);
		 }
		 
		 else if(section.equals("all_order")){
			 Set <Order> ords = user.getOrders();
			 map.put("orders", ords);
		 }
		 
		 else if(section.equals("user_wish")){
			 map.put("wishgoods", user.getWishgoods());
	 
		 }
		 else if(section.equals("history")){
			 map.put("histgoods", user.getHistorygoods());
		 }
		 else if(section.equals("lookforprice")){
			 
		 }
		 else if(section.equals("my_reviews")){
			 
		 }
		 

		 return "cabinet";
     } 
	 
	 
	 @RequestMapping(value="/cabinet/updateUserInfo", method = RequestMethod.GET)  
     public String updateUserInfo(@RequestParam (value = "name") String name, @RequestParam (value = "nick") String nick, @RequestParam (value = "bdate") String bdate, Map <String, Object> map, HttpServletRequest request) {  
		 User user = (User) request.getAttribute("user");
		 user.setName(name);
		 user.setNick(nick);
		 Date date ;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(bdate);
			user.setBirthdate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 usrServ.update(user);
		 map.put("user", user);
		 map.put("section", "uinfosucces");
		 
		 return "commsection";

     } 
	 
	 @RequestMapping(value="/cabinet/delphone", method = RequestMethod.POST)  
     public String delPhone(@RequestParam (value = "numid") Integer numid, 
    		  Map <String, Object> map, HttpServletRequest request) {
		 User user = (User) request.getAttribute("user");
		 phnServ.deleteById(numid);
		 		map.put("section", "usernumbers");
		 		map.put("user", user);
				return "commsection";
		 
	 }
	 
	 
	 @RequestMapping(value="/loadnumberform", method = RequestMethod.POST) 
	 public String loadNumberForm(@RequestParam (value = "cmd") String cmd,
			 @RequestParam (value = "mail") String mail,
			 @RequestParam (value = "sessid") String sessid,
			 @RequestParam (value = "numid") Integer numid, 
			 Map <String, Object> map, 
			 HttpSession sess, HttpServletRequest request
			 ){
		 
		 	if(numid>0){
		 		PhoneNumber numb = phnServ.getById(numid);
		 		map.put("number", numb);
		 	}
		 	map.put("section","loadnumberform");
		 	map.put("sessid", sess.getId());
		 	
			return "commsection";
		 
		 
	 }
	
	 @RequestMapping(value="/cabinet/updateUser", method = RequestMethod.POST)  
     public String updateUserPass(
    		 @RequestParam (value = "name") String name,
    		 @RequestParam (value = "birthday") String birthday,
    		 @RequestParam (value = "nick") String nick,
    		 @RequestParam (value = "type_street") String type_street,
    		 @RequestParam (value = "street") String street,
    		 @RequestParam (value = "house") String house,
    		 @RequestParam (value = "flor") String flor,
    		 @RequestParam (value = "room") String room,
    		 Map <String, Object> map, HttpServletRequest request) {
		 User user = (User) request.getAttribute("user");
		 		if(!birthday.equals("")){
		 			try {
						Date bdate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(birthday);
						user.setBirthdate(bdate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 		}
		 		user.setNick(nick);
		 		user.setName(name);
		 		
		 		Adress adr = new Adress();
		 		adr.setStreet_type(type_street);
				adr.setStreet_name(street);
				adr.setHouse_num(house);
				adr.setLevel(flor);
				adr.setRoom_num(room);
				adr.setAdresstype(AdressType.CLIENT);
				
				user.setAdress(adr);
				usrServ.update(user);
		 		
				return "redirect:?section=personal_data";  
		 
	 }
	 
	 @RequestMapping(value="/cabinet/orderdatefilter", method = RequestMethod.POST)  
     public String orderDateFilter(@RequestParam (value = "section") String section,
    		 @RequestParam (value = "datefrom") String datefrom,
    		 @RequestParam (value = "dateto") String dateto,
    		 Map <String, Object> map, HttpServletRequest request) {  
		 User user = (User) request.getAttribute("user");
		 System.out.println("rbvrewvgrwe");
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date d1 = new Date();
		 Date d2 = new Date();
		 try {
			d1 = dateformat.parse(datefrom);
			d2 = dateformat.parse(dateto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println(d1);
		 System.out.println(d2);
		 Set <Order> ords = ordServ.orderDateFilter(user, d1, d2);
		 for(Order ord: ords){
			 System.out.println(ord.getTime()+"   "+ord.getAmount());
		 }
		 map.put("orders", ords);
		 map.put("section", section);
		 
		 return "commsection";
	 }
	 
	 @RequestMapping(value="/cabinet/updateUserPass", method = RequestMethod.POST)  
     public String updateUserPass(@RequestParam (value = "psw_old") String psw_old,
    		 @RequestParam (value = "psw_new1") String psw_new1,
    		 @RequestParam (value = "psw_new2") String psw_new2,
    		 Map <String, Object> map, HttpServletRequest request) {  
		 Boolean isError = false;
		 User user = (User) request.getAttribute("user");
		 StringBuilder text = new StringBuilder();
		 map.put("section", "upass");
		 if(!psw_new1.equals(psw_new2)){
			 map.put("color", "red");
			 text.append("Введенные новые пароли не сопадают");
	//		 map.put("text", "Введенные новые пароли не сопадают");
			 isError = true;
			 
		 }
		
		 Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		 String hashedpsw_old = encoder.encodePassword(psw_old, null);
		 if(!user.getPassword().equals(hashedpsw_old)){
			 map.put("color", "red");
			 text.append("<br>Старый пароль - неверный");
	//		 map.put("text", "Старый пароль - неверный");
			 isError = true;
			 
		 }
		 
		 if(isError){
			 map.put("text", text.toString());
			 return "commsection";
			 
		 }
		 String hashedpsw_new = encoder.encodePassword(psw_new1, null);
		 user.setPassword(hashedpsw_new );
		 
		 usrServ.update(user);
		 map.put("color", "green");
		 map.put("text", "Пароль удачно сменен!");
//		  map.put("user", user);
//		 map.put("section", "upass");
		 return "commsection";
		
	 } 
	 
	 
	 
	 @RequestMapping(value="/cabinet/updateNumber", method = RequestMethod.POST)  
	 public String updateUserNumb(@RequestParam (value = "cmd") String cmd,
    		 @RequestParam (value = "sessid") String sessid,
    		 @RequestParam (value = "numb") String numb,
    		 @RequestParam (value = "numb_id") Integer numbid,
    		 @RequestParam (value = "numb_type") Integer numbtypeid,
    		 		 Map <String, Object> map, HttpServletRequest request) {
		 User user = (User) request.getAttribute("user");
		 if(user==null){
			 System.out.println("nullll");
		 }
//		 user.setPhone(numb);
//		 PhoneNumber phone = phnServ.getPhoneNumberByNum(numb);
//		 if(phone!=null){
//			 user.setPh
//		 }
		 PhoneNumber phn = new PhoneNumber();
		 
		 if(numbid>0){
			phn = phnServ.getById(numbid);
		 }
			 
		 if(!numb.equals("")){
		 phn.setNumb(numb);
		 String code =numb.substring(numb.indexOf("(")+1,numb.indexOf(")"));
		 phn.setTown(twnServ.getTownsByCode(Integer.parseInt(code)));
		 phn.setNumTypeId(numbtypeid);
		 }
			
		 if(numbid>0){
				phnServ.update(phn);
			 }
			 else{
				 phn.addUser(user);
				 phnServ.add(phn);
			 }
		 map.put("section", "usernumbers");
	 		map.put("user", user);
		 return "commsection";
		 
	 }
	
	@RequestMapping(value = "/")
	public String home(HttpServletRequest req, @ModelAttribute User usr, HttpSession sess, HttpServletRequest request){
		System.out.println("home");
		User h = new User();
		h.setIp(req.getLocalAddr());
		h.setLogin("Anonim user");
//		Bucket b = new Bucket();
//		b.setName("My Bucket");
//		List <GoodItem> usergoods = new ArrayList<GoodItem>();
//		b.setGoods(usergoods);
//		h.addBucket(b);
		System.out.println(h.getIp());
		AnonimBuck b = new AnonimBuck();
		b.setName("My Bucket");
//		b.setGoods(usergoods);
//		b.getGoods();
		sess.setAttribute("usr", h);
		sess.setAttribute("currbuck", b);
//		sess.setAttribute("currgoods", usergoods);
		return "redirect:/index";
	}
	
	@RequestMapping("/usrmenu/{usrid}")
	public String usrMenu(@PathVariable(value = "usrid")Integer id, Map <String, Object> map){
		User t = usrServ.getById(id);
		map.put("usr", t);
		System.out.println("usrMenu");
		return "usrmenu";
	}
	
	@RequestMapping("/usrbuck/{usrid}")
	public String listUserBuckBuId(@PathVariable(value = "usrid")Integer id, Map <String, Object> map){
		if(id == 0){return "redirect:/usrbuck";}
		User t = usrServ.getById(id);
		map.put("buck", new Bucket());
		map.put("buckList", t.getBuck());
		map.put("usr", t);
		System.out.println("listBuckets");
		return "bucks";
	}
	
	@RequestMapping("/usrbuck")
	public String listUserBuck(Map <String, Object> map, HttpSession sess){
		AnonimBuck b = (AnonimBuck) sess.getAttribute("currbuck");
//		User t = (User)sess.getAttribute("usr");
//		Session hibsess = SessionFactoryUtils.getSession(sessfactory, false);
//		hibsess.replicate(b, ReplicationMode.LATEST_VERSION);
//		List bucks = t.getBuck();
		double sum;
		List bucks = new ArrayList<AnonimBuck>();
		bucks.add(b);
		map.put("buck", new Bucket());
		map.put("buckList", bucks);
		System.out.println("listBuckets");
		return "bucks";
	}
	
	
		
	@RequestMapping("/usrcomm/{usrid}")
	public String listCommById(@PathVariable(value = "usrid")Integer id, Map <String, Object> map){
		User t = usrServ.getById(id);
		map.put("com", new Comment());
		map.put("comList", t.getComments());
		map.put("usr", t);
		System.out.println("listComments");
		return "com";
	}
	
	@RequestMapping("/usrorders/{usrid}")
	public String listOrdById(@PathVariable(value = "usrid")Integer id, Map <String, Object> map){
		User t = authorizeUser();
		Set <Order> ords = t.getOrders();
		map.put("orders", ords);
		return "orders";
	}

	
	
	
	@RequestMapping(value = "/delusr/{usrId}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("usrId") Integer id){
//		usrServ.deleteUserById(id);
		return "usr";
	}
	
	@RequestMapping("/users")
	public String listUsr(Map<String, Object> map){
		map.put("usr", new GoodItem());
		map.put("usrList", usrServ.getAll());
		System.out.println("listUsers");
		return "usr";
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public boolean isAuthorized(UserDetails userdet){
		boolean isAuthorized = false;
		for(GrantedAuthority role: userdet.getAuthorities()){
			if(role.getAuthority().equals("ROLE_ANONYMOUS")){
				if(userdet.getUsername().equals("guest")){
					return false;
				}
				
			}
		}
	return true;
		
	}
	
	public User authorizeUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		UserDetails userdet;
		User user = null;
		boolean isAuthorized;
			if (principal instanceof UserDetails) {
				userdet = (UserDetails)principal;
				isAuthorized = isAuthorized(userdet);
	//			System.out.println(isAuthorized);
				
				  username = (userdet).getUsername();
				  if(isAuthorized){
					  user = usrServ.getUsersByEmail(username).get(0);
				  }
				  
				} else {
				 username = principal.toString();
				 isAuthorized = false;
				}
//			System.out.println(isAuthorized);
			return user;
	}

}
