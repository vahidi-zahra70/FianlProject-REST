package JavaClass;
import static org.junit.Assert.*;  

import org.junit.Test;

import HibernateManager.UserManager;


public class test1 {

	@Test  
	//testing for insert a new user
	public void testInsert(){ 
		UserManager MM=new UserManager();
		User user=new User("z.vahidi");
		assertTrue(MM.JUnitvalidationof_user(user));

	} 
	
	
	@Test  
	//testing for delete a user
	public void testDelete(){ 
		UserManager MM=new UserManager();
		User user=new User("s.amiri");
		assertFalse(MM.JUnitvalidationof_user(user));

	} 

//	@Test  
//	public void test2(){ 
//		Employee ee=new Employee("zahra",26,2000);
//
//		assertEquals(24000,ee.ComputeSalary());  
//		assertEquals(1000,ee.Tax());  
//	} 


}
