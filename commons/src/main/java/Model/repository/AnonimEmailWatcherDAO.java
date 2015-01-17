package Model.repository;

import Model.entity.AnonimEmailWatcher;

public interface AnonimEmailWatcherDAO extends GenericDAO<AnonimEmailWatcher, Integer>{
	

	public AnonimEmailWatcher getEmail(String str);


}
