package Model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.AnyType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.ObjectType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Model.News.OrderingRules;

@Repository
public class NewsDAOImpl extends GenericHibTemplateDAOImpl<News, Integer> implements NewsDAO{

	private static final String DEFSORTPROP = "title";
    private static final String DEFSORTNAME = "Заголовок";
	@Autowired
	private HibernateTemplate template;
	
/*	@Transactional
	public void addNews(News g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteNews(News g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteNewsById(Integer id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			News prop = (News) session.get(News.class, id);
			session.delete(prop);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<News> getAllCountries() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(News.class));
		List<News> proplist = new ArrayList<News>();
		for(Object obj:list){
			proplist.add((News)obj);
		}
		return proplist;
	}

	@Transactional
	public News getNewsById(Integer id) {
		return (News) template.get(News.class, id);
	}

	@Transactional
	public void update(News k) {
		template.merge(k);
		
	}*/
	
	@Transactional
	public List<News> getTopNews() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(News.class).add(Restrictions.eq("istop", true)));
		template.setCacheQueries(true);
		return toList(list);
	}
	@Override
	public Set<News> listNewsPageByType(NewsType nwstp, Integer begin, Integer end) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select n from News as n inner join n.type as t where t = :nwstp");
		q.setParameter("nwstp", nwstp);
		q.setFirstResult(begin).setMaxResults(end);
		List<Object> list = q.list();

		return toSet(list);
	}
	@Override
	public Set<News> listCtegoryNewsPageByType(Category cat, NewsType nwstp, Integer begin, Integer end) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select n from News as n inner join n.type as t inner join n.category as c where t = :nwstp and c = :cat");
		q.setParameter("nwstp", nwstp);
		q.setParameter("cat", cat);
		q.setFirstResult(begin).setMaxResults(end);
		List<Object> list = q.list();

		return toSet(list);
	}
	
	

/*	@Transactional
	public Integer getLastId() {
		List<Object> list = template.find("select max(g.id) from News as g"); 
		if(list.get(0)==null){
			return 0;
		}
		return (Integer)list.get(0);
		
	}
	
/*	private List<News> toList(List<Object> list){
		List<News> listgoods = new ArrayList<News>();
		for(Object o:list){
			News h = (News)o;
				listgoods.add(h);
			}
		return listgoods;
	}*/

}
