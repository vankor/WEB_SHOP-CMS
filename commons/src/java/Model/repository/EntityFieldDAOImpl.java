package Model.repository;

import java.util.List;

import Model.entity.EntityField;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EntityFieldDAOImpl extends GenericHibTemplateDAOImpl<EntityField, Integer> implements EntityFieldDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Override
	public List<EntityField> listByClassname(String classname) {
//		List<Object> list = template.find("select g from EntityField as g where g.classname like :classname","classname",classname);
/*		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select g from EntityField as g where g.classname = '?'");
		q.setParameter(1, classname);*/
		
		
		DetachedCriteria crit = DetachedCriteria.forClass(EntityField.class);
		
		crit.add(Restrictions.like("classname", classname));
		List<Object> list = template.findByCriteria(crit);
		
		return toList(list);
	}

	@Override
	public EntityField getByClassAndField(String classname, String fieldname) {
		DetachedCriteria crit = DetachedCriteria.forClass(EntityField.class);
		crit.add(Restrictions.conjunction()
			       .add(Restrictions.like("classname", classname))
			       .add(Restrictions.like("propname", fieldname)));
		List<Object> list = template.findByCriteria(crit);
		return (EntityField)list.get(0);
	}

}
