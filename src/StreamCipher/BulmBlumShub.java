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
public class BulmBlumShub {

    private static int last;
    private static int m = 102079 * 103561;
    private static ArrayList<Integer> used = new ArrayList();
    private static boolean duplicate = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("m = " + m);
        ArrayList<Integer> duplicateCount = new ArrayList();
        for (int i = 0; i < 100; i++) {
            used.clear();
            duplicate = false;
            last = i;
            used.add(last);
            int count = 0;
            System.out.println("Seed: " + i);
            while (!duplicate) {
                int bit = getNextBit();
                System.out.print(bit);
                count++;
            }
            System.out.println("");
            System.out.println("Duplicate when generating the [" + count + "] random number.");
            duplicateCount.add(count);
        }
        int total = 0;
        for (int i = 0; i < 100; i++) {
            total += duplicateCount.get(i);
        }
        System.out.println("Average repeat period: " + (total / 100));
    }
    
    private static int getNextBit() {
        int next = (last * last) % m;
        last = next;
        if (!used.contains(next)) {
            used.add(next);
        } else {
            duplicate = true;
        }
        byte[] binary = toBinary(next);
        int count = 0;
        for (int i = 0; i < binary.length; i++) {
            if (binary[i] == 1) {
                count++;
            }
        }
        return count % 2;
    }
    
    public static byte[] toBinary(int number) {
        byte[] binary = new byte[32];
        int index = 0;
        int copyOfInput = number;
        while (copyOfInput > 0) {
            binary[index++] = (byte) (copyOfInput % 2);
            copyOfInput = copyOfInput / 2;
        }
        return binary;
    }
    
}
