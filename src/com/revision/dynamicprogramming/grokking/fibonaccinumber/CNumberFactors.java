package com.revision.dynamicprogramming.grokking.fibonaccinumber;

public class CNumberFactors {
    public static void main(String[] args) {
        CNumberFactors game = new CNumberFactors();
        int number = 4;
        System.out.println("Target: " + number);
        System.out.println("No of ways to represent it as a 1 or 3 or 4");
        System.out.println("Brute Force: " + game.solveWithBruteForce(number));
        System.out.println("Memoization: " + game.solveWithMemoization(number));
        System.out.println("Tabulation: " + game.solveWithTabulation(number));

        number = 5;
        System.out.println("\nTarget: " + number);
        System.out.println("No of ways to represent it as a 1 or 3 or 4");
        System.out.println("Brute Force: " + game.solveWithBruteForce(number));
        System.out.println("Memoization: " + game.solveWithMemoization(number));
        System.out.println("Tabulation: " + game.solveWithTabulation(number));

        number = 8;
        System.out.println("\nTarget: " + number);
        System.out.println("No of ways to represent it as a 1 or 3 or 4");
        System.out.println("Brute Force: " + game.solveWithBruteForce(number));
        System.out.println("Memoization: " + game.solveWithMemoization(number));
        System.out.println("Tabulation: " + game.solveWithTabulation(number));
    }

    private int solveWithBruteForce(int number) {
        return solve(number);
    }

    private int solve(int number) {
        if (number < 0) return 0;
        if (number == 0) return 1;
        if (number == 1) return 1;
        if (number == 3) return 2;
        return solve(number - 1) + solve(number - 3) + solve(number - 4);
    }

    private int solveWithMemoization(int number) {
        Integer[] memo = new Integer[ number + 1 ];
        memo[ 0 ] = 1;
        memo[ 1 ] = 1;
        memo[ 2 ] = 1;
        memo[ 3 ] = 2;
        return solve(number, memo);
    }

    private int solve(int number, Integer[] memo) {
        if (memo[ number ] == null) {
            memo[ number ] = solve(number - 1) + solve(number - 3) + solve(number - 4);
        }
        return memo[ number ];
    }

    private int solveWithTabulation(int number) {
        int[] table = new int[ number + 1 ];
        table[ 0 ] = 1;
        table[ 1 ] = 1;
        table[ 2 ] = 1;
        table[ 3 ] = 2;
        return solve(number, table);
    }

    private int solve(int number, int[] table) {
        for (int i = 4; i <= number; i++) {
            table[ i ] = table[ i - 1 ] + table[ i - 3 ] + table[ i - 4 ];
        }
        return table[ number ];
    }
}