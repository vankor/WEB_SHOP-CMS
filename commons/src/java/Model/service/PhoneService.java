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
	
/*	public void add(PhoneNumber g){
		phnDAO.addPhoneNumber(g);
	}
	public void delete(PhoneNumber g){
		phnDAO.deletePhoneNumber(g);
	}
	public void deleteById(Integer id){
		phnDAO.deletePhoneNumberById(id);
	}
	public List<PhoneNumber> getAll(){
		return phnDAO.getAllPhoneNumbers();
	}
	public PhoneNumber getById(Integer id){
		return phnDAO.getPhoneNumberById(id);
		
	}
	public void update(PhoneNumber k){
		phnDAO.update(k);
	}*/
	
	
	public PhoneNumber getPhoneNumberByNum(String numb) {
		return phnDAO.getPhoneNumberByNum(numb);
	}
	
	public Set<PhoneNumber> getHotNumbers() {
		return phnDAO.getHotNumbers();
	}
	
	public Set<PhoneNumber> getCostlessLines(){
		return phnDAO.getCostlessLines();
	}
	
/*	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
