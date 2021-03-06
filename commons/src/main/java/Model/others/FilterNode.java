package Model.others;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.entity.Entity;
import Model.service.CommonUtilsService;
import org.springframework.util.ClassUtils;


public abstract class FilterNode {
	protected FilterType type;
	protected List<Object> values = new ArrayList<Object>();
	protected Boolean isSet = false;
	
	public Boolean getIsSet() {
		return isSet;
	}
	public void setIsSet(Boolean isSet) {
		this.isSet = isSet;
	}
	public abstract FilterType getType();
	public abstract List<Object> getValues();
	public abstract void addValue(Object g);
	public abstract void setValues(List<Object> g);
	public List<String> getStringValues() {
		List<String> vals = new ArrayList<String>();
		for(Object f:values){
			if(f!=null){
			boolean isPrimitiveOrWrapped = ClassUtils.isPrimitiveOrWrapper(f.getClass());
			if(!isPrimitiveOrWrapped && !f.getClass().getSimpleName().contains("String")){
			Field fld;
			try {
				fld = CommonUtilsService.getIdField(((Entity)f).getClass());
				vals.add(CommonUtilsService.getValue(f, fld).toString());
			} catch (IntrospectionException e1) {

				e1.printStackTrace();
			
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			} catch (NoSuchMethodException e) {

				e.printStackTrace();
			}
			}
			if(f.getClass().getSimpleName().contains("Date")){
				Date g = (Date)f;
				vals.add(Long.toString(g.getTime()));
			}
			if(isPrimitiveOrWrapped || f.getClass().getSimpleName().contains("String")){
				vals.add(f.toString());
			}
			}
		}
		return vals;
	}
}
