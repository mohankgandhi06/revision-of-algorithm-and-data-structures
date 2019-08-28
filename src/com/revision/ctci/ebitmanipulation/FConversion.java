package com.revision.ctci.ebitmanipulation;

public class FConversion {
    public static void main(String[] args) {
        FConversion game = new FConversion();
        int a = 27;
        int b = 15;
        game.solve(a, b);
    }

    private void solve(int a, int b) {
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        int xor = a ^ b;//xor will identify the bits that are not same among both the digits
        int change = 0;
        while (xor > 0) {
            change++;
            xor = (xor & (xor - 1));//This is to remove each one from the xor
        }
        System.out.println("Changes required: " + change);
    }
}