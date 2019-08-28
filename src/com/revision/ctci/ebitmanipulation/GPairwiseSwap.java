package com.revision.ctci.ebitmanipulation;

public class GPairwiseSwap {
    public static void main(String[] args) {
        GPairwiseSwap game = new GPairwiseSwap();
        int number = Integer.parseInt("1101101110001", 2);
        System.out.println(game.solve(number));
    }

    private int solve(int number) {
        int oddInt = 0x55555555;  //01010101010101010101010101010101
        int evenInt = 0xaaaaaaaa; //10101010101010101010101010101010
        return ((number & oddInt) << 1) | ((number & evenInt) >>> 1);
    }
}