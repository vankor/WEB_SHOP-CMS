package cc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.chainsaw.Main;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import Model.Action;
import Model.ActionService;
import Model.Adress;
import Model.AdressService;
import Model.AdressType;
import Model.AnonimBuck;
import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Bucket;
import Model.Category;
import Model.CategoryCompareGoods;
import Model.CategoryCreateBean;
import Model.CategoryService;
import Model.ChoiseItem;
import Model.Comment;
import Model.CommentService;
import Model.CompareGoodsSet;
import Model.Configuration;
import Model.ConfigurationService;
import Model.Country;
import Model.CountryBean;
import Model.CountryService;
import Model.DeliveryType;
import Model.DeliveryTypeService;
import Model.GoodCollection;
import Model.GoodCompareBean;
import Model.GoodItem;
import Model.GoodItemCreationBean;
import Model.GoodItemService;
import Model.GoodState;
import Model.GoodStateService;
import Model.GoodStatesBean;
import Model.GoodUpdateBean;
import Model.Image;
import Model.ImageService;
import Model.Item;
import Model.LoginBean;
import Model.News;
import Model.NewsPart;
import Model.NewsService;
import Model.NewsType;
import Model.NewsTypeService;
import Model.Order;
import Model.OrderRow;
import Model.OrderService;
import Model.OrderSumCalculator;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.PayType;
import Model.PayTypeService;
import Model.PhoneNumber;
import Model.PhoneNumberType;
import Model.PhoneService;
import Model.PropSegment;
import Model.Property;
import Model.PropertyCompareBean;
import Model.PropertyService;
import Model.Row;
import Model.SearchForm;
import Model.TextPart;
import Model.Town;
import Model.TownService;
import Model.Town_Delivery;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueBean;
import Model.ValueService;
import Model.Vote;
import Model.VoteBean;
import Model.VoteService;

@Controller

public class CommonController {
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	public static final Integer PAGE_SIZE = 16;
	
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
	private OrderService ordServ;
	
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
	private NewsTypeService ntpServ;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	
	@Autowired
	private ConfigurationService confServ;
	
	@Autowired
	private CountryService ctrServ;
	
	public void setSessionParameters(User user, AnonimBuck bucket, HttpServletRequest request, HttpSession sess){
		if (user==null){
			user = new User();
			user.setIp(request.getLocalAddr());
			user.setLogin("Гость");
		}
		if(bucket==null){bucket = new AnonimBuck();}
		user.setAnonimbucket(bucket);
			
	}
	
	@RequestMapping("/good/addtobuck/{goodid}")
	public String addGoodtoBuck(@PathVariable (value="goodid") Integer id, HttpSession sess, Map<String, Object> map, HttpServletRequest request){
		User t = (User) request.getAttribute("user");
		if(t.getId() == 0){return "redirect:/addgood/{goodid}";}
		GoodItem good = Serv.getById(id);
		Bucket buck;
		if(t.getId() == 0 && t.getIp() != null){
			if(t.getBuck().isEmpty()){
			buck = new Bucket();
			buck.setName("My Bucket");
			t.addBucket(buck);
			}
			buck = t.getBuck().get(0);
			buck.addGood(good);
			sess.setAttribute("usr", t);
			map.put("buck", new Bucket());
			map.put("buckList", t.getBuck());
			System.out.println(t.getBuck().get(0).getGoods().get(0).getName());
		}
		
		return "bucks";
	}
	

	
	@RequestMapping("/addgood/{goodid}")
	public String addGood(@PathVariable (value="goodid") Integer id, HttpSession sess, Map<String, Object> map, HttpServletRequest request){
		User t = (User) request.getAttribute("user");
		GoodItem good = Serv.getById(id);
//		Hibernate.initialize(t);
//		List bucks = t.getBuck();
//		if(!bucks.isEmpty()){
//			b = (Bucket) bucks.get(0);
//			b.addGood(good);
//		}
//		ArrayList<GoodItem> usergoods = (ArrayList<GoodItem>)sess.getAttribute("currgoods");
//		usergoods.add(good);
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		List <AnonimBuck> bucks = new ArrayList<AnonimBuck>();

		bucks.add(buck);

		map.put("buckList", bucks);
		sess.setAttribute("usr", t);
		sess.setAttribute("currbuck", buck);

		return "bucks";
	}



	
	
	@RequestMapping(value = "/settown", method = RequestMethod.GET)
	public String setCity(@RequestParam (value = "cid") Integer id,HttpServletRequest request, Map<String, Object> map, HttpSession sess) {
		sess.setAttribute("cityid", id);
		String referer = request.getHeader("referer");
		return "redirect:"+referer;
	}
	
