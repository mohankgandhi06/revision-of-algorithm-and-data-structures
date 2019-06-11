package com.revision.khanacademy;

public class ERecursiveFactorial {
    public static void main(String[] args) {
        ERecursiveFactorial game = new ERecursiveFactorial();
        double factorial = 170;
        double[] memo = new double[ (int) factorial + 1 ];
        double result = game.solve(factorial, memo);
        System.out.println("Factorial of " + factorial + " is: " + result);
    }

    private double solve(double factorial, double[] memo) {
        if (memo[ (int) factorial ] != 0) return memo[ (int) factorial ];
        if (factorial == 1) return 1;
        memo[ (int) factorial ] = factorial * solve(factorial - 1, memo);
        return memo[ (int) factorial ];
    }
    /* Use this website to verify the answer */
    /* https://coolconversion.com/math/factorial/ */
    /* NOTE: This website was available at the time of creation if not google it */
}