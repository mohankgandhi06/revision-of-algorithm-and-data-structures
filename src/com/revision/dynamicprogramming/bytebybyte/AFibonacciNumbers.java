package com.revision.dynamicprogramming.bytebybyte;

public class AFibonacciNumbers {
    public static void main(String[] args) {
        AFibonacciNumbers game = new AFibonacciNumbers();
        int nth = 10;
        game.solve(nth);

        nth = 14;
        System.out.println();
        game.solve(nth);

        nth = 20;
        System.out.println();
        game.solve(nth);

        nth = 50;
        System.out.println();
        System.out.println("Please for sometime as the brute force will take some considerable time");
        game.solve(nth);
    }

    private void solve(int nth) {
        System.out.println("Brute Force: " + solveWithBruteForce(nth));
        Long[] memo = new Long[ nth + 1 ];
        memo[ 0 ] = 0L;
        memo[ 1 ] = 1L;
        System.out.println("Memoization: " + solveWithMemoization(nth, memo));
        Long[] table = new Long[ nth + 1 ];
        table[ 0 ] = 0L;
        table[ 1 ] = 1L;
        System.out.println("Tabulation: " + solveWithTabulation(table));
        System.out.println("Tabulation Optimized: " + solveWithTabulationOptimized(nth));
    }

    private long solveWithBruteForce(long nth) {
        if (nth <= 1) return nth;
        return solveWithBruteForce(nth - 1) + solveWithBruteForce(nth - 2);
    }

    private Long solveWithMemoization(int nth, Long[] memo) {
        if (memo[ nth ] == null) {
            memo[ nth ] = solveWithMemoization(nth - 1, memo) + solveWithMemoization(nth - 2, memo);
        }
        return memo[ nth ];
    }

    private Long solveWithTabulation(Long[] table) {
        for (int i = 1; i < table.length - 1; i++) {
            table[ i + 1 ] = table[ i ] + table[ i - 1 ];
        }
        return table[ table.length - 1 ];
    }

    private long solveWithTabulationOptimized(long nth) {
        long nMinus1 = 1L;
        long nMinus2 = 0L;
        long n = 0L;
        for (int i = 2; i <= nth; i++) {
            int temp = 0;//Avoid Duplicate Detection in intelliJ
            n = nMinus1 + nMinus2;
            nMinus2 = nMinus1;
            nMinus1 = n;
        }
        return n;
    }
}