package Model.dto_beans;

import Model.entity.Property;
import Model.entity.Value;

import java.util.ArrayList;
import java.util.List;

public class PropValueBean {

	public PropValueBean(){
		
	}
	public PropValueBean(Property prop, List<Value> vals) {
		super();
		this.prop = prop;
		this.vals = vals;
	}
	public Property getProp() {
		return prop;
	}
	public void setProp(Property prop) {
		this.prop = prop;
	}
	public List<Value> getVals() {
		return vals;
	}
	public void setVals(List<Value> vals) {
		this.vals = vals;
	}
	Property prop;
	List<Value> vals = new ArrayList<Value>();
	
	

}
