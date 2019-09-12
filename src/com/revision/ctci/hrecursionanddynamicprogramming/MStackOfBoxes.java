package com.revision.ctci.hrecursionanddynamicprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MStackOfBoxes {
    public static void main(String[] args) {
        MStackOfBoxes stack = new MStackOfBoxes();
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(7, 8, 9));
        boxes.add(new Box(7, 7, 6));
        boxes.add(new Box(6, 8, 7));
        boxes.add(new Box(6, 7, 7));
        boxes.add(new Box(9, 10, 11));
        boxes.add(new Box(10, 9, 9));
        boxes.add(new Box(7, 6, 5));
        boxes.add(new Box(7, 8, 9));
        boxes.add(new Box(7, 10, 10));
        boxes.add(new Box(8, 7, 9));
        boxes.add(new Box(11, 10, 10));
        boxes.add(new Box(10, 9, 12));
        stack.solve(boxes);

        boxes = new ArrayList<>();
        boxes.add(new Box(6, 4, 5));
        boxes.add(new Box(10, 5, 8));
        boxes.add(new Box(12, 4, 4));
        boxes.add(new Box(6, 6, 3));
        boxes.add(new Box(3, 3, 3));
        boxes.add(new Box(4, 2, 2));
        boxes.add(new Box(2, 1, 4));
        boxes.add(new Box(2, 1, 2));
        boxes.add(new Box(7, 5, 4));
        boxes.add(new Box(7, 6, 7));
        boxes.add(new Box(9, 6, 5));
        boxes.add(new Box(8, 8, 7));
        stack.solve(boxes);
    }

    private void solve(List<Box> boxes) {
        System.out.println("Boxes: ");
        boxes.sort(Comparator.comparing(Box::getLength).thenComparing(Box::getBreadth).thenComparing(Box::getHeight).reversed());
        boxes.forEach(box -> System.out.println("L: " + box.getLength() + " B: " + box.getBreadth() + " H: " + box.getHeight()));
        System.out.println(tallest(boxes, 0, -1));
        System.out.println("____________________________________________________");
    }

    private int tallest(List<Box> boxes, int currentIndex, int previousIndex) {
        if (currentIndex == boxes.size()) return 0;
        int include = 0;
        if (previousIndex == -1 || smallerThanPrevious(boxes.get(currentIndex), boxes.get(previousIndex))) {
            include = boxes.get(currentIndex).getHeight() + tallest(boxes, currentIndex + 1, currentIndex);
        }
        int exclude = tallest(boxes, currentIndex + 1, previousIndex);
        return Math.max(include, exclude);
    }

    private boolean smallerThanPrevious(Box currentBox, Box previousBox) {
        return currentBox.getLength() < previousBox.getLength() && currentBox.getBreadth() < previousBox.getBreadth() && currentBox.getHeight() < previousBox.getHeight();
    }
}

class Box {
    private int length;
    private int breadth;
    private int height;

    Box(int length, int breadth, int height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    int getLength() {
        return length;
    }

    int getBreadth() {
        return breadth;
    }

    int getHeight() {
        return height;
    }
}