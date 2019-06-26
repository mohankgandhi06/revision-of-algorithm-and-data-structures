package com.revision.dynamicprogramming.udemy.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FBinPacking {
    public static void main(String[] args) {
        FBinPacking game = new FBinPacking();
        List<Integer> pieces = Arrays.asList(4, 2, 7, 5, 6, 2);
        int binCapacity = 8;
        pieces.stream().forEach(integer -> System.out.print(integer+" "));
        System.out.println();
        System.out.println("\nMinimum Bins Required: " + game.solve(pieces, binCapacity));
    }

    private int solve(List<Integer> pieces, int binCapacity) {
        pieces.sort(Comparator.reverseOrder());
        /*for (int item : pieces) {
            System.out.print(item + " ");
        }
        System.out.println();*/
        if (pieces.get(0) > binCapacity) {
            System.out.println("No Solution Possible..");
            return 0;
        }
        List<Bin> bins = new ArrayList<>(pieces.size());
        bins.add(new Bin(0, binCapacity));
        for (int i = 0; i < pieces.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < bins.size(); j++) {
                if (pieces.get(i) <= bins.get(j).size) {
                    bins.get(j).pieces.add(pieces.get(i));
                    bins.get(j).size -= pieces.get(i);
                    flag = true;
                    break;
                }
            }
            /* Add the new bin and insert the one */
            if (!flag) {
                int newIndex = bins.size();
                bins.add(new Bin(newIndex, binCapacity));
                bins.get(newIndex).pieces.add(pieces.get(i));
                bins.get(newIndex).size -= pieces.get(i);
            }
        }
        show(bins);
        return bins.size();
    }

    private void show(List<Bin> bins) {
        System.out.println("Arrangement of the pieces in the bin is as below");
        for (Bin item : bins) {
            System.out.println("Bin ID: " + item.id);
            System.out.println("Unused Capacity: " + item.size);
            System.out.println("Pieces: ");
            for (Integer piece : item.pieces) {
                System.out.print(piece + " ");
            }
            System.out.println();
        }
    }
}

class Bin {
    public int id;
    public int size;
    public int capacity;
    public List<Integer> pieces;

    public Bin(int id, int size) {
        this.id = id;
        this.size = size;
        this.capacity = size;
        this.pieces = new ArrayList<>();
    }
}