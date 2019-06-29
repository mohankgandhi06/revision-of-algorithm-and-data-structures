package com.revision.dynamicprogramming.bytebybyte;

public class CSquareSubmatrix {
    public static void main(String[] args) {
        CSquareSubmatrix game = new CSquareSubmatrix();
        boolean[][] matrix = new boolean[][]{
                {false, true, false, false},
                {true, true, true, true},
                {false, true, true, false},
                {true, true, true, true}
        };
        game.solve(matrix);

        matrix = new boolean[][]{
                {false, true, false, false},
                {true, true, true, true},
                {false, true, true, true},
                {true, true, true, true}
        };
        game.solve(matrix);

        matrix = new boolean[][]{
                {false, true, false, false},
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}
        };
        game.solve(matrix);

        matrix = new boolean[][]{
                {false, true, false, false, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {false, true, true, false, true, true, true, true},
                {true, true, true, true, true, true, true, true},
        };
        game.solve(matrix);

        matrix = new boolean[][]{
                {false, true, false, false, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {false, true, true, false, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true}
        };
        game.solve(matrix);
    }

    private void solve(boolean[][] matrix) {
        System.out.println("Max square submatrix dimension that can be achieved: ");
        System.out.println("Matrix: ");
        show(matrix);
        System.out.println("Brute Force: " + solveWithBruteForce(matrix));
        System.out.println("Memoization: " + solveWithMemoization(matrix));
        System.out.println("Tabulation: " + solveWithTabulation(matrix));
    }

    private int solveWithBruteForce(boolean[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ 0 ].length; j++) {
                int temp = 0;
                if (matrix[ i ][ j ]) {
                    temp = 1 + minimum(solveWithBruteForce(matrix, i, j + 1), solveWithBruteForce(matrix, i + 1, j), solveWithBruteForce(matrix, i + 1, j + 1));
                }
                max = Math.max(temp, max);
            }
        }
        return max;
    }

    private int solveWithBruteForce(boolean[][] matrix, int row, int col) {
        if (row == matrix.length || col == matrix[ 0 ].length) return 0;
        if (!matrix[ row ][ col ]) return 0;
        return 1 + minimum(solveWithBruteForce(matrix, row, col + 1), solveWithBruteForce(matrix, row + 1, col), solveWithBruteForce(matrix, row + 1, col + 1));
    }

    private int solveWithMemoization(boolean[][] matrix) {
        Integer[][] memo = new Integer[ matrix.length ][ matrix[ 0 ].length ];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ 0 ].length; j++) {
                int temp = 0;
                if (matrix[ i ][ j ]) {
                    temp = 1 + minimum(solveWithMemoization(matrix, i, j + 1, memo), solveWithMemoization(matrix, i + 1, j, memo), solveWithMemoization(matrix, i + 1, j + 1, memo));
                }
                max = Math.max(temp, max);
            }
        }
        return max;
    }

    private int solveWithMemoization(boolean[][] matrix, int row, int col, Integer[][] memo) {
        if (row == matrix.length || col == matrix[ 0 ].length) return 0;
        if (!matrix[ row ][ col ]) return 0;
        if (memo[ row ][ col ] == null) {
            memo[ row ][ col ] = 1 + minimum(solveWithMemoization(matrix, row, col + 1, memo), solveWithMemoization(matrix, row + 1, col, memo), solveWithMemoization(matrix, row + 1, col + 1, memo));
        }
        return memo[ row ][ col ];
    }

    private int solveWithTabulation(boolean[][] matrix) {
        int[][] square = new int[ matrix.length + 1 ][ matrix[ 0 ].length + 1 ];
        int max = 0;
        for (int row = 1; row < square.length; row++) {
            for (int col = 1; col < square[ 0 ].length; col++) {
                if (matrix[ row - 1 ][ col - 1 ]) {
                    square[ row ][ col ] = 1 + minimum(square[ row ][ col - 1 ], square[ row - 1 ][ col ], square[ row - 1 ][ col - 1 ]);
                    max = Math.max(square[ row ][ col ], max);
                }
            }
        }
        /*for (int row = 1; row < square.length; row++) {
            for (int col = 1; col < square[ 0 ].length; col++) {
                System.out.print(square[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return max;
    }

    private int minimum(int topRight, int bottomLeft, int bottomRight) {
        return Math.min(topRight, Math.min(bottomLeft, bottomRight));
    }

    private void show(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ 0 ].length; j++) {
                System.out.print(matrix[ i ][ j ] ? "T " : "F ");
            }
            System.out.println();
        }
    }
}