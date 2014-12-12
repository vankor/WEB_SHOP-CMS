package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TownService extends ServiceImpl<Town, Integer, TownDAO>{
	
	@Autowired
	private TownDAO townDAO;
	
	@Autowired
	private AdressDAO adrDAO;
	
	@Autowired
	private	Town_DeliveryDAO twndlvDAO;
	
	@Autowired
	private	Adress_DeliveryDAO adrdlDAO;
	
/*	@Transactional
	public void add(Town g){
		townDAO.addTown(g);
	}
	
	@Transactional
	public void delete(Town g){
		townDAO.deleteTown(g);
	}
	
	@Transactional
	public void deleteById(Integer id){
		townDAO.deleteTownById(id);
	}
	
	@Transactional
	public Set<Town> getAll(){
		return townDAO.getAllTowns();
	}
	
	@Transactional
	public Town getById(Integer id){
		return townDAO.getTownById(id);
	}
*/	
	
public void fillnumber(PhoneNumber nphn){
		
		String phnnumb = nphn.getNumb();
		if(phnnumb!=null && !phnnumb.isEmpty()&&!phnnumb.equals("")){
			String code = phnnumb.substring(phnnumb.indexOf("(")+1,phnnumb.indexOf(")"));
			Town twn = townDAO.getTownsByCode(Integer.parseInt(code));
			System.out.println(code);
			
			System.out.println();
//				adr.addNumber(phn);
			if(twn != null){
				nphn.setTown(twn);
				nphn.setPhtype(PhoneNumberType.STATIONAR);
			}
			else{
				nphn.setPhtype(PhoneNumberType.MOBILE);
			}
		}
	}
	
	@Transactional
	public Town getDefault() {
		return townDAO.getDefault();
	}

	@Transactional
	public void update(Town town, TownBean townbean){

		if(townbean.getIsdefault()==true){
		setDefault(town);
		System.out.println(town.getIsdefault()+ "   DEFFFFAUUUULTTT!");
		}
		else{
		town.setIsdefault(false);	
		}
		Iterator<Adress> iter = town.getResidents().iterator();
		while(iter.hasNext()){
			Adress proper = iter.next();
			if(!townbean.getResidents().contains(proper)){
				iter.remove();
				System.out.println("Элемент "+proper.getStreet_name()+" удален ");
			}
			
		}
		
		for(Adress p:townbean.getResidents()){
			Adress naddr = new Adress();
			if(p.getId()!=null && town.getResidents().contains(p)){
				System.out.println("addr id !=null "+p.getId());
				for(Adress a:town.getResidents()){
					if(a.getId()!=null && a.getId().equals(p.getId())){
						naddr = a;
					}
				}
				naddr.setAdresstype(p.getAdresstype());
				naddr.setHouse_num(p.getHouse_num());
				if(p.getLevel().equals(""))	naddr.setLevel(null);
				else naddr.setLevel(p.getLevel());
				if(p.getRoom_num().equals(""))	naddr.setRoom_num(null);
				else naddr.setRoom_num(p.getRoom_num());
				
				naddr.setStreet_name(p.getStreet_name());
				naddr.setStreet_type(p.getStreet_type());	
				
					PhoneNumber ntodel = new PhoneNumber();
					Iterator<PhoneNumber> iter1 = p.getPhnnumbers().iterator();
					while(iter1.hasNext()){
						PhoneNumber proper = iter1.next();
						System.out.println("wfwfwfwf "+proper.getId()+"     "+proper.getNumb()+"    ");
						if(proper.getId() == null && proper.getNumb() == null){
							System.out.println("УДАЛЕН НОМЕР ");
							iter1.remove();
						}
						
					}
					System.out.println("НОМЕРОВ в бине"+p.getPhnnumbers().size());	
				System.out.println("НОМЕРОВ в адресе до"+naddr.getPhnnumbers().size());
					for(PhoneNumber pn: p.getPhnnumbers()){
						PhoneNumber nphn = new PhoneNumber();
						Iterator<PhoneNumber> iter2 = naddr.getPhnnumbers().iterator();
						while(iter2.hasNext()){
							PhoneNumber phn = iter2.next();
							System.out.println("Количество элементов "+elementNumber(phn,p.getPhnnumbers()));
							if(elementNumber(phn,p.getPhnnumbers())!=1){
								System.out.println("Удалена нулевая запись");
								iter2.remove();
							
								}
							if(phn.getId()!=null && phn.getId().equals(pn.getId())){
								nphn = phn;
								
							}
						}
						if(pn.getId()!=null){
							System.out.println("Номер "+pn.getNumb()+" не нулевой!");
						
						System.out.println("Выбран номе "+nphn.getNumb());
						}
						else{
							System.out.println("Новый номер "+pn.getNumb());
						}
						
						
						if(!(pn.getIsCostlessLine()==null && nphn.getIsCostlessLine()!=null))
						nphn.setIsCostlessLine(pn.getIsCostlessLine());
						if(!(pn.getIsMainCommonNumber()==null && nphn.getIsMainCommonNumber()!=null))
							nphn.setIsMainCommonNumber(pn.getIsMainCommonNumber());
						if(!(pn.getIsMainResidentNumber()==null && nphn.getIsMainResidentNumber()!=null))
							nphn.setIsMainResidentNumber(pn.getIsMainResidentNumber());
						if(pn.getNumb()!=null && !pn.getNumb().equals("")){
							nphn.setNumb(pn.getNumb());
							
						}
						
						fillnumber(nphn);
												
						if(!naddr.getPhnnumbers().contains(nphn)){

							naddr.addNumber(nphn);	
							System.out.println("Номер "+nphn.getNumb()+" добавлен!");
							
						}
							
					}
					System.out.println("НОМЕРОВ в адресе"+naddr.getPhnnumbers().size());
					naddr.getAddrdel().clear();
					for(DeliveryTypeBean d: p.getDeltypes()){
						Adress_Delivery addrdel = new Adress_Delivery();
						if(d.getDeltype()!=null && d.getDeltype().getId()!=null){
																				
						
						if(!((d.getDeltype()==null || d.getDeltype().getId()==null) && addrdel.getDeliveryType()!=null))
							addrdel.setDeliveryType(d.getDeltype());
						System.out.println(addrdel==null);
						addrdel.setAdress(naddr);
						if(d.getIscostless()!=null && d.getIscostless()==true)
						addrdel.setIscostless(true);
						else
							addrdel.setIscostless(false);	
							
						addrdel.setMaxdelprice(d.getMaxprice());
						addrdel.setMindelprice(d.getMinprice());
						addrdel.setMaxdelterm(d.getMaxdays());
						addrdel.setMindelterm(d.getMindays());
						System.out.println(addrdel.getPk().getDelivery().getId());
						System.out.println(addrdel.getPk().getAdress().getId());
						adrdlDAO.saveOrUpdate(addrdel);
						naddr.getAddrdel().add(addrdel);
						}		
						
					}
			}
			if(p.getId()==null){
				System.out.println("addr id ==null ");
				naddr.setAdresstype(p.getAdresstype());
				naddr.setHouse_num(p.getHouse_num());
				naddr.setLevel(p.getLevel());
				naddr.setRoom_num(p.getRoom_num());
				naddr.setStreet_name(p.getStreet_name());
				naddr.setStreet_type(p.getStreet_type());
				for(PhoneNumber pn: p.getPhnnumbers()){
					PhoneNumber nphn = new PhoneNumber();
					
					nphn.setIsCostlessLine(pn.getIsCostlessLine());
					nphn.setIsMainCommonNumber(pn.getIsMainCommonNumber());
					nphn.setIsMainResidentNumber(pn.getIsMainResidentNumber());
					nphn.setNumb(pn.getNumb());
					nphn.setResident(naddr);
					fillnumber(nphn);
					naddr.addNumber(nphn);	
					System.out.println(naddr+"  Адресок у номерка "+nphn.getResident()+"    "+nphn.getNumb());
				}
				
				naddr.getAddrdel().clear();
				for(DeliveryTypeBean d: p.getDeltypes()){
					Adress_Delivery addrdel = new Adress_Delivery();
					if(d.getDeltype()!=null && d.getDeltype().getId()!=null){
																			
					
					if(!((d.getDeltype()==null || d.getDeltype().getId()==null) && addrdel.getDeliveryType()!=null))
						addrdel.setDeliveryType(d.getDeltype());
					System.out.println(addrdel==null);
					addrdel.setAdress(naddr);
					if(d.getIscostless()!=null && d.getIscostless()==true)
					addrdel.setIscostless(true);
					else
						addrdel.setIscostless(false);	
						
					addrdel.setMaxdelprice(d.getMaxprice());
					addrdel.setMindelprice(d.getMinprice());
					addrdel.setMaxdelterm(d.getMaxdays());
					addrdel.setMindelterm(d.getMindays());
					System.out.println(addrdel.getPk().getDelivery().getId());
					System.out.println(addrdel.getPk().getAdress().getId());
					System.out.println("addr_del o "+addrdel.getPk());
					adrDAO.save(naddr);
					System.out.println(addrdel.getPk().getAdress().getId());
					
					adrdlDAO.update(addrdel);
					naddr.getAddrdel().add(addrdel);
					}		
					
				}
				town.addAdress(naddr);
			}
			
			
		}
		
		
		town.getTowndeliveries().clear();
		for(DeliveryTypeBean d: townbean.getDeltypes()){
			Town_Delivery addrdel = new Town_Delivery();
			if(d.getDeltype().getId()!=null){
																	
			
			if(!((d.getDeltype()==null || d.getDeltype().getId()==null) && addrdel.getDeliveryType()!=null))
				addrdel.setDeliveryType(d.getDeltype());
			System.out.println(addrdel==null);
			addrdel.setTown(town);
			if(d.getIscostless()!=null && d.getIscostless()==true)
			addrdel.setIscostless(true);
			else
				addrdel.setIscostless(false);	
				
			addrdel.setPrice(d.getPrice());
	//		addrdel.se(d.getDays());
			addrdel.setIscostless(d.getIscostless());
	//		addrdel.setMindelterm(d.getMindays());
			twndlvDAO.update(addrdel);
			town.getTowndeliveries().add(addrdel);
			}		
					
		}
		
		System.out.println("ИТОГ ПО ГОРОДУ");
		
		System.out.println(town.getId());
		System.out.println(town.getName());
		System.out.println(town.getPhonecode());
		System.out.println(town.getRegion().getId());
		System.out.println(town.getRegion().getName());
		System.out.println(town.getRegion().getCountry().getId());
		System.out.println(town.getRegion().getCountry().getName());
		System.out.println(town.getPhonecode());
		System.out.println("АДРЕСОВ "+town.getResidents().size());
		System.out.println("АДРЕСАААА");
		for(Adress adr: town.getResidents()){
			System.out.println("addrid "+adr.getId());
			System.out.println(adr.getStreet_name());
			System.out.println(adr.getStreet_type());
			System.out.println(adr.getHouse_num());
			System.out.println(adr.getLevel());
			System.out.println(adr.getRoom_num());
			System.out.println(adr.getAdresstype());
			System.out.println("НОМЕРОВ "+adr.getPhnnumbers().size());
			for(PhoneNumber t: adr.getPhnnumbers()){
				System.out.println(t.getNumb());
				System.out.println(t.getIsMainResidentNumber());
				System.out.println(t.getResident());
				if(t.getTown()!=null)
				System.out.println(t.getTown().getName());
			}
			System.out.println("адреса и доставка!");
			for(Adress_Delivery bn:adr.getAddrdel()){
				System.out.println("addr_del "+bn.getPk());
				System.out.println(bn.getDeliveryType().getId());
				System.out.println(bn.getDeliveryType().getShortname());
				System.out.println(bn.getDeliveryType().getName());
				System.out.println(bn.getMindelprice());
				System.out.println(bn.getMaxdelprice());
				System.out.println(bn.getMindelterm());
				System.out.println(bn.getMaxdelterm());
				System.out.println(bn.getIscostless());
			}
		}
		System.out.println("город и доставка!");
		for(Town_Delivery bn:town.getTowndeliveries()){
			System.out.println(bn.getDeliveryType().getId());
			System.out.println(bn.getDeliveryType().getShortname());
			System.out.println(bn.getDeliveryType().getName());
			System.out.println(bn.getPrice());
			System.out.println(bn.getIscostless());
		}
		
		
		
		
		
			System.out.println(town.getId());		
			if(town.getId()!=null)townDAO.update(town);
			else townDAO.save(town);
		
	}
	
	@Transactional
	public void add(Town g, TownBean bean){
		List<DeliveryType> dlvlist = new ArrayList<DeliveryType>();
		for(DeliveryTypeBean dtp: bean.getDeltypes()){
			boolean contains = false;
			DeliveryType deltype = dtp.getDeltype();
			dlvlist.add(dtp.getDeltype());
			for(Town_Delivery tdlv:g.getTowndeliveries()){
				if(tdlv.getDeliveryType().equals(deltype)){
					contains=true;
					tdlv.setPrice(dtp.getPrice());
					tdlv.setIscostless(dtp.getIscostless());
				}
			}
			
			if(!contains){
				Town_Delivery twndlv = new Town_Delivery();
				twndlv.setDeliveryType(deltype);
				twndlv.setTown(g);
				twndlv.setPrice(dtp.getPrice());
				twndlv.setIscostless(dtp.getIscostless());
				g.addTowndelivery(twndlv);
				deltype.addTowndeliverytype(twndlv);
				
			}
			
		}
		
		townDAO.add(g);
		
		
		
	}
	
	private Integer elementNumber(Object r, List list){
		Integer counter = 0;
		for(Object obj:list){
			if(r.equals(obj))
				counter++;
		}
		return counter;
		
	}

	@Transactional
	public void deleteResidentAdress(Town t, Adress adr){
		List<Adress>adrs = new ArrayList<Adress>();
		for(Adress d: t.getResidents()){
			if(d.getId().equals(adr.getId())){
				d.setTown(null);
			}
			
		}
		
		for (Order child : adr.getOrders()) {
		    child.setAdress(null);
		}
		
		for (User child : adr.getUsers()) {
		    child.setAdress(null);
		}
		adr.setTown(null);
		adrDAO.deleteById(adr.getId());
		
/*		t.getResidents().clear();
		t.setResidents(adrs);
		
		for(Adress j: t.getResidents()){
			System.out.println(j.getStreet_type()+"   "+j.getHouse_num()+"  "+j.getStreet_name()+ "  "+ j.getRoom_num());
		}*/
//		twn.getResidents().remove(adrServ.getById(resid));
//		townDAO.update(t);

	}


	@Transactional
	public Set<Town> getTownsByName(String name){
		return townDAO.getTownsByName(name);
	}
	
	@Transactional
	public Town getTownsByCode(Integer code) {
		return townDAO.getTownsByCode(code);
	}
	@Transactional
	public Set<Town> getResidentTowns(){
		return townDAO.getResidentTowns();
	}

	public Integer save(Town town) {
		return (Integer) townDAO.save(town);
	}

	@Transactional
	public void setDefault(Town town) {
		townDAO.setDefault(town);
	}

/*	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}
	
*/
}