	@RequestMapping("/usrmenu/usrbuck/listbuck/good/{goodid}")
	public String showGoodfromBuck(@PathVariable (value = "goodid") Integer id, Map<String, Object> mapcomm) {
		return "good";}
	
	
	@RequestMapping("/index")
	public String listGoods(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		
		
		
		
		map.put("goodList", Serv.getTopGoods());
		
//		setSubCategs(roots,4);
//		printSubCategs(roots,4);
		List<Action> allactions = actServ.getTopActions();
	
		Set<Action> randomtopactions = new TreeSet<Action>();
		Integer actsize = (allactions.size()>=4)?4:allactions.size();
		
		Random rand = new Random();
		Set<Integer> randomid = new TreeSet<Integer>();
		while(randomtopactions.size()<actsize){
			Integer o = rand.nextInt(allactions.size());

			randomtopactions.add(allactions.get(o));
			
		}
		
		
		Configuration config = confServ.getActiveConfiguration();
		map.put("config", config);
		Set<Category> topcategories = catServ.getTopCategories();

		map.put("topcatcount", topcategories.size());
		
		map.put("topcategories", topcategories);
		System.out.println("Топ категории "+topcategories);
		
		map.put("mainnews", nwsServ.getTopNews());
		map.put("seasongoods", Serv.getSeasonGoods());
		map.put("actions", randomtopactions);
		
		
//		map.put("currenttown", currenttown);
		map.put("residenttowns", twnServ.getResidentTowns());
		
		map.put("searchForm", new SearchForm());
		return "start";
	}
	
	@RequestMapping("/loadheader")
	public String loadheader(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		map.put("section", "header");
		System.out.println("/loadheader");
		return "header";
		
	}
	

	
	@RequestMapping("/loadmenu")
	public String loadmenu(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		List<Category> roots = catServ.getRootCategories();
	//	setSubCategs(roots,4);
		printSubCategs(roots,4);
		map.put("currentCatList", roots);
		map.put("section", "menu");
		System.out.println("/loadmenu");
		return "header";
	}

