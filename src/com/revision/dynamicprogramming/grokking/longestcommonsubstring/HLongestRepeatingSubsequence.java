package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class HLongestRepeatingSubsequence {
    public static void main(String[] args) {
        HLongestRepeatingSubsequence game = new HLongestRepeatingSubsequence();
        String s1 = "t o m o r r o w";
        System.out.println("Input String: " + s1);
        System.out.println("Longest Repeating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1));
        System.out.println("Memoization: " + game.solveWithMemoization(s1));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1));

        s1 = "t o m m o r r o w";
        System.out.println("\nInput String: " + s1);
        System.out.println("Longest Repeating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1));
        System.out.println("Memoization: " + game.solveWithMemoization(s1));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1));

        s1 = "t o m o m o r r o w";
        System.out.println("\nInput String: " + s1);
        System.out.println("Longest Repeating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1));
        System.out.println("Memoization: " + game.solveWithMemoization(s1));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1));

        s1 = "a a b d b c e c";
        System.out.println("\nInput String: " + s1);
        System.out.println("Longest Repeating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1));
        System.out.println("Memoization: " + game.solveWithMemoization(s1));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1));

        s1 = "f m f f";
        System.out.println("\nInput String: " + s1);
        System.out.println("Longest Repeating Subsequence");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1));
        System.out.println("Memoization: " + game.solveWithMemoization(s1));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1));
    }

    private int solveWithBruteForce(String s1) {
        return solve(s1.split(" "), 0, 1);
    }

    private int solve(String[] arr, int currenIndexOne, int currenIndexTwo) {
        if (currenIndexOne == arr.length || currenIndexTwo == arr.length) return 0;
        int include = 0;
        if (arr[ currenIndexOne ].equalsIgnoreCase(arr[ currenIndexTwo ]) && currenIndexOne != currenIndexTwo) {
            include = 1 + solve(arr, currenIndexOne + 1, currenIndexTwo + 1);
        }
        int excludeLeft = solve(arr, currenIndexOne + 1, currenIndexTwo);
        int excludeRight = solve(arr, currenIndexOne, currenIndexTwo + 1);
        return Math.max(include, Math.max(excludeLeft, excludeRight));
    }

    private int solveWithMemoization(String s1) {
        String[] arr = s1.split(" ");
        Integer[][] memo = new Integer[ arr.length ][ arr.length ];
        return solve(arr, 0, 1, memo);
    }

    private int solve(String[] arr, int currenIndexOne, int currenIndexTwo, Integer[][] memo) {
        if (currenIndexOne == arr.length || currenIndexTwo == arr.length) return 0;
        if (memo[ currenIndexOne ][ currenIndexTwo ] == null) {
            int include = 0;
            if (arr[ currenIndexOne ].equalsIgnoreCase(arr[ currenIndexTwo ]) && currenIndexOne != currenIndexTwo) {
                include = 1 + solve(arr, currenIndexOne + 1, currenIndexTwo + 1, memo);
            }
            int excludeLeft = solve(arr, currenIndexOne + 1, currenIndexTwo, memo);
            int excludeRight = solve(arr, currenIndexOne, currenIndexTwo + 1, memo);
            memo[ currenIndexOne ][ currenIndexTwo ] = Math.max(include, Math.max(excludeLeft, excludeRight));
        }
        return memo[ currenIndexOne ][ currenIndexTwo ];
    }

    private int solveWithTabulation(String s1) {
        String[] arr = s1.split(" ");
        int[][] table = new int[ arr.length + 1 ][ arr.length + 1 ];
        return solve(arr, table);
    }

    private int solve(String[] arr, int[][] table) {
        int max = 0;
        for (int row = 1; row <= arr.length; row++) {
            for (int col = row + 1; col <= arr.length; col++) {
                if (arr[ row - 1 ].equalsIgnoreCase(arr[ col - 1 ]) && row != col) {
                    table[ row ][ col ] = 1 + table[ row - 1 ][ col - 1 ];
                } else {
                    table[ row ][ col ] = Math.max(table[ row - 1 ][ col ], table[ row ][ col - 1 ]);
                }
                max = Math.max(table[ row ][ col ], max);
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        /*return table[ table.length - 2 ][ table[ 0 ].length - 1 ];*/
        return max;
    }
}