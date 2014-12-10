package Model;

import java.util.List;

public interface AnonimEmailWatcherDAO extends GenericDAO<AnonimEmailWatcher, Integer>{
	
/*	public void addEmail(AnonimEmailWatcher g);
	public void deleteEmail(AnonimEmailWatcher g);
	public void deleteEmailById(int id);
	public List<AnonimEmailWatcher> getAllEmails();
	public AnonimEmailWatcher getEmailById(int id);*/
	public AnonimEmailWatcher getEmail(String str);
	/*public void update(AnonimEmailWatcher k);*/

}
