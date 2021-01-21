package com.revature.services;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Manager;


public class ManagerService {

	private ManagerDao mDao = new ManagerDaoImpl(); 
	
	public Manager getManagerByLogin (String username, String password) {
		return mDao.getManagerByLogin(username, password); 
	}
}
