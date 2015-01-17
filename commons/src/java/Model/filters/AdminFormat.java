package Model.filters;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import Model.CommonUtilsService;
import Model.EntityField;
import Model.Filter;
import Model.FilterNode;
import Model.GoodItem;
import Model.RangeFilterNode;
import Model.Service;
import Model.ServiceImpl;
import Model.ValuesFilterNode;

@Entity
@Table(name = "adminformats")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
property = "@id")
public class AdminFormat implements Comparable, Model.Entity, Serializable{
	
	private Integer id;
	private String name;
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
	private Boolean isdefault = false;
	


	

	private Boolean isdeleted = false;
	
	
	/*private Map<String, Set<FilterNode>> propfilters = new TreeMap<String, Set<FilterNode>>();
	private List<GoodItem> filteredgoods = new ArrayList<GoodItem>();
	private String classname;
	private String coladmintype;
	private Map<String, Set<FilterNode>> joinfilters = new TreeMap<String, Set<FilterNode>>();
	private Boolean compositefilter;
	private String joinfieldname;
	private String joincolectionname;
	private Boolean multiclasstable = false;
	private String joinclassname;
	private Map<String, String> sortprops = new TreeMap<String, String>();
	private Map<String, String> activefiltercols = new TreeMap<String, String>();
	private Map<String, String> hidecols = new TreeMap<String, String>();
	private Map<String, Integer> colorder = new TreeMap<String, Integer>();*/
	
	public Filter createFilter(ApplicationContext context) throws ClassNotFoundException, IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Filter filter = new Filter();
		Map<String, Set<FilterNode>> propfilters = new TreeMap<String, Set<FilterNode>>();
		Map<String, Set<FilterNode>> joinfilters = new TreeMap<String, Set<FilterNode>>();
		Map<String, String> sortprops = new TreeMap<String, String>();
		Map<String, String> activefiltercols = new TreeMap<String, String>();
		Map<String, String> hidecols = new TreeMap<String, String>();
		Map<String, Integer> colorder = new TreeMap<String, Integer>();
			
		filter.setClassname(classname);
		filter.setColadmintype(coladmintype);
		filter.setCompositefilter(compositefilter);
		filter.setJoinclassname(joinclassname);
		filter.setJoincolectionname(joincolectionname);
		filter.setJoinfieldname(joinfieldname);
		filter.setMulticlasstable(multiclasstable);
				
		for(PropValueFilter prvflt: propvaluefilters){
			System.out.println("vals "+prvflt.getValues()+" fld "+prvflt.getFld().getPropname());
			List vallist = fillvalslist(prvflt,context);
			FilterNode filternode = new ValuesFilterNode(vallist);
			System.out.println("Коллекция "+vallist);
			Set fltnodes = propfilters.get(prvflt.getFld().getPropname());
			if(fltnodes==null){
				fltnodes = new TreeSet();
			}
			filternode.setIsSet(true);
			fltnodes.add(filternode);
			propfilters.put(prvflt.getFld().getPropname(), fltnodes);
		}
		
		for(PropValueFilter prvflt: joinvaluefilters){
			List vallist = fillvalslist(prvflt,context);
			FilterNode filternode = new ValuesFilterNode(vallist);
			System.out.println("Коллекция "+vallist);
			Set fltnodes = joinfilters.get(prvflt.getFld().getPropname());
			if(fltnodes==null){
				fltnodes = new TreeSet();
			}
			filternode.setIsSet(true);
			fltnodes.add(filternode);
			joinfilters.put(prvflt.getFld().getPropname(), fltnodes);
		}
		
		for(PropRangeFilter prvflt: proprangefilters){
			List vallist = fillvalslist(prvflt,context);
			FilterNode filternode = new RangeFilterNode(vallist);
			System.out.println("Коллекция "+vallist);
			Set fltnodes = propfilters.get(prvflt.getFld().getPropname());
			if(fltnodes==null){
				fltnodes = new TreeSet();
			}
			filternode.setIsSet(true);
			fltnodes.add(filternode);
			propfilters.put(prvflt.getFld().getPropname(), fltnodes);
		}
		
