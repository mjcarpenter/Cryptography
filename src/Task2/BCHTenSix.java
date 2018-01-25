package Task2;

import Task1.ModCalculator;

/**
 * Checks a BCH code.
 * @author Matthew Carpenter 14012396
 */
public class BCHTenSix {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * The value returned by the encode method when the provided number is in the incorrect format.
     */
    public static final String ENCODE_RESULT_INCORRECT_FORMAT = "Incorrect format";
    
    /**
     * The value returned by the encode method when the provided number is unusable for encoding.
     */
    public static final String ENCODE_RESULT_UNUSABLE_NUMBER = "Unusable number";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has a single error.
     */
    public static final String DECODE_ERROR_DETAILS_SINGLE_ERROR = "Single error at position %d with magnitude %d.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has a double error.
     */
    public static final String DECODE_ERROR_DETAILS_DOUBLE_ERROR = "Double error at position %d with magnitude %d and at position %d with magnitude %d.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has more than two errors, determined by the lack of a square root.
     */
    public static final String DECODE_ERROR_DETAILS_SQUARE_ROOT_ERROR = "More than two errors. There is no modular square root.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has more than two errors, determined by an invalid position calculated.
     */
    public static final String DECODE_ERROR_DETAILS_POSITION_ERROR = "More than two errors. i or j was 0.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has more than two errors, determined by a digit being corrected to an invalid number.
     */
    public static final String DECODE_ERROR_DETAILS_SINGLE_CORRECTION_ERROR = "More than two errors. Detected single error but digit was corrected to more than 9.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number has more than two errors, determined by a digit being corrected to an invalid number.
     */
    public static final String DECODE_ERROR_DETAILS_DOUBLE_CORRECTION_ERROR = "More than two errors. Detected double error but digit was corrected to more than 9.";
    
    /**
     * The value of the DecodeResult's error details property returned by the decode method when the provided number is in the incorrect format.
     */
    public static final String DECODE_ERROR_DETAILS_INCORRECT_FORMAT = "Incorrect format.";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Encodes the passed string by calculating the final 4 digits and returning the composite string.
     * @param digits the string to encode
     * @return the encoded string, "UNUSABLE NUMBER" if the number cannot be used, or "INCORRECT FORMAT" if the passed string is incorrect
     */
    public static String encode(String digits) {
        // check string length is correct
        if (digits.length() != 6) {
            return BCHTenSix.ENCODE_RESULT_INCORRECT_FORMAT;
        }
        try {
            // parse the digits from the string
            int[] digitArray = new int[6];
            for (int i = 0; i < 6; i++) {
                digitArray[i] = Integer.parseInt(digits.substring(i, i + 1));
            }
            // calculate the 4 final digits
            int d7 = getD7(digitArray);
            int d8 = getD8(digitArray);
            int d9 = getD9(digitArray);
            int d10 = getD10(digitArray);
            // if any of the calculated digits are 10, disregard number as unusable
            if (d7 == 10 || d8 == 10 || d9 == 10 || d10 == 10) {
                return BCHTenSix.ENCODE_RESULT_UNUSABLE_NUMBER;
            }
            return digits + d7 + d8 + d9 + d10;
        }
        // if one of the characters is not a valid int
        catch (NumberFormatException ex) {
            return BCHTenSix.ENCODE_RESULT_INCORRECT_FORMAT;
        }
    }
    
