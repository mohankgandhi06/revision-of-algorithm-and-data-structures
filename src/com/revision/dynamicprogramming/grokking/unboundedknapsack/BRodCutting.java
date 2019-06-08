package com.revision.dynamicprogramming.grokking.unboundedknapsack;

public class BRodCutting {
    public static void main(String[] args) {
        BRodCutting game = new BRodCutting();
        int[] lengths = new int[]{1, 2, 3, 4, 5};
        int[] prices = new int[]{2, 6, 7, 10, 13};
        int rodLength = 5;
        System.out.println("Brute Force - Max Profit: " + game.solveWithBruteForce(lengths, prices, rodLength));
        System.out.println("Memoization - Max Profit: " + game.solveWithMemoization(lengths, prices, rodLength));
        System.out.println("Tabulation - Max Profit: " + game.solveWithTabulation(lengths, prices, rodLength));
    }

    private int solveWithBruteForce(int[] lengths, int[] prices, int rodLength) {
        return solve(lengths, prices, rodLength, 0);
    }

    private int solve(int[] lengths, int[] prices, int rodLength, int currentIndex) {
        if (currentIndex == lengths.length || rodLength == 0) return 0;
        int include = 0;
        if (rodLength >= lengths[ currentIndex ]) {
            include = prices[ currentIndex ] + solve(lengths, prices, rodLength - lengths[ currentIndex ], currentIndex);
        }
        int exclude = solve(lengths, prices, rodLength, currentIndex + 1);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] lengths, int[] prices, int rodLength) {
        Integer[][] memo = new Integer[ lengths.length ][ rodLength + 1 ];
        return solve(lengths, prices, rodLength, 0, memo);
    }

    private int solve(int[] lengths, int[] prices, int rodLength, int currentIndex, Integer[][] memo) {
        if (currentIndex == lengths.length || rodLength == 0) return 0;
        if (memo[ currentIndex ][ rodLength ] == null) {
            int include = 0;
            if (rodLength >= lengths[ currentIndex ]) {
                include = prices[ currentIndex ] + solve(lengths, prices, rodLength - lengths[ currentIndex ], currentIndex);
            }
            int exclude = solve(lengths, prices, rodLength, currentIndex + 1);
            memo[ currentIndex ][ rodLength ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ rodLength ];
    }

    private int solveWithTabulation(int[] lengths, int[] prices, int rodLength) {
        int[][] table = new int[ lengths.length + 1 ][ rodLength + 1 ];
        return solve(lengths, prices, table);
    }

    private int solve(int[] lengths, int[] prices, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int z = 0;
                int include = 0;
                if (lengths[ row - 1 ] <= col) {
                    include = prices[ row - 1 ] + table[ row ][ col - lengths[ row - 1 ] ];
                }
                int exclude = table[ row - 1 ][ col ];
                table[ row ][ col ] = Math.max(include, exclude);
            }
        }
        System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        System.out.println("Choice of the rod length: ");
        int row = table.length - 1;
        int col = table[ 0 ].length - 1;
        while (row > 0 && col > 0) {
            if (table[ row ][ col ] != table[ row - 1 ][ col ]) {
                System.out.print(lengths[ row - 1 ] + " ");
                col = col - lengths[ row - 1 ];
            } else {
                row = row - 1;
            }
        }
        System.out.println();
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}