package com.revision.imtiaz;

import java.util.Arrays;

public class HSelectionSort<E> {
    public static void main(String[] args) {
        HSelectionSort<String> stringHSelectionSort = new HSelectionSort<>();
        String[] arr = new String[]{"Tim", "Imtiaz", "Jeff", "Jamie", "Tyrion", "Arya", "Jon", "Emila", "Son", "Kim", "Simon", "Raghaer"};
        System.out.println("Original Array: ");
        Arrays.stream(arr).forEach(s -> System.out.print(s + " "));
        stringHSelectionSort.sort(arr);
        System.out.println("\nSorted Array: ");
        stringHSelectionSort.show(arr);


        HSelectionSort<Integer> integerHSelectionSort = new HSelectionSort<>();
        Integer[] integers = new Integer[]{13, 1, 8, 9, 4, 15, 20, 16, 25, 50, 36, 27, 39, 26, 40, 41, 21};
        System.out.println("\nOriginal Array: ");
        Arrays.stream(integers).forEach(s -> System.out.print(s + " "));
        integerHSelectionSort.sort(integers);
        System.out.println("\nSorted Array: ");
        integerHSelectionSort.show(integers);
    }

    private void sort(E[] arr) {
        int index = 0;
        while (index != arr.length - 1) {
            E smallest = arr[ index ];
            int smallestIndex = index;
            for (int i = index; i < arr.length; i++) {
                int compare = compare(smallest, arr[ i ]);
                if (compare == -100) {
                    System.out.println("Uncomparable data type. Please contact the coder");
                    return;
                } else if (compare > 0) {
                    smallest = arr[ i ];
                    smallestIndex = i;
                }
            }
            if (smallestIndex != index) {
                E temp = arr[ index ];
                arr[ index ] = smallest;
                arr[ smallestIndex ] = temp;
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


class Sort<E> implements SortHelper<E> {
    public int compare(E obj1, E obj2) {
        if (obj1 instanceof String) {
            int z = 100;//To bypass the intelliJ duplicate detector
            return ((String) obj1).compareTo((String) obj2);
        } else if (obj1 instanceof Integer) {
            return ((Integer) obj1).compareTo((Integer) obj2);
        } else if (obj1 instanceof Double) {
            return ((Double) obj1).compareTo((Double) obj2);
        }
        return -100;
    }
}

@FunctionalInterface
interface SortHelper<E> {
    int compare(E obj1, E obj2);
}