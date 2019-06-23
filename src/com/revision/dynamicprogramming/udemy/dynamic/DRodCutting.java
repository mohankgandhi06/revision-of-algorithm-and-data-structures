package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.Arrays;

public class DRodCutting {
    public static void main(String[] args) {
        DRodCutting game = new DRodCutting();
        int rod = 5;
        int[] lengths = new int[]{1, 2, 3, 4};
        int[] prices = new int[]{2, 5, 7, 3};
        game.solve(rod, lengths, prices);

        rod = 15;
        lengths = new int[]{1, 2, 3, 4};
        prices = new int[]{2, 3, 7, 3};
        System.out.println("\n");
        game.solve(rod, lengths, prices);

        rod = 13;
        lengths = new int[]{1, 2, 3, 4};
        prices = new int[]{2, 4, 7, 3};
        System.out.println("\n");
        game.solve(rod, lengths, prices);

        rod = 13;
        lengths = new int[]{8, 2, 6, 4};
        prices = new int[]{12, 4, 8, 3};
        System.out.println("\n");
        game.solve(rod, lengths, prices);
    }

    private void solve(int rod, int[] lengths, int[] prices) {
        System.out.println("Rod Length: " + rod);
        System.out.println("Pieces Length: ");
        Arrays.stream(lengths).forEach(value -> System.out.print(value + " "));
        System.out.println("\nPrices: ");
        Arrays.stream(prices).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force Max Profit: " + solveWithBruteForce(rod, lengths, prices, 0, 0));
        Integer[][] memo = new Integer[ lengths.length ][ rod + 1 ];
        System.out.println("Memoization Max Profit: " + solveWithMemoization(rod, lengths, prices, 0, memo));
        int[][] table = new int[ lengths.length + 1 ][ rod + 1 ];
        System.out.println("Tabulation Max Profit: " + solveWithTabulation(lengths, prices, table));
        show(table);
        showChoices(table, lengths);
    }

    private int solveWithBruteForce(int rod, int[] lengths, int[] prices, int currentIndex, int currentSum) {
        if (currentIndex == lengths.length || rod == 0) return currentSum;
        int include = 0;
        if (lengths[ currentIndex ] <= rod) {
            include = solveWithBruteForce(rod - lengths[ currentIndex ], lengths, prices, currentIndex, prices[ currentIndex ] + currentSum);
        }
        int exclude = solveWithBruteForce(rod, lengths, prices, currentIndex + 1, currentSum);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int rod, int[] lengths, int[] prices, int currentIndex, Integer[][] memo) {
        if (currentIndex == lengths.length || rod == 0) return 0;
        if (memo[ currentIndex ][ rod ] == null) {
            int include = 0;
            if (lengths[ currentIndex ] <= rod) {
                include = prices[ currentIndex ] + solveWithMemoization(rod - lengths[ currentIndex ], lengths, prices, currentIndex, memo);
            }
            int exclude = solveWithMemoization(rod, lengths, prices, currentIndex + 1, memo);
            memo[ currentIndex ][ rod ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ rod ];
    }

    private int solveWithTabulation(int[] lengths, int[] prices, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (lengths[ row - 1 ] <= col) {
                    table[ row ][ col ] = prices[ row - 1 ] + table[ row ][ col - lengths[ row - 1 ] ];
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

    private void showChoices(int[][] table, int[] lengths) {
        int row = table.length - 1;
        int col = table[ 0 ].length - 1;
        while (row >= 1 && col >= 0) {
            if (table[ row ][ col ] != table[ row - 1 ][ col ]) {
                System.out.print(lengths[ row - 1 ] + " ");
                col = col - lengths[ row - 1 ];
            } else {
                row--;
            }
        }
    }
}