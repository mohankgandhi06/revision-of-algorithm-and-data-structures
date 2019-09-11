package com.revision.ctci.hrecursionanddynamicprogramming;

public class CMagicIndex {
    public static void main(String[] args) {
        CMagicIndex game = new CMagicIndex();
        int[] arr = new int[]{-5, -2, 1, 2, 4, 8, 10};
        System.out.println("Magic Index: " + game.solve(arr));

        arr = new int[]{-11, -7, -4, 0, 3, 4, 6, 8, 10};
        System.out.println("Magic Index: " + game.solve(arr));

        arr = new int[]{-11, -7, -4, 0, 3, 4, 7, 8, 10};
        System.out.println("Magic Index: " + game.solve(arr));

        arr = new int[]{-1, 0, 1, 2, 3, 6, 6, 6, 7, 8, 9, 10, 10, 11, 16};
        System.out.println("Magic Index: " + game.follow(arr));

        arr = new int[]{-3, -1, 1, 2, 3, 7, 7, 8, 9, 9, 11, 12, 17};
        System.out.println("Magic Index: " + game.follow(arr));

        arr = new int[]{-3, -1, 1, 2, 3, 7, 7, 8, 9, 10, 11, 12, 17};
        System.out.println("Magic Index: " + game.follow(arr));
    }

    private int solve(int[] arr) {
        return find(arr, 0, arr.length - 1);
    }

    private int find(int[] arr, int start, int end) {
        if (start > end) return -1;
        int middle = (start + end) / 2;
        if (arr[ middle ] == middle) return middle;
        if (arr[ middle ] > middle) {
            /* The element is sorted and so if the value is greater then the magic index is not going to be
             * on the greater side so just search the left side */
            return find(arr, start, middle - 1);
        } else {
            return find(arr, middle + 1, end);
        }
    }

    private int follow(int[] arr) {
        return findFollowUp(arr, 0, arr.length - 1);
    }

    private int findFollowUp(int[] arr, int start, int end) {
        if (start > end) return -1;
        int middle = (start + end) / 2;
        if (arr[ middle ] == middle) return middle;
        int left = Math.min(arr[ middle ], middle - 1);
        int l = findFollowUp(arr, start, left);
        if (l >= 0) {
            return l;
        }
        int right = Math.max(arr[ middle ], middle + 1);
        int r = findFollowUp(arr, right, end);
        if (r >= 0) {
            return r;
        }
        return -1;
    }
}