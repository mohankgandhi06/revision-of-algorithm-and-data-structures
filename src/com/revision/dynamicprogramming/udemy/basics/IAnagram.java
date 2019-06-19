package com.revision.dynamicprogramming.udemy.basics;

import java.util.Arrays;
import java.util.HashMap;

public class IAnagram {
    public static void main(String[] args) {
        IAnagram game = new IAnagram();
        String phrase = "elvis";
        String soCalledAnagram = "lives";
        game.solve(phrase, soCalledAnagram);

        phrase = "dormitory ";
        soCalledAnagram = "dirty room";
        game.solve(phrase, soCalledAnagram);

        phrase = "sweep the floor";
        soCalledAnagram = "too few helperr";
        game.solve(phrase, soCalledAnagram);

        phrase = "school student ";
        soCalledAnagram = "tends to slouch";
        game.solve(phrase, soCalledAnagram);

        phrase = "sweep the floor";
        soCalledAnagram = "too few helpers";
        game.solve(phrase, soCalledAnagram);

        phrase = "intensive care  ";
        soCalledAnagram = "I cant even rise";
        game.solve(phrase, soCalledAnagram);

        phrase = "intensive care  ";
        soCalledAnagram = "I can't even rise";
        game.solve(phrase, soCalledAnagram);
    }

    private void solve(String phrase, String soCalledAnagram) {
        System.out.println("Is it \"" + soCalledAnagram + "\" actually an anagram of \"" + phrase + "\" ? " + solveWithLessTimeComplexity(phrase, soCalledAnagram));
        System.out.println("Is it \"" + soCalledAnagram + "\" actually an anagram of \"" + phrase + "\" ? " + solveWithLessMemoryComplexity(phrase, soCalledAnagram));
        System.out.println();
    }

    private boolean solveWithLessTimeComplexity(String phrase, String soCalledAnagram) {
        phrase = phrase.toLowerCase();
        soCalledAnagram = soCalledAnagram.toLowerCase();
        /*System.out.println("Phrase: " + phrase);
        System.out.println("SoCalledAnagram: " + soCalledAnagram);*/
        if (phrase.length() != soCalledAnagram.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < phrase.length(); i++) {
            if (map.containsKey(phrase.charAt(i))) {
                map.put(phrase.charAt(i), map.get(phrase.charAt(i)) + 1);
            } else {
                map.put(phrase.charAt(i), 1);
            }
        }
        /*map.forEach((character, integer) -> System.out.println("Key: " + character + " Value: " + integer));*/
        for (int i = 0; i < soCalledAnagram.length(); i++) {
            if (map.containsKey(soCalledAnagram.charAt(i)) && map.get(soCalledAnagram.charAt(i)) > 0) {
                map.put(soCalledAnagram.charAt(i), map.get(soCalledAnagram.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean solveWithLessMemoryComplexity(String phrase, String soCalledAnagram) {
        if (phrase.length() != soCalledAnagram.length()) return false;
        char[] phraseArray = phrase.toLowerCase().toCharArray();
        char[] soCalledAnagramArray = soCalledAnagram.toLowerCase().toCharArray();
        Arrays.sort(phraseArray);
        Arrays.sort(soCalledAnagramArray);
        for (int i = 0; i < phrase.length(); i++) {
            if (phraseArray[ i ] != soCalledAnagramArray[ i ]) return false;
        }
        return true;
    }
}