package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImplementation implements UserDAO
{

	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean registerUser(User user) {
		try
		{
			sessionfactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean modifyUser(User user) {
		try
		{
			sessionfactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public User getUser(String userName) {
		Session session = sessionfactory.openSession();
		User user = session.get(User.class,userName);
		session.close();
		return user;
	}

	@Override
	public List<User> listUser() {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from User");
		List list = query.list();
		List<User> listUser = list;
		session.close();
		return listUser;
	}
}
