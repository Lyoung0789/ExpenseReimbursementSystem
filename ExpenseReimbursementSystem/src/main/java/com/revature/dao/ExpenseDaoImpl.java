package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Expense;
import com.revature.util.ConnectionFactory;


public class ExpenseDaoImpl implements ExpenseDao {

	@Override
	public List<Expense> getPendingExpenses(int user_id) {
		
		List<Expense> pendingExpenses = new ArrayList<>();
//		System.out.println("We mad eit here... finally adn the user_id associated to it is " + user_id);
		String sql = "SELECT  * from ers_expenses where user_id =? and status = 'Pending'";
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id")
					);
			pendingExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pendingExpenses;
	}

	@Override
	public Expense insertNewExpense(Expense newExpense) {

		String sql = "insert into ers_expenses (total, category, date_from, date_to, date_requested, status, denied, user_id, description) values (?,?,?,?, current_timestamp,?, ?, ?, ?)";
		try(Connection conn = ConnectionFactory.getConnection()){
//			Date dateFrom = new simpleDateFormat("mm/DD/");
//			LocalDate dateTo = LocalDate.parse(newExpense.getDateTo());
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setDouble(1, newExpense.getTotal());
			ps.setString(2, newExpense.getCategory());
			ps.setDate(3, java.sql.Date.valueOf(newExpense.getDateFrom()));
			ps.setDate(4, java.sql.Date.valueOf(newExpense.getDateTo()));
			ps.setString(5, newExpense.getStatus());
			ps.setBoolean(6, newExpense.isDenied());
			ps.setInt(7, newExpense.getUser_id());
			ps.setString(8, newExpense.getDescription());
			
			
			ps.execute(); 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return newExpense;
	}

	@Override
	public List<Expense> getPastExpenses(int user_id) {
		String sql = "select * from ers_expenses where user_id = ?  and status in ('Accepted', 'Denied')";
		List<Expense> pastExpenses = new ArrayList<>();
		System.out.println("Did we make it in here?");
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id")
					);
			pastExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pastExpenses;
	}

	@Override
	public Expense findAnExpense(int id) {
		String sql ="select * from ers_expenses where expense_id = ?";
		Expense e = null; 
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				 e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id")
					);
			
			}
			
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
		return e;
	}

	@Override
	public List<Expense> getAllPendingExpenses() {
		List<Expense> pendingExpenses = new ArrayList<>();
		String sql = "SELECT  * from ers_expenses where status = 'Pending'";
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id")
					);
			pendingExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pendingExpenses;
	}

	@Override
	public List<Expense> fetchAllPendingExpensesDetails() {
		System.out.println("DId i make it here?");
		List<Expense> pendingExpenses = new ArrayList<>();
		String sql = "select e.*, u.f_name, u.l_name from ers_expenses e inner join ers_user u on e.user_id = u.user_id where status='Pending'";
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id"), 
						rs.getString("f_name"),
						rs.getString("l_name")
					);
			pendingExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pendingExpenses;
	}

	@Override
	public Expense updateExpense(Expense updateExpense) {
		//Callable statement

		try(Connection conn = ConnectionFactory.getConnection()){
			CallableStatement stmt = conn.prepareCall("call update_expense(?,?,?)"); 

			stmt.setString(1, updateExpense.getStatus());
			stmt.setBoolean(2, updateExpense.isDenied());
			stmt.setInt(3, updateExpense.getExpense_id());
			
			stmt.execute();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("WE used a callable statememnt!");
		return updateExpense;
	}

	@Override
	public List<Expense> fetchAllExpensesDetails() {
	
		List<Expense> allExpenses = new ArrayList<>();
		String sql = "select e.*, u.f_name, u.l_name from ers_expenses e inner join ers_user u on e.user_id = u.user_id ";
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id"), 
						rs.getString("f_name"),
						rs.getString("l_name")
					);
			allExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allExpenses;
	}

	@Override
	public List<Expense> filterAllExpenses(String filter) {

		List<Expense> filteredExpenses = new ArrayList<>();
		String sql = "select e.*, u.f_name, u.l_name from ers_expenses e inner join ers_user u on e.user_id = u.user_id where status =?";
				
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, filter); 
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				Expense e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id"), 
						rs.getString("f_name"),
						rs.getString("l_name")
					);
			filteredExpenses.add(e); 
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return filteredExpenses;
	}

	@Override
	public Expense findExpenseById(int id) {
		String sql= "select * from ers_expenses where expense_id = ?"; 
		Expense e= null; 
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); 
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				e = new Expense(
						rs.getDouble("total"),
						rs.getString("category"), 
						rs.getString("description"),
						rs.getString("date_from"),
						rs.getString("date_to"), 
						rs.getString("date_requested"),
						rs.getString("status"), 
						rs.getBoolean("denied"), 
						rs.getInt("user_id"), 
						rs.getInt("expense_id") 

					);
			}
			
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		return e;
	}

}
