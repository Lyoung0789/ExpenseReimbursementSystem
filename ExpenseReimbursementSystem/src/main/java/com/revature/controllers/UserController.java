package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {
	final static Logger Loggy = Logger.getLogger(UserController.class);
	
	private static UserService uService = new UserService(); 

	public static void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println(req.getRequestURL());
		
		HttpSession sesh = req.getSession(); 
		ObjectMapper om = new ObjectMapper(); 
		Loggy.info("Getting the current user loged in: " + sesh.getAttribute("user") );

		resp.getWriter().write(om.writeValueAsString(sesh.getAttribute("user")));
	}
	
	
	public static void getNewUserForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println(req.getRequestURL());
		RequestDispatcher redis = req.getRequestDispatcher("/registerUser.html"); 
		Loggy.info("Being redirected to /registerUser.html" );
		redis.forward(req, resp);
		
	}
	
	public static void postNewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
		ObjectMapper om = new ObjectMapper(); 
		User newUser = om.readValue(req.getReader(), com.revature.models.User.class); 
		System.out.println(newUser);
		
		User postedUser = uService.postNewUser(newUser); 
		
		Loggy.info("New user has been created: " + newUser);
		
		if(postedUser != null) {
			User loggedInUser = uService.getUserByLogin(postedUser.getUsername(), postedUser.getPassword()); 
			HttpSession sesh = req.getSession(); 
			sesh.setAttribute("user", loggedInUser);
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		} else {
			System.out.println("Nopeeeeers");
				Loggy.info("New user has NOT been created. There has been an error, redirecting to http://localhost:8080/ExpenseReimbursementSystem/ers/home " );
				resp.setStatus(403);
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
				
			
		}
		
		
		
	}
	
}