    /**
     * Decodes the passed string, returning the details as a DecodeResult.
     * @param digits the string to decode
     * @return a DecodeResult holding the result and details of the decoding
     */
    public static DecodeResult decode(String digits) {
        // check string length is correct
        if (digits.length() != 10) {
            return new DecodeResult(BCHResult.INCORRECT_FORMAT, "Incorrect format.");
        }
        try {
            // parse the digits from the string
            int[] digitArray = new int[10];
            for (int i = 0; i < 10; i++) {
                digitArray[i] = Integer.parseInt(digits.substring(i, i + 1));
            }
            // calculate syndromes
            int s1 = getSyndrome(digitArray, 1);
            int s2 = getSyndrome(digitArray, 2);
            int s3 = getSyndrome(digitArray, 3);
            int s4 = getSyndrome(digitArray, 4);
            // if all syndromes are 0, code is valid
            if ((s1 + s2 + s3 + s4) == 0) {
                return new DecodeResult(BCHResult.VALID, new int[] {s1, s2, s3, s4});
            }
            // calculate p, q, r
            int p = ModCalculator.Modulus((int)(Math.pow(s2, 2) - (s1 * s3)), 11);
            int q = ModCalculator.Modulus((int)((s1 * s4) - (s2 * s3)), 11);
            int r = ModCalculator.Modulus((int)(Math.pow(s3, 2) - (s2 * s4)), 11);
            // calculate square root of Q^2 - 4PR
            int rootCheck = ModCalculator.GetSquareRoot(((q * q) - 4 * p * r), 11);
            // calculate error positions
            int i = ModCalculator.Division(-q + rootCheck, 2 * p, 11);
            int j = ModCalculator.Division(-q - rootCheck, 2 * p, 11);
            int singleErrorPos = ModCalculator.Division(s2, s1, 11);
            // if p, q and r are 0 and error position is valid
            if (p == 0 && q == 0 && r == 0 && singleErrorPos > 0) {
                // correct wrong digit
                digitArray[singleErrorPos - 1] = ModCalculator.Modulus(digitArray[singleErrorPos - 1] - s1, 11);
                // check that the correction has not invalidated the code
                if (!correctionCheck(digitArray)) {
                    return new DecodeResult(BCHResult.MORE_THAN_TWO_ERRORS, new int[] {s1, s2, s3, s4}, p, q, r, i, j, DECODE_ERROR_DETAILS_SINGLE_CORRECTION_ERROR);
                } else {
                    // create corrected string
                    String newDigits = "";
                    for (int k = 0; k < digitArray.length; k++) {
                        newDigits += digitArray[k];
                    }
                    return new DecodeResult(BCHResult.SINGLE_ERROR, newDigits, new int[] {s1, s2, s3, s4}, p, q, r, singleErrorPos, s1, String.format(DECODE_ERROR_DETAILS_SINGLE_ERROR, new Object[] {singleErrorPos, s1}));
                }
            // if there is no square root
            } else if (rootCheck == -1) {
                return new DecodeResult(BCHResult.MORE_THAN_TWO_ERRORS, new int[] {s1, s2, s3, s4}, p, q, r, i, j, DECODE_ERROR_DETAILS_SQUARE_ROOT_ERROR);
            // if one of the calculated error positions was invalid
            } else if (i == 0 || j == 0 || singleErrorPos == 0) {
                return new DecodeResult(BCHResult.MORE_THAN_TWO_ERRORS, new int[] {s1, s2, s3, s4}, p, q, r, i, j, DECODE_ERROR_DETAILS_POSITION_ERROR);
            } else {
                // calculcate error magnitudes
                int b = ModCalculator.Division((i * s1) - s2, i - j, 11);
                int a = ModCalculator.Modulus(s1 - b, 11);
                // correct wrong digits
                digitArray[i - 1] = ModCalculator.Modulus(digitArray[i - 1] - a, 11);
                digitArray[j - 1] = ModCalculator.Modulus(digitArray[j - 1] - b, 11);
                // check that the correction has not invalidated the code
                if (!correctionCheck(digitArray)) {
                    return new DecodeResult(BCHResult.MORE_THAN_TWO_ERRORS, new int[] {s1, s2, s3, s4}, p, q, r, i, j, a, b, DECODE_ERROR_DETAILS_DOUBLE_CORRECTION_ERROR);
                }
                else {
                    // create corrected string
                    String newDigits = "";
                    for (int index = 0; index < digitArray.length; index++) {
                        newDigits += digitArray[index];
                    }
                    return new DecodeResult(BCHResult.DOUBLE_ERROR, newDigits, new int[] {s1, s2, s3, s4}, p, q, r, i, j, a, b, String.format(BCHTenSix.DECODE_ERROR_DETAILS_DOUBLE_ERROR, new Object[] {i, a, j, b}));
                }
            }
        }
        // if one of the characters is not a valid int
        catch (NumberFormatException ex) {
            return new DecodeResult(BCHResult.INCORRECT_FORMAT, DECODE_ERROR_DETAILS_INCORRECT_FORMAT);
        }
    }
    
    // Calculates the 7th digit.
    private static int getD7(int[] digitArray) {
        return ModCalculator.Modulus(4 * digitArray[0] + 10 * digitArray[1] + 9 * digitArray[2] + 2 * digitArray[3] + digitArray[4] + 7 * digitArray[5],  11);
    }
    
    // Calculates the 8th digit.
    private static int getD8(int[] digitArray) {
        return ModCalculator.Modulus(7 * digitArray[0] + 8 * digitArray[1] + 7 * digitArray[2] + digitArray[3] + 9 * digitArray[4] + 6 * digitArray[5], 11);
    }
    
    // Calculates the 9th digit.
    private static int getD9(int[] digitArray) {
        return ModCalculator.Modulus(9 * digitArray[0] + digitArray[1] + 7 * digitArray[2] + 8 * digitArray[3] + 7 * digitArray[4] + 7 * digitArray[5], 11);
    }
    
    // Calculates the 10th digit.
    private static int getD10(int[] digitArray) {
        return ModCalculator.Modulus(digitArray[0] + 2 * digitArray[1] + 9 * digitArray[2] + 10 * digitArray[3] + 4 * digitArray[4] + digitArray[5], 11);
    }
    
    // Calculates the syndrome identified by the provided index.
    private static int getSyndrome(int[] digits, int syndromeIndex) {
        int total = 0;
        for (int i = 0; i < digits.length; i++) {
            total += digits[i] * ModCalculator.Modulus((int)Math.pow(i + 1, syndromeIndex - 1), 11);
        }
        return ModCalculator.Modulus(total, 11);
    }
    
    // Checks that none of the values in the passed array are more than 9.
    private static boolean correctionCheck(int[] digitArray) {
        for (int i = 0; i < digitArray.length; i++) {
            if (digitArray[i] > 9) {
                return false;
            }
        }
        return true;
    }
    
    // </editor-fold>
    
}
