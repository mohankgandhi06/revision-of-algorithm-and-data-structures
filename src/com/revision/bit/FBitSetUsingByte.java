package com.revision.bit;

import java.util.ArrayList;
import java.util.List;

public class FBitSetUsingByte {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(64);
        bitSet.setBit(7);
        bitSet.setBit(5);
        bitSet.setBit(16);
        bitSet.setBit(25);
        bitSet.setBit(30);
        bitSet.setBit(35);
        bitSet.setBit(41);
        bitSet.setBit(43);
        bitSet.showBinaryRepresentation();
        bitSet.showSetBits();
    }
}

class BitSet {
    private int bits;
    private int bitSetSize;
    private byte[] bitSet;

    BitSet(int bits) {
        this.bits = bits;
        this.bitSetSize = bits / 8;
        this.bitSet = new byte[ bitSetSize ];
    }

    void setBit(int number) {
        int indexPosition = number / 8;
        int bitPosition = number % 8;
        this.bitSet[ indexPosition ] = (byte) (this.bitSet[ indexPosition ] | (1 << bitPosition));
    }

    void showSetBits() {
        System.out.println("\nSet Bits:");
        List<Integer> setBits = new ArrayList<>();
        for (int index = 0; index < this.bitSet.length; index++) {
            int ind = 7;
            byte temp = this.bitSet[ index ];
            byte b = (byte) (1 << 7);
            while (temp != 0) {
                if ((temp & b) != 0) {
                    setBits.add(ind + (8 * index));
                }
                ind--;
                temp <<= 1;
            }
        }
        setBits.stream().sorted().forEach(integer -> System.out.print(integer + " "));
    }

    void showBinaryRepresentation() {
        System.out.println("Binary Representation: ");
        for (byte b : this.bitSet) {
            System.out.println(b);
        }
    }

    /* Getters and Setters */
    /*public int getBits() {
        return bits;
    }

    public int getBitSetSize() {
        return bitSetSize;
    }

    public byte[] getBitSet() {
        return bitSet;
    }*/
}