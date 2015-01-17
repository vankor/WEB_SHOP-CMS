package Model.service;


import Model.repository.TextPartDAO;
import Model.entity.TextPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TextPartService extends ServiceImpl<TextPart, Integer, TextPartDAO> {
	
	@Autowired
	private TextPartDAO img;
	


	
	public TextPart getTextPartByType(String type) {
		return img.getTextPartByType(type);
	}



}