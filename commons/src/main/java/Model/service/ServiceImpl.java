package Model.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import Model.others.Filter;
import Model.entity.Entity;
import Model.repository.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ServiceImpl<T extends Entity, ID extends Serializable, DAO extends GenericDAO<T,ID>> implements Model.service.Service<T,ID> {

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

		return entityDAO.getLastId();
	}

	@Override
	@Transactional
	public List<T> listPage(Filter n, Integer begin, Integer end) {

		return entityDAO.listPage(n, begin, end);
	}

	@Override
	@Transactional
	public List<T> filter(Filter n, Integer firstResult, Integer maxResult) {

		return entityDAO.filter(n, firstResult, maxResult);
	}

	@Override
	@Transactional
	public List<Object> getValues(Filter n, Class currentclass, Field field) {

		return entityDAO.getValues(n, currentclass, field);
	}

	@Override
	@Transactional
	public Integer getCount() {

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
