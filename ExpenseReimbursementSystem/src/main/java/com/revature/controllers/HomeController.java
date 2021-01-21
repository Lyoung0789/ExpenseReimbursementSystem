package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class HomeController extends HttpServlet {
	final static Logger Loggy = Logger.getLogger(HomeController.class);
	private static final long serialVersionUID = 1L;

	public static void getHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		if(req.getSession(false) != null ) {

			Loggy.info("We are logging in and being forwarded to /home.html ");
			RequestDispatcher redis = req.getRequestDispatcher("/home.html"); 
			redis.forward(req, resp);
			return; 
		} else {
			
			Loggy.info("We are Not logged in. being redirected to landing page");

			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/landing");

		}
		
	}
	
public static void getManagerHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		


		if(req.getSession(false) != null /*&& (Boolean)req.getSession().getAttribute("user")*/) {
			
			Loggy.info("We are logging in as a Manager and being forwarded to /managerHome.html ");
			RequestDispatcher redis = req.getRequestDispatcher("/managerHome.html"); 
			redis.forward(req, resp);
			return; 
		} else {

			Loggy.info("We are Not logged in as a Manager. Being redirected to landing page");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/landing");

		}
		
	}

public static void oops(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	Loggy.info("OOPS page. URI was not found: " + req.getRequestURI());
	RequestDispatcher redis = req.getRequestDispatcher("/oops.html"); 
	redis.forward(req, resp);

	
}

}
