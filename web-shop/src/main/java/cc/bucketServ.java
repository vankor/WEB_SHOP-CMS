package cc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.AnonimBuck;
import Model.Bucket;
import Model.BucketService;
import Model.Category;
import Model.CategoryService;
import Model.GoodCollection;
import Model.GoodItem;
import Model.GoodItemService;
import Model.Item;
import Model.Order;
import Model.OrderRow;
import Model.OrderService;
import Model.OrderSumCalculator;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.Property;
import Model.PropertyService;
import Model.SearchForm;
import Model.User;
import Model.Value;

@Controller
public class bucketServ {

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
	
	@RequestMapping("/listbuck/{buckid}")
	public String listBuckGoodsById(@PathVariable(value = "buckid")Integer id, Map <String, Object> map){
		if(id == 0 ){return "redirect:/listbuck";}
		Bucket t = buckServ.getById(id);
		List<GoodItem> li = t.getGoods();
		map.put("goods", new GoodItem());
		map.put("goodList", li);
		map.put("buck", t);
		System.out.println("listGoodsOfBucket");
		return "buckgoodlist";
	}
	
	@RequestMapping("/listbuck")
	public String listBuckGoodsBy(Map <String, Object> map, HttpSession sess){
		User t = (User)sess.getAttribute("usr");
		AnonimBuck b = (AnonimBuck) sess.getAttribute("currbuck");
	//	Session hibsess = SessionFactoryUtils.getSession(sessfactory, false);
	//	hibsess.saveOrUpdate(b);
	//	List <GoodItem> goods = new ArrayList<GoodItem>();
	//	List<Integer> goodsid = b.getGoodsId();
		
	//	for(int i = 0; i < goodsid.size(); i++){
	//		goods.add(goodServ.getGoodById(goodsid.get(i)));
			
	//	}
//		b.setGoods(goods);
		GoodCollection g = (GoodCollection)b;
		OrderSumCalculator calc = new OrderSumCalculator(g);
		Iterator iter = calc.getOrdmap().entrySet().iterator();
		Entry<GoodCollection, Double> entry = (Entry<GoodCollection, Double>)iter.next();
		double sum = entry.getValue();
		System.out.println("—ÛÏÏ‡: "+ sum);
		map.put("goods", new GoodItem());
	//	map.put("goodList", b.getRows());
		map.put("buck", b);
		map.put("sum", sum);
		return "buckgoodlist";
	}
	
	
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
       System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        List<String> list = new ArrayList<String>();
        list.add("2f32f");
        list.add("qwfqwf");
        return result;
    }
	
	
	
	
	@RequestMapping("/anonimgoods")
	public String listAnonimGoods(Map <String, Object> map, HttpSession sess){
		System.out.println("listGoodsOfBucket");
	//	User t = (User)sess.getAttribute("usr");
	//	List<GoodItem> li = (ArrayList<GoodItem>)sess.getAttribute("currgoods");
	//	map.put("goods", new GoodItem());
	//	map.put("goodList", li);
		return "anonimgoods";
		
	}
	

	
	@RequestMapping("/deletefrombuck/{goodid}")
	public String deleteFromBuck(@PathVariable(value = "goodid")Integer id, Map <String, Object> map){
///		Bucket t = buckServ.getBucketById(id); ??
		GoodItem g = goodServ.getById(id);
//		t.deleteGood(g);
		System.out.println("listGoodsOfBucket");
		return "buckgoodlist";
	}
	

	
	
	


	
}
