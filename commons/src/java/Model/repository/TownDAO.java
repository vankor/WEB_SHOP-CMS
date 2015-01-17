package Model.repository;

import Model.entity.Town;

import java.util.Set;

public interface TownDAO extends GenericDAO<Town, Integer>{
	
/*	public void addTown(Town g);
	public void deleteTown(Town g);
	public void deleteTownById(Integer id);
	public Set<Town> getAllTowns();
	public Town getTownById(Integer id);
	public void update(Town g);*/
	public Set<Town> getTownsByName(String name);
	public Town getTownsByCode(Integer code);
	public Set<Town> getResidentTowns();	
	public Integer save(Town entity);
	public void setDefault(Town town);
	public Town getDefault();

}
