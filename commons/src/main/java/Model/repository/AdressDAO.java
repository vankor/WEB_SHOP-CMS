package Model.repository;

import Model.others.AdressType;
import Model.entity.Adress;
import Model.entity.Town;

import java.util.Set;


public interface AdressDAO extends GenericDAO<Adress, Integer> {

	
	public Set<Adress> getAdressByType(AdressType f);
	public Adress getAdressByParams(Town town, String street_type, String street_name, String house_num, String room_num);
	

}
