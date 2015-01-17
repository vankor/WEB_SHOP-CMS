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
	
/*	@Transactional
	public void addEmail(AnonimEmailWatcher g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteEmail(AnonimEmailWatcher g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteEmailById(int id) {
		template.delete(template.get(AnonimEmailWatcher.class, id));
		
	}

	@Transactional
	public List<AnonimEmailWatcher> getAllEmails() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(AnonimEmailWatcher.class));
		List<AnonimEmailWatcher> proplist = new ArrayList<AnonimEmailWatcher>();
		for(Object obj:list){
			proplist.add((AnonimEmailWatcher)obj);
		}
		return proplist;
	}

	@Transactional
	public AnonimEmailWatcher getEmailById(int id) {
		return (AnonimEmailWatcher) template.get(AnonimEmailWatcher.class, id);
	}

	@Transactional
	public void update(AnonimEmailWatcher k) {
		template.merge(k);
		
	}
*/
	@Transactional
	public AnonimEmailWatcher getEmail(String str) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(AnonimEmailWatcher.class).add(Restrictions.eq("email", str)));
		if(list.size()==1){
			return (AnonimEmailWatcher)list.get(0);
		}
		return null;
		
	}



}
