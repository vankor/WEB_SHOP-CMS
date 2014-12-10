package cc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.AnonimBuck;
import Model.BasicConfiguration;
import Model.BasicConfigurationService;
import Model.Category;
import Model.CategoryCompareGoods;
import Model.CategoryService;
import Model.CommentService;
import Model.CompareGoodsSet;
import Model.GoodCompareBean;
import Model.GoodItem;
import Model.GoodItemService;
import Model.GoodStateService;
import Model.ImageService;
import Model.Page;
import Model.PageGroup;
import Model.PageGroupService;
import Model.PageService;
import Model.PropSegmCompareBean;
import Model.PropSegment;
import Model.Property;
import Model.PropertyCompareBean;
import Model.PropertyService;
import Model.Town;
import Model.TownService;
import Model.ValueService;

@Controller
@RequestMapping("/{catid}/comparegoods")
public class compareServ {
	
	@Autowired
	private GoodItemService Serv;
	
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
	private TownService twnServ;
	
	@Autowired
	private PageService pageServ;
	
	@Autowired
	private PageGroupService pgrServ;
	
	@Autowired
	private BasicConfigurationService bcfServ;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showCompareGoods(@PathVariable (value="catid") Integer catid, HttpSession sess, Map<String, Object> map){
		
		AnonimBuck bucket = (AnonimBuck) sess.getAttribute("currbuck");
		if(bucket==null){bucket = new AnonimBuck();}
		map.put("bucketsize", bucket.getSize());
		
		Set<Page> headerpages = pageServ.getHeaderPages();
		map.put("headerpages", headerpages);
		
		Set<PageGroup> pagegroups = pgrServ.getFooterPagegroups();
		map.put("pagegroups", pagegroups);
		
		Integer currenttownid = (Integer) sess.getAttribute("cityid");
		if(currenttownid==null){currenttownid = 908;}
		Town currenttown = new Town();
		currenttown = twnServ.getById(currenttownid);
		
		List<BasicConfiguration> bcfgs = bcfServ.getAll();
		BasicConfiguration basic = bcfgs.get(0);
		map.put("basic", basic);
		
		List<Category> roots = catServ.getRootCategories();
		map.put("currentCatList", roots);
		
		CompareGoodsSet compareset = (CompareGoodsSet)sess.getAttribute("comparelist");
		if(compareset==null)compareset = new CompareGoodsSet();
		System.out.println("–¿«Ã≈––––– "+ compareset.getCategcomparesets().size());
		for(CategoryCompareGoods h: compareset.getCategcomparesets()){
			System.out.println(h.getGoodsid());
		}
			
		CategoryCompareGoods goodcat = compareset.getComparedByCategoryId(catid);
		Set<GoodItem> goods = new TreeSet<GoodItem>();
		GoodItem g;
		for(Integer goodid: compareset.getComparedByCategoryId(catid).getGoodsid()){
			goods.add(Serv.getById(goodid));
			
		}
		Category cat = catServ.getById(catid);
		List<Property> props = new ArrayList<Property>();
		List<PropSegment> propsegments = cat.getPropsegments();
		Set<PropSegmCompareBean> propsegmset = new TreeSet<PropSegmCompareBean>();
		for(PropSegment prsg: propsegments){
	//		props.addAll(prsg.getProperties());
		
		
		
		
		
			GoodCompareBean comparebean = new GoodCompareBean();
		Set<PropertyCompareBean> propscompared = new TreeSet<PropertyCompareBean>();
		for(Property prop: prsg.getProperties()){
			PropertyCompareBean propbean = new PropertyCompareBean();
			propbean.generateCompareBeans(goods, prop);
			propscompared.add(propbean);
		}
		
		PropSegmCompareBean prsgb = new PropSegmCompareBean();
		prsgb.setPropbeans(propscompared);
		prsgb.setSgm(prsg);
		propsegmset.add(prsgb);
		}
		
		for(PropSegmCompareBean prsgb: propsegmset){
			System.out.print(prsgb.getSgm().getName()+"            ");
		for(PropertyCompareBean hh: prsgb.getPropbeans()){
			System.out.print(hh.getProp().getName()+"            ");
	//		System.out.println(hh.getComparegoods().size());
			for(GoodCompareBean j: hh.getComparegoods()){
	//			System.out.print(j.getGood().getName()+":"+" "+j.getVal().getValue()+"  ");
				
			}
			System.out.println("\n");
			
		}
		}
		map.put("goods", goods);
		map.put("propscompared", propsegmset);
		map.put("catid", catid);
		map.put("goodcount", goods.size());
				
		return "compare";
		
	}
	
	
	@RequestMapping(value = "/fromcompare", method = RequestMethod.POST)
	public String deleteCompareGood(@RequestParam (value="catid") Integer catid, @RequestParam (value="goodid") Integer goodid, @RequestParam (value="section") String section, HttpSession sess, Map<String, Object> map){
		System.out.println("heeeeeeeeeeerrrrr");
		CompareGoodsSet compareset = (CompareGoodsSet)sess.getAttribute("comparelist");
		CategoryCompareGoods goodcat = compareset.getComparedByCategoryId(catid);
		Set<GoodItem> goods = new TreeSet<GoodItem>();
		GoodItem g;
		
		compareset.removeByGoodId(goodid);
		
		for(Integer gid: compareset.getComparedByCategoryId(catid).getGoodsid()){
			if(gid!=goodid){
			goods.add((g = Serv.getById(gid)));
			}
			
		}
		
		for(Integer set: compareset.getComparedByCategoryId(catid).getGoodsid()){
			System.out.println(Serv.getById(set).getName());
		}
		
		sess.setAttribute("comparelist", compareset);
		Category cat = catServ.getById(catid);
		List<Property> props = cat.getProps();
		GoodCompareBean comparebean = new GoodCompareBean();
		Set<PropertyCompareBean> propscompared = new TreeSet<PropertyCompareBean>();
		for(Property prop: props){
			PropertyCompareBean propbean = new PropertyCompareBean();
			propbean.generateCompareBeans(goods, prop);
			propscompared.add(propbean);
		}
		
	
		map.put("goods", goods);
		map.put("section", section);
		map.put("propscompared", propscompared);
		map.put("catid", catid);
		
				
		return "commsection";
		
	}

}
