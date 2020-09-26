
package factorysystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


public class ValidationTest {
    
    public ValidationTest() {
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

    @org.junit.Test
    public void testValidateName() {
        System.out.println("validateName");
        String word = "Ahmed Rabie";
        boolean expResult = true;
        boolean result = Validation.validateName(word);
        assertEquals(expResult, result);
        /////////////////////////////////////////////////////////////
        word = "Ahmed2";
        expResult = false;
        result = Validation.validateName(word);
        assertEquals(expResult, result);
        ////////////////////////////////////////////////////////////
          word = "2Ahmed";
        expResult = false;
        result = Validation.validateName(word);
        assertEquals(expResult, result);
        ////////////////////////////////////////////////////////////
        word = "Ahmed Rabie!";
        expResult = false;
        result = Validation.validateName(word);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validateFractionNumber method, of class Validation.
     */
    @org.junit.Test
    public void testValidateFractionNumber() {
        System.out.println("validateFractionNumber");
        String number = "ahmed";
        boolean expResult = false;
        boolean result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
        //////////////////////////////////////////////////////////////////////////////////
        number = "0123.2";
        expResult = true;
        result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
        /////////////////////////////////////////////////////////////////////////////////
        number = "123.2.2";
        expResult = false;
        result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
//        ////////////////////////////////////////////////////////////////////////////////
        number = "123.2?";
        expResult = false;
        result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
//        ///////////////////////////////////////////////////////////////////////////////
        number = "01 23.2";
        expResult = false;
        result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
//        //////////////////////////////////////////////////////////////////////////////
        number = "a123.2";
        expResult = false;
        result = Validation.validateFractionNumber(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateIntegerNumber method, of class Validation.
     */
    @org.junit.Test
    public void testValidateIntegerNumber() {
        System.out.println("validateIntegerNumber");
        String number = "ahmed";
        boolean expResult = false;
        boolean result = Validation.validateIntegerNumber(number);
        assertEquals(expResult, result);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        assertTrue(Validation.validateIntegerNumber("012"));
        assertTrue(Validation.validateIntegerNumber("12"));
        assertTrue(Validation.validateIntegerNumber("0012"));
        assertTrue(Validation.validateIntegerNumber("012"));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        assertFalse(Validation.validateIntegerNumber("a012"));
        assertFalse(Validation.validateIntegerNumber("12a"));
        assertFalse(Validation.validateIntegerNumber("12.01"));
        assertFalse(Validation.validateIntegerNumber("12.001.001"));
        assertFalse(Validation.validateIntegerNumber("12!"));
        assertFalse(Validation.validateIntegerNumber("12="));
    }
    
}
