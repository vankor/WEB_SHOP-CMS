package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Value;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ValueDAOImpl extends GenericHibTemplateDAOImpl<Value, Integer> implements ValueDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	public void addValue(Value g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteValue(Value g) {
		template.delete(g);
		
	}

	@Transactional
	public void deleteValueById(int id) {
		template.delete(template.get(Value.class, id));
		
	}

	@Transactional
	public List<Value> getAllProperties() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Value.class));
		List<Value> proplist = new ArrayList<Value>();
		for(Object obj:list){
			proplist.add((Value)obj);
		}
		return proplist;
	}

	@Transactional
	public Value getValueById(int id) {
		return (Value) template.get(Value.class, id);
	}

	@Transactional
	public void update(Value k) {
		template.merge(k);
		
	}

}