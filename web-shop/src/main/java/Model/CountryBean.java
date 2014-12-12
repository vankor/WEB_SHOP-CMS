package Model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class CountryBean {
	
	private List<Country> countries = new ArrayList<Country>();

	@Valid
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	

}
