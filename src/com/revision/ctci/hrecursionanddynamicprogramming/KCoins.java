package com.revision.ctci.hrecursionanddynamicprogramming;

public class KCoins {
    public static void main(String[] args) {
        KCoins game = new KCoins();
        int[] cents = new int[]{25, 10, 5, 1};
        int total = 100;
        game.solve(cents, total);
    }

    private void solve(int[] cents, int total) {
        int[][] table = new int[ cents.length + 1 ][ total + 1 ];
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = 1;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                table[ row ][ col ] = table[ row - 1 ][ col ];//Exclude the current cent
                if (cents[ row - 1 ] <= col) {
                    table[ row ][ col ] = table[ row ][ col ] + table[ row ][ col - cents[ row - 1 ] ];//Include the current cent
                }
            }
        }
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        System.out.println("No of ways: " + table[ table.length - 1 ][ table[ 0 ].length - 1 ]);
    }
}