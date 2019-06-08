package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class ISubsequencePatternMatching {
    public static void main(String[] args) {
        ISubsequencePatternMatching game = new ISubsequencePatternMatching();
        String string = "baxmx";
        String pattern = "ax";
        System.out.println("Subsequence Pattern Matching");
        System.out.println("Brute Force: " + game.solveWithBruteForce(string, pattern));

    }

    private int solveWithBruteForce(String string, String pattern) {
        return -1;
    }

    private int solve(String string, String pattern, int currentIndex) {
        return -1;
    }
}