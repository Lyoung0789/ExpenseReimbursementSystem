package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogoutController {
	final static Logger Loggy = Logger.getLogger(LogoutController.class);

	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		Loggy.info("Loggin out and removing sessions" );
		req.getSession().invalidate(); 
		resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/landing");
		return; 
	}
}
