/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreamCipher;

import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class PRNGTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LCGRandomGenerator generator = new LCGRandomGenerator(2);
        ArrayList<Integer> ints = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            int next = generator.getNextBit();
            if (!ints.contains(next)) {
                ints.add(next);
            }
        }
        System.out.println(ints.size() + " distinct ints");
    }
    
}
