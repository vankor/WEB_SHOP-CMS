package cc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import Model.AnonimBuck;
import Model.Bucket;
import Model.Category;
import Model.CategoryService;
import Model.CommentService;

import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Comment;
import Model.CompareGoodsSet;
import Model.FileEntity;
import Model.GoodCollection;
import Model.GoodItem;
import Model.GoodItemService;
import Model.HistorySet;
import Model.Item;
import Model.NameComparator;
import Model.News;
import Model.OrderRow;
import Model.OrderSumCalculator;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.PayType;
import Model.PayTypeService;
import Model.PhoneService;
import Model.PopularityComparator;
import Model.PriceComparator;
import Model.PriceFilter;
import Model.Producer;
import Model.ProducerService;
import Model.PropSegment;
import Model.Property;
import Model.RatingComparator;
import Model.SearchForm;
import Model.Town;
import Model.TownService;
import Model.URLService;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueBean;
import Model.Vote;

@Controller
@RequestMapping(value = "/catalog/**/{section:(?:all|voices|features|media|others)}")
public class GoodController {
	public static final Integer PAGE_SIZE = 16;
	private Logger logger = LoggerFactory.getLogger(GoodController.class);
	@Autowired
	private GoodItemService Serv;
	
	@Autowired
	private CommentService comServ;
	
	@Autowired
	private CategoryService catServ;
	
	@Autowired
	private TownService twnServ;
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private PhoneService nmbServ;
	
	@Autowired
	private ProducerService prodServ;
	
	@Autowired
	private PayTypeService ptpServ;

	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	
	@RequestMapping(method = RequestMethod.GET)
    public String showGoodCategory(@PathVariable (value = "section") String section, @CookieValue(value = "isVoted", defaultValue = "0") Integer isVoted, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
	
		System.out.println("Выборки для товаров: ");
		User user = (User)sess.getAttribute("user");
		
		
		String restOfTheUrl = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String[] pprts = restOfTheUrl.split("/");
		String caturl = URLService.getUrlPart(restOfTheUrl, "catalog", pprts[pprts.length-1]);
		System.out.println("Наш урллллл "+caturl);

		GoodItem g = Serv.getGoodItemByUrl(caturl);
		
		CompareGoodsSet compareset = (CompareGoodsSet)sess.getAttribute("comparelist");
		if(compareset==null)compareset = new CompareGoodsSet();
		Boolean containswish = compareset.containsGood(g);
		System.out.println(containswish);
		map.put("containswish", containswish);
		
		Set<Value> vals = g.getVals();
		Map<String, String> hh = new HashMap<String, String>();
		for(Value val: vals){
			hh.put(val.getProp().getName(), val.getValue());
		}

		Set<GoodItem> history  = (Set<GoodItem>) sess.getAttribute("history");
		if(history==null)		{
			history = new HistorySet<GoodItem>();
		}
		if(user!=null){
			user.addHistorygood(g);
			usrServ.update(user);
		}
		
		for(GoodItem k: history){
			System.out.println("ИСТОРИЯ "+k.getName());
		}
		double total_rating = 0.0;
		double votes_count = (double) g.getVotes().size();
		
		for(Vote vote: g.getVotes()){
			total_rating+=vote.getId();
			
		}
		
		List<GoodItem> othergoods = g.getCategory().getGoods();
		othergoods.remove(g);
		
		Map<Producer,Long> prodlist = prodServ.getCategoryProducersWithCount(g.getCategory());
		
		
		
		List<PayType> paytypes = ptpServ.getAll();
		
		System.out.println("ptypes "+paytypes);
		
		List<Category> catstack = new ArrayList<Category>();
		catServ.fillCategoryStack(catstack, g.getCategory());
		Collections.reverse(catstack);
		
		map.put("pathtocategory", catstack);
		map.put("paytypes", paytypes);
		map.put("prodlist", prodlist);
		map.put("othergoods", othergoods);
		map.put("history", history);
		map.put("isVoted", isVoted);
		map.put("total_rating", total_rating);
		map.put("votes_count", (int)votes_count);
		
		if(votes_count==0)	map.put("averagenote", 5);
		else{
			map.put("averagenote", Math.round(total_rating/votes_count));
		}

		history.add(g);
		sess.setAttribute("history", history);
		
			
		map.put("good", g);
		map.put("segments", GoodItem.fillSegments(g, catServ.getById(g.getCategory().getId()).getPropsegments()));
		
		map.put("goodstate", g.getState());
		map.put("images", g.getImages());
//		map.put("bucketsize", bucket.getSize());
		Boolean onlyproved = false;
		Set<Comment> comms = new TreeSet<Comment>(new Comparator<Comment>(){

			@Override
			public int compare(Comment o1, Comment o2) {
				// TODO Auto-generated method stub
				return o2.getDatetime().compareTo(o1.getDatetime());
			}

		});
		if(onlyproved){
			comms = comServ.getProvedCommentsByGood(g);
		}
		else{
			comms = g.getRootComments();
		}
		setSubComments(comms, 2, onlyproved);
		map.put("com", new Comment());
		map.put("comList", comms);
		map.put("comCount", comms.size());
		map.put("properties", hh);
		map.put("section", section);
		System.out.println("showgood");
		return "goodpage";
	    }


	public void setSubComments(Set<Comment> comms, int levels, Boolean onlyproved){
		if(levels == 0){return;}
		if(comms == null && comms.isEmpty() ){return;}
		for(Comment com : comms){
			Set<Comment> subcats = comServ.getAnswers(com, onlyproved);
			com.setChieldcomms(subcats);
			setSubComments(subcats, levels-1, onlyproved);
		}
		
	}	
	
	

	

}





