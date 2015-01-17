package Model.repository;

import Model.entity.Value;

import java.util.List;

public interface ValueDAO extends GenericDAO<Value, Integer>{
	
	public void addValue(Value g);
	public void deleteValue(Value g);
	public void deleteValueById(int id);
	public List<Value> getAllProperties();
	public Value getValueById(int id);
	public void update(Value k);
	

}
