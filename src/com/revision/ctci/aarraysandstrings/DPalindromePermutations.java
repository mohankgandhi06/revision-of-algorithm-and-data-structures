package com.revision.ctci.aarraysandstrings;

public class DPalindromePermutations {
    public static void main(String[] args) {
        DPalindromePermutations game = new DPalindromePermutations();
        String input = "Tact Coa";
        game.solve(input);

        input = "Tacta";
        game.solve(input);

        input = "Tact doa";
        game.solve(input);

        input = "Tact dcoa";
        game.solve(input);

        input = "Tact dcoda";
        game.solve(input);

        input = "Tact dca";
        game.solve(input);
    }

    private void solve(String input) {
        int[] characters = new int[ 26 ];
        System.out.println("With Array: " + solveWithArray(input, characters));
    }

    private boolean solveWithArray(String input, int[] characters) {
        input = input.toLowerCase();
        System.out.println(input);
        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) != ' ') {
                int i = input.charAt(index) - 'a';
                characters[ i ] += 1;
            }
        }
        int oddCharacter = 0;
        for (int i = 0; i < characters.length; i++) {
            /*System.out.println((char) ('a' + i) + ": " + characters[ i ]);*/
            if (characters[ i ] % 2 == 1) {
                oddCharacter++;
                if (oddCharacter > 1) return false;
            }
        }
        return true;
    }
}