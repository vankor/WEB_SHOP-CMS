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
	
	public Country getCountryByCode(String code){
		return propDAO.getCountryByCode(code);
	}


	
	

}

