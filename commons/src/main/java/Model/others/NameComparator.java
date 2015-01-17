package Model.others;

import Model.entity.GoodItem;

import java.util.Comparator;

public class NameComparator implements Comparator<GoodItem> {

	@Override
	public int compare(GoodItem o1, GoodItem o2) {
		
			int result = 0;
			if(!o1.getName().equals(o2.getPrice())){
				result = o1.getName().compareTo(o2.getName());
			}
			
			return result;
	}

	

}
