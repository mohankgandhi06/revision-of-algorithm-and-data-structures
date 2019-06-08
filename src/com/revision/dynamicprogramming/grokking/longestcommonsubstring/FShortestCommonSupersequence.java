package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class FShortestCommonSupersequence {
    public static void main(String[] args) {
        FShortestCommonSupersequence game = new FShortestCommonSupersequence();
        String s1 = "abcf";
        String s2 = "bdcf";
        System.out.println("Shortest common super sequence of the two words");
        System.out.print(s1 + " and " + s2 + " is\n");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));

        s1 = "dynamic";
        s2 = "programming";
        System.out.println("\nShortest common super sequence of the two words");
        System.out.print(s1 + " and " + s2 + " is\n");
        System.out.println("Brute Force: " + game.solveWithBruteForce(s1, s2));
        System.out.println("Memoization: " + game.solveWithMemoization(s1, s2));
        System.out.println("Tabulation: " + game.solveWithTabulation(s1, s2));
    }

    private int solveWithBruteForce(String s1, String s2) {
        int common = solve(s1, s2, 0, 0);
        int extra = (s1.length() - common) + (s2.length() - common);
        return common + extra;
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
        int common = solve(s1, s2, 0, 0, memo);
        int extra = (s1.length() - common) + (s2.length() - common);
        return common + extra;
    }

    private int solve(String s1, String s2, int currentIndexS1, int currentIndexS2, Integer[][] memo) {
        if (currentIndexS1 == s1.length() || currentIndexS2 == s2.length()) return 0;
        if (memo[ currentIndexS1 ][ currentIndexS2 ] == null) {
            int include = 0;
            if (s1.charAt(currentIndexS1) == s2.charAt(currentIndexS2)) {
                include = 1 + solve(s1, s2, currentIndexS1 + 1, currentIndexS2 + 1);
            }
            int excludeLeft = solve(s1, s2, currentIndexS1 + 1, currentIndexS2);
            int excludeRight = solve(s1, s2, currentIndexS1, currentIndexS2 + 1);
            memo[ currentIndexS1 ][ currentIndexS2 ] = Math.max(include, Math.max(excludeLeft, excludeRight));
        }
        return memo[ currentIndexS1 ][ currentIndexS2 ];
    }

    private int solveWithTabulation(String s1, String s2) {
        int[][] table = new int[ s1.length() + 1 ][ s2.length() + 1 ];
        int common = solve(s1, s2, table);
        int extra = (s1.length() - common) + (s2.length() - common);
        return common + extra;
    }

    private int solve(String s1, String s2, int[][] table) {
        for (int row = 1; row <= s1.length(); row++) {
            for (int col = 1; col <= s2.length(); col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    table[ row ][ col ] = 1 + table[ row - 1 ][ col - 1 ];
                } else {
                    table[ row ][ col ] = Math.max(table[ row - 1 ][ col ], table[ row ][ col - 1 ]);
                }
            }
        }
        /*for (int row = 1; row <= s1.length(); row++) {
            for (int col = 1; col <= s2.length(); col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}