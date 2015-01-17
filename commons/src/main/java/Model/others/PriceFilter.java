package Model.others;

import java.util.List;

public class PriceFilter implements Cloneable {
	private Double upperlimit;
	private Double lowerlimit;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public Double getUpperlimit() {
		return upperlimit;
	}
	public void setUpperlimit(Double upperlimit) {
		this.upperlimit = upperlimit;
	}
	public Double getLowerlimit() {
		return lowerlimit;
	}
	public void setLowerlimit(Double lowerlimit) {
		this.lowerlimit = lowerlimit;
	}

	


}
