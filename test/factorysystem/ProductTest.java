
package factorysystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    Product instance = new Product(0, null, 0, 0, 0, 0);
    public ProductTest() {
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
     * Test of getProductID method, of class Product.
     */
    @Test
    public void testGetProductID() {
        System.out.println("getProductID");
        int expResult = 20;
        instance.setProductID(expResult);
        int result = instance.getProductID();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProductName method, of class Product.
     */
    @Test
    public void testGetProductName() {
        System.out.println("getProductName");
        String expResult = "Product1";
        instance.setProductName(expResult);
        String result = instance.getProductName();
        assertEquals(expResult, result);
    }
/**
     * Test of getDistributorID method, of class Product.
     */
    @Test
    public void testGetDistributorID() {
        System.out.println("getDistributorID");
        int expResult = 5;
        instance.setSupplierID(expResult);
        int result = instance.getDistributorID();
        assertEquals(expResult, result);
    }

      /**
     * Test of getUnitPrice method, of class Product.
     */
    @Test
    public void testGetUnitPrice() {
        System.out.println("getUnitPrice");
        double expResult = 50.0;
        instance.setUnitPrice(expResult);
        double result = instance.getUnitPrice();
        assertEquals(expResult, result, 0.0);
      
    }

    /**
     * Test of getRate method, of class Product.
     */
    @Test
    public void testGetRate() {
        System.out.println("getRate");
        double expResult = 1.0;
        instance.setRate(expResult);
        double result = instance.getRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        double expResult = 2000.0;
        instance.setQuantity(expResult);
        double result = instance.getQuantity();
        assertEquals(expResult, result, 0.0);
    }
  
}
