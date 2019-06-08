package com.revision.imtiaz;

public class FLinearSearch {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{5, 3, 7, 3, 8, 2, 10, 13, 18, 16, 19, 20, 45, 38, 25, 29, 23};
        String[] names = new String[]{"Alex", "Steve", "Max", "Jack", "Peter", "Leonardo", "Da Vinci", "Mario"};
        Double[] doubles = new Double[]{3.5, 6.0, 3.3, 1.0, 1.8, 1.5, 2.3, 2.7, 2.9, 3.9, 4.0, 5.2, 5.6, 5.9, 6.2};
        FLinearSearch search = new FLinearSearch();
        Object object = "Peter";
        int index = search.linearSearch(names, object);
        System.out.println(object.toString() + " is found at " + index);

        object = 18;
        index = search.linearSearch(ints, object);
        System.out.println(object.toString() + " is found at " + index);

        object = 5.3;
        index = search.linearSearch(doubles, object);
        System.out.println(object.toString() + " is found at " + index);
    }

    private int linearSearch(Object[] arr, Object object) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[ i ].equals(object)) {
                return i;
            }
        }
        return -1;
    }
}