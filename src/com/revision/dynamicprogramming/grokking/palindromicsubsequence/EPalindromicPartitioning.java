package com.revision.dynamicprogramming.grokking.palindromicsubsequence;

import java.util.Arrays;

public class EPalindromicPartitioning {
    public static void main(String[] args) {
        EPalindromicPartitioning game = new EPalindromicPartitioning();
        String input = "abdbca";
        System.out.println("Input: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "cddpd";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "pqr";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "abcddcea";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "abcddcba";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        /*input = "abcddcbaastttttsaabcddcba";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "abcddcbaastttttsaabca";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));*/
    }

    private int solveWithBruteForce(String input) {
        return solve(input, 0, input.length() - 1);
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex >= endIndex || isPalindrome(input, startIndex, endIndex)) {
            return 0;
        }
        int minCut = endIndex - startIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if (isPalindrome(input, startIndex, i)) {
                minCut = Math.min(minCut, 1 + solve(input, i + 1, endIndex));
            }
        }
        return minCut;
    }

    private boolean isPalindrome(String input, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            if (input.charAt(startIndex++) != input.charAt(endIndex--)) {
                return false;
            }
        }
        return true;
    }

    private int solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        return solve(input, 0, input.length() - 1, memo);
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex >= endIndex || isPalindrome(input, startIndex, endIndex)) {
            return 0;
        }
        if (memo[ startIndex ][ endIndex ] == null) {
            int minCuts = endIndex - startIndex;
            for (int i = startIndex; i <= endIndex; i++) {
                if (isPalindrome(input, startIndex, i)) {
                    minCuts = Math.min(minCuts, 1 + solve(input, i + 1, endIndex, memo));
                }
            }
            memo[ startIndex ][ endIndex ] = minCuts;
        }
        return memo[ startIndex ][ endIndex ];
    }

    private int solveWithTabulation(String input) {
        boolean[][] table = new boolean[ input.length() ][ input.length() ];
        for (int row = 0, col = 0; row < table.length && col < table[ 0 ].length; row++, col++) {
            table[ row ][ col ] = true;
        }
        return solve(input, table);
    }

    private int solve(String input, boolean[][] table) {

        for (int row = table.length - 1; row >= 0; row--) {
            for (int col = row + 1; col < table[ 0 ].length; col++) {
                if (input.charAt(row) == input.charAt(col)) {
                    if (col - row == 1 || table[ row + 1 ][ col - 1 ]) {
                        table[ row ][ col ] = true;
                    }
                }
            }
        }

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print((table[ row ][ col ] ? "T" : "F") + " ");
            }
            System.out.println();
        }

        int[] cuts = new int[ input.length() ];
        for (int row = table.length - 1; row >= 0; row--) {
            int minCuts = input.length();
            for (int col = table[ 0 ].length - 1; col >= row; col--) {
                if (table[ row ][ col ]) {
                    minCuts = (col == input.length() - 1) ? 0 : Math.min(minCuts, 1 + cuts[ col + 1 ]);
                }
            }
            cuts[ row ] = minCuts;
        }

        Arrays.stream(cuts).forEach(value -> System.out.print(value + " "));
        System.out.println();
        return cuts[ 0 ];
    }
}