		for(PropRangeFilter prvflt: joinrangefilters){
			List vallist = fillvalslist(prvflt,context);
			FilterNode filternode = new RangeFilterNode(vallist);
			System.out.println("Коллекция "+vallist);
			Set fltnodes = joinfilters.get(prvflt.getFld().getPropname());
			if(fltnodes==null){
				fltnodes = new TreeSet();
			}
			filternode.setIsSet(true);
			fltnodes.add(filternode);
			joinfilters.put(prvflt.getFld().getPropname(), fltnodes);
		}
		
		for(EntityField ent: this.sortprops){
			sortprops.put(ent.getPropname(), ent.getColname());
		}
		
		for(EntityField ent: this.activefiltercols){
			activefiltercols.put(ent.getPropname(), ent.getColname());
		}
		
		for(EntityField ent: this.hidecols){
			hidecols.put(ent.getPropname(), ent.getColname());
		}
		
		for(FieldPriority ent: this.colorder){
			colorder.put(ent.getFld().getPropname(), ent.getPriority());
		}
		
		filter.setPropfilters(propfilters);
		filter.setActivefiltercols(activefiltercols);
		filter.setJoinfilters(joinfilters);
		filter.setHidecols(hidecols);
		filter.setSortprops(sortprops);
		filter.setColorder(colorder);
		Service adminserv = CommonUtilsService.getService(classname, context);
		List d = adminserv.listPage(filter, 0, adminserv.getCount());
		filter.setFilteredgoods(d);
		return filter;
	}
	
	private List fillvalslist(AdminFilter prvflt, ApplicationContext context) throws ClassNotFoundException, IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List vallist = new ArrayList();
		System.out.println();
		
		for(String str:prvflt.getValues()){
			
			Object obj = null;
	/*		Class admclass = Class.forName("Model."+prvflt.getFld().getClassname());
			Class prclass = CommonUtilsService.getFieldClassByClass(admclass, prvflt.getFld().getPropname());
			System.out.println(prvflt.getFld().getPropclassname());*/
			boolean isprimitiveordate = (prvflt.getFld().getPropclassname().contains("Date") || prvflt.getFld().getPropclassname().contains("String") || prvflt.getFld().getPropclassname().contains("Integer") || prvflt.getFld().getPropclassname().contains("Double") || prvflt.getFld().getPropclassname().contains("Long") || prvflt.getFld().getPropclassname().contains("Short") || prvflt.getFld().getPropclassname().contains("Float") || prvflt.getFld().getPropclassname().contains("Character") || prvflt.getFld().getPropclassname().contains("Boolean"));
							
			
			
		
			if(isprimitiveordate){
				if(prvflt.getFld().getPropclassname().contains("Date")){
					obj = new Date(Long.parseLong(str));
				}
				else
				obj = str;
			}
			else{
				Object id = new Object();
				Class propclass = prvflt.getFld().getPropclass();
				ServiceImpl adminserv = CommonUtilsService.getService(prvflt.getFld().getPropclass().getSimpleName(), context);
				Field idfld = CommonUtilsService.getIdField(propclass);
				try{
				id = Integer.parseInt(str);
				}
				catch (NumberFormatException ex){
					id = str;
				}

				System.out.println("Айдии "+id);
				obj = adminserv.getById((Serializable) id);
				System.out.println("ОБЬЕЕЕЕКТ "+obj);
			}
			
			vallist.add(obj);

		}
		
		
		return vallist;
	}

	@Id
	@GenericGenerator(name = "id", strategy = "increment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_propvalfilters", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "propvalfilter_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<PropValueFilter> getPropvaluefilters() {
		return propvaluefilters;
	}


	public void setPropvaluefilters(List<PropValueFilter> propvaluefilters) {
		this.propvaluefilters = propvaluefilters;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_proprangefilters", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "proprangefilter_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<PropRangeFilter> getProprangefilters() {
		return proprangefilters;
	}


	public void setProprangefilters(List<PropRangeFilter> proprangefilters) {
		this.proprangefilters = proprangefilters;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_joinvalfilters", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "joinvalfilter_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<PropValueFilter> getJoinvaluefilters() {
		return joinvaluefilters;
	}


	public void setJoinvaluefilters(List<PropValueFilter> joinvaluefilters) {
		this.joinvaluefilters = joinvaluefilters;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_joinrangefilters", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "joinrangefilter_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<PropRangeFilter> getJoinrangefilters() {
		return joinrangefilters;
	}


	public void setJoinrangefilters(List<PropRangeFilter> joinrangefilters) {
		this.joinrangefilters = joinrangefilters;
	}


	@Transient
	public List<GoodItem> getFilteredgoods() {
		return filteredgoods;
	}


	public void setFilteredgoods(List<GoodItem> filteredgoods) {
		this.filteredgoods = filteredgoods;
	}

	@Column(name="classname")
	public String getClassname() {
		return classname;
	}


	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Transient
	public String getColadmintype() {
		return coladmintype;
	}


	public void setColadmintype(String coladmintype) {
		this.coladmintype = coladmintype;
	}



	@Column(name = "compositefilter")
	@Type(type = "boolean")
	public Boolean getCompositefilter() {
		return compositefilter;
	}


	public void setCompositefilter(Boolean compositefilter) {
		this.compositefilter = compositefilter;
	}

	@Column(name = "joinfieldname")
	public String getJoinfieldname() {
		return joinfieldname;
	}


	public void setJoinfieldname(String joinfieldname) {
		this.joinfieldname = joinfieldname;
	}

	@Column(name = "joincolectionname")
	public String getJoincolectionname() {
		return joincolectionname;
	}


	public void setJoincolectionname(String joincolectionname) {
		this.joincolectionname = joincolectionname;
	}


	@Column(name = "multiclasstable")
	@Type(type = "boolean")
	public Boolean getMulticlasstable() {
		return multiclasstable;
	}


	public void setMulticlasstable(Boolean multiclasstable) {
		this.multiclasstable = multiclasstable;
	}

	@Column(name = "joinclassname")
	@Type(type = "boolean")
	public String getJoinclassname() {
		return joinclassname;
	}


	public void setJoinclassname(String joinclassname) {
		this.joinclassname = joinclassname;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_sortprops", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "prop_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<EntityField> getSortprops() {
		return sortprops;
	}


	public void setSortprops(List<EntityField> sortprops) {
		this.sortprops = sortprops;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_activefiltercols", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "activefilterprop_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<EntityField> getActivefiltercols() {
		return activefiltercols;
	}


	public void setActivefiltercols(List<EntityField> activefiltercols) {
		this.activefiltercols = activefiltercols;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_hidecols", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "hidecolprop_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<EntityField> getHidecols() {
		return hidecols;
	}


	public void setHidecols(List<EntityField> hidecols) {
		this.hidecols = hidecols;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "formats_colorders", 
	joinColumns = {@JoinColumn(name = "format_id", nullable = false, updatable = false)}, 
	inverseJoinColumns = {@JoinColumn(name = "colorder_id", nullable = false, updatable = false)})
	@JsonManagedReference
	public List<FieldPriority> getColorder() {
		return colorder;
	}


	public void setColorder(List<FieldPriority> colorder) {
		this.colorder = colorder;
	}

	@Column(name = "isdeleted")
	@Type(type = "boolean")
	public Boolean getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Column(name = "isdefault")
	@Type(type = "boolean")
	public Boolean getIsdefault() {
		return isdefault;
	}


	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
