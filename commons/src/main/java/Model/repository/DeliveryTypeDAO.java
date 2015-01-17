package Model.repository;

import Model.others.AdressType;
import Model.entity.DeliveryType;

import java.util.List;

public interface DeliveryTypeDAO extends GenericDAO<DeliveryType, Integer>{
	

	public List<DeliveryType> getByType(AdressType type);

}

