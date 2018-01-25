package Task1;

/**
 * Verifies credit card numbers.
 * @author Matthew Carpenter 14012396
 */
public class CreditCardVerifier {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    /**
     * The value of the message property if the number provided was valid.
     */
    public static final String MESSAGE_VALID = "The credit card number is valid.";
    
    /**
     * The value of the message property if the number provided was invalid.
     */
    public static final String MESSAGE_INVALID = "The credit card number is not valid.";
    
    /**
     * The value of the message property if the number provided was not the correct length.
     */
    public static final String MESSAGE_INCORRECT_LENGTH = "The entered number must be 16 digits long.";
    
    /**
     * The value of the message property if the number provided contained invalid characters.
     */
    public static final String MESSAGE_INVALID_CHARACTERS = "The entered number contains invalid characters.";
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    /**
     * Creates a new CreditCardVerifier for the specified string
     * @param creditCard the string for which to verify
     */
    public CreditCardVerifier(String creditCard) {
        this.creditCard = creditCard;
        VerifyCreditCard();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String creditCard;

    /**
     * Returns the credit card property.
     * @return the credit card property
     */
    public String getCreditCard() {
        return this.creditCard;
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
    
    // Verifies the creditCard string, setting isValid and message in the process.
    private void VerifyCreditCard() {
        // remove any spaces in the string
        String number = this.creditCard.replaceAll(" ", "");
        // check that string is the correct length
        if (number.length() != 16) {
            this.isValid = false;
            this.message = CreditCardVerifier.MESSAGE_INCORRECT_LENGTH;
            return;
        }
        int[] digits = new int[16];
        try {
            // parse each character as an int
            for (int i = 0; i < 16; i++) {
                digits[i] = Integer.parseInt(number.substring(i, i+1));
                // if is at an even index
                if (i % 2 == 0) {
                    // double and reduce below 10 before adding to array
                    int doubled = digits[i] * 2;
                    if (doubled >= 10) {
                        doubled -= 9;
                    }
                    digits[i] = doubled;
                }
            }
            // total up the sum of the digits
            int total = 0;
            for (int i = 0; i < 16; i++) {
                total += digits[i];
            }
            // calculate the remainder when mod 10
            int remainder = total % 10;
            // set isValid to true/false depending on the result
            this.isValid = remainder == 0;
        }
        // if one of the characters is not a valid int
        catch (NumberFormatException ex) {
            this.isValid = false;
            this.message = CreditCardVerifier.MESSAGE_INVALID_CHARACTERS;
            return;
        }
        // set the message according to the validity of the number
        if (this.isValid) {
            this.message = CreditCardVerifier.MESSAGE_VALID;
        } else {
            this.message = CreditCardVerifier.MESSAGE_INVALID;
        }
    }
    
    // </editor-fold>
    
}
