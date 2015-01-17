package Model.service;

import Model.repository.AnonimEmailWatcherDAO;
import Model.entity.AnonimEmailWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AnonimEmailService extends ServiceImpl<AnonimEmailWatcher, Integer, AnonimEmailWatcherDAO> {
	@Autowired
	private AnonimEmailWatcherDAO emDAO;
	

	
	@Transactional
	public AnonimEmailWatcher getEmail(String str){
		return emDAO.getEmail(str);
	}


}
