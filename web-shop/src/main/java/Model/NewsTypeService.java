package Model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsTypeService  extends ServiceImpl<NewsType, Integer, NewsTypeDAO>{
	@Autowired
	private NewsTypeDAO nwsDAO;
	
	@Transactional
	public Map<NewsType, Long> getAllNewsTypeNewsCount(){
		return nwsDAO.getAllNewsTypeNewsCount();
		
	}
}
