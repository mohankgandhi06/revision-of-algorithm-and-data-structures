package com.revision.dynamicprogramming.bytebybyte;

public class BMakingChange {
    public static void main(String[] args) {
        BMakingChange game = new BMakingChange();
        int[] cents = new int[]{1, 10, 5, 25};
        int total = 49;
        game.solve(cents, total);
        total = 6;
        System.out.println();
        game.solve(cents, total);
        total = 1;
        System.out.println();
        game.solve(cents, total);
    }

    private void solve(int[] cents, int total) {
        System.out.println("Total: " + total);
        System.out.println("Brute Force: " + solveWithBruteForce(cents, total, 0, 0));
        Integer[][] memo = new Integer[ cents.length + 1 ][ total + 1 ];
        System.out.println("Memoization: " + solveWithMemoization(cents, total, 0, 0, memo));
        int[][] table = new int[ cents.length + 1 ][ total + 1 ];
        System.out.println("Tabulation: " + solveWithTabulation(cents, table));
    }

    private int solveWithBruteForce(int[] cents, int total, int currentIndex, int coins) {
        if (currentIndex == cents.length) {
            if (total == 0) return coins;
            return Integer.MAX_VALUE;
        }
        int include = Integer.MAX_VALUE;
        if (cents[ currentIndex ] <= total) {
            include = solveWithBruteForce(cents, total - cents[ currentIndex ], currentIndex, coins + 1);
        }
        int exclude = solveWithBruteForce(cents, total, currentIndex + 1, coins);
        return Math.min(include, exclude);
    }

    private int solveWithMemoization(int[] cents, int total, int currentIndex, int coins, Integer[][] memo) {
        if (currentIndex == cents.length) {
            if (total == 0) return coins;
            return Integer.MAX_VALUE;
        }
        if (memo[ currentIndex ][ currentIndex ] == null) {
            int include = Integer.MAX_VALUE;
            if (cents[ currentIndex ] <= total) {
                include = solveWithBruteForce(cents, total - cents[ currentIndex ], currentIndex, coins + 1);
            }
            int exclude = solveWithBruteForce(cents, total, currentIndex + 1, coins);
            memo[ currentIndex ][ currentIndex ] = Math.min(include, exclude);
        }
        return memo[ currentIndex ][ currentIndex ];
    }

    private int solveWithTabulation(int[] cents, int[][] table) {
        for (int col = 0; col < table[ 0 ].length; col++) {
            table[ 0 ][ col ] = Integer.MAX_VALUE;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                table[ row ][ col ] = Integer.MAX_VALUE;//IMPORTANT
                if (cents[ row - 1 ] <= col) {
                    table[ row ][ col ] = 1 + table[ row ][ col - cents[ row - 1 ] ];
                }
                table[ row ][ col ] = Math.min(table[ row ][ col ], table[ row - 1 ][ col ]);
            }
        }
        /*for (int row = 1; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}