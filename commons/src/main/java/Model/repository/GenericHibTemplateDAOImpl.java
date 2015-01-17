package Model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.lang.reflect.*;

import Model.service.CommonUtilsService;
import Model.others.Filter;
import Model.others.FilterNode;
import Model.others.FilterType;
import Model.entity.Entity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.ClassUtils;


public abstract class GenericHibTemplateDAOImpl <T extends Entity, ID extends Serializable>
implements GenericDAO<T, ID> {  

private Class<T> persistentClass;  
private static final String DEFSORTPROP = "id";
private static final String DEFSORTNAME = "ÈÄ";
@Autowired
protected HibernateTemplate template;

public GenericHibTemplateDAOImpl() {  

this.persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  

}  


public Class<T> getPersistentClass() {  
return persistentClass;  
}  



@Override
public void delete(T g) {
	template.delete(g);
	
}


@Override
public void setDelete(T ent) {
	ent.setIsdeleted(true);
	update(ent);
	
}




@Override
public void setDeleteById(ID t) {
	Session sess = template.getSessionFactory().openSession();
	String setdel = "update "+persistentClass.getSimpleName()+" SET isdeleted = 1 where id = :id";
	System.out.println(setdel);
	Query q = sess.createQuery(setdel);
	q.setParameter("id", t);
	System.out.println(t);
	q.executeUpdate();

	
	
}


@Override
public void add(T entity) {
	template.setCacheQueries(true);
	template.merge(entity);
	
}

@Override
public ID save(T entity) {
	return (ID) template.save(entity);
	
}

@Override
public void saveOrUpdate(T entity) {
	 template.saveOrUpdate(entity);
	
}



public HibernateTemplate getTemplate() {
	return template;
}


public void setTemplate(HibernateTemplate template) {
	this.template = template;
}


@Override
public ID getLastId() {
	StringBuilder bld = new StringBuilder("select max(g.id) from ");
	bld.append(getPersistentClass().getName());
	bld.append(" as g");
	List<Object> list = template.find(bld.toString()); 
	template.setCacheQueries(true);
	return (ID)list.get(0);
}



@Override
public void deleteById(ID id) {
	Session session = template.getSessionFactory().openSession();
	Transaction transaction = null;
	try {
		transaction = session.beginTransaction();
		T ent = (T) session.get(getPersistentClass(), id);
		session.delete(ent);
		transaction.commit();
	} catch (Exception exception) {
	     if (transaction != null) transaction.rollback();
	} finally {
		session.close();
	}
	
}


@SuppressWarnings("unchecked")  
public T getById(ID id, boolean lock) {  
T entity;  
if (lock)  
    entity = (T) template.get(getPersistentClass(), id, LockMode.UPGRADE);  
else  
    entity = (T) template.get(getPersistentClass(), id);  
template.setCacheQueries(true);
return entity;  
}  

@SuppressWarnings("unchecked")  
public List<T> getAll() {  
	List<Object> list = template.findByCriteria(DetachedCriteria.forClass(getPersistentClass()).add(Restrictions.ne("isdeleted", true)));
	template.setCacheQueries(true);
	return toEntityList(list);
}  

 

private List<T> toEntityList(List<Object> list) {
	List<T> tlist = new ArrayList<T>();
	for(Object o:list){
			T h = (T)o;
			tlist.add(h);
		}
	return tlist;
}


@SuppressWarnings("unchecked")  
public void update(T entity) { 
template.setCacheQueries(true);
template.merge(entity);  
}  



public void flush() {  
template.flush();  
}  

public void clear() {  
template.clear();  
}  

public List<T> toList(List<Object> list){
	List<T> listgoods = new ArrayList<T>();
	for(Object o:list){
		T h = (T)o;
		listgoods.add(h);
		}
	return listgoods;
}


public Set<T> toSet(List<Object> list){
	Set<T> listgoods = new TreeSet<T>();
	for(Object o:list){
		T h = (T)o;
		listgoods.add(h);
		}
	return listgoods;
}


public List<T> listPage(Filter n, Integer begin, Integer end){
	if(n!=null){
		return filter(n, begin, end);
	}
	
	Session sess = template.getSessionFactory().openSession();
	String start = "select g from "+ persistentClass.getSimpleName() +" as g where g.isdeleted = 0";
	StringBuilder builder = new StringBuilder(start);
	builder.append(" ORDER BY ");
	builder.append("g."+DEFSORTPROP);
	System.out.println(builder.toString());
	
	Query q = sess.createQuery(builder.toString()).setFirstResult(begin).setMaxResults(end);
	
	return toList(q.list());
}

public List<T> filter(Filter n, Integer firstResult, Integer maxResult){
	System.out.println("Ôèëüðèê "+n.getPropfilters());
	for(Entry<String, Set<FilterNode>> r:n.getPropfilters().entrySet()){
		System.out.println(r.getKey());
		for(FilterNode g: r.getValue()){
			System.out.println(g.getValues());
		}
	}
	
	
	System.out.println(!n.getJoinfilters().isEmpty());
	if(n.getMulticlasstable()!=null && !n.getJoinfilters().isEmpty()){return compositefilter(n, firstResult, maxResult);}
	n.setClassname(persistentClass.getSimpleName());
	CommonUtilsService.castFilterTypes(n);
	int size = 0;
	Map <String, Object> paramvals = new TreeMap<String, Object>();
	for(Entry<String, Set<FilterNode>> entry: n.getPropfilters().entrySet()){	
		for(FilterNode h: entry.getValue()){
			size+=h.getValues().size();
			System.out.println(h.getValues());
		}
	}
	System.out.println("razmer "+size);
	
	String start = "select g from "+ persistentClass.getSimpleName() +" as g where g.isdeleted = 0";
	StringBuilder builder = new StringBuilder(start);

	int count = 1;
	int rcount = 1;
	int outercount = 1;
	boolean last = false;
	String valand = " AND ";
	String rangeand = " AND ";
	if(!n.getPropfilters().isEmpty() && n.getHasnotnullfilters()) builder.append(" and ");
	int entrycount = 1;
	for(Entry<String, Set<FilterNode>> entry: n.getPropfilters().entrySet()){	
	boolean hasrangefilter = false;
	outercount = 1;
	Iterator<FilterNode> itr = entry.getValue().iterator();
	while(itr.hasNext()){
		FilterNode fltr = itr.next();
		System.out.println("outerc "+outercount);
		System.out.println("Ðàçìåð! "+entry.getValue().size()+" outercount" + outercount + " entrycount"+n.getPropfilters().entrySet().size());	
	if(outercount==entry.getValue().size() && entrycount == n.getPropfilters().entrySet().size()) valand = "";last = true;
	System.out.println("RANGEFILTER "+fltr.getIsSet());
	if(fltr.getIsSet()){
	if(fltr.getType().equals(FilterType.RANGE)){
		Object min = fltr.getValues().get(0);
		Object max = fltr.getValues().get(1);
		if(min!=null){
			System.out.println("miiiin");
			paramvals.put("val"+(rcount++), min);
			builder.append("g."+entry.getKey()+" >= "+":val"+(rcount-1));
			if(max!=null)builder.append(" AND ");;
		}
		
		if(max!=null){
			System.out.println("maaax");
			if(last)rangeand = "";
			paramvals.put("val"+(rcount++), max);
			builder.append("g."+entry.getKey()+" <= "+":val"+(rcount-1)+rangeand);
			hasrangefilter = true;
		}

	}
	}
	if(fltr.getType().equals(FilterType.VALUES) && fltr.getIsSet()){
		System.out.println("ÔÈËÜÒÐ ÇÍÀ×ÅÍÈÉ ÀÊÒÈÂÅÍ");
		if(hasrangefilter && !fltr.getValues().isEmpty()){
			System.out.println("hhhhhhheeeeeeyr");
			builder.append(" AND ");
		}
		System.out.println("VAAAALUUUEEESS");
		boolean containsnull=false;
		ListIterator<Object> lst = fltr.getValues().listIterator();
		if(fltr.getValues().isEmpty()){
			return new ArrayList<T>();
		}
		while(lst.hasNext()){
			Object obj = lst.next();
			if(obj==null){containsnull=true;builder.append("g."+entry.getKey()+" = null ");lst.remove();break;}
		}	
		if(containsnull){
			
			if(!fltr.getValues().isEmpty()){
				builder.append(" OR ");	
				builder.append("g."+entry.getKey()+" IN(");
				builder.append(":vals"+rcount+")"+valand);
				paramvals.put("vals"+rcount, fltr.getValues());
				rcount++;
			}
			else{
			builder.append(valand);}	
			fltr.getValues().add(null);
		}
		else{
			builder.append("g."+entry.getKey()+" IN(");
			builder.append(":vals"+rcount+")"+valand);
			paramvals.put("vals"+rcount, fltr.getValues());
			rcount++;
		}
	}
	
	outercount++;
	}	
	entrycount++;
	}
	
	builder.append(" ORDER BY ");
	int t = 1;
	String comma = ", ";
	if(n.getSortprops().isEmpty())n.getSortprops().put(DEFSORTPROP, DEFSORTNAME);
	if(n.getSortprops().size()==2)n.getSortprops().remove(DEFSORTPROP);
	for(Entry<String, String> entr : n.getSortprops().entrySet()){
		
		if(t==n.getSortprops().size()) comma = "";
		builder.append("g."+entr.getKey()+comma);
		t++;
	}

	System.out.println("ÇÀÏÐÎÑ "+builder.toString());
		

	Session sess = template.getSessionFactory().openSession();
	Query q = sess.createQuery(builder.toString());

	for(Entry<String,Object> entr:paramvals.entrySet()){
		if(java.util.Collection.class.isAssignableFrom(entr.getValue().getClass())){
			q.setParameterList(entr.getKey(), (java.util.Collection)entr.getValue());
			System.out.println("Êëþ÷: "+entr.getKey());
			System.out.println("Çíà÷åíèÿ: "+((java.util.Collection)entr.getValue()));
		}
		else{
			System.out.println("Êëþ÷: "+entr.getKey());
			System.out.println("Çíà÷åíèÿ: "+entr.getValue());
			q.setParameter(entr.getKey(),entr.getValue());
		}
		
	}

	q.setFirstResult(firstResult).setMaxResults(maxResult);
	List<Object> list = q.list();

	System.out.println("SIIIIZE "+list.size());	
	
	return toList(list);
}


private List<T> compositefilter(Filter n, Integer firstResult, Integer maxResult) {
	n.setClassname(persistentClass.getSimpleName());
	CommonUtilsService.castFilterTypes(n);
	int size = 0;
	Map <String, Object> paramvals = new TreeMap<String, Object>();
	for(Entry<String, Set<FilterNode>> entry: n.getPropfilters().entrySet()){	
		for(FilterNode h: entry.getValue()){
			size+=h.getValues().size();
			System.out.println(h.getValues());
		}
	}
	System.out.println("razmer "+size);
	
	String start = "select g from "+ persistentClass.getSimpleName() +" as g INNER JOIN g."+n.getJoinfieldname() +" as g1 where g.isdeleted = 0";
	StringBuilder builder = new StringBuilder(start);

	int count = 1;
	int rcount = 1;
	int outercount = 1;
	boolean last = false;
	String valand = " AND ";
	String rangeand = " AND ";
	if(!n.getPropfilters().isEmpty()) builder.append(" and ");
	int entrycount = 1;
	
	
	
	
	for(Entry<String, Set<FilterNode>> entry: n.getPropfilters().entrySet()){	
		
	boolean hasrangefilter = false;
	outercount = 1;
	Iterator<FilterNode> itr = entry.getValue().iterator();
	while(itr.hasNext()){
		FilterNode fltr = itr.next();
		
		System.out.println("outerc "+outercount);
		System.out.println("Ðàçìåð! "+entry.getValue().size()+" outercount" + outercount + " entrycount"+n.getPropfilters().entrySet().size());	
	if(outercount==entry.getValue().size() && entrycount == n.getPropfilters().entrySet().size()) valand = "";last = true;
	if(fltr.getType().equals(FilterType.RANGE)){
		Object min = fltr.getValues().get(0);
		Object max = fltr.getValues().get(1);
		if(min!=null){
			System.out.println("miiiin");
			paramvals.put("val"+(rcount++), min);
			builder.append("g."+entry.getKey()+" >= "+":val"+(rcount-1));
			if(max!=null)builder.append(" AND ");;
		}
		
		if(max!=null){
			System.out.println("maaax");
			if(last)rangeand = "";
			paramvals.put("val"+(rcount++), max);
			builder.append("g."+entry.getKey()+" <= "+":val"+(rcount-1)+rangeand);
			hasrangefilter = true;
		}

	}
	if(fltr.getType().equals(FilterType.VALUES)){
		if(hasrangefilter && !fltr.getValues().isEmpty()){
			builder.append(" AND ");
		}
		System.out.println("VAAAALUUUEEESS");
		boolean containsnull=false;
		ListIterator<Object> lst = fltr.getValues().listIterator();
		if(fltr.getValues().isEmpty()){
			return new ArrayList<T>();
		}
		while(lst.hasNext()){
			Object obj = lst.next();
			if(obj==null){containsnull=true;builder.append("g."+entry.getKey()+" = null ");lst.remove();break;}
		}	
		if(containsnull){
			
			if(!fltr.getValues().isEmpty()){
				builder.append(" OR ");	
				builder.append("g."+entry.getKey()+" IN(");
				builder.append(":vals"+rcount+")"+valand);
				paramvals.put("vals"+rcount, fltr.getValues());
				rcount++;
			}
			else{
			builder.append(valand);}	
			fltr.getValues().add(null);
		}
		else{
			builder.append("g."+entry.getKey()+" IN(");
			builder.append(":vals"+rcount+")"+valand);
			paramvals.put("vals"+rcount, fltr.getValues());
			rcount++;
		}
	}
	outercount++;
	}	
	entrycount++;
	}
	
	
	
	last = false;
	valand = " AND ";
	rangeand = " AND ";
	if(!n.getJoinfilters().isEmpty()) builder.append(" and ");
	outercount = 1;
	
	
	for(Entry<String, Set<FilterNode>> entry: n.getJoinfilters().entrySet()){	
		
	boolean hasrangefilter = false;
	outercount = 1;
	Iterator<FilterNode> itr = entry.getValue().iterator();
	while(itr.hasNext()){
		FilterNode fltr = itr.next();
		
		System.out.println("outerc "+outercount);
		System.out.println("Ðàçìåð! "+entry.getValue().size()+" outercount" + outercount + " entrycount"+n.getPropfilters().entrySet().size());	
	if(outercount==entry.getValue().size() && entrycount == n.getJoinfilters().entrySet().size()+n.getPropfilters().entrySet().size()) valand = "";last = true;
	if(fltr.getType().equals(FilterType.RANGE)){
		Object min = fltr.getValues().get(0);
		Object max = fltr.getValues().get(1);
		if(min!=null){
			System.out.println("miiiin");
			paramvals.put("val"+(rcount++), min);
			builder.append("g1."+entry.getKey()+" >= "+":val"+(rcount-1));
			if(max!=null)builder.append(" AND ");;
		}
		
		if(max!=null){
			System.out.println("maaax");
			if(last)rangeand = "";
			paramvals.put("val"+(rcount++), max);
			builder.append("g1."+entry.getKey()+" <= "+":val"+(rcount-1)+rangeand);
			hasrangefilter = true;
		}

	}
	if(fltr.getType().equals(FilterType.VALUES)){
		if(hasrangefilter && !fltr.getValues().isEmpty()){
			builder.append(" AND ");
		}
		System.out.println("VAAAALUUUEEESS");
		boolean containsnull=false;
		ListIterator<Object> lst = fltr.getValues().listIterator();
		if(fltr.getValues().isEmpty()){
			return new ArrayList<T>();
		}
		while(lst.hasNext()){
			Object obj = lst.next();
			if(obj==null){containsnull=true;builder.append("g1."+entry.getKey()+" = null ");lst.remove();break;}
		}	
		if(containsnull){
			
			if(!fltr.getValues().isEmpty()){
				builder.append(" OR ");	
				builder.append("g1."+entry.getKey()+" IN(");
				builder.append(":vals"+rcount+")"+valand);
				paramvals.put("vals"+rcount, fltr.getValues());
				rcount++;
			}
			else{
			builder.append(valand);}	
			fltr.getValues().add(null);
		}
		else{
			builder.append("g1."+entry.getKey()+" IN(");
			builder.append(":vals"+rcount+")"+valand);
			paramvals.put("vals"+rcount, fltr.getValues());
			rcount++;
		}
	}
	outercount++;
	}	
	entrycount++;
	}
	
	
	
	
	builder.append(" ORDER BY ");
	int t = 1;
	String comma = ", ";
	if(n.getSortprops().isEmpty())n.getSortprops().put(DEFSORTPROP, DEFSORTNAME);
	if(n.getSortprops().size()==2)n.getSortprops().remove(DEFSORTPROP);
	for(Entry<String, String> entr : n.getSortprops().entrySet()){
		
		if(t==n.getSortprops().size()) comma = "";
		builder.append("g1."+entr.getKey()+comma);
		t++;
	}

	System.out.println(builder.toString());
		

	Session sess = template.getSessionFactory().openSession();
	Query q = sess.createQuery(builder.toString());

	for(Entry<String,Object> entr:paramvals.entrySet()){
		if(java.util.Collection.class.isAssignableFrom(entr.getValue().getClass())){
			q.setParameterList(entr.getKey(), (java.util.Collection)entr.getValue());
			System.out.println("Êëþ÷: "+entr.getKey());
			System.out.println("Çíà÷åíèÿ: "+((java.util.Collection)entr.getValue()));
		}
		else{
			System.out.println("Êëþ÷: "+entr.getKey());
			System.out.println("Çíà÷åíèÿ: "+entr.getValue());
			q.setParameter(entr.getKey(),entr.getValue());
		}
		
	}

	q.setFirstResult(firstResult).setMaxResults(maxResult);
	List<Object> list = q.list();

	System.out.println("SIIIIZE "+list.size());	
	
	return toList(list);
}


public List<Object> getValues(Filter n, Class currentclass, Field field){
	Session sess = template.getSessionFactory().openSession();
	List<Object> vals = new ArrayList<Object>();
	
	String classname = currentclass.getSimpleName();
	String propname = field.getName();
	StringBuilder builder = new StringBuilder("select distinct g.");
	
	builder.append(propname);
	builder.append(" from ");
	builder.append(classname);
	builder.append(" as g");
	if(n!=null){
		
	if(n.getCompositefilter() != null ){
		if(n.getCompositefilter()==true && n.getColadmintype()!=n.getClassname()){
		builder.append(" inner join g."+n.getJoincolectionname()+" as g1");}
	}
	}
	builder.append(" where g.isdeleted = 0 ");
	if(n!=null){
		if(!n.getCompositefilter() ||n.getColadmintype()==n.getClassname()){
		builder.append(" and g IN(:vals)");
		}
		else{
			builder.append(" and g1 IN(:vals)");
		}
		System.out.println(builder.toString());
		Integer size = getCount();
		
		List<T> goods = filter(n, 0, size);
			
		System.out.println("COMPOSITE FILTER "+n.getCompositefilter());
		Query q2 = sess.createQuery(builder.toString()).setParameterList("vals", goods);
		if(goods.isEmpty()) return vals;
		System.out.println(builder.toString());
		System.out.println("ÊËÀÑÑÑÑÑ ÒÓÒ  "+goods.get(0).getClass().getSimpleName());
		vals = q2.list();
		
		
	}
	else{
	System.out.println(builder.toString());
	
	vals = template.find(builder.toString());
	}
	System.out.println("ÇÍÀ×ÅÍÈß ÔÈËÜÒÐÈÊÀ:");
	for(Object f:vals){
		if(f==null){System.out.println("null");}
		else{
		System.out.println(f.toString());}
	}
	return vals;
}

public Integer getCount(){
	Session sess = template.getSessionFactory().openSession();
	Query q1 =  sess.createQuery("select count(g.id) from "+ persistentClass.getSimpleName()+ " as g where g.isdeleted = 0");
	return((Long)q1.list().get(0)).intValue();
}

@Override
public void changeMultiValues(List<? extends Serializable> ids, Map<String, Object> propvals) {
	StringBuilder builder = new StringBuilder("update "+persistentClass.getSimpleName()+" set ");
	int count=1;
	String comma = ", ";
	String buck = "";
	Map<String, Object> paramlist = new TreeMap<String, Object>();
	for(Entry<String, Object> entr:propvals.entrySet()){
			if(count == propvals.entrySet().size())comma="";
			Class valclazz = entr.getValue().getClass();
			boolean isPrimitiveOrWrapped = ClassUtils.isPrimitiveOrWrapper(valclazz);

			Object val = entr.getValue();
			if(valclazz.equals(String.class)){
				
				val = (String)entr.getValue();
				val = (val.equals("true"))?"1":(val.equals("false"))?"0":"'"+val+"'";

				
				
			}
			if(!isPrimitiveOrWrapped && !valclazz.equals(String.class)){
				builder.append(entr.getKey()+" = :val"+count+comma);
				paramlist.put("val"+count, val);
			}
			else{
				Object strval = val.toString();
				if(valclazz.equals(Boolean.class)){System.out.println("Boolean"); strval = ((Boolean)val)?1:0;}
				builder.append(entr.getKey()+" = "+strval+comma);
				}
			count++;
		
	}
	
	builder.append(" where id IN(:vals)");
	System.out.println(builder.toString());
	Session sess = template.getSessionFactory().openSession();
	Query q = sess.createQuery(builder.toString());
	q.setParameterList("vals", ids);
	for(Entry<String, Object> entry:paramlist.entrySet()){
		q.setParameter(entry.getKey(), entry.getValue());
	}
	q.executeUpdate();
	
}











}  
