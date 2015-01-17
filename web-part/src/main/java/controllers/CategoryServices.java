package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Model.dto_beans.ParamFilterBean;
import Model.entity.Category;
import Model.entity.GoodItem;
import Model.entity.News;
import Model.entity.User;
import Model.others.AnonimBuck;
import Model.others.GoodCollection;
import Model.others.Item;
import Model.service.OrderSumCalculator;
import Model.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = {"/category/{catId}/pagin/{pgnum}"})
public class CategoryServices {
	public static final Integer PAGE_SIZE = 16;
	private Logger logger = LoggerFactory.getLogger(CategoryServices.class);
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private TownService twnServ;
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private PhoneService nmbServ;

	
	public void setSessionParameters(HttpServletRequest request, HttpSession sess){
		
			
	}
	
	
	
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String sortGoods(@RequestParam(value = "sortid") Integer sortid, @RequestParam(value = "catid") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		GoodItem.OrderingRules rule = GoodItem.OrderingRules.NAME;
		switch(sortid){
		case 0: rule = GoodItem.OrderingRules.POPULARITY;  break;
		case 1: rule = GoodItem.OrderingRules.NAME; break;
		case 2: rule = GoodItem.OrderingRules.RATING; break;
		case 4: rule = GoodItem.OrderingRules.PRICEDOWN; break;
		
		case 3: rule = GoodItem.OrderingRules.PRICEUP; break;
		}
		
		sess.setAttribute("OrderingRule", rule);
		Category cat = catServ.getById(catid);
		Map<Integer, ParamFilterBean> userfilters = (Map<Integer, ParamFilterBean>) sess.getAttribute("userfilters");
		
		List<GoodItem> notsorted = new ArrayList<GoodItem>();
		if(userfilters!=null && userfilters.get(cat.getId())!=null)
		{
			ParamFilterBean n = userfilters.get(cat.getId());
			notsorted = catServ.listGoodsPage(cat, n, rule, 0, PAGE_SIZE);
			System.out.println(notsorted.size());
		}
		else{
			notsorted = catServ.listGoodsPage(cat, null, rule, 0, PAGE_SIZE);
			System.out.println(notsorted.size());

		}
		
		
		map.put("goodList", notsorted);
		return "template/filterresults";
		
	}
	
	public void fillCategoryStack(List<Category> st, Category cat){
		if(cat.getParent_category()!=null){
		st.add(cat); 
		
		Integer id = cat.getParent_category().getId();
		if(id<=0){
			return;
		}
		
		fillCategoryStack(st, catServ.getById(id));
		}
	}

	
	@RequestMapping(value = "/listsubcategsection", method = RequestMethod.POST)
    public String getSubcategSection(@RequestParam (value = "cat_id") Integer catid, @RequestParam (value = "par_id") Integer parentid, @RequestParam (value = "section") String section, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		map.put("section", section);
		List<Category> cats = catServ.listCategoryById(catid);
		map.put("subcats", cats);
		for(Category c: cats){
			System.out.println(c.getName());
		}
		
		
		return "template/commsection";
		
	}
	
	@RequestMapping(value = "/news")
    public String getNews(@PathVariable (value = "catId") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		List<Category> roots = catServ.getRootCategories();
		setSubCategs(roots,4);
		map.put("catList", roots);
		Category cat = catServ.getById(catid);
		map.put("category", cat);
		List<News>nws = cat.getCatnews();
		System.out.println(cat.getName());
		if(cat!=null && cat.getParent_category().getId()!=0){
			
		
		Category current = cat;
		while(current.getParent_category().getId()!=0){
			current=catServ.getById(current.getParent_category().getId());
		}
		map.put("current", current);
		}
		
		else{
			List<News> allcatnews = new ArrayList<News>();
			
			CompositeIterator iter = new CompositeIterator(cat);
			while(iter.hasNext()){
				Item n = (Item)iter.next();
				if(n instanceof News){
					News nw = (News)n;
					System.out.println(nw.getTitle());
					allcatnews.add(nw);
				}
				
				
				
			}
			nws=allcatnews;
			
		}
		map.put("news", nws);
		Set<GoodItem> leaders = Serv.getSalesLeaders(5);
		map.put("leaders", leaders);
		return "template/newsList";
		
	}
	
	
	
	
	@RequestMapping(value = "/bucket", method = RequestMethod.POST)
	public String showBucket(@RequestParam (value = "goodid") Integer goodid, Map<String, Object> map,  HttpSession sess) {
		System.out.println("reached!");
		User t = authorizeUser();
		GoodItem good = Serv.getById(goodid);
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		buck.addGood(good);

		
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum = calc.getSumValue();
		System.out.println("—ÛÏÏ‡: "+ sum);
		map.put("buckrows", g.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("usr", t);
		sess.setAttribute("currbuck", buck);

		return "buckgoodlist";
		
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
	
	
	
	
	public class CompositeIterator implements Iterator {
				
		Stack  items = new Stack();
		List<Item> nodes = new ArrayList<Item>();
		List<Item> nodelists = new ArrayList<Item>();
		
		public CompositeIterator(){}
		
		
		public CompositeIterator(Item it){
			
			if(it instanceof Category){
				Category c = (Category)it;
				List <Category> ct = new ArrayList<Category>();
				ct.add(c);
				setSubCategs(ct,5);
				nodes =c.dogetNodeList();
				nodelists = c.dogetListOfLists();
				items.push(c.createiterator(nodes,nodelists));
			}
		}
		
		public CompositeIterator(List<Item> list){
			for(Item it:list){
				if(it instanceof Category){
					Category c = (Category)it;
					List <Category> ct = new ArrayList<Category>();
					ct.add(c);
					setSubCategs(ct,5);
					nodes =c.dogetNodeList();
					nodelists = c.dogetListOfLists();
					items.push(c.createiterator(nodes,nodelists));
				}
			}
			
		}
		
		
		@Override
		public Item next() {
			if(hasNext()){
				Iterator k = (Iterator) items.peek();
				Item it = (Item) k.next();
				if(it instanceof Category){
					System.out.println(((Category) it).getName());
					Category c = (Category)it;
					nodes =c.dogetNodeList();
					nodelists = c.dogetListOfLists();
					items.push(c.createiterator(nodes,nodelists));
					this.items.push(it.createiterator(nodes,nodelists));
				}
				return it;
			}
			return null;
		}

			
				
		@Override
		public boolean hasNext() {
			if(items.isEmpty()){return false;}
			else{
				Iterator g = (Iterator) items.peek();
				if(!g.hasNext()){
					items.pop();
					return hasNext();
				}
				return true;
			}
			
			
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

	}
	

	
	
	public void setSubCategs(List<Category> cats, int levels){
		if(levels == 0){return;}
		if(cats == null && cats.isEmpty() ){return;}
		for(Category cat : cats){
			List<Category> subcats = catServ.listCategory(cat);
			cat.setSubcategs(subcats);
			setSubCategs(subcats,levels-1);
		}
		
	}
}




