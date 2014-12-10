package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService extends ServiceImpl<Order, Integer, OrderDAO>{

	@Autowired
	private OrderDAO ord;
	
	@Autowired
	private PhoneNumberDAO phn;
	
	@Autowired
	private AdressDAO adr;
	
	@Transactional
	public void add(Order g){
		
	Adress adr1 =adr.getAdressByParams(g.getAdress().getTown(), g.getAdress().getStreet_type(), g.getAdress().getStreet_name(), g.getAdress().getHouse_num(), g.getAdress().getRoom_num());
		if(adr1!=null){
			g.setAdress(adr1);
			
	}
		else{
		adr.add(g.getAdress());
		}
		
		
	//		template.saveOrUpdate(adr);
			
//			adr.update(g.getAdress());
			
//			List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
//			PhoneNumber nmb1 = null;
//			for(PhoneNumber nmb: g.getClient_phones()){
				
//				if((nmb1 = phn.getPhoneNumberByNum(nmb.getNumb()))!=null){
	//				nmb=nmb1;
//					System.out.println("numb exsts!!");
	//				phn.update(nmb);
					
					
//				}
//				else{
	//				phn.addPhoneNumber(nmb);
					
	//			}
	//			phones.add(nmb);
	//			}	
	//		g.setClient_phones(phones);
		ord.add(g);
	}
	
/*	@Transactional
	public void delete(Order g){
		ord.deleteOrder(g);
	}
	
	@Transactional
	public void deleteById(Integer id){
		ord.deleteOrderById(id);
	}
	
	@Transactional
	public Order getById(Integer id){
		return ord.getOrderById(id);
	}
	
	@Transactional
	public List<Order> getAll(){
		return ord.getAllOrders();
	}
	
	@Transactional
	public void update(Order k){
		ord.update(k);
	}*/
	
	@Transactional
	public Set<Order> orderDateFilter(User user, Date datefrom, Date dateto) {
		return ord.orderDateFilter(user, datefrom, dateto);
		
	}

	
/*	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}*/

	

	
	

}
