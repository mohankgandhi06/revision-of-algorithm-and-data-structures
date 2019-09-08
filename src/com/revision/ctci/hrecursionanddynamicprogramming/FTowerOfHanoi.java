package com.revision.ctci.hrecursionanddynamicprogramming;

public class FTowerOfHanoi {
    public static void main(String[] args) {
        FTowerOfHanoi game = new FTowerOfHanoi();
        int disks = 5;
        game.solve(disks);

        disks = 3;
        game.solve(disks);

        disks = 4;
        game.solve(disks);
    }

    private void solve(int disks) {
        System.out.println("Disks Involved: " + disks);
        solveWithBruteForce(disks, 'a', 'c', 'b');
        System.out.println();
    }

    private void solveWithBruteForce(int remainingDisks, char from, char to, char middle) {
        if (remainingDisks == 1) {
            System.out.println("Moving Plate 1 from: " + from + " to: " + to);
            return;
        }
        solveWithBruteForce(remainingDisks - 1, from, middle, to);
        System.out.println("Moving Plate " + remainingDisks + " from: " + from + " to: " + to);
        solveWithBruteForce(remainingDisks - 1, middle, to, from);
    }
}