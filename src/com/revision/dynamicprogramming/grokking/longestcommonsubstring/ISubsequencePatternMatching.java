package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class ISubsequencePatternMatching {
    public static void main(String[] args) {
        ISubsequencePatternMatching game = new ISubsequencePatternMatching();
        String string = "baxmx";
        String pattern = "ax";
        System.out.println("Subsequence Pattern Matching");
        System.out.println("String: " + string + " Pattern: " + pattern);
        System.out.println("Brute Force: " + game.solveWithBruteForce(string, pattern));
        System.out.println("Memoization: " + game.solveWithMemoization(string, pattern));
        System.out.println("Tabulation: " + game.solveWithTabulation(string, pattern));

        string = "baxmx";
        pattern = "axo";
        System.out.println("\nSubsequence Pattern Matching");
        System.out.println("String: " + string + " Pattern: " + pattern);
        System.out.println("Brute Force: " + game.solveWithBruteForce(string, pattern));
        System.out.println("Memoization: " + game.solveWithMemoization(string, pattern));
        System.out.println("Tabulation: " + game.solveWithTabulation(string, pattern));

        string = "tomorrow";
        pattern = "tor";
        System.out.println("\nSubsequence Pattern Matching");
        System.out.println("String: " + string + " Pattern: " + pattern);
        System.out.println("Brute Force: " + game.solveWithBruteForce(string, pattern));
        System.out.println("Memoization: " + game.solveWithMemoization(string, pattern));
        System.out.println("Tabulation: " + game.solveWithTabulation(string, pattern));

        string = "tomorrowooo";
        pattern = "tor";
        System.out.println("\nSubsequence Pattern Matching");
        System.out.println("String: " + string + " Pattern: " + pattern);
        System.out.println("Brute Force: " + game.solveWithBruteForce(string, pattern));
        System.out.println("Memoization: " + game.solveWithMemoization(string, pattern));
        System.out.println("Tabulation: " + game.solveWithTabulation(string, pattern));
    }

    private int solveWithBruteForce(String string, String pattern) {
        return solve(string, pattern, 0, 0);
    }

    private int solve(String string, String pattern, int currentIndex, int length) {
        if (length == pattern.length()) return 1;
        if (currentIndex == string.length()) return 0;
        int include = 0;
        if (string.charAt(currentIndex) == pattern.charAt(length)) {
            include = solve(string, pattern, currentIndex + 1, length + 1);
        }
        int exclude = solve(string, pattern, currentIndex + 1, length);
        return include + exclude;
    }

    private int solveWithMemoization(String string, String pattern) {
        Integer[][] memo = new Integer[ string.length() ][ pattern.length() ];
        return solve(string, pattern, 0, 0, memo);
    }

    private int solve(String string, String pattern, int currentIndex, int length, Integer[][] memo) {
        if (length == pattern.length()) return 1;
        if (currentIndex == string.length()) return 0;
        if (memo[ currentIndex ][ length ] == null) {
            int include = 0;
            if (string.charAt(currentIndex) == pattern.charAt(length)) {
                include = solve(string, pattern, currentIndex + 1, length + 1, memo);
            }
            int exclude = solve(string, pattern, currentIndex + 1, length, memo);
            memo[ currentIndex ][ length ] = include + exclude;
        }
        return memo[ currentIndex ][ length ];
    }

    private int solveWithTabulation(String string, String pattern) {
        int[][] table = new int[ pattern.length() + 1 ][ string.length() + 1 ];
        for (int col = 0; col < table.length; col++) {
            table[ 0 ][ col ] = 1;
        }
        return solve(string, pattern, table);
    }

    private int solve(String string, String pattern, int[][] table) {
        for (int row = 1; row <= pattern.length(); row++) {
            for (int col = 1; col <= string.length(); col++) {
                if (string.charAt(col - 1) == pattern.charAt(row - 1)) {
                    table[ row ][ col ] = table[ row - 1 ][ col - 1 ];
                }
                table[ row ][ col ] += table[ row ][ col - 1 ];
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}