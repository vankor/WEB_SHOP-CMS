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
