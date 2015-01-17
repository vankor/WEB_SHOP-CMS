package Model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import Model.others.ParamContainer;
import Model.dto_beans.ParamFilterBean;
import Model.dto_beans.ValueBean;
import Model.entity.Category;
import Model.entity.GoodItem;
import Model.entity.Property;
import Model.entity.Value;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Component("genericHibTemplateDAOImpl")
public class GoodDAOImpl extends GenericHibTemplateDAOImpl<GoodItem, Integer> implements GoodDAO {

	@Autowired
    private HibernateTemplate template;
	
	@Autowired
    private ValueDAO valDAO;
	
	
    private static final String DEFSORTPROP = "name";
    private static final String DEFSORTNAME = "Наименование";


	@Transactional
	public List<GoodItem> getGoodsByDescrition(String d) {
		Session s = null;
		List k = new ArrayList();

		k = s.createCriteria(GoodItem.class).add(Restrictions.ilike("descripton", d, MatchMode.ANYWHERE)).list();
		template.setCacheQueries(true);
		return k;
	}

	
	
	@Transactional
	public List<GoodItem> propertyFilter(ParamFilterBean n, Integer firstResult, Integer maxResult) {

		Map <String, List<Integer>> paramvals = new TreeMap<String, List<Integer>>();
		
		String start = "select distinct g from GoodItem as g INNER JOIN g.vals as v where ";
		StringBuilder builder = new StringBuilder(start);
		
		builder.append("g.price >= :pricelow and g.price <= :priceupper and ");
		
		int outercount = 1;

		StringBuilder subquery = new StringBuilder(" AND g IN (select g from Value as v INNER JOIN v.goods as g where ");
		String coma = "";
		StringBuilder end = new StringBuilder();
		
		for(int j =0; j<n.getParams().size()-1; j ++){
			end.append(")");
		}
		
		for(ParamContainer bean: n.getParams()){
			boolean islast = (outercount == n.getParams().size());
			ListIterator ltr = bean.getVals().listIterator();
			while(ltr.hasNext()){
				ValueBean vval = (ValueBean)ltr.next();
			if(vval.getValue() ==null){
				ltr.remove();
			}
			}
			
			for(ValueBean valbean: bean.getVals()){
				System.out.println("Свойство 1 :"+valbean.getValue());
			}
			
			coma = ", ";
			builder.append("v.id IN (:valueList"+outercount+")");
			if(bean.getVals().isEmpty() || bean.getVals() ==null){}
			
			
			else{	
			List<Integer>	valsid = new ArrayList<Integer>();
		for(ValueBean valbean: bean.getVals()){
				Value val = valDAO.getValueById(valbean.getId());
				valsid.add(valbean.getId());
			}
		
			paramvals.put("valueList"+outercount, valsid);
			}
			if(!islast){
			builder.append(subquery.toString());

			}
			else{
				builder.append(end);
			}

		outercount++;
		}	
	
		
		for(Entry<String, List<Integer>> entr: paramvals.entrySet()){
			System.out.println(""+entr.getKey());
			for(Integer j: entr.getValue()){
				System.out.println(""+j);
			}
		}
		
		
		System.out.println(builder.toString());
			
	
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery(builder.toString()).setDouble("pricelow", n.getPricefilter().get(0).getLowerlimit()).setDouble("priceupper", n.getPricefilter().get(0).getUpperlimit());
		for(Entry<String, List<Integer>> entr: paramvals.entrySet()){
			q.setParameterList(entr.getKey(), entr.getValue());
		}
		q.setFirstResult(firstResult).setMaxResults(maxResult);
		List<Object> list = q.list();

			
		
		return toList(list);
	}
	
	
	
	

