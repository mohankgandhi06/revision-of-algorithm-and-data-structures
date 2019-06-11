package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

import java.util.Arrays;

public class EMaximumSumIncreasingSubsequence {
    public static void main(String[] args) {
        EMaximumSumIncreasingSubsequence game = new EMaximumSumIncreasingSubsequence();
        int[] arr = new int[]{4, 1, 2, 6, 10, 1, 12};
        System.out.println("Input Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Sum Increasing Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 10, 3, 7, 15};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Sum Increasing Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 10, 3, 7, 15, 1, 23, 32, 4, 5, 8, 9, 10, 36};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Increasing Sequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        return solve(arr, 0, -1);
    }

    private int solve(int[] arr, int currentIndex, int previousIndex) {
        if (currentIndex == arr.length) return 0;
        int include = 0;
        if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
            include = arr[ currentIndex ] + solve(arr, currentIndex + 1, currentIndex);
        }
        int exclude = solve(arr, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        Integer[][] memo = new Integer[ arr.length ][ arr.length ];
        return solve(arr, 0, -1, memo);
    }

    private int solve(int[] arr, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == arr.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
                include = arr[ currentIndex ] + solve(arr, currentIndex + 1, currentIndex, memo);
            }
            int exclude = solve(arr, currentIndex + 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private int solveWithTabulation(int[] arr) {
        int[] table = new int[ arr.length ];
        return solve(arr, table);
    }

    private int solve(int[] arr, int[] table) {
        table[ 0 ] = arr[ 0 ];
        int maxSum = 0;
        for (int current = 1; current < arr.length; current++) {
            table[ current ] = arr[ current ];
            for (int previous = 0; previous < current; previous++) {
                if (arr[ current ] > arr[ previous ] && table[ current ] < table[ previous ] + arr[ current ]) {
                    table[ current ] = table[ previous ] + arr[ current ];
                }
                maxSum = Math.max(maxSum, table[ current ]);
            }
            /*System.out.println("Current Index: " + current);
            Arrays.stream(table).forEach(value -> System.out.print(value + " "));
            System.out.println();*/
        }
        return maxSum;
    }
}