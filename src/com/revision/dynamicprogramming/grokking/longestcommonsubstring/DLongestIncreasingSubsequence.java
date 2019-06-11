package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

import java.util.Arrays;

public class DLongestIncreasingSubsequence {
    public static void main(String[] args) {
        DLongestIncreasingSubsequence game = new DLongestIncreasingSubsequence();
        int[] arr = new int[]{4, 2, 3, 6, 10, 1, 12};
        System.out.println("Input Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Increasing Sequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 10, 3, 7, 15};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Increasing Sequence");
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
            include = 1 + solve(arr, currentIndex + 1, currentIndex);
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
                include = 1 + solve(arr, currentIndex + 1, currentIndex, memo);
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
        Arrays.fill(table, 1);
        int maxLength = 1;
        for (int current = 1; current < table.length; current++) {
            for (int previous = 0; previous < current; previous++) {
                if (arr[ current ] > arr[ previous ] && table[ current ] <= table[ previous ]) {
                    table[ current ] = table[ previous ] + 1;
                    maxLength = Math.max(maxLength, table[ current ]);
                }
            }
            /*System.out.println("Current Index: " + current);
            Arrays.stream(table).forEach(value -> System.out.print(value + " "));
            System.out.println();*/
        }
        return maxLength;
    }
}