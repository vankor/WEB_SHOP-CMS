package Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ObjectFieldContainer {
	private Object obj;
	private Map<EntityField, Object> fields = new TreeMap<EntityField, Object>(new Comparator<EntityField>() {
	    @Override
		public int compare(EntityField arg0, EntityField arg1) {
			// TODO Auto-generated method stub
			return arg0.getPriority().compareTo(arg1.getPriority());
		}
	});
	
	private Map<String, String> refs = new TreeMap<String, String>();
	
	
	public Map<String, String> getRefs() {
		return refs;
	}
	public void setRefs(Map<String, String> refs) {
		this.refs = refs;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
			this.obj = obj;
	}
	
	public Map<EntityField, Object> getFields() {
		return fields;
	}
	public void setFields(Map<EntityField, Object> fields) {
		this.fields = fields;
	}
	public void fillFieldValues() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for(Entry<EntityField, Object> entr: fields.entrySet()){
			System.out.println(obj.toString()+entr.getKey().getPropname());
			fields.put(entr.getKey(), CommonUtilsService.getValueByName(obj, entr.getKey().getPropname()));
		}
	}


}
