package Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PropertyDAOImpl extends GenericHibTemplateDAOImpl<Property, Integer> implements PropertyDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	public void addProperty(Property g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteProperty(Property g) {
		template.delete(g);
		
	}

	@Transactional
	public void deletePropertyById(int id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Property prop = (Property) session.get(Property.class, id);
			session.delete(prop);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Property> getAllProperties() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Property.class));
		List<Property> proplist = new ArrayList<Property>();
		for(Object obj:list){
			proplist.add((Property)obj);
		}
		return proplist;
	}

	@Transactional
	public Property getPropertyById(int id) {
		return (Property) template.get(Property.class, id);
	}

	@Transactional
	public void update(Property k) {
		template.merge(k);
		
	}

}
