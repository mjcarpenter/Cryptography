package Task2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Matthew Carpenter 14012396
 */
public class BCHTenSixTest {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String ENCODE_VALID_INPUT = "123454";
    private static final String ENCODE_VALID_OUTPUT = "1234544768";
    private static final String ENCODE_INCORRECT_FORMAT_INPUT = "123f54";
    private static final String ENCODE_UNUSABLE_NUMBER_INPUT = "123456";
    
    private static final String DECODE_SINGLE_ERROR = "1234444768";
    private static final String DECODE_DOUBLE_ERROR = "1234444568";
    private static final String DECODE_SQUARE_ROOT_ERROR = "1234444561";
    private static final String DECODE_POSITION_ERROR = "1234444560";
    private static final String DECODE_SINGLE_CORRECTION_ERROR = "0000065705";
    private static final String DECODE_DOUBLE_CORRECTION_ERROR = "9234444560";
    private static final String DECODE_INCORRECT_FORMAT = "92344f4560";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public BCHTenSixTest() {
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
     * Test of encode method, of class BCHTenSix.
     */
    @Test
    public void testEncode_Valid() {
        System.out.println("encode_Valid");
        String result = BCHTenSix.encode(BCHTenSixTest.ENCODE_VALID_INPUT);
        assertEquals(BCHTenSixTest.ENCODE_VALID_OUTPUT, result);
    }
    
    /**
     * Test of encode method, of class BCHTenSix.
     */
    @Test
    public void testEncode_IncorrectFormat() {
        System.out.println("encode_IncorrectFormat");
        String result = BCHTenSix.encode(BCHTenSixTest.ENCODE_INCORRECT_FORMAT_INPUT);
        assertEquals(BCHTenSix.ENCODE_RESULT_INCORRECT_FORMAT, result);
    }
    
    /**
     * Test of encode method, of class BCHTenSix.
     */
    @Test
    public void testEncode_UnusableNumber() {
        System.out.println("encode_UnusableNumber");
        String result = BCHTenSix.encode(BCHTenSixTest.ENCODE_UNUSABLE_NUMBER_INPUT);
        assertEquals(BCHTenSix.ENCODE_RESULT_UNUSABLE_NUMBER, result);
    }

    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_CorrectCode() {
        System.out.println("decode_CorrectCode");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.ENCODE_VALID_OUTPUT);
        assertEquals(BCHResult.VALID, result.getResult());
        Assert.assertArrayEquals(new int[] {0, 0, 0, 0}, result.getSyndromes());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_SingleError() {
        System.out.println("decode_SingleError");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_SINGLE_ERROR);
        assertEquals(BCHResult.SINGLE_ERROR, result.getResult());
        assertEquals(ENCODE_VALID_OUTPUT, result.getCorrected());
        Assert.assertArrayEquals(new int[] {10, 6, 8, 7}, result.getSyndromes());
        assertEquals(0, result.getP());
        assertEquals(0, result.getQ());
        assertEquals(0, result.getR());
        assertEquals(5, result.getI());
        assertEquals(10, result.getA());
        assertEquals(String.format(BCHTenSix.DECODE_ERROR_DETAILS_SINGLE_ERROR, new Object[] {result.getI(), result.getA()}), result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_DoubleError() {
        System.out.println("decode_DoubleError");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_DOUBLE_ERROR);
        assertEquals(BCHResult.DOUBLE_ERROR, result.getResult());
        assertEquals(ENCODE_VALID_OUTPUT, result.getCorrected());
        Assert.assertArrayEquals(new int[] {8, 1, 1, 6}, result.getSyndromes());
        assertEquals(4, result.getP());
        assertEquals(3, result.getQ());
        assertEquals(6, result.getR());
        assertEquals(8, result.getI());
        assertEquals(5, result.getJ());
        assertEquals(9, result.getA());
        assertEquals(10, result.getB());
        assertEquals(String.format(BCHTenSix.DECODE_ERROR_DETAILS_DOUBLE_ERROR, new Object[] {result.getI(), result.getA(), result.getJ(), result.getB()}), result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_MoreThanTwoErrors_NoSquareRoot() {
        System.out.println("decode_MoreThanTwoErrors_NoSquareRoot");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_SQUARE_ROOT_ERROR);
        assertEquals(BCHResult.MORE_THAN_TWO_ERRORS, result.getResult());
        Assert.assertArrayEquals(new int[] {1, 8, 5, 2}, result.getSyndromes());
        assertEquals(4, result.getP());
        assertEquals(6, result.getQ());
        assertEquals(9, result.getR());
        assertEquals(6, result.getI());
        assertEquals(9, result.getJ());
        assertEquals(BCHTenSix.DECODE_ERROR_DETAILS_SQUARE_ROOT_ERROR, result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_MoreThanTwoErrors_PositionError() {
        System.out.println("decode_MoreThanTwoErrors_PositionError");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_POSITION_ERROR);
        assertEquals(BCHResult.MORE_THAN_TWO_ERRORS, result.getResult());
        Assert.assertArrayEquals(new int[] {0, 9, 4, 3}, result.getSyndromes());
        assertEquals(4, result.getP());
        assertEquals(8, result.getQ());
        assertEquals(0, result.getR());
        assertEquals(9, result.getI());
        assertEquals(0, result.getJ());
        assertEquals(BCHTenSix.DECODE_ERROR_DETAILS_POSITION_ERROR, result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_MoreThanTwoErrors_SingleCorrectionError() {
        System.out.println("decode_MoreThanTwoErrors_SingleCorrectionError");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_SINGLE_CORRECTION_ERROR);
        assertEquals(BCHResult.MORE_THAN_TWO_ERRORS, result.getResult());
        Assert.assertArrayEquals(new int[] {1, 1, 1, 1}, result.getSyndromes());
        assertEquals(0, result.getP());
        assertEquals(0, result.getQ());
        assertEquals(0, result.getR());
        assertEquals(0, result.getI());
        assertEquals(0, result.getJ());
        assertEquals(BCHTenSix.DECODE_ERROR_DETAILS_SINGLE_CORRECTION_ERROR, result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_MoreThanTwoErrors_DoubleCorrectionError() {
        System.out.println("decode_MoreThanTwoErrors_DoubleCorrectionError");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_DOUBLE_CORRECTION_ERROR);
        assertEquals(BCHResult.MORE_THAN_TWO_ERRORS, result.getResult());
        Assert.assertArrayEquals(new int[] {8, 6, 1, 0}, result.getSyndromes());
        assertEquals(6, result.getP());
        assertEquals(5, result.getQ());
        assertEquals(1, result.getR());
        assertEquals(7, result.getI());
        assertEquals(5, result.getJ());
        assertEquals(5, result.getA());
        assertEquals(3, result.getB());
        assertEquals(BCHTenSix.DECODE_ERROR_DETAILS_DOUBLE_CORRECTION_ERROR, result.getErrorDetails());
    }
    
    /**
     * Test of decode method, of class BCHTenSix.
     */
    @Test
    public void testDecode_IncorrectFormat() {
        System.out.println("decode_IncorrectFormat");
        DecodeResult result = BCHTenSix.decode(BCHTenSixTest.DECODE_INCORRECT_FORMAT);
        assertEquals(BCHResult.INCORRECT_FORMAT, result.getResult());
        assertEquals(BCHTenSix.DECODE_ERROR_DETAILS_INCORRECT_FORMAT, result.getErrorDetails());
    }
    
    // </editor-fold>
    
    // </editor-fold>
    
}
