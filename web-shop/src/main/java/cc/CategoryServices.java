package cc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.AnonimBuck;
import Model.Bucket;
import Model.Category;
import Model.CategoryService;
import Model.CommentService;

import Model.FileEntity;
import Model.GoodCollection;
import Model.GoodItem;
import Model.GoodItemService;
import Model.Item;
import Model.NameComparator;
import Model.News;
import Model.OrderRow;
import Model.OrderSumCalculator;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.PhoneService;
import Model.PopularityComparator;
import Model.PriceComparator;
import Model.PriceFilter;
import Model.PropSegment;
import Model.Property;
import Model.RatingComparator;
import Model.SearchForm;
import Model.Town;
import Model.TownService;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueBean;

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
	
//	private User user;
//	private AnonimBuck bucket;
	
/*	@RequestMapping(method = RequestMethod.GET)
    public String listCategory(@PathVariable (value = "catId") Integer id, Map<String, Object> map, HttpServletRequest request) {
		Category cat;
		
		List<Category> roots = catServ.getRootCategories();
		setSubCategs(roots,4);
		
		map.put("good", new GoodItem());
		Item y = catServ.getCategoryById(id);
		List<GoodItem> goods = new ArrayList<GoodItem>();		
		Iterator it = new catServ.CompositeIterator(y);
		String currenturl = request.getServletPath();
		System.out.println("Текущий УРЛ:"+currenturl);
		map.put("currenturl", currenturl);
		while(it.hasNext()){
			Item item = (Item) it.next();
			
			if(item instanceof Category){System.out.println(((Category) item).getId() + "   "+  ((Category) item).getName());}
			if(item instanceof GoodItem){
			GoodItem good = (GoodItem)item;
			System.out.println(good.getId() + "    "+good.getName() + "   " +good.getPrice());
			goods.add(good);
			}
		}
		if(y instanceof Category){
			cat = (Category)y;
			List<ParamContainer> params = new ArrayList<ParamContainer>();
			List<Property> props = cat.getProps();
			for(Property prop: props){
				ParamContainer param = new ParamContainer();
				param.setPropid(prop.getId());
				param.setPropname(prop.getName());
				for(Value val:prop.getVal()){
				param.addParam(val.getValue());
				}
				params.add(param);
			}
			map.put("params", params);
			ParamFilterBean filter = new ParamFilterBean();
			filter.setParams(params);
			map.put("paramfilter", filter);
		}
		map.put("goodList", goods);
		map.put("catList", roots);
		map.put("currentCatList", catServ.listCategoryById(id));
		map.put("paramcontainer", new ParamContainer());
		map.put("catid", id);
		map.put("searchForm", new SearchForm());
		
        return "bb";
    }*/
	
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
	//		goods.addAll(notsorted);	
		}
		else{
			notsorted = catServ.listGoodsPage(cat, null, rule, 0, PAGE_SIZE);
			System.out.println(notsorted.size());
	//		goods.addAll(notsorted);			
		
		}
		
		
		map.put("goodList", notsorted);
		return "filterResults";
		
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
//		Category cat = catServ.getCategoryById(catid);
		List<Category> cats = catServ.listCategoryById(catid);
//		setSubCategs(cats,2);
		map.put("subcats", cats);
		for(Category c: cats){
			System.out.println(c.getName());
		}
		
		
		return "commsection";
		
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
//		System.out.println(cat.getParent_id());
		if(cat!=null && cat.getParent_category().getId()!=0){
			
		
		Category current = cat;
		while(current.getParent_category().getId()!=0){
			current=catServ.getById(current.getParent_category().getId());
		//	System.out.println(current.getTitle());
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
		return "newsList";
		
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
//		for(int i = 0; i < g.getGoods().size(); i++){System.out.println(g.getGoods().get(i));}
		OrderSumCalculator calc = new OrderSumCalculator(g);
//		bucketrows = calc.createReport();
//		for(OrderRowBean b:bucketrows){System.out.println(b.getGood().getName()+"    "+b.getGoodcount());}
//		Iterator iter = calc.getOrdmap().entrySet().iterator();
//		Entry<GoodCollection, Double> entry = (Entry<GoodCollection, Double>)iter.next();
//		double sum = entry.getValue();
		double sum = calc.getSumValue();
		System.out.println("Сумма: "+ sum);
//		map.put("goods", new GoodItem());
		map.put("buckrows", g.getRows());
//		map.put("goodList", goods);
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("goodid", goodid);
		sess.setAttribute("usr", t);
		sess.setAttribute("currbuck", buck);
//		sess.setAttribute("currgoods", usergoods);
		
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




