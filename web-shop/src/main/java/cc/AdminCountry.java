package cc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import Model.Adress;
import Model.AdressService;
import Model.AdressType;
import Model.Adress_Delivery;
import Model.AnonimBuck;
import Model.Category;
import Model.CategoryCreateBean;
import Model.CategoryService;
import Model.CommentService;
import Model.Country;
import Model.CountryBean;
import Model.CountryService;
import Model.DeliveryType;
import Model.DeliveryTypeBean;
import Model.DeliveryTypeService;
import Model.GoodItem;
import Model.GoodItemCreationBean;
import Model.GoodItemService;
import Model.GoodPropSegmentBean;
import Model.GoodStateService;
import Model.IdBean;
import Model.Image;
import Model.ImageService;
import Model.ParamContainer;
import Model.ParamFilterBean;
import Model.PayTypeService;
import Model.PhoneNumber;
import Model.PhoneNumberType;
import Model.PhoneService;
import Model.PropSegment;
import Model.PropSegmentService;
import Model.Property;
import Model.PropertyService;
import Model.Region;
import Model.RegionListBean;
import Model.RegionService;
import Model.SearchForm;
import Model.Town;
import Model.TownBean;
import Model.TownService;
import Model.Town_Delivery;
import Model.User;
import Model.UserService;
import Model.Value;
import Model.ValueService;
import Model.VoteService;

@Controller
@RequestMapping(value = {"/admin/admincountry/{countryId}"})
public class AdminCountry{
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
	
	
	@RequestMapping(method = RequestMethod.GET)
    public String adminCountry(@PathVariable (value = "countryId") Integer id, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Country cntr = cntrServ.getById(id);
		Region reg = new Region();
		if(cntr.getRegions()!=null && !cntr.getRegions().isEmpty()){
			reg = cntr.getRegions().get(0);
		}
		map.put("region", reg);
		map.put("country", cntr);
		System.out.println(reg.getCountry().getName());
		map.put("type", "update");
		return "adminRegions";
		
	}
	@RequestMapping(value = "/deleteRegion/{regId}", method = RequestMethod.GET)
    public String deleteRegion(@PathVariable (value = "countryId") Integer cid, @PathVariable (value = "regId") Integer regid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		regServ.deleteById(regid);
		return "redirect:/admin/admincountry/"+cid;
	
	}
	
	@RequestMapping(value = "/deleteTown/{townId}", method = RequestMethod.GET)
    public String deleteTown(@PathVariable (value = "countryId") Integer cid, @PathVariable (value = "townId") Integer twnid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		twnServ.deleteById(twnid);
		return "redirect:/admin/admincountry/"+cid;
	
	}
	
	
	
	@RequestMapping(value = "/updatedRegion", method = RequestMethod.POST)
    public String updatedRegion(@ModelAttribute (value = "region") Region reg, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Region region = new Region();
		if(reg.getId()!=null){
			region = regServ.getById(reg.getId());
		}
		region.setName(reg.getName());
		regServ.update(region);
		map.put("region", region);
		map.put("country", region.getCountry());
		map.put("newregs", new RegionListBean());
		return "adminRegions";
	
	}
	
	@RequestMapping(value = "/addRegions", method = RequestMethod.POST)
    public String addRegions(@ModelAttribute (value = "newregs") RegionListBean list, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		String referrer = request.getHeader("referer");
		if(list==null || list.getRegs()==null || list.getRegs().isEmpty()){return "redirect:"+referrer;}
		Integer ctid = list.getRegs().get(0).getCountry().getId();
		Country cntr = cntrServ.getById(ctid);
		for(Region rr: list.getRegs()){
			cntr.getRegions().add(rr);
			rr.setCountry(cntr);	
			System.out.println("NFMEE "+rr.getName());
			
		}
		cntrServ.update(cntr);
		
		return "redirect:"+referrer;
	
	}
	
	
	
	@RequestMapping(value = "/adminTown/{townId}/deleteAdress/{resId}", method = RequestMethod.GET)
    public String deleteResident(@PathVariable (value = "countryId") Integer cid, @PathVariable (value = "townId") Integer twnid, @PathVariable (value = "resId") Integer resid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Town twn = twnServ.getById(twnid);
		Adress f = adrServ.getById(resid);
		twnServ.deleteResidentAdress(twn, f);
//		twnServ.deleteById(twnid);
		return "redirect:/admin/admincountry/"+cid+"/adminTown/"+twnid;
	
	}
	
