package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.controllers.RequestHelper;

public class MasterServlet extends HttpServlet{
	final static Logger Loggy = Logger.getLogger(MasterServlet.class); 

	public MasterServlet() {
		super(); 
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
		Loggy.info("In the doGet directing to RequestHelper with request to: " + req.getRequestURI());
		RequestHelper.process(req, resp); 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		Loggy.info("In the doPost directing to doGet" + req.getRequestURI());
		doGet(req, resp); 
	}
	

	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		Loggy.info("In the doPut directing to doGet" + req.getRequestURI());
		doGet(req, resp);
		
	}
}