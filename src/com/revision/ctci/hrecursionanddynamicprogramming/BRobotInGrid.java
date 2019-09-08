package com.revision.ctci.hrecursionanddynamicprogramming;

public class BRobotInGrid {
    public static void main(String[] args) {
        BRobotInGrid game = new BRobotInGrid();
        int[][] grid = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0}
        };
        game.solve(grid);

        grid = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0}
        };
        game.solve(grid);

        grid = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0}
        };
        game.solve(grid);
    }

    private void solve(int[][] grid) {
        int row = 0;
        int col = 0;
        int targetRow = grid.length - 1;
        int targetCol = grid[ 0 ].length - 1;
        System.out.println("Maze: ");
        showGrid(grid);
        grid[ 0 ][ 0 ] = 2;
        int[][] options = new int[][]{
                {0, 1},//Right
                {1, 0}//Down
        };
        boolean pathExists = solveWithBacktrack(grid, row, col, targetRow, targetCol, options);
        if (pathExists) {
            System.out.println("Path: ");
            showGrid(grid);
        } else {
            System.out.println("No path found");
        }
        System.out.println();
    }

    private boolean solveWithBacktrack(int[][] grid, int row, int col, int targetRow, int targetCol, int[][] options) {
        if (row == targetRow && col == targetCol) {
            grid[ targetRow ][ targetCol ] = 2;
            return true;
        }
        for (int paths = 0; paths < options.length; paths++) {
            if (isValid(row + options[ paths ][ 0 ], col + options[ paths ][ 1 ], grid)) {
                /* STEP #1: Make the move */
                grid[ row + options[ paths ][ 0 ] ][ col + options[ paths ][ 1 ] ] = 2;//2 signifies the robots move
                /* STEP #2: Call the recursive method for the and if that returns true then a path exists and so we can proceed */
                if (solveWithBacktrack(grid, row + options[ paths ][ 0 ], col + options[ paths ][ 1 ], targetRow, targetCol, options)) {
                    return true;
                }
                /* STEP #3: else we need to backtrack and try the other options */
                grid[ row + options[ paths ][ 0 ] ][ col + options[ paths ][ 1 ] ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int[][] grid) {
        //Even though the below two checks can be combined, we are keeping separate for easy readability
        if (row >= grid.length || col >= grid[ 0 ].length) return false;//Boundary Check
        if (grid[ row ][ col ] == 1) return false;//Road Block Check
        return true;
    }

    private void showGrid(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[ 0 ].length; col++) {
                System.out.print(grid[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}