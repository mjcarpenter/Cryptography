package Task1;

/**
 * Performs simple calculations under modulus.
 * @author Matthew Carpenter 14012396
 */
public class ModCalculator {
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Returns the positive result of the modulo operation
     * @param value the number that is to be modulated
     * @param modulus the modulus for the operation
     * @return the modulus of value
     */
    public static int Modulus(int value, int modulus) {
        int modValue = value % modulus;
        if (modValue < 0) {
            modValue = modulus + modValue;
        }
        return modValue;
    }
    
    /**
     * Returns the addition of the two values provided under the modulus provided
     * @param value1 the first number to use in the addition
     * @param value2 the second number to use in the addition
     * @param modulus the modulus for the operation
     * @return the result of the value addition under modulus
     */
    public static int Addition(int value1, int value2, int modulus) {
        // mod values initially to avoid larger numbers
        int modValue1 = Modulus(value1, modulus);
        int modValue2 = Modulus(value2, modulus);
        // add numbers and return the modulus of the result
        int addition = modValue1 + modValue2;
        int result = Modulus(addition, modulus);
        return result;
    }
    
    /**
     * Returns the multiplication of the two values provided under the modulus provided
     * @param value1 the first number to use in the multiplication
     * @param value2 the second number to use in the multiplication
     * @param modulus the modulus for the operation
     * @return the result of the multiplication under modulus
     */
    public static int Multiplication(int value1, int value2, int modulus) {
        // mod values initially to avoid larger numbers
        int modValue1 = Modulus(value1, modulus);
        int modValue2 = Modulus(value2, modulus);
        // multiply numbers together and return the modulus of the result
        int multiplication = modValue1 * modValue2;
        int result = Modulus(multiplication, modulus);
        return result;
    }
    
    /**
     * Returns the value of the division of the two values provided under the modulus provided
     * @param dividend the number to be divided
     * @param divisor the number to divide with
     * @param modulus the modulus for the operation
     * @return the result of the division under modulus
     */
    public static int Division(int dividend, int divisor, int modulus) {
        // mod values initially to avoid larger numbers
        int modDividend = Modulus(dividend, modulus);
        int modDivisor = Modulus(divisor, modulus);
        // get inverse of the divisor under modulus
        int inverseDivisor = GetModularInverse(modDivisor, modulus);
        // multiply dividend and divisor's inverse together and return the modulus of the result
        int multiplication = modDividend * inverseDivisor;
        int result = Modulus(multiplication, modulus);
        return result;
    }
    
    /**
     * Returns the inverse of the value provided under the modulus provided
     * @param value the number to find the inverse of
     * @param modulus the modulus for the operation
     * @return the inverse of the value under modulus
     */
    public static int GetModularInverse(int value, int modulus) {
        // mod value initially to avoid larger numbers
        int modValue = Modulus(value, modulus);
        // loop from 0 to modulus - 1
        for (int i = 0; i < modulus; i++) {
            // multiply current value of i with the value
            int resolve = Multiplication(modValue, i, modulus);
            // if the result is 1, then i is the inverse of value
            if (resolve == 1) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the square root of the value provided under the modulus provided
     * @param value the number to find the square root of
     * @param modulus the modulus for the operation
     * @return the inverse of the square root under modulus; or -1 if it does not have an inverse
     */
    public static int GetSquareRoot(int value, int modulus) {
        // mod value initially to avoid larger numbers
        int modValue = Modulus(value, modulus);
        // loop from 0 to modulus - 1
        for (int i = 0; i < modulus; i++) {
            // square the current value of i
            int squared = Multiplication(i, i, modulus);
            // if the result is the required value, then i is the square root
            if (squared == modValue) {
                return i;
            }
        }
        return -1;
    }
    
    // </editor-fold>
    
}
