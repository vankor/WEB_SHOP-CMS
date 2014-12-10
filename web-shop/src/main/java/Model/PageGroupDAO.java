package Model;

import java.util.Set;

public interface PageGroupDAO extends GenericDAO<PageGroup, Integer>{

	public Set<PageGroup> getFooterPagegroups();

}
