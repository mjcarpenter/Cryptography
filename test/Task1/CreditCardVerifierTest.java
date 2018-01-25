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
public class CreditCardVerifierTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String VALID_NUMBER = "5105105105105100";
    private static final String INVALID_NUMBER = "5105145105105100";
    private static final String INCORRECT_LENGTH_NUMBER = "51051451051";
    private static final String INVALID_CHARACTERS_NUMBER = "5105105a05105100";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public CreditCardVerifierTest() {
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
     * Test of getCreditCard method, of class CreditCardVerifier.
     */
    @Test
    public void testGetCreditCard() {
        System.out.println("getCreditCard");
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.VALID_NUMBER);
        String result = instance.getCreditCard();
        assertEquals(CreditCardVerifierTest.VALID_NUMBER, result);
    }

    /**
     * Test of getIsValid method, of class CreditCardVerifier.
     */
    @Test
    public void testGetIsValid_ValidNumber() {
        System.out.println("getIsValid_ValidNumber");
        boolean expResult = true;
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.VALID_NUMBER);
        boolean result = instance.getIsValid();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getIsValid method, of class CreditCardVerifier.
     */
    @Test
    public void testGetIsValid_InvalidNumber() {
        System.out.println("getIsValid_InvalidNumber");
        boolean expResult = false;
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.INVALID_NUMBER);
        boolean result = instance.getIsValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessage method, of class CreditCardVerifier.
     */
    @Test
    public void testGetMessage_ValidNumber() {
        System.out.println("getMessage_ValidNumber");
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.VALID_NUMBER);
        String expResult = CreditCardVerifier.MESSAGE_VALID;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class CreditCardVerifier.
     */
    @Test
    public void testGetMessage_InvalidNumber() {
        System.out.println("getMessage_InvalidNumber");
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.INVALID_NUMBER);
        String expResult = CreditCardVerifier.MESSAGE_INVALID;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class CreditCardVerifier.
     */
    @Test
    public void testGetMessage_IncorrectLength() {
        System.out.println("getMessage_IncorrectLength");
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.INCORRECT_LENGTH_NUMBER);
        String expResult = CreditCardVerifier.MESSAGE_INCORRECT_LENGTH;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMessage method, of class CreditCardVerifier.
     */
    @Test
    public void testGetMessage_InvalidCharacters() {
        System.out.println("getMessage_InvalidCharacters");
        CreditCardVerifier instance = new CreditCardVerifier(CreditCardVerifierTest.INVALID_CHARACTERS_NUMBER);
        String expResult = CreditCardVerifier.MESSAGE_INVALID_CHARACTERS;
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
