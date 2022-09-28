package com.company.cryptography;

import com.company.duke.FileResource;

public class TestCaesarCipher {

    /**
     * Count the occurrences of every character in the string encrypted
     *
     * @param message string encrypted
     */
    private int[] countLetters(String message) {
        String alphabetical = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char charInMessage = Character.toLowerCase(message.charAt(i));
            int indexOfChInAlphabetical = alphabetical.indexOf(charInMessage);
            if (indexOfChInAlphabetical != -1) {
                counts[indexOfChInAlphabetical] += 1;
            }
        }
        return counts;
    }

    private int maxIndex(int[] vals) {
        int indexOfLargest = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals[indexOfLargest]) {
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }

    private int getKey(String s) {
        int[] letterFrequency = countLetters(s);
        // return the index in letterFrequency that is the largest
        int maxIndex = maxIndex(letterFrequency);
        // find location from this location to E
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            // if maximal index is less than 4 wrap around from 26 to
            // find the shift use for E
            key = 26 - (4 - maxIndex);
        }
        return key;
    }

    public void simpleTests() {
        int key = 18;
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cipher = new CaesarCipher(key);
        System.out.println("Original message:\n" + message);
        String encrypted = cipher.encrypt(message);
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("Decrypted message:\n" + cipher.decrypt(encrypted));
        System.out.println("Decrypt message automatically by determining the key:\n"
                + breakCaesarCipher(encrypted));
    }


    private String breakCaesarCipher(String input) {
        int key = getKey(input);
        System.out.println("Founded key is: " + key);
        CaesarCipher cipher = new CaesarCipher(key);
        return cipher.decrypt(input);
    }
}
