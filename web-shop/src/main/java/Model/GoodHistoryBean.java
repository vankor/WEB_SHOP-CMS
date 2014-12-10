package Model;

import java.util.Date;

public class GoodHistoryBean implements Comparable{
	GoodItem good;
	Date date = new Date(System.currentTimeMillis());
	
	
	
	public GoodHistoryBean(GoodItem good) {
		this.good = good;
	}
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int compareTo(Object o) {
		GoodHistoryBean othergood = null;
		if(o instanceof GoodHistoryBean){	
			othergood = (GoodHistoryBean)o;
		}
		else{throw new ClassCastException("Сравнивать необходимо обьекты одного класса!");}
		return this.date.compareTo(othergood.date);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((good == null) ? 0 : good.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodHistoryBean other = (GoodHistoryBean) obj;
		if (good == null) {
			if (other.good != null)
				return false;
		} else if (!good.equals(other.good))
			return false;
		return true;
	}
	
	
	
	

}
