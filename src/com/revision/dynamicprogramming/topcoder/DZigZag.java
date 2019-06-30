package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;

public class DZigZag {
    public static void main(String[] args) {
        DZigZag game = new DZigZag();
        int[] arr = new int[]{1, 7, 4, 9, 2, 5};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);

        arr = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);

        arr = new int[]{44};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);

        arr = new int[]{70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);

        arr = new int[]{374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("");
        game.solve(arr);
    }

    private void solve(int[] arr) {
        if (arr.length <= 20) {
            System.out.println("Brute Force: " + Math.max(solveWithBruteForce(arr, 0, -1, false), solveWithBruteForce(arr, 0, -1, true)));
        }
        Integer[][][] memo = new Integer[ arr.length ][ arr.length ][ 2 ];
        System.out.println("Memoization: " + Math.max(solveWithMemoization(arr, 0, -1, memo, false), solveWithMemoization(arr, 0, -1, memo, true)));
        int[][] table = new int[ 2 ][ arr.length ];
        table[ 0 ][ 0 ] = 1;
        table[ 1 ][ 0 ] = 1;
        System.out.println("Tabulation: " + solveWithTabulation(arr, table));
    }

    private int solveWithBruteForce(int[] arr, int currentIndex, int previousIndex, boolean isIncreasing) {
        if (currentIndex == arr.length) return 0;
        int include = 0;
        if (previousIndex == -1) {
            include = 1 + solveWithBruteForce(arr, currentIndex + 1, currentIndex, isIncreasing);
        } else if (isIncreasing) {
            if (arr[ currentIndex ] > arr[ previousIndex ]) {
                include = 1 + solveWithBruteForce(arr, currentIndex + 1, currentIndex, !isIncreasing);
            }
        } else {
            if (arr[ currentIndex ] < arr[ previousIndex ]) {
                include = 1 + solveWithBruteForce(arr, currentIndex + 1, currentIndex, !isIncreasing);
            }
        }
        int exclude = solveWithBruteForce(arr, currentIndex + 1, previousIndex, isIncreasing);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] arr, int currentIndex, int previousIndex, Integer[][][] memo, boolean isIncreasing) {
        if (currentIndex == arr.length) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ] == null) {
            int include = 0;
            if (previousIndex == -1) {
                include = 1 + solveWithMemoization(arr, currentIndex + 1, currentIndex, memo, isIncreasing);
            } else if (isIncreasing) {
                if (arr[ currentIndex ] > arr[ previousIndex ]) {
                    include = 1 + solveWithMemoization(arr, currentIndex + 1, currentIndex, memo, !isIncreasing);
                }
            } else {
                if (arr[ currentIndex ] < arr[ previousIndex ]) {
                    include = 1 + solveWithMemoization(arr, currentIndex + 1, currentIndex, memo, !isIncreasing);
                }
            }
            int exclude = solveWithMemoization(arr, currentIndex + 1, previousIndex, memo, isIncreasing);
            memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ][ isIncreasing ? 0 : 1 ];
    }

    private int solveWithTabulation(int[] arr, int[][] table) {
        if (arr.length < 2) return arr.length;
        int max = 0;
        for (int index = 0; index < table[ 0 ].length; index++) {
            table[ 0 ][ index ] = table[ 1 ][ index ] = 1;
            for (int previousIndex = 0; previousIndex < index; previousIndex++) {
                if (arr[ index ] > arr[ previousIndex ]) {
                    table[ 0 ][ index ] = Math.max(table[ 0 ][ index ], 1 + table[ 1 ][ previousIndex ]);
                    max = Math.max(table[ 0 ][ index ], max);
                } else if (arr[ index ] < arr[ previousIndex ]) {
                    table[ 1 ][ index ] = Math.max(table[ 1 ][ index ], 1 + table[ 0 ][ previousIndex ]);
                    max = Math.max(table[ 1 ][ index ], max);
                }
            }
        }
        return max;
    }
}
