package com.company;

import com.company.duke.FileResource;

public class Main {

    public static void main(String[] args) {
        FileResource resource = new FileResource();
        String message = resource.asString();
        CaesarBreaker breaker = new CaesarBreaker();
        System.out.println(breaker.decryptTwoKeys(message));

    }
}
