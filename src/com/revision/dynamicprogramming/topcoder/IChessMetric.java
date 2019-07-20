package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;

public class IChessMetric {

    int[][] movements = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1},
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };

    public static void main(String[] args) {
        IChessMetric game = new IChessMetric();
        int size = 8;
        long[][] board = new long[ size ][ size ];
        int[] start = new int[]{3, 4};
        int[] end = new int[]{7, 7};
        int totalMoves = 4;
        game.solve(board, start, end, totalMoves);

        size = 3;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{1, 0};
        totalMoves = 1;
        System.out.println();
        game.solve(board, start, end, totalMoves);

        size = 3;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{1, 2};
        totalMoves = 1;
        System.out.println();
        game.solve(board, start, end, totalMoves);

        size = 3;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{2, 2};
        totalMoves = 1;
        System.out.println();
        game.solve(board, start, end, totalMoves);

        size = 3;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{2, 2};
        totalMoves = 2;
        System.out.println();
        game.solve(board, start, end, totalMoves);

        size = 50;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{0, 49};
        totalMoves = 26;
        System.out.println();
        game.solve(board, start, end, totalMoves);

        size = 100;
        board = new long[ size ][ size ];
        start = new int[]{0, 0};
        end = new int[]{0, 99};
        totalMoves = 50;
        System.out.println();
        game.solve(board, start, end, totalMoves);
    }

    private void solve(long[][] board, int[] start, int[] end, int totalMoves) {
        System.out.println("Start: ");
        Arrays.stream(start).forEach(value -> System.out.print(value + " "));
        System.out.println("\nEnd: ");
        Arrays.stream(end).forEach(value -> System.out.print(value + " "));
        System.out.println("\nNo. of Moves: " + totalMoves);
        this.solveWithTabulationWithLargeSize(board, start, end, totalMoves);
        /*this.solveWithTabulation(board, start, end, totalMoves, 0);*/
        /*System.out.println("Final Board");*/
        /*this.show(board);*/
    }

    /*private void solveWithTabulation(long[][] board, int[] start, int[] end, int totalMoves, long count) {
        int[][] locations = new int[ board.length * board.length * board.length* board.length ][ 2 ];
        int[][] subsequentLocations = new int[ board.length * board.length * board.length* board.length ][ 2 ];
        Set<String> duplicateLocationFinder = new HashSet<>();
        for (int[] item : locations) {
            Arrays.fill(item, -1);
        }
        locations[ 0 ][ 0 ] = start[ 0 ];
        locations[ 0 ][ 1 ] = start[ 1 ];
        for (int move = 1; move <= totalMoves; move++) {
            if (move == 25) {
                System.out.println("Move: " + 25);
            }
            for (int[] item : subsequentLocations) {
                Arrays.fill(item, -1);
            }
            int index = 0;
            for (int[] location : locations) {
                if (location[ 0 ] != -1 && location[ 1 ] != -1) {
                    for (int[] item : this.movements) {
                        int x = location[ 0 ] + item[ 0 ];
                        int y = location[ 1 ] + item[ 1 ];
                        if (x >= 0 && x < board.length && y >= 0 && y < board[ 0 ].length) {
                            if (duplicateLocationFinder.add(String.valueOf(x).concat(String.valueOf(y)))) {
                                subsequentLocations[ index ][ 0 ] = x;
                                subsequentLocations[ index ][ 1 ] = y;
                                index++;
                            }
                            if (x == end[ 0 ] && y == end[ 1 ]) count++;
                        }
                    }
                } else {
                    break;
                }
            }
            offer(locations, subsequentLocations);
            duplicateLocationFinder.clear();
        }
        System.out.println("No of ways we can reach the goal: " + count);
    }

    private void offer(int[][] locations, int[][] subsequentLocations) {
        for (int row = 0; row < locations.length; row++) {
            for (int col = 0; col < locations[ 0 ].length; col++) {
                locations[ row ][ col ] = subsequentLocations[ row ][ col ];
            }
        }
    }*/

    private void solveWithTabulationWithLargeSize(long[][] board, int[] start, int[] end, int totalMoves) {
        board[ start[ 0 ] ][ start[ 1 ] ] = 1;
        for (int move = 0; move < totalMoves; move++) {
            /* COPY CURRENT VALUE TO A TEMPORARY */
            long[][] temporary = new long[ board.length ][ board[ 0 ].length ];
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[ 0 ].length; col++) {
                    temporary[ row ][ col ] = board[ row ][ col ];
                }
            }
            /* MAKE EVERYTHING AS ZERO */
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[ 0 ].length; col++) {
                    board[ row ][ col ] = 0;
                }
            }
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[ 0 ].length; col++) {
                    for (int[] item : this.movements) {
                        int x = row + item[ 0 ];
                        int y = col + item[ 1 ];
                        if (x >= 0 && x < board.length && y >= 0 && y < board[ 0 ].length) {
                            board[ x ][ y ] += temporary[ row ][ col ];
                            //Movement made from current position row, col to x, y
                            // So the new position will have new plus the old positions count
                        }
                    }
                }
            }
        }
        System.out.println("No of ways we can reach the goal: " + board[ end[ 0 ] ][ end[ 1 ] ]);
    }

    private void show(long[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[ 0 ].length; col++) {
                System.out.print(board[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}