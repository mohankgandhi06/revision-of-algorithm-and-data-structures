package com.revision.dynamicprogramming.grokking.fibonaccinumber;

public class AIntro {
    public static void main(String[] args) {
        AIntro game = new AIntro();
        int nth = 25;
        System.out.println(nth + "' Fibonacci number :");
        System.out.println("Brute Force: " + game.solveWithBruteForce(nth));
        System.out.println("Memoization: " + game.solveWithMemoization(nth));
        System.out.println("Tabulation: " + game.solveWithTabulation(nth));
    }

    private int solveWithBruteForce(int nth) {
        if (nth == 0 || nth == 1) return nth;
        return solveWithBruteForce(nth - 1) + solveWithBruteForce(nth - 2);
    }

    private int solveWithMemoization(int nth) {
        Integer[] memo = new Integer[ nth + 1 ];
        memo[ 0 ] = 0;
        memo[ 1 ] = 1;
        return solve(nth, memo);
    }

    private int solve(int nth, Integer[] memo) {
        if (memo[ nth ] == null) {
            memo[ nth ] = solve(nth - 1, memo) + solve(nth - 2, memo);
        }
        return memo[ nth ];
    }

    private int solveWithTabulation(int nth) {
        int iMinusOne = 1;
        int iMinusTwo = 0;
        int fibonacci = 0;
        for (int i = 2; i <= nth; i++) {
            fibonacci = iMinusOne + iMinusTwo;
            iMinusTwo = iMinusOne;
            iMinusOne = fibonacci;
        }
        return fibonacci;
    }
}