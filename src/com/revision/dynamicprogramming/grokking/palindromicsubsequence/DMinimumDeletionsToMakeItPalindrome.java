package com.revision.dynamicprogramming.grokking.palindromicsubsequence;

public class DMinimumDeletionsToMakeItPalindrome {
    public static void main(String[] args) {
        DMinimumDeletionsToMakeItPalindrome game = new DMinimumDeletionsToMakeItPalindrome();
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

        input = "abcddcbaastttttsaabcddcba";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));

        input = "abcddcbaastttttsaabca";
        System.out.println("\nInput: " + input);
        System.out.println("Brute Force: " + game.solveWithBruteForce(input));
        System.out.println("Memoization: " + game.solveWithMemoization(input));
        System.out.println("Tabulation: " + game.solveWithTabulation(input));
    }

    private int solveWithBruteForce(String input) {
        return input.length() - solve(input, 0, input.length() - 1);
    }

    private int solve(String input, int startIndex, int endIndex) {
        if (startIndex == endIndex) return 1;
        if (startIndex > endIndex) return 0;
        int include = 0;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            include = 2 + solve(input, startIndex + 1, endIndex - 1);
        }
        int left = solve(input, startIndex + 1, endIndex);
        int right = solve(input, startIndex, endIndex - 1);
        return Math.max(include, Math.max(left, right));
    }

    private int solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        return input.length() - solve(input, 0, input.length() - 1, memo);
    }

    private int solve(String input, int startIndex, int endIndex, Integer[][] memo) {
        if (startIndex == endIndex) return 1;
        if (startIndex > endIndex) return 0;
        if (memo[ startIndex ][ endIndex ] == null) {
            int include = 0;
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                include = 2 + solve(input, startIndex + 1, endIndex - 1, memo);
            }
            int left = solve(input, startIndex + 1, endIndex, memo);
            int right = solve(input, startIndex, endIndex - 1, memo);
            memo[ startIndex ][ endIndex ] = Math.max(include, Math.max(left, right));
        }
        return memo[ startIndex ][ endIndex ];
    }

    private int solveWithTabulation(String input) {
        int[][] table = new int[ input.length() ][ input.length() ];
        for (int row = 0, col = 0; row < table.length && col < table[ 0 ].length; row++, col++) {
            table[ row ][ col ] = 1;
        }
        return input.length() - solve(input, table);
    }

    private int solve(String input, int[][] table) {
        for (int row = table.length - 1; row >= 0; row--) {
            for (int col = row + 1; col < table[ 0 ].length; col++) {
                if (input.charAt(row) == input.charAt(col)) {
                    table[ row ][ col ] = 2 + table[ row + 1 ][ col - 1 ];
                } else {
                    table[ row ][ col ] = Math.max(table[ row + 1 ][ col ], table[ row ][ col - 1 ]);
                }
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ 0 ][ table[ 0 ].length - 1 ];
    }
}