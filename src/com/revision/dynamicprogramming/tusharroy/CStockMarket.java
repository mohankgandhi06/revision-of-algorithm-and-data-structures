package com.revision.dynamicprogramming.tusharroy;

import java.util.Arrays;
import java.util.HashMap;

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

        prices = new int[]{2, 5, 7, 1, 4, 6, 1, 13, 5, 7, 10, 2, 4, 6};
        transaction = 5;
        System.out.println();
        game.solve(prices, transaction);

        prices = new int[]{12, 5, 7, 3, 4, 3, 7, 3, 5, 7, 6, 2, 4, 8};
        transaction = 4;
        System.out.println();
        game.solve(prices, transaction);
    }

    private void solve(int[] prices, int transaction) {
        System.out.println("------------------------------------------------------");
        System.out.print("Prices: ");
        Arrays.stream(prices).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTransaction: " + transaction);

        System.out.println("\nMaximum Profit: ");
        System.out.print("Brute Force: ");
        BuySell result = new BuySell(0, transaction);
        result = solveWithBruteForce(prices, transaction, 0, 0, result);
        showChoices(result, prices);

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Memoization: ");
        int max = 0;
        for (int item : prices) {
            max += item;
        }
        /*BuySell[][][][] memo = new BuySell[ transaction ][ prices.length ][ prices.length ][ max ];*/
        HashMap<String, BuySell> memo = new HashMap<>();
        result = new BuySell(0, transaction);
        result = solveWithMemoization(prices, transaction, 0, 0, result, memo);
        showChoices(result, prices);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        solveWithTabulation(prices, transaction);
    }

    private BuySell solveWithBruteForce(int[] prices, int transaction, int currentDay, int buyingDay, BuySell result) {
        if (transaction == 0) return result;
        if (currentDay == prices.length) return new BuySell(0, 0);
        BuySell buyInclude = solveWithBruteForce(prices, transaction, currentDay + 1, currentDay, result);
        BuySell tempResult = new BuySell((result.profit + (prices[ currentDay ] - prices[ buyingDay ])), result.days);
        tempResult.days[ transaction - 1 ][ 0 ] = buyingDay;
        tempResult.days[ transaction - 1 ][ 1 ] = currentDay;
        BuySell sellInclude = solveWithBruteForce(prices, transaction - 1, currentDay, currentDay, tempResult);
        BuySell sellExclude = solveWithBruteForce(prices, transaction, currentDay + 1, buyingDay, result);
        return maximum(buyInclude, sellInclude, sellExclude);
    }

    private BuySell maximum(BuySell buyInclude, BuySell sellInclude, BuySell sellExclude) {
        int max = Math.max(buyInclude.profit, Math.max(sellInclude.profit, sellExclude.profit));
        if (max == buyInclude.profit) return buyInclude;
        else if (max == sellInclude.profit) return sellInclude;
        return sellExclude;
    }

    private BuySell solveWithMemoization(int[] prices, int transaction, int currentDay, int buyingDay, BuySell result, HashMap<String, BuySell> memo) {
        if (transaction < 0) return new BuySell(0, 0);
        if (transaction == 0) return result;
        if (currentDay == prices.length) return new BuySell(0, 0);
        if (!memo.containsKey(String.valueOf(transaction - 1).concat(String.valueOf(currentDay)).concat(String.valueOf(buyingDay)).concat(String.valueOf(result.profit)))) {
            BuySell buyInclude = solveWithMemoization(prices, transaction, currentDay + 1, currentDay, result, memo);
            BuySell tempResult = new BuySell((result.profit + (prices[ currentDay ] - prices[ buyingDay ])), result.days);
            tempResult.days[ transaction - 1 ][ 0 ] = buyingDay;
            tempResult.days[ transaction - 1 ][ 1 ] = currentDay;
            BuySell sellInclude = solveWithMemoization(prices, transaction - 1, currentDay, currentDay, tempResult, memo);
            BuySell sellExclude = solveWithMemoization(prices, transaction, currentDay + 1, buyingDay, result, memo);
            memo.put(String.valueOf(transaction - 1).concat(String.valueOf(currentDay)).concat(String.valueOf(buyingDay)).concat(String.valueOf(result.profit)), maximum(buyInclude, sellInclude, sellExclude));
        }
        return memo.get(String.valueOf(transaction - 1).concat(String.valueOf(currentDay)).concat(String.valueOf(buyingDay)).concat(String.valueOf(result.profit)));
    }

    private void solveWithTabulation(int[] prices, int transaction) {
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

        /*System.out.println("\nProfit Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        System.out.println("Tabulation: " + table[ table.length - 1 ][ table[ 0 ].length - 1 ]);
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

    private void showChoices(BuySell result, int[] prices) {
        System.out.println(result.profit);
        for (int transaction = 0; transaction < result.days.length; transaction++) {
            System.out.println("Trasaction #" + (transaction + 1) + " Buy on: " + result.days[ transaction ][ 0 ] + " Sell on: " + result.days[ transaction ][ 1 ] + " Profit: " + (prices[ result.days[ transaction ][ 1 ] ] - prices[ result.days[ transaction ][ 0 ] ]));
        }
    }
}

class BuySell {
    public int profit;
    public int[][] days;

    public BuySell(int profit, int transactions) {
        this.profit = profit;
        this.days = new int[ transactions ][ 2 ];
    }

    public BuySell(int profit, int[][] days) {
        this.profit = profit;
        this.days = new int[ days.length ][ days[ 0 ].length ];
        for (int row = 0; row < days.length; row++) {
            for (int col = 0; col < days[ 0 ].length; col++) {
                this.days[ row ][ col ] = days[ row ][ col ];
            }
        }
    }
}