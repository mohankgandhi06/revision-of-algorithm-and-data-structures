package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.Arrays;

public class ESubsetSum {
    public static void main(String[] args) {
        ESubsetSum game = new ESubsetSum();
        int[] set = new int[]{5, 2, 1, 3};
        int sum = 9;
        game.solve(set, sum);

        set = new int[]{5, 2, 1, 3};
        sum = 10;
        game.solve(set, sum);

        set = new int[]{5, 2, 1, 3};
        sum = 14;
        game.solve(set, sum);

        set = new int[]{5, 2, 1, 3};
        sum = 4;
        game.solve(set, sum);

        set = new int[]{5, 2, 3, 3};
        sum = 9;
        game.solve(set, sum);

        set = new int[]{5, 2, 3, 6, 10, 2, 1, 3};
        sum = 12;
        game.solve(set, sum);
    }

    private void solve(int[] set, int sum) {
        System.out.println("Set: ");
        Arrays.stream(set).forEach(value -> System.out.print(value + " "));
        System.out.println("\nSum: " + sum);
        System.out.println("Brute Force: " + solveWithBruteForce(set, sum, 0));
        Boolean[][] memo = new Boolean[ set.length ][ sum + 1 ];
        System.out.println("Memoization: " + solveWithMemoization(set, sum, 0, memo));
        boolean[][] table = new boolean[ set.length + 1 ][ sum + 1 ];
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = true;
        }
        System.out.println("Tabulation: " + solveWithTabulation(set, table));
        show(table);
    }

    private boolean solveWithBruteForce(int[] set, int sum, int currentIndex) {
        if (sum == 0) return true;
        if (currentIndex == set.length) return false;
        if (set[ currentIndex ] <= sum) {
            if (solveWithBruteForce(set, sum - set[ currentIndex ], currentIndex + 1)) {
                return true;
            }
        }
        return solveWithBruteForce(set, sum, currentIndex + 1);
    }

    private boolean solveWithMemoization(int[] set, int sum, int currentIndex, Boolean[][] memo) {
        if (sum == 0) return true;
        if (currentIndex == set.length) return false;
        if (memo[ currentIndex ][ sum ] == null) {
            if (set[ currentIndex ] <= sum) {
                if (solveWithBruteForce(set, sum - set[ currentIndex ], currentIndex + 1)) {
                    return true;
                }
            }
            memo[ currentIndex ][ sum ] = solveWithBruteForce(set, sum, currentIndex + 1);
        }
        return memo[ currentIndex ][ sum ];
    }

    private boolean solveWithTabulation(int[] set, boolean[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (set[ row - 1 ] <= col) {
                    if (table[ row - 1 ][ col - set[ row - 1 ] ]) {
                        table[ row ][ col ] = true;
                    }
                }
                table[ row ][ col ] = table[ row ][ col ] || table[ row - 1 ][ col ];
            }
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }

    private void show(boolean[][] table) {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] ? "T " : "F ");
            }
            System.out.println();
        }
        System.out.println();
    }
}