package come.revature;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Manager;
import com.revature.services.ManagerService;


public class ManagerServiceTest {
	@InjectMocks
	ManagerService m = new ManagerService();
	
	@Mock
	ManagerDaoImpl mDaoImpl = new ManagerDaoImpl(); 
	
	@InjectMocks
	Manager newManager = new Manager("junit_manager", "junit_pw", 1, "first", "last", "city", "state");

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetManagerByLogin() {
		
		when(mDaoImpl.getManagerByLogin("junit_manager", "junit_pw")).thenReturn(newManager); 
		assertNotNull(m.getManagerByLogin("junit_manager", "junit_pw")); 
		verify(mDaoImpl).getManagerByLogin("junit_manager", "junit_pw"); 
	}

}
