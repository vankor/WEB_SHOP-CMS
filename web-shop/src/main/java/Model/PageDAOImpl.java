package Model;



import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PageDAOImpl extends GenericHibTemplateDAOImpl<Page, Integer> implements PageDAO{

	@Override
	@Transactional
	public Set<Page> getinfopages() {
		List<Object> list = template.find("select p from Page as p where p.isinfopage = true");
		return toSet(list);
	}

	@Override
	@Transactional
	public Set<Page> listInfoPagesPage(Filter goodfilter, 	Integer begin, Integer end) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select p from Page as p where p.isinfopage = true and p IN (:vals)").setFirstResult(begin).setMaxResults(end);
		q.setParameterList("vals", listPage(goodfilter, 0, getCount()));
		List list = q.list();
		return  toSet(list);
	}

	@Override
	@Transactional
	public Set<Page> getHeaderPages() {
		List topcats = template.findByCriteria(DetachedCriteria.forClass(Page.class).add(Restrictions.eq("isinheader", true)));
		return toSet(topcats);
	}
	
	@Override
	@Transactional
	public Page getPageByUrl(String url) {
		List<Object> list = template.findByNamedParam("select p from Page as p where p.fullurl like :url","url",url);
		return (!list.isEmpty())?(Page)list.get(0):null;
	}

}
