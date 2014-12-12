package Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodItemService extends ServiceImpl<GoodItem, Integer, GoodDAO>{
	
	@Autowired
	private GoodDAO gd;
	
	@Autowired
	private UserService usrServ;
	
	@Autowired
	private CommentService commServ;
	
	@Autowired
	private Good_GuarantieService ggServ;

	
	@Transactional
	public List<GoodItem> propertyFilter(ParamFilterBean n, Integer begin, Integer end){
		return gd.propertyFilter(n, begin, end);
	}
	
	
	
	@Override
	@Transactional
	public void update(GoodItem entity) {
		if(!entity.getGoodguaranties().isEmpty()){
		for(Good_Guarantie gg: entity.getGoodguaranties()){
			ggServ.update(gg);
		}
		}
		super.update(entity);
	}



	@Transactional
	public List<GoodItem> searchGood(String val){
		return gd.goodSearch(val);
	}
	
	@Transactional
	public List<GoodItem> getTopGoods(){
		return gd.getTopGoods();
	}
	
	@Transactional
	public List<GoodItem> getSeasonGoods(){
		return gd.getSeasonGoods();
	}
	
	@Transactional
	public Integer getLastId(){
		return gd.getLastId();
	}

	public Set<GoodItem> getSalesLeaders(Integer i) {
		// TODO Auto-generated method stub
		return gd.getSalesLeaders(i);
	}
	
	public Set<GoodItem> getLastLoaded(Integer count){
		return gd.getLastLoaded(count);
	}
	

	public Integer getCount(){
		return gd.getCount();
	}
	
	
	public void changeMultiValues(List<? extends Serializable> ids, Map<String, Object> propvals) {
		 gd.changeMultiValues(ids, propvals);
	}

	public Set<GoodItem> getTopGoods(Category cat) {
		return gd.getTopGoods(cat);
	}

	@Transactional
	public void addComment(GoodItem g, User user, Comment com){
		g.addComment(com);
		if(com.getId()==null)
		com.setId(commServ.save(com));
				
		gd.update(g);	
	}
	
	@Transactional
	public GoodItem getGoodItemByUrl(String url) {
		return gd.getGoodItemByUrl(url);
		
	}


	@Transactional
	public List<GoodItem> searchGood(String val, Integer begin, Integer end) {
		return gd.searchGood(val, begin, end);
	}


	

	

}
