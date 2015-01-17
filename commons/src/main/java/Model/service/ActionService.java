package Model.service;
import java.util.List;
import java.util.Set;

import Model.repository.ActionDAO;
import Model.entity.Action;
import Model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

