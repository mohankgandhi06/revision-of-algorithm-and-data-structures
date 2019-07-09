package com.revision.dynamicprogramming.tusharroy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DTextJustification {
    public static void main(String[] args) {
        DTextJustification game = new DTextJustification();
        String[] words = new String[]{"Thusar", "Roy", "likes", "to", "code"};
        int[] lengths = new int[]{6, 3, 5, 2, 4};
        int width = 10;
        game.solve(words, lengths, width);

        /*words = new String[]{"Once", "upon", "a", "there", "was", "king", "named", "Egbert.",
                "He", "went", "for", "war", "and", "ordered", "to", "break", "the", "ship,",
                "so", "that", "everyone", "will", "have", "no", "chance", "but", "to", "fight", "and", "win"};
        lengths = new int[]{4, 4, 1, 5, 3, 4, 5, 7, 2, 4, 3, 3, 3, 7, 2, 5, 3, 5, 2, 4, 8, 4, 4, 2, 6, 3, 2, 5, 3, 3};
        width = 18;
        game.solve(words, lengths, width);*/

        words = new String[]{"Baby", "Driver", "is", "a", "movie", "about", "a", "boy", "who", "met", "with", "an", "accident"};
        lengths = new int[]{4, 6, 2, 1, 5, 5, 1, 3, 3, 3, 4, 2, 8};
        width = 8;
        game.solve(words, lengths, width);

        words = new String[]{"Baby", "Driver", "is", "a", "movie", "about", "a", "boy", "who", "met", "with", "an", "accident", "He", "drives"};
        lengths = new int[]{4, 6, 2, 1, 5, 5, 1, 3, 3, 3, 4, 2, 8, 2, 6};
        width = 10;
        game.solve(words, lengths, width);

        words = new String[]{"Baby", "Driver", "is", "a", "movie", "about", "a", "boy", "who", "met", "with", "an", "accident", "He", "drives"};
        lengths = new int[]{4, 6, 2, 1, 5, 5, 1, 3, 3, 3, 4, 2, 8, 2, 6};
        width = 14;
        game.solve(words, lengths, width);
    }

    private void solve(String[] words, int[] lengths, int width) {
        System.out.println("Words: ");
        Arrays.stream(words).forEach(s -> System.out.print(s + " "));
        System.out.println("\nLine Width: " + width);
        /* BRUTE FORCE */
        List<Line> lines = new ArrayList<>();
        lines.add(new Line());
        lines = solveWithBruteForce(words, lengths, width, 0, lines, 0, width);
        System.out.println("\nBrute Force: \nJustified Text: ");
        show(lines, words);

        /* MEMOIZATION */
        HashMap<String, List<Line>> memo = new HashMap<>();
        lines = new ArrayList<>();
        lines.add(new Line());
        lines = solveWithMemoization(words, lengths, width, 0, lines, 0, width, memo);
        System.out.println("\nMemoization: \nJustified Text: ");
        show(lines, words);

        /* TABULATION */
        System.out.println("\nTabulation: ");
        solveWithTabulation(words, lengths, width);
    }

    private void show(List<Line> lines, String[] words) {
        for (Line line : lines) {
            for (int index : line.words) {
                System.out.print(words[ index ] + " ");
            }
            System.out.println();
        }
    }

    private List<Line> solveWithBruteForce(String[] words, int[] lengths, int width, int currentIndex, List<Line> lines, int currentLine, int currentLineCapacity) {
        if (currentLine == lengths.length && currentIndex < lengths.length) return lines;
        if (currentIndex == lengths.length) return lines;
        List<Line> include = new ArrayList<>();
        List<Line> exclude = new ArrayList<>();
        if (lengths[ currentIndex ] <= currentLineCapacity) {
            copy(include, lines);
            include.get(currentLine).words.add(currentIndex);
            include = solveWithBruteForce(words, lengths, width, currentIndex + 1, include, currentLine, lengths[ currentIndex ] == currentLineCapacity ? currentLineCapacity - lengths[ currentIndex ] : currentLineCapacity - lengths[ currentIndex ] - 1);
        }
        copy(exclude, lines);
        exclude.add(new Line());
        if (currentIndex < lengths.length) {
            exclude.get(currentLine + 1).words.add(currentIndex);
            exclude = solveWithBruteForce(words, lengths, width, currentIndex + 1, exclude, currentLine + 1, lengths[ currentIndex ] == width ? width - lengths[ currentIndex ] : width - lengths[ currentIndex ] - 1);
        } else {
            exclude = solveWithBruteForce(words, lengths, width, currentIndex, exclude, currentLine + 1, width);
        }
        return minimumBad(include, exclude, lengths, width);
    }

    private List<Line> solveWithMemoization(String[] words, int[] lengths, int width, int currentIndex, List<Line> lines, int currentLine, int currentLineCapacity, HashMap<String, List<Line>> memo) {
        if (currentLine == lengths.length && currentIndex < lengths.length) return lines;
        if (currentIndex == lengths.length) return lines;
        String key = frameKey(currentIndex, lines, currentLine, currentLineCapacity);
        if (!memo.containsKey(key)) {
            List<Line> include = new ArrayList<>();
            List<Line> exclude = new ArrayList<>();
            if (lengths[ currentIndex ] <= currentLineCapacity) {
                copy(include, lines);
                include.get(currentLine).words.add(currentIndex);
                include = solveWithMemoization(words, lengths, width, currentIndex + 1, include, currentLine, lengths[ currentIndex ] == currentLineCapacity ? currentLineCapacity - lengths[ currentIndex ] : currentLineCapacity - lengths[ currentIndex ] - 1, memo);
            }
            copy(exclude, lines);
            exclude.add(new Line());
            if (currentIndex < lengths.length) {
                exclude.get(currentLine + 1).words.add(currentIndex);
                exclude = solveWithMemoization(words, lengths, width, currentIndex + 1, exclude, currentLine + 1, lengths[ currentIndex ] == width ? width - lengths[ currentIndex ] : width - lengths[ currentIndex ] - 1, memo);
            } else {
                exclude = solveWithMemoization(words, lengths, width, currentIndex, exclude, currentLine + 1, width, memo);
            }
            memo.put(key, minimumBad(include, exclude, lengths, width));
        }
        return memo.get(key);
    }

    private String frameKey(int currentIndex, List<Line> lines, int currentLine, int currentLineCapacity) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < lines.size(); index++) {
            stringBuilder.append(index);
            for (int item : lines.get(index).words) {
                stringBuilder.append(item);
            }
        }
        return Integer.toString(currentIndex).concat(stringBuilder.toString()).concat(Integer.toString(currentLine)).concat(Integer.toString(currentLineCapacity));
    }

    private void copy(List<Line> include, List<Line> lines) {
        for (Line line : lines) {
            Line newLine = new Line(line);
            include.add(newLine);
        }
    }

    private List<Line> minimumBad(List<Line> include, List<Line> exclude, int[] lengths, int width) {
        if (include != null && exclude != null) {
            int includeBadness = include.size() == 0 ? Integer.MAX_VALUE : 0;
            int excludeBadness = exclude.size() == 0 ? Integer.MAX_VALUE : 0;
            for (int includeRow = 0; includeRow < include.size(); includeRow++) {
                int currentLength = 0;
                for (int index : include.get(includeRow).words) {
                    currentLength += lengths[ index ];
                }
                currentLength = currentLength + include.get(includeRow).words.size();
                includeBadness += Math.pow(width - currentLength, 3);
            }
            for (int excludeRow = 0; excludeRow < exclude.size(); excludeRow++) {
                int currentLength = 0;
                for (int index : exclude.get(excludeRow).words) {
                    currentLength += lengths[ index ];
                }
                currentLength = currentLength + exclude.get(excludeRow).words.size();
                excludeBadness += Math.pow(width - currentLength, 3);
            }
            if (includeBadness > excludeBadness) return exclude;
            return include;
        } else if (include == null) {
            return exclude;
        }
        return include;
    }

    private void solveWithTabulation(String[] words, int[] lengths, int width) {
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

        System.out.println("Justified Text: ");
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

class Line {
    public List<Integer> words;

    public Line() {
        this.words = new ArrayList<>();
    }

    public Line(Line line) {
        this.words = new ArrayList<>();
        for (Integer item : line.words) {
            this.words.add(item);
        }
    }
}