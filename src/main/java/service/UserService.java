package service;

import domain.User;


public interface UserService {
   
	 
	User saveUser(User user);
	
	
	User getUser(String username);
	 
}
