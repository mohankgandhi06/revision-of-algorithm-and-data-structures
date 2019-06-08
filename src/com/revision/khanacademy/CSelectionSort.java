package com.revision.khanacademy;

import java.util.Arrays;

public class CSelectionSort {
    public static void main(String[] args) {
        CSelectionSort sorter = new CSelectionSort();
        int[] array = new int[]{32, 1, 5, 4, 8, 30, 16, 19, 25, 20, 28, 5};
        sorter.sort(array);
        System.out.println();
        array = new int[]{50, 10, 54, 43, 87, 300, 166, 195, 25, 220, 258, 5};
        sorter.sort(array);
    }

    private void sort(int[] array) {
        //STEP #1: INITIALIZE
        System.out.println("Unsorted Array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        int index = 0;
        while (index != array.length) {
            //STEP #2: FIND THE MINIMUM ELEMENT INDEX
            int minIndex = index;
            for (int i = index; i < array.length; i++) {
                if (array[ i ] < array[ minIndex ]) {
                    minIndex = i;
                }
            }
            //STEP #3: SWAP
            int temp = array[ index ];
            array[ index ] = array[ minIndex ];
            array[ minIndex ] = temp;
            //STEP #4: REPEAT
            index++;
        }

        System.out.println("\nSorted Array: ");
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
        System.out.println();
    }
}