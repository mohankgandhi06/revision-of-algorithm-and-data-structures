package com.revision.ctci.hrecursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GPermutationWithoutDuplicate {
    public static void main(String[] args) {
        GPermutationWithoutDuplicate game = new GPermutationWithoutDuplicate();
        String string = "lynda";
        game.solve(string);

        string = "vogella";
        game.solve(string);
    }

    private void solve(String string) {
        System.out.println("Permutations of " + string + ": ");
        List<String> result = permute(string);
        result.sort(Comparator.naturalOrder());
        for (String r : result) {
            System.out.println(r);
        }
        System.out.println("____________________________________");
    }

    private List<String> permute(String string) {
        if (string.length() == 1) {
            List<String> result = new ArrayList<>();
            result.add(string);
            return result;
        }
        List<String> permutations = permute(string.substring(1));
        if (permutations != null) {
            permutations = possibilities(string.charAt(0), permutations);
        }
        return permutations;
    }

    private List<String> possibilities(char current, List<String> permutations) {
        List<String> result = new ArrayList<>();
        for (String s : permutations) {
            for (int i = 0; i <= s.length(); i++) {
                result.add(s.substring(0, i) + current + s.substring(i, s.length()));
            }
        }
        return result;
    }
}