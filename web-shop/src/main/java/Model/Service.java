package Model;

import java.io.Serializable;
import java.lang.reflect.Field;


import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.springframework.transaction.annotation.Transactional;


public interface Service<D  extends Model.Entity, ID extends Serializable>{

	@Transactional
	public void add(D  f);

	@Transactional
	public List<D> getAll();
	
	@Transactional
	public void deleteById(ID f);
	
	@Transactional
	public D getById(ID  id);
	
	@Transactional
	public void update(D entity);
	
	@Transactional
	public ID getLastId(); 
	
	@Transactional
	public List<D> listPage(Filter n, Integer begin, Integer end);
	
	@Transactional
	public List<D> filter(Filter n, Integer firstResult, Integer maxResult);
	
	@Transactional
	public List<Object> getValues(Filter n, Class currentclass, Field field);
	
	@Transactional
	public Integer getCount();
	
	@Transactional
	public void changeMultiValues(List<? extends Serializable> ids, Map<String, Object> propvals);
	
	@Transactional
	public void setDelete(D ent);
	
	@Transactional
	public void setDeleteById(ID id);

}
