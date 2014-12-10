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
public class PayTypeDAOImpl extends GenericHibTemplateDAOImpl<PayType, Integer> implements PayTypeDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	public void addPayType(PayType g) {
		template.save(g);
		
	}

	@Transactional
	public void deletePayType(PayType g) {
		template.delete(g);
		
	}

	@Transactional
	public void deletePayTypeById(int id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			PayType prop = (PayType) session.get(PayType.class, id);
			session.delete(prop);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<PayType> getAllProperties() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(PayType.class));
		List<PayType> proplist = new ArrayList<PayType>();
		for(Object obj:list){
			proplist.add((PayType)obj);
		}
		return proplist;
	}

	@Transactional
	public PayType getPayTypeById(int id) {
		return (PayType) template.get(PayType.class, id);
	}

	@Transactional
	public void update(PayType k) {
		template.merge(k);
		
	}

}
