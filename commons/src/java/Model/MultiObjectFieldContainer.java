package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MultiObjectFieldContainer {
	Map<String, ObjectFieldContainer> fieldcontainers = new HashMap<String, ObjectFieldContainer>();
	private Map<String, String> refs = new TreeMap<String, String>();

	public Map<String, String> getRefs() {
		return refs;
	}

	public void setRefs(Map<String, String> refs) {
		this.refs = refs;
	}

	public Map<String, ObjectFieldContainer> getFieldcontainers() {
		return fieldcontainers;
	}

	public void setFieldcontainers(Map<String, ObjectFieldContainer> fieldcontainers) {
		this.fieldcontainers = fieldcontainers;
	}
	

}
