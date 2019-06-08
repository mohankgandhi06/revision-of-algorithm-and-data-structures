package com.revision.imtiaz;

import java.util.Arrays;

public class JMergeSort<E> {
    public static void main(String[] args) {
        JMergeSort<String> stringJMergeSort = new JMergeSort<>();
        String[] arr = new String[]{"Tim", "Imtiaz", "Jeff", "Jamie", "Tyrion", "Arya", "Jon", "Emila", "Son", "Kim", "Simon", "Raghaer"};
        System.out.println("Original Array: ");
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        stringJMergeSort.sort(arr);
        System.out.println("\nSorted Array: ");
        stringJMergeSort.show(arr);

        JMergeSort<Integer> integerJMergeSort = new JMergeSort<>();
        Integer[] integers = new Integer[]{13, 1, 8, 9, 4, 15, 20, 16, 25, 50, 36, 27, 39, 26, 40, 41, 21};
        System.out.println("\nOriginal Array: ");
        Arrays.stream(integers).forEach(s -> System.out.print(s + " "));
        integerJMergeSort.sort(integers);
        System.out.println("\nSorted Array: ");
        integerJMergeSort.show(integers);
    }

    private void sort(E[] arr) {
        /* SPLIT until (Base Case) only one element is left out */
        /* MERGE while merging copy two array for left and right and perform the sort in the original array */
        sort(arr, 0, arr.length - 1);
    }

    private void sort(E[] arr, int startIndex, int endIndex) {
        if (endIndex - startIndex == 0) return;
        int midIndex = (startIndex + endIndex) / 2;
        sort(arr, startIndex, midIndex);
        sort(arr, midIndex + 1, endIndex);
        merge(arr, startIndex, midIndex, midIndex + 1, endIndex);
    }

    private void merge(E[] arr, int leftStartIndex, int leftEndIndex, int rightStartIndex, int rightEndIndex) {
        E[] leftArray = Arrays.copyOfRange(arr, leftStartIndex, leftEndIndex + 1);
        E[] rightArray = Arrays.copyOfRange(arr, rightStartIndex, rightEndIndex + 1);
        int i = 0;
        int j = 0;
        int index = leftStartIndex;
        while (i != leftArray.length && j != rightArray.length) {
            if (compare(leftArray[ i ], rightArray[ j ]) > 0) {
                /* Left array is greater */
                arr[ index ] = rightArray[ j ];
                j++;
                index++;
            } else {
                arr[ index ] = leftArray[ i ];
                i++;
                index++;
            }
        }
        if (i == leftArray.length) {
            /* Copy the elements of the right to the rest of the position */
            while (j < rightArray.length) {
                arr[ index ] = rightArray[ j ];
                j++;
                index++;
            }
        } else {
            /* Copy the elements of the left to the rest of the position */
            while (i < leftArray.length) {
                arr[ index ] = leftArray[ i ];
                i++;
                index++;
            }
        }
    }

    private void show(E[] arr) {
        System.out.println("List: ");
        for (E item : arr) {
            System.out.println(item);
        }
        System.out.println("____________________________");
    }

    private int compare(E obj1, E obj2) {
        Sort<E> sortHelper = new Sort<>();
        return sortHelper.compare(obj1, obj2);
    }
}