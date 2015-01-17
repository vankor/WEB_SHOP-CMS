package Model.others;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Transient;

public interface Item {
	
		


	@Transient
	public Iterator createiterator(List<Item> list, List<Item> list2);
	@Transient
	public List<Item> dogetNodeList();
	@Transient
	public List<Item> dogetListOfLists();
	
	

}
