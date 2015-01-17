package Model.repository;

import Model.entity.Guarantie;
import org.springframework.stereotype.Repository;

@Repository
public class GuarantieDAOImpl extends GenericHibTemplateDAOImpl<Guarantie, Integer> implements GuarantieDAO{

}
