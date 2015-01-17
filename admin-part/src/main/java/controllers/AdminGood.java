package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import Model.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.CategoryService;
import Model.CommentService;
import Model.GoodImageLoaderFactory;
import Model.GoodItem;
import Model.GoodItemService;
import Model.GoodPropSegmentBean;
import Model.GoodState;
import Model.GoodStateService;
import Model.GoodStatus;
import Model.GoodStatusService;
import Model.GoodUpdateBean;
import Model.Good_Guarantie;
import Model.Good_GuarantieService;
import Model.Guarantie;
import Model.GuarantieService;
import Model.Image;
import Model.ImageService;
import Model.Loader;
import Model.LoaderFactory;
import Model.Page;
import Model.PageService;
import Model.Producer;
import Model.ProducerService;
import Model.PropSegment;
import Model.PropValueBean;
import Model.PropertyService;
import Model.UserService;
import Model.Value;
import Model.ValueService;
import Model.VoteService;

@Controller
@RequestMapping(value = "/admin/admingood/{goodId}")
public class AdminGood {
	
	private Logger logger = LoggerFactory.getLogger(AdminGood.class);
	
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
	private PageService pageServ;
	
	@Autowired
	private ProducerService prodServ;
	
	@Autowired
	private Good_GuarantieService ggServ;
	
	@Autowired
	private GuarantieService guaServ;
	
	@Autowired
	private GoodStatusService ggstServ;
	
	
	
	private final String rootpath = "D:/¬‡Ìˇ/ÔÓ„Ë/web-shop/src/main/webapp/resources/images/";
	
/*	@Autowired
    @Qualifier("goodValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }*/
	
	@RequestMapping(value = "/deleteGood")
	public String deleteGood(@PathVariable("goodId") Integer id){
		Category cat = Serv.getById(id).getCategory();
		Serv.deleteById(id);
		File f = new File(rootpath+"goods/"+id);
		if(f.exists() && f.isDirectory()){
			for(File file: f.listFiles()){
				file.delete();
			}
			f.delete();
		}
		
		
		return "redirect:/admin/admincategory/"+cat.getId();
	}
	
	
	@RequestMapping(value = "/copyGood")
	public String copyGood(@PathVariable("goodId") Integer id, Map<String, Object> map){
		GoodItem g =Serv.getById(id);
		GoodUpdateBean good = new GoodUpdateBean(g);
		
		
		List<PropSegment> prsgList = g.getCategory().getPropsegments();
		good.setSegments(GoodItem.fillSegments(g,prsgList));

		good.setId(null);
		Set<String> states = new TreeSet<String>();
		for(GoodState st:gstServ.getAll()){
			states.add(st.getText());
		}
		good.getImages().clear();
		map.put("states",states);
		map.put("good", good);
		map.put("section", "addgood");
		map.put("category", g.getCategory());
		
		
		return "addGood";
	}
	
	
	@RequestMapping(value = "/updateGood")
	public String updateGood(@PathVariable (value = "goodId") Integer id, @RequestParam (value = "result", required = false) Integer result, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		GoodItem g =Serv.getById(id);
		GoodUpdateBean good = new GoodUpdateBean(g);
		List<PropSegment> prsgList = g.getCategory().getPropsegments();
		map.put("good", good);
		
		
/*		System.out.println(good.getState_name());
		System.out.println(good.getCategory_id());
		System.out.println(good.getIsTopGood());*/
	//	for(Image im: good.getImages()){
//			System.out.println(im.getId()+ "        "+im.getGood()+"       "+im.getUrl()+"");
//			}
			
			
	//		for(Value im: good.getVals()){
	//			System.out.println(im.getId()+ "        "+im.getGood()+"       "+im.getProp()+"     "+im.getValue());
	//			}
			
	
			
		good.setSegments(GoodItem.fillSegments(g,prsgList));
		map.put("section", "updategood");
		map.put("category", g.getCategory());
		Set<String> states = new TreeSet<String>();
		for(GoodState st:gstServ.getAll()){
			states.add(st.getText());
		}
		map.put("states",states);
//		map.put("type", "update");
//		map.put("properties", g.getVals());
		if(result!=null && result==1)
		map.put("result", "success");
		
		return "addGood";
	}
	
