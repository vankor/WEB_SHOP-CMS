package Model.repository;

import java.util.List;

import Model.AdressType;
import Model.entity.DeliveryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

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
