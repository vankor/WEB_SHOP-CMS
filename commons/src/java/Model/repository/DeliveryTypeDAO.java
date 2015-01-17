package Model.repository;

import Model.AdressType;
import Model.entity.DeliveryType;

import java.util.List;

public interface DeliveryTypeDAO extends GenericDAO<DeliveryType, Integer>{
	
/*	public void addDeliveryType(DeliveryType g);
	public void deleteDeliveryType(DeliveryType g);
	public void deleteDeliveryTypeById(int id);
	public List<DeliveryType> getAll();
	public DeliveryType getDeliveryTypeById(int id);
	public void update(DeliveryType k);*/
	public List<DeliveryType> getByType(AdressType type);

}

