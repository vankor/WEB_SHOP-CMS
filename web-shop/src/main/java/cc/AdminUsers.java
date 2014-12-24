package cc;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Model.AdressService;
import Model.Bucket;
import Model.BucketService;
import Model.Category;
import Model.Comment;
import Model.CommentService;
import Model.CommonUtilsService;
import Model.EntityField;
import Model.EntityFieldService;
import Model.EntityService;
import Model.GoodItem;
import Model.GoodItemService;
import Model.Image;
import Model.Loader;
import Model.LoaderFactory;
import Model.NewsImageLoaderFactory;
import Model.ObjectFieldContainer;
import Model.Order;
import Model.OrderService;
import Model.PhoneNumber;
import Model.PhoneService;
import Model.RoleService;
import Model.TextPart;
import Model.TownService;
import Model.User;
import Model.UserBean;
import Model.UserService;
import Model.Role;
import Model.Video;

@Controller
@RequestMapping(value = "/admin/adminusers/{userId}")
public class AdminUsers {
	
	public static final Integer PAGE_SIZE = 8;
	private Logger logger = LoggerFactory.getLogger(AdminUsers.class);
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private BucketService buckServ;
	
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private PhoneService phnServ;
	
	@Autowired
	private TownService twnServ;
	
	@Autowired
	private OrderService ordServ;
	
	@Autowired
	private AdressService adrServ;
	
	@Autowired
	private EntityFieldService entServ;
	
	@Autowired
	private RoleService roleServ;
	
