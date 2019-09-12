package com.revision.ctci.hrecursionanddynamicprogramming;

import java.util.HashSet;

public class HPermutationWithDuplicate {
    public static void main(String[] args) {
        HPermutationWithDuplicate game = new HPermutationWithDuplicate();
        String string = "messi";
        game.solve(string);

        string = "steven";
        game.solve(string);
    }

    private void solve(String string) {
        System.out.println("Permutations of " + string + ": ");
        HashSet<String> map = permute(string);
        for (String combination : map) {
            System.out.println(combination);
        }
        System.out.println("________________________________________________");
    }

    private HashSet<String> permute(String string) {
        if (string.length() == 1) {
            HashSet<String> map = new HashSet<>();
            map.add(string);
            return map;
        }
        HashSet<String> result = permute(string.substring(1));
        if (result != null) {
            result = combinations(string.charAt(0), result);
        }
        return result;
    }

    private HashSet<String> combinations(char character, HashSet<String> result) {
        HashSet<String> combinations = new HashSet<>();
        for (String s : result) {
            for (int i = 0; i <= s.length(); i++) {
                combinations.add(s.substring(0, i) + character + s.substring(i, s.length()));
            }
        }
        return combinations;
    }
}