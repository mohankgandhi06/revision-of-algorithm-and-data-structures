package com.revision.dynamicprogramming.udemy.basics;

import java.util.Arrays;

public class JLargestSumContiguousArray {
    public static void main(String[] args) {
        JLargestSumContiguousArray game = new JLargestSumContiguousArray();
        int[] arr = new int[]{2, 3, -1, 4, 5, -4, 8, 1, -3};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        game.solve(arr);
        arr = new int[]{2, 3, -3, 4, 2, -4, 8, 1, -3};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        game.solve(arr);
        arr = new int[]{12, 3, -5, 4, 5, -4, 8, 2, -3};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        game.solve(arr);
        arr = new int[]{1, -2, 3, 4, -5, 8};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        game.solve(arr);
    }

    private void solve(int[] arr) {
        System.out.println("\nLargest Contiguous Sum Subarray: " + solve(arr, 0, 0));
        int[][] table = new int[ 2 ][ arr.length ];
        System.out.println("Largest Contiguous Sum Subarray: " + solve(arr, table)+" \n");
    }

    private int solve(int[] arr, int currentIndex, int sum) {
        if (currentIndex == arr.length) return sum;
        int include = solve(arr, currentIndex + 1, sum + arr[ currentIndex ]);
        int exclude = solve(arr, currentIndex + 1, 0);
        return Math.max(Math.max(include, exclude), sum);
    }

    private int solve(int[] arr, int[][] table) {
        table[ 0 ][ 0 ] = arr[ 0 ];
        table[ 1 ][ 0 ] = arr[ 0 ];
        for (int i = 1; i < table[ 0 ].length; i++) {
            table[ 0 ][ i ] = Math.max(arr[ i ], arr[ i ] + table[ 0 ][ i - 1 ]);
            table[ 1 ][ i ] = table[ 0 ][ i ] > table[ 1 ][ i - 1 ] ? table[ 0 ][ i ] : table[ 1 ][ i - 1 ];
        }

        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/

        return table[ table.length - 1 ][ table[ 0 ].length - 1 ];
    }
}