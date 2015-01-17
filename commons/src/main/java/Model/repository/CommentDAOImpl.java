package Model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Model.entity.Category;
import Model.entity.Comment;
import Model.entity.GoodItem;
import Model.repository.CommentDAO;
import Model.repository.GenericHibTemplateDAOImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CommentDAOImpl extends GenericHibTemplateDAOImpl<Comment, Integer> implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HibernateTemplate template;

	@Transactional
	public Set<Comment> getAnswers(Comment com, Boolean onlyproved) {
		return getAnswersById(com.getId(), onlyproved);
	}

	@Transactional
	public Set<Comment> getAnswersById(Integer id, Boolean onlyproved) {
		Comment comm = getById(id, false);
		List<Object> list = new ArrayList();
		if(onlyproved){
			list = template.findByCriteria(DetachedCriteria.forClass(Comment.class).
					add(Restrictions.conjunction().add(Restrictions.eq("parent_comment", comm)).add(Restrictions.eq("isproved", true))));
		}
		else{
			list = template.findByCriteria(DetachedCriteria.forClass(Comment.class).
					add(Restrictions.eq("parent_comment", comm)));
		}
		return toSet(list);
	}

	@Transactional
	public Integer save(Comment entity) {

		return (Integer) template.save(entity);
	}
	
	public Set<Comment> getProvedComments() {
	List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
		add(Restrictions.eq("isproved", true)));
	return toSet(list);
		
	}

	@Transactional
	public Set<Comment> getProvedCommentsByGood(GoodItem good) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
				add(Restrictions.conjunction().add(Restrictions.eq("good", good)).add(Restrictions.eq("isproved", true))));
			return toSet(list);
	}

}
