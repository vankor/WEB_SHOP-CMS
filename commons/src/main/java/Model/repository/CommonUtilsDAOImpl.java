package Model.repository;

import java.lang.reflect.Field;
import java.util.List;

import org.aspectj.lang.reflect.NoSuchAdviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommonUtilsDAOImpl{
	@Autowired
	private HibernateTemplate template;
	
	
	

}
