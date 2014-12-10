package Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

public class ServiceImpl<T extends Model.Entity, ID extends Serializable, DAO extends GenericDAO<T,ID>> implements Model.Service<T,ID>{

	@Autowired
	private DAO entityDAO;
	
	@Override
	@Transactional
	public void add(T f) {
		entityDAO.add(f);
		
	}

	@Override
	@Transactional
	public List<T> getAll() {
		return entityDAO.getAll();
	}

	@Override
	@Transactional
	public void deleteById(ID f) {
		entityDAO.deleteById(f);
		
	}

	@Override
	@Transactional
	public T getById(ID id) {
		return entityDAO.getById(id, false);
	}

	@Override
	@Transactional
	public void update(T entity) {
		entityDAO.update(entity);
		
	}

	@Override
	@Transactional
	public ID getLastId() {
		// TODO Auto-generated method stub
		return entityDAO.getLastId();
	}

	@Override
	@Transactional
	public List<T> listPage(Filter n, Integer begin, Integer end) {
		// TODO Auto-generated method stub
		return entityDAO.listPage(n, begin, end);
	}

	@Override
	@Transactional
	public List<T> filter(Filter n, Integer firstResult, Integer maxResult) {
		// TODO Auto-generated method stub
		return entityDAO.filter(n, firstResult, maxResult);
	}

	@Override
	@Transactional
	public List<Object> getValues(Filter n, Class currentclass, Field field) {
		// TODO Auto-generated method stub
		return entityDAO.getValues(n, currentclass, field);
	}

	@Override
	@Transactional
	public Integer getCount() {
		// TODO Auto-generated method stub
		return entityDAO.getCount();
	}

	@Override
	@Transactional
	public void changeMultiValues(List<? extends Serializable> ids,
			Map<String, Object> propvals) {
		entityDAO.changeMultiValues(ids, propvals);
		
	}

	@Override
	@Transactional
	public void setDelete(T ent) {
		entityDAO.setDelete(ent);
		
	}

	@Override
	@Transactional
	public void setDeleteById(ID id) {
		entityDAO.setDeleteById(id);
		
	}

	


	
}
