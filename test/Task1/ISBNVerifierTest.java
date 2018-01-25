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
public class ISBNVerifierTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String VALID_NUMBER = "0-9752298-0-X";
    private static final String INVALID_NUMBER = "0-9752298-0-2";
    private static final String INCORRECT_LENGTH_NUMBER = "0-97522968-0-X";
    private static final String INVALID_CHARACTERS_NUMBER = "0-975f298-0-X";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ISBNVerifierTest() {
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
     * Test of getISBN method, of class ISBNVerifier.
     */
    @Test
    public void testGetISBN() {
        System.out.println("getISBN");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.VALID_NUMBER);
        String expResult = ISBNVerifierTest.VALID_NUMBER;
        String result = instance.getISBN();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsValid method, of class ISBNVerifier.
     */
    @Test
    public void testGetIsValid_ValidNumber() {
        System.out.println("getIsValid_ValidNumber");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.VALID_NUMBER);
        boolean expResult = true;
        boolean result = instance.getIsValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getIsValid method, of class ISBNVerifier.
     */
    @Test
    public void testGetIsValid_InvalidNumber() {
        System.out.println("getIsValid_InvalidNumber");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.INVALID_NUMBER);
        boolean expResult = false;
        boolean result = instance.getIsValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessage method, of class ISBNVerifier.
     */
    @Test
    public void testGetMessage_ValidNumber() {
        System.out.println("getMessage_ValidNumber");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.VALID_NUMBER);
        String expResult = ISBNVerifier.MESSAGE_VALID;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class ISBNVerifier.
     */
    @Test
    public void testGetMessage_InvalidNumber() {
        System.out.println("getMessage_InvalidNumber");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.INVALID_NUMBER);
        String expResult = ISBNVerifier.MESSAGE_INVALID;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class ISBNVerifier.
     */
    @Test
    public void testGetMessage_IncorrectLength() {
        System.out.println("getMessage_IncorrectLength");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.INCORRECT_LENGTH_NUMBER);
        String expResult = ISBNVerifier.MESSAGE_INCORRECT_LENGTH;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class ISBNVerifier.
     */
    @Test
    public void testGetMessage_InvalidCharacters() {
        System.out.println("getMessage_InvalidCharacters");
        ISBNVerifier instance = new ISBNVerifier(ISBNVerifierTest.INVALID_CHARACTERS_NUMBER);
        String expResult = ISBNVerifier.MESSAGE_INVALID_CHARACTERS;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
