package Model;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

public interface NewsDAO extends GenericDAO<News, Integer>{
	
/*	public void addNews(News g);
	public void deleteNews(News g);
	public void deleteNewsById(Integer id);
	public List<News> getAllCountries();
	public News getNewsById(Integer id);
	public void update(News k);
	public Integer getLastId();*/
	public List<News> getTopNews();
/*	public List<News> listPage(Filter n,Integer begin, Integer end);
	public List<News> filter(Filter n, Integer firstResult, Integer maxResult);*/

	Set<News> listNewsPageByType(NewsType nwstp, Integer begin, Integer end);
	public Set<News> listCtegoryNewsPageByType(Category cat, NewsType nwstp, Integer begin, Integer end);

}
