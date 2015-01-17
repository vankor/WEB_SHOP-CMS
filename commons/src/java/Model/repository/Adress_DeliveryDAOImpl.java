package Model.repository;

import Model.AdressDeliveryId;
import Model.entity.Adress_Delivery;
import org.springframework.stereotype.Repository;

@Repository
public class Adress_DeliveryDAOImpl extends GenericHibTemplateDAOImpl<Adress_Delivery, AdressDeliveryId> implements Adress_DeliveryDAO{

}
