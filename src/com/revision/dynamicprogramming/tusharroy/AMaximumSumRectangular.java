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