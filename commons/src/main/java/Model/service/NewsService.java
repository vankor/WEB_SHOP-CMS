package Model.service;
import java.util.List;
import java.util.Set;

import Model.entity.*;
import Model.repository.ImageDAO;
import Model.repository.NewsDAO;
import Model.repository.TextPartDAO;
import Model.repository.VideoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsService extends ServiceImpl<News, Integer, NewsDAO> {
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


	

	
	@Transactional
	public List<News> getTopNews() {
		return propDAO.getTopNews();
	}

	public Set<News> listNewsPageByType(NewsType nwstp, Integer begin,
			Integer end) {

		return propDAO.listNewsPageByType(nwstp, begin, end);
	}

	public Set<News> listCtegoryNewsPageByType(Category cat, NewsType nwstp, Integer begin, Integer end) {

		return propDAO.listCtegoryNewsPageByType(cat, nwstp, begin, end);
	}
	
	


	

}

