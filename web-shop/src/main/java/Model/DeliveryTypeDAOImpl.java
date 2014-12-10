package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DeliveryTypeDAOImpl extends GenericHibTemplateDAOImpl<DeliveryType, Integer> implements DeliveryTypeDAO{

	@Autowired
	private HibernateTemplate template;
	
	

	@Override
	public List<DeliveryType> getByType(AdressType type) {
		List list = template.findByNamedParam("select d from DeliveryType as d where d.type = :type", "type", type);
		return toList(list);
	}

}
