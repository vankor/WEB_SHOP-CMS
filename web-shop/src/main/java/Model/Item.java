package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

public interface Item {
	
		
//	public List<Item> getChildItems(){throw new UnsupportedOperationException();}
//	public void setItems(List<Item> goods) {throw new UnsupportedOperationException();}
	@Transient
	public Iterator createiterator(List<Item> list, List<Item> list2);
	@Transient
	public List<Item> dogetNodeList();
	@Transient
	public List<Item> dogetListOfLists();
	
	

}
