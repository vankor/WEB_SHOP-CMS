package Model.repository;

import Model.TownDeliveryId;
import Model.Town_Delivery;
import org.springframework.stereotype.Repository;

@Repository
public class Town_DeliveryDAOImpl extends GenericHibTemplateDAOImpl<Town_Delivery, TownDeliveryId> implements Town_DeliveryDAO{

}
