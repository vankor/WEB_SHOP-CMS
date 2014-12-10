package Model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GoodCompareBean implements Comparable {
	
	private GoodItem good;
	private Property prop;
	private Value val;
	
	
	public GoodCompareBean(GoodItem good, Property prop, Value val) {
		this.good = good;
		this.prop = prop;
		this.val = val;
	}
	
	public GoodCompareBean() {}
	
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}
	public Property getProp() {
		return prop;
	}
	public void setProp(Property prop) {
		this.prop = prop;
	}
	public Value getVal() {
		return val;
	}
	public void setVal(Value val) {
		this.val = val;
	}

	@Override
	public int compareTo(Object arg0) {
		GoodCompareBean othergood = null;
		if(arg0 instanceof GoodCompareBean){	
			othergood = (GoodCompareBean)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.good.compareTo(othergood.good);
	}
	
	

}
