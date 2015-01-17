package Model.repository;

import java.util.List;

import Model.entity.GoodState;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GoodStateDAOImpl extends GenericHibTemplateDAOImpl<GoodState, Integer> implements GoodStateDAO {

	@Autowired
	private HibernateTemplate template;


	@Transactional
	public GoodState getGoodStateByName(String name) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(GoodState.class)
				.add(Restrictions.eq("text", name)));
		return toList(list).get(0);
		
	}
	


}
