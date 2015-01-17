package Model.service;

import Model.PayTypeDAO;
import Model.ServiceImpl;
import Model.entity.PayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTypeService extends ServiceImpl<PayType, Integer, PayTypeDAO> {
	@Autowired
	private PayTypeDAO propDAO;
	
/*	@Transactional
	public void add(PayType g) {
		propDAO.addPayType(g);
		
	}

	@Transactional
	public void delete(PayType g) {
		propDAO.deletePayType(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deletePayTypeById(id);
		
	}

	@Transactional
	public List<PayType> getAll() {
		return propDAO.getAllProperties();
	}

	@Transactional
	public PayType getById(Integer id) {
		return propDAO.getPayTypeById(id);
	}

	@Transactional
	public void update(PayType k) {
		propDAO.update(k);
		
	}

	

	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}
