package Model.repository;

import Model.entity.OrderRow;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRowDAOImpl extends GenericHibTemplateDAOImpl<OrderRow, Integer> implements OrderRowDAO {



}
