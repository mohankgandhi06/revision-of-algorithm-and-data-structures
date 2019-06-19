package com.revision.dynamicprogramming.udemy.basics;

import java.util.Arrays;

public class HDuplicateInteger {
    public static void main(String[] args) {
        HDuplicateInteger game = new HDuplicateInteger();
        int[] arr = new int[]{2, 3, 1, 2, 1, 4, 5, 4, 6, 7, 10, 7, 7};
        System.out.println("Array: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nDuplicates: ");
        game.solve(arr);

        arr = new int[]{2, 3, 1, 2, 1, 4, 5, 4, 6, 9, 10, 7, 8};
        System.out.println("\n\nArray: ");
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println("\nDuplicates: ");
        game.solve(arr);
    }

    private void solve(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[ Math.abs(arr[ i ]) ] > 0) {
                arr[ Math.abs(arr[ i ]) ] = -arr[ Math.abs(arr[ i ]) ];
            } else {
                System.out.print(Math.abs(arr[ i ]) + " ");
            }
        }
    }
}