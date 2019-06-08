package com.revision.dynamicprogramming.grokking.palindromicsubsequence;

import java.util.HashSet;
import java.util.Set;

public class CCountOfPalindromicSubstrings {
    public static void main(String[] args) {
        CCountOfPalindromicSubstrings game = new CCountOfPalindromicSubstrings();
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
    }

    private int solveWithBruteForce(String input) {
        Set<String> noDuplicates = new HashSet<>();
        solve(input, 0, input.length() - 1, noDuplicates);
        System.out.println("Unique Strings: ");
        noDuplicates.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();
        return noDuplicates.size();
    }

    private int solve(String input, int startIndex, int endIndex, Set<String> noDuplicates) {
        if (startIndex == endIndex) {
            noDuplicates.add(input.substring(startIndex, endIndex + 1).concat(String.valueOf(startIndex)).concat(String.valueOf(endIndex - startIndex + 1)));
            return 1;
        }
        if (startIndex > endIndex) return 0;
        int include = 0;
        if (input.charAt(startIndex) == input.charAt(endIndex)) {
            int remaining = endIndex - startIndex - 1;
            if (endIndex - startIndex == 1 || remaining == solve(input, startIndex + 1, endIndex - 1, noDuplicates)) {
                noDuplicates.add(input.substring(startIndex, endIndex + 1).concat(String.valueOf(startIndex)).concat(String.valueOf(endIndex - startIndex + 1)));
                include = 2 + remaining;
            }
        }
        int left = solve(input, startIndex + 1, endIndex, noDuplicates);
        int right = solve(input, startIndex, endIndex - 1, noDuplicates);
        return Math.max(left, Math.max(right, include));
    }

    private int solveWithMemoization(String input) {
        Integer[][] memo = new Integer[ input.length() ][ input.length() ];
        Set<String> noDuplicates = new HashSet<>();
        solve(input, 0, input.length() - 1, noDuplicates, memo);
        System.out.println("Unique Strings: ");
        noDuplicates.stream().forEach(s -> System.out.print(s + " "));
        System.out.println();
        return noDuplicates.size();
    }

    private int solve(String input, int startIndex, int endIndex, Set<String> noDuplicates, Integer[][] memo) {
        if (startIndex == endIndex) {
            noDuplicates.add(input.substring(startIndex, endIndex + 1).concat(String.valueOf(startIndex)).concat(String.valueOf(endIndex - startIndex + 1)));
            return 1;
        }
        if (startIndex > endIndex) return 0;
        if (memo[ startIndex ][ endIndex ] == null) {
            int include = 0;
            if (input.charAt(startIndex) == input.charAt(endIndex)) {
                int z = 0;
                int remaining = endIndex - startIndex - 1;
                if (endIndex - startIndex == 1 || remaining == solve(input, startIndex + 1, endIndex - 1, noDuplicates)) {
                    int a = 0;
                    noDuplicates.add(input.substring(startIndex, endIndex + 1).concat(String.valueOf(startIndex)).concat(String.valueOf(endIndex - startIndex + 1)));
                    include = 2 + remaining;
                }
            }
            int left = solve(input, startIndex + 1, endIndex, noDuplicates);
            int right = solve(input, startIndex, endIndex - 1, noDuplicates);
            memo[ startIndex ][ endIndex ] = Math.max(left, Math.max(right, include));
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
        int count = table.length;
        for (int row = table.length - 1; row >= 0; row--) {
            for (int col = row + 1; col < table[ 0 ].length; col++) {
                if (input.charAt(row) == input.charAt(col)) {
                    if (col - row == 1 || table[ row + 1 ][ col - 1 ]) {
                        table[ row ][ col ] = true;
                        count++;
                    }
                }
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print((table[ row ][ col ] ? "T" : "F") + " ");
            }
            System.out.println();
        }*/
        return count;
    }
}