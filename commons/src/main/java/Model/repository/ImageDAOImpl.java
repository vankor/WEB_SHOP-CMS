package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Image;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ImageDAOImpl extends GenericHibTemplateDAOImpl<Image, Integer> implements ImageDAO {

	@Autowired 
	private HibernateTemplate template;
	
	@Transactional
	public void addImage(Image g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteImage(Image g) {

		
	}

	@Transactional
	public void deleteImageById(int id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Image img = (Image)session.get(Image.class, id);
			session.delete(img);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Image> getAllImages() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Image.class));
		List<Image> listimage = new ArrayList<Image>();
		for(Object obj:list){
			listimage.add((Image)obj);
		}
		return listimage;
		
	}

	@Transactional
	public Image getImageById(int id) {
		Image p = (Image) template.get(Image.class, id);
		return p;
	}

	@Transactional
	public void update(Image k) {
		template.merge(k);
		
	}

	@Transactional
	public Integer getLastId() {
		List<Object> list = template.find("select max(g.id) from Image as g"); 
		if(list.get(0)==null){list.set(0,0);}
		return (Integer)list.get(0);
		
	}

}
