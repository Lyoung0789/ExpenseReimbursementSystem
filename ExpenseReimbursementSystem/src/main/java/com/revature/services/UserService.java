package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

public class UserService {

	private UserDao uDao = new UserDaoImpl(); 
//	public static User masterUser = new User("test@gmail.com", "test"); 
	
	
	public User  getUserByLogin (String username, String password) {
		return uDao.getUserByLogin(username, password); 
	}
	
	public User postNewUser(User newUser) {
//		Set<User> allUsernames = new HashSet<>(); 
//		allUsernames = uDao.getAllUsernames(); 
//		
//		if
	
		return uDao.postNewUser(newUser); 
	}
	
}
