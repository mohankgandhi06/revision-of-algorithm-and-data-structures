package com.revision.dynamicprogramming.udemy.backtracking;

public class DKnightsTour {

    public int[][] moves = new int[][]{
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };

    public static void main(String[] args) {
        DKnightsTour game = new DKnightsTour();
        int size = 8;
        int[][] board = new int[ size ][ size ];
        int x = 0;
        int y = 0;
        System.out.println("Board Size: " + size + " Knight's Starting Position X (" + x + ") and Y(" + y + ")\n");
        game.solve(board, x, y);

        size = 5;
        board = new int[ size ][ size ];
        x = 2;
        y = 2;
        System.out.println("\nBoard Size: " + size + " Knight's Starting Position X (" + x + ") and Y(" + y + ")\n");
        game.solve(board, x, y);
    }

    private void solve(int[][] board, int x, int y) {
        board[ x ][ y ] = 1;
        if (tour(board, x, y, 1, board.length * board[ 0 ].length)) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[ 0 ].length; col++) {
                    String spaces = board[ row ][ col ] / 10 > 0 ? "  " : "   ";
                    System.out.print(board[ row ][ col ] + spaces);
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("No Solution Exists...");
        }
    }

    private boolean tour(int[][] board, int x, int y, int move, int size) {
        if (move == size) return true;
        for (int i = 0; i < this.moves.length; i++) {
            if (isValid(board, x + this.moves[ i ][ 0 ], y + this.moves[ i ][ 1 ])) {
                /* ADD TO THE MOVE */
                board[ x + this.moves[ i ][ 0 ] ][ y + this.moves[ i ][ 1 ] ] = move + 1;
                if (tour(board, x + this.moves[ i ][ 0 ], y + this.moves[ i ][ 1 ], move + 1, size)) {
                    return true;
                }
                /* BACKTRACK */
                board[ x + this.moves[ i ][ 0 ] ][ y + this.moves[ i ][ 1 ] ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[][] board, int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[ 0 ].length && board[ x ][ y ] == 0) {
            return true;
        }
        return false;
    }
}