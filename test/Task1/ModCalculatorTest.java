package Task1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Matthew Carpenter 14012396
 */
public class ModCalculatorTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ModCalculatorTest() {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // <editor-fold defaultstate="collapsed" desc="Test Lifecycle">
    
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
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests">
    
    /**
     * Test of Modulus method, of class ModCalculator.
     */
    @Test
    public void testModulus_Positive() {
        System.out.println("Modulus_Positive");
        int value = 13;
        int modulus = 11;
        int expResult = 2;
        int result = ModCalculator.Modulus(value, modulus);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of Modulus method, of class ModCalculator.
     */
    @Test
    public void testModulus_Negative() {
        System.out.println("Modulus_Negative");
        int value = -3;
        int modulus = 11;
        int expResult = 8;
        int result = ModCalculator.Modulus(value, modulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of Addition method, of class ModCalculator.
     */
    @Test
    public void testAddition() {
        System.out.println("Addition");
        int value1 = 5;
        int value2 = 7;
        int modulus = 11;
        int expResult = 1;
        int result = ModCalculator.Addition(value1, value2, modulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of Multiplication method, of class ModCalculator.
     */
    @Test
    public void testMultiplication() {
        System.out.println("Multiplication");
        int value1 = 5;
        int value2 = 3;
        int modulus = 11;
        int expResult = 4;
        int result = ModCalculator.Multiplication(value1, value2, modulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of Division method, of class ModCalculator.
     */
    @Test
    public void testDivision() {
        System.out.println("Division");
        int dividend = 5;
        int divisor = 2;
        int modulus = 11;
        int expResult = 8;
        int result = ModCalculator.Division(dividend, divisor, modulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetModularInverse method, of class ModCalculator.
     */
    @Test
    public void testGetModularInverse() {
        System.out.println("GetModularInverse");
        int value = 3;
        int modulus = 11;
        int expResult = 4;
        int result = ModCalculator.GetModularInverse(value, modulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetSquareRoot method, of class ModCalculator.
     */
    @Test
    public void testGetSquareRoot() {
        System.out.println("GetSquareRoot");
        int value = 3;
        int modulus = 11;
        int expResult = 5;
        int result = ModCalculator.GetSquareRoot(value, modulus);
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
