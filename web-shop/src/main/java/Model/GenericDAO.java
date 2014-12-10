package Model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


public interface GenericDAO<T extends Model.Entity, ID extends Serializable> {
        
        public T getById(ID id, boolean lock);  
      
        public List<T> getAll();  
      
//        public List<T> getById(T exampleInstance);  
      
        public void add(T entity);  
        
        public void update(T entity);
      
        public ID getLastId();
        
        public void delete(T g);
        
    	public void flush();
    	
    	public void clear();

    	public void deleteById(ID id);
    	
    	public List<T> toList(List<Object> list);
    	
    	public Set<T> toSet(List<Object> list);
    	
    	public ID save(T t);

    	public void setDelete(T t);
    	
    	public void setDeleteById(ID t);
    	
    	public List<T> listPage(Filter n, Integer begin, Integer end);
    	
    	public List<T> filter(Filter n, Integer firstResult, Integer maxResult);
    	
    	public List<Object> getValues(Filter n, Class currentclass, Field field);
    	
    	public Integer getCount();
    	
    	public HibernateTemplate getTemplate();


    	public void setTemplate(HibernateTemplate template);

    	public void changeMultiValues(List<? extends Serializable> ids,
				Map<String, Object> propvals);

		void saveOrUpdate(T entity);
    	
    	}
