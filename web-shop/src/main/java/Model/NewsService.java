package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsService extends ServiceImpl<News, Integer, NewsDAO>{
	@Autowired
	private NewsDAO propDAO;
	
	@Autowired
	private TextPartDAO txtpartDAO;
	
	@Override
	@Transactional
	public void update(News entity) {
		if(entity.getTextpart()!=null && entity.getTextpart().getId()!=null)
		txtpartDAO.update(entity.getTextpart());
		
		if(entity.getTextpart()!=null && entity.getTextpart().getId()==null)
			entity.getTextpart().setId(txtpartDAO.save(entity.getTextpart()));
		propDAO.update(entity);
	}

	@Autowired
	private ImageDAO imgDAO;
	
	@Autowired
	private VideoDAO vidDAO;
	
	@Autowired
	private TextPartDAO txtDAO;
	
	
	@Transactional
	public void add(News g) {
		for(Image img: g.getImages()){
			imgDAO.add(img);
		}
		for(Video vid: g.getVideos()){
			vidDAO.add(vid);
		}
		
		propDAO.add(g);
		
	}

/*	@Transactional
	public List<News> listPage(Filter n, Integer begin,
			Integer end) {
		return propDAO.listPage(n, begin, end);
	}*/
	
/*	@Transactional
	public void delete(News g) {
		propDAO.deleteNews(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		propDAO.deleteNewsById(id);
		
	}

	@Transactional
	public List<News> getAll() {
		return propDAO.getAllCountries();
	}

	@Transactional
	public News getById(Integer id) {
		return propDAO.getNewsById(id);
	}

	@Transactional
	public void update(News k) {
//		for(Image img: k.getImages()){
	//		imgDAO.update(img);
//		}
//		
//		for(Video vid: k.getVideos()){
//			vidDAO.update(vid);
///		}
		
//		for(TextPart txt: k.getTextparts()){
//			txtDAO.update(txt);
//		}
		
		propDAO.update(k);
		
	}
	
	@Transactional
	public Integer getLastId(){
		return propDAO.getLastId();
	}*/
	
	@Transactional
	public List<News> getTopNews() {
		return propDAO.getTopNews();
	}

	public Set<News> listNewsPageByType(NewsType nwstp, Integer begin,
			Integer end) {
		// TODO Auto-generated method stub
		return propDAO.listNewsPageByType(nwstp, begin, end);
	}

	public Set<News> listCtegoryNewsPageByType(Category cat, NewsType nwstp, Integer begin, Integer end) {
		// TODO Auto-generated method stub
		return propDAO.listCtegoryNewsPageByType(cat, nwstp, begin, end);
	}
	
	


	

}

