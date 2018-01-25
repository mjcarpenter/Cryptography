/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherCrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Matt
 */
public class Cracker {

    private static final String CIPHER_TEXT_1 = "27191efcabb7b1afb81f5053ed00e7695edba158d9069e5cc5124baae247b6621ac440ba769e8a733afd0117e0f2a912af0e8ddc325f29a0bd847bf03a89f7055c3ecb9899a7f9635c0454ab07da20954efb1579f6dc1ef154189eeb9865834550a0b167b6b3fe321708b4dae2af3e610e1854188bcf4c206563793430a181ca344a1b08f564721c5dec289b7238050a598916beb6bd17a6";
    private static final String CIPHER_TEXT_2 = "32510ba9a8b8b9a8fd195c49ed02e5604bcea258da0d824bc51b1ebbe809a06508d25fff6ad8d32f6fbc191faff2a517b2068d843e113fa4ac806bbe7bdaf7125034c89589b1bc24480453e504cc62d05af51330ecd35bba5e1499a4d769c6154da9f263bce6e92f5603b2c6aba437221e0e451e86815f636560727062a5c2d528561109f463651d53aa67";
    private static final String CIPHER_TEXT_3 = "32510ba99cb5b1bafd064643b91ab5644fc4ab0b8a1c854dc10b02a7ea05e56806dc5fef6a91c52e36bc1d1ceee7a907b50a8d9d325026a9f88776b83edba7154138cf9f84b0ef63550d06ea13cc2b834ff7047eec9b4cff561481a6cc6dcc5b51efa467a0bfac24521cb2c7b7b93c7b52";
    private static final String CIPHER_TEXT_4 = "274d4efca7b2aea9ea075c5eb443ec7f5b8fb911c613cd46c51a0fe9f946e57f01d95df1399bd8296ef50e11e3ecb946a601918566113ca5bdc86bbe3dc6f51a5229c79382f5e52c4f4b4eea16da6e9653ef0f74b8961eeb44049eb3d16bcd5c4ca8f263a1b5f93a471ab2c7acb97c221917450097c05f652b65373566a9c5df28461d4df07f64591ee52a99332e09074cd00eb3afbc04e0b87efec70a";
    private static final String CIPHER_TEXT_5 = "274d4efca7b2f8b5f701155da40ff93040caab1c8a0b8208d41702a7e609a67900c45af97894c6393afd0f1ffaf4e012af06de997c5727eda18777f033c8f112133bc18982b1bc6e1a1a53ee13cb279f52f30f77b8da4de9440c9db3d16bcd460eefb774b3aaf9364307b5cfe2af266b18044a0f87814a622122743f7db0c0c82f4b1f4df968701609e2229a372f4e";
    private static final String CIPHER_TEXT_6 = "23410fe4bafbb9befd545b45b943e5655ccaa2018a1ecd45c51204bbf409a0730cc250f36a9d866078e91950fbe5b312e71a9185601129afb1846ba42289f318132ec6939bf5e82b5f4b4ae20ed43dd05eff1567fdde50ba580588a6cb24c25b46efa66df2a7fc275b17fbc9e2be386713135d4c8dd30b612a66723c30b4ce9a3657170bf462731012e42685722c120848840fa9bafd";
    private static final String CIPHER_TEXT_7 = "32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f9043eb8e251ef110c98b4cc24c64d52a3b36bbce6e438404ea2c7b7ea34671f135d1c96c44f2c316a7e233e";
    private static final String[] CIPHER_TEXTS = new String[] {CIPHER_TEXT_1, CIPHER_TEXT_2, CIPHER_TEXT_3, CIPHER_TEXT_4, CIPHER_TEXT_5, CIPHER_TEXT_6, CIPHER_TEXT_7};
    
    private static final String KEY = "66396e89c9dbd8cc9874352acd6395102eafce78aa7fed28a07f6bc98d29c50b69b0339a19f8aa401a9c6d708f80c066c763fef0123148cdd8e802d05ba98777335daefcecd59c433a6b268b60bf4ef03c9a611098bb3e9a3161edc7b804a33522cfd202d2c68c57376edba8c2ca50027c61246ce2a12b0c4502175010c0a1ba4625786d911100797d8a47e9525c60692bf066cadfd37088dd0d9bb424";
    
    private static final HashMap<Integer, String> guesses = new HashMap();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
            doCiphertext(i);
        }
        int maxLength = 0;
        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
            if (CIPHER_TEXTS[i].length() > maxLength) {
                maxLength = CIPHER_TEXTS[i].length();
            }
        }
        String guessedKey = "";
        for (int i = 0; i < maxLength; i += 2) {
            if (guesses.containsKey(new Integer(i))) {
                String keyPart = guesses.get(new Integer(i));
                if (keyPart.length() == 1) {
                    keyPart = "0" + keyPart;
                }
                guessedKey += keyPart;
            } else {
                guessedKey += "--";
            }
        }
        
