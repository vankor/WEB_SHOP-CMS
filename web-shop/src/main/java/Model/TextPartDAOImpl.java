package Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TextPartDAOImpl extends GenericHibTemplateDAOImpl<TextPart, Integer> implements TextPartDAO {


	@Override
	public TextPart getTextPartByType(String type) {
		// TODO Auto-generated method stub
		List list = template.findByNamedParam("select tp from TextPart as tp where tp.textkey like :type", "type", type);
		return (!list.isEmpty())?(TextPart)list.get(0):null;
	}
	

}
