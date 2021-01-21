package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByLogin(String username, String password) {
		
		String sql = "SELECT  * from ers_user where username = ? AND password = ?"; 
		try(Connection conn = ConnectionFactory.getConnection() ){
			PreparedStatement ps =  conn.prepareStatement(sql); 
			ps.setString(1,  username);
			ps.setString(2, password);
			ResultSet rs =  ps.executeQuery(); 
			
			while(rs.next()) {
				return new User(
						rs.getString("username"), 
						rs.getString("password"), 
						rs.getInt("user_id"), 
						rs.getString("f_name"), 
						rs.getString("l_name"), 
						rs.getString("title"), 
						rs.getString("street_adress"), 
						rs.getString("city"), 
						rs.getString("state"), 
						rs.getInt("zipcode") 
						);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User postNewUser(User newUser) {
		User postedUser = null; 
		System.out.println("did we get here?");
		String sql = "insert into ers_user (username, password, f_name, l_name, title, street_adress, city, state, zipcode) values (?, ?, ?, ?, ?, ?,?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getFirstName());
			ps.setString(4, newUser.getLastName());
			ps.setString(5, newUser.getTitle());
			ps.setString(6, newUser.getStreetAddress());
			ps.setString(7, newUser.getCity());
			ps.setString(8, newUser.getState());
			ps.setInt(9, newUser.getZipcode());
			
			ps.execute(); 
			//return the updated user. 
			postedUser = newUser; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return postedUser;  
	}

}
