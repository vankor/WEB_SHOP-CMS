package Model;

public interface Row extends Comparable {
	
	public GoodItem getGood();
	public void setGood(GoodItem good);
//	public Integer getGood_id();
//	public void setGood_id(Integer good_id);
	public Integer getGoodcount();
	public void setGoodcount(Integer goodcount);
	public GoodCollection getGoodset();
	public void setGoodset(GoodCollection goodset_id);
//	public Integer getGoodset_id();
//	public void setGoodset_id(Integer goodset_id);

}
