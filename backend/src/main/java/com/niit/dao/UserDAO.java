package com.niit.dao;

import java.util.List;

import com.niit.model.User;

public interface UserDAO 
{
	public boolean registerUser(User user);
	public boolean modifyUser(User user);
	public User getUser(String userName);
	public List<User> listUser();
	
}
