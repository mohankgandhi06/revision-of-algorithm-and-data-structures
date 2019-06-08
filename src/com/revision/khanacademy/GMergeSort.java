package com.revision.khanacademy;

import java.util.Arrays;

public class GMergeSort {
    public static void main(String[] args) {
        GMergeSort game = new GMergeSort();
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
        /* STEP #1: SPLIT IT INTO SMALLER ARRAY'S SUCH THAT IT CANNOT BE SPLIT FURTHER. i.e. 1 */
        /* ONCE SPLIT IS ACHIEVED COMBINE TWO ARRAY BY ADDING THE MAX ELEMENT TO EITHER ARRAY'S
         * END AND THEN USE WHILE LOOP FOR MOVING FROM LEFT TO RIGHT ON BOTH THE ARRAY'S UNTIL
         * WE REACH THE MAX VALUE AT WHICH POINT THE ARRAY WILL BE SORTED */
        splitAndMerge(array, 0, array.length - 1);
    }

    private void splitAndMerge(int[] array, int startIndex, int endIndex) {
        if (startIndex - endIndex == 0) return;
        int midIndex = (startIndex + endIndex) / 2;
        splitAndMerge(array, startIndex, midIndex);
        splitAndMerge(array, midIndex + 1, endIndex);
        merge(array, startIndex, midIndex, midIndex + 1, endIndex);
    }

    private void merge(int[] array, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int[] leftArray = Arrays.copyOfRange(array, leftStart, leftEnd + 1);
        int[] rightArray = Arrays.copyOfRange(array, rightStart, rightEnd + 1);
        int left = 0;
        int right = 0;
        int arrayIndex = leftStart;
        while (left < leftArray.length && right < rightArray.length) {
            if (leftArray[ left ] <= rightArray[ right ]) {
                array[ arrayIndex ] = leftArray[ left ];
                arrayIndex++;
                left++;
            } else {
                array[ arrayIndex ] = rightArray[ right ];
                arrayIndex++;
                right++;
            }
        }
        if (left > leftArray.length) {//Copy all the rest of right to the remaining positions
            while (right < rightArray.length) {
                array[ arrayIndex ] = rightArray[ right ];
                arrayIndex++;
                right++;
            }
        } else {//Copy all the rest of the left to the remaining positions
            while (left < leftArray.length) {
                array[ arrayIndex ] = leftArray[ left ];
                arrayIndex++;
                left++;
            }
        }
    }
}