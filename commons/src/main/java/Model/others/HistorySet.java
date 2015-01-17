package Model.others;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HistorySet<E> implements Set<E>{
	
	
	public List<E> getList() {
		List<E> copylist = new LinkedList<E>();
		Collections.copy(copylist, list);
		return copylist;
	}




	public static int getMaxsize() {
		return MAXSIZE;
	}




	private final static int MAXSIZE = 5;
	private LinkedList<E> list = new LinkedList<E>();
	

	
	public boolean add(E obj) {
		
		if(list.contains(obj)){return false;}
							
		if(list.size()==MAXSIZE){
			list.removeLast();
			list.addFirst(obj);
		}
		else{
			list.addFirst(obj);
		}
		
		
		return true;
	}


	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		for(E f:arg0){
			list.add(f);
		}
		return false;
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {

		return list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {

		return list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {

		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		
		return list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {

		return list.retainAll(arg0);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {

		return list.toArray(arg0);
	}


	@Override
	public boolean remove(Object o) {

		return list.remove(o);
	}
	
	

}
