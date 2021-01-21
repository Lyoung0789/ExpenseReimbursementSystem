package com.revature.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Expense;
import com.revature.models.Manager;
import com.revature.models.User;
import com.revature.services.ExpenseService;

public class ExpenseController {
	final static Logger Loggy = Logger.getLogger(ExpenseController.class);
	private static ExpenseService eManager = new ExpenseService(); 
	
	public static void getExpenseForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		if(req.getSession(false) != null) {
			Loggy.info("User is creating a new Expense");
			RequestDispatcher redis = req.getRequestDispatcher("/newRequest.html"); 
			redis.forward(req, resp);
		} else {
			Loggy.info("User is not logged. Do not have access to this.");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
		
	}
	
	public static void getAllExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getSession(false) != null) {
			RequestDispatcher redis = req.getRequestDispatcher("/empViewAllExpenses.html"); 
			Loggy.info("User is viewing all his expenses. ");
			redis.forward(req, resp);
		} else {
			Loggy.info("User is not logged. Do not have access to this.");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
			
	}
	
	public static void getPendingExpenses (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getMethod().equals("GET")) {
			if(req.getSession(false) != null) {
				HttpSession sesh = req.getSession(); 			
				List<Expense> pendingExpenses= eManager.getPendingExpenses(((User)sesh.getAttribute("user")).getUser_id()); 
				Loggy.info("User is viewing all pending accounts" +  pendingExpenses);
//				System.out.println(pendingExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(pendingExpenses));
			} else {
				Loggy.info("User is not logged. Do not have access to this.");
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			}
			
		} else {
			Loggy.info("This is not a GET request");
		}
	}
	
	public static void getPastExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getMethod().equals("GET")) {
			if(req.getSession(false) != null) {
				HttpSession sesh = req.getSession();
				List<Expense> pastExpenses= eManager.getPastExpenses(((User)sesh.getAttribute("user")).getUser_id()); 
				Loggy.info("User is viewing all past accounts" +  pastExpenses);

				System.out.println(pastExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(pastExpenses));
			} else {
				Loggy.info("User is not logged. Do not have access to this.");
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			}
			
		} else {
			Loggy.info("This is not a GET request");
		}
		
	}
	

	public static void postExpense(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession(false) != null) {
			ObjectMapper om = new ObjectMapper(); 
			Expense newExpense = om.readValue(req.getReader(), com.revature.models.Expense.class); 
			
			HttpSession sesh = req.getSession(); 
			User currentUser = (User) sesh.getAttribute("user"); 
			
			newExpense.setUser_id(currentUser.getUser_id());
			newExpense.setStatus("Pending");
			
			Expense postedExpense = eManager.insertNewExpense(newExpense); 			
			if(postedExpense != null) {
				Loggy.info("User submitted new expense request: " + postedExpense);
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
			} else {
				resp.setStatus(424); 
				Loggy.info("response status 424. Post was not created. ");
			}
		} else {
			Loggy.info("User is not logged. Do not have access to this.");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
		

		
	}

	
	
	public static void getAllPendingExpenses (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getMethod().equals("GET")) {
			if(req.getSession(false) != null) {				
				HttpSession sesh = req.getSession(); 
				if(sesh.getAttribute("manager") instanceof Manager) {
					List<Expense> pendingExpenses= eManager.getAllPendingExpenses(); 
					Loggy.info("Manager is viewing all pending expenses");
					ObjectMapper om = new ObjectMapper(); 
					resp.getWriter().write(om.writeValueAsString(pendingExpenses));
				} else {
					System.out.println("Sorry you do not have access to that information. ");
					resp.setStatus(401);
				}
				
			}
		} else {
			Loggy.info("User is not logged. Do not have access to this.");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
	
	}
	
	public static void getManagerAllPendingPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{	
		
		if(req.getMethod().equals("GET") || req.getMethod().equals("PUT")) {
			
			if(req.getSession(false) != null) {				
				HttpSession sesh = req.getSession(); 
				if(sesh.getAttribute("manager") instanceof Manager) {
					Loggy.info("Manager is viewing all pending expenses");
					RequestDispatcher redis = req.getRequestDispatcher("/managerViewAllPending.html"); 
					redis.forward(req, resp);
					
				} else {
					
					resp.setStatus(401);
					resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home"); 
				}
			}
		} else {
			Loggy.info("User is not logged. Do not have access to this.");
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
	}

	public static void fetchAllPendingExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				List<Expense> allPendingExpenses = eManager.fetchAllPendingExpenses(); 
				Loggy.info("Getting all Pending Expenses " + allPendingExpenses);

				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(allPendingExpenses));
			} else {
				Loggy.info("You do not have access to this. ");
			}
		}
		
	}
	
	public static void updateExpense(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getMethod().equals("PUT")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				ObjectMapper om = new ObjectMapper(); 
				Expense updatedExpense = om.readValue(req.getReader(), com.revature.models.Expense.class); 
				Expense fullyUpdatedExpense = eManager.updateExpenseStatus(updatedExpense); 
				
				System.out.println(fullyUpdatedExpense.toString());
				if(fullyUpdatedExpense != null) {
					System.out.println("This is happening before i get redirected somehwere");
					Loggy.info("Manager has updated the status on the expense " + fullyUpdatedExpense);
					resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/managerViewAllPending");
				} else {
					resp.setStatus(424); 
					resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/managerHome");
				}
			}
		}
	
	}

	public static void getManagerAllExpensePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession sesh = req.getSession(); 
		if(sesh.getAttribute("manager") instanceof Manager) {
			Loggy.info("Manager is being directed to the view all page");
			RequestDispatcher redis = req.getRequestDispatcher("/managerViewAllExpenses.html"); 
			redis.forward(req, resp);
		} else {
			resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
		}
		
	}

	public static void fetchAllExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				List<Expense> allExpenses = eManager.fetchAllExpenses(); 
				Loggy.info("Manager fetched all the expenses: " + allExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(allExpenses));
			} else {
				resp.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/ers/home");
				System.out.println("You should not be here");
			}
		}
		
	}

	public static void filterPendingExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		
		String filter = "Pending";
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				List<Expense> allExpenses = eManager.filterAllExpenses(filter); 
				Loggy.info("Manager fetched with filter all pending expenses: " + allExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(allExpenses));
			} else {
				System.out.println("You should not be here");
			}
		}
		
	}

	public static void filterAcceptedExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filter = "Accepted"; 
		
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				List<Expense> allExpenses = eManager.filterAllExpenses(filter); 
				Loggy.info("Manager fetched with filter all Accepted expenses: " + allExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(allExpenses));
			} else {
				System.out.println("You should not be here");
			}
		}
		
	}

	public static void filterDeniedExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filter = "Denied"; 
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
			if (sesh.getAttribute("manager") instanceof Manager) {
				List<Expense> allExpenses = eManager.filterAllExpenses(filter); 
				Loggy.info("Manager fetched with filter all denied expenses: " + allExpenses);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(allExpenses));
			} else {
				System.out.println("You should not be here");
			}
		}
	}

	public static void getExpenseById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println(req.getParameter("id"));
		if(req.getMethod().equals("GET")) {
			HttpSession sesh = req.getSession(); 
//			Expense e = null; 
			resp.setContentType("application/json");
			int id = Integer.parseInt(req.getParameter("id"));
			Expense e = eManager.findExpenseById(id); 
			
			if(e != null) {
				Loggy.info("User fetched expense by id" + e);
				ObjectMapper om = new ObjectMapper(); 
				resp.getWriter().write(om.writeValueAsString(e));
			} else {
				resp.setStatus(404);
			}
			
		} else {
			resp.setStatus(405);
		}
		
	}
	
	
	
}
