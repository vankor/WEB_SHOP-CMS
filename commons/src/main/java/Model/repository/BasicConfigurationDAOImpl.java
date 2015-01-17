package Model.repository;

import Model.entity.BasicConfiguration;
import org.springframework.stereotype.Repository;

@Repository
public class BasicConfigurationDAOImpl extends GenericHibTemplateDAOImpl<BasicConfiguration, Integer> implements BasicConfigurationDAO {

}
