package com.revature.models;

public class Expense {
	private double total; 
	private String category; 
	private String description; 
	private String dateFrom; 
	private String dateTo; 
	private String dateRequested;
	private String status; 
	private boolean denied; 
	private int user_id; 
	private int expense_id;
	private String f_name; 
	private String l_name; 
	
	
	public Expense(double total, String category, String description, String dateFrom, String dateTo,
			String dateRequested, String status, boolean denied, int user_id, int expense_id, String f_name,
			String l_name) {
		super();
		this.total = total;
		this.category = category;
		this.description = description;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.dateRequested = dateRequested;
		this.status = status;
		this.denied = denied;
		this.user_id = user_id;
		this.expense_id = expense_id;
		this.f_name = f_name;
		this.l_name = l_name;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public Expense(double total, String category, String description, String dateFrom, String dateTo) {
		super(); 
		this.total = total;
		this.category = category;
		this.description = description;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	
	public Expense(double total, String category, String description, String dateFrom, String dateTo, String dateRequested,
			String status, boolean denied, int user_id, int expense_id) {
		super();
		this.total = total;
		this.category = category;
		this.description = description;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.dateRequested = dateRequested;
		this.status = status;
		this.denied = denied;
		this.user_id = user_id;
		this.expense_id = expense_id;
	}
	
	
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}


	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(String dateRequested) {
		this.dateRequested = dateRequested;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isDenied() {
		return denied;
	}
	public void setDenied(boolean denied) {
		this.denied = denied;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	@Override
	public String toString() {
		return "Expense [total=" + total + ", category=" + category + ", description=" + description + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", dateRequested=" + dateRequested + ", status=" + status
				+ ", denied=" + denied + ", user_id=" + user_id + ", expense_id=" + expense_id + "]";
	}
	

}
