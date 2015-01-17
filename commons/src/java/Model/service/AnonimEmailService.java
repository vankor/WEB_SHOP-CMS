package Model.service;

import Model.AnonimEmailWatcherDAO;
import Model.ServiceImpl;
import Model.entity.AnonimEmailWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AnonimEmailService extends ServiceImpl<AnonimEmailWatcher, Integer, AnonimEmailWatcherDAO> {
	@Autowired
	private AnonimEmailWatcherDAO emDAO;
	
/*	@Transactional
	public void add(AnonimEmailWatcher g) {
		emDAO.add(g);
		
	}

	@Transactional
	public void delete(AnonimEmailWatcher g) {
		emDAO.delete(g);
		
	}

	@Transactional
	public void deleteById(Integer id) {
		emDAO.deleteById(id);
		
	}

	@Transactional
	public List<AnonimEmailWatcher> getAll() {
		return emDAO.getAll();
	}

	@Transactional
	public AnonimEmailWatcher getById(Integer id) {
		return emDAO.getById(id, false);
	}

	@Transactional
	public void update(AnonimEmailWatcher k) {
		emDAO.update(k);
		
	}*/
	
	@Transactional
	public AnonimEmailWatcher getEmail(String str){
		return emDAO.getEmail(str);
	}

	/*

	@Override
	@Transactional
	public Integer getLastId() {
		return emDAO.getLastId();
	}*/
}
