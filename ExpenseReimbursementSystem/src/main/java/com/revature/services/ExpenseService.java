package com.revature.services;

import java.util.List;

import com.revature.dao.ExpenseDao;
import com.revature.dao.ExpenseDaoImpl;
import com.revature.models.Expense;
import com.revature.models.User;

public class ExpenseService {

	private ExpenseDao eDao = new ExpenseDaoImpl(); 
	
	public List<Expense> getPendingExpenses (int user_id) {
		return eDao.getPendingExpenses(user_id); 
	}
	public Expense insertNewExpense(Expense newExpense) {
		
		return eDao.insertNewExpense(newExpense); 
	}
	public List<Expense> getPastExpenses(int user_id) {
		System.out.println("how about here?");
		return eDao.getPastExpenses(user_id); 
	}
	public Expense findAnExpense(int id) {
		//add some fauthorization here
		return eDao.findAnExpense(id); 
	}
	
	public List<Expense> getAllPendingExpenses(){
		return eDao.getAllPendingExpenses(); 
		
	}
	public List<Expense> fetchAllPendingExpenses() {
		
		return eDao.fetchAllPendingExpensesDetails(); 
		
	}
	public Expense updateExpenseStatus(Expense updatedExpense) {
		
		return eDao.updateExpense(updatedExpense);
	}
	public List<Expense> fetchAllExpenses() {
		return eDao.fetchAllExpensesDetails(); 
	}
	public List<Expense> filterAllExpenses(String filter) {
		
		return eDao.filterAllExpenses(filter); 
	}
	public Expense findExpenseById(int id) {
		return eDao.findExpenseById(id); 
	}
}
