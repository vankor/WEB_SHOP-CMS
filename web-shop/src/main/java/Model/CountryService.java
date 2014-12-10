package Model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService extends ServiceImpl<Country, Integer, CountryDAO>{
	@Autowired
	private CountryDAO propDAO;
	
/*	@Transactional
	public void add(Country g) {
		propDAO.add(g);
		
	}

	@Transactional
	public void delete(Country g) {
		propDAO.delete(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deleteById(id);
		
	}

	@Transactional
	public List<Country> getAll() {
		return propDAO.getAll();
	}

	@Transactional
	public Country getById(Integer id) {
		return propDAO.getById(id, false);
	}

	@Transactional
	public void update(Country k) {
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

