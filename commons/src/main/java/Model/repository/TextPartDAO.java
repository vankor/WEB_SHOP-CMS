package Model.repository;

import Model.entity.TextPart;

public interface TextPartDAO extends GenericDAO<TextPart, Integer>{
	


	
	public TextPart getTextPartByType(String type);

}
