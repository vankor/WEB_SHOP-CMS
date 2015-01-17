package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import Model.entity.Category;
import Model.service.CategoryService;
import Model.service.CommentService;
import Model.service.CommonUtilsService;
import Model.EntityField;
import Model.service.EntityFieldService;
import Model.entity.GoodItem;
import Model.dto_beans.GoodItemCreationBean;
import Model.service.GoodItemService;
import Model.dto_beans.GoodPropSegmentBean;
import Model.service.GoodStateService;
import Model.dto_beans.GoodUpdateBean;
import Model.entity.Image;
import Model.others.ImageLoader;
import Model.service.ImageService;
import Model.entity.News;
import Model.dto_beans.NewsBean;
import Model.others.ObjectFieldContainer;
import Model.entity.Page;
import Model.service.PageService;
import Model.dto_beans.ProducerBean;
import Model.entity.PropSegment;
import Model.service.PropSegmentService;
import Model.dto_beans.PropValueBean;
import Model.entity.Property;
import Model.service.PropertyService;
import Model.entity.User;
import Model.service.UserService;
import Model.entity.Value;
import Model.service.ValueService;
import Model.service.VoteService;

@Controller
@RequestMapping(value = {"/admin/admincategory/{catId}"})
public class AdminCategory{
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private CommentService comServ;
	
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
	private PropSegmentService prsgServ;
	
	@Autowired
	private EntityFieldService entServ;
	
	@Autowired
	private PageService pageServ;
	
	private Logger logger = LoggerFactory.getLogger(AdminCategory.class);

