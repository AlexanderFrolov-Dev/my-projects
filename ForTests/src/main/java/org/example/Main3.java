package org.example;

public class Main3 {
    public static void main(String[] args) {
        notGood();
    }

    static void notGood() {
        System.out.println("Только не снова!");
        notGood();
    }
}

