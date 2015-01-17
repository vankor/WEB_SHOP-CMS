package Model.repository;

import Model.entity.GoodStatus;

public interface GoodStatusDAO extends GenericDAO<GoodStatus, Integer>{
	
	public GoodStatus getGoodStatusByName(String name);

}
