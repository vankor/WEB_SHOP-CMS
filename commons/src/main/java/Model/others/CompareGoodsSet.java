package Model.others;

import Model.entity.Category;
import Model.entity.GoodItem;

import java.util.Set;
import java.util.TreeSet;

public class CompareGoodsSet {
	private Set<CategoryCompareGoods> categcomparesets = new TreeSet<CategoryCompareGoods>();
	
	public void addCategoryCompareGoods(CategoryCompareGoods categcompareset){
		categcomparesets.add(categcompareset);
	}
	
	public void deleteCategoryCompareGoods(CategoryCompareGoods categcompareset){
		categcomparesets.remove(categcompareset);
	}

	public Set<CategoryCompareGoods> getCategcomparesets() {
		return categcomparesets;
	}

	public void setCategcomparesets(Set<CategoryCompareGoods> categcomparesets) {
		this.categcomparesets = categcomparesets;
	}
	
	public CategoryCompareGoods getComparedByCategory(Category cat){
		return getComparedByCategoryId(cat.getId());
	}
	
	public CategoryCompareGoods getComparedByCategoryId(Integer catid){
		for(CategoryCompareGoods goodset: categcomparesets){
			if(goodset.getCat()!=null){
				if(goodset.getCat().getId()==catid){
					return goodset;
				}
			}
		}
		return null;
	}
	
	public void removeByGoodId(Integer id){
		Integer remid = 0;
		for(CategoryCompareGoods goodset: categcomparesets){
			for(Integer goodid: goodset.getGoodsid()){
				if(goodid.equals(id)){
					remid = goodid;
					
				}
			}
			goodset.getGoodsid().remove(remid);
		}
		
	}
	
	public boolean containsGood(GoodItem g){
		boolean t=false;
		for(CategoryCompareGoods f: categcomparesets){
			if(f.getGoodsid().contains(g.getId()))t=true;
		}
		return t;
	}
	

}
