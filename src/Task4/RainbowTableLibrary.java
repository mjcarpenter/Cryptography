package Task4;

import java.math.BigInteger;
import java.util.Random;

/**
 * Offers functions related to rainbow tables.
 * @author Matthew Carpenter 14012396
 */
public class RainbowTableLibrary {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final Random random = new Random();
    
    private final byte[] characterSet;
    
    private final int maxPasswordLength;
    
    private final BigInteger[] totalPasswordCounts;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Creates a new RainbowTableLibrary with the provided parameters.
     * @param characterSet the character set for the password space
     * @param maxPasswordLength the maximum length of passwords in the password space
     */
    public RainbowTableLibrary(byte[] characterSet, int maxPasswordLength) {
        this.characterSet = characterSet;
        this.maxPasswordLength = maxPasswordLength;
        this.totalPasswordCounts = new BigInteger[maxPasswordLength + 2];
        // Calculate string length id bounds for use in idToPassword
        for (int i = 0; i < maxPasswordLength + 2; i++) {
            this.totalPasswordCounts[i] = getTotalPasswordCount(i - 1);
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Performs the reduction function for the provided parameters.
     * @param hash the hash to reduce
     * @param index the column index of the chain
     * @return the reduced password
     */
    public String reduce(String hash, int index) {
        // Convert hash to an integer value
        BigInteger bigInt = new BigInteger(hash, 16);
        // Add index to value
        bigInt = bigInt.add(BigInteger.valueOf(index));
        // Mod by the total password space size to obtain valid password id
        bigInt = bigInt.mod(this.totalPasswordCounts[maxPasswordLength + 1]);
        // Convert to password and return
        return idToPassword(bigInt);
    }
    
    private String idToPassword(BigInteger id) {
        String s = "";
        // For each possible character in the password
        for (int i = maxPasswordLength; i >= 0; i--) {
            BigInteger totalPasswordCountUpper = totalPasswordCounts[i + 1];
            BigInteger totalPasswordCountLower = totalPasswordCounts[i];
            // If id sits between bounds of lower length and current length
            if (id.compareTo(totalPasswordCountUpper) == -1 && (id.compareTo(totalPasswordCountLower) == 0 || id.compareTo(totalPasswordCountLower) == 1)) {
                // Subtract lower bound to obtain value of id in current length space
                BigInteger adjusted = id.subtract(totalPasswordCountLower);
                // Mod adjusted value by the size of the character set to obtain index of next character
                int modded = adjusted.mod(BigInteger.valueOf(characterSet.length)).intValue();
                // Add character to start of string
                s = (char)characterSet[modded] + s;
                // Divide current id by size of character set and then subtract 1 to obtain value for next character
                id = id.divide(BigInteger.valueOf(characterSet.length)).subtract(BigInteger.valueOf(1));
            }
        }
        // Return final string
        return s;
    }
    
    private BigInteger getTotalPasswordCount(int length) {
        BigInteger total = BigInteger.valueOf(0);
        // For 1 to maximum length
        for (int i = 1; i <= length; i++) {
            // Add size of character set to the power of i
            total = total.add(BigInteger.valueOf((int)Math.pow(characterSet.length, i)));
        }
        return total;
    }
    
    /**
     * Generates a random password from the password space.
     * @return the randomly generated password
     */
    public String generateRandomPassword() {
        // Generate random number to determine length (higher lengths weighted more highly)
        int rand = random.nextInt(getSum(maxPasswordLength)) + 1;
        int length = 0;
        int counter = 0;
        while (rand > counter) {
            length++;
            counter += length;
        }
        byte[] letters = new byte[length];
        // For each character in chosen length
        for (int i = 0; i < length; i++) {
            // Get random character from character set
            int randChar = random.nextInt(characterSet.length);
            letters[i] = characterSet[randChar];
        }
        // Return chosen characters as a String
        return new String(letters);
    }
    
    private int getSum(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number + getSum(number - 1);
        }
    }
    
    // </editor-fold>
    
}
