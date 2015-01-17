package controllers;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.Action;
import Model.ActionService;
import Model.AdressService;
import Model.AdressType;
import Model.BasicConfiguration;
import Model.BasicConfigurationBean;
import Model.BasicConfigurationService;
import Model.Category;
import Model.CategoryService;
import Model.Comment;
import Model.CommentBean;
import Model.CommentService;
import Model.CommonUtilsService;
import Model.ConfigurationService;
import Model.CountryService;
import Model.DeliveryType;
import Model.DeliveryTypeService;
import Model.Entity;
import Model.EntityField;
import Model.EntityFieldService;
import Model.Filter;
import Model.FilterNode;
import Model.FilterNodeBean;
import Model.GoodItemService;
import Model.GoodState;
import Model.GoodStateService;
import Model.GoodStatesBean;
import Model.GoodStatusService;
import Model.GuarantieService;
import Model.ImageLoader;
import Model.ImageService;
import Model.MultiChangeBean;
import Model.MultiObjectFieldContainer;
import Model.MultiValueBean;
import Model.NewsService;
import Model.NewsTypeService;
import Model.ObjectFieldContainer;
import Model.Order;
import Model.OrderRowService;
import Model.OrderService;
import Model.PageGroupService;
import Model.PageService;
import Model.PayType;
import Model.PayTypeService;
import Model.PhoneService;
import Model.ProducerService;
import Model.PropertyService;
import Model.RangeFilterNode;
import Model.Region;
import Model.RegionService;
import Model.Role;
import Model.RoleService;
import Model.ServiceImpl;
import Model.TownService;
import Model.User;
import Model.News;
import Model.UserService;
import Model.ValueBean;
import Model.ValueService;
import Model.ValuesFilterNode;
import Model.VoteService;
import Model.filters.AdminFormat;
import Model.filters.AdminFormatService;
import Model.filters.FieldPriorityService;
import Model.filters.PropRangeFilterService;
import Model.filters.PropValueFilterService;

import java.beans.*;
import java.lang.reflect.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	public static final Integer PAGE_SIZE = 16;
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private GoodItemService Serv;
	

	@Autowired
	private UserService usrServ;
	
	@Autowired
	private CommentService comServ;
	@Autowired
	private RegionService regServ;
	@Autowired
	private CategoryService catServ;
	@Autowired
	private PropertyService propServ;
	@Autowired
	private ValueService valServ;
	
	@Autowired
	private ImageService imgServ;
	
	@Autowired
	private GoodStateService gstServ;
	
	@Autowired
	private VoteService voteServ;
	
	@Autowired
	private OrderService ordServ;
	
	@Autowired
	private OrderRowService ordrowServ;
	
	@Autowired
	private TownService twnServ;
	@Autowired
	private PayTypeService ptpServ;
	
	@Autowired
	private DeliveryTypeService dlvServ;
	
	@Autowired
	private PhoneService phnServ;
	
	@Autowired
	private AdressService adrServ;
	
	@Autowired
	private CountryService cntrServ;
	
	@Autowired
	private NewsService nwsServ;
	
	@Autowired
	private ActionService actServ;
	
	@Autowired
	private RoleService roleServ;

	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private EntityFieldService entServ;
	
	@Autowired
	private AdminFormatService adfServ;
	
	@Autowired
	private PropValueFilterService pvfServ;
	
	@Autowired
	private PropRangeFilterService prfServ;
	
	@Autowired
	private FieldPriorityService fprServ;
	
	@Autowired
	private ProducerService prodServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private GuarantieService guaServ;
	
	@Autowired
	private GoodStatusService gsttServ;
	
	@Autowired
	private ConfigurationService confServ;
	
	@Autowired
	private BasicConfigurationService bconfServ;
	
	@Autowired
	private NewsTypeService ntpServ;
	
	@RequestMapping(method = RequestMethod.GET)
	public String adminPanel(Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		
		User user = (User) request.getAttribute("user");
		
				
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Category");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(Serv.getCount()/PAGE_SIZE);
//		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
//		map.put("filter", goodfilter);
		map.put("title", "Управление структурой");
		
		BasicConfigurationBean bconf = new BasicConfigurationBean();
		if(bconfServ.getCount()>0){
			BasicConfiguration bc = bconfServ.getById(bconfServ.getLastId());
			bconf.setFromBasicConfiguration(bc);
		}
		map.put("basic", bconf);
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Category cat:catServ.getRootCategories()){
			if(cat.getPage()!=null){
			System.out.println("УСЛОВИЕ ДЛЯ КАТЕГОРИИ: " + (cat.getIsdeleted()==null || cat.getIsdeleted()!=true));
			if(cat.getIsdeleted()==null || cat.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(cat);
			container.getRefs().put("Показать на сайте", "/catalog/"+cat.getPage().getFullurl()+"/pagin/1");
			container.getRefs().put("Развернуть", "/admin/admincategory/"+cat.getId()+"");
			container.getRefs().put("Удалить", "/admin/setdeleted/Category/"+cat.getId());
			List<EntityField> fields = entServ.listByClassname("Category");
		for(EntityField entfield: fields){
			if(entfield.getIsmainpageprop()){
				System.out.println("ИМЯ КЛАССА СТОЛБЦА "+entfield.getClassname());
		/*		boolean isPrimitiveOrWrapped = 
					    ClassUtils.isPrimitiveOrWrapper(object.getClass());*/
				if(entfield.getClassname()=="Date"){
					entfield.setIsdate(true);
				}
				container.getFields().put(entfield, null);
			
			}
		}
		container.fillFieldValues();
		containers.add(container);
		}
			}
			}
		for(ObjectFieldContainer container:containers){	
			System.out.println("Категория -  "+container.getObj());
		for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
			System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
		}
		}
		map.put("containers", containers);
