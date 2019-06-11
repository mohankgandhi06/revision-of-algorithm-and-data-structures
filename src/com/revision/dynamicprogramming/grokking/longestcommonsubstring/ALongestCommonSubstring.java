package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class ALongestCommonSubstring {
    public static void main(String[] args) {
        ALongestCommonSubstring game = new ALongestCommonSubstring();
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
        return solve(s1, s2, 0, 0, 0);
    }

    private int solve(String s1, String s2, int currentIndexS1, int currentIndexS2, int count) {
        if (currentIndexS1 == s1.length() || currentIndexS2 == s2.length()) return count;
        if (s1.charAt(currentIndexS1) == s2.charAt(currentIndexS2)) {
            count = solve(s1, s2, currentIndexS1 + 1, currentIndexS2 + 1, count + 1);
        }
        int excludeLeft = solve(s1, s2, currentIndexS1 + 1, currentIndexS2, 0);
        int excludeRight = solve(s1, s2, currentIndexS1, currentIndexS2 + 1, 0);
        return Math.max(count, Math.max(excludeLeft, excludeRight));
    }

    private int solveWithMemoization(String s1, String s2) {
        Integer[][][] memo = new Integer[ s1.length() ][ s2.length() ][ s1.length() > s2.length() ? s1.length() : s2.length() ];
        return solve(s1, s2, 0, 0, 0, memo);
    }

    private int solve(String s1, String s2, int currentIndexS1, int currentIndexS2, int count, Integer[][][] memo) {
        if (currentIndexS1 == s1.length() || currentIndexS2 == s2.length()) return count;
        if (memo[ currentIndexS1 ][ currentIndexS2 ][ count ] == null) {
            int include = count;
            if (s1.charAt(currentIndexS1) == s2.charAt(currentIndexS2)) {
                include = solve(s1, s2, currentIndexS1 + 1, currentIndexS2 + 1, count + 1, memo);
            }
            int excludeLeft = solve(s1, s2, currentIndexS1 + 1, currentIndexS2, 0, memo);
            int excludeRight = solve(s1, s2, currentIndexS1, currentIndexS2 + 1, 0, memo);
            memo[ currentIndexS1 ][ currentIndexS2 ][ count ] = Math.max(include, Math.max(excludeLeft, excludeRight));
        }
        return memo[ currentIndexS1 ][ currentIndexS2 ][ count ];
    }

    private int solveWithTabulation(String s1, String s2) {
        int[][] table = new int[ s1.length() + 1 ][ s2.length() + 1 ];
        return solve(s1, s2, table);
    }

    private int solve(String s1, String s2, int[][] table) {
        int count = 0;
        for (int row = 0; row < s1.length(); row++) {
            for (int col = 0; col < s2.length(); col++) {
                if (s1.charAt(row) == s2.charAt(col)) {
                    int current = 1 + (row >= 1 && col >= 1 ? table[ row - 1 ][ col - 1 ] : 0);
                    table[ row ][ col ] = current;
                    count = Math.max(count, current);
                }
            }
        }
        /*System.out.println("Table: ");
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return count;
    }
}