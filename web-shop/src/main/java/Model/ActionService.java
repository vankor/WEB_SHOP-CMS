package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Model.Action.OrderingRules;

@Service
public class ActionService extends ServiceImpl<Action, Integer, ActionDAO> {
	@Autowired
	private ActionDAO propDAO;
	

	@Transactional
	public List<Action> getTopActions() {
		return propDAO.getTopActions();
	}

	@Transactional
	public Action getById(Integer id) {
		return propDAO.getById(id, false);
	}


	
	@Transactional
	public Set<Action> listCategoryActions(Category cat) {
		return propDAO.listCategoryActions(cat);
	}
	

}

