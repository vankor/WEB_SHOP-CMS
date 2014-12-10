package Model;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValueService extends ServiceImpl<Value, Integer, ValueDAO>{
	@Autowired
	private ValueDAO propDAO;
	
/*	@Transactional
	public void add(Value g) {
		propDAO.addValue(g);
		
	}

	@Transactional
	public void delete(Value g) {
		propDAO.deleteValue(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deleteValueById(id);
		
	}

	@Transactional
	public List<Value> getAll() {
		return propDAO.getAllProperties();
	}

	@Transactional
	public Value getById(Integer id) {
		return propDAO.getValueById(id);
	}

	@Transactional
	public void update(Value k) {
		propDAO.update(k);
		
	}

	

	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}
	
*/
}
