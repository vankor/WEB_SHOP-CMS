package Model.repository;

import Model.entity.Order;
import Model.entity.User;
import Model.repository.GenericDAO;

import java.util.Date;
import java.util.Set;

	public interface OrderDAO extends GenericDAO<Order, Integer> {
/*	public void addOrder(Order g);
	public void deleteOrder(Order g);
	public void deleteOrderById(int id);
	public List<Order> getAllOrders();
	public Order getOrderById(int id);
	public void update(Order k);*/
	public Set <Order> orderDateFilter(User user, Date datefrom, Date dateto);
	
}

