package com.revision.khanacademy;

import java.util.Arrays;

public class DInsertionSort {
    public static void main(String[] args) {
        DInsertionSort sorter = new DInsertionSort();
        int[] array = new int[]{1, 5, 4, 8};
        sorter.sort(array);
        System.out.println();
        array = new int[]{32, 1, 5, 4, 8, 30, 16, 19, 25, 20, 28, 5};
        sorter.sort(array);
        System.out.println();
        array = new int[]{50, 10, 54, 43, 87, 300, 166, 195, 25, 220, 258, 5};
        sorter.sort(array);
    }

    private void sort(int[] array) {
        System.out.println("Unsorted Array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
        int index = 1;
        while (index != array.length) {
            int pivot = array[ index ];
            int location = index;
            while (location > 0 && pivot < array[ location - 1 ]) {
                array[ location ] = array[ location - 1 ];
                location--;
            }
            array[ location ] = pivot;
            index++;
        }

        System.out.println("Sorted Array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
    }
}