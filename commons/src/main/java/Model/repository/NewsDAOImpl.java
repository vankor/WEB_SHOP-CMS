package Model.repository;


import java.util.List;
import java.util.Set;

import Model.entity.Category;
import Model.entity.News;
import Model.entity.NewsType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NewsDAOImpl extends GenericHibTemplateDAOImpl<News, Integer> implements NewsDAO{

	private static final String DEFSORTPROP = "title";
    private static final String DEFSORTNAME = "Заголовок";
	@Autowired
	private HibernateTemplate template;
	

	
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
	
	



}
