package Model.others;

import Model.entity.GoodItem;

import java.util.Comparator;

public class PriceComparator implements Comparator<GoodItem>{

	
	@Override
	public int compare(GoodItem o1, GoodItem o2) {
		int result = 0;
		if(o1.getPrice()!=o2.getPrice()){
			result = ((o1.getPrice()-o2.getPrice())>0)?1:-1;
		}
		
		return result;
	}
	

}
