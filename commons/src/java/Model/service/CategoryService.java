package Model.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import Model.*;
import Model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends ServiceImpl<Category, Integer, CategoryDAO> {
	
	@Autowired
	private CategoryDAO catDAO;
	
	@Autowired
	private GoodDAO goodDAO;
	
	@Autowired
	private PageDAO pageDAO;
	
	@Autowired
	private PropSegmentDAO prsgDAO;
	
/*	@Transactional
	public void add(Category g) {
		catDAO.add(g);
		
	}

	@Transactional
	public void delete(Category g) {
		catDAO.delete(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		catDAO.deleteById(id);
		
	}
	@Transactional
	public List<Category> getAll() {
		return catDAO.getAll();
	}

	@Transactional
	public Category getById(Integer id) {
		return catDAO.getById(id, false);
	}
*/
	@Transactional
	public void update(Category cat) {
	//	pageDAO.update(k.getPage());
	/*	System.out.println("ID "+k.getPage().getId());
		System.out.println(k.getPage().getParent_page());*/
		System.out.println(cat.getParent_category());
		System.out.println(cat.getPage().getParent_page());
		for(PropSegment p:cat.getPropsegments()){
			System.out.println("Сегмент параметров "+p.getId()+"   "+p.getName());
		//	prsgDAO.update(p);
			for(Property prop:p.getProperties()){
				System.out.println("Свойство параметра "+prop.getId()+"   "+prop.getName()+"      "+prop.getMainvalscount()+"   "+prop.getMainvalscount()+"     "+prop.getCat());
			}
			
		}
			
		catDAO.update(cat);
	}
	
	@Transactional
	public List<Category> getRootCategories() {
		return catDAO.getRootCategories();
	}
	
	@Transactional
	public List<Category> listCategory(Category cat) {
		return catDAO.listCategory(cat);
	}
	
	@Transactional
	public List<Category> listCategoryById(Integer id) {
		return catDAO.listCategoryById(id);
	}
	
	@Transactional
	public Category getCategoryByAlias(String alias) {
		return catDAO.getCategoryByAlias(alias);
	}
	
	@Transactional
	public Double getExtremePrice(Category cat, String type) {
		
		if(type.equals("max")){return catDAO.getMaxPrice(cat);}
		if(type.equals("min")){return catDAO.getMinPrice(cat);}
		else{ throw new IllegalArgumentException("type must be max or min");}
		
	}
	
	@Transactional
	public List<GoodItem> listGoodsPage(Category cat, ParamFilterBean n, GoodItem.OrderingRules rule, Integer begin, Integer end){
		return catDAO.listGoodsPage(cat, n, rule, begin, end);
	}
	
	@Transactional
	public List<GoodItem> propertyFilter(ParamFilterBean n, GoodItem.OrderingRules rule, Integer begin, Integer end){
		return catDAO.propertyFilter(n, rule, begin, end);
	}
	
	@Transactional
	public Category getCategiryByUrl(String url) {
		return catDAO.getCategiryByUrl(url);
	}
	
	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return catDAO.getLastId();
	}
	@Transactional
	public Map<Category, Long> getAllActionCategories(){
		return catDAO.getAllActionCategories();
	}
	
	@Transactional
	public Set<Category> getTopCategories() {
		return catDAO.getTopCategories();
		
	}
	@Transactional
	public Integer getGoodsCount(Category cat){
		return catDAO.getGoodsCount(cat);
	}
	@Transactional
	public Map<Category, Long> getAllNewsTypeCategories(NewsType nwstp){
		return catDAO.getAllNewsTypeCategories(nwstp);
		
	}
	
	@Transactional
	public Long getUnderFilterGoodsCount(ParamFilterBean currentfilter, GoodItem.OrderingRules ordering, Integer propid, ValueBean val) throws CloneNotSupportedException {
		return catDAO.getUnderFilterGoodsCount(currentfilter, ordering, propid, val);
		
	}
	
	public Long propertyFilterCount(ParamFilterBean n, GoodItem.OrderingRules rule){
		return catDAO.propertyFilterCount(n, rule);
		
	}
	
	@Transactional
	public void fillCategoryStack(List<Category> st, Category cat){
		if(cat.getParent_category()!=null){
			st.add(cat); 
			
			Integer id = cat.getParent_category().getId();
			if(id<=0){
				return;
			}
			
			fillCategoryStack(st, catDAO.getById(id, false));
			}
		else{
			st.add(cat);return;
			
		}
		
	}
}
