package com.revision.dynamicprogramming.udemy.backtracking;

public class FSudoku {
    public static void main(String[] args) {
        FSudoku game = new FSudoku();
        int[][] sudokuBoard = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        game.solve(sudokuBoard);
    }

    private void solve(int[][] sudokuBoard) {
        if (answer(sudokuBoard, 0, 0)) {
            for (int row = 0; row < sudokuBoard.length; row++) {
                for (int col = 0; col < sudokuBoard[ 0 ].length; col++) {
                    if ((col) % 3 == 0) System.out.print(" ");
                    System.out.print(sudokuBoard[ row ][ col ] + " ");
                }
                if ((row + 1) % 3 == 0) {
                    System.out.println();
                }
                System.out.println();
            }
        } else {
            System.out.println("No Solution Exists...");
        }
    }

    private boolean answer(int[][] sudokuBoard, int row, int col) {
        if (col == sudokuBoard[ 0 ].length) {
            if (row == sudokuBoard.length - 1) return true;
            row++;
            col = 0;
        }
        for (int i = 1; i <= 9; i++) {
            if (isValid(sudokuBoard, row, col, i)) {
                /* ADD IT TO THE STEP */
                sudokuBoard[ row ][ col ] = i;
                /* PROCEED FURTHER */
                if (answer(sudokuBoard, row, col + 1)) {
                    return true;
                }
                /* BACKTRACK */
                sudokuBoard[ row ][ col ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[][] sudokuBoard, int row, int col, int number) {
        for (int i = 0; i < row; i++) {
            if (sudokuBoard[ i ][ col ] == number) return false;
        }
        for (int j = 0; j < col; j++) {
            if (sudokuBoard[ row ][ j ] == number) return false;
        }

        for (int r = (row / 3) * 3; r <= row; r++) {
            for (int c = (col / 3) * 3; r == row ? c < col : (c < ((col / 3) + 1) * 3); c++) {
                if (sudokuBoard[ r ][ c ] == number) return false;
            }
        }
        return true;
    }
}