package com.company.cryptography;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        //Write down the alphabet
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                // Check if the original char is lowercase
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                if (Character.isUpperCase(currChar)) {
                    newChar = Character.toUpperCase(newChar);
                }
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cipher = new CaesarCipher(26 - mainKey);
        return cipher.encrypt(input);
    }


/*    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }*/

   /* public String encryptTwoKeys(String input, int key1, int key2) {
        String encrypt1 = encrypt(input, key1);
        String encrypt2 = encrypt(input, key2);
        StringBuilder inputStringBuilder = new StringBuilder(input);
        // encrypt odd indexes with second key and even indexes with first key
        for (int i = 0; i < inputStringBuilder.length(); i++) {
            if (i % 2 == 0) {
                // Replace the char at even index with the encrypted char at the same
                // index using the key1
                inputStringBuilder.setCharAt(i, encrypt1.charAt(i));
            } else {
                // Replace the char at odd index with the encrypted char at the same
                // index using the key2
                inputStringBuilder.setCharAt(i, encrypt2.charAt(i));
            }
        }
        return inputStringBuilder.toString();
    }*/

}
