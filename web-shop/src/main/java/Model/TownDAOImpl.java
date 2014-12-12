package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TownDAOImpl extends GenericHibTemplateDAOImpl<Town, Integer> implements TownDAO{
	
	
		


	
	@Transactional
	public Set<Town> getTownsByName(String value) {
		System.out.println(value);
		List<Object> list = new ArrayList<Object>();
		
		if(value !=null && !value.isEmpty()){
		list = template.findByNamedParam("select g from Town as g where g.name like :val","val",value+'%');

		}     
//		Object[] params  = new Object[1];
//		params[0] = value;
//		List<Object> list = template.find("SELECT id FROM goods WHERE MATCH(name) AGAINST(?)", params);
//		DetachedCriteria crit = DetachedCriteria.forClass(GoodItem.class,"g");
//		crit.createAlias("g.vals", "vals");
//		crit.add(Restrictions.disjunction()
	//	        .add(Restrictions.like("vals", value))
//		        .add(Restrictions.like("g.name", value))
//		        .add(Restrictions.like("g.description", value))
//		    );
//		List<Object> list = template.findByCriteria(crit);
		
		return toSet(list);
	}

	@Override
	@Transactional
	public void add(Town entity) {
		super.add(entity);
		template.flush();
	}
	
	
	public Integer save(Town entity) {
		return (Integer) template.save(entity);
	}

	@Override
	@Transactional
	public Town getTownsByCode(Integer code) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Town.class).add(Restrictions.eq("phonecode", code)));
		Town twn = null;
		if(!list.isEmpty()){
			twn = (Town) list.get(0);
		}
		
		return twn;
	}

	@Override
	@Transactional
	public Set<Town> getResidentTowns() {
		List<Object> list = template.findByNamedParam("select t from Town as t INNER JOIN t.residents as r where r.adresstype = :adresstype", "adresstype", AdressType.RESIDENT);
		template.setCacheQueries(true);
		return toSet(list);
	}

	@Override
	@Transactional
	public void setDefault(Town town) {
		Session sess = template.getSessionFactory().openSession();
		Query query = sess.createQuery("update Town set isdefault = false where isdefault = true");
		query.executeUpdate();
		town.setIsdefault(true);
		
		
	}

	@Override
	@Transactional
	public Town getDefault() {
		Session sess = template.getSessionFactory().openSession();
		Town town = (Town) sess.createCriteria(Town.class).add(Restrictions.eq("isdefault", true)).uniqueResult();
		
		return town;
	}



}
