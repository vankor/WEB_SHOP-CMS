package Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

public interface GoodDAO extends GenericDAO<GoodItem, Integer>{

		public List<GoodItem> getGoodsByDescrition(String d);		
		public List<GoodItem> propertyFilter(ParamFilterBean n, Integer firstResult, Integer maxResult);
		public List<GoodItem> goodSearch(String value);
		public List<GoodItem> getTopGoods();
		public List<GoodItem> getSeasonGoods();
		public Set<GoodItem> getSalesLeaders(Integer count);
		public Set<GoodItem> getLastLoaded(Integer count);
		public Integer getCount();
		public Set<GoodItem> getTopGoods(Category cat);
		public GoodItem getGoodItemByUrl(String url);
		public List<GoodItem> searchGood(String val, Integer begin, Integer end);
}
