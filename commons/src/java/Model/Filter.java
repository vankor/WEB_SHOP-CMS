package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Model.filters.AdminFormat;
import Model.filters.FieldPriority;
import Model.filters.PropRangeFilter;
import Model.filters.PropValueFilter;

public class Filter implements Comparable{
	private Map<String, Set<FilterNode>> propfilters = new TreeMap<String, Set<FilterNode>>();
	private List<GoodItem> filteredgoods = new ArrayList<GoodItem>();
	private String classname;
	private String coladmintype;
	private Map<String, Set<FilterNode>> joinfilters = new TreeMap<String, Set<FilterNode>>();
	private Boolean compositefilter;
	private String joinfieldname;
	private String joincolectionname;
	private Boolean multiclasstable = false;
	private String joinclassname;
	private Boolean hasnotnullfilters = false; 


	private Map<String, String> sortprops = new TreeMap<String, String>();
	private Map<String, String> activefiltercols = new TreeMap<String, String>();
	private Map<String, String> hidecols = new TreeMap<String, String>();
	private Map<String, Integer> colorder = new TreeMap<String, Integer>();
	
	

/*	private Integer id;
	private Integer name;
	private List<PropValueFilter> propvaluefilters = new ArrayList<PropValueFilter>();
	private List<PropRangeFilter> proprangefilters = new ArrayList<PropRangeFilter>();
	private transient List<GoodItem> filteredgoods = new ArrayList<GoodItem>();
	private String classname;
	private transient String coladmintype;
	private List<PropValueFilter> joinvaluefilters = new ArrayList<PropValueFilter>();
	private List<PropRangeFilter> joinrangefilters = new ArrayList<PropRangeFilter>();
	private Boolean compositefilter;
	private String joinfieldname;
	private String joincolectionname;
	private Boolean multiclasstable = false;
	private String joinclassname;
	private List<EntityField> sortprops = new ArrayList<EntityField>();
	private List<EntityField> activefiltercols = new ArrayList<EntityField>();
	private List<EntityField> hidecols = new ArrayList<EntityField>();
	private List<FieldPriority> colorder = new ArrayList<FieldPriority>();
	private Boolean isdefault = false;*/

	public Boolean getHasnotnullfilters() {
		if(!propfilters.isEmpty() || !joinfilters.isEmpty()){
		Map<String, Set<FilterNode>> commonmap = propfilters;
		commonmap.putAll(joinfilters);
		for(Entry<String,Set<FilterNode>> entr: commonmap.entrySet()){
			for(FilterNode nd: entr.getValue()){
				System.out.println("ND "+nd);
				System.out.println(nd!=null);
		//		System.out.println(nd.getClass() == RangeFilterNode.class);
				System.out.println(nd.getIsSet());
				if(nd!=null && nd.getIsSet()){
					setHasnotnullfilters(true);
					return true;
				}
			}
		}	
		}
		return false;
	}

	public void setHasnotnullfilters(Boolean hasnotnullfilters) {
		this.hasnotnullfilters = hasnotnullfilters;
	}

	private void extractfilters(List<PropValueFilter> valuefilters, List<PropRangeFilter> rangefilters, EntityFieldService entserv){
		
		for(Entry<String,Set<FilterNode>> entr:propfilters.entrySet()){
			for(FilterNode fnd:entr.getValue()){
				System.out.println("‘ËÎ¸Ú "+fnd.getType());
				if(fnd.getType() == FilterType.RANGE){
					System.out.println(fnd.getValues()+"    "+fnd.getStringValues());
					if(fnd.getValues().size()==2 && fnd.getValues().get(0)!=null && fnd.getValues().get(1)!=null){
					PropRangeFilter prfltr = new PropRangeFilter();
					prfltr.setFld(entserv.getByClassAndField(classname, entr.getKey()));
					prfltr.setMin(fnd.getStringValues().get(0));
					prfltr.setMax(fnd.getStringValues().get(1));
					rangefilters.add(prfltr);
					}
					}
				if(fnd.getType() == FilterType.VALUES){	
					if(!fnd.getValues().isEmpty()){
						System.out.println(fnd.getValues()+"    "+fnd.getStringValues());
					PropValueFilter pvfltr = new PropValueFilter();
				pvfltr.setFld(entserv.getByClassAndField(classname, entr.getKey()));
				pvfltr.setValues(fnd.getStringValues());
				valuefilters.add(pvfltr);
					}
			}
			
		}
		}
	}
	
