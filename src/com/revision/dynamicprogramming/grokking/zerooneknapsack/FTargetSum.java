package com.revision.dynamicprogramming.grokking.zerooneknapsack;

import java.util.Arrays;

public class FTargetSum {
    public static void main(String[] args) {
        FTargetSum game = new FTargetSum();
        int[] arr = new int[]{1, 1, 2, 3};
        int target = 1;
        System.out.println("Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, target));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, target));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, target));
        arr = new int[]{1, 2, 7, 1};
        target = 9;
        System.out.println("\nArray: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(arr, target));
        System.out.println("Memoization: " + game.solveWithMemoization(arr, target));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr, target));
    }

    private int solveWithBruteForce(int[] arr, int target) {
        return solve(arr, target, 0, 0);
    }

    private int solve(int[] arr, int target, int currentSum, int currentIndex) {
        if (arr.length == currentIndex) {
            if (currentSum == target) return 1;
            return 0;
        }
        int positive = solve(arr, target, currentSum + arr[ currentIndex ], currentIndex + 1);
        int negative = solve(arr, target, currentSum - arr[ currentIndex ], currentIndex + 1);
        return positive + negative;
    }

    private int solveWithMemoization(int[] arr, int target) {
        Integer[][] memo = new Integer[ arr.length ][ target ];
        return solve(arr, target, 0, 0, memo);
    }

    private int solve(int[] arr, int target, int currentSum, int currentIndex, Integer[][] memo) {
        if (arr.length == currentIndex) {
            if (currentSum == target) return 1;
            return 0;
        }
        if (memo[ currentIndex ][ currentSum ] == null) {
            int positive = solve(arr, target, currentSum + arr[ currentIndex ], currentIndex + 1);
            int negative = solve(arr, target, currentSum - arr[ currentIndex ], currentIndex + 1);
            memo[ currentIndex ][ currentSum ] = positive + negative;
        }
        return memo[ currentIndex ][ currentSum ];
    }

    private int solveWithTabulation(int[] arr, int target) {
        /* We can convert this problem into the subset sum problem that we solved previously
         * subset1 - subset2 = target (that we need to obtain)
         * subset1 + subset2 = total (of the array)
         * adding the equation 2 * subset1 = target + total
         * we need to find the subset 1 by including or excluding the elements now to obtain (target + total)/2 */
        int total = 0;
        for (int item : arr) {
            total += item;
        }
        int[][] table = new int[ arr.length + 1 ][ ((target + total) / 2) + 1 ];
        return solve(arr, target, 0, 0, table);
    }

    private int solve(int[] arr, int target, int currentSum, int currentIndex, int[][] table) {
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = 1;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                table[ row ][ col ] = table[ row - 1 ][ col ];
                if (col >= arr[ row - 1 ]) {
                    table[ row ][ col ] += table[ row - 1 ][ col - arr[ row - 1 ] ];
                }
            }
        }

        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}