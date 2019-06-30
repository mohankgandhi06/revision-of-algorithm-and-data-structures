package com.revision.dynamicprogramming.topcoder;

import java.util.Arrays;

public class BLongestNonDecreasingSequence {
    public static void main(String[] args) {
        BLongestNonDecreasingSequence game = new BLongestNonDecreasingSequence();
        int[] arr = new int[]{5, 3, 4, 8, 6, 7};
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Longest non-decreasing sequence count: " + game.solve(arr));

        arr = new int[]{2, 3, 4, 8, 6, 7};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Longest non-decreasing sequence count: " + game.solve(arr));


        arr = new int[]{2, 3, 4, 1, 2, 4, 8, 6, 7};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Longest non-decreasing sequence count: " + game.solve(arr));

        arr = new int[]{2, 3, 4, 1, 4, 8, 6, 7};
        System.out.println();
        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("Longest non-decreasing sequence count: " + game.solve(arr));
    }

    private int solve(int[] arr) {
        int[] table = new int[ arr.length ];
        Arrays.fill(table, 1);
        for (int current = 1; current < arr.length; current++) {
            for (int previous = 0; previous < current; previous++) {
                if (arr[ previous ] < arr[ current ]) {
                    if (table[previous] + 1 > table[current]) {
                        table[ current ] = table[ previous ] + 1;
                    }
                }
            }
        }
        show(table);
        showChoices(table, arr);
        return table[ table.length - 1 ];
    }

    private void show(int[] table) {
        Arrays.stream(table).forEach(value -> System.out.print(value + " "));
        System.out.println();
    }

    private void showChoices(int[] table, int[] arr) {
        int previousIndex = table.length - 1;
        int currentIndex = previousIndex - 1;
        int count = table[ table.length - 1 ];
        while (count > 0 && currentIndex >= 0) {
            if (table[ previousIndex ] != table[ currentIndex ] && count == table[ previousIndex ]) {
                System.out.print(arr[ previousIndex ] + " ");
                count--;
            } else {
                if (count == table[ previousIndex ]) {
                    System.out.print(arr[ previousIndex ] + " ");
                    count--;
                }
            }
            previousIndex = currentIndex;
            currentIndex = previousIndex - 1;
        }
        if (count > 0) {
            System.out.println(arr[ previousIndex ]);
        }
        System.out.println();
    }
}