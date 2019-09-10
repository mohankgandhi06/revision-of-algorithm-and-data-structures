package com.revision.ctci.hrecursionanddynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class IParenthesis {
    public static void main(String[] args) {
        IParenthesis game = new IParenthesis();
        int n = 4;
        game.solve(n);
    }

    private void solve(int n) {
        //Whenever an open parenthesis is added we append additional parenthesis to the remaining string
        StringBuilder availableCharacters = new StringBuilder();
        for (int i = 0; i < n; i++) {
            availableCharacters.append("(");
        }
        Set<String> permutations = new HashSet<>();
        solveWithBruteForce(availableCharacters, 0, new StringBuilder(), permutations);
        for (String item : permutations) {
            System.out.println(item);
        }
    }

    private void solveWithBruteForce(StringBuilder availableCharacters, int currentIndex, StringBuilder currentString, Set<String> permutations) {
        if (currentIndex == availableCharacters.length()) return;
        if (availableCharacters.length() == 1 && !availableCharacters.toString().equalsIgnoreCase("(")) {
            currentString.append(availableCharacters);
            availableCharacters.deleteCharAt(0);
            permutations.add(currentString.toString());
            return;
        }
        //STEP #1: INCLUDE
        StringBuilder temporary = new StringBuilder(currentString);
        StringBuilder tempAvailable = new StringBuilder(availableCharacters);
        Character character = tempAvailable.charAt(currentIndex);
        temporary.append(tempAvailable.charAt(currentIndex));
        tempAvailable.deleteCharAt(currentIndex);
        if (character == '(') {
            tempAvailable.append(")");
        }
        solveWithBruteForce(tempAvailable, 0, temporary, permutations);
        //STEP #2: EXCLUDE
        solveWithBruteForce(availableCharacters, currentIndex + 1, currentString, permutations);
    }
}