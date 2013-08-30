package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import dao.UserDao;
import domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<User> getUser(User user) {
		// TODO Auto-generated method stub
		return hibernateTemplate.findByExample(user);
	}

	@Override
	public User getUser(String usename) {
		// TODO Auto-generated method stub
		User user = null;
		List<User> users = hibernateTemplate.find(
				"from User u where u.username = ? ", usename);
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(user);
	}
}
