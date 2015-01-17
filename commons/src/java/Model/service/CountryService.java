package Model.service;

import Model.CountryDAO;
import Model.ServiceImpl;
import Model.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends ServiceImpl<Country, Integer, CountryDAO> {
	@Autowired
	private CountryDAO propDAO;
	
	public Country getCountryByCode(String code){
		return propDAO.getCountryByCode(code);
	}


	
	

}

