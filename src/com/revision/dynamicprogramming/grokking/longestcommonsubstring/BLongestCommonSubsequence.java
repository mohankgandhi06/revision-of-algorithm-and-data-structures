package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class BLongestCommonSubsequence {
    public static void main(String[] args) {
        BLongestCommonSubsequence game = new BLongestCommonSubsequence();
        String s1 = "abdca";
        String s2 = "cbda";
        System.out.println("Longest Common Substring");
        System.out.println("S1: " + s1 + " S2: " + s2);
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));

        s1 = "passport";
        s2 = "ppsspt";
        System.out.println("\nLongest Common Substring");
        System.out.println("S1: " + s1 + " S2: " + s2);
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));

        s1 = "passport";
        s2 = "passpt";
        System.out.println("\nLongest Common Substring");
        System.out.println("S1: " + s1 + " S2: " + s2);
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));

        s1 = "passport";
        s2 = "pasrsport";
        System.out.println("\nLongest Common Substring");
        System.out.println("S1: " + s1 + " S2: " + s2);
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));

        s1 = "passport";
        s2 = "pasrspiot";
        System.out.println("\nLongest Common Substring");
        System.out.println("S1: " + s1 + " S2: " + s2);
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));
    }

    private int solveWithBruteForce(String s1, String s2) {
        return solve(s1, s2, 0, 0);
    }

    private int solve(String s1, String s2, int currentIndexS1, int currentIndexS2) {
        if (currentIndexS1 == s1.length() || currentIndexS2 == s2.length()) return 0;
        int include = 0;
        if (s1.charAt(currentIndexS1) == s2.charAt(currentIndexS2)) {
            include = 1 + solve(s1, s2, currentIndexS1 + 1, currentIndexS2 + 1);
        }
        int excludeLeft = solve(s1, s2, currentIndexS1 + 1, currentIndexS2);
        int excludeRight = solve(s1, s2, currentIndexS1, currentIndexS2 + 1);
        return Math.max(include, Math.max(excludeLeft, excludeRight));
    }

    private int solveWithMemoization(String s1, String s2) {
        Integer[][] memo = new Integer[ s1.length() ][ s2.length() ];
        return solve(s1, s2, 0, 0, memo);
    }

    private int solve(String s1, String s2, int currentIndexS1, int currentIndexS2, Integer[][] memo) {
        if (currentIndexS1 == s1.length() || currentIndexS2 == s2.length()) return 0;
        if (memo[ currentIndexS1 ][ currentIndexS2 ] == null) {
            int include = 0;
            if (s1.charAt(currentIndexS1) == s2.charAt(currentIndexS2)) {
                include = 1 + solve(s1, s2, currentIndexS1 + 1, currentIndexS2 + 1, memo);
            }
            int excludeLeft = solve(s1, s2, currentIndexS1 + 1, currentIndexS2, memo);
            int excludeRight = solve(s1, s2, currentIndexS1, currentIndexS2 + 1, memo);
            memo[ currentIndexS1 ][ currentIndexS2 ] = Math.max(include, Math.max(excludeLeft, excludeRight));
        }
        return memo[ currentIndexS1 ][ currentIndexS2 ];
    }

    private int solveWithTabulation(String s1, String s2) {
        int[][] table = new int[ s1.length() + 1 ][ s2.length() + 1 ];
        return solve(s1, s2, table);
    }

    private int solve(String s1, String s2, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    table[ row ][ col ] = 1 + table[ row - 1 ][ col - 1 ];
                } else {
                    table[ row ][ col ] = Math.max(table[ row ][ col - 1 ], table[ row - 1 ][ col ]);
                }
            }
        }
        System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}