package Model;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GoodStatusDAOImpl extends GenericHibTemplateDAOImpl<GoodStatus, Integer> implements GoodStatusDAO {

	@Transactional
	public GoodStatus getGoodStatusByName(String name) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(GoodStatus.class)
				.add(Restrictions.eq("text", name)));
		return toList(list).get(0);
		
	}
	


}
