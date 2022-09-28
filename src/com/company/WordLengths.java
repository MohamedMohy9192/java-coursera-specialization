package com.company;

import com.company.duke.FileResource;

public class WordLengths {
    private void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int newWordLength = word.length();
            // check the first char in the word if letter or not
            if (!Character.isLetter(word.charAt(0))) {
                newWordLength = newWordLength - 1;
            }

            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                newWordLength = newWordLength - 1;
            }

            // if the word's length larger than the last index of the count array
            // store it at the last index
            if (newWordLength >= counts.length) {
                counts[counts.length - 1] += 1;
            } else {
                counts[newWordLength] += 1;
            }
        }
    }

    public void testCountWordsLengths() {
        // Create a FileResource so you can select a file
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);

        for (int k = 0; k < counts.length; k++) {
            System.out.println(counts[k] + " words of length " + k);
        }
        System.out.println("The most common word length is: " + indexOfMax(counts));
    }

    private int indexOfMax(int[] values) {
        int indexOfMax = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[indexOfMax]) {
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }
}
