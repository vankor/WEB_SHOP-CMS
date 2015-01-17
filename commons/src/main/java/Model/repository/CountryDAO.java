package Model.repository;

import Model.entity.Country;

public interface CountryDAO extends GenericDAO<Country, Integer>{
	

	public Country getCountryByCode(String code);
}
