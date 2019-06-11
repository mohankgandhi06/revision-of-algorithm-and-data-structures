package com.revision.dynamicprogramming.grokking.unboundedknapsack;

import java.util.Arrays;

public class DMinimumCoinChange {
    public static void main(String[] args) {
        DMinimumCoinChange game = new DMinimumCoinChange();
        int[] denominations = new int[]{1, 2, 3};
        int totalAmount = 5;
        System.out.println("Array: ");
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, totalAmount));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, totalAmount));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, totalAmount));

        System.out.println("\nArray: ");
        denominations = new int[]{1, 2, 3};
        totalAmount = 11;
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, totalAmount));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, totalAmount));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, totalAmount));

        denominations = new int[]{1, 2, 3, 3, 1, 8, 1, 4, 3};
        totalAmount = 15;
        System.out.println("\nArray: ");
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, totalAmount));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, totalAmount));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, totalAmount));
    }

    private int solveWithBruteForce(int[] denominations, int totalAmount) {
        return solve(denominations, totalAmount, 0, 0);
    }

    private int solve(int[] denominations, int totalAmount, int currentIndex, int count) {
        if (totalAmount == 0) return count;
        if (currentIndex == denominations.length) return Integer.MAX_VALUE;
        int include = Integer.MAX_VALUE;
        if (totalAmount >= denominations[ currentIndex ]) {
            include = solve(denominations, totalAmount - denominations[ currentIndex ], currentIndex, count + 1);
        }
        int exclude = solve(denominations, totalAmount, currentIndex + 1, count);
        return Math.min(include, exclude);
    }

    private int solveWithMemoization(int[] denominations, int totalAmount) {
        Integer[][] memo = new Integer[ denominations.length ][ totalAmount + 1 ];
        return solve(denominations, totalAmount, 0, 0, memo);
    }

    private int solve(int[] denominations, int totalAmount, int currentIndex, int count, Integer[][] memo) {
        if (totalAmount == 0) return count;
        if (currentIndex == denominations.length) return Integer.MAX_VALUE;
        if (memo[ currentIndex ][ totalAmount ] == null) {
            int include = Integer.MAX_VALUE;
            if (totalAmount >= denominations[ currentIndex ]) {
                include = solve(denominations, totalAmount - denominations[ currentIndex ], currentIndex, count + 1);
            }
            int exclude = solve(denominations, totalAmount, currentIndex + 1, count);
            memo[ currentIndex ][ totalAmount ] = Math.min(include, exclude);
        }
        return memo[ currentIndex ][ totalAmount ];
    }

    private int solveWithTabulation(int[] denominations, int totalAmount) {
        int[][] table = new int[ denominations.length + 1 ][ totalAmount + 1 ];
        return solve(denominations, table);
    }

    private int solve(int[] denominations, int[][] table) {
        for (int col = 1; col < table[ 0 ].length; col++) {
            table[ 0 ][ col ] = Integer.MAX_VALUE;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int include = Integer.MAX_VALUE;
                if (col >= denominations[ row - 1 ]) {
                    include = 1 + table[ row ][ col - denominations[ row - 1 ] ];
                }
                int exclude = table[ row - 1 ][ col ];
                table[ row ][ col ] = Math.min(include, exclude);
            }
        }
        /*System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}