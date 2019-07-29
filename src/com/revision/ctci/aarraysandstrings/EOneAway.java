package com.revision.ctci.aarraysandstrings;

public class EOneAway {
    public static void main(String[] args) {
        EOneAway game = new EOneAway();
        String stringOne = "pale";
        String stringTwo = "ple";
        game.solve(stringOne, stringTwo);

        stringOne = "pales";
        stringTwo = "pale";
        System.out.println();
        game.solve(stringOne, stringTwo);

        stringOne = "pale";
        stringTwo = "bale";
        System.out.println();
        game.solve(stringOne, stringTwo);

        stringOne = "pale";
        stringTwo = "bake";
        System.out.println();
        game.solve(stringOne, stringTwo);
    }

    private void solve(String stringOne, String stringTwo) {
        System.out.println("String One: " + stringOne + " String Two: " + stringTwo);
        System.out.println("is String One one edit away from becoming String Two? ");
        System.out.println("Normal Check: " + solveWithNormalCheck(stringOne, stringTwo));
    }

    private boolean solveWithNormalCheck(String stringOne, String stringTwo) {
        //Cannot be one edit away if there are more than one character difference
        if (Math.abs(stringOne.length() - stringTwo.length()) > 1)
            return false;
        //#1 insert a character
        //#2 remove a character
        if (Math.abs(stringOne.length() - stringTwo.length()) == 1)
            return checkForInsertionOrRemoval(stringOne, stringTwo);
        //#3 replace a character
        return checkForReplace(stringOne, stringTwo);
    }

    private boolean checkForInsertionOrRemoval(String stringOne, String stringTwo) {
        String larger = stringOne.length() > stringTwo.length() ? stringOne : stringTwo;
        String smaller = stringOne.length() > stringTwo.length() ? stringTwo : stringOne;
        int largerIndex = 0;
        int smallerIndex = 0;
        boolean alreadyChanged = false;
        while (smallerIndex < smaller.length()) {
            if (larger.charAt(largerIndex) == smaller.charAt(smallerIndex)) {
                smallerIndex++;
            } else {
                if (alreadyChanged) return false;
                alreadyChanged = true;
            }
            largerIndex++;
        }
        return true;
    }

    private boolean checkForReplace(String stringOne, String stringTwo) {
        boolean alreadyChanged = false;
        for (int i = 0; i < stringOne.length(); i++) {
            if (stringOne.charAt(i) != stringTwo.charAt(i) && alreadyChanged) return false;
            if (stringOne.charAt(i) != stringTwo.charAt(i)) alreadyChanged = true;
        }
        return true;
    }
}