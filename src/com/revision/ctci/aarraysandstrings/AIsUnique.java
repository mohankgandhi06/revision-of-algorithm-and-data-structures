package com.revision.ctci.aarraysandstrings;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class AIsUnique {
    public static void main(String[] args) {
        AIsUnique game = new AIsUnique();
        String input = "abcde";
        game.solve(input);

        input = "abcdea";
        game.solve(input);

        input = "abcade";
        game.solve(input);

        input = "a?bc,cde";
        game.solve(input);

        input = "abcdedsdfhfgj";
        game.solve(input);

        input = "kgyhjnsvfet";
        game.solve(input);

        input = "kgyhjn/\"svfet";
        game.solve(input);

        input = "kgyhj?bc,nsvfet";
        game.solve(input);

        input = "kgyhj?bc,nsv?fet";
        game.solve(input);
    }

    private void solve(String input) {
        /* USING DATA STRUCTURES */
        /* Time complexity Best Conceivable Runtime: we cannot do any better than the n, since in worst case we need to go through every character in the string
         * Using HashMap: Time is BigO(n), Space is 1
         * Using Bit Vector: Time is BigO(n), Space is 1
         * Using Sorting: Time is BigO(n log n) Space is */
        /* #1 HASHMAP */
        Map<Character, Integer> alphabets = new HashMap<>();
        System.out.println("Are the letters in '" + input + "' unique? ");
        System.out.println("Hash Map: " + solveWithHashMap(input, alphabets));
        /* #2 BITVECTOR */
        BitSet vector = new BitSet(256);
        System.out.println("Bit Vector: " + solveWithBitVector(input, vector));
        /* WITHOUT USING DATA STRUCTURES */
        System.out.println("Sort and confirm: " + solveAfterSorting(input));
        System.out.println();
    }

    private boolean solveWithHashMap(String input, Map<Character, Integer> alphabets) {
        for (int i = 0; i < input.length(); i++) {
            if (alphabets.containsKey(input.charAt(i))) return false;
            alphabets.put(input.charAt(i), 1);
        }
        return true;
    }

    private boolean solveWithBitVector(String input, BitSet vector) {
        for (int i = 0; i < input.length(); i++) {
            if (vector.get(input.charAt(i))) return false;
            vector.set(input.charAt(i), true);
        }
        return true;
    }

    private boolean solveAfterSorting(String input) {
        String sorted = sort(input);
        for (int i = 0; i < sorted.length() - 1; i++) {
            if (sorted.charAt(i) == sorted.charAt(i + 1)) return false;
        }
        return true;
    }

    private String sort(String input) {
        char[] arr = input.toCharArray();
        Arrays.sort(arr);//Dual Pivot Sort being implemented BigO(n log n)
        StringBuilder output = new StringBuilder(input.length());//Setting the initial size so that resizing again is not required
        for (char item : arr) output.append(item);
        return output.toString();
    }
}