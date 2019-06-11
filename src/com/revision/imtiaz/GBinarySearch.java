package com.revision.imtiaz;

public class GBinarySearch<E> {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{2, 3, 3, 5, 7, 8, 10, 13, 16, 18, 19, 20, 23, 25, 29, 38, 45};
        String[] names = new String[]{"Alex", "Da Vinci", "Jack", "Leonardo", "Mario", "Max", "Peter", "Steve"};
        Double[] doubles = new Double[]{1.0, 1.5, 1.8, 2.3, 2.7, 2.9, 3.3, 3.5, 3.9, 4.0, 5.2, 5.6, 5.9, 6.0, 6.2};

        GBinarySearch<Integer> integerGBinarySearch = new GBinarySearch();
        Integer integerObject = 20;
        int index = integerGBinarySearch.binarySearch(ints, integerObject);
        System.out.println(integerObject.toString() + " is found at " + index);

        GBinarySearch<String> stringGBinarySearch = new GBinarySearch();
        String stringObject = "Peter";
        index = stringGBinarySearch.binarySearch(names, stringObject);
        System.out.println(stringObject + " is found at " + index);

        GBinarySearch<Double> doubleGBinarySearch = new GBinarySearch();
        Double doubleObject = 4.0;
        index = doubleGBinarySearch.binarySearch(doubles, doubleObject);
        System.out.println(doubleObject.toString() + " is found at " + index);
    }

    private int binarySearch(E[] arr, E object) {
        return binarySearch(arr, object, 0, arr.length - 1);
    }

    private int binarySearch(E[] arr, E object, int startIndex, int endIndex) {
        if (startIndex > endIndex) return -1;
        int midIndex = (startIndex + endIndex) / 2;
        if (arr[ midIndex ].equals(object)) {
            return midIndex;
        } else if (compare(arr[ midIndex ], object) == 0) {
            System.out.println("Object comparison of this type is not available. Please contact the coder");
            return -1;
        } else if (compare(arr[ midIndex ], object) < 0) {//Current Index is less than the actual value so look to the right side
            return binarySearch(arr, object, midIndex + 1, endIndex);
        } else {
            return binarySearch(arr, object, startIndex, midIndex - 1);
        }
    }

    private int compare(E arr, E object) {
        BinaryCompare<E> binaryCompare = new BinaryCompare();
        return binaryCompare.compare(arr, object);
    }
}


class BinaryCompare<E> implements BinaryHelper<E> {
    @Override
    public int compare(E obj1, E obj2) {
        if (obj1 instanceof String) {
            return ((String) obj1).compareTo((String) obj2);
        } else if (obj1 instanceof Integer) {
            return ((Integer) obj1).compareTo((Integer) obj2);
        } else if (obj1 instanceof Double) {
            return ((Double) obj1).compareTo((Double) obj2);
        }
        return 0;
    }
}

@FunctionalInterface
interface BinaryHelper<E> {
    abstract int compare(E obj1, E obj2);
}