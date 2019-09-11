package com.revision.ctci.hrecursionanddynamicprogramming;

public class JPaintFill {

    int[][] places = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, //Top Row
            {0, -1}, {0, 1},            //Middle Row
            {1, -1}, {1, 0}, {1, 1}     //Bottom Row
    };

    public static void main(String[] args) {
        JPaintFill game = new JPaintFill();
        int[][] canvas = new int[][]{
                {1, 2, 1, 1, 2, 1},
                {2, 1, 1, 1, 1, 1},
                {1, 1, 2, 2, 2, 4},
                {4, 2, 3, 1, 3, 2},
                {5, 2, 1, 3, 1, 2},
                {4, 2, 3, 3, 3, 2},
                {3, 2, 2, 2, 2, 1}
        };
        int row = 2;
        int col = 2;
        int from = canvas[ row ][ col ];
        int to = 5;
        game.show(canvas, "Original");
        game.solve(canvas, from, to, row, col);
        game.show(canvas, "After Fill");

        row = 2;
        col = 2;
        from = canvas[ row ][ col ];
        to = 6;
        game.show(canvas, "Original");
        game.solve(canvas, from, to, row, col);
        game.show(canvas, "After Fill");
    }

    private void solve(int[][] canvas, int from, int to, int row, int col) {
        for (int[] place : places) {
            int nextRow = row + place[ 0 ];
            int nextCol = col + place[ 1 ];
            if (isValid(canvas, nextRow, nextCol) && canvas[ nextRow ][ nextCol ] == from) {
                canvas[ nextRow ][ nextCol ] = to;
                solve(canvas, from, to, nextRow, nextCol);
            }
        }
    }

    private boolean isValid(int[][] canvas, int row, int col) {
        if (row < 0 || row >= canvas.length || col < 0 || col >= canvas[ 0 ].length) return false;
        return true;
    }

    private void show(int[][] canvas, String message) {
        System.out.println(message + ": ");
        for (int[] row : canvas) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}