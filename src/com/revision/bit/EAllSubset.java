package com.revision.bit;

public class EAllSubset {
    public static void main(String[] args) {
        char[] arr = new char[]{'e', 'a', 'g', 'l', 'e'};
        showAllSubset(arr);
    }

    private static void showAllSubset(char[] arr) {
        int size = arr.length;
        int twoPower = (int) Math.pow(2, size);
        for (int i = 0; i < twoPower; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(arr[ j ]);
                }
            }
            System.out.println("["+subset.toString()+"]");
        }
    }
}