package com.revision.dynamicprogramming.grokking.zerooneknapsack;

import java.util.Arrays;

public class BEqualSubsetSumPartition {
    public static void main(String[] args) {
        BEqualSubsetSumPartition game = new BEqualSubsetSumPartition();
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println("Input Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithBruteForce(arr));
        System.out.println("\nMemoization:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithMemoization(arr));
        System.out.println("\nTabulation:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 3, 4, 7};
        System.out.println("\n\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithBruteForce(arr));
        System.out.println("\nMemoization:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithMemoization(arr));
        System.out.println("\nTabulation:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithTabulation(arr));

        arr = new int[]{2, 3, 4, 6, 13, 2, 3};
        System.out.println("\n\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithBruteForce(arr));
        System.out.println("\nMemoization:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithMemoization(arr));
        System.out.println("\nTabulation:");
        System.out.println("Can it be divided into equal sum: " + game.solveWithTabulation(arr));
    }

    private boolean solveWithBruteForce(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        if (total % 2 == 1) return false;
        return solve(arr, 0, total / 2);
    }

    private boolean solveWithMemoization(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        if (total % 2 == 1) return false;
        Boolean[] memo = new Boolean[ arr.length ];
        return solve(arr, 0, total / 2, memo);
    }

    private boolean solveWithTabulation(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        if (total % 2 == 1) return false;
        boolean[][] tabulation = new boolean[ arr.length ][ (total / 2) + 1 ];
        for (int row = 0; row < tabulation.length; row++) {
            tabulation[ row ][ 0 ] = true;
        }
        for (int col = 1; col <= total / 2; col++) {
            tabulation[ 0 ][ col ] = (arr[ 0 ] == col ? true : false);
        }
        return solve(arr, tabulation);
    }

    private boolean solve(int[] arr, int index, int target) {
        if (index >= arr.length) return target == 0;
        boolean include = false;
        if (target - arr[ index ] >= 0) {
            include = solve(arr, index + 1, target - arr[ index ]);
        }
        boolean exclude = solve(arr, index + 1, target);
        return include || exclude;
    }

    private boolean solve(int[] arr, int index, int target, Boolean[] memo) {
        if (index >= arr.length) return target == 0;
        if (memo[ index ] == null || !memo[ index ]) {
            boolean include = false;
            if (target - arr[ index ] >= 0) {
                include = solve(arr, index + 1, target - arr[ index ], memo);
            }
            boolean exclude = solve(arr, index + 1, target, memo);
            memo[ index ] = include || exclude;
        }
        return memo[ index ];
    }

    private boolean solve(int[] arr, boolean[][] tabulation) {
        for (int row = 1; row < tabulation.length; row++) {
            for (int column = 1; column < tabulation[ 0 ].length; column++) {
                if (tabulation[ row - 1 ][ column ]) {
                    tabulation[ row ][ column ] = tabulation[ row - 1 ][ column ];
                } else if (column >= arr[ row ]) {
                    tabulation[ row ][ column ] = tabulation[ row - 1 ][ column - arr[ row ] ];
                }
            }
        }
        /*System.out.println("Table: ");
        for (int row = 0; row < tabulation.length; row++) {
            for (int column = 0; column < tabulation[ 0 ].length; column++) {
                System.out.print((tabulation[ row ][ column ] ? "T" : "F") + " ");
            }
            System.out.println();
        }*/
        return tabulation[ tabulation.length - 1 ][ tabulation[ 0 ].length - 1 ];
    }
}