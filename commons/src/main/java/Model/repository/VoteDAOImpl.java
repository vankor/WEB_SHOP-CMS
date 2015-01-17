package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Vote;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VoteDAOImpl extends GenericHibTemplateDAOImpl<Vote, Integer> implements VoteDAO{

	@Autowired
	private HibernateTemplate template;

	@Transactional
	public void addVote(Vote g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteVote(Vote g) {
		deleteVoteById(g.getId());
		
	}

	@Transactional
	public void deleteVoteById(Integer id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Vote cat = (Vote) session.get(Vote.class, id);
			session.delete(cat);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Vote> getAllVotes() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Vote.class));
		return toVoteList(list);
	}

	@Transactional
	public Vote getVoteById(Integer id) {
		return (Vote) template.get(Vote.class, id);
	}
	
	@Transactional
	public Vote getVoteByName(String name) {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Vote.class)
				.add(Restrictions.eq("text", name)));
		return toVoteList(list).get(0);
		
	}
	
	private List<Vote> toVoteList(List<Object> list){
		List<Vote> listcats = new ArrayList<Vote>();
		for(Object o:list){
				Vote h = (Vote)o;
				listcats.add(h);
				
			}
		return listcats;
	}

	@Transactional
	public void update(Vote g) {
		template.merge(g);
		
	}
	

	

}
