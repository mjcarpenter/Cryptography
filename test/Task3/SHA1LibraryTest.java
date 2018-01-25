package Task3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Matthew Carpenter 14012396
 */
public class SHA1LibraryTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public SHA1LibraryTest() {
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
     * Test of SHA1 method, of class SHA1Library.
     */
    @Test
    public void testSHA1() {
        System.out.println("SHA1");
        String text = "password";
        String expResult = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
        String result = SHA1Library.SHA1(text);
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
