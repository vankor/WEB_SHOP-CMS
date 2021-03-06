package controllers;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

import Model.entity.Action;
import Model.dto_beans.ActionBean;
import Model.others.ActionImageLoaderFactory;
import Model.service.ActionService;
import Model.service.AdressService;
import Model.service.CategoryService;
import Model.service.CommentService;
import Model.service.CountryService;
import Model.service.DeliveryTypeService;
import Model.entity.GoodItem;
import Model.service.GoodItemService;
import Model.service.GoodStateService;
import Model.service.ImageService;
import Model.others.Loader;
import Model.others.LoaderFactory;
import Model.service.PayTypeService;
import Model.service.PhoneService;
import Model.service.PropSegmentService;
import Model.service.PropertyService;
import Model.service.RegionService;
import Model.service.TownService;
import Model.service.UserService;
import Model.service.ValueService;
import Model.service.VoteService;

@Controller
@RequestMapping(value = {"/admin/adminactions/"})
public class AdminAction {
	
	private Logger logger = LoggerFactory.getLogger(AdminAction.class);
	
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
	
	private final String rootpath = "D:/����/�����/web-shop/src/main/webapp/resources/images/";
	
	@RequestMapping(value = "/showaction", method = RequestMethod.GET)
	  public String showAction(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
			map.put("actions", actServ.getAll());
			return "adminActionList";
		
		}
	
	@RequestMapping(value = "/deleteaction/{actId}", method = RequestMethod.GET)
    public String deleteAction(@PathVariable (value = "actId") Integer actid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		actServ.deleteById(actid);
		return "redirect:/admin/adminactions/showaction";
	
	}
	
	@RequestMapping(value = "/updateaction/{actId}", method = RequestMethod.GET)
    public String updateAction(@PathVariable (value = "actId") Integer actid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Action act = actServ.getById(actid);
		ActionBean actb = new ActionBean();
		actb.setFromAction(act);
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		f.format(act.getEndtime());
		map.put("action", actb);
		map.put("type", "update");
		return "adminAction";
	
	}
	
	@RequestMapping(value = "/updatedaction", method = RequestMethod.POST)
    public String updatedAction(@ModelAttribute (value = "action") @Valid ActionBean action, BindingResult bindingResult, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		if (bindingResult.hasErrors()) {

			map.put("action", action);
			if(action.getId()==null)
				map.put("type", "create");
			else
				map.put("type", "update");
			
            return "adminAction";
        }
		
		Action act = new Action();
		if(action.getId()!=null){
			act = actServ.getById(action.getId());
		}
		
		act.setFromActionBean(action);
		Set<GoodItem> actiongoods = new TreeSet<GoodItem>();
		Set<GoodItem> giftgoods = new TreeSet<GoodItem>();
		for(GoodItem g:action.getActiongoods()){
			actiongoods.add(Serv.getById(g.getId()));
		}
		
		for(GoodItem g1:action.getGiftgoods()){
			giftgoods.add(Serv.getById(g1.getId()));
		}
		
		
		Integer fldrid = (act.getId()==null)?(actServ.getLastId()+1):act.getId();
		
		
		LoaderFactory lfactory = new ActionImageLoaderFactory();
		Loader lloader = lfactory.createLoader(action, act, fldrid, null);
		lloader.load();
		
/*		CommonsMultipartFile thumb;
		if((thumb=action.getNewthumb())!=null){
			File folder = new File(rootpath+"actions/"+fldrid);
			if(!folder.exists() || !folder.isDirectory()){ folder.mkdirs();}
			
		
		try {
			File file = new File(rootpath+"/actions/"+fldrid+"/thumb.jpg");
			if(file.exists() && file.isFile()) file.delete();
			
			thumb.transferTo(new File(rootpath+"/actions/"+fldrid+"/thumb.jpg"));
			act.setThumb("/actions/"+fldrid+"/thumb.jpg");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
		act.setActiongoods(actiongoods);
		act.setGiftgoods(giftgoods);
		
	
		
		actServ.update(act);
		action.setFromAction(act);
		map.put("result", "success");
		map.put("action", action);
		map.put("type", "update");
		return "adminAction";
	
	}
	
	@RequestMapping(value = "/addaction")
    public String addAction(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		ActionBean act = new ActionBean();
		System.out.println(act.getGiftgoods().size());
		map.put("action", act);
		map.put("type", "create");
		return "adminAction";
	
	}
	
	@RequestMapping(value = "{actId}/deleteactiongood/{goodId}", method = RequestMethod.GET)
    public String deleteActionGood(@PathVariable (value = "actId") Integer actid, @PathVariable (value = "goodId") Integer goodid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Action act = actServ.getById(actid);
		act.getActiongoods().remove(Serv.getById(goodid));
		actServ.update(act);
		return "redirect:/admin/adminactions/updateaction/"+act.getId();
	
	}
	
	@RequestMapping(value = "{actId}/deletegift/{goodId}", method = RequestMethod.GET)
    public String deleteGiftGood(@PathVariable (value = "actId") Integer actid, @PathVariable (value = "goodId") Integer goodid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Action act = actServ.getById(actid);
		act.getGiftgoods().remove(Serv.getById(goodid));
		actServ.update(act);
		return "redirect:/admin/adminactions/updateaction/"+act.getId();
	
	}
	
	

}
