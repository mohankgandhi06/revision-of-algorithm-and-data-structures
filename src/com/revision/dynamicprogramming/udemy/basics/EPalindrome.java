package com.revision.dynamicprogramming.udemy.basics;

public class EPalindrome {
    public static void main(String[] args) {
        EPalindrome game = new EPalindrome();
        String word = "madam";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
        word = "liril";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
        word = "madman";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
        word = "holloh";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
        word = "hooh";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
        word = "madamsa";
        System.out.println("Is \"" + word + "\" a palindrome? " + game.solve(word));
    }

    private boolean solve(String word) {
        return solveWithTimeComplexity(word, 0, word.length() - 1);
    }

    private boolean solveWithTimeComplexity(String word, int startIndex, int endIndex) {
        if (startIndex - endIndex == 1 || startIndex - endIndex == 0) return true;
        if (startIndex < endIndex) {
            if (word.charAt(startIndex) == word.charAt(endIndex) && solveWithTimeComplexity(word, startIndex + 1, endIndex - 1)) {
                return true;
            }
        }
        return false;
    }
}