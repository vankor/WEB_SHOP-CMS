package Model;

import java.util.Map;

public interface NewsTypeDAO extends GenericDAO<NewsType, Integer>{
	public Map<NewsType, Long> getAllNewsTypeNewsCount();
}
