package com.revision.ctci.cstacksandqueues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CStackOfPlates {
    public static void main(String[] args) {
        /*Tray tray = new Tray(10, 5);//Tray can hold 5 stacks of 10 plates each*/
        Tray tray = new Tray(3, 3);//Tray can hold 3 stacks of 3 plates each
        tray.push(new Plate("Large", 45, Plate.Type.LARGE));
        tray.push(new Plate("Large", 44, Plate.Type.LARGE));
        tray.push(new Plate("Large", 43, Plate.Type.LARGE));
        tray.push(new Plate("Large", 42, Plate.Type.LARGE));
        tray.push(new Plate("Large", 41, Plate.Type.LARGE));
        tray.push(new Plate("Large", 40, Plate.Type.LARGE));
        tray.push(new Plate("Large", 39, Plate.Type.LARGE));
        tray.push(new Plate("Large", 38, Plate.Type.LARGE));
        tray.push(new Plate("Large", 37, Plate.Type.LARGE));

        Plate plate = tray.pop();
        System.out.println("Popped: " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        plate = tray.pop();
        System.out.println("Popped: " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        plate = tray.pop();
        System.out.println("Popped: " + (plate != null ? plate.getPrice() : "Pop operation failed..."));

        tray.push(new Plate("Large", 36, Plate.Type.LARGE));

        System.out.println("\nShowing: ");
        tray.show();

        plate = tray.pop();
        System.out.println("Popped: " + (plate != null ? plate.getPrice() : "Pop operation failed..."));

        System.out.println("\nShowing: ");
        tray.show();

        int location = 0;
        plate = tray.popAt(location);
        System.out.println("Popped At " + location + ": " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        location = 0;
        plate = tray.popAt(location);
        System.out.println("Popped At " + location + ": " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        location = 1;
        plate = tray.popAt(location);
        System.out.println("Popped At " + location + ": " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        location = 2;
        plate = tray.popAt(location);
        System.out.println("Popped At " + location + ": " + (plate != null ? plate.getPrice() : "Pop operation failed..."));
        System.out.println("\nShowing: ");
        tray.show();
    }
}

class Tray {
    private LinkedList<PlateStack> plateStack;
    private int threshold;
    private int currentStack;
    private int capacity;

    Tray(int threshold, int capacity) {
        this.plateStack = new LinkedList<>();
        this.plateStack.add(new PlateStack(threshold));
        this.threshold = threshold;
        this.currentStack = 0;
        this.capacity = capacity;
    }

    void push(Plate plate) {
        /* Check if the current stack is full if so then create
         * a new stack if that is permitted */
        PlateStack stack = this.plateStack.peek();
        if (stack == null) {
            System.out.println("Error occurred...");
            return;
        }
        if (stack.isFull()) {
            if (!this.isFull()) {
                this.plateStack.push(new PlateStack(this.threshold));
                this.currentStack++;
                stack = this.plateStack.peek();
            } else {
                System.out.println("Could not add any more. Tray is full...");
                return;
            }
        }
        stack.push(plate);
    }

    Plate pop() {
        /* pop the plate from the last
         * after popping we check if the current plate stack is empty
         * if so we take out from the stack and minus the current stack */
        LinkedList<PlateStack> stacks = new LinkedList<>();
        PlateStack stack = null;
        while (!this.plateStack.isEmpty()) {
            stack = this.plateStack.pop();
            if (stack.isEmpty()) {
                System.out.println("Could not pop the stack is empty... trying next one");
                stacks.push(stack);
            } else {
                stacks.push(stack);
                break;
            }
        }
        if (stack != null) {
            Plate plate = stack.pop();
            while (!stacks.isEmpty()) {
                this.plateStack.push(stacks.pop());
            }
            return plate;
        }
        return null;
    }

    Plate popAt(int stackNo) {
        return this.plateStack.get((this.capacity - 1) - stackNo).pop();
    }

    private boolean isFull() {
        return this.currentStack == this.capacity - 1;
    }

    public void show() {
        LinkedList<PlateStack> temp = new LinkedList<>();
        while (!this.plateStack.isEmpty()) {
            temp.push(this.plateStack.pop());
        }
        while (!temp.isEmpty()) {
            PlateStack stack = temp.pop();
            stack.show();
            this.plateStack.push(stack);
        }
    }

    public List<PlateStack> getPlateStack() {
        return plateStack;
    }

    public int getThreshold() {
        return threshold;
    }
}

class PlateStack {
    private Plate[] plates;
    private int size;
    private int top;

    PlateStack(int size) {
        this.plates = new Plate[ size ];
        this.size = size;
        this.top = -1;
    }

    void push(Plate plate) {
        if (plate == null || this.isFull()) {
            System.out.println("Could not push into the Plate Stack...");
            return;
        }
        this.top++;
        this.plates[ this.top ] = plate;
    }

    Plate pop() {
        if (isEmpty()) {
            System.out.println("Could not pop. Stack is empty");
            return null;
        }
        Plate plate = this.plates[ this.top ];
        this.plates[ this.top ] = null;
        this.top--;
        return plate;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    boolean isFull() {
        return this.top == this.size - 1;
    }

    public void show() {
        Arrays.stream(this.plates).forEach(plate -> {
            if (plate != null) {
                System.out.println(plate.getType() + " " + plate.getPrice());
            } else {
                System.out.println("null");
            }
        });
        System.out.println();
    }

    public Plate[] getPlates() {
        return plates;
    }

    public int getSize() {
        return size;
    }

    public int getTop() {
        return top;
    }
}


class Plate {
    enum Type {
        SMALL(25), MEDIUM(30), LARGE(35);

        private int size;

        Type(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    private String type;
    private int dimension;
    private double price;

    public Plate(String type, double price, Type size) {
        this.type = type;
        this.dimension = size.getSize();
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getDimension() {
        return dimension;
    }

    public double getPrice() {
        return price;
    }
}