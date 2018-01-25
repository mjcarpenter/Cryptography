package Task2;

/**
 * Represents the result of a BCH code check.
 * @author Matthew Carpenter 14012396
 */
public enum BCHResult {

    /**
     * The code is valid.
     */
    VALID,

    /**
     * There is a single error in the code.
     */
    SINGLE_ERROR,
    
    /**
     * There are two errors in the code.
     */
    DOUBLE_ERROR,
    
    /**
     * There are more than two errors in the code.
     */
    MORE_THAN_TWO_ERRORS,
    
    /**
     * The code was not provided in the correct format.
     */
    INCORRECT_FORMAT,
    
}
