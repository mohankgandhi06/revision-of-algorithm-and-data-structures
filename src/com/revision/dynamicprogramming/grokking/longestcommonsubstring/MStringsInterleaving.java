package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class MStringsInterleaving {
    public static void main(String[] args) {
        MStringsInterleaving game = new MStringsInterleaving();
        String m = "abd";
        String n = "cef";
        String p = "abcdef";
        System.out.println("Strings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));

        m = "abd";
        n = "cef";
        p = "adcbef";
        System.out.println("\nStrings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));

        m = "abc";
        n = "def";
        p = "abdccf";
        System.out.println("\nStrings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));

        m = "abcdef";
        n = "mnop";
        p = "mnaobcdepf";
        System.out.println("\nStrings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));

        m = "abc";
        n = "def";
        p = "abdecf";
        System.out.println("\nStrings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));

        m = "abcdef";
        n = "mnopf";
        p = "mnaobcdepf";
        System.out.println("\nStrings Interleaving");
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        System.out.println("Brute Force: " + game.solveWithBruteForce(m, n, p));
        System.out.println("Memoization: " + game.solveWithMemoization(m, n, p));
        System.out.println("Tabulation: " + game.solveWithTabulation(m, n, p));
    }

    private boolean solveWithBruteForce(String m, String n, String p) {
        if (m.length() + n.length() != p.length()) return false;
        return solve(m, n, p, 0, 0, 0);
    }

    private boolean solve(String m, String n, String p, int mIndex, int nIndex, int pIndex) {
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) return true;
        if (pIndex == p.length()) return false;
        boolean mString = false;
        boolean nString = false;
        if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
            mString = solve(m, n, p, mIndex + 1, nIndex, pIndex + 1);
        } else if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
            nString = solve(m, n, p, mIndex, nIndex + 1, pIndex + 1);
        }
        return mString || nString;
    }

    private boolean solveWithMemoization(String m, String n, String p) {
        if (m.length() + n.length() != p.length()) return false;
        Boolean[][][] memo = new Boolean[ m.length() + 1 ][ n.length() + 1 ][ p.length() + 1 ];
        return solve(m, n, p, 0, 0, 0, memo);
    }

    private boolean solve(String m, String n, String p, int mIndex, int nIndex, int pIndex, Boolean[][][] memo) {
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) return true;
        if (pIndex == p.length()) return false;
        if (memo[ mIndex ][ nIndex ][ pIndex ] == null) {
            boolean mString = false;
            boolean nString = false;
            if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
                mString = solve(m, n, p, mIndex + 1, nIndex, pIndex + 1, memo);
            } else if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
                nString = solve(m, n, p, mIndex, nIndex + 1, pIndex + 1, memo);
            }
            memo[ mIndex ][ nIndex ][ pIndex ] = mString || nString;
        }
        return memo[ mIndex ][ nIndex ][ pIndex ];
    }

    private boolean solveWithTabulation(String m, String n, String p) {
        if (m.length() + n.length() != p.length()) return false;
        return solve(m, n, p);
    }

    private boolean solve(String m, String n, String p) {
        int pIndex = 0;
        int mIndex = 0;
        int nIndex = 0;
        while (pIndex < p.length()) {
            if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
                mIndex++;
            } else if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
                nIndex++;
            } else {
                return false;
            }
            pIndex++;
        }
        return mIndex == m.length() && nIndex == n.length();
    }
}