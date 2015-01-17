package Model.repository;

import Model.entity.GoodState;

public interface GoodStateDAO extends GenericDAO<GoodState, Integer>{
	

	public GoodState getGoodStateByName(String name);

}
