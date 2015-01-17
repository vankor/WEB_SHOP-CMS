package Model.repository;

import java.util.List;
import java.util.Set;

import Model.entity.PhoneNumber;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneNumberDAOImpl extends GenericHibTemplateDAOImpl<PhoneNumber, Integer> implements PhoneNumberDAO{

	@Autowired
	private HibernateTemplate template;
	

	@Override
	public PhoneNumber getPhoneNumberByNum(String numb) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(PhoneNumber.class).add(Restrictions.eq("numb", numb)));
		PhoneNumber phn = null;
		if(!list.isEmpty()){
			phn = (PhoneNumber) list.get(0);
		}
		return phn;
	}


	@Override
	public Set<PhoneNumber> getHotNumbers() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(PhoneNumber.class).add(Restrictions.eq("isMainCommonNumber", true)));
		template.setCacheQueries(true);
		return toSet(list);
	}
	
	@Override
	public Set<PhoneNumber> getCostlessLines() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(PhoneNumber.class).add(Restrictions.eq("isCostlessLine", true)));
		template.setCacheQueries(true);
		return toSet(list);
	}
	
	

}
