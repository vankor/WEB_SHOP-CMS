package cc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Model.ActionBean;
import Model.Category;
import Model.CategoryCreateBean;
import Model.CategoryService;
import Model.CommentService;
import Model.DeliveryType;
import Model.GoodItemService;
import Model.Image;
import Model.ImageService;
import Model.Loader;
import Model.LoaderFactory;
import Model.News;
import Model.NewsBean;
import Model.NewsImageLoaderFactory;
import Model.NewsService;
import Model.PayType;
import Model.PropSegment;
import Model.PropSegmentService;
import Model.Property;
import Model.PropertyService;
import Model.Role;
import Model.Video;

@Controller
@RequestMapping(value = {"/admin/add"})
public class AddData {
	
	private Logger logger = LoggerFactory.getLogger(AddData.class);
	
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private PropertyService propServ;
	
	@Autowired
	private PropSegmentService prsgServ;
	

	
	
	@RequestMapping(value = "/addCateg", method = RequestMethod.GET)
	public String addCategory(Model model){
		System.out.println("here");
		model.addAttribute("category", new Category());
		model.addAttribute("type", "create");
        return "addcat";
		
	}
	
	@RequestMapping(value = "/addedCateg", method = RequestMethod.POST)
    public String addedCategory(@ModelAttribute(value = "category") Category category, Map<String, Object> map) {
//		Category category = new Category(cat);
		Category parent = catServ.getById(category.getParent_category().getId());
		category.setParent_category(parent);
		for(PropSegment segm: category.getPropsegments()){
			System.out.println(segm.getName()+":");
			
			for(Property pr: segm.getProperties()){
				pr.setPropsegment(segm);
				System.out.println(pr.getName());
			}
		}
		List<Property> params = category.getProps();
//		for(String h:cat.getParameters()){
//			System.out.println(h);
	//	}
		
		for(Property h:params){
			System.out.println(h.getName());
		}
		catServ.add(category);
		
		
	//	List <Category> t =catServ.getRootCategories();
		
        return "redirect:/admin/admincategory/"+catServ.getLastId()+"/updateCateg";
    }
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/createrole", method = RequestMethod.GET)
	  public String createRole(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("role", new Role());
		
		map.put("section", "create");
		
		return "adminRole";
		
	}
	
	@RequestMapping(value = "/addaction")
    public String addAction(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		ActionBean act = new ActionBean();
		System.out.println(act.getGiftgoods().size());
		map.put("action", act);
		map.put("type", "create");
		return "adminAction";
	
	}
	
	@RequestMapping(value = "/addpaytype", method = RequestMethod.GET)
	  public String addPayType(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("paytype", new PayType());
		
		map.put("section", "create");
		
		return "adminPaytype";
		
	}
	
	
	@RequestMapping(value = "/adddeltype", method = RequestMethod.GET)
	  public String addDelType(Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		
		map.put("deltype", new DeliveryType());
		
		map.put("section", "create");
		
		return "adminDeltype";
		
	}

}
