package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;

public class ACoinChange {
    public static void main(String[] args) {
        ACoinChange game = new ACoinChange();
        int[] coins = new int[]{1, 3, 5};
        int sum = 11;
        System.out.println("Coins: ");
        Arrays.stream(coins).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTarget: "+sum);
        System.out.println("Minimum Coins: " + game.solve(coins, sum));

        coins = new int[]{1, 2, 5, 4};
        sum = 17;
        System.out.println("\nCoins: ");
        Arrays.stream(coins).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTarget: "+sum);
        System.out.println("Minimum Coins: " + game.solve(coins, sum));

        coins = new int[]{1, 2, 3, 5};
        sum = 13;
        System.out.println("\nCoins: ");
        Arrays.stream(coins).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTarget: "+sum);
        System.out.println("Minimum Coins: " + game.solve(coins, sum));

        coins = new int[]{1, 2, 3, 4};
        sum = 13;
        System.out.println("\nCoins: ");
        Arrays.stream(coins).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTarget: "+sum);
        System.out.println("Minimum Coins: " + game.solve(coins, sum));
    }

    private int solve(int[] coins, int sum) {
        int[][] table = new int[ coins.length + 1 ][ sum + 1 ];
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                table[ row ][ col ] = Integer.MAX_VALUE;
            }
        }
        for (int row = 0; row < table.length; row++) {
            table[ row ][ 0 ] = 0;
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int include = Integer.MAX_VALUE;
                if (coins[ row - 1 ] <= col) {
                    if (col - coins[ row - 1 ] == 0 || table[ row ][ col - coins[ row - 1 ] ] != 0) {
                        include = 1 + table[ row ][ col - coins[ row - 1 ] ];
                    }
                }
                int exclude = Math.min(table[ row ][ col ], table[ row - 1 ][ col ]);
                table[ row ][ col ] = Math.min(include, exclude);
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}