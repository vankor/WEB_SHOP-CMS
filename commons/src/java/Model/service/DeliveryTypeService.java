package Model.service;
import java.util.List;

import Model.AdressType;
import Model.DeliveryTypeDAO;
import Model.ServiceImpl;
import Model.entity.DeliveryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTypeService extends ServiceImpl<DeliveryType, Integer, DeliveryTypeDAO> {
	@Autowired
	private DeliveryTypeDAO propDAO;
	
	public List<DeliveryType> getByType(AdressType type) {
		return propDAO.getByType(type);
	}

}

