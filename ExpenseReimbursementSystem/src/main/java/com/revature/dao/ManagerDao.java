package com.revature.dao;

import com.revature.models.Manager;


public interface ManagerDao {
	public Manager getManagerByLogin(String username, String password); 

}
