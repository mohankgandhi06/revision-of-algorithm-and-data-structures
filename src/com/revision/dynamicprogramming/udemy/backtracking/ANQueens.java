package com.revision.dynamicprogramming.udemy.backtracking;

public class ANQueens {
    public static void main(String[] args) {
        ANQueens game = new ANQueens();
        int n = 7;
        game.solve(n);
    }

    private void solve(int n) {
        boolean[][] board = new boolean[ n ][ n ];
        if (solve(board, 0)) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[ 0 ].length; col++) {
                    System.out.print((board[ row ][ col ] ? 'Q' : '*') + "  ");
                }
                System.out.println();
                System.out.println();
            }
        } else {
            System.out.println("No Valid Solution....");
        }
    }

    private boolean solve(boolean[][] board, int currentIndex) {
        if (currentIndex == board.length) return true;
        for (int row = 0; row < board.length; row++) {
            if (isValid(board, currentIndex, row)) {
                /* MAKE THE CHOICE */
                board[ row ][ currentIndex ] = true;
                /* CHECK IF THE SUBSEQUENT PATH IS TRUE AS WELL */
                if (solve(board, currentIndex + 1)) {
                    return true;
                }
                /* BACK TRACK THE CHOICE */
                board[ row ][ currentIndex ] = false;
            }
        }
        return false;
    }

    private boolean isValid(boolean[][] board, int currentIndex, int row) {
        /* DIAGONAL UP AND DOWN */
        int up = row - 1;
        int down = row + 1;
        int col = currentIndex - 1;
        while ((up >= 0 || down < board.length) && col >= 0) {
            if (up >= 0) {
                if (board[ up ][ col ]) return false;
            }
            if (down < board.length) {
                if (board[ down ][ col ]) return false;
            }
            up--;
            down++;
            col--;
        }
        /* STRAIGHT */
        col = currentIndex;
        while (col >= 0) {
            if (board[ row ][ col ]) return false;
            col--;
        }
        return true;
    }
}