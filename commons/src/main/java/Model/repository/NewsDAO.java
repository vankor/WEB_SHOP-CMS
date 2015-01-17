package Model.repository;

import java.util.List;
import java.util.Set;

import Model.entity.Category;
import Model.entity.News;
import Model.entity.NewsType;

public interface NewsDAO extends GenericDAO<News, Integer>{
	

	public List<News> getTopNews();


	Set<News> listNewsPageByType(NewsType nwstp, Integer begin, Integer end);
	public Set<News> listCtegoryNewsPageByType(Category cat, NewsType nwstp, Integer begin, Integer end);

}
