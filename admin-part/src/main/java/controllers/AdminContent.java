package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import Model.service.ActionService;
import Model.service.AdressService;
import Model.service.CategoryService;
import Model.service.CommentService;
import Model.entity.Configuration;
import Model.dto_beans.ConfigurationBean;
import Model.service.ConfigurationService;
import Model.service.CountryService;
import Model.service.DeliveryTypeService;
import Model.service.GoodItemService;
import Model.service.GoodStateService;
import Model.entity.GoodStatus;
import Model.dto_beans.GoodStatusBean;
import Model.service.GoodStatusService;
import Model.entity.Guarantie;
import Model.dto_beans.GuarantieBean;
import Model.service.GuarantieService;
import Model.entity.Image;
import Model.others.ImageLoader;
import Model.service.ImageService;
import Model.others.Loader;
import Model.others.LoaderFactory;
import Model.entity.News;
import Model.dto_beans.NewsBean;
import Model.others.NewsImageLoaderFactory;
import Model.service.NewsService;
import Model.entity.NewsType;
import Model.dto_beans.NewsTypeBean;
import Model.service.NewsTypeService;
import Model.entity.Page;
import Model.dto_beans.PageBean;
import Model.entity.PageGroup;
import Model.dto_beans.PageGroupBean;
import Model.service.PageGroupService;
import Model.service.PageService;
import Model.entity.Producer;
import Model.service.ProducerService;
import Model.entity.Slider;
import Model.entity.SliderImage;
import Model.dto_beans.SliderImageBean;
import Model.others.SliderImageLoaderFactory;
import Model.service.SliderImageService;
import Model.service.SliderService;
import Model.entity.Video;

import Model.service.PayTypeService;
import Model.service.PhoneService;
import Model.dto_beans.ProducerBean;
import Model.service.PropSegmentService;
import Model.service.PropertyService;
import Model.service.RegionService;
import Model.service.TownService;
import Model.service.UserService;
import Model.service.ValueService;
import Model.service.VoteService;

@Controller
@RequestMapping(value = {"/admin/admincontent/"})
public class AdminContent {
	
	private Logger logger = LoggerFactory.getLogger(AdminContent.class);
	
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
	private TownService twnServ;
	@Autowired
	private CountryService cntrServ;
	@Autowired
	private PayTypeService ptpServ;
	
	@Autowired
	private DeliveryTypeService dlvServ;
	
	@Autowired
	private PhoneService phnServ;
	
	@Autowired
	private AdressService adrServ;
	
	@Autowired
	private RegionService regServ;
	
	@Autowired
	private ActionService actServ;
	
	@Autowired
	private ProducerService prodServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private GuarantieService guaServ;
	
	@Autowired
	private GoodStatusService gsttServ;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private ConfigurationService confServ;

	@Autowired
	private SliderService sldServ;
	
	@Autowired
	private SliderImageService sldimgServ;
	
	@Autowired
	private NewsService nwsServ;
	
	@Autowired
	private CountryService ctrServ;
	@Autowired
	private NewsTypeService ntpServ;
	

	
	@RequestMapping(value = "/addPagegroup", method = RequestMethod.GET)
	public String addPagegroup( Map<String, Object> map){
		PageGroupBean prod = new PageGroupBean();
		map.put("pagegroup", prod);
		map.put("type", "create");
		return "adminPagegroup";
		
	}
	
	
	
	@RequestMapping(value = "/updateProducer/{prodid}", method = RequestMethod.GET)
	public String updateProducer(@PathVariable (value = "prodid") Integer id,  @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		Producer prod = prodServ.getById(id);
		ProducerBean prodbean = new ProducerBean();
		prodbean.fillProducerBean(prod);
		prodbean.setId(id);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("producer", prodbean);
		map.put("type", "update");
		return "adminProducer";
		
	}
	
