package Model.repository;

import Model.entity.PhoneNumber;

import java.util.Set;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber, Integer>{
	

	public PhoneNumber getPhoneNumberByNum(String id);
	public Set<PhoneNumber> getHotNumbers();

	public Set<PhoneNumber> getCostlessLines();
	

}

