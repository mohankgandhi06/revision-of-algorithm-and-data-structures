package com.revision.dynamicprogramming.tusharroy;

import java.util.Arrays;

public class DTextJustification {
    public static void main(String[] args) {
        DTextJustification game = new DTextJustification();
        String[] words = new String[]{"Thusar", "Roy", "likes", "to", "code"};
        int[] lengths = new int[]{6, 3, 5, 2, 4};
        int width = 10;
        game.solve(words, lengths, width);

        words = new String[]{"Once", "upon", "a", "there", "was", "king", "named", "Egbert.",
                "He", "went", "for", "war", "and", "ordered", "to", "break", "the", "ship,",
                "so", "that", "everyone", "will", "have", "no", "chance", "but", "to", "fight", "and", "win"};
        lengths = new int[]{4, 4, 1, 5, 3, 4, 5, 7, 2, 4, 3, 3, 3, 7, 2, 5, 3, 5, 2, 4, 8, 4, 4, 2, 6, 3, 2, 5, 3, 3};
        width = 18;
        game.solve(words, lengths, width);
    }

    private void solve(String[] words, int[] lengths, int width) {
        System.out.println("Words: ");
        Arrays.stream(words).forEach(s -> System.out.print(s + " "));
        System.out.println("\nLine Width: " + width);
        int[][] cost = new int[ lengths.length ][ lengths.length ];
        for (int row = 0; row < cost.length; row++) {
            for (int col = row; col < cost[ 0 ].length; col++) {
                int total = 0;
                for (int wordIndex = row; wordIndex <= col; wordIndex++) {
                    total += lengths[ wordIndex ];
                }
                int spaces = col - row;
                total = total + spaces;
                if (total > width) {
                    cost[ row ][ col ] = Integer.MAX_VALUE;
                } else {
                    cost[ row ][ col ] = (int) Math.pow((width - total), 2);
                }
            }
        }

        /*System.out.println("\nCost Matrix: ");
        for (int row = 0; row < cost.length; row++) {
            for (int col = 0; col < cost[ 0 ].length; col++) {
                System.out.print((cost[ row ][ col ] == Integer.MAX_VALUE ? "*" : cost[ row ][ col ]) + " ");
            }
            System.out.println();
        }*/

        int[] bestAlignmentCost = new int[ lengths.length ];
        int[] fromToIndex = new int[ lengths.length ];
        int j = lengths.length - 1;
        for (int i = cost.length - 1; i >= 0; i--) {
            int total = 0;
            for (int wordIndex = i; wordIndex <= lengths.length - 1; wordIndex++) {
                total += lengths[ wordIndex ];
            }
            int spaces = lengths.length - 1 - i;
            total = total + spaces;
            if (total > width) {
                /* CHECK FOR SPLIT */
                int split = cost.length - 1;
                int minCost = Integer.MAX_VALUE;
                for (int index = split; index >= i; index--) {
                    if (cost[ i ][ index ] != Integer.MAX_VALUE) {
                        minCost = Math.min(minCost, cost[ i ][ index ] + bestAlignmentCost[ index + 1 ]);
                        if (minCost == cost[ i ][ index ] + bestAlignmentCost[ index + 1 ]) {
                            split = index;
                        }
                    }
                }
                bestAlignmentCost[ i ] = minCost;
                fromToIndex[ i ] = split + 1;
            } else {
                bestAlignmentCost[ i ] = cost[ i ][ lengths.length - 1 ];
                fromToIndex[ i ] = j + 1;
            }
        }

        /*System.out.println("\nBest Alignment Cost: ");
        Arrays.stream(bestAlignmentCost).forEach(value -> System.out.print(value + " "));
        System.out.println("\n\nFrom To Index: ");
        for (int i = 0; i < fromToIndex.length; i++) {
            System.out.println(i + " " + fromToIndex[ i ]);
        }*/

        System.out.println("\nJustified Text: ");
        int index = 0;
        while (index < words.length) {
            for (int wordIndex = index; wordIndex < fromToIndex[ index ]; wordIndex++) {
                System.out.print(words[ wordIndex ] + " ");
            }
            System.out.println();
            index = fromToIndex[ index ];
        }
        System.out.println("__________________________________________________");
    }
}