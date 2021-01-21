package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Manager;
import com.revature.models.User;
import com.revature.services.ManagerService;
import com.revature.services.UserService;

public class LoginController extends HttpServlet{
	final static Logger Loggy = Logger.getLogger(LoginController.class);
	private static UserService userManager = new UserService(); 
	private static ManagerService managerManager = new ManagerService(); 
	
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println(req.getRequestURI());
		if(req.getMethod().equals("POST")){
			User loginUser = userManager.getUserByLogin(req.getParameter("email"), req.getParameter("password"));
			if(loginUser != null ) {
				
				HttpSession sesh = req.getSession(); 
				sesh.setAttribute("user", loginUser); 
				Loggy.info("We are being logged in. session is being created with an attribute of user"  );
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			} else {
				resp.setStatus(403);
				Loggy.info("We are Not being logged in. Username and password did not match." );
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			}
			
			
			
		} else {
			Loggy.info("This is not a Post Request" );
			HttpSession sesh = req.getSession(); 
			if(sesh.getAttribute("user")!= null){
				System.out.println("In emp redirect");
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			} else if (sesh.getAttribute("manager") != null) {
				System.out.println("In Manager redirect");
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/managerHome");
			} else {
				resp.setStatus(405);
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			}
//			resp.setStatus(405);
//			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			
			
		}
		
		
	}
	
	public static void getLandingPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//The home controller will navigate back us to our login controller, our login controller will return the login page. 
		// This can be after a false login, or if they're entering the website for the very first time. 
		RequestDispatcher redis = req.getRequestDispatcher("/login.html");
		redis.forward(req, resp);
		
	}
	
	
	public static void getManagerLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		//The home controller will navigate back us to our login controller, our login controller will return the login page. 
		// This can be after a false login, or if they're entering the website for the very first time. 
		RequestDispatcher redis = req.getRequestDispatcher("/managerLogin.html");
		
		redis.forward(req, resp);
		
	}
	
	public static void managerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		if(req.getMethod().equals("POST")){
			Manager loginManager = managerManager.getManagerByLogin(req.getParameter("email"), req.getParameter("password"));
			if(loginManager != null ) {
				HttpSession sesh = req.getSession(); 
				sesh.setAttribute("manager", loginManager); 
				Loggy.info("We are being logged in. session is being created with an attribute of manager:" + ((Manager)sesh.getAttribute("manager")).getUsername());
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/managerHome");
			} else {
				resp.setStatus(403);

				Loggy.info("Invalid attempt to log in, being redirected to landing page. " );
				//might change
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			}
			
			
		} else {
			resp.setStatus(405);
			Loggy.info("Invalid attempt to log in, being redirected to landing page. " );
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			
			
		}
	}

}
