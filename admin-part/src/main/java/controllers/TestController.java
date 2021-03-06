package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.service.BucketService;
import Model.service.CategoryService;
import Model.entity.GoodItem;
import Model.service.GoodItemService;
import Model.service.OrderService;
import Model.others.SearchForm;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private BucketService buckServ;
	
	@Autowired
	private GoodItemService goodServ;
	
	@Autowired
	private SessionFactory sessfactory;
	
	@Autowired
	private OrderService ordServ;
	
	@Autowired
	private CategoryService catServ;
	
	@RequestMapping(method = RequestMethod.GET)
    
    public String getTest(Map <String, Object> map) {
		SearchForm s = new SearchForm();
		s.setValue("wwww");
		map.put("command", s);
        return "test";
    }

	@RequestMapping(value = "/ajaxsearch", method = RequestMethod.POST)
    public @ResponseBody
    String getGoods(@RequestBody SearchForm s) {
		if(s.getValue() == null){System.out.println("NULLL");}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
		List<GoodItem> goods = goodServ.getAll();
		List<String> names = new ArrayList<String>();
		for(GoodItem g: goods){
			names.add(g.getName());
		}
		return s.getValue();
    }
	
	@RequestMapping(value = "apply", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
	public @ResponseBody String create(@ModelAttribute(value = "searchform") SearchForm s) {
		System.out.println("contact!");
		if(s.getValue() == null){System.out.println("NULLL");}
		return s.getValue();
	}
	
	

}
