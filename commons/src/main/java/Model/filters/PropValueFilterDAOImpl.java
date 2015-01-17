package Model.filters;

import org.springframework.stereotype.Repository;

import Model.repository.GenericHibTemplateDAOImpl;
@Repository
public class PropValueFilterDAOImpl extends GenericHibTemplateDAOImpl<PropValueFilter, Integer> implements PropValueFilterDAO{

}
