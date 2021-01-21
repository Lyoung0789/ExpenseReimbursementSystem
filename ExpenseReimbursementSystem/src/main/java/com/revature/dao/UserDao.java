package com.revature.dao;

import com.revature.models.User;

public interface UserDao {

	public User getUserByLogin(String username, String password); 
	public User postNewUser(User newUser); 
}
