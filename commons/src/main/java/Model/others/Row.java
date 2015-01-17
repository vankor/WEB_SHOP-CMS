package Model.others;

import Model.entity.GoodItem;

public interface Row extends Comparable {
	
	public GoodItem getGood();
	public void setGood(GoodItem good);


	public Integer getGoodcount();
	public void setGoodcount(Integer goodcount);
	public GoodCollection getGoodset();
	public void setGoodset(GoodCollection goodset_id);



}
