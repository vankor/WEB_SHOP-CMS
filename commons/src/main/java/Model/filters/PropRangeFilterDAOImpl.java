package Model.filters;

import org.springframework.stereotype.Repository;

import Model.repository.GenericHibTemplateDAOImpl;
@Repository
public class PropRangeFilterDAOImpl extends GenericHibTemplateDAOImpl<PropRangeFilter, Integer> implements PropRangeFilterDAO{

}
