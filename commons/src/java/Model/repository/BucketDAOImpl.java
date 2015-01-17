package Model.repository;

import Model.entity.Bucket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
;


@Repository
public class BucketDAOImpl extends GenericHibTemplateDAOImpl<Bucket, Integer> implements BucketDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
/*	@Transactional
	public void addBucket(Bucket g) {
		Session s = null;
		s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(g);
	}

	@Transactional
	public void deleteBucket(Bucket g) {
			Session s = null;
			s = sessionFactory.getCurrentSession();
			s.delete(g);
	}

	@Transactional
	public void deleteBucketById(int id) {
		Session s = null;
		Query q ;
		s = sessionFactory.getCurrentSession();
		q = s.createQuery("delete from buckets where id like: id");
		q.setString("id", ""+id);
	}

	@Transactional
	public List<Object> getAllBuckets() {
		Session s = null;
		ArrayList<Object> list = new ArrayList<Object>();
		s = sessionFactory.getCurrentSession();
		list = (ArrayList<Object>) s.createCriteria(Bucket.class).list();
		return list;
	}

	@Transactional
	public Bucket getBucketById(int id) {
		Session s = null;
		Bucket p = null;
		s = sessionFactory.getCurrentSession();
		p = (Bucket)s.load(Bucket.class, id);
		return p;
	}*/
	

	
//	@Transactional
//	public List<Object> getFiltered(Filter n) {
		// TODO Auto-generated method stub
//		return null;
//	}

/*	@Transactional
	public void update(Bucket k) {
//		Session s = null;
//		s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		s.merge(k);
//		s.getTransaction().commit();
				
	}

	@Transactional
	public Bucket getBuckById(int id) {
		Session s = null;
		Bucket buck;
		s = sessionFactory.getCurrentSession();
		buck = (Bucket) s.load(Bucket.class, id);
		return buck;
	}
		
	*/

}
