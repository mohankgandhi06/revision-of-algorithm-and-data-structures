package com.revision.bit;

public class CSingleNumber {
    public static void main(String[] args) {
        CSingleNumber odd = new CSingleNumber();
        int[] arr = new int[]{1, 3, 5, 6, 7, 2, 1, 9, 3, 6, 2, 7, 5, 2, 5, 7, 3, 1, 6};
        System.out.println(odd.findOddOneOutWithBitCount(arr));
        System.out.println(odd.findOddOneOutZerosAndOnes(arr));
    }

    private int findOddOneOutWithBitCount(int[] arr) {
        /* BigO (ln) [l - length of the integer 32 bits, n - no of elements] */
        int count = 0;
        int odd = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int number : arr) {
                if ((number & mask) != 0) count++;
            }
            if (count % 3 != 0) odd |= mask;
            mask <<= 1;
        }
        return odd;
    }

    private int findOddOneOutZerosAndOnes(int[] arr) {
        /* Ones: No of 1's is one 1, 4, 7
         * Twos: No of 1's is two 2, 5, 8
         * Threes: 3,6,9 should be removed
         * STEP #1: The logic is to determine first if there is any even digits which is determined
         * by taking the Ones and then perform and operation with the current value of the
         * array to get the digits that are having even count and then or it with the Twos
         * Now there can be situation were we already have a Twos bit set and adding to it
         * will make it 3 so we need to remove it. It will be done at the end
         *
         * STEP #2: Now we have transferred the no of set bits to even we will turn to Ones which
         * are currently needing to be set. Now for this if we take the xor it will *not flip
         * the bits that were passed on to the Twos integer and also set the bits that are
         * to be set newly. In this case as well we would need to remove the set bits that
         * were transferred and this too will be done at the end
         *
         * STEP #3: Now to determine the three we will first and the determine the bits that are common
         * and inverse the mask to remove those 3 bits from both Ones and Twos */
        int ones = 0;
        int twos = 0;
        int mask = 0;
        for (int element : arr) {
            twos = twos | (ones & element);//STEP #1:
            ones = ones ^ element;
            mask = ~(ones & twos);
            ones = ones & mask;
            twos = twos & mask;
        }
        return ones | twos;
    }
}