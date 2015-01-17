package Model.service;
import java.util.Set;

import Model.AdressDAO;
import Model.AdressType;
import Model.ServiceImpl;
import Model.entity.Adress;
import Model.entity.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdressService extends ServiceImpl<Adress, Integer, AdressDAO> {
	@Autowired
	private AdressDAO adrDAO;
	
/*	@Transactional
	public void add(Adress g) {
		adrDAO.add(g);
		
	}

	@Transactional
	public void delete(Adress g) {
		adrDAO.delete(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		adrDAO.deleteById(id);
		
	}

	@Transactional
	public List<Adress> getAll() {
		return adrDAO.getAll();
	}

	@Transactional
	public Adress getById(Integer id) {
		return adrDAO.getById(id, false);
	}

	@Transactional
	public void update(Adress k) {
		adrDAO.update(k);
		
	}*/
	@Transactional
	public Set<Adress> getAdressByType(AdressType f) {
		return adrDAO.getAdressByType(f);
	}
	
	@Transactional
	public Adress getAdressByParams(Town town, String street_type,
									String street_name, String house_num, String room_num) {
				return adrDAO.getAdressByParams(town, street_type, street_name, house_num, room_num);
		
	}

	/*

	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}*/

}

