package com.revision.ctci.aarraysandstrings;

import java.util.Arrays;

public class CURLify {
    public static void main(String[] args) {
        CURLify game = new CURLify();
        Character[] input = new Character[]{'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 'm', 'i', 't', 'h', ' ', ' ', ' ', ' '};
        int size = 13;
        Character[] replaceThatCharacterWith = new Character[]{'%', '2', '0'};
        game.solve(input, size, ' ', replaceThatCharacterWith);

        input = new Character[]{'D', 'r', ' ', 'S', 't', 'o', 'c', 'k', ' ', 'D', 'e', ' ', 'P', 'e', 'n', 'a', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' ',' ',' '};
        size = 17;
        replaceThatCharacterWith = new Character[]{'%', '2', '0', '#'};
        game.solve(input, size, ' ', replaceThatCharacterWith);
    }

    private void solve(Character[] input, int size, Character characterToLookFor, Character[] replaceThatCharacterWith) {
        /* THE ASSUMPTION IS THAT THE INPUT ARRAY WILL HAVE SUFFICIENT SPACE IN THE END FOR COMPLETE FILLING */
        solveInPlace(input, size - 1, characterToLookFor, replaceThatCharacterWith);
        show(input);
    }

    private void solveInPlace(Character[] input, int size, Character characterToLookFor, Character[] replaceSpaceWith) {
        int newIndex = input.length - 1;
        while (size >= 0) {
            if (input[ size ] != characterToLookFor) {
                input[ newIndex ] = input[ size ];
                newIndex--;
            } else {
                for (int i = replaceSpaceWith.length - 1; i >= 0; i--) {
                    input[ newIndex ] = replaceSpaceWith[ i ];
                    newIndex--;
                }
            }
            size--;
        }
    }

    private void show(Character[] input) {
        Arrays.stream(input).forEach(character -> System.out.print(character + " "));
        System.out.println();
    }
}