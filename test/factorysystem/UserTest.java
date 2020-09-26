
package factorysystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
     User instance = new User(0, null, null, null, 0, 0, 0, 0, 0, null);
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        int expResult = 1;
        instance.setUserID(expResult);
        int result = instance.getUserID();
        assertEquals(expResult, result);
    }

    

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        String expResult = "Ibrahim";
        instance.setUserName(expResult);
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of getUserpassword method, of class User.
     */
    @Test
    public void testGetUserpassword() {
        System.out.println("getUserpassword");
        String expResult = "xyz";
        instance.setUserpassword(expResult);
        String result = instance.getUserpassword();
        assertEquals(expResult, result);
    }

   
    /**
     * Test of getAddress method, of class User.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "Badr";
        instance.setAddress(expResult);
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHoursWorked method, of class User.
     */
    @Test
    public void testGetHoursWorked() {
        System.out.println("getHoursWorked");
        double expResult = 20.0;
        instance.setHoursWorked(expResult);
        double result = instance.getHoursWorked();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSectionID method, of class User.
     */
    @Test
    public void testGetSectionID() {
        System.out.println("getSectionID");
        int expResult = 2;
        instance.setSectionID(expResult);
        int result = instance.getSectionID();
        assertEquals(expResult, result);
      
    }

   
    /**
     * Test of getRating method, of class User.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        double expResult = 9.0;
        instance.setRating(expResult);
        double result = instance.getRating();
        assertEquals(expResult, result, 0.0);
    }

   
    /**
     * Test of getPhoneNumber method, of class User.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        int expResult = 123156789;
        instance.setPhoneNumber(expResult);
        int result = instance.getPhoneNumber();
        assertEquals(expResult, result);
    }

  
    /**
     * Test of getSalary method, of class User.
     */
    @Test
    public void testGetSalary() {
        System.out.println("getSalary");
        double expResult = 2500.0;
        instance.setSalary(expResult);
        double result = instance.getSalary();
        assertEquals(expResult, result, 0.0);
    }
  /**
     * Test of getJobID method, of class User.
     */
    @Test
    public void testGetJobID() {
        System.out.println("getJobID");
        String expResult = "Player";
        instance.setJobID(expResult);
        String result = instance.getJobID();
        assertEquals(expResult, result);
    }
}