	@RequestMapping(method = RequestMethod.GET)
    public String listCategory(@PathVariable (value = "catId") Integer id, @RequestParam (value = "result", required = false) Integer result, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = (User)sess.getAttribute("usr");
		if (user==null){
			user = new User();
			user.setIp(request.getLocalAddr());
			user.setLogin("Гость");
			
		}
		
		Category cat = catServ.getById(id);
		List<Category> subcategs  = catServ.listCategory(cat);
		List<GoodItem> goods = new ArrayList<GoodItem>();		
		System.out.println(cat.getIsFinalCategory());
		String currenturl = request.getServletPath();
		System.out.println("Текущий УРЛ:"+currenturl);
		map.put("currenturl", currenturl);
		

		
		
/*		map.put("catList", roots);
		map.put("currentCatList", subcategs);
		map.put("paramcontainer", new ParamContainer());*/
		map.put("catid", id);
		map.put("category", cat);
		
	/*	map.put("searchForm", new SearchForm());*/
		map.put("isfinal", cat.getIsFinalCategory());
		
		if(cat.getIsFinalCategory()){
			List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
			for(GoodItem good:cat.getGoods()){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				container.getRefs().put("Показать на сайте", "/good/"+CommonUtilsService.getValueByName(good, "id")+"/all");
				container.getRefs().put("Редактиовать", "/admin/admingood/"+CommonUtilsService.getValueByName(good, "id")+"/updateGood");
				container.getRefs().put("Удалить", "/admin/admingood/"+CommonUtilsService.getValueByName(good, "id")+"/deleteGood");
				List<EntityField> fields = entServ.listByClassname("GoodItem");
			for(EntityField entfield: fields){
				if(entfield.getIsmainpageprop()!=null && entfield.getIsmainpageprop()){
					container.getFields().put(entfield, null);
					}
			}
			container.fillFieldValues();
			containers.add(container);
			}
			for(ObjectFieldContainer container:containers){	
				System.out.println("Товар -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("containers", containers);
			
		}
		
		else{
		
		List<ObjectFieldContainer> containers = new ArrayList<ObjectFieldContainer>();
		for(Category categ:subcategs){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(categ);
			container.getRefs().put("Показать на сайте", "/category/"+categ.getId()+"/pgnum/1");
			container.getRefs().put("Развернуть", "/admin/admincategory/"+categ.getId()+"");
			container.getRefs().put("Удалить", "/category/"+categ.getId()+"/deleteCat");
			List<EntityField> fields = entServ.listByClassname("Category");
		for(EntityField entfield: fields){
			if(entfield.getIsmainpageprop()){
			container.getFields().put(entfield, null);
			}
		}
		container.fillFieldValues();
		containers.add(container);
		}
		for(ObjectFieldContainer container:containers){	
			System.out.println("Категория -  "+container.getObj());
		for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
			System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
		}
		}
		map.put("containers", containers);
		}
		
		
		map.put("catname", cat.getName());
		
		
		
		
		List<ObjectFieldContainer> newscontainers = new ArrayList<ObjectFieldContainer>();
		for(News news:cat.getCatnews()){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(news);
			container.getRefs().put("Показать на сайте", "/category/"+((Category)CommonUtilsService.getValueByName(news, "category")).getId()+"/news/"+CommonUtilsService.getValueByName(news, "id"));
			container.getRefs().put("Редактиовать", "/admin/adminnews/"+((Category)CommonUtilsService.getValueByName(news, "category")).getId()+"/updatenews/"+CommonUtilsService.getValueByName(news, "id"));
			container.getRefs().put("Удалить", "/admin/adminnews/"+((Category)CommonUtilsService.getValueByName(news, "category")).getId()+"/deletenews/"+CommonUtilsService.getValueByName(news, "id"));
			List<EntityField> fields = entServ.listByClassname("News");
		for(EntityField entfield: fields){
			if(entfield.getIsmainpageprop()!=null && entfield.getIsmainpageprop()){
			container.getFields().put(entfield, null);
			}
		}
		container.fillFieldValues();
		newscontainers.add(container);
		}
		
		map.put("newscontainers", newscontainers);
		
/*		if(cat.getIsFinalCategory()){
			List<ParamContainer> params = new ArrayList<ParamContainer>();
			List<Property> props = cat.getProps();
			for(Property prop: props){
				ParamContainer param = new ParamContainer();
				param.setPropid(prop.getId());
				param.setPropname(prop.getName());
				for(Value val:prop.getVal()){
				param.addParam(new ValueBean(val));
				}
				params.add(param);
			
			
			}
			ParamFilterBean filter = new ParamFilterBean();
			filter.setParams(params);
			map.put("paramfilter", filter);
			goods = cat.getGoods();
			
			map.put("count", goods.size());
			map.put("maxPrice", catServ.getExtremePrice(cat, "max"));
			map.put("minPrice", catServ.getExtremePrice(cat, "min"));
			for(GoodItem g: goods){
				System.out.println(g.getName()+"      " + g.getAverage_rating());
	//			g.setTotalVotes();
			}
			
			map.put("goodList", goods);
			
			return "bb";
			
		}
		
			Set<Category> allsubcategs = new TreeSet<Category>();
			setSubCategs(subcategs,2);
			for(Category c: subcategs){
	//			System.out.println(c.getName());
				allsubcategs.addAll(c.getSubcategs());
			}
			
			for(Category c: allsubcategs){
				System.out.println(c.getName());
			}
			
			
			map.put("allsubcategs", allsubcategs);
			map.put("subcategs", subcategs);
			map.put("subcategscount", subcategs.size());*/
			if(result!=null && result==1){
			map.put("result", "success");
			map.put("resulttext", "Товар успешно сохранен в категорию!");}
			return "bb";
    }
	
	@RequestMapping(value = "/deleteCat")
	public String deleteCat(@PathVariable("catId") Integer id){
		Category cat = catServ.getById(id);
		catServ.deleteById(id);
		return "redirect:/admin/admincategory/"+cat.getParent_category().getId();
	}
	
	@RequestMapping(value = "/deleteProperty/{propId}")
	public String deleteProperty(@PathVariable("catId") Integer catid, @PathVariable("propId") Integer propid, HttpServletRequest request){
		propServ.deleteById(propid);
		return "redirect:/admin/admincategory/"+catid+"/updateCateg";
	}
	
	
	@RequestMapping(value = "/deletePropSegment/{prsgId}")
	public String deletePropSegment(@PathVariable("catId") Integer catid, @PathVariable("prsgId") Integer prsgid, HttpServletRequest request){
		prsgServ.deleteById(prsgid);
		return "redirect:/admin/admincategory/"+catid+"/updateCateg";
	}
	
	@RequestMapping(value = "/updateCateg", method = RequestMethod.GET)
	public String updateCategory(@PathVariable(value = "catId") Integer id, @RequestParam(value = "result", required = false) Integer result, Model model){
		System.out.println("here");
		model.addAttribute("category", catServ.getById(id));
		for(Property p: catServ.getById(id).getProps()){
			System.out.println(p.getName());
		}
		model.addAttribute("type", "update");
		if(result!= null && result==1)model.addAttribute("result", "success");
        return "addcat";
		
	}
	
	@RequestMapping(value = "/addProducer", method = RequestMethod.GET)
	public String addProducer(@PathVariable(value = "catId") Integer id, Map<String, Object> map){
		ProducerBean prod = new ProducerBean();
		prod.setCategory(catServ.getById(id));
		map.put("producer", prod);
		map.put("type", "create");
		return "adminProducer";
		
	}
	
	@RequestMapping(value = "/updatedCateg", method = RequestMethod.POST)
	public String updatedCategory(@ModelAttribute (value = "category") @Valid Category ccat, BindingResult bindingResult, Map<String, Object> map){
		
		System.out.println("bindresults "+bindingResult.hasErrors()+"  "+bindingResult.getFieldErrors());
		
		Category cat = (ccat.getId()!=null)?catServ.getById(ccat.getId()):ccat;
		cat.setName(ccat.getName());
		cat.setAlias(ccat.getAlias());
		cat.setThumb(ccat.getThumb());
		cat.setPage(ccat.getPage());
		cat.setNewthumb(ccat.getNewthumb());
		cat.setIsFinalCategory(ccat.getIsFinalCategory());
		cat.setIstop(ccat.getIstop());
		
		
		if (bindingResult.hasErrors()) {

			map.put("category", cat);
			map.put("type", "update");
			
            return "addcat";
        }
		
		if(cat.getParent_category().getId()==null)
			cat.setParent_category(null);
		else
			cat.setParent_category(catServ.getById(ccat.getParent_category().getId()));
		
//		try {
//			cat.setName(new String(cat.getName().getBytes("ISO-8859-1"),"UTF-8"));
		Set<Category> categs = new TreeSet<Category>();
/*		for(PropSegment p:cat.getPropsegments()){
	//		categs = p.getCategories();
	//		p.setName(new String(p.getName().getBytes("ISO-8859-1"),"UTF-8"));
			System.out.println(p.getName());
			for(Property prop:p.getProperties()){
	//			prop.setName(new String(prop.getName().getBytes("ISO-8859-1"),"UTF-8"));
				prop.setPropsegment(p);
				System.out.println("Имя "+prop.getName());
				System.out.println("Приоритет "+prop.getPriority());
				System.out.println("Количество основных "+prop.getMainvalscount());
			}
			categs.add(cat);
//			p.setCategories(categs);
		System.out.println(p.getName());
		}*/
		
		
		
		Iterator<PropSegment> iter0 = cat.getPropsegments().iterator();
		while(iter0.hasNext()){
			PropSegment proper = iter0.next();
			boolean isdeleted = false;
			if(!ccat.getPropsegments().contains(proper)){
	
				iter0.remove();
				System.out.println("removed propsegment");
			}
			
		}
		
		for(PropSegment p:ccat.getPropsegments()){
			PropSegment prpsg = new PropSegment();
			if(p.getId()!=null && cat.getPropsegments().contains(p)){
				for(PropSegment prsgm:cat.getPropsegments()){
					if(prsgm.getId().equals(p.getId())){
						prpsg = prsgm;
					}
				}
				
				
				Property todel = new Property();
				Iterator<Property> iter = prpsg.getProperties().iterator();
				while(iter.hasNext()){
					Property proper = iter.next();
					boolean isdeleted = false;
					if(!p.getProperties().contains(proper)){
				/*		isdeleted = true;
						todel = proper;*/
						iter.remove();
					//	System.out.println("Элемент "+todel.getName()+" удален "+prpsg.getProperties().remove(todel));
					}
					
				}
				
				prpsg.setName(p.getName());
				prpsg.setPriority(p.getPriority());
				
				for(Property prop:p.getProperties()){
					Property pr = new Property();
					if(prop.getId()!=null){
						Iterator<Property> iter1 = prpsg.getProperties().iterator();
					/*	Integer size = prpsg.getProperties().size();
						int minussize = 0;*/
						while(iter1.hasNext()){
							Property proper = iter1.next();
							if(proper.getId()==null && proper.getName()==null && proper.getPriority()==null ){
								System.out.println("Удалена нулевая запись");
								iter1.remove();
							
								}
							else if(proper.getId()!=null && proper.getId().equals(prop.getId())){
								pr = proper;
							}
						}
					
						System.out.println("Размер аррея "+prpsg.getProperties().size());
					}
					
					pr.setPropsegment(prpsg);
					pr.setName(prop.getName());
					pr.setPriority(prop.getPriority());
					pr.setMainvalscount(prop.getMainvalscount());
					if(!prpsg.getProperties().contains(pr))
					prpsg.addProperty(pr);
					
					System.out.println("В процессе  1   "+prpsg.getId()+"    "+prpsg.getName()+"    "+"    Свойство    "+pr.getId()+"     "+pr.getName()+"      "+pr.getPriority()+"      "+pr.getMainvalscount());
										
				}
				
			}
			if(p.getId()==null){
				prpsg.setName(p.getName());
				prpsg.setPriority(p.getPriority());
				for(Property prop:p.getProperties()){
					Property pr = new Property();
					pr.setPropsegment(prpsg);
					pr.setName(prop.getName());
					pr.setPriority(prop.getPriority());
					pr.setMainvalscount(prop.getMainvalscount());
					prpsg.getProperties().add(pr);
					System.out.println("В процессе  2"+prpsg.getId()+"    "+prpsg.getName()+"    "+"    Свойство    "+pr.getId()+"     "+pr.getName()+"      "+pr.getPriority()+"      "+pr.getMainvalscount());						
				}
				
				cat.addPropSegment(prpsg);
			}
			
			
		}
		
		
		Integer fldrid = (cat.getId()==null)?(catServ.getLastId()+1):cat.getId();
//		ImageLoader.loadthumb(cat.getNewthumb(), fldrid, "categories");
		cat.setThumb(ImageLoader.loadthumb(cat.getNewthumb(), fldrid, "categories"));
		Page page = cat.getPage();
		System.out.println("АЙДИ ДО "+page.getId());
		
	
		if(page.getId()==null){
			if(cat.getParent_category()!=null){
				Category parent = catServ.getById(cat.getParent_category().getId());
				page.setParent_page(parent.getPage());
	
			}
		
		}
		else{
			page = pageServ.getById(cat.getPage().getId());
			page.setHtml(cat.getPage().getHtml());
			page.setMeta(cat.getPage().getMeta());
			page.setName(cat.getPage().getName());
			page.setTitle(cat.getPage().getTitle());
			page.setUrl(cat.getPage().getUrl());
			page.setH1(cat.getPage().getH1());
			cat.setPage(page);
		}
		
		System.out.println("Категории айди "+cat.getId());
		for(PropSegment p:cat.getPropsegments()){
				System.out.println("Сегмент параметров "+p.getId()+"   "+p.getName());
				for(Property prop:p.getProperties()){
					System.out.println("Свойство параметра "+prop.getId()+"   "+prop.getName()+"      "+prop.getPriority()+"   "+prop.getMainvalscount()+"     "+prop.getCat());
				}
				
			}
		catServ.update(cat);
        return "redirect:/admin/admincategory/"+cat.getId()+"/updateCateg?result=1";
		
	}
	
	
	@RequestMapping(value = "/addChildCateg", method = RequestMethod.GET)
	public String addCategory(@PathVariable("catId") Integer catid, Model model){
		System.out.println("here");
		Category cat = new Category();
//		cat.setParent_id(catid);
		cat.setParent_category(catServ.getById(catid));
		model.addAttribute("category", cat);
		model.addAttribute("type", "createchild");
        return "addcat";
		
	}
	
	
	
	@RequestMapping(value = "/addNewstoCat")
    public String addNews(@PathVariable (value="catId") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Category cat = catServ.getById(catid);
		NewsBean news = new NewsBean();
		news.setCategory(cat);
		Date date = new Date(System.currentTimeMillis());
		news.setCreation_date(new SimpleDateFormat("yyyy-MM-dd").format(date));
		news.setCreation_time(new SimpleDateFormat("hh:mm").format(date));
//		map.put("category", catServ.getCategoryById(catid));
		news.setCategory(catServ.getById(catid));
		map.put("news", news);
		map.put("type", "addtocat");
//		map.put("catid", catid);
		return "adminNews";
		
	}
	
	
	@RequestMapping(value = "/addGoodtoCat")
    public String addGoodtoCat(@PathVariable (value = "catId") Integer id, Map<String, Object> map) {
		Category cat = catServ.getById(id);
		List<PropSegment> prsgList = cat.getPropsegments();
	//	List<Property> str = cat.getProps();
	//	for(Property s : str){
	//		System.out.println(s.getName());
	//		}
		GoodUpdateBean good = new GoodUpdateBean();
		good.setCategory_id(id);
//		good.setCategory(cat);
//		Map<String,String> properties = new HashMap<String,String>();
//		for(String par:str){
//			properties.put(par, null);
//		}
//		good.setProperties(str);
//		Map<String, String> props = new HashMap<String, String>();
		List<GoodPropSegmentBean> segments = new ArrayList<GoodPropSegmentBean>();
	//	for(PropSegment prsg: prsgList){
	//		GoodPropSegmentBean goodprosegment = new GoodPropSegmentBean();
	//		goodprosegment.setId(prsg.getId());
	//		goodprosegment.setSegment_name(prsg.getName());
	//		List<Property> str = prsg.getProperties();
	//		
	//		for(Property prop: str){
	//			List<String> titles =new ArrayList<String>();
	//			titles.add(prop.getName());
	///			titles.add(null);
	//			goodprosegment.getProperties().put(prop.getId(), titles);
	//		}
	//		segments.add(goodprosegment);	
	//	}

		good.setSegments(GoodItem.fillSegments(null, prsgList));
		
		map.put("good", good);
		map.put("states", gstServ.getAll());
//		map.put("nn", titles);
			
		map.put("catid", id);
		map.put("category", cat);
		map.put("section", "addgood");
		
		return "addGood";
    }	
	
	
	@RequestMapping(value = "/addedGood", method = RequestMethod.POST)
    public String addedGood(@ModelAttribute(value = "good") GoodItemCreationBean goodbean, Map<String, Object> map, HttpServletRequest request) {
		String rootpath = "D:/Ваня/проги/projSpr/WebContent/resources/images/";
	//	Map<String, String> b = good.getProperties();
	//	for(Entry<String, String> val : b.entrySet()){
	//		System.out.println(val.getKey()+":  "+ val.getValue());
	//	}
		Integer thumbid =  Serv.getLastId()+1;
		System.out.println(thumbid);
		File folder = new File(rootpath+thumbid+"/");
		if(!folder.exists() || !folder.isDirectory()){ folder.mkdir();}
		int y = imgServ.getLastId()+1;
		GoodItem good = new GoodItem();
		String path;
		for(CommonsMultipartFile f: goodbean.getImages()){
			System.out.println(f.getName() + "  " + f.getSize());
			path = rootpath+thumbid+"/img"+y+".jpg";
			System.out.println(path);
			try {
				f.transferTo(new File(path));
				good.addImage(new Image("/"+thumbid+"/img"+y+".jpg", good));
				y++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(goodbean.getThumb().getName());
		
		try {
			copyFile("D:/Ваня/проги/web-shop/src/main/webapp/WEB-INF/resources/images/"+thumbid+"/thumb.jpg", goodbean.getThumb());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		good.setCategory(catServ.getById(goodbean.getCategory_id()));	
		good.setThumb("/"+thumbid+"/thumb.jpg");
		good.setName(goodbean.getName());
		good.setPrice(goodbean.getPrice());
		good.setDescription(goodbean.getDescription());
		good.setState(gstServ.getByName(goodbean.getState_name()));
		
		
		for(GoodPropSegmentBean segmentbean:goodbean.getSegments()){
			System.out.println(segmentbean.getSegment_name()+":");
		for(PropValueBean prbn:segmentbean.getProperties()){
//			System.out.println(prbn.getProp().getId()+"      "+prbn.getProp().getId()+"     "+entry.getValue().get(1));
			
			for(Value val:prbn.getVals()){
				Value goodval = new Value();
				
				if(val.getId()!=null){
					goodval = valServ.getById(val.getId());
				}
				else{
					goodval.setProp(propServ.getById(prbn.getProp().getId()));
					goodval.setValue(val.getValue());
				}
				System.out.println(goodval.getProp().getId()+"  "+goodval.getProp().getName()+"  "+goodval.getValue()+"  "+goodval.getId());
				good.addValue(goodval);
			}
			
			
		}
		}
		Serv.add(good);
		return "redirect:/admin/admincategory/"+goodbean.getCategory_id()+"/addGoodtoCat";
    }
	
	
	
	private void copyFile(String path, CommonsMultipartFile file) throws IOException{
		int elem;
		InputStream in = file.getInputStream();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
		while((elem = in.read())!=-1){
			out.write(elem);
		}
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
	
	
}

