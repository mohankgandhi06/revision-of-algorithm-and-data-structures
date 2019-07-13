package com.revision.dynamicprogramming.topcoder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FFlowerGarden {
    public static void main(String[] args) {
        FFlowerGarden game = new FFlowerGarden();
        int[] heights = new int[]{5, 4, 3, 2, 1};
        int[] blooms = new int[]{1, 1, 1, 1, 1};
        int[] wilts = new int[]{365, 365, 365, 365, 365};
        game.solve(heights, blooms, wilts);

        heights = new int[]{5, 4, 3, 2, 1};
        blooms = new int[]{1, 5, 10, 15, 20};
        wilts = new int[]{4, 9, 14, 19, 24};
        System.out.println();
        game.solve(heights, blooms, wilts);

        heights = new int[]{5, 4, 3, 2, 1};
        blooms = new int[]{1, 5, 10, 15, 20};
        wilts = new int[]{5, 10, 15, 20, 25};
        System.out.println();
        game.solve(heights, blooms, wilts);

        heights = new int[]{5, 4, 3, 2, 1};
        blooms = new int[]{1, 5, 10, 15, 20};
        wilts = new int[]{5, 10, 14, 20, 25};
        System.out.println();
        game.solve(heights, blooms, wilts);

        heights = new int[]{1, 2, 3, 4, 5, 6};
        blooms = new int[]{1, 3, 1, 3, 1, 3};
        wilts = new int[]{2, 4, 2, 4, 2, 4};
        System.out.println();
        game.solve(heights, blooms, wilts);

        heights = new int[]{3, 2, 5, 4};
        blooms = new int[]{1, 2, 11, 10};
        wilts = new int[]{4, 3, 12, 13};
        System.out.println();
        game.solve(heights, blooms, wilts);
    }

    private void solve(int[] heights, int[] blooms, int[] wilts) {
        List<Flower> flowers = process(heights, blooms, wilts);
        flowers.sort(Comparator.comparing(Flower::getHeight).reversed());
        show(flowers, "Sort");
        System.out.println("Brute Force: ");
        List<Flower> plantingOrder = new ArrayList<>();
        plantingOrder.add(flowers.get(0));
        solveWithBruteForce(flowers, 1, plantingOrder);
        /*System.out.println("Order: ");*/
        show(plantingOrder, "Order");

        System.out.println("Memoization: ");
        HashMap<String, List<Flower>> memo = new HashMap<>();
        plantingOrder = new ArrayList<>();
        plantingOrder.add(flowers.get(0));
        solveWithMemoization(flowers, 1, plantingOrder, memo);
        show(plantingOrder, "Order");

        System.out.println("Tabulation: ");
        solveWithTabulation(flowers);
        show(plantingOrder, "Order");
    }

    private List<Flower> solveWithBruteForce(List<Flower> flowers, int currentIndex, List<Flower> plantingOrder) {
        if (currentIndex == flowers.size()) return plantingOrder;
        int i = 0;
        while (i < currentIndex && plantingOrder.get(i) != null) {
            if (isOverlapping(plantingOrder.get(i), flowers.get(currentIndex))) {//IF TWO OVERLAPS THEN TALLEST GOES BEHIND
                plantingOrder.add(i, flowers.get(currentIndex));
                break;
            }
            i++;//NO OVERLAP (START COMPARING WITH THE NEXT INDEX)
        }
        if (i >= currentIndex || plantingOrder.get(i) == null) {
            plantingOrder.add(flowers.get(currentIndex));
        }
        return solveWithBruteForce(flowers, currentIndex + 1, plantingOrder);
    }

    private List<Flower> solveWithMemoization(List<Flower> flowers, int currentIndex, List<Flower> plantingOrder, HashMap<String, List<Flower>> memo) {
        if (currentIndex == flowers.size()) return plantingOrder;
        String key = frameKey(currentIndex, plantingOrder);
        if (!memo.containsKey(key)) {
            int i = 0;
            while (i < currentIndex && plantingOrder.get(i) != null) {
                if (isOverlapping(plantingOrder.get(i), flowers.get(currentIndex))) {//IF TWO OVERLAPS THEN TALLEST GOES BEHIND
                    plantingOrder.add(i, flowers.get(currentIndex));
                    break;
                }
                i++;//NO OVERLAP (START COMPARING WITH THE NEXT INDEX)
            }
            if (i >= currentIndex || plantingOrder.get(i) == null) {
                plantingOrder.add(flowers.get(currentIndex));
            }
            memo.put(key, solveWithMemoization(flowers, currentIndex + 1, plantingOrder, memo));
        }
        return memo.get(key);
    }

    private List<Flower> solveWithTabulation(List<Flower> flowers) {
        List<Flower> plantingOrder = new ArrayList<>();
        plantingOrder.add(flowers.get(0));
        for (int currentIndex = 1; currentIndex < flowers.size(); currentIndex++) {
            int i = 0;
            while (i < currentIndex && plantingOrder.get(i) != null) {
                if (isOverlapping(plantingOrder.get(i), flowers.get(currentIndex))) {//IF TWO OVERLAPS THEN TALLEST GOES BEHIND
                    plantingOrder.add(i, flowers.get(currentIndex));
                    break;
                }
                i++;//NO OVERLAP (START COMPARING WITH THE NEXT INDEX)
            }
            if (i >= currentIndex || plantingOrder.get(i) == null) {
                plantingOrder.add(flowers.get(currentIndex));
            }
        }
        return plantingOrder;
    }

    private String frameKey(int currentIndex, List<Flower> plantingOrder) {
        StringBuilder key = new StringBuilder();
        key.append(currentIndex);
        for (Flower flower : plantingOrder) {
            key.append(flower.getHeight());
        }
        return key.toString();
    }

    private boolean isOverlapping(Flower orderedFlower, Flower currentFlower) {
        if ((currentFlower.getBloom() >= orderedFlower.getBloom() && currentFlower.getBloom() <= orderedFlower.getWilt())
                || (orderedFlower.getBloom() >= currentFlower.getBloom() && orderedFlower.getBloom() <= currentFlower.getWilt())
                || (currentFlower.getWilt() >= orderedFlower.getWilt() && currentFlower.getWilt() <= orderedFlower.getWilt())
                || (orderedFlower.getWilt() >= currentFlower.getWilt() && orderedFlower.getWilt() <= currentFlower.getBloom())) {
            return true;
        }
        return false;
    }

    private List<Flower> process(int[] heights, int[] blooms, int[] wilts) {
        List<Flower> flowers = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            flowers.add(new Flower(heights[ i ], blooms[ i ], wilts[ i ]));
        }
        return flowers;
    }

    private void show(List<Flower> flowers, String type) {
        if (type.equalsIgnoreCase("Sort")) {
            for (Flower flower : flowers) {
                System.out.println("Flower: H(" + flower.getHeight() + ") B(" + flower.getBloom() + ") W(" + flower.getWilt() + ")");
            }
        } else {
            for (Flower flower : flowers) {
                System.out.print(flower.getHeight() + " ");
            }
            System.out.println();
        }
    }
}

class Flower {
    private int height;
    private int bloom;
    private int wilt;

    public Flower(int height, int bloom, int wilt) {
        this.height = height;
        this.bloom = bloom;
        this.wilt = wilt;
    }

    public int getHeight() {
        return height;
    }

    public int getBloom() {
        return bloom;
    }

    public int getWilt() {
        return wilt;
    }
}