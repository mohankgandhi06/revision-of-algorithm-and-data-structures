package com.revision.dynamicprogramming.tusharroy;

public class EWildcardMatching {
    public static void main(String[] args) {
        EWildcardMatching game = new EWildcardMatching();

        String string = "xrylmnoz";
        String regex = "x?y*z";
        game.solve(string, regex);

        string = "xylmnoz";//? is 0 - definitely false
        regex = "x?y*z";
        System.out.println();
        game.solve(string, regex);

        string = "xyylmnoz";
        regex = "x?y*z";
        System.out.println();
        game.solve(string, regex);

        string = "xryz";//* is 0 - is acceptable
        regex = "x?y*z";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmznoz";
        regex = "x?y*z";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x?y*z";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x?y*";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "xrylmnozols";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x?ylmnozol?";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x?ylmnozo?";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x*ylmnozol?";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x*ylmnozol";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x*?ls";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "x?*ls";
        System.out.println();
        game.solve(string, regex);

        string = "ols";
        regex = "?ls";
        System.out.println();
        game.solve(string, regex);

        string = "ls";
        regex = "?ls";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "*ls";
        System.out.println();
        game.solve(string, regex);

        string = "xrylsmnozols";
        regex = "*ls";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "*l";
        System.out.println();
        game.solve(string, regex);

        string = "xrylmnozols";
        regex = "*l?";
        System.out.println();
        game.solve(string, regex);
    }

    private void solve(String string, String regex) {
        System.out.println("String: " + string + " Regex: " + regex);
        boolean match = solveWithBruteForce(string, regex, 0, 0);
        System.out.println("Brute Force: " + match);
        Boolean[][] memo = new Boolean[ string.length() + 1 ][ regex.length() + 1 ];
        match = solveWithMemoization(string, regex, 0, 0, memo);
        System.out.println("Memoization: " + match);
        match = solveWithTabulation(string, regex);
        System.out.println("Tabulation: " + match);
    }

    private boolean solveWithBruteForce(String string, String regex, int stringIndex, int regexIndex) {
        if (stringIndex == string.length() && regexIndex == regex.length()) return true;
        if ((stringIndex == string.length() || regexIndex == regex.length())
                && ((regexIndex >= 1 && regex.charAt(regexIndex - 1) != '*') || (stringIndex == string.length() && regex.charAt(0) == '*'))) {
            return false;
        }
        boolean result = false;
        if (isPatternMatchingSoFar(string, regex, stringIndex, regexIndex)) {
            if (regexIndex == regex.length() && regex.charAt(regexIndex - 1) == '*') return true;
            if (regex.charAt(regexIndex) == '*') {
                boolean include = solveWithBruteForce(string, regex, stringIndex + 1, regexIndex);
                boolean exclude = solveWithBruteForce(string, regex, stringIndex, regexIndex + 1);
                result = include || exclude;
            } else if (solveWithBruteForce(string, regex, stringIndex + 1, regexIndex + 1)) {
                result = true;
            }
        }
        return result;
    }

    private boolean isPatternMatchingSoFar(String string, String regex, int stringIndex, int regexIndex) {
        if (regexIndex == regex.length() && regexIndex >= 1 && regex.charAt(regexIndex - 1) == '*') return true;
        if (regex.charAt(regexIndex) != '?' && regex.charAt(regexIndex) != '*') {
            return string.charAt(stringIndex) == regex.charAt(regexIndex);
        }
        return true;
    }

    private boolean solveWithMemoization(String string, String regex, int stringIndex, int regexIndex, Boolean[][] memo) {
        if (stringIndex == string.length() && regexIndex == regex.length()) return true;
        if ((stringIndex == string.length() || regexIndex == regex.length())
                && ((regexIndex >= 1 && regex.charAt(regexIndex - 1) != '*') || (stringIndex == string.length() && regex.charAt(0) == '*'))) {
            return false;
        }
        if (memo[ stringIndex ][ regexIndex ] == null) {
            boolean result = false;
            if (isPatternMatchingSoFar(string, regex, stringIndex, regexIndex)) {
                if (regexIndex == regex.length() && regex.charAt(regexIndex - 1) == '*') return true;
                if (regex.charAt(regexIndex) == '*') {
                    boolean include = solveWithMemoization(string, regex, stringIndex + 1, regexIndex, memo);
                    boolean exclude = solveWithMemoization(string, regex, stringIndex, regexIndex + 1, memo);
                    result = include || exclude;
                } else if (solveWithMemoization(string, regex, stringIndex + 1, regexIndex + 1, memo)) {
                    result = true;
                }
            }
            memo[ stringIndex ][ regexIndex ] = result;
        }
        return memo[ stringIndex ][ regexIndex ];
    }

    private boolean solveWithTabulation(String string, String regex) {
        boolean[][] table = new boolean[ string.length() + 1 ][ regex.length() + 1 ];
        table[ 0 ][ 0 ] = true;
        for (int col = 1; col < table[ 0 ].length; col++) {
            table[ 0 ][ col ] = regex.charAt(col - 1) == '*' && table[ 0 ][ col - 1 ];
        }
        for (int row = 1; row < table.length; row++) {
            for (int col = 1; col < table[ 0 ].length; col++) {
                if (regex.charAt(col - 1) != '*') {
                    if (regex.charAt(col - 1) == string.charAt(row - 1) || regex.charAt(col - 1) == '?') {
                        table[ row ][ col ] = table[ row - 1 ][ col - 1 ];
                    }
                } else if (regex.charAt(col - 1) == '*') {
                    table[ row ][ col ] = table[ row - 1 ][ col ] || table[ row ][ col - 1 ];
                }
            }
        }
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] ? "T " : "F ");
            }
            System.out.println();
        }
        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}