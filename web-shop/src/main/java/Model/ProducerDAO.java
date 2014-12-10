package Model;

import java.util.Map;
import java.util.Set;

public interface ProducerDAO extends GenericDAO<Producer, Integer>{
	public Map<Producer, Long> getCategoryProducersWithCount(Category cat);
}