//        int testingCipher = 0;
//        String subject = CIPHER_TEXTS[testingCipher];
//        ArrayList<Integer> positions = new ArrayList();
//        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
//            if (i == testingCipher) {
//                continue;
//            }
//            String subjectCurrent = subject;
//            String current = CIPHER_TEXTS[i];
//            int originalLength = current.length();
//            if (subjectCurrent.length() > current.length()) {
//                originalLength = current.length();
//                int difference = subjectCurrent.length() - current.length();
//                for (int j = 0; j < difference; j++) {
//                    current += "0";
//                }
//            } else if (current.length() > subjectCurrent.length()) {
//                originalLength = subjectCurrent.length();
//                int difference = current.length() - subjectCurrent.length();
//                for (int j = 0; j < difference; j++) {
//                    subjectCurrent += "0";
//                }
//            }
//            for (int j = 0; j < originalLength; j += 2) {
//                String character1 = subjectCurrent.substring(j, j + 2);
//                String character2 = current.substring(j, j + 2);
//                int subjectHex = Integer.parseInt(character1, 16);
//                int currentHex = Integer.parseInt(character2, 16);
//                int result = subjectHex ^ currentHex;
//                if (result >= 65 && result <= 90) {
//                    if (!positions.contains(new Integer(j))) {
//                        positions.add(new Integer(j));
//                    }
//                } else if (result >= 97 && result <= 122) {
//                    if (!positions.contains(new Integer(j))) {
//                        positions.add(new Integer(j));
//                    }
//                } else {
//                    if (positions.contains(new Integer(j))) {
//                        positions.remove(new Integer(j));
//                    }
//                }
//            }
//        }
//        Collections.sort(positions);
//        for (Integer i : positions) {
//            String character = subject.substring(i, i + 2);
//            int hex = Integer.parseInt(character, 16);
//            int key = hex ^ Integer.parseInt("20", 16);
//            //System.out.print("Position " + i + ": ");
//            ArrayList<Integer> evaluatedValues = new ArrayList();
//            for (int j = 0; j < CIPHER_TEXTS.length; j++) {
//                if (j == testingCipher) {
//                    
//                } else {
//                    String current = CIPHER_TEXTS[j];
//                    if (current.length() < i) {
//                        //System.out.print("n/a ");
//                        continue;
//                    }
//                    String currentCharacter = current.substring(i, i + 2);
//                    int currentHex = Integer.parseInt(currentCharacter, 16);
//                    int result = currentHex ^ key;
//                    evaluatedValues.add(result);
//                    //System.out.print((char)result + " ");
//                }
//            }
//            //System.out.println("");
//            boolean valid = true;
//            for (Integer val : evaluatedValues) {
//                if (val < 65 || (val > 90 && val < 97) || val > 122) {
//                    valid = false;
//                }
//            }
//            if (valid) {
//                System.out.println("Position " + i + " in key is: " + Integer.toHexString(key));
//            }
//        }

        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
            String decrypted = "";
            for (int j = 0; j < guessedKey.length() && j < CIPHER_TEXTS[i].length(); j += 2) {
                String character = CIPHER_TEXTS[i].substring(j, j + 2);
                String key = guessedKey.substring(j, j + 2);
                if (key.equals("--")) {
                    decrypted += "#";
                    continue;
                }
                int charVal = Integer.parseInt(character, 16);
                int keyVal = Integer.parseInt(key, 16);
                int result = charVal ^ keyVal;
                String res = (char)result + "";
                decrypted += res;
            }
            System.out.println((i + 1) + ": " + decrypted);
        }

