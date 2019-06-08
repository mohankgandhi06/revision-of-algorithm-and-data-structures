package com.revision.dynamicprogramming.grokking.longestcommonsubstring;

public class CMinimumDeletionInsertionToTransform {
    public static void main(String[] args) {
        CMinimumDeletionInsertionToTransform game = new CMinimumDeletionInsertionToTransform();
        String s1 = "abc";
        String s2 = "fbc";
        System.out.println("Minimum Deletion Insertion To Transform");
        System.out.println("S1: " + s1 + " to S2: " + s2);
        int[] insertionAndDeletion = game.solveWithBruteForce(s1, s2);
        System.out.println("Brute Force: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithMemoization(s1, s2);
        System.out.println("Memoization: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithTabulation(s1, s2);
        System.out.println("Tabulation: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);

        s1 = "abdca";
        s2 = "cbda";
        System.out.println("\nMinimum Deletion Insertion To Transform");
        System.out.println("S1: " + s1 + " to S2: " + s2);
        insertionAndDeletion = game.solveWithBruteForce(s1, s2);
        System.out.println("Brute Force: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithMemoization(s1, s2);
        System.out.println("Memoization: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithTabulation(s1, s2);
        System.out.println("Tabulation: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);

        s1 = "passport";
        s2 = "ppsspt";
        System.out.println("\nMinimum Deletion Insertion To Transform");
        System.out.println("S1: " + s1 + " to S2: " + s2);
        insertionAndDeletion = game.solveWithBruteForce(s1, s2);
        System.out.println("Brute Force: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithMemoization(s1, s2);
        System.out.println("Memoization: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
        insertionAndDeletion = game.solveWithTabulation(s1, s2);
        System.out.println("Tabulation: Deletion " + insertionAndDeletion[ 0 ] + " Insertion: " + insertionAndDeletion[ 1 ]);
    }

    private int[] solveWithBruteForce(String s1, String s2) {
        int maximumCommonSubstring = solve(s1, s2, 0, 0);
        int[] insertionAndDeletion = new int[ 2 ];
        insertionAndDeletion[ 0 ] = s1.length() - maximumCommonSubstring;//Deletion
        insertionAndDeletion[ 1 ] = s2.length() - (s1.length() - insertionAndDeletion[ 0 ]);//Insertion
        return insertionAndDeletion;
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

    private int[] solveWithMemoization(String s1, String s2) {
        Integer[][] memo = new Integer[ s1.length() ][ s2.length() ];
        int maximumCommonSubstring = solve(s1, s2, 0, 0, memo);
        int[] insertionAndDeletion = new int[ 2 ];
        insertionAndDeletion[ 0 ] = s1.length() - maximumCommonSubstring;//Deletion
        insertionAndDeletion[ 1 ] = s2.length() - (s1.length() - insertionAndDeletion[ 0 ]);//Insertion
        return insertionAndDeletion;
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

    private int[] solveWithTabulation(String s1, String s2) {
        int[][] table = new int[ 2 ][ s2.length() + 1 ];
        int maximumCommonSubstring = solve(s1, s2, table);
        int[] insertionAndDeletion = new int[ 2 ];
        insertionAndDeletion[ 0 ] = s1.length() - maximumCommonSubstring;//Deletion
        insertionAndDeletion[ 1 ] = s2.length() - (s1.length() - insertionAndDeletion[ 0 ]);//Insertion
        return insertionAndDeletion;
    }

    private int solve(String s1, String s2, int[][] table) {
        int maxLength = 0;
        for (int row = 1; row <= s1.length(); row++) {
            for (int col = 1; col <= s2.length(); col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    table[ row % 2 ][ col ] = 1 + table[ (row - 1) % 2 ][ col - 1 ];
                } else {
                    table[ row % 2 ][ col ] = Math.max(table[ (row - 1) % 2 ][ col ], table[ row % 2 ][ col - 1 ]);
                }
                maxLength = Math.max(maxLength, table[ row % 2 ][ col ]);
            }
            /*System.out.println("Table: ");
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[ 0 ].length; j++) {
                    System.out.print(table[ i ][ j ] + " ");
                }
                System.out.println();
            }*/
        }
        return maxLength;
    }
}