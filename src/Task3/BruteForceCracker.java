package Task3;

import java.util.Arrays;

/**
 * Cracks a SHA-1 hash by brute force.
 * @author Matthew Carpenter 14012396
 */
public class BruteForceCracker {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private int currentLength = 1;
    private boolean cancel;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates a new SHA1BruteForceCracker to crack using the provided values.
     * @param hash the SHA-1 hash to crack
     * @param characterSet the character set to use to attempt to crack the hash
     * @param maxLength the maximum length of password that should be checked
     */
    public BruteForceCracker(String hash, byte[] characterSet, int maxLength) {
        this.currentLength = 1;
        this.hash = hash;
        this.characterSet = characterSet;
        this.maxLength = maxLength;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String hash;

    /**
     * Returns the hash property.
     * @return the hash property
     */
    public String getHash() {
        return this.hash;
    }
    
    private final byte[] characterSet;

    /**
     * Returns the character set property.
     * @return the character set property
     */
    public byte[] getCharacterSet() {
        return this.characterSet;
    }
    
    private final int maxLength;

    /**
     * Returns the max length property.
     * @return the max length property
     */
    public int getMaxLength() {
        return this.maxLength;
    }
    
    private String password;

    /**
     * Returns the password property.
     * @return the password property
     */
    public String getPassword() {
        return this.password;
    }
    
    private long passwordsChecked;

    /**
     * Returns the passwords checked property.
     * @return the passwords checked property
     */
    public long getPasswordsChecked() {
        return this.passwordsChecked;
    }
    
    private long nanoTimeTaken;
    
    /**
     * Returns the time taken property.
     * @return the time taken property
     */
    public String getTimeTaken() {
        double seconds = nanoTimeTaken / 1000000000.0;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        // depending on the time, return a string representing the most readable value
        if (hours > 1) {
            return hours + " hours";
        } else if (minutes > 1) {
            return minutes + " minutes";
        } else if (seconds > 0.01) {
            return seconds + " seconds";
        }
        return nanoTimeTaken + " nanoseconds";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    /**
     * Attempts to crack the provided hash with the character set up to the maximum length.
     * @return true if the password is cracked successfully; otherwise false
     */
    public boolean crack() {
        long startTime = System.nanoTime();
        byte[] letters = new byte[this.maxLength];
        Arrays.fill(letters, (byte)'\u0000');
        letters[0] = this.characterSet[0];
        String currentHash;
        boolean passwordFound = false;
        String current = "";
        // while the password has not been found, the password length is not more than the max length and the process has not been cancelled
        while (!passwordFound && this.currentLength <= this.maxLength && !this.cancel) {
            // extract the actual password from the array (removes unneeded chars that would affect hashing)
            byte[] currentLetters = Arrays.copyOfRange(letters, 0, this.currentLength);
            // create string and hash
            current = new String(currentLetters);
            currentHash = SHA1Library.SHA1(current);
            // check there has not been an error during hashing
            if ("ERROR".equals(currentHash)) {
                return false;
            }
            // check the returned hash against the required result
            this.passwordsChecked++;
            passwordFound = currentHash.equals(this.hash);
            // if not found, move to next string
            if (!passwordFound) {
                incrementPassword(letters);
            }
        }
        this.password = current;
        this.nanoTimeTaken = System.nanoTime() - startTime;
        return passwordFound;
    }
    
    /**
     * Cancels the current cracking operation.
     */
    public void cancel() {
        this.cancel = true;
    }
    
    private void incrementPassword(byte[] letters) {
        // loop over characters from right to left
        for (int i = this.currentLength - 1; i >= 0; i--) {
            // get index of current char in the set
            int charIndex = getIndexOfCharacter(letters[i]);
            // if current char is not last in set
            if (charIndex < this.characterSet.length - 1) {
                // set char to the next in the set and jump out of method
                letters[i] = this.characterSet[charIndex + 1];
                return;
            } else {
                // set char to first char in set
                letters[i] = this.characterSet[0];
                // if at very left of string
                if (i == 0) {
                    // increment length
                    this.currentLength++;
                    if (this.currentLength > this.maxLength) {
                        return;
                    }
                    // reset all characters to first in set
                    for (int j = 0; j < this.currentLength; j++) {
                        letters[j] = this.characterSet[0];
                    }
                }
            }
        }
    }
    
    private int getIndexOfCharacter(byte c) {
        // for every char in the set
        for (int i = 0; i < this.characterSet.length; i++) {
            // if char equals char at current index
            if (c == this.characterSet[i]) {
                return i;
            }
        }
        return -1;
    }
    
    // </editor-fold>
    
}
