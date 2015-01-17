package Model.repository;

import java.util.List;
import java.util.Set;

import Model.entity.Action;
import Model.entity.Category;

public interface ActionDAO extends GenericDAO<Action, Integer>{
	
/*	public void addAction(Action g);
	public void deleteAction(Action g);
	public void deleteActionById(int id);
	public List<Action> getAllActions();
	public Action getActionById(int id);
	public void update(Action k);
	public Integer getLastId();*/
	public List<Action> getTopActions();
	public List<Action> listPage(Action.OrderingRules rule, Integer begin, Integer end);
	public Set<Action> listCategoryActions(Category cat);

}