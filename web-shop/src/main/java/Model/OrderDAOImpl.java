package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderDAOImpl extends GenericHibTemplateDAOImpl<Order, Integer> implements OrderDAO {

	@Autowired
    private HibernateTemplate template;
	
	@Autowired
	private PhoneNumberDAO phn;
	
	@Autowired
	private AdressDAO adr;

	@Transactional
	public void addOrder(Order g) {
		

			
		
		template.save(g);
			
	}

	@Transactional
	public void deleteOrder(Order g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteOrderById(int id) {
		template.delete(template.get(Order.class, id));
		
	}

	@Transactional
	public List<Order> getAllOrders() {
		List<Order> h = template.loadAll(Order.class);
		return h;
	}

	@Transactional
	public Order getOrderById(int id) {
		Order or = (Order) template.get(Order.class, id);
	//	template.initialize(or.getUsr());
		return or;
	}

	@Transactional
	public void update(Order k) {
		template.merge(k);
	}

	@Transactional
	public Set<Order> orderDateFilter(User user, Date datefrom, Date dateto) {
		String[] params = new String[3];
		Object[] vals = new Object[3];
		params[0] = "datefrom";
		params[1] = "dateto";
		params[2] = "userid";
		vals[0] = datefrom;
		vals[1] = dateto;
		vals[2] = user.getId();
		System.out.println("select o from Order as o join o.usr as u where o.time between("+vals[0]+", "+vals[1]+") and u.id = "+vals[2]+"");
		List<Object> list = template.findByNamedParam("select o from Order as o join o.usr as u where o.time >=:datefrom and o.time <= :dateto and u.id = :userid", params, vals);
		return toOrdertSet(list);
	}
	
	private Set<Order> toOrdertSet(List<Object> list){
		Set<Order> listPropSet = new TreeSet<Order>();
		for(Object o:list){
				Order h = (Order)o;
				listPropSet.add(h);
				
			}
		return listPropSet;
	}

}
