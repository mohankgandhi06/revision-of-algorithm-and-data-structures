package com.revision.ctci.aarraysandstrings;

import java.util.Arrays;
import java.util.HashMap;

public class BCheckPermutations {
    public static void main(String[] args) {
        BCheckPermutations game = new BCheckPermutations();
        String first = "abcde";
        String second = "bdeac";
        game.solve(first, second);

        first = "abcfasfaede";
        second = "bdeafeafdda";
        System.out.println();
        game.solve(first, second);

        first = "abcfasfaede";
        second = "bdeaeafscaf";
        System.out.println();
        game.solve(first, second);

        first = "abcfasfaede";
        second = "bdeafeafsca";
        System.out.println();
        game.solve(first, second);

        first = "abcfasfaede";
        second = "bdeaeascaf";
        System.out.println();
        game.solve(first, second);
    }

    private void solve(String first, String second) {
        System.out.println("First: " + first + " Second: " + second);
        /* FOR A WORD TO BE A PERMUTATION IT HAS TO CONTAIN ALL THE CHARACTER THE OTHER AND NO MORE */
        if (first.length() != second.length()) {
            System.out.println("I am sure that, it is not a permutation as the length differs");
            return;
        }
        /* Best Conceivable Runtime: cannot be better than BigO(n) as we need to iterate through one populate
         * the HashMap and then iterate the second one till we reach the end.
         * Hash Map: Time is BigO(n) and CONSUMES SPACE
         * Sort and compare: Time is BigO(n log n) and Space 1 */
        System.out.println("Is First a Permutation a permutation of Second? ");
        System.out.println("Hash Map: " + solveWithHashMap(first, second));
        System.out.println("Sort and compare: " + solveAfterSort(first, second));
    }

    private boolean solveWithHashMap(String first, String second) {
        HashMap<Character, Integer> hashMap = new HashMap<>(first.length());
        for (int i = 0; i < first.length(); i++) {
            Character character = first.charAt(i);
            if (hashMap.containsKey(character)) {
                hashMap.put(character, hashMap.get(character) + 1);
            } else {
                hashMap.put(character, 1);
            }
        }
        /* FOR DEBUG PURPOSE */
        /*hashMap.forEach((character, integer) -> System.out.println(character + " " + integer));*/
        for (int i = 0; i < second.length(); i++) {
            Character character = second.charAt(i);
            if (!hashMap.containsKey(character) || hashMap.get(character) == 0) return false;
            hashMap.put(character, hashMap.get(character) - 1);
        }
        return true;
    }

    private boolean solveAfterSort(String first, String second) {
        String firstSorted = sort(first);
        String secondSorted = sort(second);
        for (int i = 0; i < firstSorted.length(); i++) {
            if (firstSorted.charAt(i) != secondSorted.charAt(i)) return false;
        }
        return true;
    }

    private String sort(String input) {
        int x = 0;//To Skip IntelliJ duplicate code check
        char[] arr = input.toCharArray();
        Arrays.sort(arr);//Dual Pivot Sort being implemented BigO(n log n)
        StringBuilder output = new StringBuilder(input.length());//Setting the initial size so that resizing again is not required
        for (char item : arr) output.append(item);
        return output.toString();
    }
}