	@RequestMapping(value = "/updatePagegroup/{pgroupid}", method = RequestMethod.GET)
	public String updatePagegroup(@PathVariable (value = "pgroupid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		PageGroup prod = pgrServ.getById(id);
		PageGroupBean prodbean = new PageGroupBean();
		prodbean.fillPageGroupBean(prod);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("pagegroup", prodbean);
		map.put("type", "update");
		return "adminPagegroup";	
	}
	
	@RequestMapping(value = "/updatedProducer", method = RequestMethod.POST)
	public String updatedProducer(@ModelAttribute (value = "producer") @Valid ProducerBean prodbean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			prodbean.setCategory(catServ.getById(prodbean.getCategory().getId()));
			map.put("producer", prodbean);
			
			if(prodbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminProducer";
		}
		
		Producer pr = new Producer();
		if(prodbean.getId()!=null)
		pr = prodServ.getById(prodbean.getId());
		
		pr.fillProducer(prodbean);
		if(prodbean.getCategory().getId()!=null)
		pr.getCategories().add(catServ.getById(prodbean.getCategory().getId()));
		if(prodbean.getNewthumb()!=null){
		Integer last = pgrServ.getLastId();
		if(last==null) last = 0;
		Integer fldrid = (prodbean.getId()==null)?(last+1):prodbean.getId();
		if(prodbean.getId()==null)
		fldrid++;
		pr.setThumb(ImageLoader.loadthumb(prodbean.getNewthumb(), fldrid, "producers"));
		}
		prodServ.update(pr);
		map.put("result","success");
		prodbean = new ProducerBean();
		prodbean.fillProducerBean(pr);
		map.put("pagegroup", prodbean);
		if(prodbean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminProducer";
		
	}
	
	
	
	@RequestMapping(value = "/updatedPagegroup", method = RequestMethod.POST)
	public String updatedPagegroup(@ModelAttribute (value = "pagegroup") @Valid PageGroupBean pagegroupbean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("pagegroup", pagegroupbean);
			if(pagegroupbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminPagegroup";
		}
		
		PageGroup pggroup = new PageGroup();
		if(pagegroupbean.getId()!=null)
		pggroup =  pgrServ.getById(pagegroupbean.getId());
		pggroup.fillpagegroup(pagegroupbean);
		System.out.println(pagegroupbean.getId()+"    "+pagegroupbean+"    "+pgrServ.getLastId());
		if(pagegroupbean.getNewthumb()!=null){
		Integer last = pgrServ.getLastId();
		if(last==null) last = 0;
		Integer fldrid = (pagegroupbean.getId()==null)?(last+1):pagegroupbean.getId();
		pggroup.setThumb(ImageLoader.loadthumb(pagegroupbean.getNewthumb(), fldrid, "pagegroups"));
		}
		pgrServ.update(pggroup);
		map.put("result","success");
		pagegroupbean = new PageGroupBean();
		pagegroupbean.fillPageGroupBean(pggroup);
		map.put("pagegroup", pagegroupbean);
		if(pagegroupbean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminPagegroup";
		
	}
	
	
	@RequestMapping(value = "/addNewstype", method = RequestMethod.GET)
	public String addNewstype( Map<String, Object> map){
		NewsTypeBean newsbean = new NewsTypeBean();
		map.put("nwstype", newsbean);
		map.put("type", "create");
		return "adminNewstype";
		
	}
	
	@RequestMapping(value = "/updateNewstype/{nwstypeid}", method = RequestMethod.GET)
	public String updateNewstype(@PathVariable (value = "nwstypeid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		NewsType ntype = ntpServ.getById(id);
		NewsTypeBean newsbean = new NewsTypeBean();
		newsbean.constructbean(ntype);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("nwstype", newsbean);
		map.put("type", "update");
		return "adminNewstype";	
	}
	
	
	
	@RequestMapping(value = "/updatedNewstype", method = RequestMethod.POST)
	public String updatedNewstype(@ModelAttribute (value = "nwstype") @Valid NewsTypeBean nwstypebean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("nwstype", nwstypebean);
			if(nwstypebean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminNewstype";
		}
		
		NewsType ntype = new NewsType();
		if(nwstypebean.getId()!=null)
			ntype =  ntpServ.getById(nwstypebean.getId());
		ntype.constructfrombean(nwstypebean);
		
	
		map.put("result","success");
		nwstypebean = new NewsTypeBean();
		nwstypebean.constructbean(ntype);
		map.put("nwstype", nwstypebean);
		if(nwstypebean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminNewstype";
		
	}
	
	
	@RequestMapping(value = "/addGuarantie", method = RequestMethod.GET)
	public String addGuarantie( Map<String, Object> map){
		GuarantieBean guar = new GuarantieBean();
		map.put("guarantie", guar);
		map.put("type", "create");
		return "adminGuarantie";
		
	}
	
	@RequestMapping(value = "/updateGuarantie/{grid}", method = RequestMethod.GET)
	public String updateGuarantie(@PathVariable (value = "grid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		Guarantie guar = guaServ.getById(id);
		GuarantieBean guarbean = new GuarantieBean(guar);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("guarantie", guarbean);
		map.put("type", "update");
		return "adminGuarantie";	
	}
	
	
	@RequestMapping(value = "/updatedGuarantie", method = RequestMethod.POST)
	public String updatedPagegroup(@ModelAttribute (value = "guarantie") @Valid GuarantieBean guarantiebean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("guarantie", guarantiebean);
			if(guarantiebean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminGuarantie";
		}
		
		Guarantie guar = new Guarantie();
		if(guarantiebean.getId()!=null)
			guar =  guaServ.getById(guarantiebean.getId());
		guar.fillpagegroup(guarantiebean);
		
		guaServ.update(guar);
		map.put("result","success");
		guarantiebean = new GuarantieBean(guar);
		map.put("guarantie", guarantiebean);
		if(guarantiebean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminGuarantie";
		
	}
	
	
	@RequestMapping(value = "/addGoodstatus", method = RequestMethod.GET)
	public String addGoodstatus( Map<String, Object> map){
		GoodStatusBean gst = new GoodStatusBean();
		map.put("goodstatus", gst);
		map.put("type", "create");
		return "adminGoodstat";
		
	}
	
	@RequestMapping(value = "/updateGoodstatus/{gstid}", method = RequestMethod.GET)
	public String updateGoodstatus(@PathVariable (value = "gstid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		GoodStatus gst = gsttServ.getById(id);
		GoodStatusBean gstbean = new GoodStatusBean(gst);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("goodstatus", gstbean);
		map.put("type", "update");
		return "adminGoodstat";	
	}
	
	
	@RequestMapping(value = "/updatedGoodstatus", method = RequestMethod.POST)
	public String updatedGoodstatus(@ModelAttribute (value = "goodstatus") @Valid GoodStatusBean gstbean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("goodstatus", gstbean);
			if(gstbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminGoodstat";
		}
		
		GoodStatus gst = new GoodStatus();
		if(gstbean.getId()!=null)
			gst =  gsttServ.getById(gstbean.getId());
		gst.fillgoodstatus(gstbean);
		if(gstbean.getNewthumb()!=null){
			Integer last = gsttServ.getLastId();
			if(last==null) last = 0;
			Integer fldrid = (gstbean.getId()==null)?(last+1):gstbean.getId();
			gst.setThumb(ImageLoader.loadthumb(gstbean.getNewthumb(), fldrid, "goodstatuses"));
			}
		gsttServ.update(gst);
		map.put("result","success");
		gstbean = new GoodStatusBean(gst);
		map.put("goodstatus", gstbean);
		if(gstbean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminGoodstat";
		
	}
	
	
	
	@RequestMapping(value = "/addInfopage", method = RequestMethod.GET)
	public String addInfopage( Map<String, Object> map){
		PageBean gst = new PageBean();
		map.put("page", gst);
		map.put("type", "create");
		return "adminPage";
		
	}
	
	@RequestMapping(value = "/updateInfopage/{pgid}", method = RequestMethod.GET)
	public String updateInfopage(@PathVariable (value = "pgid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		Page pg = pageServ.getById(id);
		PageBean pgbean = new PageBean(pg);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("page", pgbean);
		map.put("type", "update");
		return "adminPage";	
	}
	
	
	@RequestMapping(value = "/updatedInfopage", method = RequestMethod.POST)
	public String updatedInfopage(@ModelAttribute (value = "page") @Valid PageBean gstbean, BindingResult bindingResult, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			System.out.println("rehreh");
			System.out.println(bindingResult.getFieldErrors());
			map.put("page", gstbean);
			if(gstbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminPage";
		}
		
		Page pg = new Page();
		if(gstbean.getId()!=null)
			pg =  pageServ.getById(gstbean.getId());
		pg.fillfrombean(gstbean);
		
		if(gstbean.getPagegroup()!=null && gstbean.getPagegroup().getId()!=null){
			pg.setPagegroup(pgrServ.getById(gstbean.getPagegroup().getId()));
		}
		
		if(gstbean.getParent_page()!=null && gstbean.getParent_page().getId()!=null){
			pg.setParent_page(pageServ.getById(gstbean.getParent_page().getId()));
		}
		pg.setIsinfopage(true);
		System.out.println(pg.getFullurl()+"  "+pg.getId()+"   "+pg.getH1()+"   "+pg.getH1()+"   "+pg.getUrl());
		pageServ.update(pg);
		map.put("result","success");
		gstbean = new PageBean(pg);
		map.put("page", gstbean);
		if(gstbean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminPage";
		
	}
	
	
	
	
	@RequestMapping(value = "/addConfiguration", method = RequestMethod.GET)
	public String addConfiguration( Map<String, Object> map){
		ConfigurationBean gst = new ConfigurationBean();
		map.put("configuration", gst);
		map.put("type", "create");
		return "adminConfiguration";
		
	}
	
	@RequestMapping(value = "/updateConfiguration/{pgid}", method = RequestMethod.GET)
	public String updateConfiguration(@PathVariable (value = "pgid") Integer id, @RequestParam(value = "result", required = false) Integer result, Map<String, Object> map){
		Configuration conf = confServ.getById(id);
		ConfigurationBean confbean = new ConfigurationBean(conf);
		if(result!=null && result==1)
		map.put("result", "success");
		map.put("configuration", confbean);
		map.put("type", "update");
		return "adminConfiguration";	
	}
	
	
	@RequestMapping(value = "/updatedConfiguration", method = RequestMethod.POST)
	public String updatedConfiguration(@ModelAttribute (value = "configuration") @Valid ConfigurationBean confbean, BindingResult bindingResult, HttpServletRequest request, Map<String, Object> map){
		if(bindingResult.hasErrors()){
			map.put("configuration", confbean);
			if(confbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminConfiguration";
		}
		
		String h = request.getSession().getServletContext().getRealPath("/WEB-INF").split("/WEB-INF")[0];
		System.out.println("оссссрэ   "+h);
		Configuration conf = new Configuration();
		if(confbean.getId()!=null)
			conf =  confServ.getById(confbean.getId());
		conf.fillfrombean(confbean);
		Slider slider = (confbean.getSlider().getId()!=null)?sldServ.getById(confbean.getSlider().getId()):new Slider();
		slider.fillfrombean(confbean.getSlider());
		slider.getImages().clear();
		if(!confbean.getSlider().getImages().isEmpty()){
			for(SliderImageBean sl:confbean.getSlider().getImages()){
				SliderImage img = new SliderImage();
				System.out.println("IDDD "+sl.getId());
				System.out.println(slider);
				Integer lastid = (sldServ.getLastId()!=null)?sldServ.getLastId()+1:1;
				System.out.println("lastiiid "+lastid+"  "+slider.getId());
				Integer fldrid = (slider.getId()==null)?lastid:slider.getId();
				int filenumber = imgServ.getLastId()+1;
				LoaderFactory loaderfactory = new SliderImageLoaderFactory();
				Loader loader = null;
						if(sl.getId()!=null)
						{
							System.out.println("not null");
							img =sldimgServ.getById(sl.getId());
							String url = img.getUrl();
							img.fillfrombean(sl);
							img.setUrl(url);
							img.setSlider(slider);
							String[] parts = url.split("/");
							if( sl.getNewimage()!=null && sl.getNewimage().getSize()>0 ){
							filenumber = Integer.parseInt(parts[parts.length-1].substring(3,parts.length-1));
							loader = loaderfactory.createLoader(confbean.getSlider(), slider, fldrid, filenumber);
							img.setUrl(loader.loadimage(sl.getNewimage(), sl));
							}
							slider.getImages().add(img);
						}
						
						else{
							
							System.out.println("null");
							img.fillfrombean(sl);
							img.setSlider(slider);
							loader = loaderfactory.createLoader(confbean.getSlider(), slider, fldrid, filenumber);
							img.setUrl(loader.loadimage(sl.getNewimage(), sl));
							if(sl.getNewimage()==null){img.setUrl(sl.getUrl());}
							slider.getImages().add(img);
						}
		
			}
			slider.setIsonmain(true);
			
	
		}
		conf.setSlider(slider);
		for(SliderImage f:conf.getSlider().getImages()){
			System.out.println(" ggg  "+f.getUrl());
		}
		
		System.out.println(conf);
		
		
		/**/
		
//		pg.setIsinfopage(true);
//		System.out.println(pg.getFullurl()+"  "+pg.getId()+"   "+pg.getH1()+"   "+pg.getH1()+"   "+pg.getUrl());
		
		confServ.update(conf);
		map.put("result","success");
		conf = confServ.getById(conf.getId());
		confbean = new ConfigurationBean(conf);
		map.put("configuration", confbean);
		if(confbean.getId()==null)
			map.put("type", "create");
		else
			map.put("type", "update");
		return "adminConfiguration";
		
	}
	
	
	@RequestMapping(value = "/addnews")
    public String addNews(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		NewsBean news = new NewsBean();
		Date date = new Date(System.currentTimeMillis());
		news.setCreation_date(new SimpleDateFormat("yyyy-MM-dd").format(date));
		news.setCreation_time(new SimpleDateFormat("hh:mm").format(date));
//		map.put("category", catServ.getCategoryById(catid));
		map.put("news", news);
		map.put("type", "create");
//		map.put("catid", catid);
		return "adminNews";
		
	}
	
	@RequestMapping(value = "/updatednews", method = RequestMethod.POST)
    public String updatedNews(@ModelAttribute (value="news") @Valid NewsBean newsbean, BindingResult bindingResult,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		if(bindingResult.hasErrors()){
			map.put("news", newsbean);
			if(newsbean.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			return "adminNews";
		}
		News news = new News();
		if(newsbean.getId()!=null){
			news = nwsServ.getById(newsbean.getId());
			news.setUpdating(new Date(System.currentTimeMillis()));
		}
		
		
			news.setFromNewsBean(newsbean);
			if(newsbean.getCategory()!=null){
				news.setCategory(catServ.getById(newsbean.getCategory().getId()));
			}
		
		if(newsbean.getType().getId()!=null)
			news.setType(ntpServ.getById(newsbean.getType().getId()));
	//	System.out.println("eeeee   "+newsbean.getId());
		
		List<Image> images = new ArrayList<Image>();
		List<Video> videos = new ArrayList<Video>();
			

		news.setImages(newsbean.getImages());
		news.setVideos(newsbean.getVideos());
		news.setTextpart(newsbean.getTextpart());
		
			
			Integer fldrid = (news.getId()==null)?((nwsServ.getLastId()!=null)?(nwsServ.getLastId()+1):1):news.getId();
				

			Integer y = imgServ.getLastId()+1;
			
			LoaderFactory lfactory = new NewsImageLoaderFactory();
			Loader lloader = lfactory.createLoader(newsbean, news, fldrid, y);
			lloader.load();
			
		

				
		for(Image i:news.getImages()){
			System.out.println(i.getUrl());
		}
		
		for(Video v:news.getVideos()){
			System.out.println(v.getUrl());
		}
		
		nwsServ.update(news);
		
		newsbean.setFromNews(news);
		map.put("news", newsbean);
		map.put("type", "update");
		map.put("result", "success");
		return "adminNews";
		
	}
	
	
}