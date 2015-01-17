package controllers;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import Model.service.AnonimEmailService;
import Model.entity.AnonimEmailWatcher;
import Model.service.CategoryService;
import Model.entity.Comment;
import Model.service.CommentService;
import Model.others.CategoryCompareGoods;
import Model.others.CompareGoodsSet;
import Model.entity.GoodItem;
import Model.service.GoodItemService;
import Model.service.GoodStateService;
import Model.service.OrderService;
import Model.service.PropertyService;
import Model.service.TownService;
import Model.entity.User;
import Model.service.UserService;
import Model.entity.Value;
import Model.service.ValueService;
import Model.service.VoteService;

@Controller
@RequestMapping("/good/{goodid}/{section}")
class GoodItemController {
	
	
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
		return "template/commsection";
		
		
		
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
	
		
		return "template/comments";
	}
	
	
	
	@RequestMapping(value = "/addanswer", method = RequestMethod.POST)
	public String addAnswer(@RequestParam (value = "goodid") Integer goodid, @RequestParam (value = "text") String text, @RequestParam (value = "parentcomm_id") Integer parentcomm_id, @RequestParam (value = "section") String section, HttpSession sess, Map<String, Object> map) {
		map.put("section", section);
		
		GoodItem g =Serv.getById(goodid);
		Comment com = new Comment();
		User user = authorizeUser();
		if(user!=null){
			com.setAuthor(user);
			}
		if(section.equals("add")){
		com.setText(text);
		com.setParent_comment(comServ.getById(parentcomm_id));
		com.setDatetime(new Date(System.currentTimeMillis()));
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
		
		return "template/commsection";
	}

	
	
	@RequestMapping(value = "/addresponce", method = RequestMethod.GET)
	public String addResponce(@RequestParam (value = "id") Integer goodid, @RequestParam (value = "section") String elemid, Map<String, Object> map) {
		
		map.put("section", "respblock");
		map.put("goodid", goodid);
		return "template/commsection";
		
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
		
		return "template/goodparams";
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

				  username = (userdet).getUsername();
				  if(isAuthorized){
					  user = usrServ.getUsersByEmail(username).get(0);
				  }
				  
				} else {
				 username = principal.toString();
				 isAuthorized = false;
				}
			return user;
	}
	
}


