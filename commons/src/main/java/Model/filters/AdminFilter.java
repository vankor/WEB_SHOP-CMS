package Model.filters;

import Model.EntityField;

import java.util.List;

public interface AdminFilter {
	
	List<String> getValues();
	EntityField getFld();
	
}
