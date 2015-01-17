package Model.repository;

import java.util.List;

import Model.entity.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ConfigurationDAOImpl extends GenericHibTemplateDAOImpl<Configuration, Integer> implements ConfigurationDAO{

	@Transactional
	public void setActiveConfigById(Integer id) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("update Configuration set isactive = false");
		Query q1 = sess.createQuery("update Configuration set isactive = true where id = :id");
		q1.setParameter("id", id);
		
		q.executeUpdate();
		q1.executeUpdate();
		
	}

	@Transactional
	public void setActiveConfig(Configuration conf) {
		setActiveConfigById(conf.getId());
		
	}

	@Transactional
	public Configuration getActiveConfiguration() {
		List list = template.find("select distinct c from Configuration as c where isactive = true");
		return (!list.isEmpty())?(Configuration)list.get(0):null;
	}

}
