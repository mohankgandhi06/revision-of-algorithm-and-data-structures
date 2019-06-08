package com.revision.imtiaz;

public class AStack {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>(new String[ 4 ]);
        try {
            stringStack.push("Alex");
            stringStack.push("Baldwin");
            stringStack.push("is");
            stringStack.push("enacting");
            /*stringStack.push("as");*/
            stringStack.pop();
            stringStack.revealStack();
            stringStack.size();
        } catch (EmptyStackException e) {
            System.out.print("Exception has occurred");
            System.out.print(": " + e.getError());
            System.out.print("\nMessage: " + e.getMessage());
            System.out.print("\nReason: " + e.getReason() + "\n");
        } catch (FullStackException e) {
            System.out.print("Exception has occurred");
            System.out.print(": " + e.getError());
            System.out.print("\nMessage: " + e.getMessage());
            System.out.print("\nReason: " + e.getReason() + "\n");
        }
    }
}

class Stack<E> {
    private E[] slots;
    private int top;
    private int capacity;

    public E[] getSlots() {
        return slots;
    }

    public int getTop() {
        return top;
    }

    public int getCapacity() {
        return capacity;
    }

    public Stack(E[] slots) {
        this.slots = slots;
        this.top = -1;
        this.capacity = slots.length - 1;
    }

    public void push(E data) throws FullStackException {
        System.out.println("Trying to push: " + data);
        if (isFull()) throw new FullStackException();
        slots[ ++this.top ] = data;
    }

    public E pop() throws EmptyStackException {
        System.out.println("Trying to pop the " + size() + " element");
        if (isEmpty()) throw new EmptyStackException();
        int index = this.top;
        this.slots[ this.top ] = null;
        this.top--;
        return this.slots[ index ];
    }

    public void revealStack() {
        System.out.println("Revealing Stack of size: " + size());
        E[] copy = this.slots.clone();
        for (E slot : copy) {
            System.out.println(slot);
        }
        System.out.println();
    }

    public int size() {
        return this.top + 1;
    }

    private boolean isFull() {
        return this.top == this.capacity;
    }

    private boolean isEmpty() {
        return this.top == -1;
    }
}

class EmptyStackException extends Exception {
    private final String error = "EmptyStackException";
    private final String message = "Cannot pop any more element";
    private final String reason = "Already stack is empty";

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}

class FullStackException extends Exception {
    private final String error = "FullStackException";
    private final String message = "Cannot push any more element";
    private final String reason = "Already stack is full";

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}