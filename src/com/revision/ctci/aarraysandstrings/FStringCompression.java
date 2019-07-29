package com.revision.ctci.aarraysandstrings;

public class FStringCompression {
    public static void main(String[] args) {
        FStringCompression game = new FStringCompression();
        System.out.println("NOTE: After compression if the size of the compressed is greater than or equal to the original then the original is returned instead");
        System.out.println("______________________________________________________________________________________________________________________________________");
        String input = "aabcccccaaa";
        game.solve(input);

        input = "aabca";
        System.out.println();
        game.solve(input);

        input = "aabccccc";
        System.out.println();
        game.solve(input);

        input = "aabbbbccaaa";
        System.out.println();
        game.solve(input);

        input = "aabbaaa";
        System.out.println();
        game.solve(input);

        input = "aabaaa";
        System.out.println();
        game.solve(input);
    }

    private void solve(String input) {
        System.out.println("Input: " + input);
        input = input.toLowerCase();
        System.out.println("With Comparison: " + solveWithComparison(input));
    }

    private String solveWithComparison(String input) {
        StringBuilder result = new StringBuilder();
        char currentCharacter = input.charAt(0);
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != currentCharacter) {
                result.append(currentCharacter).append(count);
                currentCharacter = input.charAt(i);
                count = 1;
                continue;
            }
            count++;
        }
        result.append(currentCharacter).append(count);
        return result.length() >= input.length() ? input : result.toString();
    }
}