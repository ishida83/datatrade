package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.UserService;
import dao.UserDao;
import domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User saveUser(User user) {  
		// TODO Auto-generated method stub
		userDao.saveUser(user);
		return userDao.getUser(user.getUsername());
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDao.getUser(username);
	}
	
}
