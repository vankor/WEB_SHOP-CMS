package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.PropSegment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PropSegmentDAOImpl extends GenericHibTemplateDAOImpl<PropSegment, Integer> implements PropSegmentDAO{

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	public void addPropSegment(PropSegment g) {
		template.save(g);
		
	}

	@Transactional
	public void deletePropSegment(PropSegment g) {
		deletePropSegmentById(g.getId());
		
	}

	@Transactional
	public void deletePropSegmentById(int id) {
		Session sess = template.getSessionFactory().openSession();
		Transaction trans = null;
		try {
		trans = sess.beginTransaction();
		PropSegment ps = (PropSegment)sess.get(PropSegment.class, id);
		sess.delete(ps);
		trans.commit();
		}
		catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
		}
		finally{
			sess.close();
		}
		
	}

	@Transactional
	public List<PropSegment> getAllPropSegments() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(PropSegment.class));
		return toPropSegmentList(list);
	}

	@Transactional
	public PropSegment getPropSegmentById(int id) {
		
		return template.get(PropSegment.class, id);
	}

	@Transactional
	public void update(PropSegment k) {
		template.merge(k);
		
	}
	
	private List<PropSegment> toPropSegmentList(List<Object> list){
		List<PropSegment> listPropSegments = new ArrayList<PropSegment>();
		for(Object o:list){
				PropSegment h = (PropSegment)o;
				listPropSegments .add(h);
				
			}
		return listPropSegments ;
	}

}
