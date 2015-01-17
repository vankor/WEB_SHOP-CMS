package Model.repository;

import Model.entity.NewsType;

import java.util.Map;

public interface NewsTypeDAO extends GenericDAO<NewsType, Integer>{
	public Map<NewsType, Long> getAllNewsTypeNewsCount();
}
