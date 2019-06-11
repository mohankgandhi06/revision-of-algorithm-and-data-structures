package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

import java.util.Arrays;

public class JLongestBitonicSubsequence {
    public static void main(String[] args) {
        JLongestBitonicSubsequence game = new JLongestBitonicSubsequence();
        int[] arr = new int[]{4, 2, 3, 6, 10, 1, 12};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Bitonic Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{4, 2, 5, 9, 7, 6, 10, 3, 1};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Bitonic Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{4, 5, 9, 6, 10, 7, 16, 10, 3, 2};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Bitonic Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));


        arr = new int[]{1, 3, 4, 5, 6, 7, 8, 9};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Bitonic Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));

        arr = new int[]{-4, 10, 3, 7, 15, 1, 23, 32, 4, 5, 8, 9, 10, 36};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nLongest Bitonic Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(arr));
        System.out.println("Memoization: " + game.solveWithMemoization(arr));
        System.out.println("Tabulation: " + game.solveWithTabulation(arr));
    }

    private int solveWithBruteForce(int[] arr) {
        return solve(arr);
    }

    private int solve(int[] arr) {
        int maxLength = 0;
        for (int index = 0; index < arr.length; index++) {
            int left = longestIncreasingSequence(arr, 0, -1, index);
            int right = longestIncreasingSequenceReverse(arr, arr.length - 1, arr.length, index);
            maxLength = Math.max(maxLength, left + right - 1);
        }
        return maxLength;
    }

    private int longestIncreasingSequence(int[] arr, int currentIndex, int previousIndex, int endIndex) {
        if (currentIndex > endIndex) return 0;
        int include = 0;
        if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
            include = 1 + longestIncreasingSequence(arr, currentIndex + 1, currentIndex, endIndex);
        }
        int exclude = longestIncreasingSequence(arr, currentIndex + 1, previousIndex, endIndex);
        return Math.max(include, exclude);
    }

    private int longestIncreasingSequenceReverse(int[] arr, int currentIndex, int previousIndex, int endIndex) {
        if (currentIndex < endIndex) return 0;
        int include = 0;
        if (previousIndex == arr.length || arr[ currentIndex ] > arr[ previousIndex ]) {
            include = 1 + longestIncreasingSequenceReverse(arr, currentIndex - 1, currentIndex, endIndex);
        }
        int exclude = longestIncreasingSequenceReverse(arr, currentIndex - 1, previousIndex, endIndex);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] arr) {
        return solveMemo(arr);
    }

    private int solveMemo(int[] arr) {
        int maxLength = 0;
        for (int index = 0; index < arr.length; index++) {
            Integer[][] leftMemo = new Integer[ arr.length ][ arr.length + 1 ];
            Integer[][] rightMemo = new Integer[ arr.length ][ arr.length + 1 ];
            int left = longestIncreasingSequence(arr, 0, -1, index, leftMemo);
            int right = longestIncreasingSequenceReverse(arr, arr.length - 1, arr.length, index, rightMemo);
            /*System.out.println("Left: " + left + " Right: " + right);*/
            maxLength = Math.max(maxLength, left + right - 1);
        }
        return maxLength;
    }

    private int longestIncreasingSequence(int[] arr, int currentIndex, int previousIndex, int endIndex, Integer[][] memo) {
        if (currentIndex > endIndex) return 0;
        if (memo[ currentIndex ][ previousIndex + 1 ] == null) {
            int include = 0;
            if (previousIndex == -1 || arr[ currentIndex ] > arr[ previousIndex ]) {
                include = 1 + longestIncreasingSequence(arr, currentIndex + 1, currentIndex, endIndex, memo);
            }
            int exclude = longestIncreasingSequence(arr, currentIndex + 1, previousIndex, endIndex, memo);
            memo[ currentIndex ][ previousIndex + 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex + 1 ];
    }

    private int longestIncreasingSequenceReverse(int[] arr, int currentIndex, int previousIndex, int endIndex, Integer[][] memo) {
        if (currentIndex < endIndex) return 0;
        if (memo[ currentIndex ][ previousIndex - 1 ] == null) {
            int include = 0;
            if (previousIndex == arr.length || arr[ currentIndex ] > arr[ previousIndex ]) {
                include = 1 + longestIncreasingSequenceReverse(arr, currentIndex - 1, currentIndex, endIndex, memo);
            }
            int exclude = longestIncreasingSequenceReverse(arr, currentIndex - 1, previousIndex, endIndex, memo);
            memo[ currentIndex ][ previousIndex - 1 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ previousIndex - 1 ];
    }

    private int solveWithTabulation(int[] arr) {
        int[] longestIncreasingSequence = new int[ arr.length ];
        int[] longestDecreasingSequence = new int[ arr.length ];
        Arrays.fill(longestIncreasingSequence, 1);
        Arrays.fill(longestDecreasingSequence, 1);
        int maxLength = 1;
        for (int current = 1; current < longestIncreasingSequence.length; current++) {
            for (int previous = 0; previous < current; previous++) {
                if (arr[ current ] > arr[ previous ] && longestIncreasingSequence[ current ] <= longestIncreasingSequence[ previous ]) {
                    longestIncreasingSequence[ current ] = longestIncreasingSequence[ previous ] + 1;
                    maxLength = Math.max(maxLength, longestIncreasingSequence[ current ]);
                }
            }
        }
        maxLength = 1;
        for (int current = longestDecreasingSequence.length - 2; current >= 0; current--) {
            for (int previous = longestDecreasingSequence.length - 1; previous > current; previous--) {
                if (arr[ current ] > arr[ previous ] && longestDecreasingSequence[ current ] <= longestDecreasingSequence[ previous ]) {
                    longestDecreasingSequence[ current ] = longestDecreasingSequence[ previous ] + 1;
                    maxLength = Math.max(maxLength, longestDecreasingSequence[ current ]);
                }
            }
        }
        int bitonicMax = 1;
        for (int i = 0; i < arr.length; i++) {
            bitonicMax = Math.max(bitonicMax, (longestIncreasingSequence[ i ] + longestDecreasingSequence[ i ] - 1));
        }
        return bitonicMax;
    }
}