package com.revision.dynamicprogramming.grokking.zerooneknapsack;

import java.util.Arrays;

public class DMinimumSubsetSumDifference {
    public static void main(String[] args) {
        DMinimumSubsetSumDifference game = new DMinimumSubsetSumDifference();
        int[] arr = new int[]{1, 2, 3, 9};
        System.out.println("Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        System.out.println("\nArray: ");
        arr = new int[]{1, 2, 7, 1, 5};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        System.out.println("\nArray: ");
        arr = new int[]{1, 3, 100, 4};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        System.out.println("\nArray: ");
        arr = new int[]{1, 3, 100, 4, 40, 4, 74, 2, 19};// 19, 100, 1, 3 (123) || 74, 40, 4, 4, 2 (124)
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        return solve(arr, 0, Integer.MAX_VALUE, total, 0);
    }

    private int solve(int[] arr, int index, int minDifference, int total, int leftSubset) {
        if (index == arr.length) return minDifference;
        int leftSubsetWithIncluded = leftSubset + arr[ index ];
        int diff = (total - leftSubsetWithIncluded) - leftSubsetWithIncluded;
        int include = minDifference;
        if (diff >= 0) {
            int min = Math.min(minDifference, diff);
            include = solve(arr, index + 1, min, total, leftSubsetWithIncluded);
        }
        int exclude = solve(arr, index + 1, minDifference, total, leftSubset);
        return Math.min(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        Integer[] memo = new Integer[ arr.length ];
        return solve(arr, 0, Integer.MAX_VALUE, total, 0, memo);
    }

    private int solve(int[] arr, int index, int minDifference, int total, int leftSubset, Integer[] memo) {
        if (index == arr.length) return minDifference;
        if (memo[ index ] == null) {
            int leftSubsetWithIncluded = leftSubset + arr[ index ];
            int diff = (total - leftSubsetWithIncluded) - leftSubsetWithIncluded;
            int include = minDifference;
            if (diff >= 0) {
                int min = Math.min(minDifference, diff);
                include = solve(arr, index + 1, min, total, leftSubsetWithIncluded);
            }
            int exclude = solve(arr, index + 1, minDifference, total, leftSubset);
            memo[ index ] = Math.min(include, exclude);
        }
        return memo[ index ];
    }

    private int solveWithTabulation(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        boolean[][] table = new boolean[ arr.length + 1 ][ (total / 2) + 1 ];
        return solve(arr, table, total);
    }

    private int solve(int[] arr, boolean[][] table, int total) {
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = true;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (arr[ row - 1 ] <= col) {
                    table[ row ][ col ] = table[ row - 1 ][ col ] || table[ row - 1 ][ col - arr[ row - 1 ] ];
                } else {
                    table[ row ][ col ] = table[ row - 1 ][ col ];
                }
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print((table[ row ][ col ] ? "T" : "F") + " ");
            }
            System.out.println();
        }*/
        boolean flag = false;
        int count = 0;
        while (!flag) {
            if (table[ table.length - 1 ][ table[ 0 ].length - 1 - count ]) {
                break;
            }
            count++;
        }
        /*System.out.println("Count: " + count);
        System.out.println(total - (count * 2) + ((total % 2) == 1 ? 1 : 0));*/
        return total - (total - (count * 2)) + ((total % 2) == 1 ? 1 : 0);
    }
}