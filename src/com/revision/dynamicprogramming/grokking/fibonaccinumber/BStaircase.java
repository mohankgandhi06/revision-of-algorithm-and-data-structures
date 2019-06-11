package com.revision.dynamicprogramming.grokking.fibonaccinumber;

public class BStaircase {
    public static void main(String[] args) {
        BStaircase game = new BStaircase();
        int numberOfStairs = 3;
        System.out.println("Number of stairs: " + numberOfStairs + "\nAvailable ways:");
        System.out.println("Brute Force: " + game.solveWithBruteForce(numberOfStairs));
        System.out.println("Memoization: " + game.solveWithMemoization(numberOfStairs));
        System.out.println("Tabulation: " + game.solveWithTabulation(numberOfStairs));

        numberOfStairs = 7;
        System.out.println("\nNumber of stairs: " + numberOfStairs + "\nAvailable ways:");
        System.out.println("Brute Force: " + game.solveWithBruteForce(numberOfStairs));
        System.out.println("Memoization: " + game.solveWithMemoization(numberOfStairs));
        System.out.println("Tabulation: " + game.solveWithTabulation(numberOfStairs));

        numberOfStairs = 9;
        System.out.println("\nNumber of stairs: " + numberOfStairs + "\nAvailable ways:");
        System.out.println("Brute Force: " + game.solveWithBruteForce(numberOfStairs));
        System.out.println("Memoization: " + game.solveWithMemoization(numberOfStairs));
        System.out.println("Tabulation: " + game.solveWithTabulation(numberOfStairs));

        numberOfStairs = 13;
        System.out.println("\nNumber of stairs: " + numberOfStairs + "\nAvailable ways:");
        System.out.println("Brute Force: " + game.solveWithBruteForce(numberOfStairs));
        System.out.println("Memoization: " + game.solveWithMemoization(numberOfStairs));
        System.out.println("Tabulation: " + game.solveWithTabulation(numberOfStairs));
    }

    private int solveWithBruteForce(int numberOfStairs) {
        return solve(numberOfStairs);
    }

    private int solve(int numberOfStairs) {
        if (numberOfStairs == 0) return 1;
        if (numberOfStairs == 1) return 1;
        if (numberOfStairs == 2) return 2;
        return solve(numberOfStairs - 1) + solve(numberOfStairs - 2) + solve(numberOfStairs - 3);
    }

    private int solveWithMemoization(int numberOfStairs) {
        Integer[] memo = new Integer[ numberOfStairs + 1 ];
        memo[ 0 ] = 1;
        memo[ 1 ] = 1;
        memo[ 2 ] = 2;
        return solve(numberOfStairs, memo);
    }

    private int solve(int numberOfStairs, Integer[] memo) {
        if (memo[ numberOfStairs ] == null) {
            memo[ numberOfStairs ] = solve(numberOfStairs - 1, memo) + solve(numberOfStairs - 2, memo) + solve(numberOfStairs - 3, memo);
        }
        return memo[ numberOfStairs ];
    }

    private int solveWithTabulation(int numberOfStairs) {
        int[] table = new int[ numberOfStairs + 1 ];
        table[ 0 ] = 1;
        table[ 1 ] = 1;
        table[ 2 ] = 2;
        return solve(numberOfStairs, table);
    }

    private int solve(int numberOfStairs, int[] table) {
        for (int i = 3; i <= numberOfStairs; i++) {
            table[ i ] = table[ i - 1 ] + table[ i - 2 ] + table[ i - 3 ];
        }
        return table[ numberOfStairs ];
    }
}