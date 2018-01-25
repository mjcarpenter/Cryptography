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
public class SHA1BruteForceCrackerTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String HASH = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
    private static final byte[] CHARACTER_SET = "abcde".getBytes();
    
    private static final String HASH_AB = "da23614e02469a0d7c7bd1bdab5c9c474b1904dc";
    private static final String HASH_ZZ = "d7dacae2c968388960bf8970080a980ed5c5dcb7";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public SHA1BruteForceCrackerTest() {
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
     * Test of getHash method, of class BruteForceCracker.
     */
    @Test
    public void testGetHash() {
        System.out.println("getHash");
        BruteForceCracker instance = new BruteForceCracker(HASH, null, 0);
        String result = instance.getHash();
        assertEquals(HASH, result);
    }

    /**
     * Test of getCharacterSet method, of class BruteForceCracker.
     */
    @Test
    public void testGetCharacterSet() {
        System.out.println("getCharacterSet");
        BruteForceCracker instance = new BruteForceCracker("", CHARACTER_SET, 0);
        byte[] result = instance.getCharacterSet();
        assertArrayEquals(CHARACTER_SET, result);
    }

    /**
     * Test of getMaxLength method, of class BruteForceCracker.
     */
    @Test
    public void testGetMaxLength() {
        System.out.println("getMaxLength");
        int expResult = 5;
        BruteForceCracker instance = new BruteForceCracker("", null, expResult);
        int result = instance.getMaxLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class BruteForceCracker.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        BruteForceCracker instance = new BruteForceCracker("", null, 0);
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPasswordsChecked method, of class BruteForceCracker.
     */
    @Test
    public void testGetPasswordsChecked() {
        System.out.println("getPasswordsChecked");
        BruteForceCracker instance = new BruteForceCracker("", null, 0);
        long expResult = 0L;
        long result = instance.getPasswordsChecked();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeTaken method, of class BruteForceCracker.
     */
    @Test
    public void testGetTimeTaken() {
        System.out.println("getTimeTaken");
        BruteForceCracker instance = new BruteForceCracker("", null, 0);
        String expResult = "0 nanoseconds";
        String result = instance.getTimeTaken();
        assertEquals(expResult, result);
    }

    /**
     * Test of crack method, of class BruteForceCracker.
     */
    @Test
    public void testCrack_Crackable() {
        System.out.println("crack_Crackable");
        BruteForceCracker instance = new BruteForceCracker(HASH_AB, CHARACTER_SET, 2);
        boolean expResult = true;
        boolean result = instance.crack();
        assertEquals(expResult, result);
        assertEquals("ab", instance.getPassword());
        assertEquals(7, instance.getPasswordsChecked());
    }
    
    /**
     * Test of crack method, of class BruteForceCracker.
     */
    @Test
    public void testCrack_Uncrackable() {
        System.out.println("crack_Uncrackable");
        BruteForceCracker instance = new BruteForceCracker(HASH_ZZ, CHARACTER_SET, 2);
        boolean expResult = false;
        boolean result = instance.crack();
        assertEquals(expResult, result);
        assertEquals(30, instance.getPasswordsChecked());
    }

    /**
     * Test of cancel method, of class BruteForceCracker.
     */
    @Test
    public void testCancel() {
        System.out.println("cancel");
        BruteForceCracker instance = new BruteForceCracker(HASH_AB, CHARACTER_SET, 2);
        instance.cancel();
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
