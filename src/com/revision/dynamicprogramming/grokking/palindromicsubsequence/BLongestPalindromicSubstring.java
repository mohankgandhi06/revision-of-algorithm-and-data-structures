package com.revision.dynamicprogramming.grokking.palindromicsubsequence;

public class BLongestPalindromicSubstring {
    public static void main(String[] args) {
        BLongestPalindromicSubstring game = new BLongestPalindromicSubstring();
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
    }

    private int solveWithBruteForce(String input) {
        return solve(input, 0, input.length() - 1);
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex == endIndex) return 1;
        if (startIndex > endIndex) return 0;
        int include = 0;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            int remainingLength = endIndex - startIndex - 1;
            if (remainingLength == solve(input, startIndex + 1, endIndex - 1)) {
                include = 2 + remainingLength;
            }
        }
        int left = solve(input, startIndex + 1, endIndex);
        int right = solve(input, startIndex, endIndex - 1);
        return Math.max(include, Math.max(left, right));
    }

    private int solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        return solve(input, 0, input.length() - 1, memo);
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex == endIndex) return 1;
        if (startIndex > endIndex) return 0;
        if (memo[ startIndex ][ endIndex ] == null) {
            int include = 0;
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                int remainingLength = endIndex - startIndex - 1;
                if (remainingLength == solve(input, startIndex + 1, endIndex - 1, memo)) {
                    include = 2 + remainingLength;
                }
            }
            int left = solve(input, startIndex + 1, endIndex, memo);
            int right = solve(input, startIndex, endIndex - 1, memo);
            memo[ startIndex ][ endIndex ] = Math.max(include, Math.max(left, right));
        }
        return memo[ startIndex ][ endIndex ];
    }

    private int solveWithTabulation(String input) {
        boolean[][] table = new boolean[ input.length() ][ input.length() ];
        for (int row = 0, col = 0; row < table.length && col < table[ 0 ].length; row++, col++) {
            table[ row ][ col ] = true;
        }
        return solve(input, 1, table);
    }

    private int solve(String input, int maxLength, boolean[][] table) {
        for (int row = table.length - 1; row >= 0; row--) {
            for (int col = row + 1; col < table[ 0 ].length; col++) {
                if (input.charAt(row) == input.charAt(col) && (col - row == 1 || table[ row + 1 ][ col - 1 ])) {
                    maxLength = Math.max(maxLength, col - row + 1);
                    table[ row ][ col ] = true;
                }
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print((table[ row ][ col ] ? "T" : "F") + " ");
            }
            System.out.println();
        }*/
        return maxLength;
    }
}