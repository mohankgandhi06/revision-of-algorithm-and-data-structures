package com.revision.dynamicprogramming.grokking.zerooneknapsack;

import java.util.Arrays;

public class CSubsetSum {
    public static void main(String[] args) {
        CSubsetSum game = new CSubsetSum();
        int[] arr = new int[]{1, 2, 3, 7};
        int subsetSum = 6;
        System.out.println("Array #1: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, subsetSum));
        System.out.println("\nMemoization: " + game.solveWithMemoization(arr, subsetSum));
        System.out.println("\nTabulation: " + game.solveWithTabulation(arr, subsetSum));

        arr = new int[]{1, 2, 7, 1, 5};
        subsetSum = 10;
        System.out.println("\nArray #2: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, subsetSum));
        System.out.println("\nMemoization: " + game.solveWithMemoization(arr, subsetSum));
        System.out.println("\nTabulation: " + game.solveWithTabulation(arr, subsetSum));

        arr = new int[]{1, 3, 4, 8};
        subsetSum = 6;
        System.out.println("\nArray #3: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, subsetSum));
        System.out.println("\nMemoization: " + game.solveWithMemoization(arr, subsetSum));
        System.out.println("\nTabulation: " + game.solveWithTabulation(arr, subsetSum));

        arr = new int[]{1, 3, 4, 8, 2};
        subsetSum = 6;
        System.out.println("\nArray #4: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, subsetSum));
        System.out.println("\nMemoization: " + game.solveWithMemoization(arr, subsetSum));
        System.out.println("\nTabulation: " + game.solveWithTabulation(arr, subsetSum));

        arr = new int[]{1, 3, 2, 4, 8, 2};
        subsetSum = 6;
        System.out.println("\nArray #5: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, subsetSum));
        System.out.println("\nMemoization: " + game.solveWithMemoization(arr, subsetSum));
        System.out.println("\nTabulation: " + game.solveWithTabulation(arr, subsetSum));
    }

    private boolean solveWithBruteForce(int[] arr, int subsetSum) {
        return solve(arr, subsetSum, subsetSum, 0);
    }

    private boolean solveWithMemoization(int[] arr, int subsetSum) {
        Boolean[][] memo = new Boolean[ arr.length ][ subsetSum + 1 ];
        return solve(arr, subsetSum, subsetSum, 0, memo);
    }

    private boolean solveWithTabulation(int[] arr, int subsetSum) {
        boolean[][] table = new boolean[ arr.length + 1 ][ subsetSum + 1 ];
        return solve(arr, table);
    }

    private boolean solve(int[] arr, int subsetSum, int target, int index) {
        if (index == arr.length || subsetSum == 0) return subsetSum == 0;
        boolean include = false;
        if (subsetSum >= arr[ index ]) {
            include = solve(arr, subsetSum - arr[ index ], target, index + 1);
        }
        boolean exclude = solve(arr, subsetSum, target, index + 1);
        return include || exclude;
    }

    private boolean solve(int[] arr, int subsetSum, int target, int index, Boolean[][] memo) {
        if (index == arr.length || subsetSum == 0) return subsetSum == 0;
        if (memo[ index ][ subsetSum ] == null || !memo[ index ][ subsetSum ]) {
            boolean include = false;
            if (subsetSum >= arr[ index ]) {
                include = solve(arr, subsetSum - arr[ index ], target, index + 1, memo);
            }
            boolean exclude = solve(arr, subsetSum, target, index + 1, memo);
            memo[ index ][ subsetSum ] = include || exclude;
        }
        return memo[ index ][ subsetSum ];
    }

    private boolean solve(int[] arr, boolean[][] table) {
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = true;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (arr[ row - 1 ] > col) {
                    table[ row ][ col ] = table[ row - 1 ][ col ];
                } else {
                    boolean exclude = ((col - arr[ row - 1 ] >= 0) ? table[ row - 1 ][ col - arr[ row - 1 ] ] : false);
                    table[ row ][ col ] = table[ row - 1 ][ col ] || exclude;
                }
            }
        }
        System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print((table[ row ][ col ] ? "T" : "F") + " ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}