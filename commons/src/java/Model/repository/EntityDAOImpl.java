package Model.repository;

import Model.entity.Entity;
import org.springframework.stereotype.Repository;

@Repository
public class EntityDAOImpl extends GenericHibTemplateDAOImpl<Entity, Integer> implements EntityDAO{

}
