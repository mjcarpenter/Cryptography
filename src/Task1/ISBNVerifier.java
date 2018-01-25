package Task1;

/**
 * Verifies ISBN numbers.
 * @author Matthew Carpenter 14012396
 */
public class ISBNVerifier {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * The value of the message property if the number provided was valid.
     */
    public static final String MESSAGE_VALID = "The ISBN number is valid.";
    
    /**
     * The value of the message property if the number provided was invalid.
     */
    public static final String MESSAGE_INVALID = "The ISBN number is not valid.";
    
    /**
     * The value of the message property if the number provided was not the correct length.
     */
    public static final String MESSAGE_INCORRECT_LENGTH = "The entered number must be 10 digits long.";
    
    /**
     * The value of the message property if the number provided contained invalid characters.
     */
    public static final String MESSAGE_INVALID_CHARACTERS = "The entered number contains invalid characters.";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates a new ISBNVerifier for the specified string
     * @param isbn the string for which to verify
     */
    public ISBNVerifier(String isbn) {
        this.isbn = isbn;
        VerifyISBN();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String isbn;

    /**
     * Returns the ISBN property.
     * @return the ISBN property
     */
    public String getISBN() {
        return this.isbn;
    }
    
    private boolean isValid;
    
    /**
     * Returns the is valid property.
     * @return the is valid property
     */
    public boolean getIsValid() {
        return this.isValid;
    }
    
    private String message;
    
    /**
     * Returns the message property.
     * @return the message property
     */
    public String getMessage() {
        return this.message;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // Verifies the isbn string, setting isValid and message in the process.
    private void VerifyISBN() {
        // remove any hyphens in the string
        String number = this.isbn.replaceAll("-", "");
        if (number.length() != 10) {
            this.isValid = false;
            this.message = ISBNVerifier.MESSAGE_INCORRECT_LENGTH;
            return;
        }
        int[] digits = new int[10];
        try {
            // parse each character as an int
            for (int i = 0; i < number.length(); i++) {
                String character = number.substring(i, i+1);
                // if character is an X, use 10 instead
                if ("X".equals(character)) {
                    digits[i] = 10;
                } else {
                    digits[i] = Integer.parseInt(character);
                }
            }
            // total up the sum of the digits times their position
            int total = 0;
            for (int i = 0; i < digits.length - 1; i++) {
                total += (i + 1) * digits[i];
            }
            // calculate remainder after mod 11 and check if that equals the last digit
            int remainder = total % 11;
            this.isValid = remainder == digits[9];
        }
        // if one of the characters is not a valid int
        catch (NumberFormatException ex) {
            this.isValid = false;
            this.message = ISBNVerifier.MESSAGE_INVALID_CHARACTERS;
            return;
        }
        // set the message according to the validity of the number
        if (this.isValid) {
            this.message = ISBNVerifier.MESSAGE_VALID;
        } else {
            this.message = ISBNVerifier.MESSAGE_INVALID;
        }
    }
    
    // </editor-fold>
    
}
