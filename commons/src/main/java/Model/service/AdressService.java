package Model.service;
import java.util.Set;

import Model.repository.AdressDAO;
import Model.others.AdressType;
import Model.entity.Adress;
import Model.entity.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdressService extends ServiceImpl<Adress, Integer, AdressDAO> {
	@Autowired
	private AdressDAO adrDAO;
	

	@Transactional
	public Set<Adress> getAdressByType(AdressType f) {
		return adrDAO.getAdressByType(f);
	}
	
	@Transactional
	public Adress getAdressByParams(Town town, String street_type,
									String street_name, String house_num, String room_num) {
				return adrDAO.getAdressByParams(town, street_type, street_name, house_num, room_num);
		
	}



}

