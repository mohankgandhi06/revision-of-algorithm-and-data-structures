package com.revision.dynamicprogramming.topcoder;

public class GAppleFarming {
    public static void main(String[] args) {
        GAppleFarming game = new GAppleFarming();
        int[][] farm = new int[][]{
                {2, 4, 6, 2, 4, 3},
                {5, 1, 1, 4, 3, 1},
                {4, 3, 4, 2, 3, 2},
                {3, 4, 2, 5, 6, 6},
                {5, 5, 3, 4, 5, 4}
        };
        game.solve(farm);

        farm = new int[][]{
                {2, 4, 6, 2, 4, 3},
                {5, 8, 4, 4, 3, 1},
                {4, 3, 4, 2, 7, 2},
                {10, 4, 7, 6, 6, 6},
                {12, 5, 3, 4, 3, 4}
        };
        System.out.println();
        game.solve(farm);

        farm = new int[][]{
                {2, 4, 6, 2, 4, 3},
                {5, 8, 4, 4, 3, 1},
                {4, 3, 4, 2, 7, 2},
                {10, 4, 7, 6, 6, 6},
                {12, 8, 3, 4, 3, 4}
        };
        System.out.println();
        game.solve(farm);

        farm = new int[][]{
                {2, 4, 6, 9, 4, 8},
                {5, 8, 4, 4, 8, 1},
                {4, 3, 4, 9, 7, 3},
                {10, 4, 7, 6, 6, 6},
                {12, 8, 3, 4, 3, 4}
        };
        System.out.println();
        game.solve(farm);
    }

    private void solve(int[][] farm) {
        show(farm);
        System.out.println("Maximum Apples");
        System.out.println("Brute Force: " + solveWithBruteForce(farm, 0, 0));
        Integer[][] memo = new Integer[ farm.length ][ farm[ 0 ].length ];
        System.out.println("Memoization: " + solveWithMemoization(farm, 0, 0, memo));
        System.out.println("Tabulation: " + solveWithTabulation(farm));
    }

    private int solveWithBruteForce(int[][] farm, int row, int col) {
        if (row == farm.length || col == farm[ 0 ].length) return 0;
        int right = farm[ row ][ col ] + solveWithBruteForce(farm, row, col + 1);
        int down = farm[ row ][ col ] + solveWithBruteForce(farm, row + 1, col);
        return Math.max(right, down);
    }

    private int solveWithMemoization(int[][] farm, int row, int col, Integer[][] memo) {
        if (row == farm.length || col == farm[ 0 ].length) return 0;
        if (memo[ row ][ col ] == null) {
            int right = farm[ row ][ col ] + solveWithMemoization(farm, row, col + 1, memo);
            int down = farm[ row ][ col ] + solveWithMemoization(farm, row + 1, col, memo);
            memo[ row ][ col ] = Math.max(right, down);
        }
        return memo[ row ][ col ];
    }

    private int solveWithTabulation(int[][] farm) {
        int[][] apples = new int[ farm.length + 1 ][ farm[ 0 ].length + 1 ];
        for (int row = 1; row < apples.length; row++) {
            for (int col = 1; col < apples[ 0 ].length; col++) {
                apples[ row ][ col ] = farm[ row - 1 ][ col - 1 ] + Math.max(apples[ row - 1 ][ col ], apples[ row ][ col - 1 ]);
            }
        }
        for (int row = 1; row < apples.length; row++) {
            for (int col = 1; col < apples[ 0 ].length; col++) {
                System.out.print(apples[ row ][ col ] + " ");
            }
            System.out.println();
        }
        return apples[ apples.length - 1 ][ apples[ 0 ].length - 1 ];
    }

    private void show(int[][] farm) {
        System.out.println("Farm: ");
        for (int row = 0; row < farm.length; row++) {
            for (int col = 0; col < farm[ 0 ].length; col++) {
                System.out.print(farm[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}