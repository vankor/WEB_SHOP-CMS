package Model.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Model.entity.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NewsTypeDAOImpl extends GenericHibTemplateDAOImpl<NewsType, Integer> implements NewsTypeDAO{
	
	@Autowired
	private CategoryDAO catDAO;
	
	@Override
	@Transactional
	public Map<NewsType, Long> getAllNewsTypeNewsCount() {
		List list = template.find("select nt, count(distinct n) from NewsType as nt inner join nt.news as n group by nt");
		Map<NewsType, Long> nwstpnwscounts = new TreeMap<NewsType, Long>();
		for(Object f:list){
			int i = 0;
			NewsType nwstp = null;
			Long count = new Long(0);
			Object[]g = (Object[])f;
			for(Object h:g){
				if(i==0){
					nwstp = (NewsType) h;
					nwstp.setCategnews(catDAO.getAllNewsTypeCategories(nwstp));
				}
				else{
					count = (Long) h;
				}
				i++;
				System.out.println(h.getClass().getCanonicalName());
				System.out.println(h);
			}
			nwstpnwscounts.put(nwstp, count);
			
		}
		return nwstpnwscounts;
	}
	
}
