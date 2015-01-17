package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import Model.AdressService;
import Model.Category;
import Model.CategoryService;
import Model.CommentService;
import Model.CountryService;
import Model.DeliveryTypeService;
import Model.GoodItemService;
import Model.GoodStateService;
import Model.Image;
import Model.ImageService;
import Model.News;
import Model.NewsBean;
import Model.NewsService;
import Model.PayTypeService;
import Model.PhoneService;
import Model.PropSegmentService;
import Model.PropertyService;
import Model.RegionService;
import Model.TextPartService;
import Model.TownService;
import Model.UserService;
import Model.ValueService;
import Model.VideoService;
import Model.VoteService;

@Controller
@RequestMapping(value = {"/admin/adminnews/{categId}"})
public class AdminNews {
	
	private Logger logger = LoggerFactory.getLogger(AdminNews.class);
	
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
	private NewsService nwsServ;
	
	@Autowired
	private VideoService vidServ;
	
	@Autowired
	private TextPartService txtServ;
	
	@RequestMapping(value = "/showcatnews", method = RequestMethod.GET)
	  public String showCatNews(@PathVariable (value="categId") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Category cat = catServ.getById(catid);
		map.put("category", cat);
		map.put("news", cat.getCatnews());
		
		return "adminNewsList";
		
		}
	
	@RequestMapping(value = "/addnews")
    public String addNews(@PathVariable (value="categId") Integer catid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		NewsBean news = new NewsBean();
		Date date = new Date(System.currentTimeMillis());
		news.setCreation_date(new SimpleDateFormat("yyyy-MM-dd").format(date));
		news.setCreation_time(new SimpleDateFormat("hh:mm").format(date));
//		map.put("category", catServ.getCategoryById(catid));
		Category cat = catServ.getById(catid);
		news.setCategory(cat);
		map.put("news", news);
		map.put("type", "create");
		map.put("category", cat);
//		map.put("catid", catid);
		return "adminNews";
		
	}
	
	@RequestMapping(value = "/deletenews/{newsid}")
    public String deleteNews(@PathVariable (value="categId") Integer catid, @PathVariable (value="newsid") Integer newsid,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		nwsServ.deleteById(newsid);
		return "redirect:/admin/adminnews/"+catid+"/showcatnews";
		
	}
	
	@RequestMapping(value = "/deletenewsimage/{newsid}/{imgid}")
    public String deleteNewsImage(@PathVariable (value="categId") Integer catid, @PathVariable (value="imgid") Integer imgid, @PathVariable (value="newsid") Integer newsid,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		System.out.println(imgid);
		News n = nwsServ.getById(newsid);
		n.removeImage((Image)imgServ.getById(imgid));
		for(Image t: n.getImages()){
			System.out.println(t.getUrl());
		}
		nwsServ.update(n);
		imgServ.deleteById(imgid); 
		return "redirect:/admin/adminnews/"+catid+"/updatenews/"+newsid;
		
	}
	
	@RequestMapping(value = "/deletenewstextpart/{newsid}/{textid}")
    public String deleteNewsTextPart(@PathVariable (value="categId") Integer catid, @PathVariable (value="textid") Integer textid, @PathVariable (value="newsid") Integer newsid,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		txtServ.deleteById(textid); 
		return "redirect:/admin/adminnews/"+catid+"/updatenews/"+newsid;
		
	}
	
	@RequestMapping(value = "/deletenewsvideo/{newsid}/{vidid}")
    public String deleteNewsVideo(@PathVariable (value="categId") Integer catid, @PathVariable (value="vidid") Integer vidid, @PathVariable (value="newsid") Integer newsid,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		vidServ.deleteById(vidid); 
		return "redirect:/admin/adminnews/"+catid+"/updatenews/"+newsid;
		
	}
	
	@RequestMapping(value = "/updatenews/{newsid}")
    public String updateNews(@PathVariable (value="categId") Integer catid, @PathVariable (value="newsid") Integer newsid,   Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		News news = nwsServ.getById(newsid);
		NewsBean nwb = new NewsBean();
		nwb.setFromNews(news);
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		f.format(news.getCreation());
		Category cat = catServ.getById(catid);
		map.put("news", nwb);
		map.put("type", "update");
		map.put("catid", catid);
		map.put("category", cat);
		return "adminNews";
		
	}
	
	
	
	private void copyFile(String path, CommonsMultipartFile file) throws IOException{
		int elem;
		InputStream in = file.getInputStream();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
		while((elem = in.read())!=-1){
			out.write(elem);
		}
	}
	
	

}
