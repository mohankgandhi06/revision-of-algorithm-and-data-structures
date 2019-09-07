package com.revision.ctci.ebitmanipulation;

public class CFlipBitToWin {
    public static void main(String[] args) {
        CFlipBitToWin game = new CFlipBitToWin();
        int num = 0b11011101111;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b11010111101;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b11111111;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b00000000;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b1001000111;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b101111110;
        System.out.println("Longest Sequence: " + game.longest(num));

        num = 0b1111000111;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b10001111101;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b1100111001;
        System.out.println("Longest Sequence: " + game.longest(num));

        num = 0b110011110110;
        System.out.println("Longest Sequence: " + game.longest(num));
        num = 0b111011110110;
        System.out.println("Longest Sequence: " + game.longest(num));
    }

    private int longest(int num) {
        System.out.println(Integer.toBinaryString(num));
        if (num < 0) return -1;
        int max = 0;
        int[] current = new int[ 3 ];
        int[] previous = new int[ 3 ];
        int side = 0;//side can be either 0 or 2;
        int mask = 1;
        while (num != 0) {
            if ((num & mask) == 0 && current[ 1 ] == 0) {
                current[ 1 ] = 1;
                if (side == 0) side = 2;
                else side = 0;

            } else if ((num & mask) == 0) {
                //Calculate Max
                max = Math.max(max, current[ 0 ] + current[ 1 ] + current[ 2 ]);
                previous = current;
                current[ 0 ] = previous[ 2 ];
                current[ 1 ] = 1;
                current[ 2 ] = 0;
                if (side == 2) side = 2;
                else side = 0;
            } else {
                current[ side ]++;
            }
            num >>= 1;
        }
        if (current[ 1 ] == 0) {
            current[ 1 ] = 1;
        }
        max = Math.max(max, current[ 0 ] + current[ 1 ] + current[ 2 ]);
        return max;
    }
}