package com.company;

import com.company.duke.FileResource;

public class CaesarBreaker {
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

    public String decrypt(String encrypted) {
        CaesarCipher cipher = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        // return the index in freqs that is the largest
        int maxDex = maxIndex(freqs);
        // find location from this location to E
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            // if maximal index is less than 4 wrap around from 26 to
            // find the shift use for E
            dkey = 26 - (4 - maxDex);
        }

        return cipher.encrypt(encrypted, 26 - dkey);
    }

    public void testDecrypt() {
        System.out.println();
    }

    public String halfOfString(String message, int start) {
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

    private int getKey(String s) {
        int[] letterFrequency = countLetters(s);
        int maxIndex = maxIndex(letterFrequency);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }

    public String decryptTwoKeys(String encrypted) {
        String firstHalfString = halfOfString(encrypted, 0);
        String secondHalfString = halfOfString(encrypted, 1);

        int key1 = getKey(firstHalfString);
        int key2 = getKey(secondHalfString);

        System.out.println("Key1 " + key1 + " Key2 " + key2);

        CaesarCipher cipher = new CaesarCipher();
        return cipher.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecryptTwoKey() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Message before encrypt " + message);
        CaesarCipher cipher = new CaesarCipher();
        String encryptTwoKeys = cipher.encryptTwoKeys(message, 10, 23);
        System.out.println("encryptTwoKeys " + encryptTwoKeys);
        String decryptTwoKeys = decryptTwoKeys(encryptTwoKeys);
        System.out.println("decryptTwoKeys " + decryptTwoKeys);
    }
}