//		map.put("adminclassname", "Category");
		map.put("isroot", true);
		return "bb";
		
		
	
	}
	
	@RequestMapping(value = "/updatedBasicConfig", method = RequestMethod.POST)
	public String updatedPagegroup(@ModelAttribute (value = "basic") @Valid BasicConfigurationBean bconf, BindingResult bindingResult, HttpServletRequest request, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("basic", bconf);
			return "bb";
		}
		
		BasicConfiguration basic = new BasicConfiguration();
		if(bconf.getId()!=null)
			basic =  bconfServ.getById(bconf.getId());
		basic.setShopname(bconf.getShopname());
		
		if(bconf.getNewthumb()!=null){
		
		basic.setLogo(ImageLoader.loadthumb(bconf.getNewthumb(), 1, "logo"));
		}
		bconfServ.update(basic);
		map.put("result","success");
		bconf =  new BasicConfigurationBean();
		bconf.setFromBasicConfiguration(basic);
		map.put("basic", bconf);
		String referer = request.getHeader("referer");
		return "redirect:"+referer;
		
	}
	
	
	
	@RequestMapping(value = "/admincategory/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminCategories(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Category");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(catServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("title", "Управление категориями");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();

		fillobjectcontainers(containers, "Category", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "Category");
		map.put("entityurl", "/admin/admincategory/pagin/");
		map.put("section", "categories");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/admincategory/{catid}/list/{type}/pagin/{pgnum}", method = RequestMethod.GET)
    public String categoryAssociate(@PathVariable (value = "catid") Integer catid, @PathVariable (value = "pgnum") Integer pgnum, @PathVariable (value = "type") String type, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		ServiceImpl adminserv = null;
		String classname = "";
		Category cat = catServ.getById(catid);
		List list = new ArrayList();
		switch(type){
		case "goods": 
			classname = "GoodItem";
			list = cat.getGoods();
			break;
		case "news": 
			classname = "News";
			list = cat.getCatnews();
			break;
		case "subcategs": 
			classname = "Category";
			list = cat.getSubcategs();
			break;
		}
	
		Integer pagecount = Math.round(list.size()/PAGE_SIZE);
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		if(end>list.size())
			end = list.size();
		List pageitems = list.subList(begin, end);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		Iterator iter = pageitems.listIterator();
		
		while(iter.hasNext()){
			Object good = iter.next();
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			List<EntityField> fields = entServ.listByClassname(classname);
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		map.put("pagecount", pagecount);
		map.put("pgnum", pgnum);
		map.put("containers", containers);
		map.put("adminclassname", classname);
		map.put("entityurl", "/admincategory/"+catid+"/list/"+type+"/pagin/");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/admingoods/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminGoods(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("GoodItem");
		if(goodfilter==null){System.out.println("NULLfilter");}
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		System.out.println("fffsiize: "+goodfilter.getHidecols().size());
		
		Integer pagecount = Math.round(Serv.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление товарами");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
	
		System.out.println("HiddenCols^");
		for(Entry<String, String> entr:goodfilter.getHidecols().entrySet()){
			System.out.println(entr.getKey()+"     "+entr.getValue());
			
		}
		fillobjectcontainers(containers, "GoodItem", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "GoodItem");
		map.put("entityurl", "/admin/admingoods/pagin/");
		map.put("section", "goods");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminproducers/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminProducers(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Producer");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		Integer pagecount = Math.round(prodServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление производителями");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		
		fillobjectcontainers(containers, "Producer", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "Producer");
		map.put("entityurl", "/admin/adminproducers/pagin/");
		map.put("section", "goods");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminpagegroups/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminPageGroup(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("PageGroup");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		Integer pagecount = Math.round(prodServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление группами страниц");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		
		fillobjectcontainers(containers, "PageGroup", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "PageGroup");
		map.put("entityurl", "/admin/adminpagegroups/pagin/");
		map.put("section", "goods");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/adminpages/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminPages(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Page");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		Integer pagecount = Math.round(prodServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление информационными страницами");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
	//	fillobjectcontainers(containers, "Page", goodfilter, begin, end);
		
		
		
		Iterator iter = pageServ.listInfoPagesPage(goodfilter, begin, end).iterator(); 
		while(iter.hasNext()){
			Object obj = iter.next();
			Entity good = (Entity)obj;
			if(good.getIsdeleted()==null || !good.getIsdeleted()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("Page");
			boolean customorder = false;
			if(goodfilter!=null && !goodfilter.getColorder().isEmpty()){customorder = true;}
		
		for(EntityField entfield: fields){
			if(!goodfilter.getHidecols().containsKey(entfield.getPropname())){
				if(customorder)
				entfield.setPriority(goodfilter.getColorder().get(entfield.getPropname()));
				container.getFields().put(entfield, null);
			}
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		
		map.put("containers", containers);
		map.put("adminclassname", "Page");
		map.put("entityurl", "/admin/adminpages/pagin/");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminguaranties/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminGuaranties(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Guarantie");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		Integer pagecount = Math.round(guaServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление видами гарантий");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		
		fillobjectcontainers(containers, "Guarantie", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "Guarantie");
		map.put("entityurl", "/admin/adminguaranties/pagin/");
//		map.put("section", "goods");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/admingoodstatuses/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminGoodstatuses(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("GoodStatus");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		
		Integer pagecount = Math.round(gstServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
		map.put("title", "Управление статусами товара");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		
		fillobjectcontainers(containers, "GoodStatus", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "GoodStatus");
		map.put("entityurl", "/admin/admingoodstatuses/pagin/");
//		map.put("section", "goods");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminusers/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminUsers(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("User");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(usrServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("title", "Управление пользователями");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();

		fillobjectcontainers(containers, "User", goodfilter, begin, end);
		map.put("containers", containers);
		map.put("adminclassname", "User");
		map.put("entityurl", "/admin/adminusers/pagin/");
		map.put("section", "users");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/adminorders/{ordid}/setdelete", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable (value = "ordid") Integer ordid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		ordServ.setDeleteById(ordid);
		return "/admin/adminorders/pagin/1";
	}
	
	
	
	
	@RequestMapping(value = "/adminconfigurations/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminConfigurationss(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter ordfilter = getCurrentFilter(filters, "Configuration");
		
		if(ordfilter ==null){
			System.out.println("ФИЛЬТР НУЛЕВОЙ ");
			ordfilter = new Filter();
		}
		Integer pagecount = Math.round(confServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", ordfilter);
		map.put("title", "Управление шаблонами конфигурации");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
	
		
		fillobjectcontainers(containers, "Configuration", ordfilter, begin, end);
		System.out.println(confServ.getCount());
		map.put("containers", containers);
		map.put("adminclassname", "Configuration");
		map.put("entityurl", "/admin/adminconfigurations/pagin/");
		map.put("section", "configurations");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminnewstypes/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminNewstypes(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter ordfilter = getCurrentFilter(filters, "NewsType");
		
		if(ordfilter ==null){
			System.out.println("ФИЛЬТР НУЛЕВОЙ ");
			ordfilter = new Filter();
		}
		Integer pagecount = Math.round(ntpServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", ordfilter);
		map.put("title", "Управление типами новостей");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
	
		
		fillobjectcontainers(containers, "NewsType", ordfilter, begin, end);
		System.out.println(confServ.getCount());
		map.put("containers", containers);
		map.put("adminclassname", "NewsType");
		map.put("entityurl", "/admin/adminnewstypes/pagin/");
//		map.put("section", "configurations");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/adminorders/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminOrders(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter ordfilter = getCurrentFilter(filters, "Order");
		
		if(ordfilter ==null){
			System.out.println("ФИЛЬТР НУЛЕВОЙ ");
			ordfilter = new Filter();
		}
		Integer pagecount = Math.round(ordServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", ordfilter);
		map.put("title", "Управление заказами");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			
		fillobjectcontainers(containers, "Order", ordfilter, begin, end);

		map.put("containers", containers);
		map.put("adminclassname", "Order");
		map.put("entityurl", "/admin/adminorders/pagin/");
		map.put("section", "orders");
		return "adminGoodList";
		
	}
	
	
	
	public void fillobjectcontainers(List<ObjectFieldContainer> containers, String adminclassname, Filter filter, Integer begin, Integer end) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException{
		ServiceImpl adminserv = (ServiceImpl)getService(adminclassname);
		
		Iterator iter = adminserv.listPage(filter, begin, end).iterator(); 
		while(iter.hasNext()){
			Object obj = iter.next();
			Entity good = (Entity)obj;
			if(good.getIsdeleted()==null || !good.getIsdeleted()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname(adminclassname);
			boolean customorder = false;
			if(filter!=null && !filter.getColorder().isEmpty()){customorder = true;}
			
		
			
			
		for(EntityField entfield: fields){
			if(!filter.getHidecols().containsKey(entfield.getPropname())){
				if(customorder)
				entfield.setPriority(filter.getColorder().get(entfield.getPropname()));
				container.getFields().put(entfield, null);
			}
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		
		
		

	}
	
	
	
	
	public void fillmultiobjectcontainers(List<MultiObjectFieldContainer> multicontainers, String adminclassname, String joinclassname, Filter filter, Integer begin, Integer end) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IntrospectionException{
		ServiceImpl adminserv = (ServiceImpl)getService(adminclassname);
		if(joinclassname==null || joinclassname==""){}
	
		
		Iterator iter = adminserv.listPage(filter, begin, end).iterator(); 
		
		
			while(iter.hasNext()){
				Object obj = iter.next();
				Entity good = (Entity)obj;
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				MultiObjectFieldContainer multicontainer = new MultiObjectFieldContainer();	
				
				ObjectFieldContainer container1 = new ObjectFieldContainer();
				container1.setObj(good);
				CommonUtilsService.setEntityAdminReferences(container1);
				List<EntityField> fields1 = entServ.listByClassname(adminclassname);
			for(EntityField entfield: fields1){
				container1.getFields().put(entfield, null);
			}
			container1.fillFieldValues();
			
			
			ObjectFieldContainer container2 = new ObjectFieldContainer();
			
			container2.setObj(CommonUtilsService.getForeignKeyValueByTypeName(good, joinclassname));
			List<EntityField> fields2 = entServ.listByClassname(joinclassname);
			for(EntityField entfield: fields2){
				container2.getFields().put(entfield, null);
			}
			container2.fillFieldValues();
			
			
			multicontainer.getFieldcontainers().put(adminclassname,container1);
			multicontainer.getFieldcontainers().put(joinclassname,container2);
			multicontainers.add(multicontainer);
			}
			}
	

	}
	
	@RequestMapping(value = "/managecols", method = RequestMethod.POST)
	 public String hideColumn(@RequestParam(value="adminclassname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess){
		map.put("section", "managecols");
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter filter = getCurrentFilter(filters, adminclassname);
		map.put("filter", filter);
		map.put("activecols", getVisibleColumns(adminclassname, filter));
		map.put("hiddencols", filter.getHidecols());
		map.put("adminclassname",adminclassname);
		
		return "admincommsection";
	}
	
	
	public Map<String, String> getVisibleColumns(String adminclassname, Filter filter){
		List<EntityField> flds = entServ.listByClassname(adminclassname);
		Set<String> hiddencols = filter.getHidecols().keySet();
		Map<String, String> visiblecols = new TreeMap<String, String>();
		for(EntityField entfld:flds){
			if(!hiddencols.contains(entfld.getPropname())){
				visiblecols.put(entfld.getPropname(), entfld.getColname());
			}
		}
		
		return visiblecols;
		
	}
	
	
	@RequestMapping(value = "/savecolumnstructure", method = RequestMethod.POST)
	 public String saveColumnStructure(@ModelAttribute(value="hiddencols") List<String> hiddencols, @RequestParam (value = "adminclassname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IntrospectionException{
		map.put("section", "filterres");
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		Integer pgnum = 0;
		for(int i =0; i<urlparts.length; i++){
			if(i<urlparts.length-1){
			entityurl.append(urlparts[i]+"/");}
			else{
			pgnum = Integer.parseInt(urlparts[i]);
			
			}	
		}
		System.out.println("Номер страницы "+pgnum);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		System.out.println("Класс фильтра "+adminclassname);
	Filter filter = getCurrentFilter(filters, adminclassname);
		List<EntityField> flds = entServ.listByClassname(adminclassname);
		char data[] = {'"'};
		String buck = new String(data);
		System.out.println("siize: "+hiddencols.size());
		filter.getHidecols().clear();
		System.out.println("Пустая?? "+filter.getHidecols().isEmpty());
		for(String str :hiddencols){
			
			str = str.replace("[", "").replace("]", "").replace(buck, "");
			String colname = "";
			for(EntityField f:flds){
				System.out.println(str + "   " +f.getPropname());
				if(f.getPropname().equals(str)){
					System.out.println(f.getColname());
					colname = f.getColname();
					break;
				}
			}
			System.out.println("HIDDENS: "+str + "  " + colname);
			filter.getHidecols().put(str, colname);
		}
		System.out.println("Пустая2?? "+filter.getHidecols().isEmpty());
		
		System.out.println("HiddenCols^");
		for(Entry<String, String> entr:filter.getHidecols().entrySet()){
			System.out.println(entr.getKey()+"     "+entr.getValue());
			
		}
	
		System.out.println("fffsiize1: "+filter.getHidecols().size());
		
		System.out.println("Класссс "+adminclassname);
		ServiceImpl adminserv = getService(adminclassname);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(filter, begin, end);
		List allgoods = adminserv.listPage(filter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		filter.setFilteredgoods(allgoods);
		System.out.println("fffsiize2: "+filter.getHidecols().size());
		
	
		if(!filter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, adminclassname, filter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, adminclassname, filter.getJoinclassname(), filter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		System.out.println("fffsiize3: "+filter.getHidecols().size());
		sess.setAttribute("adminfilters", filters);
		map.put("filter", filter);
		map.put("adminclassname", adminclassname);
		map.put("entityurl", entityurl.toString());
		
		return "admincommsection";
		
	}
	
	@RequestMapping(value = "/savecolumnorder", method = RequestMethod.POST)
	 public @ResponseBody String saveColumnOrder(@ModelAttribute(value="cols") List<String> cols, @RequestParam (value = "adminclassname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess){
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter filter = getCurrentFilter(filters, adminclassname);
		char data[] = {'"'};
		String buck = new String(data);
		filter.getColorder().clear();
		int i = 0;
		for(String kk: cols){
			
			kk = kk.replace("[", "").replace("]", "").replace(buck, "");
			System.out.println(i+" "+kk);
			filter.getColorder().put(kk,i);
			i++;
		}
		
		for(Entry<String, Integer> ert: filter.getColorder().entrySet()){
		System.out.println("ORDCOLS  "+ert.getKey()+"   "+ert.getValue());
		}
		sess.setAttribute("adminfilters", filters);
		
		return "1";
		
	}
	
	
	@RequestMapping(value = "/hidecolumn", method = RequestMethod.POST)
	 public String hideColumn(@RequestParam(value="propname") String propname, @RequestParam(value="pgnum") Integer pgnum, @RequestParam(value="adminclassname") String adminclassname, @RequestParam(value="coladmintype") String coladmintype, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IntrospectionException{
		map.put("section", "filterres");
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}

		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter filter = getCurrentFilter(filters, adminclassname);
		String colname = "";
		if(!filter.getMulticlasstable()){
		List<EntityField> cols = entServ.listByClassname(adminclassname);
		for(EntityField entfld:cols){
			if(entfld.getPropname().equals(propname)){
				colname = entfld.getColname(); break;
			}
		}
		}
		filter.getHidecols().put(propname, colname);
		sess.setAttribute("adminfilters", filters);
		ServiceImpl adminserv = getService(adminclassname);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(filter, begin, end);
		List allgoods = adminserv.listPage(filter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		filter.setFilteredgoods(allgoods);
//		sess.setAttribute("adminfilters", filters);
/*		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(GoodItem good:pagegoods){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
		for(String fld:goodfields){
			container.getFields().add(good.getClass().getDeclaredField(fld));
		}
		}*/
		
		if(!filter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, adminclassname, filter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, adminclassname, filter.getJoinclassname(), filter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		
		
/*		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Object good:adminserv.listPage(goodfilter,begin, end)){
			Entity ent = (Entity)good;
			if(ent.getIsdeleted()==null || !ent.getIsdeleted()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname(adminclassname);
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
//		map.put("goods", pagegoods);
		}*/
//		map.put("containers", containers);
		map.put("filter", filter);
//		map.put("classname", classname);
		map.put("adminclassname", adminclassname);
		map.put("entityurl", entityurl.toString());
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+adminclassname);
		return "admincommsection";
		
	}
	
	
	@RequestMapping(value = "/admincomments/pagin/{pgnum}", method = RequestMethod.GET)
    public String admiComments(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter ordfilter = filters.get("Comment");
		if(ordfilter ==null){
			ordfilter = new Filter();
		}
		Integer pagecount = Math.round(comServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("filter", ordfilter);
		map.put("title", "Управление комментариями");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		
		fillobjectcontainers(containers, "Comment", ordfilter, begin, end);
	
		map.put("containers", containers);
		map.put("adminclassname", "Comment");
		map.put("entityurl", "/admin/admincomments/pagin/");
		map.put("section", "comments");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/updaterole/{roleid}", method = RequestMethod.GET)
	  public String updateRole(@PathVariable (value = "roleid") String roleid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("role", roleServ.getById(roleid));
		
		map.put("section", "update");
		
		return "adminRole";
		
	}
	
	@RequestMapping(value = "/updatecomment/{commid}", method = RequestMethod.GET)
	  public String updateRole(@PathVariable (value = "commid") Integer commid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		CommentBean commentbean = new CommentBean();
		commentbean.constructfromcomment(comServ.getById(commid));
		map.put("comment", commentbean);
		
		map.put("section", "update");
		System.out.println("Менять коммент "+commid);
		System.out.println();
		return "updatecomment";
		
	}
	

	
	
	
	@RequestMapping(value = "/updatedeltype/{dtpid}", method = RequestMethod.GET)
	  public String updateDelType(@PathVariable (value = "dtpid") Integer dtpid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("deltype", dlvServ.getById(dtpid));
		List<String> types = new ArrayList<String>();
		
		map.put("types", AdressType.values());
		map.put("section", "update");
		
		return "adminDeltype";
		
	}

	
	
	@RequestMapping(value = "/updatepaytype/{ptpid}", method = RequestMethod.GET)
	  public String updatePayType(@PathVariable (value = "ptpid") Integer ptpid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("paytype", ptpServ.getById(ptpid));
		
		map.put("section", "update");
		
		return "adminPaytype";
		
	}
	

	@RequestMapping(value = "/updatedcomment", method = RequestMethod.POST)
	  public String updatedComment(@ModelAttribute (value = "comment") CommentBean comment, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ParseException {
		Comment r = comServ.getById(comment.getId());
		r.constructfrombean(comment);
		comServ.update(r);
		
		return "redirect:"+request.getHeader("referer");
		
	}
	
	@RequestMapping(value = "/updatedrole", method = RequestMethod.POST)
	  public String updatedRole(@ModelAttribute (value = "role") Role role, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Role r = roleServ.getById(role.getAuthority());
		r.setDescription(role.getDescription());
		roleServ.update(r);
		
		return "redirect:"+request.getHeader("referer");
		
	}
	
	@RequestMapping(value = "/updateddeltype", method = RequestMethod.POST)
	  public String updatedDelType(@ModelAttribute (value = "deltype") DeliveryType deltype, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		DeliveryType r = new DeliveryType();
		if(deltype.getId()!=null)
		r = dlvServ.getById(deltype.getId());
		r.setName(deltype.getName());
		r.setShortname(deltype.getShortname());
		r.setType(deltype.getType());
		dlvServ.update(r);
		return "redirect:"+request.getHeader("referer");
		
	}
	
	@RequestMapping(value = "/updatedpaytype", method = RequestMethod.POST)
	  public String updatedPayType(@ModelAttribute (value = "paytype") PayType paytype, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		PayType r = ptpServ.getById(paytype.getId());
		r.setName(paytype.getName());
		r.setShortname(paytype.getShortname());
		ptpServ.update(r);
		return "redirect:"+request.getHeader("referer");
		
	}
	
	@RequestMapping(value = "/adminorders/{ordid}/list/{type}/pagin/{pgnum}", method = RequestMethod.GET)
	  public String adminOrderLists(@PathVariable (value = "ordid") Integer ordid, @PathVariable (value = "pgnum") Integer pgnum, @PathVariable (value = "type") String type, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User) request.getAttribute("user");
		ServiceImpl adminserv = null;
		String classname = "";
		Order ord = ordServ.getById(ordid);
		List list = new ArrayList();
		switch(type){
		case "items": 
			classname = "OrderRow";
			list = new ArrayList(ord.getOrderrows());
			break;
		case "phones": 
			classname = "PhoneNumber";
			list = ord.getClient_phones();
			break;
	
		}
		
		
		
		Integer pagecount = Math.round(list.size()/PAGE_SIZE);
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		if(end>list.size())
			end = list.size();
		List pageitems = list.subList(begin, end);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		Iterator iter = pageitems.listIterator();
		
		while(iter.hasNext()){
			Object good = iter.next();
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			List<EntityField> fields = entServ.listByClassname(classname);
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		map.put("pagecount", pagecount);
		map.put("pgnum", pgnum);
		map.put("containers", containers);
		map.put("adminclassname", classname);
		map.put("entityurl", "/admincategory/"+ordid+"/list/"+type+"/pagin/");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/adminnews/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminNews(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter goodfilter = filters.get("News");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(Serv.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("pgnum", pgnum);
		map.put("title", "Управление новостями");
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(News good:nwsServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("News");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		map.put("containers", containers);
		map.put("adminclassname", "News");
		map.put("entityurl", "/admin/adminnews/pagin/");
		map.put("section", "news");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminactions/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminActions(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter goodfilter = filters.get("Action");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(Serv.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Action good:actServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("Action");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		map.put("containers", containers);
		map.put("adminclassname", "Action");
		map.put("entityurl", "/admin/adminactions/pagin/");
		map.put("section", "actions");
		return "adminGoodList";
		
	}
	
	@RequestMapping(value = "/admindeltypes/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminDeltypes(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter goodfilter = filters.get("DeliveryType");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(dlvServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(DeliveryType good:dlvServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("DeliveryType");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		map.put("containers", containers);
		map.put("adminclassname", "DeliveryType");
		map.put("entityurl", "/admin/admindeltypes/pagin/");
		map.put("section", "deltypes");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminpaytypes/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminPaytypes(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter goodfilter = filters.get("PayType");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(ptpServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(PayType good:ptpServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("PayType");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}
		map.put("containers", containers);
		map.put("adminclassname", "PayType");
		map.put("entityurl", "/admin/adminpaytypes/pagin/");
		map.put("section", "paytypes");
		return "adminGoodList";
		
	}
	
	
	@RequestMapping(value = "/adminorderrows/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminOrderrows(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IntrospectionException {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		Filter goodfilter = filters.get("OrderRow");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(ordrowServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("pgnum", pgnum);
		Filter f = new Filter();
		goodfilter.setMulticlasstable(true);
		goodfilter.setJoinclassname("Order");
		filters.put("OrderRow", goodfilter);
		sess.setAttribute("adminfilters", filters);
/*		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(OrderRow good:ordrowServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("OrderRow");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}*/
		
		
		
		List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
	
		/*for(OrderRow good:ordrowServ.listPage(goodfilter,begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				MultiObjectFieldContainer multicontainer = new MultiObjectFieldContainer();	
			ObjectFieldContainer container1 = new ObjectFieldContainer();
			container1.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container1);
			List<EntityField> fields1 = entServ.listByClassname("OrderRow");
		for(EntityField entfield: fields1){
			container1.getFields().put(entfield, null);
		}
		container1.fillFieldValues();
		ObjectFieldContainer container2 = new ObjectFieldContainer();
		container2.setObj(good.getOrd());
		List<EntityField> fields2 = entServ.listByClassname("Order");
		for(EntityField entfield: fields2){
			container2.getFields().put(entfield, null);
		}
		container2.fillFieldValues();
		
		multicontainer.getFieldcontainers().put("OrderRow",container1);
		multicontainer.getFieldcontainers().put("Order",container2);
		multicontainers.add(multicontainer);
		}
		}*/
		
		
		fillmultiobjectcontainers(multicontainers, "OrderRow", "Order", goodfilter, begin, end);
		
		int i =0;
		for(MultiObjectFieldContainer gg:multicontainers){
			System.out.println(" ");
			System.out.println("Строка "+i);
			for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
				System.out.println("Сегмент класса "+entr.getKey());
				for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
					System.out.println("Свойство: "+en.getKey().getPropname());
					System.out.println("Значение: "+en.getValue());
				}
			}
			i++;
		}
	
//		sess.setAttribute(name, value)
		
//		map.put("containers", containers);
		map.put("multicontainers", multicontainers);
		map.put("adminclassname", "OrderRow");
		map.put("entityurl", "/admin/adminorderrows/pagin/");
		map.put("section", "orderrows");
		return "multiClassList";
		
	}
	
	
/*	@RequestMapping(value = "/admincategory/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminCategories(@PathVariable (value = "pgnum") Integer pgnum,Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Category.OrderingRules rule = Category.OrderingRules.NAME;
		map.put("categories", catServ.listPage(rule, begin, end));
		return "goodslist";
		
	}
	
	@RequestMapping(value = "/adminusers/pagin/{pgnum}", method = RequestMethod.GET)
    public String adminUsers(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		User.OrderingRules rule = User.OrderingRules.NAME;
		System.out.println(begin+"  "+end);
		map.put("users", usrServ.listPage(rule, begin, end));
		return null;
		
	}*/
	
	
	public Filter initCurrentFilter(Map<String, Filter> filters, String adminclassname, String coladmintype) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get(adminclassname);
		if(goodfilter ==null){
			
			goodfilter = new Filter();
		}
		goodfilter.setCompositefilter(false);
		if(!adminclassname.equals(coladmintype)){
			goodfilter.setMulticlasstable(true);
			goodfilter.setColadmintype("Model."+coladmintype);
			goodfilter.setCompositefilter(true);

			Field fld = CommonUtilsService.getForeignKeyByTypeName(adminclassname, coladmintype);
			String fname = fld.getName();
			goodfilter.setJoinfieldname(fname);
			goodfilter.setJoincolectionname(CommonUtilsService.getJoinCollectionFieldName(coladmintype, adminclassname));
			
		}
		
		return goodfilter;
	}
	
	public Filter getCurrentFilter(Map<String, Filter> filters, String adminclassname){
		
		Filter goodfilter = filters.get(adminclassname);
		System.out.println(adminclassname + " фильтры "+goodfilter);
		if(goodfilter ==null){
			
			goodfilter = new Filter();
			filters.put(adminclassname, goodfilter);
		}
	
		return goodfilter;
	}
	
	
	@RequestMapping(value = "/dofilter", method = RequestMethod.POST)
    public String doFilter(@RequestBody FilterNodeBean filternodebean, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, IntrospectionException {
		
System.out.println("Сортируется ли ?? "+filternodebean.getIssortcriteria());
		
		map.put("section", "filterres");
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}
		
//		Filter goodfilter = (Filter) sess.getAttribute("admingoodfilter");
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
	/*			Filter goodfilter = filters.get(filternodebean.getAdminclassname());
		if(goodfilter ==null){
			
			goodfilter = new Filter();
		}
		goodfilter.setCompositefilter(false);
		if(!filternodebean.getAdminclassname().equals(filternodebean.getColadmintype())){
			goodfilter.setMulticlasstable(true);
			goodfilter.setColadmintype("Model."+filternodebean.getColadmintype());
			goodfilter.setCompositefilter(true);

			Field fld = CommonUtilsService.getForeignKeyByTypeName(filternodebean.getAdminclassname(), filternodebean.getColadmintype());
			String fname = fld.getName();
			goodfilter.setJoinfieldname(fname);
			goodfilter.setJoincolectionname(CommonUtilsService.getJoinCollectionFieldName(filternodebean.getColadmintype(), filternodebean.getAdminclassname()));
			
		}*/
		Filter goodfilter = initCurrentFilter(filters,filternodebean.getAdminclassname(), filternodebean.getColadmintype());
		System.out.println(goodfilter.getMulticlasstable()+"  Joincolumnname "+goodfilter.getJoinfieldname() + " adminclass "+filternodebean.getAdminclassname()+"   "+filternodebean.getColadmintype());
//		goodfilter.setHasvaluefilter(filternodebean.getHasvaluefilter());
		/*	if(goodfilter.getJoinfieldname()!=null){
		goodfilter=null;
		goodfilter.getClass();}*/
		System.out.println("ФИЛЬТРРРРР КОМПОЗИТНЫЙ ?? "+goodfilter.getCompositefilter());
		ServiceImpl adminserv = getService(filternodebean.getAdminclassname());
		
		
		if(filternodebean.getIssortcriteria()==null){
			filternodebean.setIssortcriteria(false);
		}
		else if(filternodebean.getIssortcriteria()){
			goodfilter.getSortprops().put(filternodebean.getPropname(), filternodebean.getColname());
		}
		
		List<Object> rangelist = new ArrayList<Object>();
		if(filternodebean.getHasrangefilter()){
		rangelist.add(filternodebean.getMin());
		rangelist.add(filternodebean.getMax());
		}
		
		FilterNode rangenode = new RangeFilterNode(rangelist);
		rangenode.setIsSet(filternodebean.getHasrangefilter());
		
		List<Object> list = new ArrayList<Object>();
	
		if(filternodebean.getHasvaluefilter())
		fillvaluefilter(list, filternodebean);
		
		
		FilterNode valnode = new ValuesFilterNode(list);
		valnode.setIsSet(filternodebean.getHasvaluefilter());
		
		
		
		Map<String,Set<FilterNode>> propfilter = new TreeMap<String,Set<FilterNode>>();
		if(!goodfilter.getCompositefilter()){
			propfilter = goodfilter.getPropfilters();
		}
		else{
			propfilter = goodfilter.getJoinfilters();
		}
		Set<FilterNode> nodes = propfilter.get(filternodebean.getPropname());

				
		if(nodes==null){
			nodes = new TreeSet<FilterNode>(new Comparator<FilterNode>(){

				@Override
				public int compare(FilterNode f1, FilterNode f2) {
					if(f1.getType().ordinal()>f2.getType().ordinal()){
						return 1;
					}
					else if (f1.getType().ordinal()<f2.getType().ordinal()){
						return -1;
					}
					return 0;
				}
				
			});
			propfilter.put(filternodebean.getPropname(), nodes);
		}
		nodes.clear();
		nodes.add(rangenode);
		nodes.add(valnode);
		
		
		
		
		
		
		Integer end = filternodebean.getPgnum()*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		for(Entry<String,Set<FilterNode>> vv:goodfilter.getPropfilters().entrySet()){
			for(FilterNode f:vv.getValue()){
				System.out.println(f.getType());
				for(Object o: f.getValues()){
					System.out.println(o);
				}
			}
		}
		List pagegoods = ((ServiceImpl)adminserv).listPage(goodfilter, begin, end);
		List allgoods = ((ServiceImpl)adminserv).listPage(goodfilter, 0, Serv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		goodfilter.setFilteredgoods(allgoods);
		if(allgoods.size()<((ServiceImpl)adminserv).getCount()){
		goodfilter.getActivefiltercols().put(filternodebean.getPropname(), filternodebean.getColname());
		}
		
	/*	List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
	
		Iterator iter = pagegoods.listIterator();
		
		while(iter.hasNext()){
			Object obj = iter.next();
			Entity good = (Entity)obj;
			if(good.getIsdeleted()==null || !good.getIsdeleted()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname(filternodebean.getAdminclassname());
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
		}*/
		
		if(!goodfilter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, filternodebean.getAdminclassname(), goodfilter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, filternodebean.getAdminclassname(), goodfilter.getJoinclassname(), goodfilter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		
		
		
		filters.put(filternodebean.getAdminclassname(), goodfilter);
		
		sess.setAttribute("adminfilters", filters);
		System.out.println("Новый фильтр: ");
		map.put("pagecount", pagecount);
		map.put("filter", goodfilter);
	//	map.put("goods", pagegoods);
	//	map.put("containers", containers);
		map.put("pgnum", filternodebean.getPgnum());
		map.put("adminclassname", filternodebean.getAdminclassname());
	
		
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+filternodebean.getClassname());
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+filternodebean.getAdminclassname());
		map.put("entityurl", entityurl.toString());
		return "admincommsection";
		
	}
	
	public void checkifhavejoins(Filter goodfilter, FilterNodeBean filternodebean) throws ClassNotFoundException, IntrospectionException{
		if(!filternodebean.getAdminclassname().equals(filternodebean.getColadmintype())){
			goodfilter.setColadmintype("Model."+filternodebean.getColadmintype());
			goodfilter.setCompositefilter(true);

			Field fld = CommonUtilsService.getForeignKeyByTypeName(filternodebean.getAdminclassname(), filternodebean.getColadmintype());
			String fname = fld.getName();
			goodfilter.setJoinfieldname(fname);
		}
	}
	
	public void fillvaluefilter(List<Object> list,FilterNodeBean filternodebean) throws ClassNotFoundException{
	for(ValueBean v:filternodebean.getVals()){
		System.out.println("Getted val "+v.getValue());
		System.out.println("ID "+v.getId());
		if(v.getValue()!=null && v.getId()==null){
		list.add(v.getValue());
		}
		if(v.getValue()!=null && v.getId()!=null){
			Reflections reflections = new Reflections("Model");
			System.out.println("ИМЯ КЛАССА  "+filternodebean.getClassname());
			Class current = Class.forName(filternodebean.getClassname());
			Set<Class<? extends ServiceImpl>> classes = reflections.getSubTypesOf(ServiceImpl.class);
			System.out.println("CLASSES");
			Object serv = new Object();
			Object h = new Object();
			for(Class d: classes){
				System.out.println(d.getName());
				
				ParameterizedType tp = (ParameterizedType) d.getGenericSuperclass();
				Type[] generics = tp.getActualTypeArguments();
				for(int i =0;i<generics.length;i++){
					
					Class cl = (Class)generics[i];
				
					if(cl.getName().equals(filternodebean.getClassname())){
						serv = appContext.getBean(d);
				           
				      /*  EmployeeBean employee = (EmployeeBean)context.getBean("employee");
						serv = d.newInstance();*/
						
					}
				}
			}
			System.out.println(serv.getClass());
			h =((ServiceImpl)serv).getById(v.getId());
			list.add(h);	
		System.out.println("Скастована категория! "+h.toString());
		}
	}
	}

	@RequestMapping(value = "/multidelete", method = RequestMethod.POST)
    public String multiDelete(@RequestBody MultiChangeBean multibean, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IntrospectionException {
		map.put("section", "filterres");
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}
		System.out.println("Имя класса "+multibean.getClassname());
		ServiceImpl adminserv = getService(multibean.getClassname());
		
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter filter = this.getCurrentFilter(filters, multibean.getClassname());
		String adminclassname = multibean.getClassname();
		Integer pgnum = multibean.getPgnum();
		
		for(String id:multibean.getIds()){
			System.out.println(id+"   "+adminserv.getClass().getSimpleName());
			Integer currid = Integer.parseInt(id);
			Object g =adminserv.getById(currid);
			System.out.println(g);
			filter.getFilteredgoods().remove(g);
			adminserv.setDeleteById(currid);
			
		}
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(filter, begin, end);
		List allgoods = adminserv.listPage(filter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);

		if(!filter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, adminclassname, filter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, adminclassname, filter.getJoinclassname(), filter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		
		map.put("filter", filter);
		map.put("pgnum", pgnum);
		map.put("adminclassname", adminclassname);
		map.put("entityurl", entityurl.toString());
		return "admincommsection";
		
	}
	
	@RequestMapping(value = "/savefilter", method = RequestMethod.POST)
    public @ResponseBody String createFilter(@RequestParam (value = "formatname") String formatname, @RequestParam (value = "type") String type, @RequestParam (value = "format_id") Integer format_id, @RequestParam (value = "adminclassname") String adminclassname,  Map<String, Object> map, HttpServletRequest request, HttpSession sess){
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = getCurrentFilter(filters,adminclassname);
		if(goodfilter ==null){
			return "0";
		}
		System.out.println("ФИЛЬТР ТУТ "+goodfilter.getPropfilters());
		AdminFormat format = goodfilter.createFormat(formatname, entServ, false);
		System.out.println("Тип "+type+"    "+" айди формата "+format_id);
		if(type.equals("update") && format_id!=null){
			format.setId(format_id); 
		}
		
			
		System.out.println("АЙДИ   "+format.getId());
		System.out.println("Класс   "+format.getClassname());
		System.out.println("Упр столбец   "+format.getColadmintype());
		System.out.println("Джойн класс   "+format.getJoinclassname());
		System.out.println("Джойн поле   "+format.getJoinfieldname());
		System.out.println("Джойн коллекция    "+format.getJoincolectionname());
		System.out.println("Имя   "+format.getName());
		System.out.println("Compositefilter   "+format.getCompositefilter());
		System.out.println("Multiclasstable   "+format.getMulticlasstable());
		System.out.println("Activefiltercols   "+format.getActivefiltercols());
		System.out.println("Colorder   "+format.getColorder());
		System.out.println("Sortprops   "+format.getSortprops());
		System.out.println("Hidecols   "+format.getHidecols());
		System.out.println("Propvaluefilters   "+format.getPropvaluefilters());
		System.out.println("Proprangefilters   "+format.getProprangefilters());
		System.out.println("Joinvaluefilters   "+format.getJoinvaluefilters());
		System.out.println("Joinrangefilters   "+format.getJoinrangefilters());
		
		
		adfServ.update(format);
		return "1";
		
	}
	
	
	@RequestMapping(value = "/loadformat", method = RequestMethod.POST)
    public String loadFormat(@RequestParam (value = "format_id") Integer format_id, @RequestParam (value = "adminclassname") String adminclassname, @RequestParam(value="pgnum") Integer pgnum,  Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		map.put("section", "filterres");
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}

		
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		AdminFormat adf = adfServ.getById(format_id);
		Filter filter = adf.createFilter(appContext);
		if(filter ==null){
			filter = new Filter();
		}
		filters.put(adminclassname, filter);
		System.out.println("ФИЛЬТР ТУТ "+filter.getPropfilters());
		
		sess.setAttribute("adminfilters", filters);
		ServiceImpl adminserv = getService(adminclassname);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(filter, begin, end);
		List allgoods = adminserv.listPage(filter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);

		if(!filter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, adminclassname, filter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, adminclassname, filter.getJoinclassname(), filter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		Map<String,AdminFormat> formats = (Map<String, AdminFormat>) sess.getAttribute("formats");
		if(formats==null)
		formats = new TreeMap<String, AdminFormat>();
		formats.put(adminclassname, adf);
		sess.setAttribute("formats", formats);
		map.put("filter", filter);
		map.put("pgnum", pgnum);
		map.put("adminclassname", adminclassname);
		map.put("entityurl", entityurl.toString());
		return "admincommsection";
		
	}
	
	@RequestMapping(value = "/saveformatwindow", method = RequestMethod.POST)
    public String saveFormatWindow(@RequestParam (value = "adminclassname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess){
		map.put("section", "saveformatwindow");
		map.put("formats", adfServ.getClassFormats(adminclassname));
		Map<String,AdminFormat> formats = (Map<String, AdminFormat>) sess.getAttribute("formats");
		AdminFormat currentformat = null;
		if(formats!=null)
		currentformat = formats.get(adminclassname);
		map.put("currentformat", currentformat);
		return "admincommsection";
	}
	
	
	@RequestMapping(value = "/multichange", method = RequestMethod.POST)
    public String multiChange(@RequestBody MultiChangeBean multibean, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IntrospectionException {
		
		String classname = "Model."+multibean.getClassname();
		System.out.println("current ИМЯ КЛАССА  "+classname);
		System.out.println("pagenum "+multibean.getPgnum());
		ServiceImpl adminserv = getService(multibean.getClassname());
		
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}
		
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter filter = getCurrentFilter(filters,multibean.getClassname());
		if(filter ==null){
			filter = new Filter();
		}
		List<Integer> listid = new ArrayList<Integer>();
		for(String str:multibean.getIds()){
			Integer id = Integer.parseInt(str);
			listid.add(id);
		}
		
		Map<String,Object> propmap = new TreeMap<String,Object>();
		for(MultiValueBean bn: multibean.getPropvals()){
			Class currclass = Class.forName("Model."+multibean.getClassname());
			Class fieldclass = CommonUtilsService.getFieldClassByClass(currclass, bn.getPropname());
			boolean isPrimitiveOrWrapped = ClassUtils.isPrimitiveOrWrapper(fieldclass);
			if(!isPrimitiveOrWrapped && !fieldclass.getSimpleName().contains("String") && !fieldclass.getSimpleName().contains("Date")){
				Object fvalid = new Object();
				ServiceImpl adminfieldserv = getService(fieldclass.getSimpleName());
				Class fvaltype = CommonUtilsService.getIdField(currclass).getType();
				
				fvalid = Integer.parseInt((String) bn.getPropvalue());

				System.out.println("Айдии "+fvalid);
				Object obj = adminfieldserv.getById((Serializable) fvalid);
				System.out.println(obj);
				bn.setPropvalue(obj);
			}
			System.out.println(bn.getPropname() + "   "+bn.getPropvalue());
			propmap.put(bn.getPropname(), bn.getPropvalue());
		}
		
		adminserv.changeMultiValues(listid, propmap);
	/*	for(String id:multibean.getIds()){
			Integer currid = Integer.parseInt(id);
			Object g =((ServiceImpl)serv).getById(currid);
			System.out.println(((GoodItem)g).getName());
			goodfilter.getFilteredgoods().remove(g);
			((ServiceImpl)serv).deleteById(currid);
			
		}*/
		System.out.println("Страница номер "+multibean.getPgnum());
		Integer end = multibean.getPgnum()*PAGE_SIZE;
		
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(filter, begin, end);
	
		if(!filter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, multibean.getClassname(), filter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, multibean.getClassname(), filter.getJoinclassname(), filter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}
		map.put("section", "filterres");
		map.put("filter", filter);
//		map.put("goods", pagegoods);
		map.put("pgnum",  multibean.getPgnum());
		map.put("classname", multibean.getClassname());
		map.put("adminclassname", multibean.getClassname());
		map.put("entityurl", entityurl.toString());
		return "admincommsection";
		
	}
	
	
	@RequestMapping(value = "/formmultichange", method = RequestMethod.POST)
    public String formMultiChange(@RequestBody List<MultiValueBean> beans, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		
		map.put("section", "formmultichange");
		
		for(MultiValueBean v:beans){
			Class c = Class.forName("Model."+beans.get(0).getAdminclassname());
			Field fld = c.getDeclaredField(v.getPropname());
			v.setType(fld.getType());
			v.setClassname(fld.getType().getSimpleName());
		}
		map.put("propvals", beans);
		
		if(beans.get(0).getObjid()!=null){
			map.put("id", beans.get(0).getObjid());
		}
		System.out.println("АЙДИИИИХА "+beans.get(0).getObjid());
		map.put("adminclassname", beans.get(0).getAdminclassname());
		System.out.println("NEN   ege  ИМЯ КЛАСССССААА !!!!! - "+beans.get(0).getAdminclassname());
		return "admincommsection";
		
	}
	
	@RequestMapping(value = "/adminroles/pagin/{pgnum}", method = RequestMethod.GET)
	public String adminrole(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		User user = (User) request.getAttribute("user");
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
	
		System.out.println(begin+"  "+end);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get("Role");
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		Integer pagecount = Math.round(roleServ.getCount()/PAGE_SIZE);
		map.put("pagecount", pagecount);
//		map.put("goods", Serv.listPage(goodfilter,begin, end));
		map.put("filter", goodfilter);
		map.put("title", "Управление ролями");
		map.put("pgnum", pgnum);
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Role usr:roleServ.listPage(goodfilter,begin, end)){
			if(usr.getIsdeleted()==null || usr.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(usr);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname("Role");
		for(EntityField entfield: fields){
			Class fldclass = CommonUtilsService.getClassByName(usr, entfield.getPropname());
			System.out.println("ИМЯ КЛАССА СТОЛБЦА "+fldclass.getSimpleName());
			entfield.setPropclassname(fldclass.getSimpleName());
					boolean isPrimitiveOrWrapped = ClassUtils.isPrimitiveOrWrapper(fldclass);
					if(!isPrimitiveOrWrapped){
						entfield.setIsobject(true);
					}
					
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
	}
		for(ObjectFieldContainer container:containers){	
			System.out.println("Пользователь -  "+container.getObj());
		for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
			System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
		}
		}
		map.put("containers", containers);
		map.put("adminclassname", "Role");
		map.put("entityurl", "/admin/adminroles/pagin/");
		map.put("section", "roles");
		return "adminGoodList";
		
	}
	
	
	
	@RequestMapping(value = "/getentitychoise", method = RequestMethod.POST)
	public String getEntityChoise(@RequestParam (value = "classname") String classname, @RequestParam (value = "idfld") String idfld, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException{
		Class curr = Class.forName(classname);
		
		ServiceImpl adminserv = getService(curr.getSimpleName());
		if(!classname.contains("Category") && !classname.contains("GoodItem")){
			String simplclassname = Class.forName(classname).getSimpleName();
			System.out.println("dddd "+simplclassname);
			map.put("section", "otherentitychoise");
			map.put("entities", adminserv.getAll());
			map.put("classname", simplclassname);
			map.put("idfld", idfld);
		}
		
		if(classname.contains("Category") ){
		
		List<Category> roots = catServ.getRootCategories();
		setSubCategs(roots,4);
		map.put("catList", roots);
		map.put("section", "choisecategory");
		map.put("idfld", idfld);
		}
		
		if(classname.contains("GoodItem") ){
		List<Category> roots = catServ.getRootCategories();
		setSubCategs(roots,4);
		map.put("catList", roots);
		map.put("section", "goodchoise");
		map.put("idfld", idfld);
		}
		
		if(classname.contains("Role") ){
			System.out.println("ROOOOOLLLLLLEEEE");
			List<Role> roles = roleServ.getAll();
			
			map.put("roles", roles);
			map.put("userid", idfld);
			map.put("section", "rolechoise");
			
			}
		
			
	
		
		return "admincommsection";
		
	}
	
	public void setSubCategs(List<Category> cats, int levels){
		if(levels == 0){return;}
		if(cats == null && cats.isEmpty() ){return;}
		for(Category cat : cats){
			List<Category> subcats = catServ.listCategory(cat);
			cat.setSubcategs(subcats);
			setSubCategs(subcats, levels-1);
		}
		
	}


	@RequestMapping(value = "/choiseEntity", method = RequestMethod.POST)
	public @ResponseBody Entity choiseEntity(@RequestParam(value="id") Integer id, @RequestParam(value="classname") String classname) throws ClassNotFoundException{
	//	System.out.println(catid);
		ServiceImpl service = getService(classname);
		return service.getById(id);
		
	}
	
	@RequestMapping(value = "/deletepropfilter", method = RequestMethod.POST)
    public String deletePropFilter(@RequestParam (value = "classname") String classname, @RequestParam (value = "adminclassname") String adminclassname, @RequestParam (value = "type") String type, @RequestParam (value = "propname") String propname, @RequestParam (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IntrospectionException {
		
		map.put("section", "filterres");
		map.put("pgnum", pgnum);
		System.out.println("ФФФФФФИИИИИИИИИИЛЛЛЬТР");
		System.out.println("Название свойства "+propname);
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length-1; i++){
			entityurl.append(urlparts[i]+"/");
		}
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
/*		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get(adminclassname);
		if(goodfilter ==null){
			goodfilter = new Filter();
		}*/
		Filter goodfilter = getCurrentFilter(filters,adminclassname);
		System.out.println(goodfilter.getMulticlasstable()+"  Joincolumnname "+goodfilter.getJoinfieldname() + " adminclass "+adminclassname);
		
		Reflections reflections = new Reflections("Model");
		String adminclass = "Model."+adminclassname;
		System.out.println("ИМЯ КЛАССА  "+classname);
//		System.out.println("pagenum "+multibean.getPgnum());
		ServiceImpl adminserv = getService(adminclassname);
		
		if(type.equals("filter")){
			if(!goodfilter.isjoinfilter(propname)){
				goodfilter.getPropfilters().remove(propname);
				}
			else{
				goodfilter.getJoinfilters().remove(propname);
			}
			goodfilter.getActivefiltercols().remove(propname);
		}
		if(type.equals("sort")){
			goodfilter.getSortprops().remove(propname);
		}

		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(goodfilter, begin, end);
		List allgoods = adminserv.listPage(goodfilter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		goodfilter.setFilteredgoods(allgoods);
		sess.setAttribute("adminfilters", filters);
	
		if(!goodfilter.getMulticlasstable()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			fillobjectcontainers(containers, adminclassname, goodfilter, begin, end);
			map.put("table", "singleclasstable");
			map.put("containers", containers);
		}
		else{
			List<MultiObjectFieldContainer> multicontainers = new ArrayList<MultiObjectFieldContainer>();
			fillmultiobjectcontainers(multicontainers, adminclassname, goodfilter.getJoinclassname(), goodfilter, begin, end);
			map.put("table", "multiclasstable");
			map.put("multicontainers", multicontainers);
			
			int i =0;
			for(MultiObjectFieldContainer gg:multicontainers){
				System.out.println(" ");
				System.out.println("Строка "+i);
				for(Entry<String, ObjectFieldContainer> entr:gg.getFieldcontainers().entrySet()){
					System.out.println("Сегмент класса "+entr.getKey());
					for(Entry<EntityField, Object> en:entr.getValue().getFields().entrySet()){
						System.out.println("Свойство: "+en.getKey().getPropname());
						System.out.println("Значение: "+en.getValue());
					}
				}
				i++;
			}
			
		}

		map.put("filter", goodfilter);
		map.put("classname", classname);
		map.put("adminclassname", adminclassname);
		map.put("entityurl", entityurl.toString());
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+adminclassname);
		return "admincommsection";
		
	}
	
	
	@RequestMapping(value = "/deletesortcriteria", method = RequestMethod.POST)
    public String deleteSortCriteria(@RequestParam (value = "classname") String classname, @RequestParam (value = "propname") String propname, @RequestParam (value = "adminclassname") String adminclassname, @RequestParam (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		
		map.put("section", "filterres");
		map.put("pgnum", pgnum);
		System.out.println("Название свойства "+propname);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get(adminclassname);
		if(goodfilter ==null){
			goodfilter = new Filter();
		}
		goodfilter.getSortprops().remove(propname);
		ServiceImpl adminserv = getService(adminclassname);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		List pagegoods = adminserv.listPage(goodfilter, begin, end);
		List allgoods = adminserv.listPage(goodfilter, 0, adminserv.getCount());
		Integer pagecount = Math.round(allgoods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		goodfilter.setFilteredgoods(allgoods);
		sess.setAttribute("adminfilters", filters);
/*		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(GoodItem good:pagegoods){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
		for(String fld:goodfields){
			container.getFields().add(good.getClass().getDeclaredField(fld));
		}
		}*/
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Object good:adminserv.listPage(goodfilter,begin, end)){
			Entity ent = (Entity)good;
			if( ent.getIsdeleted()==null || !ent.getIsdeleted()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			List<EntityField> fields = entServ.listByClassname(adminclassname);
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		containers.add(container);
		}
//		map.put("goods", pagegoods);
		}
		map.put("containers", containers);
		map.put("filter", goodfilter);
		map.put("classname", classname);
		map.put("adminclassname", adminclassname);
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+adminclassname);
		return "admincommsection";
	}
	
	
	@RequestMapping(value = "/getpropertyvalue", method = RequestMethod.POST)
    public @ResponseBody Object getPropValue(@RequestParam (value = "adminclassname") String adminclassname, @RequestParam (value = "propname") String propname, @RequestParam (value = "id") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("eeeeeeeeeeeeeeeeeeeee");
		ServiceImpl adminserv = getService(adminclassname);
		Object v = PropertyUtils.getProperty(adminserv.getById(id), propname);
		map.put("value", v.toString());
		map.put("section","propvalue");
		System.out.println(v.toString());
		return v;
		
	}
	
	
	@RequestMapping(value = "/getpropertystringvalue", method = RequestMethod.POST)
    public @ResponseBody String getPropStringValue(@RequestParam (value = "adminclassname") String adminclassname, @RequestParam (value = "propname") String propname, @RequestParam (value = "id") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("eeeeeeeeeeeeeeeeeeeee "+adminclassname+ "  ID = "+id+"  Свойство  "+propname);
		ServiceImpl adminserv = getService(adminclassname);
		Object v = new Object();
		if(id!=null){
		
			v = PropertyUtils.getProperty(adminserv.getById(id), propname);
		}
		if(id==null || v ==null) return "";
		map.put("value", v.toString());
		System.out.println(v.toString());
		map.put("section","propvalue");
	
		return v.toString();
		
	}
	
	@RequestMapping(value = "/getpropvals", method = RequestMethod.POST)
    public String adminGoods(@RequestParam (value = "coladmintype") String coladmintype, @RequestParam (value = "adminclassname") String adminclassname, @RequestParam (value = "currurl") String currurl, @RequestParam (value = "colname") String colname, @RequestParam (value = "propname") String propname, @RequestParam (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException, IntrospectionException {
		
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+adminclassname);
		map.put("section", "propvalues");
		map.put("pgnum", pgnum);
		map.put("visiblecount", 8);
		List<Object> vals = new ArrayList<Object>();
		List<Object> existvals = new ArrayList<Object>();
		FilterNodeBean fltrnode = new FilterNodeBean();
		fltrnode.setPropname(propname);
		fltrnode.setPgnum(pgnum);
		fltrnode.setColname(colname);
		fltrnode.setAdminclassname(adminclassname);
		fltrnode.setColadmintype(coladmintype);
		Map<String, Filter> filters = (Map<String, Filter>) sess.getAttribute("adminfilters");
		if(filters ==null){
			filters = new TreeMap<String, Filter>();
		}
		
		Filter goodfilter = filters.get(adminclassname);
		
		boolean filterexist = true;
		
		ServiceImpl adminserv = getService(adminclassname);
		
		
		
		try {
			Class currentclass = Class.forName("Model."+coladmintype);
			Field f = currentclass.getDeclaredField(propname);
			Class ftype = f.getType();
			
			boolean isPrimitiveOrWrapped = ClassUtils.isPrimitiveOrWrapper(ftype);
			if(!isPrimitiveOrWrapped){
				fltrnode.setIsobject(true);
			}
			String ftypename = f.getType().getName();
			
			fltrnode.setPropclassname(f.getType().getSimpleName());
		
			
			if(goodfilter ==null){
				goodfilter = new Filter();
				filterexist = false;
			}
			goodfilter.setClassname("Model."+adminclassname);
			Field joinfld = null;
			goodfilter.setCompositefilter(false);
			System.out.println(coladmintype+"   "+adminclassname);
	//		goodfilter = null; goodfilter.getClass();
			if(!adminclassname.equals(coladmintype)){
				goodfilter.setCompositefilter(true);
				goodfilter.setColadmintype("Model."+coladmintype);
				joinfld = CommonUtilsService.getForeignKeyByTypeName(adminclassname, coladmintype);
				String fname = joinfld.getName();
				goodfilter.setJoinfieldname(fname);
				goodfilter.setJoincolectionname(CommonUtilsService.getJoinCollectionFieldName(coladmintype, adminclassname));
			}
			vals = ((ServiceImpl)adminserv).getValues(goodfilter, currentclass, f);
			
			for(Entry<String,String> entr:goodfilter.getSortprops().entrySet()){
				if(entr.getKey().equals(propname)){fltrnode.setIssortcriteria(true);break;}
				
			}
			System.out.println("goodfilter "+goodfilter.getFilteredgoods());
			for(Object good: goodfilter.getFilteredgoods()){
				Object v = new Object();
				if(!coladmintype.equals(good.getClass().getSimpleName()) && goodfilter.getJoinfieldname()!=null){
				Object fkey = CommonUtilsService.getValueByName(good, goodfilter.getJoinfieldname());
				v = PropertyUtils.getProperty(fkey, propname);
				}
				else{
				v = PropertyUtils.getProperty(good, propname);	
				}
				existvals.add(v);
			}
			System.out.println("EXISTVALS "+existvals);
			for(Object o: vals){
				if(o==null){System.out.println("null");}
				else{
				System.out.println("Кастанутый  "+ftype.cast(o).toString());
				}
				ValueBean b = new ValueBean();
				b.setIschecked(false);
			/*	for(Entry<String,Set<FilterNode>> entry: goodfilter.getPropfilters().entrySet()){
				for(FilterNode n: entry.getValue()){
					System.out.println("Значения в текущем фильтре: "+n.getValues());
					if(n.getValues().contains(o)) b.setIschecked(true);
				}
				}*/
		/*		for(GoodItem good: goodfilter.getFilteredgoods()){
					for (PropertyDescriptor pd : Introspector.getBeanInfo(currentclass).getPropertyDescriptors()) {
						  if (pd.getReadMethod() != null && !propname.equals(pd.getName()))
							Method t = pd.getReadMethod().invoke(currentclass); 
						   
					}
				}*/
				if(existvals.contains(o)) b.setIschecked(true);
				if(!filterexist) b.setIschecked(true);
				if(o!=null){
					if(Entity.class.isAssignableFrom(o.getClass())){
						Entity ent = (Entity)o;
						System.out.println("Айдиииха "+ent.getId());
						b.setId(ent.getId());
					}
					b.setValue(o.toString());
				}
				else{
					b.setValue("Null");
				}
				
				fltrnode.getVals().add(b);
//				j.add(ftype.cast(o));
				fltrnode.setClassname(ftypename);
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Класс с именем "+adminclassname+" не найден!");
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("Поле с именем "+propname+" не найдено в классе "+adminclassname);
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		map.put("node", fltrnode);
		map.put("propname", propname);
		map.put("adminclassname", adminclassname);
		map.put("currurl", currurl);
		System.out.println("ИМЯ КЛАСССССААА !!!!! - "+adminclassname);
		return "admincommsection";
		
	}
	
	public ServiceImpl getService(String adminclassname) throws ClassNotFoundException{
		Object adminserv = new Object();
		Reflections reflect = new Reflections("Model");
		Class adminentity = Class.forName("Model."+adminclassname);
		Set<Class<? extends ServiceImpl>> services = reflect.getSubTypesOf(ServiceImpl.class);
		
		
		for(Class d: services){
			System.out.println(d.getName());
			
			ParameterizedType tp = (ParameterizedType) d.getGenericSuperclass();
			Type[] generics = tp.getActualTypeArguments();
			for(int i =0;i<generics.length;i++){
				
				Class cl = (Class)generics[i];
				System.out.println(cl.getName()+"   "+adminclassname);
				if(cl.getName().equals("Model."+adminclassname)){
					System.out.println("ВОТ ОН КЛАСС "+cl.getName()+"   "+adminclassname);
					adminserv = appContext.getBean(d);
			        return (ServiceImpl) adminserv;
			      /*  EmployeeBean employee = (EmployeeBean)context.getBean("employee");
					serv = d.newInstance();*/
					
				}
			}
			
			
			
		}
		return null;
	}
	@RequestMapping(value = "/getregiontowns", method = RequestMethod.POST)
    public String getregiontowns(@RequestParam (value = "regid") Integer regid, @RequestParam (value = "regnum") Integer regnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException {
		Region reg = regServ.getById(regid);
		map.put("region", reg);
		map.put("regnum", regnum);
		map.put("section", "regiontowns");
		return "admincommsection";
		
	}
	
	
	@RequestMapping(value = "/setdeleted/{classname}/{id}", method = RequestMethod.GET)
    public String setEntityDeleted(@PathVariable (value = "id") Integer id, @PathVariable (value = "classname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException{
		ServiceImpl adminserv = getService(adminclassname);
		adminserv.setDeleteById(id);
		String referrer = request.getHeader("referer");
		
		return "redirect:"+referrer;
		
	}
	
	@RequestMapping(value = "/realdelete/{classname}/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable (value = "id") Integer id, @PathVariable (value = "classname") String adminclassname, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws ClassNotFoundException{
		ServiceImpl adminserv = getService(adminclassname);
		System.out.println("Id "+id);
		adminserv.deleteById(id);
		String referrer = request.getHeader("referer");
		
		return "redirect:"+referrer;
		
	}
	
	
	
	@RequestMapping(value = "/admingoodstates", method = RequestMethod.GET)
	public String addGoodState(Map<String, Object> map){
		List<GoodState> states = new ArrayList<GoodState>();
		GoodStatesBean goodstates = new GoodStatesBean();
		states = gstServ.getAll();
   //  	if(!states.isEmpty()){
	//	map.put("states", states);
	//	}
		goodstates.setStates(states);
  //   	map.put("states", new ArrayList<GoodState>());
		map.put("goodstates", goodstates);
//		model.addAttribute("type", "create");
        return "addStates";
		
	}
	
	@RequestMapping(value = "/addedGoodState",method = RequestMethod.POST)
    public String addedGoodState(@ModelAttribute(value = "goodstates") GoodStatesBean goodstates, Map<String, Object> map,HttpServletRequest request) {
		
		System.out.println(goodstates.getStates().size());
		
		for(GoodState state: goodstates.getStates()){
		System.out.println("Айди:"+state.getId());
		if(state.getId()!=null){
		gstServ.update(state);
		}
		else{gstServ.update(state);}
		}
		

		String referrer = request.getHeader("referer");
		
		return "redirect:"+referrer;
    }
	
	
/*	@RequestMapping(value = "/adddeltype", method = RequestMethod.GET)
	public String addDeltype(Map<String, Object> map){
		List<GoodState> states = new ArrayList<GoodState>();
		GoodStatesBean goodstates = new GoodStatesBean();
		states = gstServ.getAll();
   //  	if(!states.isEmpty()){
	//	map.put("states", states);
	//	}
		goodstates.setStates(states);
  //   	map.put("states", new ArrayList<GoodState>());
		map.put("goodstates", goodstates);
//		model.addAttribute("type", "create");
        return "adminDeltypes";
		
	}
	
	
	@RequestMapping(value = "/addpaytype", method = RequestMethod.GET)
	public String addPaytype(Map<String, Object> map){
		List<GoodState> states = new ArrayList<GoodState>();
		GoodStatesBean goodstates = new GoodStatesBean();
		states = gstServ.getAll();
   //  	if(!states.isEmpty()){
	//	map.put("states", states);
	//	}
		goodstates.setStates(states);
  //   	map.put("states", new ArrayList<GoodState>());
		map.put("goodstates", goodstates);
//		model.addAttribute("type", "create");
        return "adminPaytypes";
		
	}
	
	*/
	
}
