package Model;

import java.util.List;
import java.util.Set;

public interface PhoneNumberDAO extends GenericDAO<PhoneNumber, Integer>{
	
/*	public void addPhoneNumber(PhoneNumber g);
	public void deletePhoneNumber(PhoneNumber g);
	public void deletePhoneNumberById(int id);
	public List<PhoneNumber> getAllPhoneNumbers();
	public PhoneNumber getPhoneNumberById(int id);*/
	public PhoneNumber getPhoneNumberByNum(String id);
	public Set<PhoneNumber> getHotNumbers();
	/*public void update(PhoneNumber k);*/
	public Set<PhoneNumber> getCostlessLines();
	

}

