package cc;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import Model.GoodCollection;
import Model.GoodCompareBean;
import Model.GoodHistoryBean;
import Model.GoodItem;
import Model.GoodItemCreationBean;
import Model.GoodItemService;
import Model.GoodStateService;
import Model.HistorySet;
import Model.Order;
import Model.OrderRow;
import Model.OrderService;
import Model.OrderSumCalculator;
import Model.Property;
import Model.PropertyService;
import Model.Row;
import Model.Town;
import Model.TownService;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueService;
import Model.Vote;
import Model.VoteService;

@Controller
@RequestMapping("/good/{goodid}/{section}")
public class goodServ {
	
	
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
	
//	private User user;
//	private AnonimBuck bucket;
	
	public void setSessionParameters(HttpServletRequest request, HttpSession sess){
		
			
	}
	
/*	@RequestMapping(method = RequestMethod.GET)
	public String showGood(@PathVariable (value = "goodid") Integer id, @PathVariable (value = "section") String section, @CookieValue(value = "isVoted", defaultValue = "0") Integer isVoted, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		System.out.println("Выборки для товаров: ");
		User user = (User)request.getAttribute("user");
		
		
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
//		setSessionParameters(user, bucket, request, sess);
//		System.out.println(section);
//		if (user==null){
//			user = new User();
//			user.setIp(request.getLocalAddr());
//			user.setLogin("Гость");
			
//		}
		if(bucket==null){bucket = new AnonimBuck();}
		if(user!=null){
		System.out.println("Имя пользователя "+user.getName());
			
		}
		GoodItem g =Serv.getById(id);
		CompareGoodsSet compareset = (CompareGoodsSet)sess.getAttribute("comparelist");
		if(compareset==null)compareset = new CompareGoodsSet();
		Boolean containswish = compareset.containsGood(g);
		System.out.println(containswish);
		map.put("containswish", containswish);
		
		Set<Value> vals = g.getVals();
		Map<String, String> hh = new HashMap<String, String>();
		for(Value val: vals){
			hh.put(val.getProp().getName(), val.getValue());
		}
//		for(Entry<String,String> j:hh.entrySet()){
//		System.out.println(j.getKey()+"  "+j.getValue());
//		}
		Set<GoodItem> history  = (Set<GoodItem>) sess.getAttribute("history");
		if(history==null)		{
			history = new HistorySet<GoodItem>();
		}
		if(user!=null){
			user.addHistorygood(g);
			usrServ.update(user);
		}
		
		for(GoodItem k: history){
			System.out.println("ИСТОРИЯ "+k.getName());
		}
		double total_rating = 0.0;
		double votes_count = (double) g.getVotes().size();
		
		for(Vote vote: g.getVotes()){
			total_rating+=vote.getId();
			
		}
		
		List<GoodItem> othergoods = g.getCategory().getGoods();
		othergoods.remove(g);
		
		List<Category> roots = catServ.getRootCategories();
		
		map.put("othergoods", othergoods);
		map.put("history", history);
		map.put("isVoted", isVoted);
		map.put("total_rating", total_rating);
		map.put("votes_count", (int)votes_count);
		map.put("currentCatList", roots);
//		System.out.println(total_rating);
//		System.out.println((int)votes_count);
		if(votes_count==0)	map.put("averagenote", 5);
		else{
			map.put("averagenote", Math.round(total_rating/votes_count));
		}
		
//		System.out.println(isVoted);
		
		
//		for(GoodItem h: history){
//			System.out.println("История: "+ h.getName());
//		}
		
		
		history.add(g);
		sess.setAttribute("history", history);
		
		Town currenttown = (Town) sess.getAttribute("city");
		if(currenttown==null){
			currenttown = twnServ.getById(908);
		}
		map.put("currenttown", currenttown);
		
		map.put("good", g);
		map.put("segments", GoodItem.fillSegments(g, catServ.getById(g.getCategory().getId()).getPropsegments()));
		
		map.put("goodstate", g.getState());
		map.put("images", g.getImages());
		map.put("bucketsize", bucket.getSize());
		Boolean onlyproved = false;
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
		map.put("com", new Comment());
		map.put("comList", comms);
		map.put("comCount", comms.size());
		map.put("properties", hh);
		map.put("section", section);
		System.out.println("showgood");
		return "goodpage";
	}
	*/
	
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
	
/*	@RequestMapping(value = "/addwishgood", method = RequestMethod.POST)
	public String addWishgood(@RequestParam (value = "goodid") Integer goodid,HttpServletRequest request, HttpSession sess, @RequestParam (value = "section") String section,  Map<String, Object> map) {
			User user = (User)sess.getAttribute("user");
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
*/	
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
	
	
	/*	@RequestMapping(value = "/changecount", method = RequestMethod.POST)
	public String changeGoodCountInBucket(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "count") Integer count, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User user = (User)request.getAttribute("user");
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		buck.changeCount(Serv.getById(goodid), count);
		sess.setAttribute("currbuck", buck);
	//	map.put("section", "bucket");
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum =calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("bucketsize", buck.getSize());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
		return "bucket";
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
//		map.put("section", "bucket");
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
		return "bucket";
	}
	
	@RequestMapping(value = "/bucket", method = RequestMethod.POST)
	public String addBucket(@RequestParam (value = "goodid") Integer goodid, Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
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
	//	map.put("section", "bucket");
		return "bucket";
		
	}*/
	
	
	
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


