package Model;

import java.lang.reflect.Field;
import java.util.List;

public interface CommonUtils {
	public Object getValue(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException;
	public Object getValueByName(Object obj, String fieldname) throws IllegalArgumentException, IllegalAccessException;
}
