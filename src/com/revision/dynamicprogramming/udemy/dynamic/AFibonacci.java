package com.revision.dynamicprogramming.udemy.dynamic;

public class AFibonacci {
    public static void main(String[] args) {
        AFibonacci game = new AFibonacci();
        int nth = 8;
        game.solve(nth);

        nth = 20;
        System.out.println();
        game.solve(nth);

        nth = 30;
        System.out.println();
        game.solve(nth);

        nth = 40;
        System.out.println();
        game.solve(nth);
    }

    private void solve(int nth) {
        System.out.println("Nth "+nth);
        System.out.println("Brute Force - fibonacci number is: " + solveWithBruteForce(nth));
        Integer[] memo = new Integer[ nth + 1 ];
        memo[ 0 ] = 0;
        memo[ 1 ] = 1;
        System.out.println("Memoization - fibonacci number is: " + solveWithMemoization(nth, memo));
        System.out.println("Tabulation - fibonacci number is: " + solveWithTabulation(nth));
        System.out.println("Tabulation Optimized - fibonacci number is: " + solveWithTabulationOptimization(nth));
    }

    private int solveWithBruteForce(int nth) {
        if (nth == 0 || nth == 1) return nth;
        return solveWithBruteForce(nth - 1) + solveWithBruteForce(nth - 2);
    }

    private int solveWithMemoization(int nth, Integer[] memo) {
        if (memo[ nth ] == null) {
            memo[ nth ] = solveWithMemoization(nth - 1, memo) + solveWithMemoization(nth - 2, memo);
        }
        return memo[ nth ];
    }

    private int solveWithTabulation(int nth) {
        int[] tabulation = new int[ nth + 1 ];
        tabulation[ 0 ] = 0;
        tabulation[ 1 ] = 1;
        for (int i = 1; i < nth; i++) {
            tabulation[ i + 1 ] = tabulation[ i ] + tabulation[ i - 1 ];
        }
        return tabulation[ tabulation.length - 1 ];
    }

    private int solveWithTabulationOptimization(int nth) {
        int n = 1;
        int nMinusOne = 0;
        int fibonacci = 0;
        for (int i = 1; i < nth; i++) {
            fibonacci = n + nMinusOne;
            nMinusOne = n;
            n = fibonacci;
        }
        return fibonacci;
    }
}