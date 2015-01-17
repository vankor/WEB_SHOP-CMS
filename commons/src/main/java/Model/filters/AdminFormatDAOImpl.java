package Model.filters;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import Model.repository.GenericHibTemplateDAOImpl;

@Repository
public class AdminFormatDAOImpl extends GenericHibTemplateDAOImpl<AdminFormat, Integer> implements AdminFormatDAO{

	@Override
	public List<AdminFormat> getClassFormats(String adminclasssname) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(AdminFormat.class).add(Restrictions.like("classname", adminclasssname)));
		
		return toList(list);
	}

}
