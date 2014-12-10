package Model.filters;

import java.util.List;

import Model.EntityField;

public interface AdminFilter {
	
	List<String> getValues();
	EntityField getFld();
	
}
