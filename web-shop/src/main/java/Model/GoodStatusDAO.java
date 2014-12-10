package Model;

public interface GoodStatusDAO extends GenericDAO<GoodStatus, Integer>{
	
	public GoodStatus getGoodStatusByName(String name);

}
