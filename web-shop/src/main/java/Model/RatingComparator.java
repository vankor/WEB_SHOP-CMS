package Model;

import java.util.Comparator;

public class RatingComparator implements Comparator<GoodItem>{

	@Override
	public int compare(GoodItem o1, GoodItem o2) {
		int result = 0;
		if(o1.getAverage_rating()!=o2.getAverage_rating()){
			result = ((o1.getAverage_rating()-o2.getAverage_rating())>0)?1:-1;
		}
		
		return result;
	}

}