	public void printSubCategs(List<Category> cats, int levels){
		if(levels == 0){return;}
		if(cats == null && cats.isEmpty() ){return;}
		for(Category cat : cats){
			System.out.println(cat.getName());
			printSubCategs(cat.getSubcategs(), levels-1);
		}
		
	}
	
/*	public void setSubCategs(List<Category> cats, int levels){
		if(levels == 0){return;}
		if(cats == null && cats.isEmpty() ){return;}
		for(Category cat : cats){
			List<Category> subcats = catServ.listCategory(cat);
			cat.setSubcategs(subcats);
			setSubCategs(subcats, levels-1);
		}
		
	}*/

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("good") GoodItem good,
            BindingResult result) {

        Serv.add(good);

        return "redirect:/index";
    }
	
	

	
	@RequestMapping(value = "/deleteState/{stateId}")
	public String deleteGoodState(@PathVariable("stateId") Integer id){
		gstServ.deleteById(id);
		return "redirect:/addGoodState";
		
	}
	
	
	
	@RequestMapping(value = "/choiseWindow", method = RequestMethod.POST)
	public String choiseWindow( Map <String, Object> map, @RequestParam(value="idfld") String idfld, @RequestParam(value="section") String section){
//		System.out.println("regbreg");
		List<Category> roots = catServ.getRootCategories();
//		setSubCategs(roots,4);
		section = section.toLowerCase();
		System.out.println("hhhhhhhhh"+"   "+section+"   "+idfld+roots);
		
		map.put("catList", roots);
		map.put("section", section);
		map.put("idfld", idfld);
		return "commsection";
		
	}
	

	@RequestMapping(value = "/choiseCategory", method = RequestMethod.POST)
	public String choiseGood( Map <String, Object> map, @RequestParam(value="idfld") String idfld, @RequestParam(value="section") String section){
//		System.out.println("regbreg");
		List<Category> roots = catServ.getRootCategories();
	//	setSubCategs(roots,4);
		System.out.println("hhhhhhhhh"+"   "+section+"   "+idfld+roots);
		map.put("catList", roots);
		map.put("section", section);
		map.put("idfld", idfld);
		return "commsection";
		
	}
	
	
	
	
	
	@RequestMapping(value = "/addPropValue", method = RequestMethod.POST)
	public String addPropValue( Map <String, Object> map, @RequestParam(value="propid") Integer propid,
			@RequestParam(value="section") String section
			, @RequestParam(value="lval") Integer lval
			, @RequestParam(value="lprop") Integer lprop
			, @RequestParam(value="lsegm") Integer lsegm){
//		System.out.println("regbreg");
		Property prop = propServ.getById(propid);
		map.put("propvals", prop.getVal());
		map.put("section", section);
		map.put("lval", lval);
		map.put("lprop", lprop);
		map.put("lsegm", lsegm);
		return "commsection";
		
	}
	
	
	@RequestMapping(value = "/showbucket", method = RequestMethod.POST)
	public String showBucket(Map<String, Object> map,  HttpSession sess, HttpServletRequest request) {
		User t = (User)request.getAttribute("user");
		
		AnonimBuck buck = (AnonimBuck)sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		
		GoodCollection g = (GoodCollection)buck;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		double sum = calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
	//	map.put("section", "bucket");
		return "bucket";
		
	}
	
	
	@RequestMapping(value = "/choisePropValue", method = RequestMethod.POST)
	public @ResponseBody Value choisePropValue (@RequestParam(value="id") Integer valid){
//		System.out.println("regbreg");
		System.out.println(valid);
		Value val = valServ.getById(valid);
		System.out.println("eeeeeeeeeeee");
		return val;
		
	}
	
	
	@RequestMapping(value = "/choiseGood", method = RequestMethod.POST)
	public @ResponseBody GoodItem choiseGood(@RequestParam(value="id") Integer goodid){
		
		return Serv.getById(goodid);
		
	}
	
	@RequestMapping(value = "/choiseCat", method = RequestMethod.POST)
	public @ResponseBody Category choiseCategory(@RequestParam(value="id") Integer catid){
	//	System.out.println(catid);
		return catServ.getById(catid);
		
	}
	
		
	@RequestMapping(value = "/addVote", method = RequestMethod.GET)
	public String addVote(Map<String, Object> map){
		List<Vote> votelst = new ArrayList<Vote>();
		VoteBean votes = new VoteBean();
		votelst = voteServ.getAll();
   //  	if(!states.isEmpty()){
	//	map.put("states", states);
	//	}
		votes.setvotes(votelst);
  //   	map.put("states", new ArrayList<GoodState>());
		map.put("votes", votes);
//		model.addAttribute("type", "create");
        return "addVotes";
		
	}
	
	  @RequestMapping(value = "/logindialog",method = RequestMethod.POST)
      public String logindialog(@RequestParam (value = "section") String section, Map <String, Object> map, HttpServletRequest req) {
		  
		  System.out.println(section);
          map.put("login", new LoginBean());
          map.put("section", section);
          return "login";
      }
	
	@RequestMapping(value = "/addedVote",method = RequestMethod.POST)
    public String addedVote(@ModelAttribute(value = "votes") VoteBean votes, Map<String, Object> map,HttpServletRequest request) {
		
		System.out.println(votes.getvotes().size());
		
		for(Vote vote: votes.getvotes()){
		System.out.println("Айди:"+vote.getId());
		if(vote.getId()!=null){
		voteServ.update(vote);
		}
		else{voteServ.update(vote);}
		}
		
		
        return "redirect:/addVote";
    }
	
	


	
	

	
	@RequestMapping(value = "/nd/img/ul/active_li.gif", method = RequestMethod.GET)
	public String blabla(@PathVariable(value = "catid") Integer catid, Model model){
		 return null;
		
	}
	
	
	@RequestMapping("/order")
    public String order(Map<String, Object> map, HttpSession sess, ServletRequest request) {
		User t = (User) request.getAttribute("user");
		if(t!=null){
			map.put("user", t);
			Town town = t.getTown();
//			List<DeliveryType> towndtp = new ArrayList<DeliveryType>();
			if(town!=null){
					for(Town_Delivery dtp: town.getTowndeliveries()){
					map.put("dtype", dtp.getDeliveryType());
					break;
				}
				
			}
			
		}
		map.put("paytypes", ptpServ.getAll());
		map.put("deliverytypes", dlvServ.getAll());
		map.put("sessid", sess.getId());
		return "order";
		
	}
	
	
	
	@RequestMapping("/ordergoods")
    public String orderGoods(@RequestParam (value = "section") String section, Map<String, Object> map, HttpSession sess) {
		AnonimBuck buck = (AnonimBuck) sess.getAttribute("currbuck");
		if(buck==null){
			buck = new AnonimBuck();
		}
		map.put("bucket", buck);
		OrderSumCalculator calc = new OrderSumCalculator(buck);
		double sum = calc.getSumValue();
		System.out.println("Сумма: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("bucketsize", buck.getSize());
		map.put("section", section);
		String text = "";
		try {
			text = Base64.encode("оплата за заказ с интернет-магазина".getBytes("UTF-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("text", text);
		
		return "commsection";
		
	}
	
	
	
	@RequestMapping(value = "/citysearch", method = RequestMethod.GET)
    public String citySearch(@RequestParam (value = "q") String town, Map<String, Object> map, HttpSession sess) {
		String name ="";
		try {
		name = new String(town.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		Set<Town> towns = twnServ.getTownsByName(name);
		List<ChoiseItem> ff = new ArrayList<ChoiseItem>();
		for(Town t: towns){
			ff.add(new ChoiseItem(t.getName(), t.getId().toString()));
		}
		map.put("towns", towns);
		map.put("section", "towns");
		String g = "";
		try {
			 
			// convert user object to json string, and save to a file
	//		mapper.writeValue(new File("d:\\towns.json"), towns);
	 
			// display to console
			g = mapper.writeValueAsString(ff);
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
			System.out.println(g);
		
		return "autocomplete";
		
	}
	
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public String sendGoods(Map <String, Object> map, HttpSession sess, 
			@RequestParam(value="clientname") String clientname, 
			@RequestParam(value="clientphone_number[]") String clientphone_number,
			@RequestParam(value="clientemail") String clientemail,
			@RequestParam(value="clientcomment") String clientcomment,
			@RequestParam(value="clientsrochnost") String clientsrochnost,
			@RequestParam(value="client_delivdate") String client_delivdate,
			@RequestParam(value="client_delivtime") String client_delivtime,
			@RequestParam(value="clienttown_f") String clienttown_name,
			@RequestParam(value="clienttown") String clienttown_id,
			@RequestParam(value="clientoplata") Integer clientoplata,
			@RequestParam(value="clientdeliverytype") Integer clientdeliverytype,
			@RequestParam(value="type_street") String type_street,
			@RequestParam(value="street") String street,
			@RequestParam(value="house") String house,
			@RequestParam(value="flor") String flor,
			@RequestParam(value="room") String room,
			@RequestParam(value="nzanos") String nzanos,
			@RequestParam(value="netaj") String netaj,
			@RequestParam(value="nlift") String nlift,
			@RequestParam(value="sessid") String sessid,
			@RequestParam(value="action") String action,
			@RequestParam(value="srok_cred") String srok_cred,
			@RequestParam(value="client_company") String client_company,
			@RequestParam(value="client_okpo") String client_okpo,
			@RequestParam(value="filial_adress") Integer filial_adress,
			ServletRequest request
						
			){
		
		System.out.println("clientname "+clientname.split(",")[0]);
		System.out.println("clientphone_number "+clientphone_number.split(","));
		System.out.println("clientemail "+clientemail.split(",")[0]);
		System.out.println("clientcomment "+clientcomment.split(",")[0]);
		System.out.println("clientsrochnost "+clientsrochnost);
		System.out.println("client_delivdate "+client_delivdate.split(",")[0]);
		System.out.println("client_delivtime "+client_delivtime.split(",")[0]);
		System.out.println("clienttown_name "+clienttown_name);
		System.out.println("clienttown_id "+clienttown_id);
		System.out.println("clientoplata "+clientoplata);
		System.out.println("clientdeliverytype "+clientdeliverytype);
		System.out.println("type_street "+type_street.split(",")[0]);
		System.out.println("street "+street.split(",")[0]);
		System.out.println("house "+house.split(",")[0]);
		System.out.println("flor "+flor.split(",")[0]);
		System.out.println("room "+room.split(",")[0]);
		System.out.println("nzanos "+nzanos);
		System.out.println("netaj "+netaj.split(",")[0]);
		System.out.println("nlift "+nlift.split(",")[0]);
		System.out.println("sessid "+sessid.split(",")[0]);
		System.out.println("srok_cred "+srok_cred.split(",")[0]);
		System.out.println("client_company "+client_company.split(",")[0]);
		System.out.println("client_okpo "+client_okpo.split(",")[0]);
		
		
	
		String[] phones = clientphone_number.split(",");
		
		User t = (User) request.getAttribute("user");
		AnonimBuck c = (AnonimBuck) sess.getAttribute("currbuck");
		if(c==null){
			return "NO GOODS in Bucket";
//			c = new AnonimBuck();
		}
		Order ord = new Order();
		
		ord.setTime(new Date(System.currentTimeMillis()));
		ord.setRows(c.getRows());
		OrderSumCalculator calc = new OrderSumCalculator(c);
		ord.setAmount(calc.getSumValue());
		ord.setSessid(sessid);
		Town town = twnServ.getById(Integer.parseInt(clienttown_id));
		if(t!=null){
			ord.setUsr(t);
		
		}
		
			ord.setClientname(clientname);
			ord.setClientemail(clientemail);
			
			ord.setClienttown(town);
			ord.setComment(clientcomment);
			for(int i = 0; i< phones.length; i++){
				PhoneNumber phn = new PhoneNumber();
				String code = phones[i].substring(phones[i].indexOf("(")+1,phones[i].indexOf(")"));
				System.out.println(code);
				phn.setNumb(phones[i]);
				Town twn = twnServ.getTownsByCode(Integer.parseInt(code));
				if(twn != null){
					phn.setTown(twn);
					phn.setPhtype(PhoneNumberType.STATIONAR);
				}
				else{
					phn.setPhtype(PhoneNumberType.MOBILE);
				}
				
				ord.addClient_phone(phn);
				
			}
		
		PayType ptp = ptpServ.getById(clientoplata);
		DeliveryType dlv = dlvServ.getById(clientdeliverytype);
		ord.setPaytype(ptp);
		ord.setDeliverytype(dlv);
		StringBuilder adress = new StringBuilder();
		Adress adr = new Adress();
//		Adress adrexist = adrServ.getAdressByParams(town, type_street.split(",")[0], street.split(",")[0], house.split(",")[0], room.split(",")[0]);
//		if(adrexist!=null){
//			System.out.println("ADRESS EXSTTTTTTT");
//			adr = adrexist;
//		}
	//	else{
		if(dlv.getShortname().equals("adress")){
			adress.append(""+type_street.split(",")[0]+" "+street.split(",")[0]+" "+house.split(",")[0]+", этаж "+flor.split(",")[0]+", квартира "+room.split(",")[0]);
			
			adr.setStreet_type(type_street.split(",")[0]);
			adr.setStreet_name(street.split(",")[0]);
			adr.setHouse_num(house.split(",")[0]);
			adr.setLevel(flor.split(",")[0]);
			adr.setRoom_num(room.split(",")[0]);
			adr.setAdresstype(AdressType.CLIENT);
//		}
		
		if(dlv.getShortname().equals("samovyvoz")){
			adr = adrServ.getById(filial_adress);
	//		adress.append("Самовывоз по адресу: "+filial_adress.split(",")[0]);
			adr.setAdresstype(AdressType.RESIDENT);
			
		}
		}

		adr.setTown(town);
		ord.setAdress(adr);
		
		if(ptp.getShortname().equals("nocash")){
			ord.setClient_company(client_company.split(",")[0]);
			ord.setClient_okpo(client_okpo.split(",")[0]);
		}
		
		if(ptp.getShortname().equals("onlinecred")){
			ord.setCred_term(srok_cred.split(",")[0]);
		}
		
		if(clientsrochnost.split(",").length==2 && clientsrochnost.split(",")[1].equals("1")){
			System.out.println("Срочнооооо!");
			String str_date = client_delivdate.split(",")[0]+" "+client_delivtime.split(",")[0];
			try {
				Date del_time = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(str_date);
				ord.setDelivtime(del_time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		ordServ.add(ord);
//		ord = ordServ.getOrderById(ord.getId());
		System.out.println("Заказчик: "+ord.getClientname());
		System.out.println("Сeccия: " + ord.getSessid());
		System.out.println("Пользователь: " + ord.getUsr());
		System.out.println("Емейл: "+ord.getClientemail());
		System.out.println("Комментарий: "+ord.getComment());
		System.out.println("Номера телефонов: ");
		for(PhoneNumber nmb: ord.getClient_phones()){
			System.out.println(nmb.getNumb());
		}
//		System.out.println("Дата доставки: "+ord.getDelivdate());
		System.out.println("Время доставки: "+ord.getDelivtime());
		System.out.println("Город: "+ ord.getClienttown().getName());
		System.out.println("Тип оплаты: "+ ord.getPaytype().getName());
		System.out.println("Компания клиента: "+ ord.getClient_company());
		System.out.println("ОКПО компании клиента: "+ ord.getClient_okpo());
		System.out.println("Срок кредита: "+ ord.getCred_term());
		System.out.println("Тип доставки: "+ ord.getDeliverytype().getName());
		System.out.println(""+ord.getAdress().getStreet_type()+" "+ord.getAdress().getStreet_name()+" "+ord.getAdress().getHouse_num()+", этаж "+ord.getAdress().getLevel()+", квартира "+ord.getAdress().getRoom_num());
		System.out.println("Сумма заказа: " + ord.getAmount());
		int i = 1;
		for(Row r: ord.getRows()){
			 System.out.println(""+i+")"+r.getGood().getName()+". Цена: "+r.getGood().getPrice()+". Количество: "+r.getGoodcount());
			 i++;
		 }
		
		
		
//		OrderSender sender = new OrderSender();
		
//		try {
//			sender.send(ord);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return "YES";
		
	}
	
	
	@RequestMapping(value = "/changeparams", method = RequestMethod.POST)
	public String sendGoods(Map <String, Object> map, HttpSession sess, 
			@RequestParam(value="city_id") Integer city_id, 
			@RequestParam(value="payment_id") Integer payment_id,
			@RequestParam(value="delivery_id") Integer delivery_id){
		
		System.out.println(city_id+"   "+payment_id+"    "+delivery_id);
		Town t =new Town();
		List<DeliveryType> deltypes = new ArrayList<DeliveryType>();
		if(city_id!=null){
		 t = twnServ.getById(city_id);
		 for(Town_Delivery tdlv:t.getTowndeliveries()){
			 deltypes.add(tdlv.getDeliveryType());
		 }
		
		}
		else{
			deltypes = new ArrayList(dlvServ.getAll());
		}
		
		PayType ptype = ptpServ.getById(payment_id);
		DeliveryType d = dlvServ.getById(delivery_id);
		System.out.println(d.getName());
		if(d.getShortname().equals("samovyvoz")){
//			Set<Adress> residents = adrServ.getAdressByType(AdressType.RESIDENT);
			List<Adress> residents = t.getResidents();
			map.put("residents", residents);
		}
		
		if(deltypes.contains(d)){
			map.put("deliverytype", d);
			System.out.println(d.getShortname());
	//		map.put("deliverytype", dlvServ.getDeliveryTypeById(delivery_id));
		}
				
		else{
			for(DeliveryType f: deltypes){
				map.put("deliverytype", f);
				break;
			}
			
		}
		System.out.println(ptype.getName());
		System.out.println(d.getName());
		System.out.println(d.getShortname());
		map.put("ptype", ptype);
		map.put("town", t);
		map.put("paytypes", ptpServ.getAll());
		map.put("deliverytypes", deltypes);
		map.put("section", "orderparams");
		
		System.out.println(t.getName());
		
		return "commsection";
		
	}
	
	
	
	@RequestMapping("/popup")
    public String popupBucket(@RequestParam (value = "section") String section, @RequestParam (value = "colid") Integer colid, Map<String, Object> map, HttpSession sess) {

		System.out.println(section + "  "+colid);
		OrderSumCalculator calc = null;
		
		if(section.equals("bucket") && colid==-1){
			AnonimBuck buck = (AnonimBuck) sess.getAttribute("currbuck");
			if(buck==null){
				buck = new AnonimBuck();
			}
			map.put("bucket", buck);
			map.put("buckrows", buck.getRows());
			map.put("buck", buck);
			map.put("bucketsize", buck.getSize());
			calc = new OrderSumCalculator(buck);
		}
		
		if(section.equals("orderpositions")&& colid>=0){
			Order ord = ordServ.getById(colid);
			map.put("order", ord);
			map.put("orderrows",ord.getRows());
			calc = new OrderSumCalculator(ord);
		}
		
		if(calc!=null){
			double sum = calc.getSumValue();
			map.put("sum", sum);
		}
		
		
		
		map.put("section", section);
		return "commsection";
		
	}
	
	@RequestMapping(value = "/resmerchurl", method = RequestMethod.POST)
    public @ResponseBody String resultWebMoneyPay(@RequestParam (value = "LMI_PREREQUEST") Integer pre_req, 
    		@RequestParam (value = "LMI_PAYEE_PURSE") String purse,
    		@RequestParam (value = "LMI_PAYMENT_NO") String pay_num,
    		@RequestParam (value = "LMI_PAYMENT_AMOUNT") Double amount,
    		@RequestParam (value = "LMI_PAYER_WM") String wmid,
    		@RequestParam (value = "LMI_PAYMER_NUMBER") String paymer_number,
    		@RequestParam (value = "LMI_WMCHECK_NUMBER") String wmcheck_number,
    		@RequestParam (value = "LMI_HASH") String hash,
    		@RequestParam (value = "LMI_SYS_TRANS_DATE") String trans_date,
    		Map<String, Object> map, HttpSession sess) {
	
		String result = "";
		
		if(pre_req==1){
			AnonimBuck buck = (AnonimBuck) sess.getAttribute("currbuck");
			OrderSumCalculator calc = new OrderSumCalculator((GoodCollection)buck);
		//	bucketrows = calc.createReport();
		//	for(OrderRowBean b:bucketrows){System.out.println(b.getGood().getName()+"    "+b.getGoodcount());}
			Iterator iter = calc.getOrdmap().entrySet().iterator();
			Entry<GoodCollection, Double> entry = (Entry<GoodCollection, Double>)iter.next();
			double sum = entry.getValue();
			if(sum!=amount){
				return "Суммы не свопадают!!!";
			}
			
			return "yes";
			
		}
		else{
			Order ord = new Order();
			ord.setTime(new Date(System.currentTimeMillis()));
			ord.setAmount(amount);
			ordServ.add(ord);
			
			OrderSender sender = new OrderSender();
			
			try {
				sender.send(ord);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 result = "<br>Order #<b>" + ord.getId() + " is created at " + new Date().toString() + " and will be handled) </b>";
		//	map.put("order",ord);
					
			System.out.println("succes");
		}
		return result;
		
	}
	
	
	@RequestMapping("/succmerchurl")
    public String succesWebMoneyPay(@RequestParam (value = "section") String section, Map<String, Object> map, HttpSession sess) {
		return section;
		
	}
	
	
	
	@RequestMapping(value = "/seasongoods", method = RequestMethod.GET)
    public String seasonGoods(@PathVariable (value = "catId") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		List<Category> roots = catServ.getRootCategories();
//		setSubCategs(roots,4);
		map.put("bucketsize", bucket.getSize());
		Category cat = catServ.getById(id);
		List<Category> subcategs  = catServ.listCategoryById(id);
		System.out.println(cat.getIsFinalCategory());
		String currenturl = request.getServletPath();
		System.out.println("Текущий УРЛ:"+currenturl);
		map.put("currenturl", currenturl);
		map.put("catList", roots);
		map.put("currentCatList", subcategs);
		map.put("paramcontainer", new ParamContainer());
		map.put("catid", id);
		map.put("category", cat);
		map.put("currenttown", twnServ.getById(908));
		map.put("residenttowns", twnServ.getResidentTowns());
		for(Town t:twnServ.getResidentTowns()){
			System.out.println(t.getName());
		}
		map.put("searchForm", new SearchForm());
		
		
		if(cat.getIsFinalCategory()){
			List<ParamContainer> params = new ArrayList<ParamContainer>();
			List<PropSegment> segments = cat.getPropsegments();
			for(PropSegment segm: segments){
				for(Property prop: segm.getProperties()){
					ParamContainer param = new ParamContainer();
					param.setPropid(prop.getId());
					param.setPropname(prop.getName());
					for(Value val:prop.getVal()){
						ValueBean valbean = new ValueBean(val);
						param.addParam(valbean);
					}
					params.add(param);
				}
			}
			

			ParamFilterBean filter = new ParamFilterBean();
			filter.setParams(params);
			map.put("paramfilter", filter);
			}
			List<GoodItem> goods = Serv.getSeasonGoods();
		
			map.put("count", goods.size());
			map.put("maxPrice", catServ.getExtremePrice(cat, "max"));
			map.put("minPrice", catServ.getExtremePrice(cat, "min"));
			for(GoodItem g: goods){
				System.out.println(g.getName()+"      " + g.getAverage_rating());
	//			g.setTotalVotes();
			}
			
			map.put("goodList", goods);
			
			return "bb1";
		
			
		}


	
	@RequestMapping(value = "/livesearch", method = RequestMethod.POST)
	public @ResponseBody List<GoodItem> liveSearchGood(@RequestParam (value = "search") String val){
		List<GoodItem> goods = Serv.searchGood(val);
		return goods;
	}
	
	@RequestMapping(value = "/search/pagin/{pgnum}", method = RequestMethod.GET)
	public String searchGood(@RequestParam (value = "req") String val, @PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map){
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		System.out.println("eeee "+val+"   "+begin+"   "+end);
		List<GoodItem> goods = Serv.searchGood(val, begin, end);
		map.put("goods", goods);
		map.put("req", val);
		Integer pagecount = Math.round(goods.size()/PAGE_SIZE);
		map.put("pagecount", pagecount);
		map.put("currentpage", pagecount);
		System.out.println(goods);
		return "searchresults";
	}
	
	
	@RequestMapping(value = "/addedParams",method = RequestMethod.GET)
    public String addedCatParams(@ModelAttribute(value = "category") Category cat, Map<String, Object> map) {
		
		catServ.add(cat);
		List <Category> t =catServ.getRootCategories();
		
        return "redirect:/index";
    }
	
	@RequestMapping(value = "/addGood")
    public String addGood(Map<String, Object> map) {
		map.put("good", new GoodItem());
		return "addGood";
    }
	
	@RequestMapping(value = "/loadtopgoods", method = RequestMethod.POST)
    public String addGood(@RequestParam(value="cat_id") Integer cat_id, HttpSession sess, Map<String, Object> map) {
		map.put("section", "topgoods");
		Category cat = catServ.getById(cat_id);
		cat.setTopgoods(Serv.getTopGoods(cat));
		map.put("category", cat);
		map.put("goodcount", cat.getTopgoods().size());
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		return "topgoods";
    }
	
	@RequestMapping(value = "/actions/{pgnum}", method = RequestMethod.GET)
    public String listActions(@PathVariable (value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		final Integer PAGE_SIZE = 12;
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length; i++){
			if(i<urlparts.length-1){
			entityurl.append(urlparts[i]+"/");}
		/*	else{
			pgnum = Integer.parseInt(urlparts[i]);
			
			}	*/
		}
		List<Category> categories = catServ.getRootCategories();
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
		fillsubcategoryactions(catactions, categories);
		map.put("catactions", catactions);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Integer pagecount = actServ.getCount()/PAGE_SIZE;
		map.put("pagecount", pagecount);
		map.put("currentCatList", categories);
		map.put("entityurl", entityurl.toString());
		map.put("actions", actServ.listPage(null, begin, end));
		return "actionList";
		
	}
	
	@RequestMapping(value = "/category/{catid}/actions/{pgnum}", method = RequestMethod.GET)
	public String listCategoryActions(@PathVariable(value = "catid") Integer catid, @PathVariable(value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		final Integer PAGE_SIZE = 12;
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		for(int i =0; i<urlparts.length; i++){
			if(i<urlparts.length-1){
			entityurl.append(urlparts[i]+"/");}
		/*	else{
			pgnum = Integer.parseInt(urlparts[i]);
			
			}	*/
		}
		
		Category cat = catServ.getById(catid);
		List<Category> categories = catServ.getRootCategories();
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
		fillsubcategoryactions(catactions, categories);
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Integer pagecount = actServ.getCount()/PAGE_SIZE;
		map.put("catactions", catactions);
		map.put("pagecount", pagecount);
		map.put("currentCatList", categories);
		map.put("actions", actServ.listCategoryActions(cat));
		map.put("entityurl", entityurl.toString());
		return "actionList";
		
	}
	
	@RequestMapping(value = "/newslist/{pgnum}", method = RequestMethod.GET)
	public String newsList(@RequestParam(value = "type") Integer nwstp_id, @PathVariable(value = "pgnum") Integer pgnum, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		final Integer PAGE_SIZE = 12;

		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		String slash = "/";
		for(int i =0; i<urlparts.length; i++){
			if(i<urlparts.length-1){
			entityurl.append(urlparts[i]+slash);}

		}
		
		NewsType nwstp = ntpServ.getById(nwstp_id);
		
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
//		fillsubcategoryactions(catactions, categories);
		
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Integer pagecount = nwsServ.getCount()/PAGE_SIZE;
		Set<News> news = nwsServ.listNewsPageByType(nwstp, begin, end);
		
		Map<NewsType, Long> newstypeList = ntpServ.getAllNewsTypeNewsCount();
		map.put("pagecount", pagecount);
		map.put("newsList", news);
		map.put("newsTypes", newstypeList);
		map.put("currnewstype", nwstp);
		map.put("entityurl", entityurl.toString());
//		map.put("currentCatList", catServ.getRootCategories());
		return "newsList";
		
	}
	
	@RequestMapping(value = "/category/{catid}/newslist/{pgnum}", method = RequestMethod.GET)
	public String categoryNewsList(@RequestParam(required = false, value = "type") Integer nwstp_id, @PathVariable(value = "pgnum") Integer pgnum, @PathVariable(value = "catid") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		final Integer PAGE_SIZE = 12;
	
		
		
		StringBuilder entityurl = new StringBuilder();
		String[] urlparts = request.getHeader("referer").split("/");
		String slash = "/";
		for(int i =0; i<urlparts.length; i++){
			if(i<urlparts.length-1){
	//			if(i==urlparts.length-2)slash = "";
			entityurl.append(urlparts[i]+slash);}
		/*	else{
			pgnum = Integer.parseInt(urlparts[i]);
			
			}	*/
		}
		NewsType nwstp = ntpServ.getById(nwstp_id);
		
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
//		fillsubcategoryactions(catactions, categories);
		Category cat = catServ.getById(catid);
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Integer pagecount = actServ.getCount()/PAGE_SIZE;
//		Set<News> news = nwsServ.listNewsPageByType(nwstp, begin, end);
		Set<News> news = nwsServ.listCtegoryNewsPageByType(cat,nwstp, begin, end);
		Map<NewsType, Long> newstypeList = ntpServ.getAllNewsTypeNewsCount();
		map.put("pagecount", pagecount);
		map.put("newsList", news);
		map.put("newsTypes", newstypeList);
		map.put("currnewstype", nwstp);
		map.put("entityurl", entityurl.toString());
//		map.put("currentCatList", catServ.getRootCategories());
		return "newsList";
		
	}
	
	
	
	public void fillsubcategoryactions(Map<Category, Long> catactions,List<Category> categories){
		Map<Category, Long> catats = catServ.getAllActionCategories();
		for(Category cat: categories){
			Long parcount = new Long(0);
			for(Entry<Category, Long> entr:catats.entrySet()){
				Long count = new Long(0);
				System.out.println(entr.getKey()+"     "+entr.getValue());
				if(Category.isChildOf(entr.getKey(), cat)){
					System.out.println("child!");
					count = entr.getValue();
					parcount +=count;
					cat.getActionschild().put(entr.getKey(), count);
				}
			}
			if(parcount>0)
			catactions.put(cat, parcount);
		}
		
	}
	
	@RequestMapping(value = "/admin/adminCountry", method = RequestMethod.GET)
    public String listCountries(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		CountryBean countrybean = new CountryBean();
		countrybean.setCountries(cntrServ.getAll());
		System.out.println(countrybean.getCountries().get(0).getName());
		map.put("countrybean", countrybean);
		return "adminCountries";
		
	}
	
	
	@RequestMapping(value = "/admin/updatedCountries", method = RequestMethod.POST)
	public String updatedCountries(@ModelAttribute (value = "countrybean") @Valid CountryBean countrybean, HttpServletRequest req, BindingResult bindingResult, Map<String, Object> map){
		
		if(bindingResult.hasErrors()){
			map.put("countrybean", countrybean);
			return "adminCountries";
		}
		for(Country cntr:countrybean.getCountries()){
			if(cntr.getId()!=null){
				Country country = ctrServ.getById(cntr.getId());
				country.setName(cntr.getName());
				ctrServ.update(country);
			}
			else{
				if(cntr.getName()!=null && cntr.getName()!="")
				ctrServ.add(cntr);
			}
		}
		countrybean.setCountries(ctrServ.getAll());
		map.put("countrybean", countrybean);
		map.put("result", "success");
		return "adminCountries";
		
	}
	
	
	@RequestMapping(value = "/category/{catid}/news/{newsid}")
    public String showNews( @PathVariable(value = "catid") Integer catid,  @PathVariable(value = "newsid") Integer newsid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		map.put("user", user);
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		List<Category> roots = catServ.getRootCategories();
//		setSubCategs(roots,4);
		News n = nwsServ.getById(newsid);
		Category cat = catServ.getById(catid);
		map.put("currentCatList", roots);
		map.put("news", n);
		Set<NewsPart> parts = new TreeSet<NewsPart>();
		int i = 0;
	/*	for(TextPart h: n.getTextparts()){
			
			parts.add(h);
			if((i<n.getImages().size())){
			parts.add(n.getImages().get(i++));
			}
			
		}*/
		for(NewsPart y:parts){
			System.out.println(y.getValue());
		}
		Set<News> othernews = new TreeSet<News>();
		List<News> catnews = cat.getCatnews();
		int size = catnews.size();
		int othercount = 3;
		Random d = new Random();
		if(size>1){
		while(true){
			if(othernews.size()==othercount) break;
			Integer f = d.nextInt(size-1);
			News nn = catnews.get(f);
			if(!nn.equals(n))othernews.add(nn);
		}
		}
	/*	map.put("news", nwsServ.getAll());
		map.put("topnews", nwsServ.getTopNews());*/
		Map<NewsType, Long> newstypeList = ntpServ.getAllNewsTypeNewsCount();
		map.put("newsTypes", newstypeList);
		map.put("parts", parts);
		Set<GoodItem> leaders = Serv.getSalesLeaders(5);
		map.put("leaders", leaders);
		map.put("othernews", othernews);
		return "news";
		
	}
	
	@RequestMapping(value = "/action/{actid}")
    public String showAction(@PathVariable(value = "actid") Integer actid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		map.put("user", user);
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		List<Category> roots = catServ.getRootCategories();
//		setSubCategs(roots,4);
		Action act = actServ.getById(actid);
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
		fillsubcategoryactions(catactions, roots);
		map.put("catactions", catactions);
		map.put("currentCatList", roots);
		map.put("action", act);
		
		Set<GoodItem> leaders = Serv.getSalesLeaders(5);
	//	map.put("leaders", leaders);
	//	map.put("othernews", othernews);
		return "action";
		
	}
	
	
	@RequestMapping(value = "/news")
    public String getNews(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		List<Category> roots = catServ.getRootCategories();
//		setSubCategs(roots,4);
		map.put("catList", roots);
		map.put("news", nwsServ.getAll());
		map.put("topnews", nwsServ.getTopNews());
		Set<GoodItem> leaders = Serv.getSalesLeaders(5);
		map.put("leaders", leaders);
		return "news";
		
	}
	

	
	
}