	@RequestMapping(value = "/updatedGood", method = RequestMethod.POST)
	public String updatedGood(@ModelAttribute(value = "good") @Valid GoodUpdateBean goodbean, BindingResult bindingResults, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		if(bindingResults.hasErrors()){
			System.out.println("hassssss errrors");
			if(goodbean.getId()!=null)
			map.put("section", "updategood");
			else map.put("section", "addgood");
			map.put("good", goodbean);
			GoodItem g = null;
			Category cat = catServ.getById(goodbean.getCategory_id());
			List<PropSegment> prsgList = cat.getPropsegments();
			if(goodbean.getId()!=null)
			g =Serv.getById(goodbean.getId());
			
			goodbean.setSegments(GoodItem.fillSegments(g,prsgList));
			map.put("category", cat);
			Set<String> states = new TreeSet<String>();
			for(GoodState st:gstServ.getAll()){
				states.add(st.getText());
			}
			map.put("states",states);
			return "addGood";
		}
		
		GoodItem good0 = new GoodItem();
		System.out.println("¿…ƒ»»»» "+goodbean.getId());
		if(goodbean.getId()!=null){
			good0 = Serv.getById(goodbean.getId());
			System.out.println(goodbean.getId());
			System.out.println(good0);
			good0.setUpdating_date(new Date(System.currentTimeMillis()));
		}
		else{
			good0.setCreation_date(new Date(System.currentTimeMillis()));
		}
		
				
		goodbean.updateGood(good0);
		good0.setCategory(catServ.getById(goodbean.getCategory_id()));
		good0.setState(gstServ.getByName(goodbean.getState_name()));
		Page page = goodbean.getPage();
		if(page.getId()==null){			
				Category parent = catServ.getById(good0.getCategory().getId());
				page.setParent_page(parent.getPage());
		}
		else{
			page= pageServ.getById(good0.getPage().getId());
			page.setHtml(goodbean.getPage().getHtml());
			page.setMeta(goodbean.getPage().getMeta());
			page.setName(goodbean.getPage().getName());
			page.setTitle(goodbean.getPage().getTitle());
			page.setUrl(goodbean.getPage().getUrl());
			page.setH1(goodbean.getPage().getH1());
		}
		good0.setPage(page);
		Producer pr = prodServ.getById(goodbean.getProducer().getId());
		good0.setProducer(pr);
		
		Set<Good_Guarantie> guearanties = good0.getGoodguaranties();
		guearanties.clear();
		for(Good_Guarantie gg: goodbean.getGoodguaranties()){
			Good_Guarantie goodgar = new Good_Guarantie();
			goodgar.setGood(good0);
			Guarantie guar = guaServ.getById(gg.getGuarantie().getId());
			goodgar.setGuarantie(guar);
			goodgar.setIscostless(gg.getIscostless());
			if(gg.getIscostless())
				goodgar.setPrice(0.0);
			else
				goodgar.setPrice(gg.getPrice());
			
			
			guearanties.add(goodgar);
			
		}
		
		good0.setGoodguaranties(guearanties);
		System.out.println(guearanties);
		
		
		good0.setBonusscores(goodbean.getBonusscores());
		good0.setMaxdeliverydays(goodbean.getMaxdeliverydays());
		good0.setMindeliverydays(goodbean.getMindeliverydays());
		if(goodbean.getExchangeguarantie()!=null && goodbean.getExchangeguarantie().getId()!=null)
		good0.setExchangeguarantie(guaServ.getById(goodbean.getExchangeguarantie().getId()));
		if(goodbean.getProdguarantie()!=null && goodbean.getProdguarantie().getId()!=null)
		good0.setProdguarantie(guaServ.getById(goodbean.getProdguarantie().getId()));
		good0.setOldprice(goodbean.getOldprice());
		good0.setDiscount(goodbean.getDiscount());
		
		
		Set<GoodStatus> goodstatuses = good0.getStatuses();
		goodstatuses.clear();
		for(GoodStatus gg: goodbean.getStatuses()){
			GoodStatus goodstat = ggstServ.getById(gg.getId());
			goodstatuses.add(goodstat);
			
		}
		
		good0.setStatuses(goodstatuses);
		
		for(Image im: goodbean.getImages()){
		System.out.println(im.getId()+ "        "+im.getGood()+"       "+im.getUrl()+"");
		Image mage = (im.getId()!=null)?imgServ.getById(im.getId()):im;
		mage.setUrl(im.getUrl());
		imgServ.update(mage);
		}

			Integer fldrid = (good0.getId()==null)?(Serv.getLastId()+1):good0.getId();
			int filenumber = imgServ.getLastId()+1;
	
			LoaderFactory loaderfactory = new GoodImageLoaderFactory();
			Loader l = loaderfactory.createLoader(goodbean, good0, fldrid, filenumber);
			l.load();

		
		for(GoodPropSegmentBean segmentbean:goodbean.getSegments()){
			System.out.println(segmentbean.getSegment_name()+":");
		for(PropValueBean prbn:segmentbean.getProperties()){
			
			for(Value val:prbn.getVals()){
				if(val!=null && (val.getValue()!=null||val.getId()!=null)){
				Value goodval = new Value();
				
				if(val.getId()!=null){
					System.out.println("…ƒ»»»»»»»» "+val.getId());
					goodval = valServ.getById(val.getId());
				}
				else{
					System.out.println("ÌÂÚÛ ‡È‰ËıË!");
					goodval.setProp(propServ.getById(prbn.getProp().getId()));
					goodval.setValue(val.getValue());
				}
				System.out.println(goodval.getProp().getId()+"  "+goodval.getProp().getName()+"  "+goodval.getValue()+"  "+goodval.getId());
				good0.addValue(goodval);
				}
			}
			
			
		}
		}
	
		Serv.update(good0);
		if(good0.getId()==null){
			return "redirect:/admin/admincategory/"+good0.getCategory().getId()+"?result=1";
		}
		
		
		return "redirect:/admin/admingood/"+good0.getId()+"/updateGood?result=1";
	}
	
	@RequestMapping(value = "/deleteImage/{imgid}", method = RequestMethod.GET)
	  public String showAction(@PathVariable (value="goodId") Integer goodId, @PathVariable (value="imgid") Integer imgid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
			GoodItem good = Serv.getById(goodId);
			good.getImages().remove(imgServ.getById(imgid));
			Serv.update(good);
			imgServ.deleteById(imgid);
			return "redirect:/admin/admingood/"+goodId+"/updateGood";
		
		}
	
	@RequestMapping(value = "/deletePropValue/{valid}", method = RequestMethod.GET)
	  public String deletePropValue(@PathVariable (value="goodId") Integer goodid, @PathVariable (value="valid") Integer valid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
			GoodItem good = Serv.getById(goodid);
			good.deleteValue(valServ.getById(valid));
			Serv.update(good);
			return "redirect:/admin/admingood/"+goodid+"/updateGood";
		
		}
	


}
