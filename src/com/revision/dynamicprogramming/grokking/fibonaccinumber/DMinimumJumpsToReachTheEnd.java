package com.revision.dynamicprogramming.grokking.fibonaccinumber;

import java.util.Arrays;

public class DMinimumJumpsToReachTheEnd {
    public static void main(String[] args) {
        DMinimumJumpsToReachTheEnd game = new DMinimumJumpsToReachTheEnd();
        int[] arr = new int[]{2, 1, 1, 1, 4};
        System.out.println("Array:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 1, 2, 0, 3, 0, 0, 0};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
        arr = new int[]{1, 1, 1, 4, 0, 3, 0, 0, 0};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 1, 1, 0, 3, 0, 0, 0};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 1, 2, 0, 2, 0, 0, 0};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{2, 1, 6, 0, 3, 0, 0, 0};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{1, 1, 2, 3, 9, 3, 0, 1, 3};
        System.out.println("\nArray:");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Jumps required to reach the end: ");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        return solve(arr, 0, 0);
    }

    private int solve(int[] arr, int currentIndex, int count) {
        if (currentIndex == arr.length - 1) return count;
        if (arr[ currentIndex ] == 0) return Integer.MAX_VALUE;
        int include = Integer.MAX_VALUE;
        for (int i = 1; i <= arr[ currentIndex ]; i++) {
            if (currentIndex + i <= arr.length - 1) {
                int temp = include;
                include = solve(arr, currentIndex + i, count + 1);
                include = Math.min(temp, include);
            }
        }
        int exclude = solve(arr, currentIndex + 1, count + 1);
        return Math.min(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        Integer[] memo = new Integer[ arr.length + 1 ];
        return solve(arr, 0, 0, memo);
    }

    private int solve(int[] arr, int currentIndex, int count, Integer[] memo) {
        if (currentIndex == arr.length - 1) return count;
        if (arr[ currentIndex ] == 0) return Integer.MAX_VALUE;
        if (memo[ currentIndex ] == null) {
            int include = Integer.MAX_VALUE;
            for (int i = 1; i <= arr[ currentIndex ]; i++) {
                if (currentIndex + i <= arr.length - 1) {
                    int z = 0;
                    int temp = include;
                    include = solve(arr, currentIndex + i, count + 1);
                    include = Math.min(temp, include);
                }
            }
            int exclude = solve(arr, currentIndex + 1, count + 1, memo);
            memo[ currentIndex ] = Math.min(include, exclude);
        }
        return memo[ currentIndex ];
    }

    private int solveWithTabulation(int[] arr) {
        int[] table = new int[ arr.length ];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[ 0 ] = 0;
        return solve(arr, table);
    }

    private int solve(int[] arr, int[] table) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j <= i + arr[ i ] && j < arr.length; j++) {
                table[ j ] = Math.min(table[ j ], table[ i ] + 1);
            }
        }
        return table[ arr.length - 1 ];
    }
}