	@RequestMapping(value = "/updateuser")
	public String updateuser(@PathVariable("userId") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	
		User currentuser = (User) request.getAttribute("user");
		
		User usr = usrServ.getById(id);
		
		UserBean userbean = new UserBean();
		userbean.constructfromuser(usr);
		map.put("user", userbean);
		System.out.println("Адресс: "+userbean.getAdress());
		map.put("section","commonuserinfo");		
		if(!userbean.getWishgoods().isEmpty()){
		Integer wishpagecount = Math.round(userbean.getWishgoods().size()/PAGE_SIZE);
		List<GoodItem> ords = userbean.getWishgoods();
		Integer end = 1*PAGE_SIZE;
		Integer begin = end - PAGE_SIZE;
		if(end>ords.size())
			end = ords.size();
		List<ObjectFieldContainer> wishgoodcontainers = new ArrayList<ObjectFieldContainer>();
		for(GoodItem good:ords.subList(begin, end)){
			if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
			ObjectFieldContainer container = new ObjectFieldContainer();
			container.setObj(good);
			CommonUtilsService.setEntityAdminReferences(container);
			container.getRefs().put("Убрать товар", "/admin/adminusers/"+id+"/dropwishgood/"+good.getId()+"");
			List<EntityField> fields = entServ.listByClassname("GoodItem");
		for(EntityField entfield: fields){
			container.getFields().put(entfield, null);
		}
		container.fillFieldValues();
		wishgoodcontainers.add(container);
		}
		}
		for(ObjectFieldContainer container:wishgoodcontainers){	
			System.out.println("Категория -  "+container.getObj());
		for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
			System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
		}
		}
		map.put("wishgoodcontainers", wishgoodcontainers);
		map.put("wishgoodpagecount", wishpagecount);
		map.put("wishgoodpgnum", 1);
		}
		
		
		
		if(!userbean.getWatchgoods().isEmpty()){
			Integer watchpagecount = Math.round(userbean.getWatchgoods().size()/PAGE_SIZE);
			List<GoodItem> ords = userbean.getWatchgoods();
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>ords.size())
				end = ords.size();
			List<ObjectFieldContainer> watchgoodcontainers = new ArrayList<ObjectFieldContainer>();
			for(GoodItem good:ords.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				CommonUtilsService.setEntityAdminReferences(container);
				container.getRefs().put("Убрать товар", "/admin/adminusers/"+id+"/dropwatchgood/"+good.getId()+"");
				List<EntityField> fields = entServ.listByClassname("GoodItem");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
			}
			container.fillFieldValues();
			watchgoodcontainers.add(container);
			}
			}
			for(ObjectFieldContainer container:watchgoodcontainers){	
				System.out.println("Категория -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("watchgoodcontainers", watchgoodcontainers);
			map.put("watchgoodpagecount", watchpagecount);
			map.put("watchgoodpgnum", 1);
			}
		
		if(!userbean.getHistorygoods().isEmpty()){
			Integer historypagecount = Math.round(userbean.getHistorygoods().size()/PAGE_SIZE);
			List<GoodItem> ords = userbean.getHistorygoods();
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>ords.size())
				end = ords.size();
			List<ObjectFieldContainer> historygoodcontainers = new ArrayList<ObjectFieldContainer>();
			for(GoodItem good:ords.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				CommonUtilsService.setEntityAdminReferences(container);
				
				container.getRefs().put("Убрать товар", "/admin/adminusers/"+id+"/drophistorygood/"+good.getId()+"");
				List<EntityField> fields = entServ.listByClassname("GoodItem");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
			}
			container.fillFieldValues();
			historygoodcontainers.add(container);
			}
			}
			for(ObjectFieldContainer container:historygoodcontainers){	
				System.out.println("Категория -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("historygoodcontainers", historygoodcontainers);
			map.put("historygoodpagecount", historypagecount);
			map.put("historygoodpgnum", 1);
			}
		
		if(!userbean.getOrders().isEmpty()){
			Integer orderspagecount = Math.round(userbean.getOrders().size()/PAGE_SIZE);
			List<Order> ords = userbean.getOrders();
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>ords.size())
				end = ords.size();
			
			
			List<ObjectFieldContainer> ordercontainers = new ArrayList<ObjectFieldContainer>();
			for(Order good:ords.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				CommonUtilsService.setEntityAdminReferences(container);
				container.getRefs().put("Убрать заказ", "/admin/adminusers/"+id+"/droporder/"+good.getId()+"");
				List<EntityField> fields = entServ.listByClassname("Order");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
			}
			container.fillFieldValues();
			ordercontainers.add(container);
			}
			}
			for(ObjectFieldContainer container:ordercontainers){	
				System.out.println("Категория -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("ordercontainers", ordercontainers);
			map.put("orderspagecount", orderspagecount);
			map.put("orderspgnum", 1);
			}
		
		
		
		if(!userbean.getPhonenumbers().isEmpty()){
			Integer phonespagecount = Math.round(userbean.getPhonenumbers().size()/PAGE_SIZE);
			List<PhoneNumber> ords = userbean.getPhonenumbers();
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>ords.size())
				end = ords.size();
			List<ObjectFieldContainer> phonecontainers = new ArrayList<ObjectFieldContainer>();
			for(PhoneNumber good:ords.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				CommonUtilsService.setEntityAdminReferences(container);
				List<EntityField> fields = entServ.listByClassname("PhoneNumber");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
			}
			container.fillFieldValues();
			phonecontainers.add(container);
			}
			}
			for(ObjectFieldContainer container:phonecontainers){	
				System.out.println("Категория -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("phonecontainers",phonecontainers);
			map.put("phonespagecount", phonespagecount);
			map.put("phonespgnum", 1);
			}
		
		
		String referrer = request.getHeader("referer");
		
		return "adminUser";
	}
	
	
	@RequestMapping(value = "/addrole/{roleId}")
	public String addrole(@PathVariable("userId") Integer userid, @PathVariable("roleId") String roleid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
	System.out.println("ROOOOOLLLLLLEEEE");
	User user = usrServ.getById(userid);
	Role role = roleServ.getById(roleid);
	user.getRoles().add(role);
	usrServ.update(user);
	
	return "redirect:"+request.getHeader("referer");
	}
	
	
	@RequestMapping(value = "/droprole/{roleId}")
	public String droprole(@PathVariable("userId") Integer userid, @PathVariable("roleId") String roleid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
	System.out.println("ROOOOOLLLLLLEEEE");
	User user = usrServ.getById(userid);
	Role role = roleServ.getById(roleid);
	user.getRoles().remove(role);
	usrServ.update(user);
	
	return "redirect:"+request.getHeader("referer");
	}
	
	@RequestMapping(value = "/updateuserroles")
	public String updateuserroles(@PathVariable("userId") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	
		User currentuser = (User) request.getAttribute("user");
		
		User usr = usrServ.getById(id);
		
		UserBean userbean = new UserBean();
		userbean.constructfromuser(usr);
		map.put("user", userbean);
		
		if(!usr.getRoles().isEmpty()){
			Integer rolespagecount = Math.round(userbean.getRoles().size()/PAGE_SIZE);
			List<Role> ords = new ArrayList<Role>(usr.getRoles());
			System.out.println("РОООЛЬ "+ords);
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>ords.size())
				end = ords.size();
			
			
			List<ObjectFieldContainer> rolecontainers = new ArrayList<ObjectFieldContainer>();
			for(Role good:ords.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				container.getRefs().put("Редактировать", "/admin/updaterole/"+good.getAuthority()+"");
				container.getRefs().put("Забрать роль", "/admin/adminusers/"+id+"/droprole/"+good.getAuthority()+"");
				List<EntityField> fields = entServ.listByClassname("Role");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
				
			}
			container.fillFieldValues();
			rolecontainers.add(container);
			}
			}
			for(ObjectFieldContainer container:rolecontainers){	
				System.out.println("Категория -  "+container.getObj());
			for(Entry<EntityField,Object> entr:container.getFields().entrySet()){
				System.out.println("Свойство "+entr.getKey().getPropname() +"        "+"Значение "+entr.getValue());
			}
			}
			map.put("rolecontainers",rolecontainers);
			map.put("rolespagecount", rolespagecount);
			map.put("rolespgnum", 1);
			}
		else{System.out.println("НЕЕЕЕЕЕЕТТТТТТТТТТТ РОЛЕЙ");}
		map.put("section","userroles");
		map.put("adminclassname","Role");
		return "adminUser";
	}
	
	
	@RequestMapping(value = "/updateusercomments")
	public String updateusercomments(@PathVariable("userId") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	
		User currentuser = (User) request.getAttribute("user");
		
		User usr = usrServ.getById(id);
		
		
		
		if(!usr.getComments().isEmpty()){
			Integer commentspagecount = Math.round(usr.getComments().size()/PAGE_SIZE);
			List<Comment> comments = new ArrayList<Comment>(usr.getComments());
			Integer end = 1*PAGE_SIZE;
			Integer begin = end - PAGE_SIZE;
			if(end>comments.size())
				end = comments.size();
			
			
			List<ObjectFieldContainer> commentcontainers = new ArrayList<ObjectFieldContainer>();
			for(Comment good:comments.subList(begin, end)){
				if(good.getIsdeleted()==null || good.getIsdeleted()!=true){
				ObjectFieldContainer container = new ObjectFieldContainer();
				container.setObj(good);
				container.getRefs().put("Редактировать", "/admin/updatecomment/"+good.getId()+"");
				container.getRefs().put("Удалить комментарий", "/admin/setdeleted/Comment/"+good.getId()+"");
				List<EntityField> fields = entServ.listByClassname("Comment");
			for(EntityField entfield: fields){
				container.getFields().put(entfield, null);
			}
			container.fillFieldValues();
			commentcontainers.add(container);
			}
			}
	
			map.put("containers",commentcontainers);
			map.put("pagecount", commentspagecount);
			map.put("pgnum", 1);
			}
		
		map.put("section","commentroles");
		map.put("adminclassname","Comment");
		return "adminGoodList";
	}

	
	@RequestMapping(value = "/updateduser", method = RequestMethod.POST)
	public String updateduser(@ModelAttribute("user") UserBean user, HttpServletRequest request, HttpSession sess) throws ParseException{
		User user0 = new User();
		if(user.getId()!=null){
			user0 = usrServ.getById(user.getId());
			
		}
		
		
		user0.setFromUserBean(user);

		if(user.getAdress()!=null){
			user0.setAdress(adrServ.getById(user.getUseradress().getId()));
		}
		
		if(user.getTown()!=null){
			user0.setTown(twnServ.getById(user.getTown().getId()));
		}
	
	
//	for(TextPart t:newsbean.getTextparts()){
//		textparts.add(txtServ.getTextPartById(t.getId()));
//	}
	
//	for(Image i:newsbean.getImages()){
//		images.add(imgServ.getImageById(i.getId()));
//	}
	
//	for(Video v:newsbean.getVideos()){
//		videos.add(vidServ.getVideoById(v.getId()));
//	}
	
	
	if(!user.getRoles().isEmpty()){
		Set<Role> roles = new TreeSet<Role>();
		for(Role b: user.getRoles()){
			roles.add(roleServ.getById(b.getAuthority()));
		}
		user0.setRoles(roles);
		}
	
	
	if(!user.getBuck().isEmpty()){
	List<Bucket> bucks = new ArrayList<Bucket>();
	for(Bucket b: user.getBuck()){
		bucks.add(buckServ.getById(b.getId()));
	}
	user0.setBuck(bucks);
	}
	
	if(!user.getHistorygoods().isEmpty()){
		Set<GoodItem> historygoods = new TreeSet<GoodItem>();
		for(GoodItem b: user.getHistorygoods()){
			historygoods.add(Serv.getById(b.getId()));
		}
		user0.setHistorygoods(historygoods);
		}
	
	if(!user.getWishgoods().isEmpty()){
		Set<GoodItem> wishgoods = new TreeSet<GoodItem>();
		for(GoodItem b: user.getWishgoods()){
			wishgoods.add(Serv.getById(b.getId()));
		}
		user0.setWishgoods(wishgoods);
		}
	
	if(!user.getWatchgoods().isEmpty()){
		Set<GoodItem> watchgoods = new TreeSet<GoodItem>();
		for(GoodItem b: user.getWatchgoods()){
			watchgoods.add(Serv.getById(b.getId()));
		}
		user0.setWatchgoods(watchgoods);
		}
	
	if(!user.getOrders().isEmpty()){
		Set<Order> orders = new TreeSet<Order>();
		for(Order b: user.getOrders()){
			orders.add(ordServ.getById(b.getId()));
		}
		user0.setOrders(orders);
		}
	

	
			
	for(Bucket t:user0.getBuck()){
		System.out.println(t.getName());
	}
	
	for(Comment i:user0.getComments()){
		System.out.println("Комментарии: "+i.getText());
	}
	
	for(GoodItem v:user0.getHistorygoods()){
		System.out.println("Исторический товар: "+v.getName());
	}
	
	for(GoodItem v:user0.getWatchgoods()){
		System.out.println("Следящий товар: "+v.getName());
	}
	
	for(GoodItem v:user0.getWishgoods()){
		System.out.println("Желаемый товар: "+v.getName());
	}
	
	for(Order v:user0.getOrders()){
		System.out.println("Заказы: "+v.getId());
	}
	
	for(PhoneNumber v:user0.getPhonenumbers()){
		System.out.println("rerrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		System.out.println("Номера: "+v.getNumb());
	}
	
//	usrServ.update(user0);
		
		
		
		String referrer = request.getHeader("referer");
		
		return "redirect:"+referrer;
	}
	

}
