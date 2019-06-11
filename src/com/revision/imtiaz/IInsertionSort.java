package com.revision.imtiaz;

import java.util.Arrays;

public class IInsertionSort<E> {
    public static void main(String[] args) {
        IInsertionSort<String> stringIInsertionSort = new IInsertionSort<>();
        String[] arr = new String[]{"Tim", "Imtiaz", "Jeff", "Jamie", "Tyrion", "Arya", "Jon", "Emila", "Son", "Kim", "Simon", "Raghaer"};
        System.out.println("Original Array: ");
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        stringIInsertionSort.sort(arr);
        System.out.println("\nSorted Array: ");
        stringIInsertionSort.show(arr);

        IInsertionSort<Integer> integerIInsertionSort = new IInsertionSort<>();
        Integer[] integers = new Integer[]{13, 1, 8, 9, 4, 15, 20, 16, 25, 50, 36, 27, 39, 26, 40, 41, 21};
        System.out.println("\nOriginal Array: ");
        Arrays.stream(integers).forEach(s -> System.out.print(s + " "));
        integerIInsertionSort.sort(integers);
        System.out.println("\nSorted Array: ");
        integerIInsertionSort.show(integers);
    }

    private void sort(E[] arr) {
        int index = 1;
        while (index != arr.length) {
            int in = -1;
            E current = arr[ index ];
            for (int i = index-1; i >=0; i--) {
                int compare = compare(current, arr[ i ]);
                if (compare == -100) {
                    System.out.println("Uncomparable data type. Please contact the coder");
                    return;
                } else if (compare > 0) {
                    break;
                } else {
                    arr[ i + 1 ] = arr[ i ];
                    in = i;
                }
            }
            if (in != -1) {
                arr[ in ] = current;
            }
            index++;
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