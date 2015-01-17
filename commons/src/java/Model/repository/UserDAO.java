package Model.repository;

import Model.entity.User;

import java.util.List;


public interface UserDAO extends GenericDAO<User, Integer> {
	public void addUser(User g);
	public void deleteUser(User g);
	public void deleteUserById(int id);
	public List<User> getAllUsers();
	public User getUserById(int id);
	public List<User> getUsersByDescrition(String d);
	public List<User> getUsersByEmail(String d);
//	public List<User> getFiltered(Filter n);
	public void update(User g);
	public User checkUser(String login, String pass);
	public List<User> getUsersByPassword(String pass);
	public List<User> listPage(User.OrderingRules rule, Integer begin, Integer end);
	

}
