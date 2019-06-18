package com.revision.dynamicprogramming.udemy.basics;

import java.util.Arrays;

public class CQuickSelect {
    public static void main(String[] args) {
        CQuickSelect game = new CQuickSelect();
        int[] arr = new int[]{27, 3, 1, 6, 57, 40, 24, 26, 30, 11, 16, 10, 26, 8, 37};
        System.out.println("Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();

        int kth = 1;
        System.out.println(kth + " smallest item " + game.solve(arr, kth));
        kth = 5;
        System.out.println(kth + " smallest item " + game.solve(arr, kth));
        kth = 3;
        System.out.println(kth + " smallest item " + game.solve(arr, kth));
        kth = 7;
        System.out.println(kth + " smallest item " + game.solve(arr, kth));
        kth = 10;
        System.out.println(kth + " smallest item " + game.solve(arr, kth));

        System.out.println("\nSorted Array: ");
        Arrays.stream(arr).sorted().forEach(value -> System.out.print(value + " "));
        System.out.println();
    }

    private int solve(int[] arr, int kth) {
        if (kth <= 0) return -1;
        return solve(arr, 0, arr.length - 1, kth - 1);
    }

    private int solve(int[] arr, int start, int end, int kth) {
        if (start < end) {
            /* Partition Phase */
            int pivot = partition(arr, start, end);
            /* Recursion Phase */
            if (pivot > kth) {
                return solve(arr, start, pivot - 1, kth);
            } else if (pivot < kth) {
                return solve(arr, pivot + 1, end, kth);
            }
            return arr[ kth ];
        }
        return -1;
    }

    private int partition(int[] arr, int start, int end) {
        int i = start;
        int j = end - 1;
        while (i < end) {
            if (arr[ i ] > arr[ end ]) {
                while (i < j) {
                    if (arr[ j ] < arr[ end ]) {
                        swap(arr, i, j);
                        i++;
                        break;
                    }
                    j--;
                }
                /* SWAP AND REDUCE I AND PROCEED */
                if (i == j) {
                    swap(arr, i, end);
                    return i;
                }
            }
            i++;
        }
        return end;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[ i ];
        arr[ i ] = arr[ j ];
        arr[ j ] = temp;
    }
}