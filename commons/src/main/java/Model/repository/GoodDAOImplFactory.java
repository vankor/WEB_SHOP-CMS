package Model.repository;

public class GoodDAOImplFactory {
	
	private static GoodDAOImplFactory instance;
	private GoodDAO n;
	
	public GoodDAO getGoodDAOImpl(){
		if(n==null){
			n = new GoodDAOImpl();
		}
		return n;
		
	}
	
	public static GoodDAOImplFactory getInstance(){
		if(instance ==null){
			instance = new GoodDAOImplFactory();
		}
		return instance;
	}


}