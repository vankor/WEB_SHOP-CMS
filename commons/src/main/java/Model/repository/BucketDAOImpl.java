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
	

	

	








}
