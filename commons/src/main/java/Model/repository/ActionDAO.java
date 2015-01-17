package Model.repository;

import java.util.List;
import java.util.Set;

import Model.entity.Action;
import Model.entity.Category;
import Model.repository.GenericDAO;

public interface ActionDAO extends GenericDAO<Action, Integer> {
	List<Action> getTopActions();
	List<Action> listPage(Action.OrderingRules rule, Integer begin, Integer end);
	Set<Action> listCategoryActions(Category cat);

}