package Model.repository;

import java.util.List;
import java.util.Set;

import Model.AdressType;
import Model.entity.Adress;
import Model.entity.Town;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdressDAOImpl extends GenericHibTemplateDAOImpl<Adress, Integer> implements AdressDAO {

	@Autowired
	private HibernateTemplate template;


	@Override
	public Set<Adress> getAdressByType(AdressType f) {
		List <Object> adresses = template.findByCriteria(DetachedCriteria.forClass(Adress.class).add(Restrictions.eq("adresstype", f)));
		
		return toSet(adresses);
	}

	@Override
	public Adress getAdressByParams(Town town, String street_type,
									String street_name, String house_num, String room_num) {
		String[] parnames = new String[5];
		Object[] parvals = new Object[5];
		parnames[0]="town_id";
		parnames[1]="street_type";
		parnames[2]="street_name";
		parnames[3]="house_num";
		parnames[4]="room_num";
		parvals[0]=town.getId();
		parvals[1]=street_type;
		parvals[2]=street_name;
		parvals[3]=house_num;
		parvals[4]=room_num;
		System.out.println("select a from Adress as a inner join a.town as t where t.id = "+town.getId()+" and a.street_type like "+street_type+" and a.street_name like "+street_name+" and a.house_num like "+house_num+" and a.room_num = "+room_num+" ");
		List<Object> list = template.findByNamedParam("select a from Adress as a inner join a.town as t where t.id = :town_id and a.street_type like :street_type and a.street_name like :street_name and a.house_num like :house_num and a.room_num = :room_num", parnames, parvals);
		Adress adr = null;
		if(!list.isEmpty()){
			System.out.println(list.get(0).getClass());
			adr = (Adress)list.get(0);
			for (Object o:list){
				Adress ff = (Adress)o;
			System.out.println(ff);
			}
		}
		return adr;
		}


	



}
