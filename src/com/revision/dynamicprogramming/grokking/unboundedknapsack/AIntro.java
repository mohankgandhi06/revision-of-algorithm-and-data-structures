package com.revision.dynamicprogramming.grokking.unboundedknapsack;

public class AIntro {
    public static void main(String[] args) {
        AIntro game = new AIntro();
        /*String[] fruits = new String[]{"Apple", "Orange", "Melon"};*/
        int[] weights = new int[]{1, 2, 3};
        int[] profits = new int[]{15, 20, 50};
        int knapsackCapacity = 5;
        System.out.println("Brute Force: " + game.solveWithBruteForce(weights, profits, knapsackCapacity));
        System.out.println("Memoization: " + game.solveWithMemoization(weights, profits, knapsackCapacity));
        System.out.println("Tabulation: " + game.solveWithTabulation(weights, profits, knapsackCapacity));

        weights = new int[]{1, 3, 4, 5};
        profits = new int[]{15, 50, 60, 90};
        knapsackCapacity = 8;
        System.out.println("Brute Force: " + game.solveWithBruteForce(weights, profits, knapsackCapacity));
        System.out.println("Memoization: " + game.solveWithMemoization(weights, profits, knapsackCapacity));
        System.out.println("Tabulation: " + game.solveWithTabulation(weights, profits, knapsackCapacity));

        weights = new int[]{1, 3, 4, 5};
        profits = new int[]{15, 50, 30, 20};
        knapsackCapacity = 6;
        System.out.println("Brute Force: " + game.solveWithBruteForce(weights, profits, knapsackCapacity));
        System.out.println("Memoization: " + game.solveWithMemoization(weights, profits, knapsackCapacity));
        System.out.println("Tabulation: " + game.solveWithTabulation(weights, profits, knapsackCapacity));
    }

    private int solveWithBruteForce(int[] weights, int[] profits, int knapsackCapacity) {
        return solve(weights, profits, knapsackCapacity, 0);
    }

    private int solve(int[] weights, int[] profits, int knapsackCapacity, int currentIndex) {
        if (weights.length == currentIndex) return 0;
        int include = 0;
        if (weights[ currentIndex ] <= knapsackCapacity) {
            include = profits[ currentIndex ] + solve(weights, profits, knapsackCapacity - weights[ currentIndex ], currentIndex);
        }
        int exclude = solve(weights, profits, knapsackCapacity, currentIndex + 1);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] weights, int[] profits, int knapsackCapacity) {
        Integer[][] memo = new Integer[ weights.length ][ knapsackCapacity + 1 ];
        return solve(weights, profits, knapsackCapacity, 0, memo);
    }

    private int solve(int[] weights, int[] profits, int knapsackCapacity, int currentIndex, Integer[][] memo) {
        if (weights.length == currentIndex) return 0;
        if (memo[ currentIndex ][ knapsackCapacity ] == null) {
            int include = 0;
            if (weights[ currentIndex ] <= knapsackCapacity) {
                include = profits[ currentIndex ] + solve(weights, profits, knapsackCapacity - weights[ currentIndex ], currentIndex);
            }
            int exclude = solve(weights, profits, knapsackCapacity, currentIndex + 1);
            memo[ currentIndex ][ knapsackCapacity ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ knapsackCapacity ];
    }

    private int solveWithTabulation(int[] weights, int[] profits, int knapsackCapacity) {
        int[][] table = new int[ weights.length + 1 ][ knapsackCapacity + 1 ];
        return solve(weights, profits, table);
    }

    private int solve(int[] weights, int[] profits, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int include = 0;
                if (weights[ row - 1 ] <= col) {
                    include = profits[ row - 1 ] + table[ row ][ col - weights[ row - 1 ] ];
                }
                int exclude = table[ row - 1 ][ col ];
                table[ row ][ col ] = Math.max(include, exclude);
            }
        }

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}