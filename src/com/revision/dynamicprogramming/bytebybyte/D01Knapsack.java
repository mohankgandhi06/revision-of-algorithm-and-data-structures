package com.revision.dynamicprogramming.bytebybyte;

import java.util.Arrays;

public class D01Knapsack {
    public static void main(String[] args) {
        D01Knapsack game = new D01Knapsack();
        int[] weights = new int[]{2, 2, 3};
        int[] prices = new int[]{6, 10, 12};
        int capacity = 5;
        game.solve(weights, prices, capacity);

        weights = new int[]{2, 2, 3};
        prices = new int[]{6, 10, 12};
        capacity = 10;
        System.out.println();
        game.solve(weights, prices, capacity);

        weights = new int[]{1, 2, 3};
        prices = new int[]{6, 10, 11};
        capacity = 5;
        System.out.println();
        game.solve(weights, prices, capacity);

        weights = new int[]{2, 3, 4};
        prices = new int[]{6, 11, 18};
        capacity = 5;
        System.out.println();
        game.solve(weights, prices, capacity);

        weights = new int[]{1, 3, 4};
        prices = new int[]{6, 11, 12};
        capacity = 15;
        System.out.println();
        game.solve(weights, prices, capacity);
    }

    private void solve(int[] weights, int[] prices, int capacity) {
        System.out.println("Capacity: " + capacity);
        System.out.println("Weights: ");
        Arrays.stream(weights).forEach(value -> System.out.print(value + " "));
        System.out.println("\nPrices: ");
        Arrays.stream(prices).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + solveWithBruteForce(weights, prices, capacity, 0));
        Integer[][] memo = new Integer[ weights.length ][ capacity + 1 ];
        System.out.println("Brute Force: " + solveWithMemoization(weights, prices, capacity, 0, memo));
        int[][] table = new int[ weights.length + 1 ][ capacity + 1 ];
        System.out.println("Brute Force: " + solveWithTabulation(weights, prices, table));
    }

    private int solveWithBruteForce(int[] weights, int[] prices, int capacity, int currentIndex) {
        if (currentIndex == weights.length) return 0;
        int include = 0;
        if (weights[ currentIndex ] <= capacity) {
            include = prices[ currentIndex ] + solveWithBruteForce(weights, prices, capacity - weights[ currentIndex ], currentIndex + 1);
        }
        int exclude = solveWithBruteForce(weights, prices, capacity, currentIndex + 1);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] weights, int[] prices, int capacity, int currentIndex, Integer[][] memo) {
        if (currentIndex == weights.length) return 0;
        if (memo[ currentIndex ][ capacity ] == null) {
            int include = 0;
            if (weights[ currentIndex ] <= capacity) {
                include = prices[ currentIndex ] + solveWithMemoization(weights, prices, capacity - weights[ currentIndex ], currentIndex + 1, memo);
            }
            int exclude = solveWithMemoization(weights, prices, capacity, currentIndex + 1, memo);
            memo[ currentIndex ][ capacity ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ capacity ];
    }

    private int solveWithTabulation(int[] weights, int[] prices, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (weights[ row - 1 ] <= col) {
                    table[ row ][ col ] = prices[ row - 1 ] + table[ row - 1 ][ col - weights[ row - 1 ] ];
                }
                table[ row ][ col ] = Math.max(table[ row ][ col ], table[ row - 1 ][ col ]);
            }
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}