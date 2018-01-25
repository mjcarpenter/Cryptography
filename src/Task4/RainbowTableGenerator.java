package Task4;

import Task3.SHA1Library;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Generates a rainbow table.
 * @author Matthew Carpenter 14012396
 */
public class RainbowTableGenerator {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final RainbowTableLibrary rainbowTableLibrary;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Creates a new RainbowTableGenerator with the provided parameters.
     * @param characterSet the character set for the password space
     * @param maxPasswordLength the maximum length of passwords in the password space
     */
    public RainbowTableGenerator(byte[] characterSet, int maxPasswordLength) {
        this.rainbowTableLibrary = new RainbowTableLibrary(characterSet, maxPasswordLength);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">

    /**
     * Generates a rainbow table with the provided parameters.
     * @param tableSize the number of desired chains in the table
     * @param chainLength the length of the chains in the table
     * @return a HashMap representing the table, where the keys are the ends of the chain and the values are the starts
     */
    public HashMap<String, String> generateTable(int tableSize, int chainLength) {
        HashMap<String, String> rainbowTable = new HashMap();
        // For size of requested table
        for (int i = 0; i < tableSize; i++) {
            // Create HashSet to keep track of passwords in chain
            HashSet<String> currentChain = new HashSet();
            boolean duplicate = true;
            String initialPassword = "";
            // Generate random password that is not already a start
            while (duplicate) {
                initialPassword = this.rainbowTableLibrary.generateRandomPassword();
                duplicate = rainbowTable.containsValue(initialPassword);
            }
            String currentPassword = initialPassword;
            String currentHash = SHA1Library.SHA1(currentPassword);
            boolean valid = true;
            // For the length of the chain
            for (int j = 0; j < chainLength; j++) {
                // Add password to chain list
                currentChain.add(currentPassword);
                // Reduce current hash
                currentPassword = this.rainbowTableLibrary.reduce(currentHash, j);
                // If password is already in chain, set invalid and break loop
                if (currentChain.contains(currentPassword)) {
                    valid = false;
                    break;
                }
                // Hash current password
                currentHash = SHA1Library.SHA1(currentPassword);
            }
            // Check that generated chain is valid and that final hash is not already chain end
            valid = valid && !rainbowTable.containsKey(currentHash);
            // If valid chain
            if (valid) {
                // Add chain to table
                rainbowTable.put(currentHash, initialPassword);
                if (i % 1000 == 0) {
                    System.out.println("Generated chain " + i);
                }
            } else {
                // Start this loop index again
                i--;
            }
        }
        // Return generated table
        return rainbowTable;
    }
    
    /**
     * Extends the provided rainbow table by adding more chains to the end.
     * @param initialTable the rainbow table to extend
     * @param tableSize the number of desired chains in the table
     * @param chainLength the length of the chains in the table
     * @return a HashMap representing the table, where the keys are the ends of the chain and the values are the starts
     */
    public HashMap<String, String> extendTable(HashMap<String, String> initialTable, int tableSize, int chainLength) {
        // For size of requested table
        for (int i = initialTable.size(); i < tableSize; i++) {
            // Create HashSet to keep track of passwords in chain
            HashSet<String> currentChain = new HashSet();
            boolean duplicate = true;
            String initialPassword = "";
            // Generate random password that is not already a start
            while (duplicate) {
                initialPassword = this.rainbowTableLibrary.generateRandomPassword();
                duplicate = initialTable.containsValue(initialPassword);
            }
            String currentPassword = initialPassword;
            String currentHash = SHA1Library.SHA1(currentPassword);
            boolean valid = true;
            // For the length of the chain
            for (int j = 0; j < chainLength; j++) {
                // Add password to chain list
                currentChain.add(currentPassword);
                // Reduce current hash
                currentPassword = this.rainbowTableLibrary.reduce(currentHash, j);
                // If password is already in chain, set invalid and break loop
                if (currentChain.contains(currentPassword)) {
                    valid = false;
                    break;
                }
                // Hash current password
                currentHash = SHA1Library.SHA1(currentPassword);
            }
            // Check that generated chain is valid and that final hash is not already chain end
            valid = valid && !initialTable.containsKey(currentHash);
            // If valid chain
            if (valid) {
                // Add chain to table
                initialTable.put(currentHash, initialPassword);
                System.out.println("Added new chain");
                if (i % 1000 == 0) {
                    System.out.println("Generated chain " + i);
                }
            } else {
                // Start this loop index again
                i--;
            }
        }
        // Return generated table
        return initialTable;
    }
    
    // </editor-fold>
    
}
