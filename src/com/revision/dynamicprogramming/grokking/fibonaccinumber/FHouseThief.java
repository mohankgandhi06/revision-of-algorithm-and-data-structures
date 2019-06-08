package com.revision.dynamicprogramming.grokking.fibonaccinumber;

import java.util.Arrays;

public class FHouseThief {
    public static void main(String[] args) {
        FHouseThief game = new FHouseThief();
        int[] loots = new int[]{2, 5, 1, 3, 6, 2, 4};
        System.out.println("Array: ");
        Arrays.stream(loots).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Loot Possible: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(loots));
        System.out.println("Memoization: " + game.solveWithMemoization(loots));
        System.out.println("Tabulation: " + game.solveWithTabulation(loots));

        loots = new int[]{2, 10, 14, 8, 1};
        System.out.println("\nArray: ");
        Arrays.stream(loots).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Loot Possible: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(loots));
        System.out.println("Memoization: " + game.solveWithMemoization(loots));
        System.out.println("Tabulation: " + game.solveWithTabulation(loots));

        loots = new int[]{2, 10, 14, 8, 10, 5, 8, 7, 11};
        System.out.println("\nArray: ");
        Arrays.stream(loots).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMaximum Loot Possible: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(loots));
        System.out.println("Memoization: " + game.solveWithMemoization(loots));
        System.out.println("Tabulation: " + game.solveWithTabulation(loots));
    }

    private int solveWithBruteForce(int[] loots) {
        return solve(loots, 0, 0);
    }

    private int solve(int loots[], int currentIndex, int currentLoot) {
        if (currentIndex >= loots.length) return currentLoot;
        int include = solve(loots, currentIndex + 2, currentLoot + loots[ currentIndex ]);
        int exclude = solve(loots, currentIndex + 1, currentLoot);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] loots) {
        Integer[] memo = new Integer[ loots.length + 1 ];
        return solve(loots, 0, 0, memo);
    }

    private int solve(int[] loots, int currentIndex, int currentLoot, Integer[] memo) {
        if (currentIndex >= loots.length) return currentLoot;
        if (memo[ currentIndex ] == null) {
            int include = solve(loots, currentIndex + 2, currentLoot + loots[ currentIndex ]);
            int exclude = solve(loots, currentIndex + 1, currentLoot);
            memo[ currentIndex ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ];
    }

    private int solveWithTabulation(int[] loots) {
        int[] table = new int[ loots.length ];
        table[ 0 ] = loots[ 0 ];
        table[ 1 ] = loots[ 1 ];
        return solve(loots, table);
    }

    private int solve(int[] loots, int[] table) {
        for (int i = 2; i < loots.length; i++) {
            table[ i ] = Math.max(loots[ i ] + (Math.max(i - 2 >= 0 ? table[ i - 2 ] : 0, i - 3 >= 0 ? table[ i - 3 ] : 0)), table[ i - 1 ]);
        }
        return table[ table.length - 1 ];
    }
}