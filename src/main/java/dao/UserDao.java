package dao;

import java.util.List;

import domain.User;

public interface UserDao {
	/**
	 * select the user
	 * @param user the User to check
	 */
	List<User> getUser(User user);
	
	
	/**
	 * select the user
	 * @param usename String
	 */
	User getUser(String usename);

	/**
	 * save User
	 * @param user the User to check
	 */
	void saveUser(User user);

}
