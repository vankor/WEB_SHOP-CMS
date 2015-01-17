package Model.repository;

import Model.AdressType;
import Model.entity.Adress;
import Model.entity.Town;

import java.util.Set;


public interface AdressDAO extends GenericDAO<Adress, Integer>{
/*	public void addAdress(Adress g);
	public void deleteAdress(Adress g);
	public void deleteAdressById(Integer id);
	public List<Adress> getAllAdresses();
	public Adress getAdressById(Integer id);*/
	
	public Set<Adress> getAdressByType(AdressType f);
	public Adress getAdressByParams(Town town, String street_type, String street_name, String house_num, String room_num);
	
/*	public void update(Adress k);*/
}