	@RequestMapping(value = "/region/{regid}/addtown", method = RequestMethod.GET)
    public String addTown(@PathVariable (value = "countryId") Integer cid, @PathVariable (value = "regid") Integer regid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		TownBean town = new TownBean();
		
/*		for(DeliveryType dlv: town.getDeltypes()){
			idbean.addId(dlv.getId());
		}*/
		
		Country country = cntrServ.getById(cid);
		Region reg = regServ.getById(regid);
		town.setRegion(reg);
		List<DeliveryType> dlvlst = dlvServ.getAll();
//		map.put("idbean", idbean);
		map.put("town", town);
		map.put("deltypes", dlvlst);
		map.put("regions", country.getRegions());
		map.put("countries", cntrServ.getAll());
	//	dlvlst.removeAll(town.getDeltypes());
	//	map.put("uncheckedels", dlvlst);
		return "adminTown";
	}
	
	
	@RequestMapping(value = "/adminTown/{townId}", method = RequestMethod.GET)
    public String adminTown(@RequestParam(required = false, value = "result") String result, @PathVariable (value = "countryId") Integer cid, @PathVariable (value = "townId") Integer twnid, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Town town0 = twnServ.getById(twnid);
		TownBean town = new TownBean();
		town.constructFromTown(town0);
/*		for(DeliveryType dlv: town.getDeltypes()){
			idbean.addId(dlv.getId());
		}*/
		System.out.println("–‡ÁÏÂ "+ town0.getTowndeliveries().size());
		for(Adress l: town.getResidents())
		{
			l.filldeltypes();
			System.out.println("«‡ÔÓÎÌÂÌÓ: ");
			for(DeliveryTypeBean g:l.getDeltypes()){
				System.out.println(g.getMindays()+"    "+g.getMaxdays()+"     "+g.getMinprice()+"    "+g.getMaxprice());
			}
			
			for(PhoneNumber t:l.getPhnnumbers()){
				System.out.println(t.getNumb());
			}
		}
		
		List<DeliveryType> dlvlst = dlvServ.getAll();
//		map.put("idbean", idbean);
		map.put("town", town);
		map.put("deltypes", dlvlst);
		map.put("regions", town0.getRegion().getCountry().getRegions());
		map.put("countries", cntrServ.getAll());
	//	dlvlst.removeAll(town.getDeltypes());
		map.put("result", result);
		return "adminTown";
	}
	
	
	@RequestMapping(value = "/updatedCountry", method = RequestMethod.POST)
	 public String updatedCountry(@ModelAttribute (value = "country") Country country, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
		Country cntr = cntrServ.getById(country.getId());
		
		cntr.setName(country.getName());
		int i = 0;
		for(Region r:country.getRegions()){
			if(r!=null){
				if(cntr.getRegions().contains(r)){
				Region old = regServ.getById(r.getId());
				old.setName(r.getName());
				regServ.update(old);
				}
				else{
					cntr.getRegions().add(r);
				}
			}
			i++;
		}
		
		for(Region r:cntr.getRegions()){
			System.out.println(r.getName());
		}
			
//		cntrServ.update(cntr);
		return "redirect:/admin/admincountry/"+country.getId();
		
	}
	
	
	@RequestMapping(value = "/updatedTown", method = RequestMethod.POST)
	 public String updatedTown(@ModelAttribute (value = "town") @Valid TownBean townbean,  BindingResult bindingResult, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
	//	System.out.println("“»œ ”ÀÃ÷€ .... "+townbean.getResidents().get(0).getStreet_type());
		
		System.out.println(townbean.getId());
		System.out.println(townbean.getName());
		System.out.println(townbean.getPhonecode());
		System.out.println(townbean.getRegion().getId());
		System.out.println(townbean.getRegion().getName());
		System.out.println(townbean.getRegion().getCountry().getId());
		System.out.println(townbean.getRegion().getCountry().getName());
		System.out.println(townbean.getPhonecode());
		System.out.println(townbean.getPhonecode());
		System.out.println(townbean.getPhonecode());
		System.out.println("¿ƒ–≈—¿¿¿¿");
		for(Adress adr: townbean.getResidents()){
			System.out.println(adr.getStreet_name());
			System.out.println(adr.getStreet_type());
			System.out.println(adr.getHouse_num());
			System.out.println(adr.getLevel());
			System.out.println(adr.getRoom_num());
			System.out.println(adr.getAdresstype());
			for(PhoneNumber t: adr.getPhnnumbers()){
				System.out.println(t.getNumb());
				System.out.println(t.getIsMainResidentNumber());
			}
			System.out.println("‡‰ÂÒ‡ Ë ‰ÓÒÚ‡‚Í‡!");
			for(DeliveryTypeBean bn:adr.getDeltypes()){
				if(bn.getDeltype()!=null){
				System.out.println(bn);
				System.out.println(bn.getDeltype());
				System.out.println(bn.getDeltype().getId());
				System.out.println(bn.getDeltype().getShortname());
				System.out.println(bn.getDeltype().getName());
				System.out.println(bn.getMinprice());
				System.out.println(bn.getMaxprice());
				System.out.println(bn.getMindays());
				System.out.println(bn.getMaxdays());
				System.out.println(bn.getIscostless());
				}
			}
		}
		
		System.out.println("Œ¡Ÿ»≈ ƒÀﬂ √Œ–Œƒ¿ ÷»‘–€ œŒ ƒŒ—“¿¬ ≈");
		
			for(DeliveryTypeBean bn:townbean.getDeltypes()){
				System.out.println(bn.getDeltype().getId());
				System.out.println(bn.getDeltype().getShortname());
				System.out.println(bn.getDeltype().getName());
				System.out.println(bn.getMinprice());
				System.out.println(bn.getMaxprice());
				System.out.println(bn.getMindays());
				System.out.println(bn.getMaxdays());
				System.out.println(bn.getIscostless());
			}
		
		
		
		if (bindingResult.hasErrors()) {
			List<DeliveryType> dlvlst = dlvServ.getAll();
			map.put("town", townbean);
			map.put("deltypes", dlvlst);
			map.put("regions", townbean.getRegion().getCountry().getRegions());
			map.put("countries", cntrServ.getAll());
		    return "adminTown";
        }
		
		
		
		List<DeliveryTypeBean> twndlv = townbean.getDeltypes();
		ListIterator<DeliveryTypeBean> ltr = twndlv.listIterator();
		System.out.println("SIIIIZE 1"+ twndlv.size());
		while(ltr.hasNext()){
			DeliveryTypeBean db= ltr.next();
			System.out.println(db.getDeltype().getId()+"   "+db.getDeltype().getName()+"   "+db.getPrice()+"   "+db.getIscostless());
			if(db.getDeltype().getId()==null){
				System.out.println("removing....");
				ltr.remove();
			}
			else{
				db.setDeltype(dlvServ.getById(db.getDeltype().getId()));
			}
			if(db.getIscostless()!=null){
				db.setIscostless(true);
			}
			else{
				db.setIscostless(false);
			}
		}
		
		
		System.out.println("SIIIIZE 2"+ twndlv.size());
		
		Town town = new Town();
		if(townbean.getId()!=null){
			town = twnServ.getById(townbean.getId());
		}
			
		town.constructFromBean(townbean);
				
		twnServ.update(town, townbean);
		
		List<DeliveryType> dlvlst = dlvServ.getAll();
		map.put("town", townbean);
		map.put("deltypes", dlvlst);
		map.put("regions", townbean.getRegion().getCountry().getRegions());
		map.put("countries", cntrServ.getAll());
		String referer = request.getHeader("referer").split("\\?")[0];
		map.put("result", "success");
		return "redirect:"+referer;
	}
	
