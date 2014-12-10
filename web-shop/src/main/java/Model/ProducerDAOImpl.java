package Model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProducerDAOImpl extends GenericHibTemplateDAOImpl<Producer, Integer> implements ProducerDAO{

	@Transactional
	public Map<Producer, Long> getCategoryProducersWithCount(Category cat) {
		System.out.println(cat.getName());
		List<Object> list = template.findByNamedParam("select p, count(g) from GoodItem as g inner join g.producer as p inner join g.category as c where c = :cat group by p","cat",cat);
		
		Map<Producer, Long> prodcount = new TreeMap<Producer, Long>();
		for(Object f:list){
			int i = 0;
			Producer prod = null;
			Long count = new Long(0);
			Object[]g = (Object[])f;
			for(Object h:g){
				if(i==0){
					prod = (Producer) h;
				}
				else{
					count = (Long) h;
				}
				i++;
				System.out.println(h.getClass().getCanonicalName());
				System.out.println(h);
			}
			prodcount.put(prod, count);
			
		}
		return prodcount;
	}

}
