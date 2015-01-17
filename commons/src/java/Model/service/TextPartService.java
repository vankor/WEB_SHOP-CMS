package Model.service;


import Model.ServiceImpl;
import Model.TextPartDAO;
import Model.entity.TextPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TextPartService extends ServiceImpl<TextPart, Integer, TextPartDAO> {
	
	@Autowired
	private TextPartDAO img;
	

/*	@Transactional
	public void add(TextPart f){
		img.addTextPart(f);
	}

	@Transactional
	public List<TextPart> getAll(){
		return img.getAllTextParts();
	}
	
	@Transactional
	public void deleteById(Integer f){
		img.deleteTextPartById(f);
	}
	
	@Transactional
	public TextPart getById(Integer id){
		return img.getTextPartById(id);
	}
	
	@Transactional
	public void update(TextPart textpart){
		 img.update(textpart);
	}

	

	@Override
	@Transactional
	public Integer getLastId() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	public TextPart getTextPartByType(String type) {
		return img.getTextPartByType(type);
	}



}