	@Transactional
	public List<GoodItem> prropertyFilter(ParamFilterBean n) {

		
		
		
		List<String> params = new ArrayList<String>();
		List<Object> vals = new ArrayList<Object>();
		List<GoodItem> listgoods = new ArrayList<GoodItem>();
		String start = "select distinct g from GoodItem as g INNER JOIN g.vals as v where ";
		StringBuilder builder = new StringBuilder(start);
		
		builder.append("g.price >= :pricelow and g.price <= :priceupper and ");
		params.add("pricelow");
		vals.add(n.getPricefilter().get(0).getLowerlimit());
		params.add("priceupper");
		vals.add(n.getPricefilter().get(0).getUpperlimit());
		Property prop;
		Integer propid;
		int i;
		int count = 1;
		int outercount = 1;
		
		
		

		StringBuilder subquery = new StringBuilder(" AND g IN (select g from Value as v INNER JOIN v.goods as g where ");
		String coma = "";
		StringBuilder end = new StringBuilder();
		
		for(int j =0; j<n.getParams().size()-1; j ++){
			end.append(")");
		}
		
		for(ParamContainer bean: n.getParams()){
			boolean islast = (outercount == n.getParams().size());
			ListIterator ltr = bean.getVals().listIterator();
			while(ltr.hasNext()){
				ValueBean vval = (ValueBean)ltr.next();
			if(vval.getValue() ==null){
				ltr.remove();
			}
			}
			
			for(ValueBean valbean: bean.getVals()){
				System.out.println("Свойство 1 :"+valbean.getValue());
			}
			i=1;
			coma = ", ";
			propid = bean.getPropid();

			builder.append("v.id IN (");
			if(bean.getVals().isEmpty() || bean.getVals() ==null){builder.append(":value"+count+")");
			params.add("value"+count); vals.add(null); count++;}
			
			
			else{	
		for(ValueBean valbean: bean.getVals()){
			
				Value val = valDAO.getValueById(valbean.getId());
				for(GoodItem good:val.getGoods()){
					System.out.println(good.getName());
				}
				if(i == bean.getVals().size()){coma = ") ";}
				System.out.println(outercount);

				if(islast && i==bean.getVals().size()){coma = ")";}
				builder.append(":value"+count+coma);
				params.add("value"+count);
				
				vals.add(val.getId());
			
				i++; count++;
			}
			
			}
			if(!islast){
			builder.append(subquery.toString());

			}
			else{
				builder.append(end);
			}

		
		outercount++;
		}	
		String [] param_names = params.toArray(new String[params.size()]);
		Object [] values = vals.toArray();
		
		for(i = 0; i< param_names.length; i++){
			System.out.println(""+param_names[i]+"     "+values[i]);
		}
		
		System.out.println(builder.toString());
		for(i = 0; i< param_names.length; i++){
			if(values[i].getClass().equals(Value.class)){
				System.out.println(""+param_names[i]+"     "+((Value)values[i]).getId());
			}
			
		}
		
		List<Object> list = template.findByNamedParam(builder.toString(),param_names,values);
		template.setCacheQueries(true);
			
		
		return toList(list);
	}

	@Transactional
	public List<GoodItem> goodSearch(String value){
		System.out.println(value);
		List<Object> list = new ArrayList<Object>();
		
		if(value !=null && !value.isEmpty()){
		list = template.findByNamedParam("select distinct g from GoodItem as g INNER JOIN g.vals as v INNER JOIN v.prop as p where g.name like :val or g.description like :val or v.value like :val or p.name like :val","val",value+'%');
		
		}     

		
		return toList(list);
	}
	
	@Transactional
	public List<GoodItem> getTopGoods() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(GoodItem.class).add(Restrictions.eq("isTopGood", true)));
		template.setCacheQueries(true);
		return toList(list);
	}
	
	@Transactional
	public List<GoodItem> getSeasonGoods() {
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(GoodItem.class).add(Restrictions.eq("isSeasonGood", true)));
		template.setCacheQueries(true);
		return toList(list);
	}

	
	@Transactional
	public Integer getLastId() {
		List<Object> list = template.find("select max(g.id) from GoodItem as g"); 
		template.setCacheQueries(true);
		return (Integer)list.get(0);
		
	}


	@Transactional
	public Set<GoodItem> getSalesLeaders(Integer count) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select g from GoodItem as g inner join g.orderrows as ordrow where ordrow IN (select o from OrderRow as o group by o.good order by count(o.good))").setMaxResults(count);
		List<Object> list = q.list();
		sess.close();
		for(Object d: list){
		System.out.println("Товар лидер:    "+((GoodItem)d).getName());
		}
		return toSet(list);
	}
	
	
	@Transactional
	public Set<GoodItem> getLastLoaded(Integer count) {
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery("select g from GoodItem as g order by g.creation_date DESC)").setMaxResults(count);
		List<Object> list = q.list();
		sess.close();
		for(Object d: list){
		System.out.println("Последние добавленные:    "+((GoodItem)d).getName());
		}
		return toSet(list);
	}



	@Transactional
	public Integer getCount(){
		Session sess = template.getSessionFactory().openSession();
		Query q1 =  sess.createQuery("select count(g.id) from GoodItem as g");
		return((Long)q1.list().get(0)).intValue();
		
	}
	
	@Transactional
	public Set<GoodItem> getTopGoods(Category cat) {
		List topgoods = template.findByNamedParam("select g from GoodItem as g inner join g.category as c where c like :cat and g.isTopGood = true", "cat", cat);
		return toSet(topgoods);
	}


	@Transactional
	public GoodItem getGoodItemByUrl(String url) {
		List<Object> list = template.findByNamedParam("select g from GoodItem as g inner join g.page as p  where p.fullurl like :url","url",url);
		return (GoodItem)list.get(0);
	}



	@Override
	@Transactional
	public List<GoodItem> searchGood(String val, Integer begin, Integer end) {
		
		List<Object> list = new ArrayList<Object>();
		
		if(val !=null && !val.isEmpty()){
			Session sess = template.getSessionFactory().openSession();
			System.out.println("ffffff     select distinct g from GoodItem as g INNER JOIN g.vals as v INNER JOIN v.prop as p where g.name like :val or g.description like "+val+" or v.value like "+val+" or p.name like "+val);
			Query query = sess.createQuery("select distinct g from GoodItem as g INNER JOIN g.vals as v INNER JOIN v.prop as p where g.name like :val or g.description like :val or v.value like :val or p.name like :val");
			query.setString("val", "%"+val+"%");
			query.setFirstResult(begin).setMaxResults(end);
			list = query.list();
			
		}   
		
		return toList(list);
		
	}


	

	
		
	}



	



