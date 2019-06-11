package com.revision.khanacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BGuessingGame {
    public static void main(String[] args) {
        BGuessingGame game = new BGuessingGame();
        int[] valueRange = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 14, 15, 16, 17, 18, 25, 26, 28, 29, 31, 35, 37, 39, 41, 44, 48, 50};
        System.out.println("Game #1");
        int answer = 15;
        try {
            game.guess(valueRange, answer);
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
        }

        System.out.println("\nGame #2");
        answer = 41;
        try {
            game.guess(valueRange, answer);
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
        }
    }

    private void guess(int[] valueRange, int answer) throws IOException {
        //STEP #1: INITIALIZE
        List<Integer> guessMade = new LinkedList<>();
        int noOfGuesses = 0;
        int guess = -1;
        int startIndex = 0;
        int endIndex = valueRange.length - 1;
        int answerIndex = findIndex(valueRange, answer);
        //STEP #2: KEEP GUESSING UNTIL FOUND
        while (guess != answer) {
            print(valueRange, startIndex, endIndex);
            System.out.println("Make a Guess:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            guess = Integer.valueOf(reader.readLine());
            noOfGuesses++;
            guessMade.add(guess);
            int index = findIndex(valueRange, guess);
            if (index == answerIndex) {
                System.out.println("You have got it right!!! : " + guess);
                break;
            }
            System.out.println("Your guess is wrong : " + guess);
            if (index > answerIndex && index != -1) {
                endIndex = index - 1;
            } else if (index < answerIndex && index != -1) {
                startIndex = index + 1;
            }
        }
        //STEP #3: SHOW RESULT
        System.out.println();
        System.out.println("You have made " + noOfGuesses + " many guesses");
        for (int g : guessMade) {
            System.out.print(g + " ");
        }
        System.out.println();
    }

    private int findIndex(int[] valueRange, int value) {
        return find(valueRange, value, 0, valueRange.length - 1);
    }

    private int find(int[] valueRange, int value, int startIndex, int endIndex) {
        if (startIndex > endIndex) return -1;
        int guessIndex = (startIndex + endIndex) / 2;
        if (valueRange[ guessIndex ] == value) {
            return guessIndex;
        } else if (valueRange[ guessIndex ] > value) {
            return find(valueRange, value, startIndex, guessIndex - 1);
        } else {
            return find(valueRange, value, guessIndex + 1, endIndex);
        }
    }

    private void print(int[] valueRange, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(valueRange[ i ] + " ");
        }
        System.out.println();
    }
}