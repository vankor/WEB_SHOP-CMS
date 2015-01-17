package Model.repository;

import java.util.ArrayList;
import java.util.List;

import Model.entity.News;
import Model.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserDAOImpl extends GenericHibTemplateDAOImpl<User, Integer> implements UserDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    private HibernateTemplate template;
	
	@Autowired
    private AdressDAO adrDAO;
	
	@Transactional
	public void addUser(User g) {
		template.save(g);
	}

	@Transactional
	public void deleteUser(User g) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<User> getAllUsers() {
		Session s = null;
		List k = new ArrayList();
		s = sessionFactory.getCurrentSession();
		k = s.createCriteria(User.class).list();
		return k;
	}

	@Transactional
	public User getUserById(int id) {
		User u = (User) template.get(User.class, id);
//		template.initialize(u.getBuck());
//		template.initialize(u.getComments());
//		template.initialize(u.getOrders());
		return u;
		
	}

	@Transactional
	public List<User> getUsersByDescrition(String d) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Transactional
//	public List<User> getFiltered(Filter n) {
		// TODO Auto-generated method stub
//		return null;
//	}

	
	@Transactional
	public void update(User g) {
		
		template.merge(g);
	}

	@Transactional
	public User checkUser(String login, String pass) {
		
		List<Object> g = template.findByCriteria(DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("pass", pass)));
		
		if(g != null && !g.isEmpty())
	    {
	        return (User)g.get(0);
	    }
	    else return null;
	}

	@Transactional
	public List<User> getUsersByEmail(String d) {
		List<Object> g = template.findByCriteria(DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("username", d)));
		return toUserList(g);
	}
	
	
	private List<User> toUserList(List<Object> list){
		List<User> listusers = new ArrayList<User>();
		for(Object o:list){
				User h = (User)o;
				listusers .add(h);
				
			}
		return listusers ;
	}

	@Transactional
	public List<User> getUsersByPassword(String pass) {
		Md5PasswordEncoder h = new Md5PasswordEncoder();
		List<Object> g = template.findByCriteria(DetachedCriteria.forClass(User.class).add(Restrictions.eq("password", h.encodePassword(pass, null))));
		
		return toUserList(g);
	}

	@Override
	@Transactional
	public List<User> listPage(User.OrderingRules rule, Integer begin, Integer end) {
		Session sess = template.getSessionFactory().openSession();
		System.out.println("Начало: "+begin+"Конец: "+end);
		String start = "select g from User as g ";
		StringBuilder builder = new StringBuilder(start);
		
		
			
//		builder.append("where c.id = :catid ");
		
		switch(rule)
		{
		case NAME: builder.append("ORDER BY g.name"); break;
		case USERNAME: builder.append("ORDER BY g.username"); break;
		case COMMENTSCOUNT: builder.append("INNER JOIN g.comments as c ORDER BY count(c.id) DESC"); break;
		case BIRTHDATE: builder.append("ORDER BY g.birthdate"); break;
		case NICK: builder.append("ORDER BY g.nick"); break;
		case ORDERCOUNT: builder.append("INNER JOIN g.orders as c ORDER BY count(c.id) DESC"); break;
		}
		
		System.out.println(builder.toString());
		
		Query q = sess.createQuery(builder.toString()).setFirstResult(begin).setMaxResults(end);
		for(Object f: q.list()){
			System.out.println(((News)f).getTitle());
		}
		return toList(q.list());
	}

	

	
		
	

}
