package Model.service;

import Model.RegionDAO;
import Model.ServiceImpl;
import Model.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService extends ServiceImpl<Region, Integer, RegionDAO> {
	@Autowired
	private RegionDAO propDAO;
	
/*	@Transactional
	public void add(Region g) {
		propDAO.addRegion(g);
		
	}

	@Transactional
	public void delete(Region g) {
		propDAO.deleteRegion(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deleteRegionById(id);
		
	}

	@Transactional
	public List<Region> getAll() {
		return propDAO.getAllCountries();
	}

	@Transactional
	public Region getById(Integer id) {
		return propDAO.getRegionById(id);
	}

	@Transactional
	public void update(Region k) {
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


