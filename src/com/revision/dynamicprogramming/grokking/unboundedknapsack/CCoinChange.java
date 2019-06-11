package com.revision.dynamicprogramming.grokking.unboundedknapsack;

import java.util.Arrays;

public class CCoinChange {
    public static void main(String[] args) {
        CCoinChange game = new CCoinChange();
        int[] denominations = new int[]{3, 2, 1};
        int total = 5;
        System.out.println("Array: ");
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, total));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, total));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, total));

        denominations = new int[]{1, 2, 3, 2, 4, 5, 1, 6};
        total = 12;
        System.out.println("\nArray: ");
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, total));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, total));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, total));

        denominations = new int[]{1, 2, 3, 2, 4, 5};
        total = 12;
        System.out.println("\nArray: ");
        Arrays.stream(denominations).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(denominations, total));
        System.out.println("Memoization: " + game.solveWithMemoization(denominations, total));
        System.out.println("Tabulation: " + game.solveWithTabulation(denominations, total));
    }

    private int solveWithBruteForce(int[] denominations, int total) {
        return solve(denominations, total, 0);
    }

    private int solve(int[] denominations, int total, int currentIndex) {
        if (total == 0) return 1;
        if (currentIndex == denominations.length) return 0;
        int include = 0;
        if (total >= denominations[ currentIndex ]) {
            include = solve(denominations, total - denominations[ currentIndex ], currentIndex);
        }
        int exclude = solve(denominations, total, currentIndex + 1);
        return include + exclude;
    }

    private int solveWithMemoization(int[] denominations, int total) {
        Integer[][] memo = new Integer[ denominations.length ][ total + 1 ];
        return solve(denominations, total, 0, memo);
    }

    private int solve(int[] denominations, int total, int currentIndex, Integer[][] memo) {
        if (total == 0) return 1;
        if (currentIndex == denominations.length) return 0;
        if (memo[ currentIndex ][ total ] == null) {
            int include = 0;
            if (total >= denominations[ currentIndex ]) {
                include = solve(denominations, total - denominations[ currentIndex ], currentIndex);
            }
            int exclude = solve(denominations, total, currentIndex + 1);
            memo[ currentIndex ][ total ] = include + exclude;
        }
        return memo[ currentIndex ][ total ];
    }

    private int solveWithTabulation(int[] denominations, int total) {
        int[][] table = new int[ denominations.length + 1 ][ total + 1 ];
        return solve(denominations, table);
    }

    private int solve(int[] denominations, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int z = 1;
                int include = 0;
                if (denominations[ row - 1 ] <= col) {
                    if (col - denominations[ row - 1 ] == 0) {
                        include = 1;
                    }
                    include += table[ row ][ col - denominations[ row - 1 ] ];
                }
                int exclude = table[ row - 1 ][ col ];
                table[ row ][ col ] = include + exclude;
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