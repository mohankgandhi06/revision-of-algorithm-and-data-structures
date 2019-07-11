package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;

public class EBadNeighbors {
    public static void main(String[] args) {
        EBadNeighbors game = new EBadNeighbors();
        int[] donations = new int[]{10, 3, 2, 5, 7, 8};
        game.solve(donations);

        donations = new int[]{11, 15};
        System.out.println();
        game.solve(donations);

        donations = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println();
        game.solve(donations);

        donations = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println();
        game.solve(donations);

        donations = new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
                6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
                52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};
        System.out.println();
        game.solve(donations);
    }

    private void solve(int[] donations) {
        System.out.println("Donations: ");
        Arrays.stream(donations).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Maximum Donations");
        System.out.println("Brute Force:" + solveWithBruteForce(donations, 0, false));
        Integer[][] memo = new Integer[ donations.length ][ 2 ];
        System.out.println("Memoization:" + solveWithMemoization(donations, 0, false, memo));
        System.out.println("Tabulation:" + solveWithTabulation(donations));
    }

    private int solveWithBruteForce(int[] donations, int currentIndex, boolean first) {
        if (currentIndex >= donations.length) return 0;
        int include = 0;
        if (currentIndex != donations.length - 1) {
            include = donations[ currentIndex ] + solveWithBruteForce(donations, currentIndex + 2, currentIndex == 0 || first);
        } else if (!first) {
            include = donations[ currentIndex ] + solveWithBruteForce(donations, currentIndex + 2, first);
        }
        int exclude = solveWithBruteForce(donations, currentIndex + 1, first);
        return Math.max(include, exclude);
    }

    private int solveWithMemoization(int[] donations, int currentIndex, boolean first, Integer[][] memo) {
        if (currentIndex >= donations.length) return 0;
        if (memo[ currentIndex ][ first ? 1 : 0 ] == null) {
            int include = 0;
            if (currentIndex != donations.length - 1) {
                include = donations[ currentIndex ] + solveWithMemoization(donations, currentIndex + 2, currentIndex == 0 || first, memo);
            } else if (!first) {
                include = donations[ currentIndex ] + solveWithMemoization(donations, currentIndex + 2, first, memo);
            }
            int exclude = solveWithMemoization(donations, currentIndex + 1, first, memo);
            memo[ currentIndex ][ first ? 1 : 0 ] = Math.max(include, exclude);
        }
        return memo[ currentIndex ][ first ? 1 : 0 ];
    }

    private int solveWithTabulation(int[] donations) {
        int[][] include = new int[ 2 ][ donations.length ];
        int[][] exclude = new int[ 2 ][ donations.length ];
        include[ 0 ][ 0 ] = donations[ 0 ];
        for (int index = 1; index < donations.length - 1; index++) {
            include[ 0 ][ index ] = Math.max(include[ 0 ][ index - 1 ], donations[ index ] + include[ 1 ][ index - 1 ]);
            include[ 1 ][ index ] = Math.max(include[ 0 ][ index - 1 ], include[ 1 ][ index - 1 ]);
        }
        include[ 0 ][ donations.length - 1 ] = include[ 0 ][ donations.length - 2 ];
        include[ 1 ][ donations.length - 1 ] = include[ 1 ][ donations.length - 2 ];
        for (int index = 1; index < donations.length; index++) {
            exclude[ 0 ][ index ] = Math.max(exclude[ 0 ][ index - 1 ], donations[ index ] + exclude[ 1 ][ index - 1 ]);
            exclude[ 1 ][ index ] = Math.max(exclude[ 0 ][ index - 1 ], exclude[ 1 ][ index - 1 ]);
        }
        System.out.println("INCLUDE: ");
        for (int row = 0; row < 2; row++) {
            for (int index = 0; index < donations.length; index++) {
                System.out.print(include[ row ][ index ] + " ");
            }
            System.out.println();
        }

        System.out.println("EXCLUDE: ");
        for (int row = 0; row < 2; row++) {
            for (int index = 0; index < donations.length; index++) {
                System.out.print(exclude[ row ][ index ] + " ");
            }
            System.out.println();
        }
        return maximum(include, exclude);
    }

    private int maximum(int[][] include, int[][] exclude) {
        int includeMax = Math.max(include[ 0 ][ include[ 0 ].length - 1 ], include[ 1 ][ include[ 0 ].length - 1 ]);
        int excludeMax = Math.max(exclude[ 0 ][ exclude[ 0 ].length - 1 ], exclude[ 1 ][ exclude[ 0 ].length - 1 ]);
        return Math.max(includeMax, excludeMax);
    }
}