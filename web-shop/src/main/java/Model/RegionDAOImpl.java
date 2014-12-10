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
public class RegionDAOImpl extends GenericHibTemplateDAOImpl<Region, Integer> implements RegionDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	public void addRegion(Region g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteRegion(Region g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteRegionById(int id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Region prop = (Region) session.get(Region.class, id);
			session.delete(prop);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Region> getAllCountries() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Region.class));
		List<Region> proplist = new ArrayList<Region>();
		for(Object obj:list){
			proplist.add((Region)obj);
		}
		return proplist;
	}

	@Transactional
	public Region getRegionById(int id) {
		return (Region) template.get(Region.class, id);
	}

	@Transactional
	public void update(Region k) {
		template.merge(k);
		
	}

}
