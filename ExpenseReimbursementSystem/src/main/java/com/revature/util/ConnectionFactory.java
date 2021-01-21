package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://database-1.chldbb4eh8x3.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = System.getenv("DB_USERNAME");
	private static final String PASSWORD = System.getenv("DB_PASSWORD");
	

	private static Connection conn; 
	
	public static Connection getConnection() throws SQLException {

		if(conn == null) {
			try {
				Class.forName("org.postgresql.Driver"); 
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
		}
		
		if(conn.isClosed()) {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
		}
		
//		try {
//		
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
		return conn; 
	}
	
}
