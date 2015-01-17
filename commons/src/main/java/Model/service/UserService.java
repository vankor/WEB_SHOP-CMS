package Model.service;

import java.util.List;

import Model.repository.AdressDAO;
import Model.others.Filter;
import Model.repository.UserDAO;
import Model.entity.Bucket;
import Model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService extends ServiceImpl<User, Integer, UserDAO> {

	
	@Autowired
	private UserDAO usr;
	@Autowired
	private AdressDAO adrDAO;
	


	
	@Transactional
	public void add(User t){
		usr.addUser(t);
	}
	@Transactional
	public void delete(User t){
		usr.deleteUser(t);
	}
	@Transactional
	public void deleteById(Integer id){
		usr.deleteById(id);
	}









		

	@Transactional
	public void addBucket(User t, Bucket b){
		t.addBucket(b);
		usr.update(t);
	}
	
	@Transactional
	public List<User> getAll(){
		return	usr.getAllUsers();
		
	}
	
	@Transactional
	public User getById(Integer id){
		return	usr.getUserById(id);
		
		
	}
	
	@Transactional
	public User loginUser(String login, String pass){
		return	usr.checkUser(login, pass);
		
		
	}
	
	@Transactional
	public List<User> getUsersByEmail(String d) {
		return usr.getUsersByEmail(d);
	}
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void update(User g) {

		if(g.getAdress()!=null){
			if(g.getAdress().getId()==null)
			g.getAdress().setId((Integer) adrDAO.save(g.getAdress()));
			else
				adrDAO.update(g.getAdress());
		}

		usr.update(g);
	}
	
	public List<User> getUsersByPassword(String pass) {
		return usr.getUsersByPassword(pass);
	}
	
	
	@Transactional
	public Integer getLastId() {
		
		return null;
	}
	
	@Transactional
	public List<User> listPage(Filter goodfilter, Integer begin, Integer end) {
		return usr.listPage(goodfilter, begin, end);
	}
	

}