	public AdminFormat createFormat(String name, EntityFieldService entserv, Boolean multiclasstable){
		AdminFormat form = new AdminFormat();
		
		form.setName(name);
		form.setClassname(classname);
		form.setJoinfieldname(joinfieldname);
		form.setColadmintype(coladmintype);
		form.setCompositefilter(compositefilter);
		form.setIsdefault(false);
		form.setJoinclassname(joinclassname);
		form.setJoincolectionname(joincolectionname);
		form.setMulticlasstable(multiclasstable);
		List<PropValueFilter> propvaluefilters = new ArrayList<PropValueFilter>();
		List<PropRangeFilter> proprangefilters = new ArrayList<PropRangeFilter>();
		List<PropValueFilter> joinvaluefilters = new ArrayList<PropValueFilter>();
		List<PropRangeFilter> joinrangefilters = new ArrayList<PropRangeFilter>();
		List<EntityField> sortprops = new ArrayList<EntityField>();
		List<EntityField> activefiltercols = new ArrayList<EntityField>();
		List<EntityField> hidecols = new ArrayList<EntityField>();
		List<FieldPriority> priorities = new ArrayList<FieldPriority>();
		System.out.println("–¿«Ã≈– ‘»À‹“–¿ "+propfilters.size());
		extractfilters(propvaluefilters, proprangefilters, entserv);
		if(multiclasstable)
		extractfilters(joinvaluefilters, joinrangefilters, entserv);
		for(Entry<String, String> entr:this.sortprops.entrySet()){
			sortprops.add(entserv.getByClassAndField(classname, entr.getKey()));
		}
		
		for(Entry<String, String> entr:this.activefiltercols.entrySet()){
			activefiltercols.add(entserv.getByClassAndField(classname, entr.getKey()));
		}
		
		for(Entry<String, String> entr:this.hidecols.entrySet()){
			hidecols.add(entserv.getByClassAndField(classname, entr.getKey()));
		}
			
		for(Entry<String,Integer> entr:this.colorder.entrySet()){
			FieldPriority fprt = new FieldPriority();
			fprt.setFld(entserv.getByClassAndField(classname, entr.getKey()));
			fprt.setPriority(entr.getValue());
			priorities.add(fprt);
		}
		form.setActivefiltercols(activefiltercols);
		form.setColorder(priorities);
		form.setHidecols(hidecols);
		form.setJoinrangefilters(joinrangefilters);
		form.setJoinvaluefilters(joinvaluefilters);
		form.setProprangefilters(proprangefilters);
		form.setPropvaluefilters(propvaluefilters);
		form.setSortprops(sortprops);
		
		System.out.println("–¿«Ã≈– ‘»À‹“–¿ 11 "+propvaluefilters.size());
		System.out.println(propvaluefilters);
		return form;
			
	}
	


	public String getJoinclassname() {
		return joinclassname;
	}

	public void setJoinclassname(String joinclassname) {
		this.joinclassname = joinclassname;
	}

	public Boolean getMulticlasstable() {
		return multiclasstable;
	}

	public void setMulticlasstable(Boolean multiclasstable) {
		this.multiclasstable = multiclasstable;
	}

	public String getJoincolectionname() {
		return joincolectionname;
	}

	public void setJoincolectionname(String joincolectionname) {
		this.joincolectionname = joincolectionname;
	}


	
	public Map<String, Integer> getColorder() {
		return colorder;
	}

	public void setColorder(Map<String, Integer> colorder) {
		this.colorder = colorder;
	}

	public Map<String, String> getHidecols() {
		return hidecols;
	}

	public void setHidecols(Map<String, String> hidecols) {
		this.hidecols = hidecols;
	}

	public Map<String, String> getActivefiltercols() {
		return activefiltercols;
	}

	public void setActivefiltercols(Map<String, String> activefiltercols) {
		this.activefiltercols = activefiltercols;
	}

	public Map<String, String> getSortprops() {
		return sortprops;
	}

	public void setSortprops(Map<String, String> sortprops) {
		this.sortprops = sortprops;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public List<GoodItem> getFilteredgoods() {
		return filteredgoods;
	}

	public void setFilteredgoods(List<GoodItem> filteredgoods) {
		this.filteredgoods = filteredgoods;
	}

	public Map<String, Set<FilterNode>> getPropfilters() {
		return propfilters;
	}
	
	public String getJoinfieldname() {
		return joinfieldname;
	}

	public void setJoinfieldname(String joinfieldname) {
		this.joinfieldname = joinfieldname;
	}

	public void setPropfilters(Map<String, Set<FilterNode>> propfilters) {
		this.propfilters = propfilters;
	}
	
	public String getColadmintype() {
		return coladmintype;
	}

	public void setColadmintype(String coladmintype) {
		this.coladmintype = coladmintype;
	}

	public Boolean getCompositefilter() {
		return compositefilter;
	}

	public void setCompositefilter(Boolean compositefilter) {
		this.compositefilter = compositefilter;
	}
	
	public Map<String, Set<FilterNode>> getJoinfilters() {
		return joinfilters;
	}

	public void setJoinfilters(Map<String, Set<FilterNode>> joinfilters) {
		this.joinfilters = joinfilters;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return classname.compareTo(o.toString());
	}
	
	public boolean isjoinfilter(String propname){
		return joinfilters.keySet().contains(propname);
		
		
	}


}
