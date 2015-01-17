package Model.dto_beans;

import Model.others.GoodCollection;
import Model.others.Row;
import Model.entity.GoodItem;

import javax.persistence.Transient;

public class BucketRowBean implements Row, Comparable {

	private Integer goodcount;

	private transient GoodItem good;
	
	private transient GoodCollection goodset;

	
	






	
	public Integer getGoodcount() {
		return goodcount;
	}
	public void setGoodcount(Integer goodcount) {
		this.goodcount = goodcount;
	}

	








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
