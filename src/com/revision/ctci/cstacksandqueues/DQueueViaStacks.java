package com.revision.ctci.cstacksandqueues;

import java.util.Stack;

public class DQueueViaStacks {
    public static void main(String[] args) {
        QueueStack<Integer> integerQueueStack = new QueueStack<>(5);
        integerQueueStack.offer(5);
        integerQueueStack.offer(1);
        integerQueueStack.offer(4);
        integerQueueStack.offer(6);
        integerQueueStack.offer(7);
        integerQueueStack.offer(8);
        integerQueueStack.show();
        System.out.println("Polled: "+integerQueueStack.poll());
        System.out.println("Polled: "+integerQueueStack.poll());
        integerQueueStack.offer(10);
        integerQueueStack.offer(14);
        integerQueueStack.show();
    }
}

class QueueStack<E> {
    private Stack<E> stackOne;
    private Stack<E> stackTwo;
    private int size;
    private int capacity;

    public QueueStack(int size) {
        this.stackOne = new Stack<>();
        this.stackTwo = new Stack<>();
        this.size = 0;
        this.capacity = size;
    }

    public boolean offer(E data) {
        /* - check if addition is allowed
         * - add to the stack one directly
         * - increase the size */
        if (isFull()) {
            System.out.println("Cannot add: " + data + " to stack any more... stack is full");
            return false;
        }
        this.stackOne.push(data);
        this.size++;
        return true;
    }

    public E poll() {
        /* - check if removal is possible
         * - move the values to stack two
         * - pop the top stack
         * - move the values back to stack one
         * - reduce the size */
        if (isEmpty()) {
            System.out.println("Could not remove any more... stack already empty");
            return null;
        }
        while (!stackOne.isEmpty()) {
            stackTwo.push(stackOne.pop());
        }
        E data = stackTwo.pop();
        while (!stackTwo.isEmpty()) {
            stackOne.push(stackTwo.pop());
        }
        this.size--;
        return data;
    }

    public E peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        while (!stackOne.isEmpty()) {
            stackTwo.push(stackOne.pop());
        }
        E data = stackTwo.peek();
        while (!stackTwo.isEmpty()) {
            stackOne.push(stackTwo.pop());
        }
        return data;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        while (!stackOne.isEmpty()) {
            stackTwo.push(stackOne.pop());
        }
        while (!stackTwo.isEmpty()) {
            System.out.println(stackTwo.peek());
            stackOne.push(stackTwo.pop());
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}