package Model.dto_beans;

import Model.entity.Category;
import Model.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class CategoryCreateBean {
	private String name;
	private int parent_id;
	private String alias;
	private Boolean isFinalCategory = false;

	
	public 	CategoryCreateBean(){}
	public CategoryCreateBean(Category cat) {
		this.name = cat.getName();
//		this.parent_id = cat.getParent_id();
		this.parent_id = cat.getParent_category().getId();
//		this.alias = cat.getAlias();
		this.isFinalCategory = cat.getIsFinalCategory();
		for(Property p: cat.getProps()){
			parameters.add(p.getName());
		}
		
	}
	
	public Boolean getIsFinalCategory() {
		return isFinalCategory;
	}
	public void setIsFinalCategory(Boolean isFinalCategory) {
		this.isFinalCategory = isFinalCategory;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	private List<String> parameters = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public List<String> getParameters() {
		return parameters;
	}
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	

}
