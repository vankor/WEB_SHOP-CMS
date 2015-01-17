package Model.repository;

import java.util.List;

import Model.entity.Country;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CountryDAOImpl extends GenericHibTemplateDAOImpl<Country, Integer> implements CountryDAO{

	@Autowired
	private HibernateTemplate template;

	@Transactional
	public Country getCountryByCode(String code) {
		
			List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Country.class).
					add(Restrictions.eq("phonecode", code)));
			
			Country cntr = (!list.isEmpty())?(Country)list.get(0):null;
			return cntr;
	}
	

}
