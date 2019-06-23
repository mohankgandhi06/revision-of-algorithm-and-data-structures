package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.Arrays;

public class CCoinChange {
    public static void main(String[] args) {
        CCoinChange game = new CCoinChange();
        int[] coins = new int[]{1, 2, 3};
        int total = 4;
        game.solve(coins, total);

        coins = new int[]{1, 2, 3};
        total = 10;
        game.solve(coins, total);

        coins = new int[]{1, 2, 3};
        total = 13;
        game.solve(coins, total);
    }

    private void solve(int[] coins, int total) {
        System.out.println("Coins: ");
        Arrays.stream(coins).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTotal for which we have to find combinations: " + total);
        System.out.println("Brute Force: " + solveWithBruteForce(coins, total, 0));
        Integer[][] memo = new Integer[ coins.length ][ total + 1 ];
        System.out.println("Memoization: " + solveWithMemoization(coins, total, 0, memo));
        int[][] table = new int[ coins.length + 1 ][ total + 1 ];
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = 1;
        }
        System.out.println("Tabulation: " + solveWithTabulation(coins, table));
        show(table);
    }

    private int solveWithBruteForce(int[] coins, int total, int currentIndex) {
        if (currentIndex == coins.length) return 0;
        if (total == 0) return 1;
        int include = 0;
        if (coins[ currentIndex ] <= total) {
            include = solveWithBruteForce(coins, total - coins[ currentIndex ], currentIndex);
        }
        int exclude = solveWithBruteForce(coins, total, currentIndex + 1);
        return include + exclude;
    }

    private int solveWithMemoization(int[] coins, int total, int currentIndex, Integer[][] memo) {
        if (currentIndex == coins.length) return 0;
        if (total == 0) return 1;
        if (memo[ currentIndex ][ total ] == null) {
            int include = 0;
            if (coins[ currentIndex ] <= total) {
                include = solveWithMemoization(coins, total - coins[ currentIndex ], currentIndex, memo);
            }
            int exclude = solveWithMemoization(coins, total, currentIndex + 1, memo);
            memo[ currentIndex ][ total ] = include + exclude;
        }
        return memo[ currentIndex ][ total ];
    }

    private int solveWithTabulation(int[] coins, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (coins[ row - 1 ] <= col) {
                    table[ row ][ col ] = table[ row ][ col - coins[ row - 1 ] ];
                }
                table[ row ][ col ] += table[ row - 1 ][ col ];
            }
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }

    private void show(int[][] table) {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}