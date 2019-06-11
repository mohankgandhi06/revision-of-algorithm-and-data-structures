package com.revision.dynamicprogramming.grokking.zerooneknapsack;

public class AIntro {
    public static void main(String[] args) {
        /*String[] items = new String[]{"Apple", "Orange", "Banana", "Melon"};*/
        int[] weights = new int[]{2, 3, 1, 4};
        int[] profits = new int[]{4, 7, 3, 5};
        int capacity = 5;
        AIntro game = new AIntro();
        System.out.println("Brute Force: ");
        int maxProfit = game.solveWithBruteForce(weights, profits, capacity);
        System.out.println("Max Profit reaped: " + maxProfit);

        System.out.println("Memoization: ");
        maxProfit = game.solveWithMemoization(weights, profits, capacity);
        System.out.println("Max Profit reaped: " + maxProfit);

        System.out.println("Tabulation: ");
        maxProfit = game.solveWithTabulation(weights, profits, capacity);
        System.out.println("Max Profit reaped: " + maxProfit);
    }

    public int solveWithBruteForce(int[] weights, int[] profits, int capacity) {
        return solve(weights, profits, 0, 0, 0, capacity);
    }

    public int solveWithMemoization(int[] weights, int[] profits, int capacity) {
        Integer[][] memo = new Integer[ weights.length ][ profits.length ];
        int result = solve(weights, profits, 0, 0, 0, capacity, memo);
        System.out.println("Memo: ");
        for (int row = 0; row < memo.length; row++) {
            for (int column = 0; column < memo[ 0 ].length; column++) {
                System.out.print((memo[ row ][ column ] == null ? 0 : memo[ row ][ column ]) + " ");
            }
            System.out.println();
        }
        return result;
    }

    public int solveWithTabulation(int[] weights, int[] profits, int capacity) {
        int[][] tabulation = new int[ weights.length ][ capacity + 1 ];
        return solve(weights, profits, capacity, tabulation);
    }

    /* BRUTE FORCE'S SOLVE */
    private int solve(int[] weights, int[] profits, int index, int currentWeight, int currentSum, int capacity) {
        if (index == weights.length) return currentSum;
        int include = 0;
        if (currentWeight + weights[ index ] <= capacity) {
            include = solve(weights, profits, index + 1, currentWeight + weights[ index ], currentSum + profits[ index ], capacity);
        }
        int exclude = solve(weights, profits, index + 1, currentWeight, currentSum, capacity);
        return Math.max(include, exclude);
    }

    /* MEMOIZATION'S SOLVE */
    private int solve(int[] weights, int[] profits, int currentIndex, int currentWeight, int currentSum, int capacity, Integer[][] memo) {
        if (currentIndex >= profits.length || currentWeight >= weights.length) return currentSum;
        if (memo[ currentIndex ][ currentWeight ] == null) {
            int include = 0;
            if (currentWeight + weights[ currentIndex ] <= capacity) {
                include = solve(weights, profits, currentIndex + 1, currentWeight + weights[ currentIndex ], currentSum + profits[ currentIndex ], capacity, memo);
            }
            int exclude = solve(weights, profits, currentIndex + 1, currentWeight, currentSum, capacity, memo);
            memo[ currentIndex ][ currentWeight ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ currentWeight ];
    }

    /* TABULATION'S SOLVE */
    private int solve(int[] weights, int[] profits, int capacity, int[][] table) {
        for (int row = 0; row < table.length; row++) {
            for (int column = 1; column < table[ 0 ].length; column++) {
                int include = 0;
                if (weights[ row ] <= column) {
                    include = profits[ row ] + (row - 1 >= 0 ? table[ row - 1 ][ column - weights[ row ] ] : 0);
                }
                int exclude = row - 1 >= 0 ? table[ row - 1 ][ column ] : 0;
                table[ row ][ column ] = Math.max(include, exclude);
            }
        }
        System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[ 0 ].length; column++) {
                System.out.print(table[ row ][ column ] + " ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ capacity ];
    }
}