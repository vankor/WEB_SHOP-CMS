package controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import Model.service.BasicConfigurationService;
import Model.service.CategoryService;
import Model.service.GoodItemService;
import Model.entity.Page;
import Model.entity.PageGroup;
import Model.service.PageGroupService;
import Model.service.PageService;
import Model.others.ResourceNotFoundException;
import Model.service.TownService;
import Model.service.URLService;

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
		return "template/infopage";
		
	}
	
}
