package com.revision.dynamicprogramming.udemy.backtracking;

import java.util.Arrays;

public class BHamiltonianCycle {
    public static void main(String[] args) {
        BHamiltonianCycle game = new BHamiltonianCycle();
        char[] vertex = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int[][] adjacency = new int[][]{
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0}
        };
        game.solve(vertex, adjacency);

        adjacency = new int[][]{
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        game.solve(vertex, adjacency);
    }

    private void solve(char[] vertex, int[][] adjacency) {
        /* Add Vertex at zero'th index of the path (same length as the no of vertices)
        and start adding vertices after checking if they are adjacent to the previously
        added vertex and that they are not previously added before this instance */
        int[] path = new int[ vertex.length ];
        Arrays.fill(path, -1);
        path[ 0 ] = 0;
        /*for (int item : path) {
            System.out.print((item >= 0 ? vertex[ item ] : '-') + " ");
        }
        System.out.println();*/
        if (isAHamiltonianCycle(vertex, adjacency, path, 1)) {
            for (int item : path) {
                System.out.print((item >= 0 ? vertex[ item ] : '-') + " ");
            }
            System.out.println();
        } else {
            System.out.println("No Solution Exists...");
        }
        return;
    }

    private boolean isAHamiltonianCycle(char[] vertex, int[][] adjacency, int[] path, int count) {
        if (count == vertex.length) {
            /* If it is just the path the returning true is sufficient */
            /* but for a cycle the last and the first of the path should be adjacent again */
            if (adjacency[ path[ count - 1 ] ][ path[ 0 ] ] == 1) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < adjacency[ 0 ].length; i++) {
            if (isValidPath(adjacency, path, i, count - 1)) {
                /* Add the Path */
                path[ count ] = i;

                /* Check if there is subsequent path available return true if so */
                if (isAHamiltonianCycle(vertex, adjacency, path, count + 1)) {
                    return true;
                }

                /* else backtrack */
                path[ count ] = -1;
            }
        }
        return false;
    }

    private boolean isValidPath(int[][] adjacency, int[] path, int currentVertex, int previousVertex) {
        if (adjacency[ path[ previousVertex ] ][ currentVertex ] == 1) {
            for (int i = 0; i < path.length; i++) {
                if (path[ i ] == -1) break;
                if (path[ i ] == currentVertex) return false;
            }
            return true;
        }
        return false;
    }
}