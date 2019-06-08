package com.revision.khanacademy;

import java.util.LinkedList;
import java.util.Queue;

public class APathFinder {
    public static void main(String[] args) {
        APathFinder game = new APathFinder();
        int[][] maze = new int[][]{
                {-1, -1, -1, -1, -1, 0, -1, -1},
                {-1, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, -1, -1, -1, -1, 0, -1},
                {-1, 0, -1, 0, -1, 0, 0, -1},
                {-1, 0, -1, 0, -1, -1, 0, -1},
                {-1, 0, -1, 0, -1, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1}
        };

        /*int[] start = new int[]{3, 3};//X and Y coordinate*/
        int[] start = new int[]{-6, 1};
        /*int[] goal = new int[]{0, 5};*/
        int[] goal = new int[]{4, 6};

        game.findPath(maze, start, goal);
    }

    private int[][] findPath(int[][] maze, int[] start, int[] goal) {
        if (start[ 0 ] < 0 || start[ 0 ] >= maze.length || start[ 1 ] < 0 || start[ 1 ] >= maze[ 0 ].length
                || goal[ 0 ] < 0 || goal[ 0 ] >= maze.length || goal[ 1 ] < 0 || goal[ 1 ] >= maze[ 0 ].length) {
            System.out.println("Invalid Index Locations. out of bounds of the maze");
            return null;
        }
        if (maze[ start[ 0 ] ][ start[ 1 ] ] == -1 || maze[ goal[ 0 ] ][ goal[ 1 ] ] == -1) {
            System.out.println("Invalid Start or End Location. Please modify the value");
            return null;
        }

        Queue<Queue<int[]>> coordinates = new LinkedList<>();
        Queue<int[]> innerQueue = new LinkedList();
        innerQueue.offer(new int[]{goal[ 0 ], goal[ 1 ]});
        coordinates.offer(innerQueue);
        maze[ goal[ 0 ] ][ goal[ 1 ] ] = 1;
        int value = 2;
        boolean found = false;
        while (!coordinates.isEmpty()) {
            Queue<int[]> queue = coordinates.poll();
            Queue<int[]> nextSet = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] coordinate = queue.poll();
                int[] left = new int[]{coordinate[ 0 ], coordinate[ 1 ] - 1};
                int[] right = new int[]{coordinate[ 0 ], coordinate[ 1 ] + 1};
                int[] up = new int[]{coordinate[ 0 ] - 1, coordinate[ 1 ]};
                int[] down = new int[]{coordinate[ 0 ] + 1, coordinate[ 1 ]};

                if (isPathAvailable(maze, left)) {
                    maze[ left[ 0 ] ][ left[ 1 ] ] = value;
                    if (left[ 0 ] == start[ 0 ] && left[ 1 ] == start[ 1 ]) {
                        found = true;
                        break;
                    }
                    nextSet.offer(left);
                }
                if (isPathAvailable(maze, right)) {
                    maze[ right[ 0 ] ][ right[ 1 ] ] = value;
                    if (right[ 0 ] == start[ 0 ] && right[ 1 ] == start[ 1 ]) {
                        found = true;
                        break;
                    }
                    nextSet.offer(right);
                }
                if (isPathAvailable(maze, up)) {
                    maze[ up[ 0 ] ][ up[ 1 ] ] = value;
                    if (up[ 0 ] == start[ 0 ] && up[ 1 ] == start[ 1 ]) {
                        found = true;
                        break;
                    }
                    nextSet.offer(up);
                }
                if (isPathAvailable(maze, down)) {
                    maze[ down[ 0 ] ][ down[ 1 ] ] = value;
                    if (down[ 0 ] == start[ 0 ] && down[ 1 ] == start[ 1 ]) {
                        found = true;
                        break;
                    }
                    nextSet.offer(down);
                }
            }
            if (found) break;
            coordinates.offer(nextSet);
            value++;
        }
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[ 0 ].length; col++) {
                System.out.print(((maze[ row ][ col ] / 10 == 0 && maze[ row ][ col ] != -1) ? " " : "") + maze[ row ][ col ] + ((maze[ row ][ col ] == -1 || (maze[ row ][ col ] / 10 > 0)) ? " " : " "));
            }
            System.out.println();
        }

        int[][] path = new int[ maze.length ][ maze[ 0 ].length ];
        path[ start[ 0 ] ][ start[ 1 ] ] = 1;
        int nextValue = maze[ start[ 0 ] ][ start[ 1 ] ] - 1;
        int[] nextCoordinate = new int[ 2 ];
        int[] currentCoordinate = new int[]{start[ 0 ], start[ 1 ]};
        while (nextValue != 0) {
            if (isWithinBounds(maze, currentCoordinate[ 0 ], currentCoordinate[ 1 ] - 1) && maze[ currentCoordinate[ 0 ] ][ currentCoordinate[ 1 ] - 1 ] == nextValue) {
                path[ currentCoordinate[ 0 ] ][ currentCoordinate[ 1 ] - 1 ] = 1;
                nextCoordinate = new int[]{currentCoordinate[ 0 ], currentCoordinate[ 1 ] - 1};
            } else if (isWithinBounds(maze, currentCoordinate[ 0 ], currentCoordinate[ 1 ] + 1) && maze[ currentCoordinate[ 0 ] ][ currentCoordinate[ 1 ] + 1 ] == nextValue) {
                path[ currentCoordinate[ 0 ] ][ currentCoordinate[ 1 ] + 1 ] = 1;
                nextCoordinate = new int[]{currentCoordinate[ 0 ], currentCoordinate[ 1 ] + 1};
            } else if (isWithinBounds(maze, currentCoordinate[ 0 ] - 1, currentCoordinate[ 1 ]) && maze[ currentCoordinate[ 0 ] - 1 ][ currentCoordinate[ 1 ] ] == nextValue) {
                path[ currentCoordinate[ 0 ] - 1 ][ currentCoordinate[ 1 ] ] = 1;
                nextCoordinate = new int[]{currentCoordinate[ 0 ] - 1, currentCoordinate[ 1 ]};
            } else if (isWithinBounds(maze, currentCoordinate[ 0 ] + 1, currentCoordinate[ 1 ]) && maze[ currentCoordinate[ 0 ] + 1 ][ currentCoordinate[ 1 ] ] == nextValue) {
                path[ currentCoordinate[ 0 ] + 1 ][ currentCoordinate[ 1 ] ] = 1;
                nextCoordinate = new int[]{currentCoordinate[ 0 ] + 1, currentCoordinate[ 1 ]};
            }
            currentCoordinate = nextCoordinate;
            nextValue--;
        }
        System.out.println("\nPath: ");
        for (int row = 0; row < path.length; row++) {
            for (int col = 0; col < path[ 0 ].length; col++) {
                System.out.print(((path[ row ][ col ] / 10 == 0 && path[ row ][ col ] != -1) ? " " : "") + path[ row ][ col ] + ((path[ row ][ col ] == -1 || (path[ row ][ col ] / 10 > 0)) ? " " : " "));
            }
            System.out.println();
        }

        return path;
    }

    private boolean isWithinBounds(int[][] maze, int row, int col) {
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[ 0 ].length) {
            return true;
        }
        return false;
    }

    private boolean isPathAvailable(int[][] maze, int[] coordinate) {
        if (coordinate[ 0 ] >= 0 && coordinate[ 0 ] < maze.length && coordinate[ 1 ] >= 0 && coordinate[ 1 ] < maze[ 0 ].length) {
            return maze[ coordinate[ 0 ] ][ coordinate[ 1 ] ] == 0;
        }
        return false;
    }
}