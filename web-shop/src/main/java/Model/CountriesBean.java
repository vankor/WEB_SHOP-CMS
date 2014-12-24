package Model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class CountriesBean {
	
	private List<CountryBean> countries = new ArrayList<CountryBean>();

	@Valid
	public List<CountryBean> getCountries() {
		return countries;
	}

	public void setCountries(List<CountryBean> countries) {
		this.countries = countries;
	}
	
	
	

}
