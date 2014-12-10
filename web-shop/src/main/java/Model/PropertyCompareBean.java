package Model;

import java.util.Set;
import java.util.TreeSet;

public class PropertyCompareBean implements Comparable {
	
	private Property prop;
	private Set<GoodCompareBean> comparegoods = new TreeSet<GoodCompareBean>();
	
	
	
	public PropertyCompareBean() {}
	
	
	public Set<GoodCompareBean> getComparegoods() {
		return comparegoods;
	}


	public void setComparegoods(Set<GoodCompareBean> comparegoods) {
		this.comparegoods = comparegoods;
	}


	public Property getProp() {
		return prop;
	}
	public void setProp(Property prop) {
		this.prop = prop;
	}
	
	public void generateCompareBeans(Set<GoodItem> goodset, Property prop){
		this.prop = prop;
		
		Value propvalue = null;
		for(GoodItem good: goodset){
			System.out.println(prop.getName()+"   "+prop.getId()+"    "+good.getName());
			for(Value val: good.getVals()){
				System.out.println("Значение свойстав: "+val.getValue() + val.getId());
				if(prop.equals(val.getProp())){
					System.out.println("     равно");
					propvalue = val;
				}
			}
			GoodCompareBean bean = new GoodCompareBean(good,prop,propvalue);
			comparegoods.add(bean);
		}
		
	}

	@Override
	public int compareTo(Object arg0) {
		PropertyCompareBean othergood = null;
		if(arg0 instanceof PropertyCompareBean){	
			othergood = (PropertyCompareBean)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.prop.compareTo(othergood.prop);
	}
	


}
