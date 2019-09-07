package com.revision.bit;

public class DMaxProductOfWordLength {
    public static void main(String[] args) {
        DMaxProductOfWordLength maxProduct = new DMaxProductOfWordLength();
        String[] arr = new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(maxProduct.solve(arr));
        arr = new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        System.out.println(maxProduct.solve(arr));
        arr = new String[]{"a", "aa", "aaa", "aaaa"};
        System.out.println(maxProduct.solve(arr));
    }

    private int solve(String[] arr) {
        int[] size = new int[ arr.length ];
        int[] characterBit = new int[ arr.length ];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            size[ i ] = arr[ i ].length();
            characterBit[ i ] = setBits(arr[ i ]);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((characterBit[ i ] & characterBit[ j ]) == 0) {
                    max = Math.max(max, size[ i ] * size[ j ]);
                }
            }
        }
        return max;
    }

    private int setBits(String word) {
        /* Integer has 32 bits and we can use that to set the character bits */
        int wordInInt = 0;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            wordInInt |= 1 << index;
        }
        return wordInInt;
    }
}