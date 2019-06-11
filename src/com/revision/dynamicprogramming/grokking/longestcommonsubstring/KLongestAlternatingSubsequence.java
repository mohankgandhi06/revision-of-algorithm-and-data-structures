package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

import java.util.Arrays;

public class KLongestAlternatingSubsequence {
    public static void main(String[] args) {
        KLongestAlternatingSubsequence game = new KLongestAlternatingSubsequence();
        int[] arr = new int[]{1, 2, 3, 4};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Alternating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{3, 2, 1, 4};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Alternating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 3, 2, 4};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Alternating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{3, 6, 2, 1, 0, 1, 3, 2, 4};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Alternating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 3, 5, 6, 1, 1, 1, 3, 2, 4};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Alternating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        return Math.max(solve(arr, 0, -1, false), solve(arr, 0, -1, true));
    }

    private int solve(int[] arr, int currentIndex, int previousIndex, boolean isIncreasing) {
        if (currentIndex == arr.length) return 0;
        int include = 0;
        if (isIncreasing) {
            if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
                include = 1 + solve(arr, currentIndex + 1, currentIndex, !isIncreasing);
            }
        } else {
            if (previousIndex == -1 || arr[ currentIndex ] < arr[ previousIndex ]) {
                include = 1 + solve(arr, currentIndex + 1, currentIndex, !isIncreasing);
            }
        }
        int exclude = solve(arr, currentIndex + 1, previousIndex, isIncreasing);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        Integer[][][] memo = new Integer[ arr.length ][ arr.length ][ 2 ];
        return Math.max(solve(arr, 0, -1, false, memo), solve(arr, 0, -1, true, memo));
    }

    private int solve(int[] arr, int currentIndex, int previousIndex, boolean isIncreasing, Integer[][][] memo) {
        if (currentIndex == arr.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ] == null) {
            int include = 0;
            if (isIncreasing) {
                if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
                    include = 1 + solve(arr, currentIndex + 1, currentIndex, !isIncreasing);
                }
            } else {
                if (previousIndex == -1 || arr[ currentIndex ] < arr[ previousIndex ]) {
                    include = 1 + solve(arr, currentIndex + 1, currentIndex, !isIncreasing);
                }
            }
            int exclude = solve(arr, currentIndex + 1, previousIndex, isIncreasing);
            memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ];
    }

    private int solveWithTabulation(int[] arr) {
        int[][] table = new int[ arr.length + 1 ][ 2 ];
        return solve(arr, table);
    }

    private int solve(int[] arr, int[][] table) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            table[ i ][ 0 ] = table[ i ][ 1 ] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[ i ] > arr[ j ]) {
                    table[ i ][ 0 ] = Math.max(table[ i ][ 0 ], 1 + table[ j ][ 1 ]);
                    max = Math.max(max, table[ i ][ 0 ]);
                } else if (arr[ i ] < arr[ j ]) {
                    table[ i ][ 1 ] = Math.max(table[ i ][ 1 ], 1 + table[ j ][ 0 ]);
                    max = Math.max(max, table[ i ][ 1 ]);
                }
            }
        }
        return max;
    }
}