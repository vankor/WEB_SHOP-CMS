package Model;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProducerService extends ServiceImpl<Producer, Integer, ProducerDAO>{
	@Autowired
	private ProducerDAO prodDAO;
	
	@Transactional
	public Map<Producer, Long> getCategoryProducersWithCount(Category cat) {
		return prodDAO.getCategoryProducersWithCount(cat);
	}
}
