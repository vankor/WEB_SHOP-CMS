package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryTypeService extends ServiceImpl<DeliveryType, Integer, DeliveryTypeDAO>{
	@Autowired
	private DeliveryTypeDAO propDAO;
	
	public List<DeliveryType> getByType(AdressType type) {
		return propDAO.getByType(type);
	}

}

