package Model.repository;

import Model.dto_beans.ParamFilterBean;
import Model.dto_beans.ValueBean;
import Model.entity.Category;
import Model.entity.GoodItem;
import Model.entity.NewsType;
import Model.repository.GenericDAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CategoryDAO extends GenericDAO<Category, Integer> {
	

	public List<Category> getRootCategories();
	public List<Category> listCategory(Category cat);
	public List<Category> listCategoryById(Integer id);
	public Category getCategoryByAlias(String alias);
	public Double getMaxPrice(Category cat);
	public Double getMinPrice(Category cat);
	public List<GoodItem> getSortedGoods(String criteria);
	public Set<Category> getTopCategories();


	public List<GoodItem> listGoodsPage(Category cat, ParamFilterBean n, GoodItem.OrderingRules rule, Integer begin, Integer end);
	public List<GoodItem> propertyFilter(ParamFilterBean n, GoodItem.OrderingRules rule, Integer firstResult, Integer maxResult);
	public Integer getGoodsCount(Category cat);
	public List<Category> listPage(Category.OrderingRules rule, Integer begin, Integer end);
	public Map<Category, Long> getAllActionCategories();
	public Map<Category, Long> getAllNewsTypeCategories(NewsType nwstp);
	public Category getCategiryByUrl(String url);
	public Long propertyFilterCount(ParamFilterBean n, GoodItem.OrderingRules rule);
	public Long getUnderFilterGoodsCount(ParamFilterBean currentfilter, GoodItem.OrderingRules ordering, Integer propid, ValueBean val) throws CloneNotSupportedException;

}
