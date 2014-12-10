package cc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import Model.AnonimBuck;
import Model.AnonimEmailService;
import Model.AnonimEmailWatcher;
import Model.Category;
import Model.CategoryService;
import Model.Comment;
import Model.CommentService;
import Model.CategoryCompareGoods;
import Model.CompareGoodsSet;
import Model.Country;
import Model.GoodCollection;
import Model.GoodCompareBean;
import Model.GoodHistoryBean;
import Model.GoodItem;
import Model.GoodItemCreationBean;
import Model.GoodItemService;
import Model.GoodStateService;
import Model.Guarantie;
import Model.GuarantieService;
import Model.HistorySet;
import Model.Order;
import Model.OrderRow;
import Model.OrderService;
import Model.OrderSumCalculator;
import Model.PayType;
import Model.PayTypeService;
import Model.Property;
import Model.PropertyService;
import Model.Region;
import Model.RegionService;
import Model.Row;
import Model.TextPart;
import Model.TextPartService;
import Model.Town;
import Model.TownService;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueService;
import Model.Vote;
import Model.VoteService;

@Controller
@RequestMapping("/goodservices")
public class GoodServices {
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private GoodStateService gstServ;
	
	@Autowired
	private CategoryService catServ;
	@Autowired
	private PropertyService propServ;
	@Autowired
	private ValueService valServ;
	@Autowired
	private OrderService ordServ;
	@Autowired
	private VoteService voteServ;
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private AnonimEmailService emServ;
	
