package com.revision.ctci.ebitmanipulation;

public class AInsertion {
    public static void main(String[] args) {
        AInsertion game = new AInsertion();
        String mInStringFormat = "10011";
        String nInStringFormat = "1000000000";
        int m = Integer.parseInt(mInStringFormat, 2);
        int n = Integer.parseInt(nInStringFormat, 2);
        int i = 2;
        int j = 6;
        game.insert(m, n, i, j);
    }

    private void insert(int m, int n, int i, int j) {
        /* Insert M into N such that I and J represent the start and end index of the position */
        int mask = ((~((1 << (j - 1)) - 1)) + 1 << i) - 1;
        System.out.println("N:      " + Integer.toBinaryString(n));
        System.out.println("M:      " + Integer.toBinaryString(m));
        System.out.println("I:      " + i);
        System.out.println("J:      " + j);
        //Debug Purpose
        System.out.println("Mask:   " + Integer.toBinaryString(mask));
        int interOne = (mask & n);
        int result = (interOne | m << i);
        System.out.println("Insert: "+Integer.toBinaryString(result));
    }
}