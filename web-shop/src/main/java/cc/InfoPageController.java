package cc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import Model.AnonimBuck;
import Model.AnonimEmailService;
import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Category;
import Model.CategoryService;
import Model.CommentService;
import Model.GoodItemService;
import Model.GoodStateService;
import Model.OrderService;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.PropertyService;
import Model.ResourceNotFoundException;
import Model.Town;
import Model.TownService;
import Model.URLService;
import Model.User;
import Model.UserService;
import Model.ValueService;
import Model.VoteService;

@Controller
@RequestMapping(value = "/info/**")
public class InfoPageController {
	
	private Logger logger = LoggerFactory.getLogger(InfoPageController.class);
	
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;

	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private TownService twnServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	
	@RequestMapping(method = RequestMethod.GET)
    public String showPage(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, HttpSession sess) {
		
		String restOfTheUrl = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String[] pprts = restOfTheUrl.split("/");
		String pageurl = URLService.getUrlAfter(restOfTheUrl, "info");
		System.out.println(pageurl);
		Page currpage = pageServ.getPageByUrl(pageurl);
		if(currpage==null){
			throw new ResourceNotFoundException();
		}
		List<PageGroup> allpagegroups = pgrServ.getAll();
		map.put("allpagegroups", allpagegroups);
		map.put("currpage", currpage);
		return "infopage";
		
	}
	
}
