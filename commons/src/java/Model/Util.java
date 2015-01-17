package Model;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Util {

	  public static boolean contains(Collection<?> coll, Object o) {
	    return coll.contains(o);
	  }
	  
	  public static int getdelposition(Collection<DeliveryTypeBean> coll, Object o) {
		  int i = 0;
		  for(DeliveryTypeBean h: coll){
			  
			  if(h.getDeltype()!=null && h.getDeltype().equals(o))
			  {return i;}
			  i++;
		  }
		  return -1;
		  }
	  
	  public static boolean containsdt(Collection<DeliveryTypeBean> coll, Object o) {
		  for(DeliveryTypeBean h: coll){
			  if(h.getDeltype().equals(o))
			  {return true;}
		  }
		  return false;
		  }
	  
	  public static boolean containsval(Filter coll, Object o) {
		  for(Entry<String,Set<FilterNode>> entry: coll.getPropfilters().entrySet()){
		  for(FilterNode h: entry.getValue()){
			  if(h.getValues().contains(o))
			  {return true;}
		  }
		  }
		  return false;
		  }

}
