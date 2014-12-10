package Model;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PageGroupDAOImpl extends GenericHibTemplateDAOImpl<PageGroup, Integer> implements PageGroupDAO{
	@Override
	@Transactional
	public Set<PageGroup> getFooterPagegroups() {
		List topcats = template.findByCriteria(DetachedCriteria.forClass(PageGroup.class).add(Restrictions.eq("isinfooter", true)));
		return toSet(topcats);
	}
}
