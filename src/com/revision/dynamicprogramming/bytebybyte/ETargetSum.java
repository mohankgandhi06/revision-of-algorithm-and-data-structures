package com.revision.dynamicprogramming.bytebybyte;

import java.util.Arrays;
import java.util.HashMap;

public class ETargetSum {
    public static void main(String[] args) {
        ETargetSum game = new ETargetSum();
        int[] numbers = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        game.solve(numbers, target);

        numbers = new int[]{2, 1, 2, 1, 2, 2, 1, 2, 3};
        target = 8;
        System.out.println();
        game.solve(numbers, target);

        numbers = new int[]{2, 1, 5, 1, 4, 2, 5, 2, 3};
        target = 15;
        System.out.println();
        game.solve(numbers, target);

        numbers = new int[]{2, 1, 2, 1, 2, 2, 6, 2, 3};
        target = 13;
        System.out.println();
        game.solve(numbers, target);
    }

    private void solve(int[] numbers, int target) {
        System.out.println("Numbers: ");
        Arrays.stream(numbers).forEach(value -> System.out.print(value + " "));
        System.out.println("\nTarget: " + target);
        System.out.println("Brute Force: " + solveWithBruteForce(numbers, target, 0));
        HashMap<String, Integer> memo = new HashMap<>();
        System.out.println("Memoization: " + solveWithMemoization(numbers, target, 0, memo));
        int total = 0;
        for (int item : numbers) {
            total += item;
        }
        int[][] table = new int[ numbers.length + 1 ][ (2 * total) + 1 ];
        table[ 0 ][ total ] = 1;
        System.out.println("Tabulation: " + solveWithTabulation(numbers, target, table, total));
        System.out.println("Byte by Byte Solution: " + targetSum(numbers, target));
    }

    private int solveWithBruteForce(int[] numbers, int target, int currentIndex) {
        if (currentIndex == numbers.length && target == 0) return 1;
        if (currentIndex == numbers.length) return 0;
        int plus = solveWithBruteForce(numbers, target + numbers[ currentIndex ], currentIndex + 1);
        int minus = solveWithBruteForce(numbers, target - numbers[ currentIndex ], currentIndex + 1);
        return plus + minus;
    }

    private int solveWithMemoization(int[] numbers, int target, int currentIndex, HashMap<String, Integer> memo) {
        if (currentIndex == numbers.length && target == 0) return 1;
        if (currentIndex == numbers.length) return 0;
        if (!memo.containsKey(String.valueOf(currentIndex).concat(String.valueOf(target)))) {
            int plus = solveWithMemoization(numbers, target + numbers[ currentIndex ], currentIndex + 1, memo);
            int minus = solveWithMemoization(numbers, target - numbers[ currentIndex ], currentIndex + 1, memo);
            memo.put(String.valueOf(currentIndex).concat(String.valueOf(target)), plus + minus);
        }
        return memo.get(String.valueOf(currentIndex).concat(String.valueOf(target)));
    }

    private int solveWithTabulation(int[] numbers, int target, int[][] table, int total) {
        for (int row = 1; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                int plus = (col - numbers[ row - 1 ] >= 0) ? table[ row - 1 ][ col - numbers[ row - 1 ] ] : 0;
                int minus = (col + numbers[ row - 1 ] < table[ 0 ].length) ? table[ row - 1 ][ col + numbers[ row - 1 ] ] : 0;
                table[ row ][ col ] = plus + minus;
            }
        }
        /*for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[ 0 ].length; col++) {
                System.out.print(table[ row ][ col ] + " ");
            }
            System.out.println();
        }*/
        return table[ table.length - 1 ][ total + target];
    }

    public int targetSum(int[] nums, int target) {
        return targetSum(nums, target, 0, 0);
    }

    private int targetSum(int[] nums, int T, int i, int sum) {
        if (i == nums.length) {
            return sum == T ? 1 : 0;
        }
        return targetSum(nums, T, i + 1, sum + nums[ i ]) + targetSum(nums, T, i + 1, sum - nums[ i ]);
    }
}