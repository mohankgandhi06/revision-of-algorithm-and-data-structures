package com.revision.ctci.hrecursionanddynamicprogramming;

import java.util.ArrayList;

public class DPowerSet {
    public static void main(String[] args) {
        DPowerSet game = new DPowerSet();
        char[] set = new char[]{'e', 'x', 'a', 'm'};
        game.solve(set);

        set = new char[]{'e', 'a', 'g', 'l', 'e'};
        game.solve(set);

        set = new char[]{'r', 'a', 'n', 'g', 'e', 'r'};
        game.solve(set);
    }

    private void solve(char[] set) {
        ArrayList<ArrayList<Character>> powerset = new ArrayList<>();
        ArrayList<Character> subset = new ArrayList<>(set.length);
        System.out.print("Set: ");
        for (char c : set) {
            System.out.print(c + " ");
        }
        System.out.println();
        powerSet(set, 0, powerset, subset);
        show(powerset);
        System.out.println();
    }

    private void powerSet(char[] set, int currentIndex, ArrayList<ArrayList<Character>> powerset, ArrayList<Character> subset) {
        if (currentIndex == set.length) {
            powerset.add(subset);
            return;
        }
        //STEP #1: Include the current
        ArrayList<Character> copy = copy(subset);
        copy.add(set[ currentIndex ]);
        powerSet(set, currentIndex + 1, powerset, copy);

        //STEP #2: Exclude the current
        powerSet(set, currentIndex + 1, powerset, subset);
    }

    private ArrayList<Character> copy(ArrayList<Character> subset) {
        ArrayList<Character> copy = new ArrayList<>(subset.size());
        copy.addAll(subset);
        return copy;
    }

    private void show(ArrayList<ArrayList<Character>> powerset) {
        System.out.println("Power Set: ");
        for (ArrayList<Character> subset : powerset) {
            System.out.print("[");
            for (Character c : subset) {
                System.out.print(c + " ");
            }
            System.out.print("]");
            System.out.println();
        }
    }
}