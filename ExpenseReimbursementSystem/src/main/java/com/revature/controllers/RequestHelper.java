package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RequestHelper {
//	final static Logger Loggy = Logger.getLogger(RequestHelper.class); 
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String endpoint = req.getRequestURI(); 
		
		switch(endpoint) {
			case "/ExpenseReimbursementSystem/ers/view/Expenses":
				ExpenseController.getAllExpenses(req, resp);
				
				break; 
				
			case "/ExpenseReimbursementSystem/ers/Expense":
				switch(req.getMethod()) {
					case "PUT":
						ExpenseController.updateExpense(req, resp); 
						break; 
					case "GET":
						ExpenseController.getExpenseById(req, resp);
						break; 
					case "POST":
						ExpenseController.postExpense(req, resp); 
						break; 
					default: 
						break; 
				}
				break;
			case "/ExpenseReimbursementSystem/ers/manager":
				switch(req.getMethod()) {
				case "GET":
					ManagerController.getCurrentManager(req, resp); 
					break; 
				default: 
					break; 
				}
				break;
			case "/ExpenseReimbursementSystem/ers/user":
				switch(req.getMethod()) {
					case "GET":
						
						UserController.getCurrentUser(req, resp); 
						break; 
					default: 
						break; 
				}
				break; 
			case "/ExpenseReimbursementSystem/ers/newuser":
				switch(req.getMethod()) {
					case "GET":
						UserController.getNewUserForm(req, resp); 
						break; 
					case "POST":
						UserController.postNewUser(req, resp); 
						break; 
					default : 
						break; 
				}
				break; 
			case "/ExpenseReimbursementSystem/ers/pendingExpenses":
				ExpenseController.getPendingExpenses(req, resp); 
				break ; 
			case "/ExpenseReimbursementSystem/ers/allPendingExpenses":
				ExpenseController.getAllPendingExpenses(req, resp); 
				break ; 
			case "/ExpenseReimbursementSystem/ers/managerLanding":
				LoginController.getManagerLoginPage(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/managerLogin":
				LoginController.managerLogin(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/managerViewAllPending":
				ExpenseController.getManagerAllPendingPage(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/pastExpenses":
				ExpenseController.getPastExpenses(req,resp);
				break; 
			case "/ExpenseReimbursementSystem/ers/landing":
				
				LoginController.getLandingPage(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/login":
				
				LoginController.login(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/home":
				
				HomeController.getHomePage(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/managerHome":
				
				HomeController.getManagerHomePage(req, resp); 
				break;
			case "/ExpenseReimbursementSystem/ers/managerViewAllExpense":
				ExpenseController.getManagerAllExpensePage(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/logout":
				LogoutController.logout(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/new/Expense":
				System.out.println(req.getMethod()); 
				ExpenseController.getExpenseForm(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/fetchAllExpenses":
				ExpenseController.fetchAllExpenses(req,resp);
				break; 
			case "/ExpenseReimbursementSystem/ers/fetchAllPendingExpenses":
				System.out.println("We mad eit here?");
				ExpenseController.fetchAllPendingExpenses(req, resp); 
				break; 
//			case "/ExpenseReimbursementSystem/ers/view/Expense":
//				ExpenseController.getExpensePage(req, resp);
//				break; 
			case "/ExpenseReimbursementSystem/ers/view/pending/Expense":				
				ExpenseController.filterPendingExpenses(req, resp); 
				break; 
			case "/ExpenseReimbursementSystem/ers/view/accepted/Expense":				
				ExpenseController.filterAcceptedExpenses(req, resp);
				break;
			case "/ExpenseReimbursementSystem/ers/view/Denied/Expense":				
				ExpenseController.filterDeniedExpenses(req, resp);
				break;
			default:
				HomeController.oops(req, resp); 
				break; 
				
		}
		
	}

}
