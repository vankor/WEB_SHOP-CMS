package Model.service;

import java.util.Date;
import java.util.Set;

import Model.repository.AdressDAO;
import Model.repository.OrderDAO;
import Model.repository.PhoneNumberDAO;
import Model.entity.Order;
import Model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService extends ServiceImpl<Order, Integer, OrderDAO> {

	@Autowired
	private OrderDAO ord;
	
	@Autowired
	private PhoneNumberDAO phn;
	
	@Autowired
	private AdressDAO adr;
	
	

	
	@Transactional
	public Set<Order> orderDateFilter(User user, Date datefrom, Date dateto) {
		return ord.orderDateFilter(user, datefrom, dateto);
		
	}

	


	

	
	

}
