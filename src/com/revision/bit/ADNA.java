package com.revision.bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ADNA {
    public static void main(String[] args) {
        String sequence = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        findRepeated10Characters(sequence);
    }

    private static void findRepeated10Characters(String sequence) {
        /* It is mentioned to find the 10 characters. so we have to determine the
         * mask for extracting the 10 characters alone, and the terms A, C, G, T
         * can be represented with the help of two bits from 0 to 3
         * 00 - A
         * 01 - G
         * 10 - C
         * 11 - T
         * and so they require two bits and so we could use 20 bits
         * the mask can be created using bit shifting to 20 and then -1
         * now the logic is that we move along the string and try to extract
         * the sequence of characters in the form of mapped bits and then
         * once the 10 character limit is reached then we start extracting
         * the 20 bits (10 characters) alone and check if the value has already
         * been saved in a hash map if so then add it to a list of strings
         * */
        List<String> matchingSequence = new ArrayList<>();

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Integer mask = (1 << 20) - 1; //1 0000 0000 0000 0000 0000 - 1 = 1111 1111 1111 1111 1111
        Integer hash = 0;

        HashSet<Integer> adder = new HashSet<>();
        HashSet<Integer> forDuplicate = new HashSet<>();
        for (int i = 0; i < sequence.length(); i++) {
            hash = (hash << 2) + map.get(sequence.charAt(i)); //we are moving the previous character over to the left and then adding the current one
            if (i >= 9) {
                hash = hash & mask;//extracting the 10 characters alone
                if (forDuplicate.contains(hash) && !adder.contains(hash)) {//match has been found and it is not a duplicate
                    matchingSequence.add(sequence.substring(i - 9, i + 1));
                    adder.add(hash);
                }
                forDuplicate.add(hash);
            }
        }

        matchingSequence.stream().forEach(s -> System.out.print(s + " "));
    }
}