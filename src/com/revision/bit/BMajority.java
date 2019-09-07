package com.revision.bit;

public class BMajority {
    public static void main(String[] args) {
        BMajority majority = new BMajority();
        int[] arr = new int[]{1, 3, 2, 2, 2, 2, 3, 3, 2, 1, 3};
        majority.findMajorityWithoutBitManipulation(arr);
        majority.findMajorityWithBitManipulation(arr);
        arr = new int[]{5, 5, 15, 15, 11, 11, 11};
        majority.findMajorityWithoutBitManipulation(arr);
        majority.findMajorityWithBitManipulation(arr);
    }

    private void findMajorityWithoutBitManipulation(int[] arr) {
        int element = potentialMajority(arr);
        if (isMajority(arr, element)) {
            System.out.println("Element with the majority: " + element);
        } else {
            System.out.println("No Majority");
        }
    }

    private int potentialMajority(int[] arr) {
        /* We are identifying the potential candidate for the majority
         * by taking a majority index and checking at each index whether
         * it can be a maximum at that position. We could also make it easier
         * by sorting and then iterating but it will make it BigO(n log n) complexity
         * but the current one is BigO(n). When we find a element losing its majority
         * count to 0 then we are replacing it with the next potential one
         * (i.e., next one in the line) */
        int majorityIndex = 0;
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[ majorityIndex ] == arr[ i ]) count++;
            else count--;
            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }
        return arr[ majorityIndex ];
    }

    private boolean isMajority(int[] arr, int element) {
        int count = 0;
        int nBy2 = arr.length / 2;
        for (int e : arr) {
            if (e == element) count++;
            if (count > nBy2) return true;
        }
        return false;
    }

    private void findMajorityWithBitManipulation(int[] arr) {
        /* Count the set bits of a corresponding digit across all
         * the elements and check if it is a majority then set the bit
         * otherwise leave it as 0. finally return if the number is actually
         * existing in the arr
         * BigO (ln) [l - length of the integer 32 bits, n - no of elements]*/
        int nBy2 = arr.length / 2;
        int number = 0;
        int mask = 1;
        int count;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int j = 0; j < arr.length; j++)
                if ((mask & arr[ j ]) != 0) count++;
            if (count > nBy2) number |= mask;
            mask <<= 1;
        }
        if (isMajority(arr, number)) {
            System.out.println("Element with the majority: " + number);
        } else {
            System.out.println("No Majority");
        }
    }
}