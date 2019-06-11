package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

import java.util.Arrays;

public class GMinimumDeletionsToMakeSequenceSorted {
    public static void main(String[] args) {
        GMinimumDeletionsToMakeSequenceSorted game = new GMinimumDeletionsToMakeSequenceSorted();
        int[] arr = new int[]{4, 2, 3, 6, 10, 1, 12};
        System.out.println("Input Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Deletions Required to make the array sorted");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));


        arr = new int[]{-4, 10, 3, 7, 15};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Deletions Required to make the array sorted");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 10, 3, 7, 15, 1, 23, 32, 4, 5, 8, 9, 10, 36};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Deletions Required to make the array sorted");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{3, 2, 1, 0};
        System.out.println("\nInput Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nMinimum Deletions Required to make the array sorted");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        int maxIncreasingSequence = solve(arr, 0, -1);
        return arr.length - maxIncreasingSequence;
    }

    private int solve(int[] arr, int currentIndex, int previousIndex) {
        if (currentIndex == arr.length) return 0;
        int include = 0;
        if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
            include = 1 + solve(arr, currentIndex + 1, currentIndex);
        }
        int exclude = solve(arr, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        Integer[][] memo = new Integer[ arr.length ][ arr.length ];
        int maxIncreasingSequence = solve(arr, 0, -1, memo);
        return arr.length - maxIncreasingSequence;
    }

    private int solve(int[] arr, int currentIndex, int previousIndex, Integer[][] memo) {
        if (currentIndex == arr.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
                include = 1 + solve(arr, currentIndex + 1, currentIndex, memo);
            }
            int exclude = solve(arr, currentIndex + 1, previousIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private int solveWithTabulation(int[] arr) {
        int[] table = new int[ arr.length ];
        int maxIncreasingSequence = solve(arr, table);
        return arr.length - maxIncreasingSequence;
    }

    private int solve(int[] arr, int[] table) {
        int max = 1;
        table[ 0 ] = 1;
        for (int current = 1; current < table.length; current++) {
            table[ current ] = 1;
            for (int previous = 0; previous < current; previous++) {
                if (arr[ current ] > arr[ previous ] && table[ current ] <= table[ previous ]) {
                    table[ current ] = 1 + table[ previous ];
                    max = Math.max(max, table[ current ]);
                }
            }
            /*Arrays.stream(table).forEach(value -> System.out.print(value + " "));
            System.out.println();*/
        }
        return max;
    }
}