	@RequestMapping(value = "/addDelTypeToGood", method = RequestMethod.POST)
    public String addDelTypeToGood(@RequestParam (value = "counter") Integer counter,@RequestParam (value = "type") String type, @RequestParam (value = "innercounter") Integer innercounter, Map<String, Object> map, HttpServletRequest request, HttpSession sess) {
	//	Town town = twnServ.getTownById(id);
//		Set<DeliveryType> deltypes = dlvServ.getAllDeliveryTypes();
//		deltypes.removeAll(town.getDeltypes());	
		Adress resident = new Adress();
		List<DeliveryType> dlvlst = new ArrayList<DeliveryType>();
		if(!type.equals(AdressType.DELIVERYSERVICE.toString()) && !type.equals(AdressType.RESIDENT.toString())){
			dlvlst = dlvServ.getAll();
		}
		else{
		if(type.equals(AdressType.DELIVERYSERVICE.toString())){
			dlvlst = dlvServ.getByType(AdressType.DELIVERYSERVICE);
		}
		if(type.equals(AdressType.RESIDENT.toString())){
			dlvlst = dlvServ.getByType(AdressType.RESIDENT);
		}
		}
		
		map.put("innercounter", innercounter);
		map.put("type", type);
		map.put("resident", resident);
		map.put("deltypes", dlvlst);
		map.put("section", "addresident");
		map.put("counter",counter);
		return "admincommsection";
	
	}
	
	
	
	
	
/*	public void filladressphones(List<Adress> list, AdressType addrtp){
		for(Adress adr:list){
			System.out.println(adr.getId());
			
			if(adr.getId()==null){
				adr.setAdresstype(addrtp);
				for(PhoneNumber phn:adr.getPhnnumbers()){
					String phnnumb = phn.getNumb();
					
					if(!phnnumb.isEmpty()&&!phnnumb.equals("")){
						String code = phnnumb.substring(phnnumb.indexOf("(")+1,phnnumb.indexOf(")"));
						Town twn = twnServ.getTownsByCode(Integer.parseInt(code));
						System.out.println(code);
						phn.setResident(adr);
						System.out.println();
		//				adr.addNumber(phn);
						if(twn != null){
							phn.setTown(twn);
							phn.setPhtype(PhoneNumberType.STATIONAR);
						}
						else{
							phn.setPhtype(PhoneNumberType.MOBILE);
						}
					}
					
				}
			}
		}
	}*/

}