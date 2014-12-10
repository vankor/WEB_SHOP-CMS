package Model;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService extends ServiceImpl<Property, Integer, PropertyDAO>{
	@Autowired
	private PropertyDAO propDAO;
	
/*	@Transactional
	public void add(Property g) {
		propDAO.addProperty(g);
		
	}

	@Transactional
	public void delete(Property g) {
		propDAO.deleteProperty(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deletePropertyById(id);
		
	}

	@Transactional
	public List<Property> getAll() {
		return propDAO.getAllProperties();
	}

	@Transactional
	public Property getById(Integer id) {
		return propDAO.getPropertyById(id);
	}

	@Transactional
	public void update(Property k) {
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