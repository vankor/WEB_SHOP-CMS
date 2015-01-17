package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GoodInfo {
	protected int id;
	protected List<String> parameters = new ArrayList<String>();
	protected List<String> values = new ArrayList<String>();
	protected Category category;

	public abstract List<String> getParameters();
	
	public abstract int getId();

	public abstract Category getCategory();

	public abstract void setCategory(Category category);

	public abstract void setId(int id);

	public abstract List<String> getValues();

	public abstract void setValues(List<String> values);

	public abstract void setParameters(List<String> parameters);

	public abstract Map<String, String> getProperties();
	
	

}
