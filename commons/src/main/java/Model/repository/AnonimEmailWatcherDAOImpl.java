package Model.repository;

import java.util.List;

import Model.entity.AnonimEmailWatcher;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnonimEmailWatcherDAOImpl extends GenericHibTemplateDAOImpl<AnonimEmailWatcher, Integer> implements AnonimEmailWatcherDAO{

	@Autowired
	private HibernateTemplate template;
	

	@Transactional
	public AnonimEmailWatcher getEmail(String str) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(AnonimEmailWatcher.class).add(Restrictions.eq("email", str)));
		if(list.size()==1){
			return (AnonimEmailWatcher)list.get(0);
		}
		return null;
		
	}



}
