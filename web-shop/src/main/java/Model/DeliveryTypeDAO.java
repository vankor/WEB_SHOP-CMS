package Model;

import java.util.List;
import java.util.Set;

public interface DeliveryTypeDAO extends GenericDAO<DeliveryType, Integer>{
	
/*	public void addDeliveryType(DeliveryType g);
	public void deleteDeliveryType(DeliveryType g);
	public void deleteDeliveryTypeById(int id);
	public List<DeliveryType> getAll();
	public DeliveryType getDeliveryTypeById(int id);
	public void update(DeliveryType k);*/
	public List<DeliveryType> getByType(AdressType type);

}

