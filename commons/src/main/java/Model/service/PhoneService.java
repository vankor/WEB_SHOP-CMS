package Model.service;

import java.util.Set;

import Model.entity.PhoneNumber;
import Model.repository.PhoneNumberDAO;
import Model.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends ServiceImpl<PhoneNumber, Integer, PhoneNumberDAO> {
	
	@Autowired
	private PhoneNumberDAO phnDAO;

	public PhoneNumber getPhoneNumberByNum(String numb) {
		return phnDAO.getPhoneNumberByNum(numb);
	}
	
	public Set<PhoneNumber> getHotNumbers() {
		return phnDAO.getHotNumbers();
	}
	
	public Set<PhoneNumber> getCostlessLines(){
		return phnDAO.getCostlessLines();
	}


}
