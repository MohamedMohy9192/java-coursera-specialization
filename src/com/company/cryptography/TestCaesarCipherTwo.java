package com.company.cryptography;

import com.company.duke.FileResource;

public class TestCaesarCipherTwo {

    private String halfOfString(String message, int start) {
     /*   StringBuilder halfMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (i % 2 == start) {
                halfMessage.append(message.charAt(i));
            }
        }*/
        StringBuilder m = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            m.append(message.charAt(i));
        }
        return m.toString();
        //    return halfMessage.toString();
    }

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
        int maxIndex = maxIndex(letterFrequency);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }

    public void simpleTests() {
        int key1 = 17;
        int key2 = 3;
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(key1, key2);
        System.out.println("Original message:\n" + message);
        String encrypted = cipherTwo.encrypt(message);
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("Decrypted message:\n" + cipherTwo.decrypt(encrypted));
        System.out.println("Decrypt message automatically by determining the tow keys:\n"
                + breakCaesarCipher(encrypted));
    }

    private String breakCaesarCipher(String input) {
        String firstHalfString = halfOfString(input, 0);
        String secondHalfString = halfOfString(input, 1);

        int key1 = getKey(firstHalfString);
        int key2 = getKey(secondHalfString);

        System.out.println("Founded key1 is: " + key1 + "\nFounded key2 is:" + key2);
        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(key1, key2);
        return cipherTwo.decrypt(input);

    }
}

