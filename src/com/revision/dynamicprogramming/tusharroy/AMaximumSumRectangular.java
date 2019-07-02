package com.revision.dynamicprogramming.tusharroy;

public class AMaximumSumRectangular {
    public static void main(String[] args) {
        AMaximumSumRectangular game = new AMaximumSumRectangular();
        int[][] matrix = new int[][]{
                {2, 1, -3, -4, 5},
                {0, 6, 3, 4, 1},
                {2, -2, -1, 4, -5},
                {-3, 3, 1, 0, 3}
        };
        game.solve(matrix);

        matrix = new int[][]{
                {2, 1, -3, 4, 5},
                {0, 6, 3, 4, 1},
                {2, -2, 10, 4, 15},
                {-3, 3, 1, 1, 6}
        };
        System.out.println();
        game.solve(matrix);

        matrix = new int[][]{
                {2, 1, -3, -4, 15},
                {0, 6, -3, 4, 10},
                {2, -2, -11, 4, 15},
                {-3, 3, -1, 0, 13}
        };
        System.out.println();
        game.solve(matrix);
    }

    private void solve(int[][] matrix) {
        System.out.println("Brute Force Method: ");
        solveWithBruteForce(matrix);
        System.out.println("________________________________");
        System.out.println("Brute Force Recursive Method: ");
        solveWithBruteForceRecursive(matrix);
        System.out.println("________________________________");
        System.out.println("Memoization Method: ");
        solveWithBruteForceMemoization(matrix);
        System.out.println("________________________________");
        System.out.println("Tabulation Method: ");
        solveWithTabulation(matrix);
    }

