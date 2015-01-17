package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import Model.Category;
import Model.CategoryService;
import Model.CommentService;

import Model.BasicConfigurationService;
import Model.GoodItem;
import Model.GoodItemService;
import Model.PageGroupService;
import Model.PageService;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.PhoneService;
import Model.Producer;
import Model.ProducerService;
import Model.PropSegment;
import Model.Property;
import Model.SearchForm;
import Model.TownService;
import Model.URLService;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueBean;

@Controller
@RequestMapping(value = "/catalog/**/pagin/{pgnum}")
public class CategoryController {
	public static final Integer PAGE_SIZE = 16;
	private Logger logger = LoggerFactory.getLogger(CategoryController.class);
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
	
	@Autowired
	private ProducerService prodServ;

	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	
	@RequestMapping(method = RequestMethod.GET)
    public String listCategory(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
					
			
			String restOfTheUrl = (String) request.getAttribute(
			        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			String caturl = URLService.getUrlPart(restOfTheUrl, "catalog", "pagin");
			System.out.println("Наш урллллл "+caturl);
			
			Category cat = catServ.getCategiryByUrl(caturl);
			
			map.put("good", new GoodItem());

			List<Category> subcategs  = cat.getSubcategs();
			List<GoodItem> goods = new ArrayList<GoodItem>();		
		
			String currenturl = request.getServletPath();
				
			Set<GoodItem> lastgoods = Serv.getLastLoaded(6);
			System.out.println(lastgoods.size());
			map.put("lastgoods", lastgoods);
			map.put("currenturl", currenturl);
			map.put("hotnumbs", nmbServ.getHotNumbers());
			map.put("costlesslines", nmbServ.getCostlessLines());
//			map.put("catList", roots);
//			map.put("currentCatList", roots);
			map.put("paramcontainer", new ParamContainer());
			map.put("catid", cat.getId());
			map.put("category", cat);
			Integer catgoodscount = catServ.getGoodsCount(cat);
			map.put("catgoodscount", catgoodscount);
			map.put("residenttowns", twnServ.getResidentTowns());
			map.put("page", cat.getPage());
			map.put("searchForm", new SearchForm());
			
			List<Category> catstack = new ArrayList<Category>();
			catServ.fillCategoryStack(catstack, cat);
			Collections.reverse(catstack);
			
			
			map.put("pathtocategory", catstack);
			
			
			
			if(cat.getIsFinalCategory()){
				List<ParamContainer> params = new ArrayList<ParamContainer>();
				List<PropSegment> segments = cat.getPropsegments();
				for(PropSegment segm: segments){
					for(Property prop: segm.getProperties()){
						ParamContainer param = new ParamContainer();
						param.setPropid(prop.getId());
						param.setPropname(prop.getName());
						param.setMainvalscount(prop.getMainvalscount());
						param.setPriority(prop.getPriority());
						for(Value val:prop.getVal()){
							ValueBean valbean = new ValueBean(val);
							param.addParam(valbean);
						}
						params.add(param);
					}
				}
				

				ParamFilterBean filter = new ParamFilterBean();
				filter.setParams(params);
				
		//		goods = cat.getGoods();
				Integer end = pgnum*PAGE_SIZE;
				Integer begin = end - PAGE_SIZE;
				System.out.println(begin+"  "+end);
				
				GoodItem.OrderingRules ordering = (GoodItem.OrderingRules) sess.getAttribute("GoodOrderingRule");
				if(ordering==null)
				{
					ordering = GoodItem.OrderingRules.NAME;
				}
				
				List<GoodItem> pagegoods = new ArrayList<GoodItem>();
				Map<Integer, ParamFilterBean> userfilters = (Map<Integer, ParamFilterBean>) sess.getAttribute("userfilters");
				if(userfilters!=null && userfilters.get(cat.getId())!=null)
				{
					ParamFilterBean paramfilter = userfilters.get(cat.getId());
					pagegoods = catServ.listGoodsPage(cat, paramfilter, ordering , begin, end);
					try {
						filter = filter.copyParamVals(paramfilter);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					
					
					
					
				}
				else{
					pagegoods = catServ.listGoodsPage(cat, null, ordering , begin, end);
				}
				
				map.put("paramfilter", filter);
				Integer pagecount = Math.round(catgoodscount/PAGE_SIZE);
				map.put("pagecount", pagecount);
				map.put("maxPrice", catServ.getExtremePrice(cat, "max"));
				map.put("minPrice", catServ.getExtremePrice(cat, "min"));
		//		for(GoodItem g: goods){
		//			System.out.println(g.getName()+"      " + g.getAverage_rating());
		//			g.setTotalVotes();
		//		}
				
				
				
				System.out.println("ewgewgwe    "+pgnum);
				map.put("currentpage", pgnum);
				map.put("goodList", pagegoods);
				
				return "template/finalcategory";
				
			}
			
				Set<Category> allsubcategs = new TreeSet<Category>();
	//			setSubCategs(subcategs,2);
				
				for(Category c: subcategs){
		//			System.out.println(c.getName());
					allsubcategs.addAll(c.getSubcategs());
				}
				
				for(Category c: allsubcategs){
					System.out.println(c.getName());
				}
				
				
				map.put("allsubcategs", allsubcategs);
				map.put("subcategs", subcategs);
				map.put("subcategscount", subcategs.size());
				return "template/subcategory";
	    }


	@RequestMapping(method = RequestMethod.POST, headers="Accept=*/*")
	public String filterGood(@RequestBody ParamFilterBean parambean, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws CloneNotSupportedException{
		
		
		User user = (User) request.getAttribute("user");	
		
		Map<Integer, ParamFilterBean> filters = (Map<Integer, ParamFilterBean>) sess.getAttribute("userfilters");
		if(filters == null){
			filters = new TreeMap<Integer, ParamFilterBean>();
		}

		
		
		Category cat = catServ.getById(parambean.getCatid());
		Integer catgoodscount = catServ.getGoodsCount(cat);
		map.put("catgoodscount", catgoodscount);
		
		
		List<String> names = new ArrayList<String>();
		if(parambean.getPricefilter() == null){System.out.println("NULLL");}
		
		GoodItem.OrderingRules ordering = (GoodItem.OrderingRules) sess.getAttribute("GoodOrderingRule");
		if(ordering==null)
		{
			ordering = GoodItem.OrderingRules.NAME;
		}
		System.out.println("ПРОПФИЛТЕР :");		
		List<GoodItem> goods= catServ.propertyFilter(parambean, ordering, 0, PAGE_SIZE);
		
		for(GoodItem g:goods){
			names.add(g.getName());
			System.out.println(g.getName() + "     " + g.getId());
		}
		
		map.put("catgoodscount", goods.size());
			
		System.out.println("ПОТЕНЦИАЛЬНЫЙ ПРОПФИЛТЕР :");		
		for(ParamContainer t: parambean.getParams()){
			System.out.println(" Свойство "+t.getPropname()+" и его значения:");
			for(ValueBean f:t.getVals()){
				if(f.getValue()==null){
					System.out.println("ID свойства "+t.getPropid()+"  АЙДИ значения "+f.getId()+"     "+f.getValue());
					Long count = catServ.getUnderFilterGoodsCount(parambean, null, t.getPropid(),f);
					f.setCount(count);
					f.setIschecked(false);
					System.out.println("Значение "+f.getValue()+"Количество: "+f.getCount());
				}
				else{
					f.setIschecked(true);
				}
					
			}
		}
		System.out.println("В итоге: ");
		for(ParamContainer t: parambean.getParams()){
			System.out.println(t.getPropid()+"     "+t.getPropname()+"     "+t.getValcount());
			for(ValueBean f:t.getVals()){
				System.out.println(f.getId()+"     "+f.getValue()+"     "+f.getCount()+"    "+f.getIschecked());
					
			}
		
		}
		filters.put(parambean.getCatid(), parambean);
		sess.setAttribute("userfilters", filters);
		Integer gdcount = goods.size();
		Integer pagecount = Math.round(gdcount/PAGE_SIZE);
		System.out.println("Quantity of pages!! "+pagecount);
		map.put("paramfilter", parambean);
		map.put("catgoodscount", gdcount);
		map.put("pagecount", pagecount);
		map.put("currentpage", 1);
		map.put("category", cat);
		map.put("goodList", goods);
		return "template/filterresults";
	}
	
	
	@RequestMapping(value = "/producergoods", method = RequestMethod.POST)
	public String producerGoods(@RequestParam(value="prodid") Integer prodid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws CloneNotSupportedException{
		Producer pr = prodServ.getById(prodid);
		map.put("prodgoods", pr.getGoods());
		map.put("section", "prodgoods");
		return "template/brands";
		
	}
	
	
	
		
	
	

	

}





