package Model.repository;

import Model.entity.PageGroup;
import Model.repository.GenericDAO;

import java.util.Set;

public interface PageGroupDAO extends GenericDAO<PageGroup, Integer> {

	public Set<PageGroup> getFooterPagegroups();

}
