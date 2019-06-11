package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class LEditDistance {
    public static void main(String[] args) {
        LEditDistance game = new LEditDistance();
        String s1 = "bat";
        String s2 = "but";
        System.out.println("Edit Distance");
        System.out.println("S1: " + s1 + " S2: " + s2);
        int[] result = game.solveWithBruteForce(s1, s2);
        int total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Brute Force: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithMemoization(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Memoization: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithTabulation(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Tabulation: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");

        s1 = "abdca";
        s2 = "cbda";
        System.out.println("\nEdit Distance");
        System.out.println("S1: " + s1 + " S2: " + s2);
        result = game.solveWithBruteForce(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Brute Force: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithMemoization(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Memoization: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithTabulation(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Tabulation: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");

        s1 = "passpot";
        s2 = "ppsspqrt";
        System.out.println("\nEdit Distance");
        System.out.println("S1: " + s1 + " S2: " + s2);
        result = game.solveWithBruteForce(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Brute Force: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithMemoization(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Memoization: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
        result = game.solveWithTabulation(s1, s2);
        total = 0;
        for (int item : result) {
            total += item;
        }
        System.out.println("Tabulation: Total Edits Remaining: " + total + " ***** Split Up: INSERT (" + result[ 0 ] + ") DELETE (" + result[ 1 ] + ") REPLACE (" + result[ 2 ] + ")");
    }

    private int[] solveWithBruteForce(String s1, String s2) {
        int[] insertDeleteReplace = new int[ 3 ];
        int common = solve(s1, s2, 0, 0);
        if (s1.length() > s2.length()) {//We would need to delete extra characters from s1
            insertDeleteReplace[ 1 ] = (s1.length() - common) - (s2.length() - common);
            insertDeleteReplace[ 2 ] = s1.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else if (s1.length() < s2.length()) {
            insertDeleteReplace[ 0 ] = (s2.length() - common) - (s1.length() - common);
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else {
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        }
        return insertDeleteReplace;
    }

    private int solve(String s1, String s2, int s1Index, int s2Index) {
        if (s1Index == s1.length() || s2Index == s2.length()) return 0;
        int include = 0;
        if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
            include = 1 + solve(s1, s2, s1Index + 1, s2Index + 1);
        }
        int excludeLeft = solve(s1, s2, s1Index + 1, s2Index);
        int excludeRight = solve(s1, s2, s1Index, s2Index + 1);
        return Math.max(include, Math.max(excludeLeft, excludeRight));
    }

    private int[] solveWithMemoization(String s1, String s2) {
        Integer[][] memo = new Integer[ s1.length() ][ s2.length() ];
        int[] insertDeleteReplace = new int[ 3 ];
        int common = solve(s1, s2, 0, 0, memo);
        if (s1.length() > s2.length()) {//We would need to delete extra characters from s1
            insertDeleteReplace[ 1 ] = (s1.length() - common) - (s2.length() - common);
            insertDeleteReplace[ 2 ] = s1.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else if (s1.length() < s2.length()) {
            insertDeleteReplace[ 0 ] = (s2.length() - common) - (s1.length() - common);
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else {
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        }
        return insertDeleteReplace;
    }

    private int solve(String s1, String s2, int s1Index, int s2Index, Integer[][] memo) {
        if (s1Index == s1.length() || s2Index == s2.length()) return 0;
        if (memo[ s1Index ][ s2Index ] == null) {
            int include = 0;
            if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
                include = 1 + solve(s1, s2, s1Index + 1, s2Index + 1, memo);
            }
            int excludeLeft = solve(s1, s2, s1Index + 1, s2Index, memo);
            int excludeRight = solve(s1, s2, s1Index, s2Index + 1, memo);
            memo[ s1Index ][ s2Index ] = Math.max(include, Math.max(excludeLeft, excludeRight));
        }
        return memo[ s1Index ][ s2Index ];
    }

    private int[] solveWithTabulation(String s1, String s2) {
        int[][] table = new int[ s1.length() + 1 ][ s2.length() + 1 ];
        int[] insertDeleteReplace = new int[ 3 ];
        int common = solve(s1, s2, table);
        if (s1.length() > s2.length()) {//We would need to delete extra characters from s1
            insertDeleteReplace[ 1 ] = (s1.length() - common) - (s2.length() - common);
            insertDeleteReplace[ 2 ] = s1.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else if (s1.length() < s2.length()) {
            insertDeleteReplace[ 0 ] = (s2.length() - common) - (s1.length() - common);
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        } else {
            insertDeleteReplace[ 2 ] = s2.length() - common - insertDeleteReplace[ 0 ] - insertDeleteReplace[ 1 ];
        }
        return insertDeleteReplace;
    }

    private int solve(String s1, String s2, int[][] table) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    table[ row ][ col ] = 1 + table[ row - 1 ][ col - 1 ];
                }
                int excludeLeft = table[ row - 1 ][ col ];
                int excludeRight = table[ row ][ col - 1 ];
                table[ row ][ col ] = Math.max(table[ row ][ col ], Math.max(excludeLeft, excludeRight));
            }
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}