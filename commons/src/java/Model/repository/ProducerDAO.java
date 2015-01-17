package Model.repository;

import Model.entity.Category;
import Model.entity.Producer;

import java.util.Map;

public interface ProducerDAO extends GenericDAO<Producer, Integer>{
	public Map<Producer, Long> getCategoryProducersWithCount(Category cat);
}
