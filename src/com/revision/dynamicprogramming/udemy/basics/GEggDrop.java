package com.revision.dynamicprogramming.udemy.basics;

import java.util.Arrays;
import java.util.Random;

public class GEggDrop {
    public static void main(String[] args) {
        GEggDrop game = new GEggDrop();
        int noOfFloors = 100;
        game.solve(noOfFloors);
    }

    private void solve(int noOfFloors) {
        Random random = new Random();
        int criticalFloor = random.nextInt(noOfFloors - 1) + 1;
        System.out.println("Critical Floor: " + criticalFloor);
        /*rand.nextInt(upperbound - lowerbound) + lowerbound*/
        int[] result = solveWithBruteForce(noOfFloors, criticalFloor);
        System.out.println("Minimum drops to find: " + result[ 0 ] + " critical floor: " + result[ 1 ]);
        result = solveUsingIteration(noOfFloors, criticalFloor);
        System.out.println("Minimum drops to find: " + result[ 0 ] + " critical floor: " + result[ 1 ]);
    }

    private int[] solveUsingIteration(int noOfFloors, int criticalFloor) {
        /* Even to make the worst case scenario to be same we have to use
         * the following logic in order to determine the exact location
         * let's consider the initial drop location as n then on the
         * next drop we take n-1 since if it breaks at this location
         * and we have already taken some drops before we need to minimize
         * the interval so that the spread is even as we go on
         * so the equation which finally comes for the floor n is that
         * n + (n-1) + (n-2) + (n-3) + ... + 1 = 100
         * n (n + 1) / 2 = 100; n^2 + n - 200 = 0 and solving this quadratic
         * equation will yield 13.67 something and rounded of to the next value 14 */
        int previousFloor = 0;
        int currentFloor = 0;
        int n = performQuadraticEquation(noOfFloors);
        System.out.println("n: " + n);
        int factor = 0;
        int dropCount = 0;
        int[] result = new int[ 2 ];
        Arrays.fill(result, -1);
        while (currentFloor < noOfFloors) {
            dropCount++;
            previousFloor = currentFloor;
            currentFloor = currentFloor + (n - factor);
            if (isBroken(currentFloor, criticalFloor)) {
                result = linearSearch(previousFloor + 1, currentFloor, dropCount, criticalFloor);
                return result;
            }
            factor++;
        }
        return result;
    }

    private boolean isBroken(int currentFloor, int criticalFloor) {
        return currentFloor > criticalFloor;
    }

    private int[] linearSearch(int previousFloor, int currentFloor, int dropCount, int criticalFloor) {
        int[] result = new int[ 2 ];
        Arrays.fill(result, -1);
        for (int i = previousFloor; i < currentFloor + 1; i++) {
            if (isBroken(i, criticalFloor)) {
                result[ 0 ] = dropCount;
                result[ 1 ] = i - 1;
                return result;
            }
            dropCount++;
        }
        return result;
    }

    private int performQuadraticEquation(int noOfFloors) {
        int a = 1;
        int b = 1;
        int c = -(2 * noOfFloors);
        double plus = -b + Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double minus = -b - Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        double positive = plus / (2 * a);
        double negative = minus / (2 * a);
        if (positive > 0) return (int) Math.ceil(positive);
        return (int) Math.ceil(negative);
    }

    private int[] solveWithBruteForce(int noOfFloors, int criticalFloor) {
        int previousFloor = 0;
        int currentFloor = 0;
        int n = performQuadraticEquation(noOfFloors);
        System.out.println("n: " + n);
        int factor = 0;
        int dropCount = 0;
        int[] result = new int[ 2 ];
        Arrays.fill(result, -1);
        return solve(noOfFloors, criticalFloor, previousFloor, currentFloor, n, factor, dropCount, result);
    }

    private int[] solve(int noOfFloors, int criticalFloor, int previousFloor, int currentFloor, int n, int factor, int dropCount, int[] result) {
        if (currentFloor > noOfFloors) {
            return linearSearch(previousFloor, currentFloor, dropCount-1, criticalFloor);
        }
        if (!isBroken(currentFloor, criticalFloor)) {
            return solve(noOfFloors, criticalFloor, currentFloor, currentFloor + (n - factor), n, factor + 1, dropCount + 1, result);
        }
        return linearSearch(previousFloor + 1, currentFloor, dropCount, criticalFloor);
    }
}