package Task3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Offers functions related to the SHA1 hashing algorithm.
 * @author Matthew Carpenter 14012396
 */
public class SHA1Library {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    // used to extract hex values, index corresponds to value
    private final static char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Hashes the provided string with the SHA-1 hashing algorithm.
     * Provided by Rong Yang.
     * @param text the string to hash
     * @return the SHA-1 hash of the string; or ERROR if there was an issue
     */
    public static String SHA1(String text)  { 
        try {
            // initialise hasher
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            // get hash
            byte[] hash = md.digest();
            // convert to hex string and return
            return bytesToHex(hash);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return "ERROR";
        }
    }
    
    private static String bytesToHex(byte[] bytes) {
        // create result array, 1 byte = 2 chars
        char[] hexChars = new char[bytes.length * 2];
        // for each byte
        for (int i = 0; i < bytes.length; i++) {
            // get least significant byte
            int hexPair = bytes[i] & 0xFF;
            // get each hex value
            hexChars[i * 2] = SHA1Library.HEX_ARRAY[hexPair >>> 4];
            hexChars[i * 2 + 1] = SHA1Library.HEX_ARRAY[hexPair & 0x0F];
        }
        // create string and return
        return new String(hexChars);
    }
    
    // </editor-fold>
    
}
