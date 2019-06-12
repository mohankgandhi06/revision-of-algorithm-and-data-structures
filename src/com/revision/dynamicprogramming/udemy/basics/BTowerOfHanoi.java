package com.revision.dynamicprogramming.udemy.basics;

public class BTowerOfHanoi {
    public static void main(String[] args) {
        BTowerOfHanoi game = new BTowerOfHanoi();
        int plates = 10;
        game.solve(plates);
    }

    private void solve(int plates) {
        solve(plates, 'a', 'b', 'c');
    }

    private void solve(int plates, char fromPlate, char middle, char toPlate) {
        if (plates == 1) {
            System.out.println("Moving 1 from " + fromPlate + " to " + toPlate);
            return;
        }
        solve(plates - 1, fromPlate, toPlate, middle);
        System.out.println("Moving " + plates + " from " + fromPlate + " to " + toPlate);
        solve(plates - 1, middle, fromPlate, toPlate);
    }
}