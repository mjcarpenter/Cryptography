package Task3;

import javax.swing.SwingWorker;

/**
 * Runs a brute force crack with the provided parameters.
 * @author Matthew Carpenter 14012396
 */
public class BruteForceWorker extends SwingWorker<String, String> {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private BruteForceCracker cracker;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates a new BruteForceWorker to crack the provided hash with the provided character set and under the specified length.
     * The hash index and worker index should be set correctly if multiple workers are running at once or cracking the same hash.
     * @param hash the SHA-1 hash to crack
     * @param characters the character set to use to attempt to crack the hash
     * @param maxLength the maximum length of password that should be checked
     * @param hashIndex the index of the hash passed
     * @param workerIndex the index of the worker that is cracking the hash passed
     */
    public BruteForceWorker(String hash, byte[] characters, int maxLength, int hashIndex, int workerIndex) {
        this.characterSet = characters;
        this.hash = hash;
        this.maxLength = maxLength;
        this.hashIndex = hashIndex;
        this.workerIndex = workerIndex;
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
    
    private final int hashIndex;

    /**
     * Returns the hash index property.
     * @return the hash index property
     */
    public int getHashIndex() {
        return this.hashIndex;
    }
    
    private final int workerIndex;

    /**
     * Returns the worker index property.
     * @return the worker index property
     */
    public int getWorkerIndex() {
        return this.workerIndex;
    }
    
    private boolean passwordFound;

    /**
     * Returns the password found property.
     * @return the password found property
     */
    public boolean getPasswordFound() {
        return this.passwordFound;
    }
    
    private String crackedPassword;

    /**
     * Returns the cracked password property.
     * @return the cracked password property
     */
    public String getCrackedPassword() {
        return this.crackedPassword;
    }
    
    private long passwordsChecked;

    /**
     * Returns the passwords checked property.
     * @return the passwords checked property
     */
    public long getPasswordsChecked() {
        return this.passwordsChecked;
    }
    
    private String timeTaken;

    /**
     * Returns the time taken property.
     * @return the time taken property
     */
    public String getTimeTaken() {
        return this.timeTaken;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    protected String doInBackground() throws Exception {
        // create cracker object and start crack
        this.cracker = new BruteForceCracker(this.hash, this.characterSet, this.maxLength);
        this.passwordFound = cracker.crack();
        // if the worker has not been cancelled
        if (!isCancelled()) {
            // set values
            this.crackedPassword = cracker.getPassword();
            this.passwordsChecked = cracker.getPasswordsChecked();
            this.timeTaken = cracker.getTimeTaken();
        }
        return null;
    }
    
    /**
     * Cancels the worker and the cracking process.
     * @return false if the task could not be cancelled, typically because it has already completed normally; true otherwise
     */
    public boolean cancel() {
        if (this.cracker != null) {
            this.cracker.cancel();
        }
        return cancel(true);
    }
    
    // </editor-fold>
    
}
