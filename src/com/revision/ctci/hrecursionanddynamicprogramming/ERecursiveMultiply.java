package com.revision.ctci.hrecursionanddynamicprogramming;

public class ERecursiveMultiply {
    public static void main(String[] args) {
        ERecursiveMultiply game = new ERecursiveMultiply();
        int num1 = 23;
        int num2 = 24;
        System.out.println("Result: " + game.solve(num1, num2));

        num1 = 54;
        num2 = 5;
        System.out.println("Result: " + game.solve(num1, num2));
    }

    private int solve(int num1, int num2) {
        return recurse(num1, num2);
    }

    private int recurse(int num1, int num2) {
        /* 23 * 24 = ((11 * 2) + 1) * 24
        * Print : 552
        * 1: even = recurse(11, 24 * 2) [528]
        *    odd = 24
        * 2: even = recurse (5, 48 * 2) [480]
        *    odd = 48
        * 3: even = recurse (2, 96 * 2) [384]
        *    odd = 96
        * 4: (Base case) 192 * 2 [384]; */
        if (num1 == 2) return num2 << 1;
        if (num1 == 1)return num2;
        int evenPart = num1 / 2;
        int oddpart = num1 % 2;
        int even = recurse(evenPart, num2 << 1);
        int odd = oddpart == 1 ? num2 : 0;
        return even + odd;
    }
}