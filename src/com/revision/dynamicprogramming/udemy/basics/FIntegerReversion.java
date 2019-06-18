package com.revision.dynamicprogramming.udemy.basics;

public class FIntegerReversion {
    public static void main(String[] args) {
        FIntegerReversion game = new FIntegerReversion();
        int number = 1234;
        System.out.println("Reversion of " + number + " gives " + game.solve(number));
    }

    private int solve(int number) {
        int reverse = 0;
        while (number > 0) {
            reverse = (number % 10) + reverse *10;
            number /= 10;
        }
        return reverse;
    }
}