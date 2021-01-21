package come.revature;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.ExpenseDao;
import com.revature.dao.ExpenseDaoImpl;
import com.revature.models.Expense;
import com.revature.services.ExpenseService;

public class ExpenseServiceTest {
	@InjectMocks
	ExpenseService eService = new ExpenseService(); 
	@InjectMocks
	Expense newExpense = new Expense(100.99, null, null, null, null); 
	
	@Mock
	ExpenseDaoImpl eDaoImpl = new ExpenseDaoImpl(); 
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getPendingExpensesTest(){
		List<Expense> expenseList = new ArrayList<>(); 
		expenseList.add(newExpense); 
		when(eDaoImpl.getPendingExpenses(3)).thenReturn(expenseList); 	
		assertEquals(1, eService.getPendingExpenses(3).size()); 
		verify(eDaoImpl).getPendingExpenses(3); 

	}

	@Test
	public void insertNewExpenseTest(){
		when(eDaoImpl.insertNewExpense(newExpense)).thenReturn(newExpense); 
		assertEquals(newExpense, eService.insertNewExpense(newExpense)); 
		verify(eDaoImpl).insertNewExpense(newExpense); 
	}
	
	@Test
	public void getPastExpenseTest() {
		List<Expense> pastExpenses = new ArrayList<>(); 
		pastExpenses.add(newExpense); 
		when(eDaoImpl.getPastExpenses(1)).thenReturn(pastExpenses);
		assertEquals(1, eService.getPastExpenses(1).size());
	 
		verify(eDaoImpl).getPastExpenses(1); 
	}
	
	@Test
	public void  getAllpendingExpensesTest() {
		List<Expense> allPendingExpenses = new ArrayList<>(); 
		Expense anotherExpense = new Expense(100.99, null, null, null, null); 
		allPendingExpenses.add(newExpense); 
		allPendingExpenses.add(anotherExpense); 
		when(eDaoImpl.getAllPendingExpenses()).thenReturn(allPendingExpenses);
		assertEquals(2, eService.getAllPendingExpenses().size());

		verify(eDaoImpl).getAllPendingExpenses(); 
	}
	
	@Test
	public void fetchAllPendignExpensesTest() {
		List<Expense> allPendingExpenses = new ArrayList<>(); 
		Expense anotherExpense = new Expense(100.99, null, null, null, null); 
		allPendingExpenses.add(newExpense); 
		allPendingExpenses.add(anotherExpense); 
		when(eDaoImpl.fetchAllPendingExpensesDetails()).thenReturn(allPendingExpenses);
		assertEquals(2, eService.fetchAllPendingExpenses().size());
		verify(eDaoImpl).fetchAllPendingExpensesDetails(); 
	}
	
	@Test
	public void updateExpenseStatusTest() {
		
		when(eDaoImpl.updateExpense(newExpense)).thenReturn(newExpense); 
		assertEquals(newExpense, eService.updateExpenseStatus(newExpense)); 
		verify(eDaoImpl).updateExpense(newExpense); 

	}
	
	@Test
	public void fetchAllExpensesTest() {
		List<Expense> allExpenses = new ArrayList<>(); 
		Expense anotherExpense = new Expense(100.99, null, null, null, null); 
		Expense anotherExpense2= new Expense(200.99, null, null, null, null); 

		allExpenses.add(newExpense); 
		allExpenses.add(anotherExpense); 
		allExpenses.add(anotherExpense2); 
		when(eDaoImpl.fetchAllExpensesDetails()).thenReturn(allExpenses);
		assertEquals(3, eService.fetchAllExpenses().size());
		verify(eDaoImpl).fetchAllExpensesDetails();
	}
	
	@Test
	public void filterAllExpenseTest() {
		List<Expense> allFilteredExpenses = new ArrayList<>(); 
		Expense anotherExpense = new Expense(100.99, null, null, null, null,null, "Accepted" ,false, 0,0); 

		allFilteredExpenses.add(anotherExpense); 
	

		when(eDaoImpl.filterAllExpenses("Accepted")).thenReturn(allFilteredExpenses);
		assertEquals(1, eService.filterAllExpenses("Accepted").size());
		verify(eDaoImpl).filterAllExpenses("Accepted");
	}
	
	@Test
	public void findExpenseByIdTest() {
		when(eDaoImpl.findExpenseById(1000)).thenReturn(null); 
		assertNull(eService.findExpenseById(1000)); 
	}
	
	
}
