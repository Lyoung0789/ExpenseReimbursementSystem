package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Manager;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public Manager getManagerByLogin(String username, String password) {
		String sql = "SELECT  * from ers_managers where username = ? AND password = ?"; 
		try(Connection conn = ConnectionFactory.getConnection() ){
			PreparedStatement ps =  conn.prepareStatement(sql); 
			ps.setString(1,  username);
			ps.setString(2, password);
			ResultSet rs =  ps.executeQuery(); 
			
			while(rs.next()) {
				return new Manager(
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getInt("manager_id"), 
						rs.getString("f_name"), 
						rs.getString("l_name"), 
						rs.getString("city"), 
						rs.getString("state") 
						);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
