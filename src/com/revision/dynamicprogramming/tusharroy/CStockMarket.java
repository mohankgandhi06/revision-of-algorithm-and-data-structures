package com.revision.dynamicprogramming.tusharroy;

import java.util.Arrays;

public class CStockMarket {
    public static void main(String[] args) {
        CStockMarket game = new CStockMarket();
        int[] prices = new int[]{2, 5, 7, 1, 4, 3, 1, 3};
        int transaction = 3;
        game.solve(prices, transaction);

        prices = new int[]{2, 5, 7, 1, 4, 3, 1, 3, 5, 7, 3, 2, 4, 1};
        transaction = 5;
        System.out.println();
        game.solve(prices, transaction);

        prices = new int[]{2, 5, 7, 1, 4, 3, 1, 3, 5, 7, 3, 2, 4, 6};
        transaction = 5;
        System.out.println();
        game.solve(prices, transaction);
    }

    private void solve(int[] prices, int transaction) {
        System.out.println("------------------------------------------------------");
        System.out.print("Prices: ");
        Arrays.stream(prices).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTransaction: " + transaction);

        int[][] table = new int[ transaction + 1 ][ prices.length ];
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                /* EITHER EXCLUDE [row][col-1]
                 * TRY MAKING THE TRANSACTION ON EACH DAY BEFORE THE CURRENT AND ADDING IT TO THE PROFIT EARLIER I.E. ON THE DAY OF SELLING CURRENT ONE */
                int exclude = table[ row ][ col - 1 ];
                int include = 0;
                for (int i = 0; i < col; i++) {
                    include = Math.max(prices[ col ] - prices[ i ] + table[ row - 1 ][ i ], include);
                }
                table[ row ][ col ] = Math.max(exclude, include);
            }
        }

        System.out.println("\nProfit Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        System.out.println("\nTotal Profit Made: " + table[ table.length - 1 ][ table[ 0 ].length - 1 ]);
        System.out.println();
        showChoices(table, prices, transaction);
    }

    private void showChoices(int[][] table, int[] prices, int transaction) {
        int row = table.length - 1;
        int col = table[ 0 ].length - 1;
        while (row >= 0 && col >= 1) {
            if (table[ row ][ col - 1 ] != table[ row ][ col ]) {
                System.out.println("Transaction #" + transaction--);
                System.out.println("Chose to sell on day: " + col);
                int remainingProfit = table[ row ][ col ] - prices[ col ];
                int sellingAt = prices[ col ];
                /*System.out.println(remainingProfit);*/
                row--;
                while (col >= 0 && table[ row ][ col ] - prices[ col ] != remainingProfit) {
                    col--;
                }
                if (col >= 0) {
                    System.out.println("Buy on this Day: " + col);
                    System.out.println("Profit/Loss: " + (sellingAt - prices[ col ]));
                    continue;
                }
            }
            col--;
        }
    }
}