//        String testingKey = "";
//        int maxLength = 0;
//        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
//            if (CIPHER_TEXTS[i].length() > maxLength) {
//                maxLength = CIPHER_TEXTS[i].length();
//            }
//        }
//        for (int i = 0; i < maxLength; i++) {
//            testingKey += "-";
//        }
//        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
//            String testingCipher = CIPHER_TEXTS[i];
//            ArrayList<Integer> testingPositions = new ArrayList();
//            for (int j = 0; j < CIPHER_TEXTS.length; j++) {
//                if (i == j) {
//                    continue;
//                }
//                String pairedCipher = CIPHER_TEXTS[j];
//                int originalLength = testingCipher.length() > pairedCipher.length() ? pairedCipher.length() : testingCipher.length();
//                while (testingCipher.length() > pairedCipher.length()) {
//                    pairedCipher += "0";
//                }
//                while (pairedCipher.length() > testingCipher.length()) {
//                    testingCipher += "0";
//                }
//                for (int k = 0; k < originalLength; k += 2) {
//                    String character1 = testingCipher.substring(k, k + 2);
//                    String character2 = pairedCipher.substring(k, k + 2);
//                    int subjectHex = Integer.parseInt(character1, 16);
//                    int currentHex = Integer.parseInt(character2, 16);
//                    int result = subjectHex ^ currentHex;
//                    if (result >= 65 && result <= 90) {
//                        if (!testingPositions.contains(new Integer(k))) {
//                            testingPositions.add(new Integer(k));
//                        }
//                    } else if (result >= 97 && result <= 122) {
//                        if (!testingPositions.contains(new Integer(k))) {
//                            testingPositions.add(new Integer(k));
//                        }
//                    } else {
//                        if (testingPositions.contains(new Integer(k))) {
//                            testingPositions.remove(new Integer(k));
//                        }
//                    }
//                }
//            }
//            Collections.sort(testingPositions);
//            for (Integer pos : testingPositions) {
//                String character = testingCipher.substring(pos, pos + 2);
//                int hex = Integer.parseInt(character, 16);
//                int key = hex ^ 20;
//                
//                ArrayList<Integer> evaluatedValues = new ArrayList();
//                for (int j = 0; j < CIPHER_TEXTS.length; j++) {
//                    if (j == i) {
//                        continue;
//                    } else {
//                        String current = CIPHER_TEXTS[j];
//                        if (current.length() < pos) {
//                            continue;
//                        }
//                        String currentCharacter = current.substring(pos, pos + 2);
//                        int currentHex = Integer.parseInt(currentCharacter, 16);
//                        int result = currentHex ^ key;
//                        evaluatedValues.add(result);
//                    }
//                }
//                boolean valid = true;
//                for (Integer val : evaluatedValues) {
//                    if (val < 65 || (val > 90 && val < 97) || val > 122) {
//                        valid = false;
//                    }
//                }
//                if (valid) {
//                    char[] chars = testingKey.toCharArray();
//                    String keyValue = Integer.toHexString(key);
//                    chars[pos] = keyValue.charAt(0);
//                    chars[pos + 1] = keyValue.charAt(1);
//                    testingKey = new String(chars);
//                }
//            }
//        }
//        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
//            String decrypted = "";
//            for (int j = 0; j < testingKey.length() && j < CIPHER_TEXTS[i].length(); j += 2) {
//                String character = CIPHER_TEXTS[i].substring(j, j + 2);
//                String key = testingKey.substring(j, j + 2);
//                if (key.equals("--")) {
//                    decrypted += "#";
//                    continue;
//                }
//                int charVal = Integer.parseInt(character, 16);
//                int keyVal = Integer.parseInt(key, 16);
//                int result = charVal ^ keyVal;
//                String res = (char)result + "";
//                decrypted += res;
//                
//            }
//            System.out.println((i + 1) + ": " + decrypted);
//        }
    }
    
    private static void doCiphertext(int ciphertext) {
        int testingCipher = ciphertext;
        String subject = CIPHER_TEXTS[testingCipher];
        ArrayList<Integer> positions = new ArrayList();
        for (int i = 0; i < CIPHER_TEXTS.length; i++) {
            if (i == testingCipher) {
                continue;
            }
            String subjectCurrent = subject;
            String current = CIPHER_TEXTS[i];
            int originalLength = current.length();
            if (subjectCurrent.length() > current.length()) {
                originalLength = current.length();
                int difference = subjectCurrent.length() - current.length();
                for (int j = 0; j < difference; j++) {
                    current += "0";
                }
            } else if (current.length() > subjectCurrent.length()) {
                originalLength = subjectCurrent.length();
                int difference = current.length() - subjectCurrent.length();
                for (int j = 0; j < difference; j++) {
                    subjectCurrent += "0";
                }
            }
            for (int j = 0; j < originalLength; j += 2) {
                String character1 = subjectCurrent.substring(j, j + 2);
                String character2 = current.substring(j, j + 2);
                int subjectHex = Integer.parseInt(character1, 16);
                int currentHex = Integer.parseInt(character2, 16);
                int result = subjectHex ^ currentHex;
                if (result >= 65 && result <= 90) {
                    if (!positions.contains(new Integer(j))) {
                        positions.add(new Integer(j));
                    }
                } else if (result >= 97 && result <= 122) {
                    if (!positions.contains(new Integer(j))) {
                        positions.add(new Integer(j));
                    }
                } else {
                    if (positions.contains(new Integer(j))) {
                        positions.remove(new Integer(j));
                    }
                }
            }
        }
        Collections.sort(positions);
        for (Integer i : positions) {
            String character = subject.substring(i, i + 2);
            int hex = Integer.parseInt(character, 16);
            int key = hex ^ Integer.parseInt("20", 16);
            //System.out.print("Position " + i + ": ");
            ArrayList<Integer> evaluatedValues = new ArrayList();
            for (int j = 0; j < CIPHER_TEXTS.length; j++) {
                if (j == testingCipher) {
                    
                } else {
                    String current = CIPHER_TEXTS[j];
                    if (current.length() < i) {
                        //System.out.print("n/a ");
                        continue;
                    }
                    String currentCharacter = current.substring(i, i + 2);
                    int currentHex = Integer.parseInt(currentCharacter, 16);
                    int result = currentHex ^ key;
                    evaluatedValues.add(result);
                    //System.out.print((char)result + " ");
                }
            }
            //System.out.println("");
            boolean valid = true;
            for (Integer val : evaluatedValues) {
                if (val < 65 || (val > 90 && val < 97) || val > 122) {
                    valid = false;
                }
            }
            if (valid) {
                //System.out.println("Position " + i + " in key is: " + Integer.toHexString(key));
                guesses.put(new Integer(i), Integer.toHexString(key));
            }
        }
    }
    
}
