package Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityFieldService extends ServiceImpl<EntityField, Integer, EntityFieldDAO>{
	
	@Autowired
	private EntityFieldDAO entDAO;
	
	public List<EntityField> listByClassname(String classname) {
		return entDAO.listByClassname(classname);
	}
	
	public EntityField getByClassAndField(String classname, String fieldname) {
		return entDAO.getByClassAndField(classname, fieldname);
		
	}
}
