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
public class BruteForceWorkerTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String HASH = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
    private static final byte[] CHARACTER_SET = "abcde".getBytes();
    
    private static final String HASH_AB = "da23614e02469a0d7c7bd1bdab5c9c474b1904dc";
    private static final String HASH_ZZ = "d7dacae2c968388960bf8970080a980ed5c5dcb7";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public BruteForceWorkerTest() {
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
     * Test of getHash method, of class BruteForceWorker.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        BruteForceWorker instance = new BruteForceWorker(HASH, null, 0, 0, 0);
        String result = instance.getHash();
        assertEquals(HASH, result);
    }

    /**
     * Test of getCharacterSet method, of class BruteForceWorker.
     */
    @Test
    public void testGetCharacterSet() {
        System.out.println("getCharacterSet");
        BruteForceWorker instance = new BruteForceWorker("", CHARACTER_SET, 0, 0, 0);
        byte[] result = instance.getCharacterSet();
        assertArrayEquals(CHARACTER_SET, result);
    }

    /**
     * Test of getMaxLength method, of class BruteForceWorker.
     */
    @Test
    public void testGetMaxLength() {
        System.out.println("getMaxLength");
        int expResult = 5;
        BruteForceWorker instance = new BruteForceWorker("", null, expResult, 0, 0);
        int result = instance.getMaxLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHashIndex method, of class BruteForceWorker.
     */
    @Test
    public void testGetHashIndex() {
        System.out.println("getHashIndex");
        int expResult = 5;
        BruteForceWorker instance = new BruteForceWorker("", null, 0, expResult, 0);
        int result = instance.getHashIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkerIndex method, of class BruteForceWorker.
     */
    @Test
    public void testGetWorkerIndex() {
        System.out.println("getWorkerIndex");
        int expResult = 5;
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, expResult);
        int result = instance.getWorkerIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPasswordFound method, of class BruteForceWorker.
     */
    @Test
    public void testGetPasswordFound() {
        System.out.println("getPasswordFound");
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, 0);
        boolean expResult = false;
        boolean result = instance.getPasswordFound();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCrackedPassword method, of class BruteForceWorker.
     */
    @Test
    public void testGetCrackedPassword() {
        System.out.println("getCrackedPassword");
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, 0);
        String expResult = null;
        String result = instance.getCrackedPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPasswordsChecked method, of class BruteForceWorker.
     */
    @Test
    public void testGetPasswordsChecked() {
        System.out.println("getPasswordsChecked");
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, 0);
        long expResult = 0L;
        long result = instance.getPasswordsChecked();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeTaken method, of class BruteForceWorker.
     */
    @Test
    public void testGetTimeTaken() {
        System.out.println("getTimeTaken");
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, 0);
        String expResult = null;
        String result = instance.getTimeTaken();
        assertEquals(expResult, result);
    }

    /**
     * Test of doInBackground method, of class BruteForceWorker.
     * @throws java.lang.Exception
     */
    @Test
    public void testDoInBackground_Crackable() throws Exception {
        System.out.println("doInBackground_Crackable");
        BruteForceWorker instance = new BruteForceWorker(HASH_AB, CHARACTER_SET, 2, 0, 0);
        instance.execute();
        while (!instance.isDone()) {}
        assertEquals(true, instance.getPasswordFound());
        assertEquals("ab", instance.getCrackedPassword());
        assertEquals(7, instance.getPasswordsChecked());
    }
    
    /**
     * Test of doInBackground method, of class BruteForceWorker.
     * @throws java.lang.Exception
     */
    @Test
    public void testDoInBackground_Uncrackable() throws Exception {
        System.out.println("doInBackground_Uncrackable");
        BruteForceWorker instance = new BruteForceWorker(HASH_ZZ, CHARACTER_SET, 2, 0, 0);
        instance.execute();
        while (!instance.isDone()) {}
        assertEquals(false, instance.getPasswordFound());
    }

    /**
     * Test of cancel method, of class BruteForceWorker.
     */
    @Test
    public void testCancel() {
        System.out.println("cancel");
        BruteForceWorker instance = new BruteForceWorker("", null, 0, 0, 0);
        boolean expResult = true;
        boolean result = instance.cancel();
        assertEquals(expResult, result);
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
