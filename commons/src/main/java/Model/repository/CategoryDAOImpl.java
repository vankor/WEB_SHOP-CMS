package Model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import Model.others.ParamContainer;
import Model.dto_beans.ParamFilterBean;
import Model.dto_beans.ValueBean;
import Model.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Model.entity.Category.OrderingRules;

@Repository
public class CategoryDAOImpl extends GenericHibTemplateDAOImpl<Category, Integer> implements CategoryDAO {

	@Autowired
	private HibernateTemplate template;

	@Autowired
	private ValueDAO valDAO;



	@Transactional
	public List<Category> getRootCategories() {

		return listCategory(null);
	}


	@Override
	@Transactional
	public Map<Category, Long> getAllActionCategories() {
		List<Object> list = template.find("select c, count(distinct a) from Action as a inner join a.actiongoods as g  inner join g.category as c group by c");
		Map<Category, Long> actioncategs = new TreeMap<Category, Long>();
		for(Object f:list){
			int i = 0;
			Category categ = null;
			Long count = new Long(0);
			Object[]g = (Object[])f;
			for(Object h:g){
				if(i==0){
					categ = (Category) h;
				}
				else{
					count = (Long) h;
				}
				i++;
				System.out.println(h.getClass().getCanonicalName());
				System.out.println(h);
			}
			actioncategs.put(categ, count);

		}
		return actioncategs;
	}

