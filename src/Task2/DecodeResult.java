package Task2;

/**
 * The result of a BCH decode.
 * @author Matthew Carpenter 14012396
 */
public class DecodeResult {
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    /**
     * Creates a new DecodeResult with the provided result and error details.
     * @param result the result of the decode
     * @param syndromes the syndromes calculated from the code
     */
    public DecodeResult(BCHResult result, int[] syndromes) {
        this(result, "", syndromes, -1, -1, -1, -1, -1, -1, -1, "");
    }
    
    /**
     * Creates a new DecodeResult with the provided result and error details.
     * @param result the result of the decode
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, String errorDetails) {
        this(result, "", null, -1, -1, -1, -1, -1, -1, -1, errorDetails);
    }
    
    /**
     * Creates a new DecodeResult with the provided result and syndromes.
     * @param result the result of the decode
     * @param syndromes the syndromes calculated from the code
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, int[] syndromes, String errorDetails) {
        this(result, "", syndromes, -1, -1, -1, -1, -1, -1, -1, errorDetails);
    }
    
    /**
     * Creates a new DecodeResult to hold the provided data.
     * @param result the result of the decode
     * @param corrected the corrected code
     * @param syndromes the syndromes calculated from the code
     * @param p p calculated from the code
     * @param q q calculated from the code
     * @param r r calculated from the code
     * @param i i calculated from the code
     * @param a a calculated from the code
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, String corrected, int[] syndromes, int p, int q, int r, int i, int a, String errorDetails) {
        this(result, corrected, syndromes, p, q, r, i, -1, a, -1, errorDetails);
    }
    
    /**
     * Creates a new DecodeResult to hold the provided data.
     * @param result the result of the decode
     * @param syndromes the syndromes calculated from the code
     * @param p p calculated from the code
     * @param q q calculated from the code
     * @param r r calculated from the code
     * @param i i calculated from the code
     * @param j j calculated from the code
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, int[] syndromes, int p, int q, int r, int i, int j, String errorDetails) {
        this(result, "", syndromes, p, q, r, i, j, -1, -1, errorDetails);
    }
    
    /**
     * Creates a new DecodeResult to hold the provided data.
     * @param result the result of the decode
     * @param syndromes the syndromes calculated from the code
     * @param p p calculated from the code
     * @param q q calculated from the code
     * @param r r calculated from the code
     * @param i i calculated from the code
     * @param j j calculated from the code
     * @param a a calculated from the code
     * @param b b calculated from the code
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, int[] syndromes, int p, int q, int r, int i, int j, int a, int b, String errorDetails) {
        this(result, "", syndromes, p, q, r, i, j, a, b, errorDetails);
    }
    
    /**
     * Creates a new DecodeResult to hold the provided data.
     * @param result the result of the decode
     * @param corrected the corrected code
     * @param syndromes the syndromes calculated from the code
     * @param p p calculated from the code
     * @param q q calculated from the code
     * @param r r calculated from the code
     * @param i i calculated from the code
     * @param j j calculated from the code
     * @param a a calculated from the code
     * @param b b calculated from the code
     * @param errorDetails details of the error
     */
    public DecodeResult(BCHResult result, String corrected, int[] syndromes, int p, int q, int r, int i, int j, int a, int b, String errorDetails) {
        this.result = result;
        this.corrected = corrected;
        this.syndromes = syndromes;
        this.p = p;
        this.q = q;
        this.r = r;
        this.i = i;
        this.j = j;
        this.a = a;
        this.b = b;
        this.errorDetails = errorDetails;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    
    private final BCHResult result;
    
    /**
     * Returns the result property.
     * @return the result property
     */
    public BCHResult getResult() {
        return this.result;
    }
    
    private final String corrected;
    
    /**
     * Returns the corrected property.
     * @return the corrected property
     */
    public String getCorrected() {
        return this.corrected;
    }
    
    private final int[] syndromes;

    /**
     * Returns the syndromes property.
     * @return the syndromes property
     */
    public int[] getSyndromes() {
        return this.syndromes;
    }
    
    private final int p;

    /**
     * Returns the p property.
     * @return the p property
     */
    public int getP() {
        return this.p;
    }
    
    private final int q;

    /**
     * Returns the q property.
     * @return the q property
     */
    public int getQ() {
        return this.q;
    }
    
    private final int r;

    /**
     * Returns the r property.
     * @return the r property
     */
    public int getR() {
        return this.r;
    }
    
    private final int i;

    /**
     * Returns the i property.
     * @return the i property
     */
    public int getI() {
        return this.i;
    }
    
    private final int j;

    /**
     * Returns the j property.
     * @return the j property
     */
    public int getJ() {
        return this.j;
    }
    
    private final int a;

    /**
     * Returns the a property.
     * @return the a property
     */
    public int getA() {
        return this.a;
    }
    
    private final int b;

    /**
     * Returns the b property.
     * @return the b property
     */
    public int getB() {
        return this.b;
    }
    
    private final String errorDetails;
    
    /**
     * Returns the error details property.
     * @return the error details property
     */
    public String getErrorDetails() {
        return this.errorDetails;
    }
    
    // </editor-fold>
    
}
