package com.revision.ctci.cstacksandqueues;

import java.util.LinkedList;

public class BStackMin {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());
        stack.add(3);
        stack.add(6);
        stack.add(2);
        stack.add(4);
        stack.add(5);
        stack.add(8);
        stack.add(1);
        stack.add(7);
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("Removed: " + stack.remove());
        System.out.println("\nAfter Removal");
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        stack.add(1);
        stack.add(7);
        stack.add(1);
        stack.add(7);
        stack.add(0);
        System.out.print("\nStack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("Removed: " + stack.remove());
        System.out.println("\nAfter Removal");
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());
        /*System.out.println("Minimum: " + stack.min() + " " + stack.currentMin + " " + stack.previousMin);*/
        System.out.println("\nRemoved: " + stack.remove());
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        /*System.out.println("\nRemoved: " + stack.remove());
        System.out.println("\nRemoved: " + stack.remove());
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("\nRemoved: " + stack.remove());
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());

        System.out.println("\nRemoved: " + stack.remove());
        System.out.println("\nRemoved: " + stack.remove());
        System.out.print("Stack Content: ");
        stack.show();
        System.out.println("Minimum: " + stack.min());*/
    }
}

class Stack {
    /* For simplicity we are implementing the Java Util Linked List */
    private LinkedList<Data> dataList;
    Data currentMin;
    Data previousMin;

    public Stack() {
        this.dataList = new LinkedList<>();
        this.currentMin = null;
        this.previousMin = null;
    }

    public boolean add(int data) {
        /* When adding a new data to the stack check if it smaller or equal to current min then update that */
        Data newData = new Data(data, this.currentMin);
        if (this.currentMin == null || data <= this.currentMin.getData()) {
            this.previousMin = this.currentMin;
            this.currentMin = newData;
            /*System.out.println("Previous: " + (this.previousMin != null ? this.previousMin.getData() : null) + " Current: " + this.currentMin.getData());
            System.out.println("Current: " + this.currentMin + " Previous: " + this.previousMin);*/
        }
        dataList.offer(newData);
        return true;
    }

    public int remove() {
        Data data = dataList.pollLast();
        if (data == null) return -1;
        if (data == this.currentMin) {
            /*System.out.println("Before Removing: Current Min: " + this.currentMin.getData() + " " + this.currentMin + " Previous: " + this.previousMin.getData() + " " + this.previousMin);*/
            /* Replace it with previous min */
            this.currentMin = this.previousMin;
            this.previousMin = this.currentMin != null ? this.currentMin.getMin() : null;
        }
        return data.getData();
    }

    public int min() {
        return this.currentMin != null ? this.currentMin.getData() : -1;
    }

    public void show() {
        for (Data data : this.dataList) {
            System.out.print(data.getData() + " ");
        }
        System.out.println();
    }
}

class Data {
    private int data;
    private Data min;

    public Data(int data, Data min) {
        this.data = data;
        this.min = min;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Data getMin() {
        return min;
    }

    public void setMin(Data min) {
        this.min = min;
    }
}