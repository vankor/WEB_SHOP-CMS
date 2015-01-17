package Model.repository;

import java.util.List;
import java.util.Set;

import Model.entity.Action;
import Model.entity.Category;
import Model.entity.News;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ActionDAOImpl extends GenericHibTemplateDAOImpl<Action, Integer> implements ActionDAO {

    @Autowired
    private HibernateTemplate template;


    @Transactional
    public List<Action> getTopActions() {
        List<Object> list = template.findByCriteria(DetachedCriteria.forClass(Action.class).add(Restrictions.eq("istop", true)).add(Restrictions.eq("isactive", true)));
        template.setCacheQueries(true);
        return toList(list);
    }


    @Override
    @Transactional
    public List<Action> listPage(Action.OrderingRules rule, Integer begin,
                                 Integer end) {
        Session sess = template.getSessionFactory().openSession();
        System.out.println("Начало: "+begin+"Конец: "+end);
        String start = "select g from Action as g ";
        StringBuilder builder = new StringBuilder(start);

        switch(rule)
        {
            case NAME: builder.append("ORDER BY g.name"); break;
            case ENDTIME: builder.append("ORDER BY g.endtime DESC"); break;
        }

        System.out.println(builder.toString());

        Query q = sess.createQuery(builder.toString()).setFirstResult(begin).setMaxResults(end);
        for(Object f: q.list()){
            System.out.println(((News)f).getTitle());
        }
        return toList(q.list());
    }

    @Transactional
    public Set<Action> listCategoryActions(Category cat) {
        List topcats = template.findByNamedParam("select a from Action as a inner join a.actiongoods as g inner join g.category as c where c = :cat","cat",cat);
        for(Object b:topcats){
            System.out.println((Action)b);
        }
        return toSet(topcats);
    }





}