	@Autowired
	private TownService twnServ;
	@Autowired
	private TextPartService txtpartServ;
	@Autowired
	private GuarantieService guarServ;
	@Autowired
	private PayTypeService ptpServ;
	@Autowired
	private RegionService regServ;
	
	
	@RequestMapping(value = "/gettext", method = RequestMethod.POST)
	public String getText(@RequestParam (value = "pid") Integer id, @RequestParam (value = "type") String type,  Map<String, Object> map) {
		String text = "";
		if(type.equals("garanty")){
		Guarantie guar = guarServ.getById(id);
		text = guar.getInfo();
		
		}
		if(type.equals("paytype")){
			PayType ptp = ptpServ.getById(id);
			text = ptp.getInfo();
			
		}
		System.out.println(text);
		String utf8str = "";
		try {
			utf8str = new String(text.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("text", text);
		return "goodserviceinfo";
	}
	
	@RequestMapping(value = "/deliveryprices", method = RequestMethod.POST)
	public String deliveryPrices(@RequestParam (value = "pid") Integer goodid,  Map<String, Object> map, HttpSession sess) {
		System.out.println("ewgwehg");
		Integer currenttownid = (Integer) sess.getAttribute("cityid");
		if(currenttownid==null){currenttownid = 908;}
		Town currenttown = new Town();
		currenttown = twnServ.getById(currenttownid);
		
		
		Country cntr = currenttown.getRegion().getCountry();
		List<Region> regions = cntr.getRegions();
		System.out.println(currenttown.getName()+"      "+cntr.getName()+"      "+regions);
		map.put("regions", regions);
		
		map.put("section", "regions");
		if(goodid<0)
		map.put("type", "citychoise");	
		map.put("goodid", goodid);
		map.put("seltown", currenttown);
		map.put("selregion", currenttown.getRegion());
		return "deliveryprices";
	}
	
	@RequestMapping(value = "/selectregion", method = RequestMethod.POST)
	public String changeDeliveryRegion(@RequestParam (value = "rid") Integer regid, @RequestParam (value = "gid") Integer goodid, Map<String, Object> map, HttpSession sess) {
		Region reg = regServ.getById(regid);
		List<Town> towns = reg.getDeliverytowns();
		Town seltown = null;
		if(!towns.isEmpty())
		seltown = towns.get(0);
		map.put("goodid", goodid);
		map.put("seltown", seltown);
		map.put("selregion", reg);
		if(goodid<0)
			map.put("type", "citychoise");		
		map.put("deliverytowns", towns);
		map.put("section", "deliverytowns");
		
		return "deliveryprices";
	}
	
	
	@RequestMapping(value = "/selectcity", method = RequestMethod.POST)
	public String changeDeliveryCity(@RequestParam (value = "cid") Integer cid, @RequestParam (value = "gid") Integer goodid, Map<String, Object> map, HttpSession sess) {
		
		Town seltown = twnServ.getById(cid);
		
		map.put("goodid", goodid);
		map.put("seltown", seltown);
		map.put("section", "adressdeliveries");
		if(goodid<0)
			map.put("type", "citychoise");	
		return "deliveryprices";
	}
	
	
	@RequestMapping(value = "/choisecity", method = RequestMethod.POST)
	public @ResponseBody Integer changeUserCity(@RequestParam (value = "cid") Integer cid, Map<String, Object> map, HttpSession sess) {
		
		Town currenttown = twnServ.getById(cid);
		sess.setAttribute("city", currenttown);
		return 1;
	}
	
	
	
	
	
	@RequestMapping(value = "/addanonimemail", method = RequestMethod.POST)
	public @ResponseBody String addAnonimemail(@RequestParam (value = "email") String email, @RequestParam (value = "goodid") Integer goodid,  Map<String, Object> map) {
		
		AnonimEmailWatcher as = emServ.getEmail(email);
		
		if(as==null){
			as = new AnonimEmailWatcher();
		}
		
		as.setEmail(email);
		as.addGood(Serv.getById(goodid));
		emServ.update(as);
		return "addedToWatches";
		
		
	}
	
	@RequestMapping(value = "/addwishgood", method = RequestMethod.POST)
	public String addWishgood(@RequestParam (value = "goodid") Integer goodid,HttpServletRequest request, @RequestParam (value = "section") String section,  Map<String, Object> map) {
			User user = (User)request.getAttribute("user");
			map.put("section", section);
			System.out.println("Имя товара желания - "+Serv.getById(goodid).getName());
			if(user!=null){
				user.addWishgood(Serv.getById(goodid));
				usrServ.update(user);
				System.out.println("Желанный товар: "+user.getWishgoods());
			}
			
			map.put("user", user);
			
			return "commsection";
		
		
	}
	
	@RequestMapping(value = "/addwatchgood", method = RequestMethod.POST)
	public @ResponseBody String addWatchgood(@RequestParam (value = "goodid") Integer goodid, HttpServletRequest request,  Map<String, Object> map) {
			User user = (User)request.getAttribute("user");
			user.addWatchgood(Serv.getById(goodid));
			usrServ.update(user);
			return "addedToWishes";
		
		
	}
	
	@RequestMapping(value = "/historygood", method = RequestMethod.POST)
	public @ResponseBody String addHistorygood(@RequestParam (value = "goodid") Integer goodid,  Map<String, Object> map , HttpServletRequest request) {
			User user = (User)request.getAttribute("user");
			user.addHistorygood(Serv.getById(goodid));
			usrServ.update(user);
			System.out.println("История просмотров "+user.getHistorygoods());
			return "addedToWishes";
		
		
	}

	@RequestMapping(value = "/estimate", method = RequestMethod.GET)
	public String addComment(@RequestParam (value = "good_id") Integer goodid, 
			@RequestParam (value = "user_votes") Integer user_votes,
			@RequestParam (value = "section") String section,
			Map<String, Object> map,
			HttpServletResponse response){
			
		GoodItem good = Serv.getById(goodid);
		good.addVote(voteServ.getById(user_votes));
		Serv.update(good);
		map.put("section", section);
		Cookie c = new Cookie("isVoted", "1"); 
		response.addCookie(c);
//		c.setMaxAge(1000); //set expire time to 1000 sec
		
		return "commsection";
		
		
		
	}
	
	
	@RequestMapping(value = "/addcomment", method = RequestMethod.POST)
	public String addComment(@RequestParam (value = "goodid") Integer goodid, 
			@RequestParam (value = "text") String text, 
			@RequestParam (value = "parentcomm_id") Integer parentcomm_id, 
			@RequestParam (value = "section") String section, 
			@RequestParam (value = "name") String name,
			@RequestParam (value = "plus") String plus,
			@RequestParam (value = "minus") String minus,
			@RequestParam (value = "note") Integer note,
			HttpSession sess, Map<String, Object> map, HttpServletRequest request) {
		
		map.put("section", section);
		
		GoodItem g =Serv.getById(goodid);
		Comment com = new Comment();
//		User user = (User)sess.getAttribute("usr");
		User user = (User)request.getAttribute("user");
		
		
		
//		com.setAuthoremail(email);
		com.setPlus(plus);
		com.setMinus(minus);
		com.setText(text);
		com.setNote(note);
//		com.setParent_id(-1);
	//	String time = new Date(System.currentTimeMillis()).toString();
//		com.setTime(new Date(System.currentTimeMillis()).toString());
		com.setDatetime(new Date(System.currentTimeMillis()));
		
		
		if(user!=null){
			user.addComment(com, g);
			com.setAuthor(user);
			usrServ.update(user);
			}
		else{com.setAuthorname(name);}
		System.out.println("АЙДДДИ   "+com.getId());
		//	g.addComment(com);	
		Serv.addComment(g, user, com);		
		
		final Boolean onlyproved = false;
		
		Set<Comment> comms = new TreeSet<Comment>(new Comparator<Comment>(){

			@Override
			public int compare(Comment o1, Comment o2) {
				// TODO Auto-generated method stub
				return o2.getDatetime().compareTo(o1.getDatetime());
			}

			
			
		});
		if(onlyproved){
			comms = comServ.getProvedCommentsByGood(g);
		}
		else{
			comms = g.getRootComments();
		}
		
		setSubComments(comms, 2, onlyproved);
		
		System.out.println("Комментарии: ");
		System.out.println(comms);
		
		map.put("comList", comms);
		map.put("good", g);
	
		
		return "comments";
	}
	
	
	
	@RequestMapping(value = "/addanswer", method = RequestMethod.POST)
	public String addAnswer(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "text") String text, @RequestParam (value = "parentcomm_id") Integer parentcomm_id, @RequestParam (value = "section") String section, HttpSession sess, Map<String, Object> map) {
		map.put("section", section);
		
		GoodItem g =Serv.getById(goodid);
		Comment com = new Comment();
//		User user = (User)sess.getAttribute("usr");
		User user = authorizeUser();
		if(user!=null){
			com.setAuthor(user);
			}
		if(section.equals("add")){
		com.setText(text);
		com.setParent_comment(comServ.getById(parentcomm_id));
		com.setDatetime(new Date(System.currentTimeMillis()));
//		String time = new Date(System.currentTimeMillis()).toString();
//		com.setTime(time);
		g.addComment(com);
		Serv.update(g);		
		final Boolean onlyproved = false;
		
		Set<Comment> comms = new TreeSet<Comment>(new Comparator<Comment>(){

			@Override
			public int compare(Comment o1, Comment o2) {
				// TODO Auto-generated method stub
				return o2.getDatetime().compareTo(o1.getDatetime());
			}

			
			
		});
		if(onlyproved){
			comms = comServ.getProvedCommentsByGood(g);
		}
		else{
			comms = g.getRootComments();
		}
		setSubComments(comms, 2, onlyproved);
		map.put("comList", comms);
		map.put("good", g);
		}
		
		return "commsection";
	}

	
	
	@RequestMapping(value = "/addresponce", method = RequestMethod.GET)
	public String addResponce(@RequestParam (value = "id") Integer goodid, @RequestParam (value = "section") String elemid, Map<String, Object> map) {
		
		map.put("section", "respblock");
		map.put("goodid", goodid);
		return "commsection";
		
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public String changeFragment(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "elemid") String elemid, Map<String, Object> map) {
		Set<Value> vals;
		Set<Comment> comms;
		GoodItem g =Serv.getById(goodid);
		Map<String, String> hh = new HashMap<String, String>();
		map.put("section", elemid);
		map.put("good", g);
		
		if(elemid.equals("all")){
			System.out.println("ALLLLL");
			map.put("good", g);
			vals = g.getVals();
			hh = new HashMap<String, String>();
			for(Value val: vals){
				hh.put(val.getProp().getName(), val.getValue());
			}
			comms = g.getComments();
			map.put("com", new Comment());
			map.put("comList", comms);
			map.put("properties", hh);
		}	
		 
			if(elemid.equals("params")){
			System.out.println("PARAAAAMS");
			vals = g.getVals();
			hh = new HashMap<String, String>();
			for(Value val: vals){
				hh.put(val.getProp().getName(), val.getValue());
			}
			map.put("properties", hh);
			}	
		
		
			if(elemid.equals("voices")){
			comms = g.getComments();
			map.put("com", new Comment());
			map.put("comList", comms);
			}	
		
		return "goodparams";
		}
	
	
	@RequestMapping(value = "/changecount", method = RequestMethod.POST)
	public String changeGoodCountInBucket(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "count") Integer count, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User user = (User)request.getAttribute("user");
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		buck.changeCount(Serv.getById(goodid), count);
		sess.setAttribute("currbuck", buck);
		map.put("section", "bucket");
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum =calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
		return "commsection";
	}
	
