package com.revision.dynamicprogramming.udemy.backtracking;

import java.util.Arrays;

public class CColoringProblem {

    private int[] color;

    public CColoringProblem() {
        this.color = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    }

    public static void main(String[] args) {
        CColoringProblem game = new CColoringProblem();
        char[] vertex = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int[][] adjacency = new int[][]{
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 1, 1, 1},
                {1, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 1, 1, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 0, 0}
        };
        game.solve(vertex, adjacency);
    }

    private void solve(char[] vertex, int[][] adjacency) {
        int[] colors = new int[ vertex.length ];
        colors[ 0 ] = 1;
        if (color(colors, adjacency, 1)) {
            Arrays.stream(colors).forEach(value -> System.out.print(value + " "));
            System.out.println();
        } else {
            System.out.println("No Solution Exists...");
        }
    }

    private boolean color(int[] colors, int[][] adjacency, int currentIndex) {
        if (currentIndex == colors.length) return true;
        for (int i = 0; i < this.color.length; i++) {
            if (isValid(colors, adjacency, currentIndex, this.color[ i ])) {
                /* SET THE VALUE */
                colors[ currentIndex ] = this.color[ i ];
                /* CHECK FURTHER */
                if (color(colors, adjacency, currentIndex + 1)) {
                    return true;
                }
                /* BACKTRACK */
                colors[ currentIndex ] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[] colors, int[][] adjacency, int currentIndex, int color) {
        for (int i = 0; i < adjacency[ 0 ].length; i++) {
            if (adjacency[ currentIndex ][ i ] == 1 && colors[ i ] == color) {
                return false;
            }
        }
        return true;
    }
}