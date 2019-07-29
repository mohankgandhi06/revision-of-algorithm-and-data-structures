package com.revision.ctci.aarraysandstrings;

import java.util.ArrayList;
import java.util.List;

public class HZeroMatrix {
    public static void main(String[] args) {
        HZeroMatrix game = new HZeroMatrix();
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 1, 2, 3, 4, 0, 6, 7},
                {8, 9, 0, 1, 2, 3, 4, 0},
                {9, 1, 2, 3, 4, 6, 4, 2},
                {8, 4, 3, 6, 7, 1, 0, 5}
        };
        game.solve(matrix);
    }

    private void solve(int[][] matrix) {
        System.out.println("Initial Matrix");
        show(matrix);

        System.out.println("\nAfter Assignment of Zero");
        System.out.println("Extra Space: ");
        int[][] duplicate = copy(matrix);
        solveWithExtraSpace(duplicate);
        show(duplicate);

        System.out.println("Without Extra Space: ");
        duplicate = copy(matrix);
        solveWithoutExtraSpace(duplicate);
        show(duplicate);
    }

    private void solveWithExtraSpace(int[][] matrix) {
        List<int[]> location = new ArrayList<>();
        /* TO AVOID FILLING THE ZERO ACCIDENTALLY INTO THE LOCATIONS WHICH WERE NOT INITIALLY FILLED AS ZERO
         * WE PROCESS FIRST THE LOCATIONS AND THEN SEPARATELY PROCESS THE ZERO ASSIGNMENT */
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                if (matrix[ row ][ col ] == 0) {
                    location.add(new int[]{row, col});
                }
            }
        }

        for (int[] item : location) {
            for (int column = 0; column < matrix[ 0 ].length; column++) {
                matrix[ item[ 0 ] ][ column ] = 0;
            }
            for (int row = 0; row < matrix.length; row++) {
                matrix[ row ][ item[ 1 ] ] = 0;
            }
        }
    }

    private void solveWithoutExtraSpace(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                if (matrix[ row ][ col ] == 0) {
                    matrix[ row ][ 0 ] = -1;
                    matrix[ 0 ][ col ] = -1;
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            if (matrix[ row ][ 0 ] == -1) {
                for (int col = 0; col < matrix[ 0 ].length; col++) {
                    matrix[ row ][ col ] = 0;
                }
            }
        }

        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[ 0 ][ col ] == -1) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[ row ][ col ] = 0;
                }
            }
        }
    }

    private void show(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                System.out.print(matrix[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }

    private int[][] copy(int[][] matrix) {
        int[][] duplicate = new int[ matrix.length ][ matrix[ 0 ].length ];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                duplicate[ row ][ col ] = matrix[ row ][ col ];
            }
        }
        return duplicate;
    }
}