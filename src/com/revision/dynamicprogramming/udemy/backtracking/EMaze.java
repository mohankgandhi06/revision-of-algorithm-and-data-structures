package com.revision.dynamicprogramming.udemy.backtracking;

public class EMaze {

    public int[][] possibleWays = new int[][]{
            {0, 1},//RIGHT
            {1, 0}//DOWN
    };

    public static void main(String[] args) {
        EMaze game = new EMaze();
        int[][] maze = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0}
        };
        game.solve(maze, 0, 0, 4, 4);
    }

    private void solve(int[][] maze, int row, int col, int goalX, int goalY) {
        int[][] path = new int[ maze.length ][ maze[ 0 ].length ];
        path[ row ][ col ] = -1;
        if (pathfinder(maze, path, row, col, goalX, goalY)) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[ 0 ].length; j++) {
                    System.out.print((path[ i ][ j ] == 0 ? maze[ i ][ j ] : "-") + " ");
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("No Solution Exists...");
        }
    }

    private boolean pathfinder(int[][] maze, int[][] path, int row, int col, int goalX, int goalY) {
        if (row == goalX && col == goalY) return true;
        for (int i = 0; i < this.possibleWays.length; i++) {
            if (isValid(maze, row + this.possibleWays[ i ][ 0 ], col + this.possibleWays[ i ][ 1 ])) {
                /* ADD IT TO THE STEP */
                path[ row + this.possibleWays[ i ][ 0 ] ][ col + this.possibleWays[ i ][ 1 ] ] = -1;
                /* PROCEED FURTHER */
                if (pathfinder(maze, path, row + this.possibleWays[ i ][ 0 ], col + this.possibleWays[ i ][ 1 ], goalX, goalY)) {
                    return true;
                }
                /* BACKTRACK */
                path[ row + this.possibleWays[ i ][ 0 ] ][ col + this.possibleWays[ i ][ 1 ] ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[][] maze, int row, int col) {
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[ 0 ].length && maze[ row ][ col ] == 0) {
            return true;
        }
        return false;
    }
}