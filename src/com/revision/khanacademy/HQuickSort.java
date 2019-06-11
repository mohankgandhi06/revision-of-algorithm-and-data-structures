package com.revision.khanacademy;

import java.util.Arrays;

public class HQuickSort {
    public static void main(String[] args) {
        System.out.println("Quick Sort");
        HQuickSort game = new HQuickSort();
        int[] array = new int[]{34, 27, 31, 13, 16, 78, 28, 40, 5, 2, 10, 67, 5, 17, 25, 30, 50, 48, 55, 58, 72};
        System.out.println("Unsorted array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
        game.sort(array);
        System.out.println("Sorted array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();

        array = new int[]{40, 10, 1, 5, 60, 12, 23, 25, 3, 17, 29, 30, 50, 46, 48, 42};
        System.out.println("\nUnsorted array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
        game.sort(array);
        System.out.println("Sorted array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
    }

    private void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(int[] array, int startIndex, int endIndex) {
        /* BASE CASE */
        if (startIndex > endIndex) return;
        int pivot = array[ endIndex ];
        int i = startIndex;
        int j = endIndex - 1;
        while (i <= endIndex) {
            if (array[ i ] >= pivot) {
                while (j > i) {
                    if (array[ j ] < pivot) {
                        swap(array, i, j);
                        i++;
                        break;
                    }
                    j--;
                }
                if (i == j) {
                    swap(array, i, endIndex);
                    break;
                }
            }
            i++;
        }
        if (i >= endIndex) {//The pivot is in proper position only sort the left side
            sort(array, startIndex, endIndex - 1);
        } else {
            sort(array, startIndex, i - 1);
            sort(array, i + 1, endIndex);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    }
}