    private void solveWithBruteForce(int[][] matrix) {
        int max = 0;
        int[] coordinates = new int[ 4 ];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                int[] result = findMaxSumSubMatrix(matrix, row, col);
                max = Math.max(max, result[ 0 ]);
                if (max == result[ 0 ]) {
                    coordinates[ 0 ] = row;
                    coordinates[ 1 ] = result[ 1 ];
                    coordinates[ 2 ] = col;
                    coordinates[ 3 ] = result[ 2 ];
                }
            }
        }

        System.out.println("Maximum Sum is " + max);
        System.out.println("Length: from " + coordinates[ 0 ] + " to " + coordinates[ 1 ]);
        System.out.println("Breadth: from " + coordinates[ 2 ] + " to " + coordinates[ 3 ]);
        System.out.println("Sub Matrix is as follows: ");
        for (int row = coordinates[ 0 ]; row <= coordinates[ 1 ]; row++) {
            for (int col = coordinates[ 2 ]; col <= coordinates[ 3 ]; col++) {
                System.out.print(matrix[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }

    private int[] findMaxSumSubMatrix(int[][] matrix, int row, int col) {
        int sum = 0;
        int xEnd = 0;
        int yEnd = 0;
        for (int r = row; r < matrix.length; r++) {
            for (int c = col; c < matrix[ 0 ].length; c++) {
                int temp = sum(matrix, row, col, r, c);
                sum = Math.max(temp, sum);
                if (temp == sum) {
                    xEnd = r;
                    yEnd = c;
                }
            }
        }
        int[] result = new int[ 3 ];
        result[ 0 ] = sum;
        result[ 1 ] = xEnd;
        result[ 2 ] = yEnd;
        return result;
    }

    private int sum(int[][] matrix, int row, int col, int r, int c) {
        int sum = 0;
        for (int vertical = row; vertical <= r; vertical++) {
            for (int horizontal = col; horizontal <= c; horizontal++) {
                sum += matrix[ vertical ][ horizontal ];
            }
        }
        return sum;
    }

    private void solveWithBruteForceRecursive(int[][] matrix) {
        int max = 0;
        int[] coordinates = new int[ 4 ];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                int[] result = findMaxSumSubMatrixRecursive(matrix, row, col, row, col);
                max = Math.max(max, result[ 0 ]);
                if (max == result[ 0 ]) {
                    coordinates[ 0 ] = row;
                    coordinates[ 1 ] = result[ 1 ];
                    coordinates[ 2 ] = col;
                    coordinates[ 3 ] = result[ 2 ];
                }
            }
        }
        show(matrix, max, coordinates);
    }

    private int[] findMaxSumSubMatrixRecursive(int[][] matrix, int row, int col, int r, int c) {
        if (r == matrix.length && c >= 0) {
            int[] result = new int[ 3 ];
            result[ 0 ] = 0;
            result[ 1 ] = 0;
            result[ 2 ] = 0;
            return result;
        }
        int[] recursiveResult = findMaxSumSubMatrixRecursive(matrix, row, col, c == matrix.length ? r + 1 : r, c == matrix.length ? col : c + 1);
        int temp = sum(matrix, row, col, r, c);
        return max(temp, recursiveResult, r, c);
    }

    private void solveWithBruteForceMemoization(int[][] matrix) {
        int max = 0;
        int[] coordinates = new int[ 4 ];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                Result[][] memo = new Result[ matrix.length ][ matrix[ 0 ].length ];
                int[] result = findMaxSumSubMatrixMemoization(matrix, row, col, row, col, memo);
                max = Math.max(max, result[ 0 ]);
                if (max == result[ 0 ]) {
                    coordinates[ 0 ] = row;
                    coordinates[ 1 ] = result[ 1 ];
                    coordinates[ 2 ] = col;
                    coordinates[ 3 ] = result[ 2 ];
                }
            }
        }
        show(matrix, max, coordinates);
    }

    private int[] findMaxSumSubMatrixMemoization(int[][] matrix, int row, int col, int r, int c, Result[][] memo) {
        if (r == matrix.length && c >= 0) {
            int[] result = new int[ 3 ];
            result[ 0 ] = 0;
            result[ 1 ] = 0;
            result[ 2 ] = 0;
            return result;
        }
        if (memo[ r ][ c ] == null) {
            int[] recursiveResult = findMaxSumSubMatrixMemoization(matrix, row, col, c == matrix.length ? r + 1 : r, c == matrix.length ? col : c + 1, memo);
            int temp = sum(matrix, row, col, r, c);
            memo[ r ][ c ] = new Result(max(temp, recursiveResult, r, c));
        }
        return memo[ r ][ c ].getArr();
    }

    private int[] max(int temp, int[] recursiveResult, int r, int c) {
        int[] result = new int[ 3 ];
        if (temp > recursiveResult[ 0 ]) {
            result[ 0 ] = temp;
            result[ 1 ] = r;
            result[ 2 ] = c;
        } else {
            result[ 0 ] = recursiveResult[ 0 ];
            result[ 1 ] = recursiveResult[ 1 ];
            result[ 2 ] = recursiveResult[ 2 ];
        }
        return result;
    }

    private void show(int[][] matrix, int max, int[] coordinates) {
        System.out.println("Maximum Sum is " + max);
        System.out.println("Length: from " + coordinates[ 0 ] + " to " + coordinates[ 1 ]);
        System.out.println("Breadth: from " + coordinates[ 2 ] + " to " + coordinates[ 3 ]);
        System.out.println("Sub Matrix is as follows: ");
        for (int row = coordinates[ 0 ]; row <= coordinates[ 1 ]; row++) {
            for (int col = coordinates[ 2 ]; col <= coordinates[ 3 ]; col++) {
                System.out.print(matrix[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }

    private void solveWithTabulation(int[][] matrix) {
        int[] arr = new int[ matrix.length ];
        int maxSum = 0;

        int lengthStart = 0;
        int lengthEnd = 0;
        int breadthStart = 0;
        int breadthEnd = 0;

        for (int l = 0; l < matrix[ 0 ].length; l++) {
            for (int r = l; r < matrix[ 0 ].length; r++) {
                if (l == r) {
                    /* RESET THE arr AND REPLACE WITH THE CURRENT COLUMNS ROW ELEMENTS*/
                    for (int row = 0; row < matrix.length; row++) {
                        arr[ row ] = matrix[ row ][ l ];
                    }
                } else {
                    /* ASSIMILATE THE TOTAL OF THE CURRENT COLUMN TO THE EXISTING arr VALUES */
                    for (int row = 0; row < matrix.length; row++) {
                        arr[ row ] += matrix[ row ][ r ];
                    }
                }
                /*Arrays.stream(arr).forEach(value -> System.out.print(value+" "));
                System.out.println();*/
                /* CALCULATE THE MAXIMUM SUM SUB ARRAY USING KADANE'S ALGORITHM AND ALLOCATE TO currentSum */
                int[] kadanesResult = kandanesAlgorithm(arr);
                if (kadanesResult[ 0 ] > maxSum) {
                    /*System.out.println("Modified********"+maxSum);*/
                    lengthStart = l;
                    lengthEnd = r;
                    breadthStart = kadanesResult[ 1 ];
                    breadthEnd = kadanesResult[ 2 ];
                    maxSum = kadanesResult[ 0 ];
                }
            }
        }
        System.out.println("Maximum Sum is " + maxSum);
        System.out.println("Length: from " + lengthStart + " to " + lengthEnd);
        System.out.println("Breadth: from " + breadthStart + " to " + breadthEnd);
        System.out.println("Sub Matrix is as follows: ");
        for (int row = breadthStart; row <= breadthEnd; row++) {
            for (int col = lengthStart; col <= lengthEnd; col++) {
                System.out.print(matrix[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }

    private int[] kandanesAlgorithm(int[] arr) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        int[] table = new int[ arr.length ];
        table[ 0 ] = arr[ 0 ];
        for (int i = 1; i < arr.length; i++) {
            table[ i ] = Math.max(arr[ i ], table[ i - 1 ] + arr[ i ]);
            max = Math.max(table[ i ], max);
            if (table[ i ] == arr[ i ]) {
                startIndex = i;
                endIndex = i;
            } else {
                endIndex++;
            }
        }
        /*System.out.println("Maximum Sub Array Sum is: " + table[ table.length - 1 ] + " spanning from " + startIndex + " till " + endIndex);
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[ i ] + " ");
        }
        System.out.println();*/
        int[] result = new int[ 3 ];
        result[ 0 ] = table[ table.length - 1 ];
        result[ 1 ] = startIndex;
        result[ 2 ] = endIndex;
        return result;
    }
}

class Result {
    private int[] arr;

    public Result(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}