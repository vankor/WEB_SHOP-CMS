package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
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
	
/*	@Transactional
	public void addComment(Comment g) {
		Session s = null;
		s = sessionFactory.getCurrentSession();
		s.save(g);
	}

	@Transactional
	public void deleteComment(Comment g) {
		Session s = null;
		s = sessionFactory.getCurrentSession();
		s.delete(g);
	}

	@Transactional
	public void deleteCommentById(int id) {
		Session s = null;
		Comment com;
		Query q ;
		s = sessionFactory.getCurrentSession();
		com = (Comment) s.load(Comment.class, id);
		s.delete(com);
	}

	@Transactional
	public List<Object> getAllComments() {
		Session s = null;
		List list = new ArrayList();
		s = sessionFactory.getCurrentSession();
		list = s.createCriteria(Comment.class).list();
		return list;
	}

	@Transactional
	public Comment getCommentById(int id) {
		Session s = null;
		Comment com;
		s = sessionFactory.getCurrentSession();
		com = (Comment) s.load(Comment.class, id);
		return com;
	}*/

/*	
//	@Override
//	public List<Object> getFiltered(Filter n) {
		// TODO Auto-generated method stub
//		return null;
//	}

	@Transactional
	public void update(Comment k) {
		Session s = null;
		s = sessionFactory.getCurrentSession();
		s.beginTransaction();
		s.merge(k);
		s.getTransaction().commit();
	}
*/
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
		// TODO Auto-generated method stub
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
