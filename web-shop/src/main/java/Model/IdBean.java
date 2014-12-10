package Model;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class IdBean {
	
	private Set<Integer> ids = new TreeSet<Integer>();

	public IdBean(Collection<ModelEntity> f){
		for(ModelEntity m: f){
			ids.add(m.getId());
		}
	}
	
	public IdBean(){}
	
	public Set<Integer> getIds() {
		return ids;
	}

	public void setIds(Set<Integer> ids) {
		this.ids = ids;
	}
	
	public void addId(Integer id) {
		ids.add(id);
	}

}
