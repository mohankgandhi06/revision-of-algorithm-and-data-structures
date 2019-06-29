package com.revision.dynamicprogramming.tusharroy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BWordBreak {

    HashMap<String, String> dictionary = new HashMap<>();

    public BWordBreak() {
        buildDictionary(dictionary, Arrays.asList("I", "AM", "ACE", "STAR", "BORN", "MAGIC", "IS", "NATURE", "PIG", "HAMLET", "MONEY", "REST", "SKILL", "SONG", "MUSIC", "A"));
    }

    public static void main(String[] args) {
        BWordBreak game = new BWordBreak();
        String word = "IAMACE";
        game.solve(word);

        word = "IAMACESSTAR";
        System.out.println();
        game.solve(word);

        word = "MAGICISNATURE";
        System.out.println();
        game.solve(word);

        word = "MAGICISNARTURE";
        System.out.println();
        game.solve(word);

        word = "MAGICISANATURE";
        System.out.println();
        game.solve(word);

        word = "MAGICISRNATURE";
        System.out.println();
        game.solve(word);


    }

    private void solve(String word) {
        System.out.println("Can the entire characters be used to form a phrase? \n" + word);
        System.out.print(wordSplit(word)?"Yes it can be split meaningfully":"No it can't be done");
        System.out.println();
    }

    private boolean wordSplit(String word) {
        boolean[][] table = new boolean[ word.length() ][ word.length() ];
        for (int position = 0; position < table.length; position++) {
            if (this.dictionary.containsValue(String.valueOf(word.charAt(position)))) {
                table[ position ][ position ] = true;
            }
        }
        for (int row = table.length - 1; row >= 0; row--) {
            for (int col = row + 1; col < table[ 0 ].length; col++) {
                String result = word.substring(row, col + 1);
                /*System.out.println(result);*/
                if (this.dictionary.containsValue(result)) {
                    /*System.out.println("Whole Word Found");*/
                    table[ row ][ col ] = true;
                } else {
                    for (int splitAt = row + 1; splitAt <= col; splitAt++) {
                        /*System.out.println(row + " - " + (splitAt - 1));
                        System.out.println(splitAt + " - " + (col));*/
                        if (table[ row ][ splitAt - 1 ] && table[ splitAt ][ col ]) {
                            /*System.out.println("TRUE");*/
                            table[ row ][ col ] = true;
                            break;
                        }
                    }
                }
            }
        }
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] ? "T " : "F ");
            }
            System.out.println();
        }
        return table[ 0 ][ table[ 0 ].length - 1 ];
    }

    private void buildDictionary(HashMap<String, String> dictionary, List<String> words) {
        for (String word : words) {
            dictionary.put(word, word);
        }
    }
}