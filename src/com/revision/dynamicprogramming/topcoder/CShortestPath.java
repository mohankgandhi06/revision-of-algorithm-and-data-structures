package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;
import java.util.HashMap;

public class CShortestPath {
    public static void main(String[] args) {
        CShortestPath game = new CShortestPath();
        int vertex = 10;
        int[][] adjacency = new int[][]{
                {0, 1, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0}
        };
        System.out.println("INFO: Brute Force and Memoization is skipped for this problem\n");
        int[] cost = new int[]{5, 7, 6, 5, 6, 7, 7, 8, 8, 5};
        game.solve(vertex, adjacency, cost);

        cost = new int[]{5, 7, 2, 5, 2, 1, 7, 8, 8, 5};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        cost = new int[]{5, 7, 6, 5, 4, 6, 7, 8, 6, 5};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        cost = new int[]{5, 7, 4, 5, 6, 7, 5, 6, 4, 5};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        vertex = 9;
        adjacency = new int[][]{
                {0, 1, 0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0},
        };
        cost = new int[]{5, 7, 4, 5, 6, 7, 5, 6, 4};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        vertex = 8;
        adjacency = new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}
        };
        cost = new int[]{3, 5, 10, 6, 5, 3, 4, 10};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        vertex = 8;
        adjacency = new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}
        };
        cost = new int[]{3, 5, 1, 6, 1, 3, 4, 10};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);

        vertex = 8;
        adjacency = new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}
        };
        cost = new int[]{3, 5, 4, 2, 2, 2, 7, 10};
        System.out.println("____________________________________________________________");
        game.solve(vertex, adjacency, cost);
    }

    private void solve(int vertex, int[][] adjacency, int[] cost) {
        System.out.println("Shortest path from: " + 1 + " to " + vertex);
        System.out.println("Adjacency Matrix");
        show(adjacency);
        System.out.println("Cost of passing through each vertex");
        Arrays.stream(cost).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Brute Force: ");
        boolean[] visited = new boolean[ vertex ];
        visited[ 0 ] = true;
        System.out.println("Path Cost: " + solveWithBruteForce(0, adjacency, cost, cost[ 0 ], visited, Integer.MAX_VALUE));

        System.out.println("Memoization: ");
        visited = new boolean[ vertex ];
        visited[ 0 ] = true;
        HashMap<String, Integer> memo = new HashMap<>();
        System.out.println("Path Cost: " + solveWithMemoization(0, adjacency, cost, cost[ 0 ], visited, Integer.MAX_VALUE, memo));

        System.out.println("Tabulation: ");
        int[] table = new int[ adjacency.length ];
        Arrays.fill(table, Integer.MAX_VALUE);
        System.out.println("Path Cost: " + solveWithTabulation(adjacency, cost, table));
    }

    private int solveWithBruteForce(int currentVertex, int[][] adjacency, int[] cost, int pathValue, boolean[] visited, int shortestSoFar) {
        if (currentVertex == adjacency.length - 1) return pathValue;
        for (int way = 0; way < adjacency[ 0 ].length; way++) {
            if (adjacency[ currentVertex ][ way ] == 1 && !visited[ way ]) {
                visited[ way ] = true;
                int value = solveWithBruteForce(way, adjacency, cost, pathValue + cost[ way ], visited, shortestSoFar);
                shortestSoFar = Math.min(value, shortestSoFar);
                visited[ way ] = false;
            }
        }
        return shortestSoFar;
    }

    private int solveWithMemoization(int currentVertex, int[][] adjacency, int[] cost, int pathValue, boolean[] visited, int shortestSoFar, HashMap<String, Integer> memo) {
        if (currentVertex == adjacency.length - 1) return pathValue;
        String key = frameKey(currentVertex, pathValue, visited, shortestSoFar);
        if (!memo.containsKey(key)) {
            for (int way = 0; way < adjacency[ 0 ].length; way++) {
                if (adjacency[ currentVertex ][ way ] == 1 && !visited[ way ]) {
                    visited[ way ] = true;
                    int value = solveWithMemoization(way, adjacency, cost, pathValue + cost[ way ], visited, shortestSoFar, memo);
                    shortestSoFar = Math.min(value, shortestSoFar);
                    visited[ way ] = false;
                }
            }
            memo.put(key, shortestSoFar);
        }
        return memo.get(key);
    }

    private int solveWithTabulation(int[][] adjacency, int[] cost, int[] table) {
        table[ 0 ] = cost[ 0 ];
        for (int col = 0; col < adjacency[ 0 ].length; col++) {
            if (adjacency[ 0 ][ col ] == 1) {
                table[ col ] = cost[ col ] + cost[ 0 ];
            }
        }

        for (int col = 1; col < table.length - 1; col++) {
            int min = table[ col ];
            for (int index = 1; index < adjacency[ 0 ].length; index++) {
                if (adjacency[ col ][ index ] == 1) {
                    min = Math.min(min, table[ index ] != Integer.MAX_VALUE ? (table[ index ] + cost[ col ]) : Integer.MAX_VALUE);
                }
            }
            table[ col ] = min;
        }

        /* CONSOLIDATING FOR THE CHANGES IN THE VALUE AFTER THE INITIAL CHECK FOR EXAMPLE TAKE THE
        * SHORTEST PATH FROM 1 - 8 WITH COST (3 5 1 6 1 3 4 10) */
        for (int col = 1; col < table.length; col++) {
            int min = table[ col ];
            for (int index = 1; index < adjacency[ 0 ].length; index++) {
                if (adjacency[ col ][ index ] == 1) {
                    min = Math.min(min, table[ index ] != Integer.MAX_VALUE ? (table[ index ] + cost[ col ]) : Integer.MAX_VALUE);
                }
            }
            table[ col ] = min;
        }

        for (int row = 0; row < table.length; row++) {
            System.out.print(table[ row ] + " ");
        }
        System.out.println();
        return table[ table.length - 1 ];
    }

    private String frameKey(int currentVertex, int pathValue, boolean[] visited, int shortestSoFar) {
        StringBuilder key = new StringBuilder();
        StringBuilder booleanArrayString = new StringBuilder();
        for (boolean item : visited) {
            booleanArrayString.append(item ? "T" : "F");
        }
        key.append(currentVertex).append(pathValue).append(booleanArrayString).append(shortestSoFar);
        return key.toString();
    }

    private void show(int[][] adjacency) {
        for (int row = 0; row < adjacency.length; row++) {
            for (int col = 0; col < adjacency[ 0 ].length; col++) {
                System.out.print(adjacency[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}