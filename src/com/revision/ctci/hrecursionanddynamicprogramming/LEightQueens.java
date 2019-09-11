package com.revision.ctci.hrecursionanddynamicprogramming;

public class LEightQueens {
    public static void main(String[] args) {
        LEightQueens game = new LEightQueens();//Let us consider this as a n queen problem
        int n = 8;
        game.solve(n);
    }

    private void solve(int n) {
        int[][] board = new int[ n ][ n ];
        if (backtrack(board, 0)) {
            showBoard(board, "Final Board");
        } else {
            System.out.println("No solution available");
        }
    }

    private boolean backtrack(int[][] board, int currentCol) {
        if (currentCol == board.length) return true;
        for (int currentRow = 0; currentRow < board.length; currentRow++) {//for all the position of the queen in a particular row
            if (isValid(board, currentRow, currentCol)) {
                //STEP #1: Make the move
                board[ currentRow ][ currentCol ] = 1;
                //STEP #2: Check for next column
                if (backtrack(board, currentCol + 1)) {
                    return true;
                }
                //STEP #3: Backtrack if no solution is found in the previous step
                board[ currentRow ][ currentCol ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[][] board, int currentRow, int currentCol) {
        if (currentRow < 0 || currentRow >= board.length || currentCol < 0 || currentCol >= board[ 0 ].length)
            return false;
        //same row
        for (int col = 0; col < currentCol; col++) {
            if (board[ currentRow ][ col ] == 1) return false;
        }
        //diagonal
        int up = currentRow;
        int down = currentRow;
        for (int col = currentCol - 1; col >= 0; col--) {
            up--;
            down++;
            if (up >= 0 && up < board.length) {
                if (board[ up ][ col ] == 1) return false;
            }
            if (down >= 0 && down < board[ 0 ].length) {
                if (board[ down ][ col ] == 1) return false;
            }
        }
        return true;
    }

    private void showBoard(int[][] board, String message) {
        for (int[] row : board) {
            for (int col : row) {
                System.out.print(col == 1 ? "Q " : "* ");
            }
            System.out.println();
        }
    }
}