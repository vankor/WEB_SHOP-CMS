package Model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Model.AdressType;
import Model.entity.Town;
import Model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

		
		return toSet(list);
	}

	@Transactional
	public void deleteById(Integer id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Town twn = (Town) session.get(Town.class, id);
			session.delete(twn);
			for(User usr:twn.getUsers()){
				usr.setTown(null);
			}
			transaction.commit();
			session.flush();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
		
	
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
