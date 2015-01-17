package Model.repository;

import Model.entity.TextPart;

public interface TextPartDAO extends GenericDAO<TextPart, Integer>{
	
/*	public void addTextPart(TextPart g);
	public void deleteTextPart(TextPart g);
	public void deleteTextPartById(Integer id);
	public List<TextPart> getAllTextParts();
	public TextPart getTextPartById(Integer id);
	public void update(TextPart k);*/
//	public Integer getLastId();
	
	public TextPart getTextPartByType(String type);

}
