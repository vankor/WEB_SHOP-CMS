package Model.filters;

import org.springframework.stereotype.Repository;

import Model.repository.GenericHibTemplateDAOImpl;
@Repository
public class FieldPriorityDAOImpl extends GenericHibTemplateDAOImpl<FieldPriority, Integer> implements FieldPriorityDAO{

}