	@Transactional
	public List<Category> listCategory(Category cat) {
		List<Object> list = new ArrayList();
		if(cat!=null){
		list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
				add(Restrictions.eq("parent_category", cat)));
		}
		else{
			list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
					add(Restrictions.isNull("parent_category")));
		}
		template.setCacheQueries(true);



		return toList(list);
	}

	@Transactional
	public List<Category> listCategoryById(Integer id) {
		List<Object> list = new ArrayList();

		if(id != null && id != 0){
			Category cat = getById(id, false);
			list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
			add(Restrictions.eq("parent_category", cat)));
		}
		else{
			list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
					add(Restrictions.isNull("parent_category")));
		}

		template.setCacheQueries(true);



		return toList(list);
	}

	@Transactional
	public Category getCategoryByAlias(String alias) {
		List<Category> listcat = new ArrayList<Category>();
		List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Category.class).
				add(Restrictions.eq("alias", alias)));

		Category cat = (Category)list.get(0);
		return cat;
	}

	@Transactional
	public Double getMaxPrice(Category cat) {
		List<Object> list = template.find("select max(g.price) from GoodItem as g INNER JOIN g.category as c where c.id = ?", cat.getId());
		return (Double)list.get(0);
	}

	@Transactional
	public Double getMinPrice(Category cat) {
		List<Object> list = template.find("select min(g.price) from GoodItem as g INNER JOIN g.category as c where c.id = ?", cat.getId());
		return (Double)list.get(0);
	}

	@Transactional
	public List<GoodItem> getSortedGoods(String criteria) {

		return null;
	}



	@Transactional
	public Integer getLastId() {
		Integer maxid=0;
		List<Object> list = template.find("select max(g.id) from Category as g");
		if(!list.isEmpty()){
			maxid = (Integer)list.get(0);
		}
		return maxid;
	}

	@Override
	public List<GoodItem> listGoodsPage(Category cat, ParamFilterBean n, GoodItem.OrderingRules rule, Integer begin, Integer end) {
		if(n!=null)
		{
			return propertyFilter(n, rule, begin, end);
		}
		Session sess = template.getSessionFactory().openSession();
		System.out.println("Õ‡˜‡ÎÓ: "+begin+" ÓÌÂˆ: "+end);
		String start = "select g from GoodItem as g INNER JOIN g.category as c ";
		StringBuilder builder = new StringBuilder(start);





		switch(rule)
		{
		case PRICEUP: builder.append("where c.id = :catid ORDER BY g.price"); break;
		case PRICEDOWN: builder.append("where c.id = :catid ORDER BY g.price DESC"); break;
		case RATING: builder.append("INNER JOIN g.votes as gv where c.id = :catid order by sum(gv.id) DESC"); break;
		case NAME: builder.append("where c.id = :catid order by g.name"); break;
		case POPULARITY: builder.append("INNER JOIN g.orderrows as gv where c.id = :catid ORDER BY sum(gv.goodcount)"); break;
		}

		System.out.println(builder.toString());

		Query q = sess.createQuery(builder.toString()).setFirstResult(begin).setMaxResults(end);
		q.setInteger("catid", cat.getId());
		for(Object f: q.list()){
			System.out.println(((GoodItem)f).getName());
		}
		return toGoodList(q.list());
	}



	private List<GoodItem> toGoodList(List<Object> list) {
		List<GoodItem> goodset = new ArrayList<GoodItem>();
		for(Object g : list){
			goodset.add((GoodItem)g);
		}
		return goodset;
	}

	@Transactional
	public List<GoodItem> propertyFilter(ParamFilterBean n, GoodItem.OrderingRules rule, Integer firstResult, Integer maxResult) {
		String start = "select distinct g from GoodItem as g INNER JOIN g.vals as v ";
		Map <String, List<Integer>> paramvals = new TreeMap<String, List<Integer>>();
		Category cat = new Category();
		if(n.getCatid()!=null){
			cat = getById(n.getCatid(), false);
		}
		Query query = constructFilterQuery(start, n, paramvals, cat, rule);
		System.out.println(query.getQueryString());

		query.setFirstResult(firstResult).setMaxResults(maxResult);
		List<Object> list = query.list();

		return toGoodList(list);
	}



	@Transactional
	public Long propertyFilterCount(ParamFilterBean n, GoodItem.OrderingRules rule) {
		Map <String, List<Integer>> paramvals = new TreeMap<String, List<Integer>>();
		String start = "select count(distinct g) from GoodItem as g INNER JOIN g.vals as v ";
		Category cat = new Category();
		if(n.getCatid()!=null){
			cat = getById(n.getCatid(), false);
		}
		Query q = constructFilterQuery(start, n, paramvals, cat, rule);
		String textquery = q.getQueryString();
		System.out.println(textquery);


		List<Object> list = q.list();

		return (!list.isEmpty())?(Long)list.get(0):null;
	}



	private String constructFilterQueryText(String start, ParamFilterBean n, Map <String, List<Integer>> paramvals, Category cat, GoodItem.OrderingRules rule){

		StringBuilder builder = new StringBuilder(start);
		if(rule!=null){
		switch(rule)
		{
		case RATING: builder.append("INNER JOIN g.votes as gv"); break;
		case POPULARITY: builder.append("INNER JOIN g.orderrows as gv"); break;
		}
		}
		builder.append(" where g.price >= :pricelow and g.price <= :priceupper and ");
		if(n.getCatid()!=null){
			builder.append(" g.category =:category and ");

		}
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


			for(ValueBean valbean: bean.getVals()){
				System.out.println("—‚ÓÈÒÚ‚Ó 1 :"+valbean.getValue());
			}

			coma = ", ";
			builder.append("v.id IN (:valueList"+outercount+")");
			if(bean.getVals().isEmpty() || bean.getVals() ==null){}


			else{
			List<Integer>	valsid = new ArrayList<Integer>();
			for(ValueBean valbean: bean.getVals()){
				if(valbean.getValue()!=null && valbean.getIschecked() != false){
				Value val = valDAO.getValueById(valbean.getId());
				valsid.add(valbean.getId());
				}
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
		if(rule!=null){
		switch(rule)
		{
		case PRICEUP: builder.append(" ORDER BY g.price"); break;
		case PRICEDOWN: builder.append(" ORDER BY g.price DESC"); break;
		case RATING: builder.append(" ORDER BY sum(gv.id) DESC"); break;
		case NAME: builder.append(" ORDER BY g.name"); break;
		case POPULARITY: builder.append(" ORDER BY sum(gv.goodcount)"); break;
		}
		}

		for(Entry<String, List<Integer>> entr: paramvals.entrySet()){
			System.out.println(""+entr.getKey());
			for(Integer j: entr.getValue()){
				System.out.println(""+j);
			}
		}

		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery(builder.toString()).setDouble("pricelow", n.getPricefilter().get(0).getLowerlimit()).setDouble("priceupper", n.getPricefilter().get(0).getUpperlimit());
		if(n.getCatid()!=null){
			q.setParameter("category", cat);
		}
		for(Entry<String, List<Integer>> entr: paramvals.entrySet()){
			q.setParameterList(entr.getKey(), entr.getValue());
		}

		return builder.toString();
	}


	private Query constructFilterQuery(String start, ParamFilterBean n, Map <String, List<Integer>> paramvals, Category cat, GoodItem.OrderingRules rule){
		String qtext = constructFilterQueryText(start, n, paramvals, cat, rule);
		Session sess = template.getSessionFactory().openSession();
		Query q = sess.createQuery(qtext).setDouble("pricelow", n.getPricefilter().get(0).getLowerlimit()).setDouble("priceupper", n.getPricefilter().get(0).getUpperlimit());
		if(n.getCatid()!=null){
			q.setParameter("category", cat);
		}
		for(Entry<String, List<Integer>> entr: paramvals.entrySet()){
			q.setParameterList(entr.getKey(), entr.getValue());
		}



		return q;
	}



	private Set<GoodItem> toGoodSet(List list) {
		Set<GoodItem> goodset = new TreeSet<GoodItem>();
		for(Object g : list){
			goodset.add((GoodItem)g);
		}
		return goodset;
	}

	@Transactional
	public Integer getGoodsCount(Category cat) {
	Session sess = template.getSessionFactory().openSession();
	int count = ((Long)sess.createQuery("select count(g) from GoodItem as g INNER JOIN g.category as c where c = :catid").setInteger("catid", cat.getId()).uniqueResult()).intValue();
	return count;
	}

	@Transactional
	public List<Category> listPage(OrderingRules rule, Integer begin,
			Integer end) {
		Session sess = template.getSessionFactory().openSession();
		System.out.println("Õ‡˜‡ÎÓ: "+begin+" ÓÌÂˆ: "+end);
		String start = "select g from Category as g ";
		StringBuilder builder = new StringBuilder(start);




		if(rule!=null){
		switch(rule)
		{
		case NAME: builder.append("ORDER BY g.name"); break;
		case GOODSCOUNT: builder.append("INNER JOIN g.goods as c ORDER BY count(c.id) DESC"); break;
		case PROPSCOUNT: builder.append("INNER JOIN g.props as c ORDER BY count(c.id) DESC"); break;
		case NEWSCOUNT: builder.append("INNER JOIN g.catnews as c ORDER BY count(c.id) DESC"); break;

		}
		}
		System.out.println(builder.toString());

		Query q = sess.createQuery(builder.toString()).setFirstResult(begin).setMaxResults(end);
		for(Object f: q.list()){
			System.out.println(((News)f).getTitle());
		}
		return toList(q.list());
	}


	@Transactional
	public Set<Category> getTopCategories() {
		List topcats = template.findByCriteria(DetachedCriteria.forClass(Category.class).add(Restrictions.eq("istop", true)));
		return toSet(topcats);
	}


	@Override
	public Map<Category, Long> getAllNewsTypeCategories(NewsType nwstp) {
		List<Object> list = template.findByNamedParam("select c, count(distinct a) from News as a inner join a.type as t  inner join a.category as c where t = :nwstp group by c","nwstp",nwstp);

		Map<Category, Long> actioncategs = new TreeMap<Category, Long>();
		for(Object f:list){
			int i = 0;
			Category categ = null;
			Long count = new Long(0);
			Object[]g = (Object[])f;
			for(Object h:g){
				if(i==0){
					categ = (Category) h;
				}
				else{
					count = (Long) h;
				}
				i++;
				System.out.println(h.getClass().getCanonicalName());
				System.out.println(h);
			}
			actioncategs.put(categ, count);

		}
		return actioncategs;

	}


	@Override
	public Category getCategiryByUrl(String url) {
		List<Object> list = template.findByNamedParam("select c from Category as c inner join c.page as p  where p.fullurl like :url","url",url);
		return (Category)list.get(0);
	}


	@Override
	public Long getUnderFilterGoodsCount(ParamFilterBean currentfilter, GoodItem.OrderingRules ordering, Integer propid, ValueBean val) throws CloneNotSupportedException {

		ParamFilterBean potentialfilter = currentfilter.clone();




		for(ParamContainer cont:potentialfilter.getParams()){
			System.out.println("¿…ƒ»’» —¬Œ…—“¬ "+cont.getPropid()+"       "+propid);
			if(cont.getPropid().equals(propid)){
				Value vval = valDAO.getById(val.getId(), false);
				System.out.println("Õ”À‹ "+val.getValue());
				val.setValue(vval.getValue());
				cont.addParam(val);
				System.out.println("¡€¬ÿ»… Õ”À‹ "+val.getValue());
			}
		}
		Map <String, List<Integer>> paramvals = new TreeMap<String, List<Integer>>();
		Query q = null;


			String start = "select count(distinct g) from GoodItem as g INNER JOIN g.vals as v ";
			Category cat = new Category();
			if(potentialfilter.getCatid()!=null){
				cat = getById(potentialfilter.getCatid(), false);
			}
			q = constructFilterQuery(start, potentialfilter, paramvals, cat, ordering);



			String textquery = q.getQueryString();
			System.out.println(textquery);
			List list = q.list();







		return (!list.isEmpty())?(Long)list.get(0):null;
	}








}
