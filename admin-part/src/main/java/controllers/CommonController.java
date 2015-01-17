/*package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import javax.mail.MessagingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.Installator;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import Model.entity.Action;
import Model.service.ActionService;
import Model.entity.Adress;
import Model.service.AdressService;
import Model.others.AdressType;
import Model.others.AnonimBuck;
import Model.service.BasicConfigurationService;
import Model.entity.Bucket;
import Model.entity.Category;
import Model.service.CategoryService;
import Model.others.ChoiseItem;
import Model.service.CommentService;
import Model.entity.Configuration;
import Model.service.ConfigurationService;
import Model.entity.Country;
import Model.dto_beans.CountriesBean;
import Model.dto_beans.CountryBean;
import Model.service.CountryService;
import Model.entity.DeliveryType;
import Model.service.DeliveryTypeService;
import Model.others.GoodCollection;
import Model.entity.GoodItem;
import Model.service.GoodItemService;
import Model.service.GoodStateService;
import Model.others.ImageLoader;
import Model.service.ImageService;
import Model.dto_beans.LoginBean;
import Model.entity.News;
import Model.others.NewsPart;
import Model.service.NewsService;
import Model.entity.NewsType;
import Model.service.NewsTypeService;
import Model.entity.Order;
import Model.dto_beans.OrderBean;
import Model.service.OrderService;
import Model.service.OrderSumCalculator;
import Model.service.PageGroupService;
import Model.service.PageService;
import Model.others.ParamContainer;
import Model.dto_beans.ParamFilterBean;
import Model.entity.PayType;
import Model.service.PayTypeService;
import Model.entity.PhoneNumber;
import Model.others.PhoneNumberType;
import Model.service.PhoneService;
import Model.entity.PropSegment;
import Model.entity.Property;
import Model.service.PropertyService;
import Model.others.Row;
import Model.others.SearchForm;
import Model.entity.Town;
import Model.service.TownService;
import Model.others.Town_Delivery;
import Model.entity.User;
import Model.service.UserService;
import Model.entity.Value;
import Model.dto_beans.ValueBean;
import Model.service.ValueService;
import Model.entity.Vote;
import Model.dto_beans.VoteBean;
import Model.service.VoteService;

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

		}
		
		return "template/bucketlist";
	}
	

	
	@RequestMapping(value = "/settown", method = RequestMethod.GET)
	public String setCity(@RequestParam (value = "cid") Integer id,HttpServletRequest request, Map<String, Object> map, HttpSession sess) {
		sess.setAttribute("cityid", id);
		String referer = request.getHeader("referer");
		return "redirect:"+referer;
	}
	
	@RequestMapping("/usrmenu/usrbuck/listbuck/good/{goodid}")
	public String showGoodfromBuck(@PathVariable (value = "goodid") Integer id, Map<String, Object> mapcomm) {
		return "template/good";
		}
	
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		InputStream input = null;
		String init = new String();
		try{
			Properties prop = new Properties();
			
			input = getClass().getClassLoader().getResourceAsStream("database.properties");
			if(input==null){
	            System.out.println("Sorry, unable to find " + Installator.filename);
	          
			}
			prop.load(input);
			init = prop.getProperty("init");
			System.out.println("fff "+prop.getProperty("init"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("init "+init);
		if(init.equals("true"))
		return "redirect:/install";
		else
		return "redirect:/admin";	
	}
	
	@RequestMapping("/start")
	public String startPage(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		map.put("goodList", Serv.getTopGoods());

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
		map.put("mainnews", nwsServ.getTopNews());
		map.put("seasongoods", Serv.getSeasonGoods());
		map.put("actions", randomtopactions);
		map.put("residenttowns", twnServ.getResidentTowns());
		map.put("searchForm", new SearchForm());
		return "template/start";
	}
	

	public void printSubCategs(List<Category> cats, int levels){
		if(levels == 0){return;}
		if(cats == null && cats.isEmpty() ){return;}
		for(Category cat : cats){
			System.out.println(cat.getName());
			printSubCategs(cat.getSubcategs(), levels-1);
		}
		
	}

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

		List<Category> roots = catServ.getRootCategories();
		section = section.toLowerCase();
		map.put("catList", roots);
		map.put("section", section);
		map.put("idfld", idfld);
		return "template/commsection";
		
	}
	

	@RequestMapping(value = "/choiseCategory", method = RequestMethod.POST)
	public String choiseGood( Map <String, Object> map, @RequestParam(value="idfld") String idfld, @RequestParam(value="section") String section){

		List<Category> roots = catServ.getRootCategories();

		System.out.println("hhhhhhhhh"+"   "+section+"   "+idfld+roots);
		map.put("catList", roots);
		map.put("section", section);
		map.put("idfld", idfld);
		return "template/commsection";
		
	}
	
	
	
	
	
	@RequestMapping(value = "/addPropValue", method = RequestMethod.POST)
	public String addPropValue( Map <String, Object> map, @RequestParam(value="propid") Integer propid,
			@RequestParam(value="section") String section
			, @RequestParam(value="lval") Integer lval
			, @RequestParam(value="lprop") Integer lprop
			, @RequestParam(value="lsegm") Integer lsegm){
		Property prop = propServ.getById(propid);
		map.put("propvals", prop.getVal());
		map.put("section", section);
		map.put("lval", lval);
		map.put("lprop", lprop);
		map.put("lsegm", lsegm);
		return "template/commsection";
		
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
		logger.info("Сумма товаров в корзине: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		sess.setAttribute("currbuck", buck);
		map.put("bucketsize", buck.getSize());
		return "template/bucket";
		
	}
	
	
	@RequestMapping(value = "/choisePropValue", method = RequestMethod.POST)
	public @ResponseBody Value choisePropValue (@RequestParam(value="id") Integer valid){
		System.out.println(valid);
		Value val = valServ.getById(valid);
		return val;
	}
	
	
	@RequestMapping(value = "/choiseGood", method = RequestMethod.POST)
	public @ResponseBody GoodItem choiseGood(@RequestParam(value="id") Integer goodid){
		return Serv.getById(goodid);
	}
	
	@RequestMapping(value = "/choiseCat", method = RequestMethod.POST)
	public @ResponseBody Category choiseCategory(@RequestParam(value="id") Integer catid){
			return catServ.getById(catid);
	}
	
		
	@RequestMapping(value = "/addVote", method = RequestMethod.GET)
	public String addVote(Map<String, Object> map){
		List<Vote> votelst = new ArrayList<Vote>();
		VoteBean votes = new VoteBean();
		votelst = voteServ.getAll();
  		votes.setvotes(votelst);
  		map.put("votes", votes);
        return "addVotes";
		
	}
	
	  @RequestMapping(value = "/logindialog",method = RequestMethod.POST)
      public String logindialog(@RequestParam (value = "section") String section, Map <String, Object> map, HttpServletRequest req) {
		  map.put("login", new LoginBean());
          map.put("section", section);
          return "template/login";
      }
	
	@RequestMapping(value = "/addedVote",method = RequestMethod.POST)
    public String addedVote(@ModelAttribute(value = "votes") VoteBean votes, Map<String, Object> map,HttpServletRequest request) {
		for(Vote vote: votes.getvotes()){
			if(vote.getId()!=null){
				voteServ.update(vote);
			}
			else{voteServ.update(vote);}
		}

        return "redirect:/addVote";
    }

	
	
	@RequestMapping("/order")
    public String order(Map<String, Object> map, HttpSession sess, ServletRequest request) {
		User t = (User) sess.getAttribute("user");
		if(t!=null){
			Town town = t.getTown();
//			List<DeliveryType> towndtp = new ArrayList<DeliveryType>();
			if(town!=null){
					for(Town_Delivery dtp: town.getTowndeliveries()){
					map.put("dtype", dtp.getDeliveryType());
					break;
				}
				
			}
			
		}
		map.put("order", new OrderBean());
		map.put("paytypes", ptpServ.getAll());
		map.put("deliverytypes", dlvServ.getAll());
		map.put("sessid", sess.getId());
		return "template/order";
		
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
		logger.info("Сумма заказа: "+ sum);
		map.put("buckrows", buck.getRows());
		map.put("buck", buck);
		map.put("sum", sum);
		map.put("bucketsize", buck.getSize());
		map.put("section", section);
		String text = "";
		try {
			text = Base64.encode("оплата за заказ с интернет-магазина".getBytes("UTF-8"));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("text", text);
		return "template/commsection";
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
			g = mapper.writeValueAsString(ff);
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
				
		return "template/autocomplete";
		
	}
	

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendGoods(Map <String, Object> map, HttpSession sess, @ModelAttribute (value = "order") OrderBean orderbean, HttpServletRequest request){
		
		System.out.println("clientname "+orderbean.getClientname());
		System.out.println("clientphone_number "+orderbean.getClientphones());
		System.out.println("clientemail "+orderbean.getClientemail());
		System.out.println("clientcomment "+orderbean.getClientcomment());
		System.out.println("clientsrochnost "+orderbean.getClientsrochnost());
		System.out.println("client_delivdate "+orderbean.getClient_delivdate());
		System.out.println("client_delivtime "+orderbean.getClient_delivtime());
		System.out.println("clienttown_name "+orderbean.getClienttown_name());
		System.out.println("clienttown_id "+orderbean.getClienttown());
		System.out.println("clientoplata "+orderbean.getClientoplata());
		System.out.println("clientdeliverytype "+orderbean.getClientdeliverytype());
		System.out.println("type_street "+orderbean.getType_street());
		System.out.println("street "+orderbean.getStreet());
		System.out.println("house "+orderbean.getHouse());
		System.out.println("flor "+orderbean.getFlor());
		System.out.println("room "+orderbean.getRoom());
		System.out.println("nzanos "+orderbean.getNzanos());
		System.out.println("netaj "+orderbean.getNetaj());
		System.out.println("nlift "+orderbean.getNlift());
		System.out.println("sessid "+orderbean.getSessid());
		System.out.println("srok_cred "+orderbean.getSrok_cred());
		System.out.println("client_company "+orderbean.getClient_company());
		System.out.println("client_okpo "+orderbean.getClient_okpo());
		
		String referer = request.getHeader("referer");		
		User t = (User) sess.getAttribute("user");
		AnonimBuck c = (AnonimBuck) sess.getAttribute("currbuck");
		if(c==null){
			map.put("result", "nogoods");
			return "redirect:"+referer;
		}
		Order ord = new Order();
		ord.setTime(new Date(System.currentTimeMillis()));
		ord.setRows(c.getRows());
		OrderSumCalculator calc = new OrderSumCalculator(c);
		ord.setAmount(calc.getSumValue());
		ord.setSessid(orderbean.getSessid());
		
		Town town = twnServ.getById(orderbean.getClienttown());
		if(t!=null){
			ord.setUsr(t);
		
		}
		
			ord.setClientname(orderbean.getClientname());
			ord.setClientemail(orderbean.getClientemail());
			ord.setClienttown(town);
			ord.setComment(orderbean.getClientcomment());
			for(PhoneNumber phone:orderbean.getClientphones()){
				PhoneNumber phn = phnServ.getPhoneNumberByNum(phone.getNumb());
				if(phn==null)
				phn = new PhoneNumber();
				
				String numb = phone.getNumb();
				String code = numb.substring(numb.indexOf("(")+1,numb.indexOf(")"));
				System.out.println(code);
				phn.setNumb(numb);
				Country cntr = ctrServ.getCountryByCode(code);
				phn.setCountry(cntr);
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
		
		PayType ptp = ptpServ.getById(orderbean.getClientoplata());
		DeliveryType dlv = dlvServ.getById(orderbean.getClientdeliverytype());
		ord.setPaytype(ptp);
		ord.setDeliverytype(dlv);
		StringBuilder adress = new StringBuilder();
		
		Adress adr =adrServ.getAdressByParams(town, orderbean.getType_street(), orderbean.getStreet(), orderbean.getHouse(), orderbean.getRoom());
		if(adr==null)
		adr = new Adress();
		if(dlv.getShortname().equals("adress")){
			
			adr.setStreet_type(orderbean.getType_street());
			adr.setStreet_name(orderbean.getStreet());
			adr.setHouse_num(orderbean.getHouse());
			adr.setLevel(orderbean.getFlor());
			adr.setRoom_num(orderbean.getRoom());
			adr.setAdresstype(AdressType.CLIENT);

		
		if(dlv.getShortname().equals("samovyvoz")){
			adr = adrServ.getById(orderbean.getFilial_adress());
			if(adr!=null)
			adr.setAdresstype(AdressType.RESIDENT);
		}
		}

		adr.setTown(town);
		ord.setAdress(adr);
		adr.getOrders().add(ord);
		
		if(ptp.getShortname().equals("nocash")){
			ord.setClient_company(orderbean.getClient_company());
			ord.setClient_okpo(orderbean.getClient_okpo());
		}
		
		if(ptp.getShortname().equals("onlinecred")){
			ord.setCred_term(orderbean.getSrok_cred());
		}
		
		System.out.println("Срочность "+orderbean.getClientsrochnost());
		if(orderbean.getClientsrochnost()!=null && orderbean.getClientsrochnost().equals("on")){
			System.out.println("Срочнооооо!");
			String str_date = orderbean.getClient_delivdate()+" "+orderbean.getClient_delivtime();
			try {
				Date del_time = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(str_date);
				ord.setDelivtime(del_time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		ordServ.add(ord);
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
		map.put("result", "success");
		
		return "redirect:"+referer;
		
	}
	
	
	@RequestMapping(value = "/changeparams", method = RequestMethod.POST)
	public String sendGoods(Map <String, Object> map, HttpSession sess, 
			@RequestParam(value="city_id") Integer city_id, 
			@RequestParam(value="payment_id") Integer payment_id,
			@RequestParam(value="delivery_id") Integer delivery_id){
		
		logger.info("ИД города = "+city_id+"; ИД вида оплаты = "+payment_id+" ; ИД вида доставки = "+delivery_id);
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
	
		if(d.getShortname().equals("samovyvoz")){
			List<Adress> residents = t.getResidents();
			map.put("residents", residents);
		}
		
		if(deltypes.contains(d)){
			map.put("deliverytype", d);
			System.out.println(d.getShortname());
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
		
		return "template/commsection";
		
	}
	
	
	
	@RequestMapping("/popup")
    public String popupBucket(@RequestParam (value = "section") String section, @RequestParam (value = "colid") Integer colid, Map<String, Object> map, HttpSession sess) {
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
		return "template/commsection";
		
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
			
			return "template/finalcategory";
		
			
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
		return "template/searchresults";
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
		return "template/topgoods";
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
			
			}
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
		return "template/actionList";
		
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
			
			}
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
		return "template/actionList";
		
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
		return "template/newsList";
		
	}
	
	@RequestMapping(value = "/category/{catid}/newslist/{pgnum}", method = RequestMethod.GET)
	public String categoryNewsList(@RequestParam(required = false, value = "type") Integer nwstp_id, @PathVariable(value = "pgnum") Integer pgnum, @PathVariable(value = "catid") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
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
		Category cat = catServ.getById(catid);
		Integer end = pgnum*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		Integer pagecount = actServ.getCount()/PAGE_SIZE;
		Set<News> news = nwsServ.listCtegoryNewsPageByType(cat,nwstp, begin, end);
		Map<NewsType, Long> newstypeList = ntpServ.getAllNewsTypeNewsCount();
		map.put("pagecount", pagecount);
		map.put("newsList", news);
		map.put("newsTypes", newstypeList);
		map.put("currnewstype", nwstp);
		map.put("entityurl", entityurl.toString());
		return "template/newsList";
		
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
		CountriesBean countrybean = new CountriesBean();
		List<CountryBean> ctrbeans = new ArrayList<CountryBean>();
		for(Country ctr: cntrServ.getAll()){
			CountryBean ctrbn = new CountryBean();
			ctrbn.constructFromEntity(ctr);
			ctrbeans.add(ctrbn);
		}
		countrybean.setCountries(ctrbeans);
		System.out.println(countrybean.getCountries().get(0).getName());
		map.put("countrybean", countrybean);
		return "adminCountries";
		
	}
	
	
	@RequestMapping(value = "/admin/updatedCountries", method = RequestMethod.POST)
	public String updatedCountries(@ModelAttribute (value = "countrybean") @Valid CountriesBean countrybean, HttpServletRequest req, BindingResult bindingResult, Map<String, Object> map){
		
		if(bindingResult.hasErrors()){
			map.put("countrybean", countrybean);
			return "adminCountries";
		}
		for(CountryBean cntr:countrybean.getCountries()){
			if(cntr.getId()!=null){
				Country country = ctrServ.getById(cntr.getId());
				country.setName(cntr.getName());
				country.setPhonecode(cntr.getPhonecode());
				if(cntr.getNewthumb()!=null){
					Integer fldrid = cntr.getId();
					country.setFlagicon(ImageLoader.loadthumb(cntr.getNewthumb(), fldrid, "countries"));
					}
				ctrServ.update(country);
			}
			else{
				Country country = new Country();
				if(cntr.getName()!=null && cntr.getName()!="")
					country.setName(cntr.getName());
				if(cntr.getPhonecode()!=null && cntr.getPhonecode()!="")
					country.setPhonecode(cntr.getPhonecode());
					System.out.println("newtumb!!   "+cntr.getNewthumb());
					if(cntr.getNewthumb()!=null){
						Integer last = ctrServ.getLastId();
						if(last==null) last = 0;
						Integer fldrid =last+1;
						fldrid++;
						System.out.println(fldrid+ "  eeeerrr");
						country.setFlagicon(ImageLoader.loadthumb(cntr.getNewthumb(), fldrid, "countries"));
					}	
				ctrServ.add(country);
			}
		}
		List<CountryBean> ctrbeans = new ArrayList<CountryBean>();
		for(Country ctr: cntrServ.getAll()){
			CountryBean ctrbn = new CountryBean();
			ctrbn.constructFromEntity(ctr);
			ctrbeans.add(ctrbn);
		}
		countrybean.setCountries(ctrbeans);
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
		News n = nwsServ.getById(newsid);
		Category cat = catServ.getById(catid);
		map.put("currentCatList", roots);
		map.put("news", n);
		Set<NewsPart> parts = new TreeSet<NewsPart>();
		int i = 0;

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
		Map<NewsType, Long> newstypeList = ntpServ.getAllNewsTypeNewsCount();
		map.put("newsTypes", newstypeList);
		map.put("parts", parts);
		Set<GoodItem> leaders = Serv.getSalesLeaders(5);
		map.put("leaders", leaders);
		map.put("othernews", othernews);
		return "template/news";
		
	}
	
	@RequestMapping(value = "/action/{actid}")
    public String showAction(@PathVariable(value = "actid") Integer actid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		User user = (User) request.getAttribute("user");
		map.put("user", user);
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		List<Category> roots = catServ.getRootCategories();

		Action act = actServ.getById(actid);
		Map<Category, Long> catactions = new TreeMap<Category, Long>();
		fillsubcategoryactions(catactions, roots);
		map.put("catactions", catactions);
		map.put("currentCatList", roots);
		map.put("action", act);
		return "template/action";
		
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
		return "template/news";
		
	}
	

	
	
}
*/