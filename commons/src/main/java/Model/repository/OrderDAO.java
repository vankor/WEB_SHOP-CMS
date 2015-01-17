package Model.repository;

import Model.entity.Order;
import Model.entity.User;
import Model.repository.GenericDAO;

import java.util.Date;
import java.util.Set;

	public interface OrderDAO extends GenericDAO<Order, Integer> {

	public Set <Order> orderDateFilter(User user, Date datefrom, Date dateto);
	
}

