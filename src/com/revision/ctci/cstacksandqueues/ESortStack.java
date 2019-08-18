package com.revision.ctci.cstacksandqueues;

public class ESortStack {
    public static void main(String[] args) {
        SortStack stack = new SortStack(10);
        stack.push(5);
        stack.push(1);
        stack.push(6);
        stack.push(4);
        stack.push(8);
        stack.push(2);
        stack.show();
        stack.pop();
        stack.push(2);
        stack.show();
        stack.sort();
        stack.show();

        System.out.println("Second Wave");
        stack.push(10);
        stack.push(-1);
        stack.push(0);
        stack.push(2);
        stack.show();
        stack.sort();
        stack.show();
    }
}

class SortStack {
    private int[] stack;
    private int capacity;
    private int top;
    private SortStack swapStack;

    public SortStack(int capacity) {
        this.stack = new int[ capacity ];
        this.capacity = capacity;
        this.top = -1;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("Cannot add: " + data + " since stack is full...");
            return;
        }
        this.top++;
        this.stack[ this.top ] = data;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Cannot pop since the stack is empty...");
            return -1;
        }
        int data = this.stack[ this.top ];
        this.stack[ this.top ] = 0;
        this.top--;
        return data;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty...");
            return -1;
        }
        return this.stack[ this.top ];
    }

    private boolean isEmpty() {
        return this.top == -1;
    }

    private boolean isFull() {
        return this.top == this.capacity - 1;
    }

    public int size() {
        return this.top + 1;
    }

    public void show() {
        System.out.println("______________________________");
        System.out.println("Stack: ");
        for (int i = this.top; i >= 0; i--) {
            System.out.println(this.stack[ i ]);
        }
        System.out.println("______________________________");
    }

    public void sort() {
        /* - while loop is iterated until the stack one is moved fully to stack two
         * - now the rule for pushing an element into stack two is that it should be greater than the one below
         * - if that above condition is not satisfied then we take that element into a temp variable and then
         *   move the stack two by comparing each element until the condition that the peek element in stack two is
         *   lower than the temp variable. then the temp variable is pushed into the stack two and then process
         *   continues again */
        this.swapStack = new SortStack(capacity);
        while (!this.isEmpty()) {
            int data = this.pop();
            if (this.swapStack.isEmpty() || this.swapStack.stack[ this.swapStack.top ] <= data) {
                this.swapStack.push(data);
            } else if (this.swapStack.stack[ this.swapStack.top ] > data) {
                while (this.swapStack.peek() > data) {
                    this.push(this.swapStack.pop());
                }
                this.swapStack.push(data);
            }
        }
        while (!this.swapStack.isEmpty()) {
            this.push(this.swapStack.pop());
        }
    }
}