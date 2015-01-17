package Model.others;

import java.util.Set;
import java.util.TreeSet;

import Model.entity.Category;
import Model.entity.GoodItem;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class CategoryCompareGoods implements Comparable{
	
	private Category cat;
	
	private Set <Integer> goodsid= new TreeSet<Integer>();
	private Set <GoodItem> goods= new TreeSet<GoodItem>();

	@JsonBackReference
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	
	
		
	public Set<Integer> getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Set<Integer> goodsid) {
		this.goodsid = goodsid;
	}






	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryCompareGoods other = (CategoryCompareGoods) obj;
		if (cat == null) {
			if (other.cat != null)
				return false;
		} else if (!cat.equals(other.cat))
			return false;
		return true;
	}

	public void addGood(Integer k) {goodsid.add(k);}
	
	@Override
	public int compareTo(Object o) {
		CategoryCompareGoods other = null;
		if(o instanceof CategoryCompareGoods){	
			other = (CategoryCompareGoods)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.cat.compareTo(other.cat);
	}
	
	
}
