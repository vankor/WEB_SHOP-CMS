package Model.dto_beans;

import Model.entity.Property;
import Model.entity.Value;

import java.util.ArrayList;
import java.util.List;

public class GoodPropSegmentBean {


	private String segment_name;
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	List<PropValueBean> properties = new ArrayList<PropValueBean>();







	
	public void addProperty(Property prop, List<Value> vals) {
		PropValueBean prbn = new PropValueBean(prop, vals);
		properties.add(prbn);
	}
	



	public String getSegment_name() {
		return segment_name;
	}
	public void setSegment_name(String segment_name) {
		this.segment_name = segment_name;
	}






	
	
	public List<PropValueBean> getProperties() {
		return properties;
	}
	public void setProperties(List<PropValueBean> properties) {
		this.properties = properties;
	}



	
	

}
