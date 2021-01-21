package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Manager;

public class ManagerController extends HttpServlet{
	final static Logger Loggy = Logger.getLogger(ManagerController.class);

	public static void getCurrentManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		
		HttpSession sesh = req.getSession(); 
		ObjectMapper om = new ObjectMapper(); 
		Loggy.info("Current manger is: " + ((Manager)sesh.getAttribute("manager")).getUsername());

		resp.getWriter().write(om.writeValueAsString(sesh.getAttribute("manager")));
	}
	
	
	
}
