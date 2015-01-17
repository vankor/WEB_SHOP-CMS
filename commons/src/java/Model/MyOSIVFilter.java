package Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
 

public class MyOSIVFilter extends OpenSessionInViewFilter{
public void closeSession(Session session, SessionFactory sessionFactory){
session.flush();
super.closeSession(session,sessionFactory);
}


}

