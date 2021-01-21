package com.revature.dao;

import java.util.List;

import com.revature.models.Expense;

public interface ExpenseDao {
	public List<Expense> getPendingExpenses(int user_id);

	public Expense insertNewExpense(Expense newExpense);

	public List<Expense> getPastExpenses(int user_id);

	public Expense findAnExpense(int id);

	public List<Expense> getAllPendingExpenses();

	public List<Expense> fetchAllPendingExpensesDetails();

	public Expense updateExpense(Expense updateExpense);

	public List<Expense> fetchAllExpensesDetails();

	public List<Expense> filterAllExpenses(String filter);

	public Expense findExpenseById(int id); 
}
