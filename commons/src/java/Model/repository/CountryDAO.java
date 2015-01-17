package Model.repository;

import Model.entity.Country;

public interface CountryDAO extends GenericDAO<Country, Integer>{
	
/*	public void addCountry(Country g);
	public void deleteCountry(Country g);
	public void deleteCountryById(int id);
	public List<Country> getAllCountries();
	public Country getCountryById(int id);
	public void update(Country k);
	*/
	public Country getCountryByCode(String code);
}
