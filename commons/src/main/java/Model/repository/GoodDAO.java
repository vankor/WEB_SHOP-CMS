package Model.repository;

import java.util.List;
import java.util.Set;

import Model.dto_beans.ParamFilterBean;
import Model.entity.Category;
import Model.entity.GoodItem;

public interface GoodDAO extends GenericDAO<GoodItem, Integer> {

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
