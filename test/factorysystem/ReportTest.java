
package factorysystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportTest {
     Report instance = new Report(null, null, null);
    public ReportTest() {
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
     * Test of getFrom method, of class Report.
     */
    @Test
    public void testGetFrom() {
        System.out.println("getFrom");
        String expResult = "Ahmed";
        instance.setFrom(expResult);
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTo method, of class Report.
     */
    @Test
    public void testGetTo() {
        System.out.println("getTo");
        String expResult = "Ali";
        instance.setTo(expResult);
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Report.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Abdo";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
