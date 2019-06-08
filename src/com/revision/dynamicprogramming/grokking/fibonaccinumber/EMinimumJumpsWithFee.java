package com.revision.dynamicprogramming.grokking.fibonaccinumber;

import java.util.Arrays;

public class EMinimumJumpsWithFee {
    public static void main(String[] args) {
        EMinimumJumpsWithFee game = new EMinimumJumpsWithFee();
        int noOfSteps = 6;
        int[] fees = new int[]{1, 2, 5, 2, 1, 2};
        System.out.println("No of Steps: " + noOfSteps);
        System.out.print("Array: ");
        Arrays.stream(fees).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(noOfSteps, fees));
        System.out.println("Memoization: " + game.solveWithMemoization(noOfSteps, fees));
        System.out.println("Tabulation: " + game.solveWithTabulation(noOfSteps, fees));

        noOfSteps = 4;
        fees = new int[]{2, 3, 4, 5};
        System.out.println("\nNo of Steps: " + noOfSteps);
        System.out.print("Array: ");
        Arrays.stream(fees).forEach(value -> System.out.print(value + " "));
        System.out.println("\nBrute Force: " + game.solveWithBruteForce(noOfSteps, fees));
        System.out.println("Memoization: " + game.solveWithMemoization(noOfSteps, fees));
        System.out.println("Tabulation: " + game.solveWithTabulation(noOfSteps, fees));
    }

    private int solveWithBruteForce(int noOfSteps, int[] fees) {
        return solve(noOfSteps, fees, 0, 0);
    }

    private int solve(int noOfSteps, int[] fees, int currentIndex, int fee) {
        if (currentIndex == noOfSteps) return fee;
        if (currentIndex > noOfSteps) return Integer.MAX_VALUE;
        int oneJump = solve(noOfSteps, fees, currentIndex + 1, fee + fees[ currentIndex ]);
        int twoJump = solve(noOfSteps, fees, currentIndex + 2, fee + fees[ currentIndex ]);
        int threeJump = solve(noOfSteps, fees, currentIndex + 3, fee + fees[ currentIndex ]);
        return Math.min(Math.min(oneJump, twoJump), threeJump);
    }

    private int solveWithMemoization(int noOfSteps, int[] fees) {
        Integer[] memo = new Integer[ noOfSteps + 1 ];
        return solve(noOfSteps, fees, 0, 0, memo);
    }

    private int solve(int noOfSteps, int[] fees, int currentIndex, int fee, Integer[] memo) {
        if (currentIndex == noOfSteps) return fee;
        if (currentIndex > noOfSteps) return Integer.MAX_VALUE;
        if (memo[ currentIndex ] == null) {
            int oneJump = solve(noOfSteps, fees, currentIndex + 1, fee + fees[ currentIndex ]);
            int twoJump = solve(noOfSteps, fees, currentIndex + 2, fee + fees[ currentIndex ]);
            int threeJump = solve(noOfSteps, fees, currentIndex + 3, fee + fees[ currentIndex ]);
            memo[ currentIndex ] = Math.min(Math.min(oneJump, twoJump), threeJump);
        }
        return memo[ currentIndex ];
    }

    private int solveWithTabulation(int noOfSteps, int[] fees) {
        int[] table = new int[ noOfSteps + 1 ];
        Arrays.fill(table, Integer.MAX_VALUE);
        return solve(noOfSteps, fees, table);
    }

    private int solve(int noOfSteps, int[] fees, int[] table) {
        table[ 0 ] = 0;
        table[ 1 ] = table[ 2 ] = table[ 3 ] = fees[ 0 ];
        for (int i = 3; i < noOfSteps; i++) {
            /*int iMinusOne = i >= 1 ? (((i != noOfSteps - 1) ? fees[ i ] : 0) + (table[ i - 1 ] != Integer.MAX_VALUE ? table[ i - 1 ] : 0)) : Integer.MAX_VALUE;
            int iMinusTwo = i >= 2 ? (((i != noOfSteps - 1) ? fees[ i ] : 0) + (table[ i - 2 ] != Integer.MAX_VALUE ? table[ i - 2 ] : 0)) : Integer.MAX_VALUE;
            int iMinusThree = i >= 3 ? (fees[ i ] + (table[ i - 3 ] != Integer.MAX_VALUE ? table[ i - 3 ] : 0)) : Integer.MAX_VALUE;*/
            table[ i + 1 ] = Math.min(fees[ i ] + table[ i ], Math.min(fees[ i - 1 ] + table[ i - 1 ], fees[ i - 2 ] + table[ i - 2 ]));
        }
        /*System.out.println("Answer: ");
        Arrays.stream(table).forEach(value -> System.out.print(value + " "));
        System.out.println();*/
        return table[ table.length - 1 ];
    }
}