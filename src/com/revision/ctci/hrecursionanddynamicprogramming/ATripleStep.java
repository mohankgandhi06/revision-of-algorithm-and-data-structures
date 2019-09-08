package com.revision.ctci.hrecursionanddynamicprogramming;

public class ATripleStep {
    public static void main(String[] args) {
        ATripleStep game = new ATripleStep();
        int n = 6;
        game.solve(n);
        n = 5;
        game.solve(n);
        n = 25;
        game.solve(n);
    }

    private void solve(int remainingSteps) {
        System.out.println("Steps: " + remainingSteps);
        if (remainingSteps < 0) {
            System.out.println("Cannot perform for negative values");
            return;
        }
        System.out.println("Brute Force: ");
        System.out.println(solveWithBruteForce(remainingSteps));
        System.out.println("Memoization: ");
        Integer[] memo = new Integer[ remainingSteps + 1 ];
        memo[ 1 ] = 1;// [1] steps required to reach the goal
        memo[ 2 ] = 2;// [1,1] [2]
        memo[ 3 ] = 3;// [1,1,1] [2,1] [3]
        System.out.println(solveWithMemo(remainingSteps, memo));
        System.out.println();
    }

    private int solveWithBruteForce(int remainingSteps) {
        if (remainingSteps < 0) return -1;
        if (remainingSteps == 1) return 1;
        if (remainingSteps == 2) return 2;
        if (remainingSteps == 3) return 3;
        return solveWithBruteForce(remainingSteps - 1) + solveWithBruteForce(remainingSteps - 2) + solveWithBruteForce(remainingSteps - 3);
    }

    private int solveWithMemo(int remainingSteps, Integer[] memo) {
        if (memo[ remainingSteps ] == null) {
            memo[ remainingSteps ] = solveWithMemo(remainingSteps - 1, memo) + solveWithMemo(remainingSteps - 2, memo) + solveWithMemo(remainingSteps - 3, memo);
        }
        return memo[ remainingSteps ];
    }
}