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

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTest {
	@InjectMocks
	UserService u = new UserService(); 
	
	@Mock
	UserDaoImpl uDaoImpl = new UserDaoImpl(); 

	@InjectMocks
	User newUser = new User("junit", "junit_pw", 0, null, null, null, null, null, null, 0);
	

	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUserByLogin() {
		when(uDaoImpl.getUserByLogin("junit", "junit_pw")).thenReturn(newUser); 
		assertNotNull(uDaoImpl.getUserByLogin("junit", "junit_pw")); 
		verify(uDaoImpl).getUserByLogin("junit", "junit_pw"); 
		
	}
	
	@Test
	public void testPostNewUser() {
		when(uDaoImpl.postNewUser(newUser)).thenReturn(newUser); 
		assertEquals(newUser, uDaoImpl.postNewUser(newUser)); 
		verify(uDaoImpl).postNewUser(newUser); 
		
	}

}
