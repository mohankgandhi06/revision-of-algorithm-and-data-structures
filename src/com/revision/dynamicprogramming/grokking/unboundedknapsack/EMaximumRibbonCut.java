package com.revision.dynamicprogramming.grokking.unboundedknapsack;

import java.util.Arrays;

public class EMaximumRibbonCut {
    public static void main(String[] args) {
        EMaximumRibbonCut game = new EMaximumRibbonCut();
        int totalLength = 5;
        int[] ribbonLengths = new int[]{2, 3, 5};
        System.out.println("Array: ");
        Arrays.stream(ribbonLengths).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTotal Length: "+totalLength);
        System.out.println("Brute Force: " + game.solveWithBruteForce(ribbonLengths, totalLength));
        System.out.println("Memoization: " + game.solveWithMemoization(ribbonLengths, totalLength));
        System.out.println("Tabulation: " + game.solveWithTabulation(ribbonLengths, totalLength));

        totalLength = 7;
        ribbonLengths = new int[]{2, 3};
        System.out.println("\nArray: ");
        Arrays.stream(ribbonLengths).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTotal Length: "+totalLength);
        System.out.println("Brute Force: " + game.solveWithBruteForce(ribbonLengths, totalLength));
        System.out.println("Memoization: " + game.solveWithMemoization(ribbonLengths, totalLength));
        System.out.println("Tabulation: " + game.solveWithTabulation(ribbonLengths, totalLength));

        totalLength = 13;
        ribbonLengths = new int[]{3, 5, 7, 2, 2, 7};
        System.out.println("\nArray: ");
        Arrays.stream(ribbonLengths).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTotal Length: "+totalLength);
        System.out.println("Brute Force: " + game.solveWithBruteForce(ribbonLengths, totalLength));
        System.out.println("Memoization: " + game.solveWithMemoization(ribbonLengths, totalLength));
        System.out.println("Tabulation: " + game.solveWithTabulation(ribbonLengths, totalLength));

        totalLength = 30;
        ribbonLengths = new int[]{6, 5, 7, 3, 8, 7};
        System.out.println("\nArray: ");
        Arrays.stream(ribbonLengths).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTotal Length: "+totalLength);
        System.out.println("Brute Force: " + game.solveWithBruteForce(ribbonLengths, totalLength));
        System.out.println("Memoization: " + game.solveWithMemoization(ribbonLengths, totalLength));
        System.out.println("Tabulation: " + game.solveWithTabulation(ribbonLengths, totalLength));
    }

    private int solveWithBruteForce(int[] ribbonLengths, int totalLength) {
        return solve(ribbonLengths, totalLength, 0, 0);
    }

    private int solve(int[] ribbonLengths, int totalLength, int currentIndex, int count) {
        if (totalLength == 0) return count;
        if (currentIndex == ribbonLengths.length) return 0;
        int include = 0;
        if (totalLength >= ribbonLengths[ currentIndex ]) {
            include = solve(ribbonLengths, totalLength - ribbonLengths[ currentIndex ], currentIndex, count + 1);
        }
        int exclude = solve(ribbonLengths, totalLength, currentIndex + 1, count);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] ribbonLengths, int totalLength) {
        Integer[][] memo = new Integer[ ribbonLengths.length ][ totalLength + 1 ];
        return solve(ribbonLengths, totalLength, 0, 0, memo);
    }

    private int solve(int[] ribbonLengths, int totalLength, int currentIndex, int count, Integer[][] memo) {
        if (totalLength == 0) return count;
        if (currentIndex == ribbonLengths.length) return 0;
        if (memo[ currentIndex ][ totalLength ] == null) {
            int include = 0;
            if (totalLength >= ribbonLengths[ currentIndex ]) {
                include = solve(ribbonLengths, totalLength - ribbonLengths[ currentIndex ], currentIndex, count + 1);
            }
            int exclude = solve(ribbonLengths, totalLength, currentIndex + 1, count);
            memo[ currentIndex ][ totalLength ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ totalLength ];
    }

    private int solveWithTabulation(int[] ribbonLengths, int totalLength) {
        int[][] table = new int[ ribbonLengths.length + 1 ][ totalLength + 1 ];
        return solve(ribbonLengths, table);
    }

    private int solve(int[] ribbonLengths, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                int include = 0;
                int exclude = table[ row - 1 ][ col ];
                if (ribbonLengths[ row - 1 ] <= col) {
                    if (ribbonLengths[ row - 1 ] - col == 0) {
                        include = 1;
                    }
                    include += table[ row ][ col - ribbonLengths[ row - 1 ] ] != 0 ? (1 + table[ row ][ col - ribbonLengths[ row - 1 ] ]) : 0;
                }
                table[ row ][ col ] = Math.max(include, exclude);
            }
        }
        /*System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}