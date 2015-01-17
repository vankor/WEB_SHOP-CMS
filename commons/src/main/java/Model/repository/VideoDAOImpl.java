package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.Video;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VideoDAOImpl extends GenericHibTemplateDAOImpl<Video, Integer> implements VideoDAO {

	@Autowired 
	private HibernateTemplate template;
	
	@Transactional
	public void addVideo(Video g) {
		template.save(g);
		
	}

	@Transactional
	public void deleteVideo(Video g) {

		
	}

	@Transactional
	public void deleteVideoById(Integer id) {
		Session session = template.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Video img = (Video)session.get(Video.class, id);
			session.delete(img);
			transaction.commit();
		} catch (Exception exception) {
		     if (transaction != null) transaction.rollback();
		} finally {
			session.close();
		}
		
	}

	@Transactional
	public List<Video> getAllVideos() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Video.class));
		List<Video> listVideo = new ArrayList<Video>();
		for(Object obj:list){
			listVideo.add((Video)obj);
		}
		return listVideo;
		
	}

	@Transactional
	public Video getVideoById(Integer id) {
		Video p = (Video) template.get(Video.class, id);
		return p;
	}

	@Transactional
	public void update(Video k) {
		template.merge(k);
		
	}
	
	@Transactional
	public Integer getLastId() {
		List<Object> list = template.find("select max(g.id) from Video as g"); 
		if(list.get(0)==null){list.set(0,0);}
		return (Integer)list.get(0);
		
	}


}
