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
public class CountryDAOImpl extends GenericHibTemplateDAOImpl<Country, Integer> implements CountryDAO{

	@Autowired
	private HibernateTemplate template;
	
/*	@Transactional
	public void addCountry(Country g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteCountry(Country g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteCountryById(int id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Country prop = (Country) session.get(Country.class, id);
			session.delete(prop);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Country> getAllCountries() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Country.class));
		List<Country> proplist = new ArrayList<Country>();
		for(Object obj:list){
			proplist.add((Country)obj);
		}
		return proplist;
	}

	@Transactional
	public Country getCountryById(int id) {
		return (Country) template.get(Country.class, id);
	}

	@Transactional
	public void update(Country k) {
		template.merge(k);
		
	}
*/
}
