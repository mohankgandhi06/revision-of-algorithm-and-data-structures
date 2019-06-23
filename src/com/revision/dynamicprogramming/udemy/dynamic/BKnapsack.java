package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.Arrays;

public class BKnapsack {
    public static void main(String[] args) {
        BKnapsack game = new BKnapsack();
        int capacity = 10;
        int[] weights = new int[]{5, 7, 9, 2};
        int[] prices = new int[]{10, 13, 19, 4};
        game.solve(capacity, weights, prices);

        capacity = 10;
        weights = new int[]{5, 7, 9, 2};
        prices = new int[]{10, 13, 19, 20};
        game.solve(capacity, weights, prices);

        capacity = 5;
        weights = new int[]{4, 2, 3};
        prices = new int[]{10, 4, 7};
        game.solve(capacity, weights, prices);
    }

    private void solve(int capacity, int[] weights, int[] prices) {
        System.out.println("Weights: ");
        Arrays.stream(weights).forEach(value -> System.out.print(value + " "));
        System.out.println("\nPrices: ");
        Arrays.stream(prices).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Profit: ");
        System.out.println("Brute Force: " + solveWithBruteForce(capacity, weights, prices, 0, 0));
        Integer[][] memo = new Integer[ weights.length ][ capacity + 1 ];
        System.out.println("Memoization: " + solveWithMemoization(capacity, weights, prices, 0, 0, memo));
        show(memo);
        int[][] table = new int[ weights.length + 1 ][ capacity + 1 ];
        System.out.println("Tabulation: " + solveWithTabulation(capacity, weights, prices, table));
        show(table);
        showChoices(table, weights);
        System.out.println();
    }

    private int solveWithBruteForce(int capacity, int[] weights, int[] prices, int currentIndex, int currentProfit) {
        if (currentIndex == weights.length || capacity == 0) return currentProfit;
        int include = 0;
        if (weights[ currentIndex ] <= capacity) {
            include = solveWithBruteForce(capacity - weights[ currentIndex ], weights, prices, currentIndex + 1, currentProfit + prices[ currentIndex ]);
        }
        int exclude = solveWithBruteForce(capacity, weights, prices, currentIndex + 1, currentProfit);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int capacity, int[] weights, int[] prices, int currentIndex, int currentProfit, Integer[][] memo) {
        if (currentIndex == weights.length || capacity == 0) return currentProfit;
        if (memo[ currentIndex ][ capacity ] == null) {
            int include = 0;
            if (weights[ currentIndex ] <= capacity) {
                include = solveWithMemoization(capacity - weights[ currentIndex ], weights, prices, currentIndex + 1, currentProfit + prices[ currentIndex ], memo);
            }
            int exclude = solveWithMemoization(capacity, weights, prices, currentIndex + 1, currentProfit, memo);
            memo[ currentIndex ][ capacity ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ capacity ];
    }

    private int solveWithTabulation(int capacity, int[] weights, int[] prices, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col <= capacity; col++) {
                if (weights[ row - 1 ] <= col) {
                    table[ row ][ col ] = prices[ row - 1 ] + table[ row - 1 ][ col - weights[ row - 1 ] ];
                }
                table[ row ][ col ] = Math.max(table[ row ][ col ], table[ row - 1 ][ col ]);
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

    private void showChoices(int[][] table, int[] weights) {
        int row = table.length - 1;
        int col = table[ 0 ].length - 1;
        while (row >= 1 && col >= 1) {
            if (table[ row ][ col ] == table[ row - 1 ][ col ]) {
                row--;
            } else {
                System.out.print(weights[ row - 1 ] + " ");
                col = col - weights[ row - 1 ];
                row--;
            }
        }
        System.out.println();
    }

    private void show(Integer[][] memo) {
        for (int row = 0; row < memo.length; row++) {
            for (int col = 0; col < memo[ 0 ].length; col++) {
                System.out.print(memo[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}