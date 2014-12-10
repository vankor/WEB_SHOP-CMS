package Model;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PageService  extends ServiceImpl<Page, Integer, PageDAO>{

	@Autowired
	private PageDAO pageDAO;
	
	public Set<Page> getinfopages(){
		return pageDAO.getinfopages();
	}

	@Transactional
	public Set<Page> listInfoPagesPage(Filter goodfilter,
			Integer begin, Integer end){
		return pageDAO.listInfoPagesPage(goodfilter, begin, end);
	}
	
	@Transactional
	public Set<Page> getHeaderPages() {
		return pageDAO.getHeaderPages();
		
	}
	
	@Transactional
	public Page getPageByUrl(String url) {
		return pageDAO.getPageByUrl(url);
		
	}
}
