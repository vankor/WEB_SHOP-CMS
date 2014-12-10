package Model;

import javax.persistence.Transient;

public class BucketRowBean implements Row, Comparable {
//	private Integer good_id;
	private Integer goodcount;
//	private Integer goodset_id;
	private transient GoodItem good;
	
	private transient GoodCollection goodset;

	
	
//	public Integer getGood_id() {
//		return good_id;
//	}
//	public void setGood_id(Integer good_id) {
//		this.good_id = good_id;
//	}
	
	public Integer getGoodcount() {
		return goodcount;
	}
	public void setGoodcount(Integer goodcount) {
		this.goodcount = goodcount;
	}

	

//	public Integer getGoodset_id() {
//		return goodset_id;
//	}
//	public void setGoodset_id(Integer goodset_id) {
//		this.goodset_id = goodset_id;
//	}

	@Transient
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}
	public GoodCollection getGoodset() {
		return goodset;
	}
	public void setGoodset(GoodCollection goodset) {
		this.goodset = goodset;
	}
	@Override
	public int compareTo(Object arg0) {
		BucketRowBean otherrow = null;
		if(arg0 instanceof BucketRowBean){	
			otherrow = (BucketRowBean)arg0;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.good.compareTo(otherrow.good);
	}
	
}
