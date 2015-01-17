package Model.dto_beans;

import Model.entity.Property;
import Model.entity.Value;

import java.util.ArrayList;
import java.util.List;

public class GoodPropSegmentBean {

	//	private PropSegment prsegment;
	private String segment_name;
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	//	private Map<Property, List<Value>> properties = new TreeMap<Property, List<Value>>();
	List<PropValueBean> properties = new ArrayList<PropValueBean>();

//	public Map<Property, List<Value>> getProperties() {
//		return properties;
//	}
//	public void setProperties(Map<Property, List<Value>> properties) {
//		this.properties = properties;
//	}
	
	public void addProperty(Property prop, List<Value> vals) {
		PropValueBean prbn = new PropValueBean(prop, vals);
		properties.add(prbn);
	}
	
	//	public Map<Integer, List<PropValueBean>> getProperties() {
//		return properties;
//	}
	public String getSegment_name() {
		return segment_name;
	}
	public void setSegment_name(String segment_name) {
		this.segment_name = segment_name;
	}
//	public Integer getSegment_id() {
///		return segment_id;
//	}
//	public void setSegment_id(Integer segment_id) {
//		this.segment_id = segment_id;
//	}
	
	
	public List<PropValueBean> getProperties() {
		return properties;
	}
	public void setProperties(List<PropValueBean> properties) {
		this.properties = properties;
	}
//	public void setProperties(Map<Integer, List<PropValueBean>> properties) {
//		this.properties = properties;
//	}
	
	

}
