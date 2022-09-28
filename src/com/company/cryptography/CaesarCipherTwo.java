package com.company.cryptography;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        //Write down the alphabet
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        shiftedAlphabet1 = alphabet.substring(key1) +
                alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) +
                alphabet.substring(0, key2);

        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encryptedBuilder = new StringBuilder(input);
        // Start from index 0 (even index)
        for (int i = 0; i < encryptedBuilder.length(); i += 2) {
            char currChar = encryptedBuilder.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet1.charAt(idx);
                encryptedBuilder.setCharAt(i, isLowerOrUpperCase(newChar));
            }
        }
        // Start from odd index 1 (odd index)
        for (int i = 1; i < encryptedBuilder.length(); i += 2) {
            char currChar = encryptedBuilder.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet2.charAt(idx);
                encryptedBuilder.setCharAt(i, isLowerOrUpperCase(newChar));
            }
        }
        return encryptedBuilder.toString();
    }

    private char isLowerOrUpperCase(char c) {
        return Character.isLowerCase(c)
                ? Character.toLowerCase(c)
                : Character.toUpperCase(c);
    }

    public String decrypt(String input) {
        CaesarCipherTwo cipher = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cipher.encrypt(input);
    }
}
