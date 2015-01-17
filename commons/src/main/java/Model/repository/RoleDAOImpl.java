package Model.repository;

import Model.entity.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDAOImpl extends GenericHibTemplateDAOImpl<Role, String> implements RoleDAO{

	@Autowired
	private HibernateTemplate template;



	@Transactional
	public void deleteRoleById(String id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Role cat = (Role) session.get(Role.class, id);
			session.delete(cat);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}



	@Transactional
	public Role getRoleById(String id) {
		return (Role) template.get(Role.class, id);
	}
	

	

}
