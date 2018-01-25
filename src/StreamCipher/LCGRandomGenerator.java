/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreamCipher;

import java.math.BigInteger;

/**
 *
 * @author Matt
 */
public class LCGRandomGenerator {
    
    // <editor-fold defaultstate="collapsed" desc="Variables">
    
    private final BigInteger MODULUS = new BigInteger("2000000000000000000000000000000000000");
    private final BigInteger MULTIPLIER = new BigInteger("1664525");
    private final BigInteger INCREMENT = new BigInteger("1013904223");
    
    private BigInteger last;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public LCGRandomGenerator(long seed) {
        this.last = BigInteger.valueOf(seed);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    public int getNextInt(int upper) {
        BigInteger next = MULTIPLIER.multiply(last).add(INCREMENT).mod(MODULUS);
        last = next;
        return next.mod(BigInteger.valueOf(upper)).intValue();
    }
    
    public int getNextBit() {
        int val = getNextInt(Integer.MAX_VALUE);
        return val % 2;
    }
    
    // </editor-fold>
    
}
