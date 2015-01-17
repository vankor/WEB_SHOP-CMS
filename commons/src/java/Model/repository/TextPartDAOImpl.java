package Model.repository;

import java.util.List;

import Model.entity.TextPart;
import org.springframework.stereotype.Repository;

@Repository
public class TextPartDAOImpl extends GenericHibTemplateDAOImpl<TextPart, Integer> implements TextPartDAO {


	@Override
	public TextPart getTextPartByType(String type) {
		// TODO Auto-generated method stub
		List list = template.findByNamedParam("select tp from TextPart as tp where tp.textkey like :type", "type", type);
		return (!list.isEmpty())?(TextPart)list.get(0):null;
	}
	

}
