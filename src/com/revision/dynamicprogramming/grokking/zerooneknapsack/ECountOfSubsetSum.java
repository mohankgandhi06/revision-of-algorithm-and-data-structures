package com.revision.dynamicprogramming.grokking.zerooneknapsack;

import java.util.Arrays;

public class ECountOfSubsetSum {
    public static void main(String[] args) {
        ECountOfSubsetSum game = new ECountOfSubsetSum();
        int[] arr = new int[]{1, 1, 2, 3};
        int sum = 4;
        System.out.println("Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, sum));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, sum));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, sum));

        arr = new int[]{1, 2, 7, 1, 5};
        sum = 9;
        System.out.println("\nArray: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, sum));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, sum));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, sum));

        arr = new int[]{1, 2, 7, 1, 5, 2, 5};
        sum = 9;
        System.out.println("\nArray: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, sum));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, sum));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, sum));

        arr = new int[]{1, 2, 7, 1, 5, 2, 5, 4, 3};
        sum = 9;
        System.out.println("\nArray: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, sum));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, sum));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, sum));
    }

    private int solveWithBruteForce(int[] arr, int sum) {
        return solve(arr, sum, 0);
    }

    private int solve(int[] arr, int sum, int currentIndex) {
        if (sum == 0) return 1;
        if (arr.length == currentIndex) return 0;
        int include = 0;
        if (arr[ currentIndex ] <= sum) {
            include = solve(arr, sum - arr[ currentIndex ], currentIndex + 1);
        }
        int exclude = solve(arr, sum, currentIndex + 1);
        return include + exclude;
    }

    private int solveWithMemoization(int[] arr, int sum) {
        Integer[][] memo = new Integer[ arr.length ][ sum + 1 ];
        return solve(arr, sum, 0, memo);
    }

    private int solve(int[] arr, int sum, int currentIndex, Integer[][] memo) {
        if (sum == 0) return 1;
        if (arr.length == currentIndex) return 0;
        if (memo[ currentIndex ][ sum ] == null) {
            int include = 0;
            if (arr[ currentIndex ] <= sum) {
                include = solve(arr, sum - arr[ currentIndex ], currentIndex + 1);
            }
            int exclude = solve(arr, sum, currentIndex + 1);
            memo[ currentIndex ][ sum ] = include + exclude;
        }
        return memo[ currentIndex ][ sum ];
    }

    private int solveWithTabulation(int[] arr, int sum) {
        int[][] table = new int[ arr.length + 1 ][ sum + 1 ];
        return solve(arr, table);
    }

    private int solve(int[] arr, int[][] table) {
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = 1;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (col >= arr[ row - 1 ]) {
                    table[ row ][ col ] = table[ row - 1 ][ col ] + table[ row - 1 ][ col - arr[ row - 1 ] ];
                } else {
                    table[ row ][ col ] = table[ row - 1 ][ col ];
                }
            }
        }
        System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}