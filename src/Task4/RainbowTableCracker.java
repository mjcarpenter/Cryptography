package Task4;

import Task3.SHA1Library;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Cracks SHA1 hashes via rainbow tables.
 * @author Matthew Carpenter 14012396
 */
public class RainbowTableCracker {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final RainbowTableLibrary rainbowTableLibrary;
    
    private final HashMap<String, String> rainbowTable;
    private final int chainLength;
    private final byte[] characterSet;
    private final int maxPasswordLength;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Creates a new RainbowTableCracker with the provided parameters.
     * @param rainbowTable the rainbow table to crack with
     * @param chainLength the length of the chains in the table
     * @param characterSet the character set for the password space
     * @param maxPasswordLength the maximum length of passwords in the password space
     */
    public RainbowTableCracker(HashMap<String, String> rainbowTable, int chainLength, byte[] characterSet, int maxPasswordLength) {
        this.rainbowTable = rainbowTable;
        this.chainLength = chainLength;
        this.characterSet = characterSet;
        this.maxPasswordLength = maxPasswordLength;
        this.rainbowTableLibrary = new RainbowTableLibrary(this.characterSet, this.maxPasswordLength);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Cracks the provided hash.
     * @param hash the SHA1 hash to crack
     * @return the original string if cracked; otherwise null
     */
    public String crack(String hash) {
        String currentPassword = "";
        String currentHash;
        HashSet<String> matchedChainEnds = new HashSet<>();
        // Start by checking if password is at last position, then check down to the first
        for (int i = this.chainLength - 1; i >= 0; i--) {
            currentHash = hash;
            // Finish the end of the chain
            for (int j = i; j < this.chainLength; j++) {
                currentPassword = this.rainbowTableLibrary.reduce(currentHash, j);
                currentHash = SHA1Library.SHA1(currentPassword);
            }
            // For each chain
            for (String key : rainbowTable.keySet()) {
                if (matchedChainEnds.contains(key)) {
                    continue;
                }
                // If current hash is at end of chain
                if (currentHash.equals(key)) {
                    matchedChainEnds.add(key);
                    String attempt = getPassword(hash, this.rainbowTable.get(key));
                    // If hash was found, return password
                    if (attempt != null) {
                        return currentPassword;
                    }
                }
            }
        }
        // If not found in table, return null
        return null;
    }
    
    private String getPassword(String hash, String chainStart) {
        String currentHash;
        String currentPassword = chainStart;
        // For the length of the chain
        for (int i = 0; i < chainLength; i++) {
            currentHash = SHA1Library.SHA1(currentPassword);
            // If current hash is the required one, return the relevant password
            if (hash.equals(currentHash)) {
                return currentPassword;
            }
            currentPassword = rainbowTableLibrary.reduce(currentHash, i);
        }
        // If not found, return null
        return null;
    }
    
    // </editor-fold>
    
}
