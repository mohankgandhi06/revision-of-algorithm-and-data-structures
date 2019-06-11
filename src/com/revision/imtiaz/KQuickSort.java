package com.revision.imtiaz;

import java.util.Arrays;

public class KQuickSort<E> {
    public static void main(String[] args) {
        KQuickSort<String> stringKQuickSort = new KQuickSort<>();
        String[] arr = new String[]{"Zebra", "Imtiaz", "Jeff", "Jamie", "Tyrion", "Arya", "Jon", "Emila", "Son", "Kim", "Simon", "Raghaer"};
        System.out.println("Original Array: ");
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        stringKQuickSort.sort(arr);
        System.out.println("\nSorted Array: ");
        stringKQuickSort.show(arr);

        KQuickSort<Integer> integerKQuickSort = new KQuickSort<>();
        Integer[] integers = new Integer[]{13, 1, 8, 9, 4, 15, 20, 16, 25, 50, 36, 27, 39, 26, 40, 41, 21};
        System.out.println("\nOriginal Array: ");
        Arrays.stream(integers).forEach(s -> System.out.print(s + " "));
        integerKQuickSort.sort(integers);
        System.out.println("\nSorted Array: ");
        integerKQuickSort.show(integers);
    }

    private void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(E[] arr, int startIndex, int endIndex) {
        if (startIndex > endIndex) return;
        int pivot = arrange(arr, startIndex, endIndex);
        sort(arr, startIndex, pivot - 1);
        sort(arr, pivot + 1, endIndex);
    }

    private int arrange(E[] arr, int startIndex, int endIndex) {
        int pivot = endIndex;
        int i = startIndex;
        int j = endIndex - 1;
        while (i < pivot) {
            if (compare(arr[ i ], arr[ pivot ]) <= 0) {
                i++;
            } else {
                while (i < j) {
                    if (compare(arr[ j ], arr[ pivot ]) > 0) {
                        j--;
                    } else {
                        E temp = arr[ i ];
                        arr[ i ] = arr[ j ];
                        arr[ j ] = temp;
                        i++;
                        break;
                    }
                }
                if (i == j) {
                    E temp = arr[ i ];
                    arr[ i ] = arr[ pivot ];
                    arr[ pivot ] = temp;
                    return i;
                }
            }
        }
        return pivot;
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