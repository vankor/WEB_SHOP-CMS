package Model;

import java.util.List;

public interface GoodStateDAO extends GenericDAO<GoodState, Integer>{
	

	public GoodState getGoodStateByName(String name);

}
