package Task2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Matthew Carpenter 14012396
 */
public class DecodeResultTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public DecodeResultTest() {
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
     * Test of getResult method, of class DecodeResult.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        DecodeResult instance = new DecodeResult(BCHResult.VALID, new int[] {0, 0, 0, 0});
        BCHResult result = instance.getResult();
        assertEquals(BCHResult.VALID, result);
    }

    /**
     * Test of getCorrected method, of class DecodeResult.
     */
    @Test
    public void testGetCorrected() {
        System.out.println("getCorrected");
        String expResult = "Corrected";
        DecodeResult instance = new DecodeResult(BCHResult.VALID, expResult, new int[] {0, 0, 0, 0}, 0, 0, 0, 0, 0, "");
        String result = instance.getCorrected();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSyndromes method, of class DecodeResult.
     */
    @Test
    public void testGetSyndromes() {
        System.out.println("getSyndromes");
        int[] expResult = new int[] {1, 2, 3, 4};
        DecodeResult instance = new DecodeResult(BCHResult.VALID, expResult);
        int[] result = instance.getSyndromes();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getP method, of class DecodeResult.
     */
    @Test
    public void testGetP() {
        System.out.println("getP");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, "", new int[] {0, 0, 0, 0}, expResult, 0, 0, 0, 0, "");
        int result = instance.getP();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQ method, of class DecodeResult.
     */
    @Test
    public void testGetQ() {
        System.out.println("getQ");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, "", new int[] {0, 0, 0, 0}, 0, expResult, 0, 0, 0, "");
        int result = instance.getQ();
        assertEquals(expResult, result);
    }

    /**
     * Test of getR method, of class DecodeResult.
     */
    @Test
    public void testGetR() {
        System.out.println("getR");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, "", new int[] {0, 0, 0, 0}, 0, 0, expResult, 0, 0, "");
        int result = instance.getR();
        assertEquals(expResult, result);
    }

    /**
     * Test of getI method, of class DecodeResult.
     */
    @Test
    public void testGetI() {
        System.out.println("getI");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, "", new int[] {0, 0, 0, 0}, 0, 0, 0, expResult, 0, "");
        int result = instance.getI();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJ method, of class DecodeResult.
     */
    @Test
    public void testGetJ() {
        System.out.println("getJ");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, new int[] {0, 0, 0, 0}, 0, 0, 0, 0, expResult, "");
        int result = instance.getJ();
        assertEquals(expResult, result);
    }

    /**
     * Test of getA method, of class DecodeResult.
     */
    @Test
    public void testGetA() {
        System.out.println("getA");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, "", new int[] {0, 0, 0, 0}, 0, 0, 0, 0, expResult, "");
        int result = instance.getA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getB method, of class DecodeResult.
     */
    @Test
    public void testGetB() {
        System.out.println("getB");
        int expResult = 9;
        DecodeResult instance = new DecodeResult(BCHResult.VALID, new int[] {0, 0, 0, 0}, 0, 0, 0, 0, 0, 0, expResult, "");
        int result = instance.getB();
        assertEquals(expResult, result);
    }

    /**
     * Test of getErrorDetails method, of class DecodeResult.
     */
    @Test
    public void testGetErrorDetails() {
        System.out.println("getErrorDetails");
        String expResult = "Error Details";
        DecodeResult instance = new DecodeResult(BCHResult.VALID, expResult);
        String result = instance.getErrorDetails();
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