	@RequestMapping(value = "/deletefrombuck", method = RequestMethod.POST)
	public String deleteFromBucket(@RequestParam (value = "goodid") Integer goodid, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User t = (User)request.getAttribute("user");
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		buck.deleteGood(Serv.getById(goodid));
		sess.setAttribute("currbuck", buck);
		map.put("section", "bucket");
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum =calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("usr", t);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
		return "commsection";
	}
	
	@RequestMapping(value = "/bucket", method = RequestMethod.POST)
	public String showBucket(@RequestParam (value = "goodid") Integer goodid, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User t = (User)request.getAttribute("user");
		GoodItem good = Serv.getById(goodid);
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		buck.addGood(good);
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum = calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
		map.put("section", "bucket");
		return "commsection";
		
	}
	
	@RequestMapping(value = "/tocompare", method = RequestMethod.POST)
	public @ResponseBody CompareGoodsSet addGoodToCompare(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "catid") Integer catid, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User t = (User)request.getAttribute("user");
		GoodItem good = Serv.getById(goodid);
		CompareGoodsSet compareset = (CompareGoodsSet)sess.getAttribute("comparelist");
		if(compareset==null){
			compareset = new CompareGoodsSet();
		}
		if(compareset.getComparedByCategoryId(catid)==null){
			CategoryCompareGoods comparegoods = new CategoryCompareGoods();
			comparegoods.setCat(catServ.getById(catid));
			compareset.addCategoryCompareGoods(comparegoods);
		}
		
		System.out.println("wegewgewgegwgewgewgeg!!!!!!!!!!!!!");
			
		System.out.println(compareset.getCategcomparesets());
		compareset.getComparedByCategoryId(catid).addGood(goodid);
		for(CategoryCompareGoods h: compareset.getCategcomparesets()){
			System.out.println("ID товара "+h.getGoodsid());
		}
		sess.setAttribute("comparelist", compareset);
		
		
		
		return compareset;
		
	}
	
	
	
	public void setSubComments(Set<Comment> comms, int levels, Boolean onlyproved){
		if(levels == 0){return;}
		if(comms == null && comms.isEmpty() ){return;}
		for(Comment com : comms){
			Set<Comment> subcats = comServ.getAnswers(com, onlyproved);
			com.setChieldcomms(subcats);
			setSubComments(subcats, levels-1, onlyproved);
		}
		
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



