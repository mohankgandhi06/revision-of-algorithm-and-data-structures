package com.revision.ctci.cstacksandqueues;

public class AThreeInOne {
    public static void main(String[] args) {
        TStack three = new TStack(new int[]{4, 5, 8});
        System.out.println("Total Length: " + three.stacks.length);
        int i = 1;
        System.out.println();
        for (int[] startSizeAndTopIndex : three.startSizeAndTopIndex) {
            System.out.println("Stack #" + i);
            System.out.println("Start Index: " + startSizeAndTopIndex[ 0 ]);
            System.out.println("Size: " + startSizeAndTopIndex[ 1 ]);
            System.out.println("Top Index: " + startSizeAndTopIndex[ 2 ]);
            System.out.println();
            i++;
        }

        three.push(0, 5);
        three.push(0, 6);
        three.push(0, 8);
        three.pop(0);
        three.push(0, 9);
        three.push(0, 5);
        three.pop(2);
        three.push(2, 1);
        three.push(1, 16);
        three.push(2, 98);
        three.pop(1);
        three.push(1, 13);
        three.push(2, 78);
        three.push(1, 29);
        three.push(1, 30);
        three.pop(2);

        System.out.println("\nShowing Stack");
        show(three);

        System.out.println("\nTop: ");
        System.out.println("Stack #0: " + three.peek(0));
        System.out.println("Stack #1: " + three.peek(1));
        System.out.println("Stack #2: " + three.peek(2));
    }

    private static void show(TStack stack) {
        for (int i = 0; i < stack.stacks.length; i++) {
            System.out.println(stack.stacks[ i ]);
        }
    }
}

class TStack {
    Integer[] stacks;
    int[][] startSizeAndTopIndex;

    TStack(int[] sizes) {//length of the array determines the number of stack
        this.startSizeAndTopIndex = new int[ sizes.length ][ 3 ];
        int total = 0;
        for (int i = 0; i < sizes.length; i++) {
            this.startSizeAndTopIndex[ i ][ 0 ] = total;
            this.startSizeAndTopIndex[ i ][ 2 ] = total - 1;
            this.startSizeAndTopIndex[ i ][ 1 ] = sizes[ i ];
            total += sizes[ i ];
        }
        this.stacks = new Integer[ total ];
    }

    public void push(int stackNo, Integer data) {
        if (!isFull(stackNo)) {
            this.startSizeAndTopIndex[ stackNo ][ 2 ]++;
            this.stacks[ this.startSizeAndTopIndex[ stackNo ][ 2 ] ] = data;
        } else {
            System.out.println("Stack " + stackNo + " is full. Please try another one...");
        }
    }

    public Integer pop(int stackNo) {
        if (!isEmpty(stackNo)) {
            Integer data = this.stacks[ this.startSizeAndTopIndex[ stackNo ][ 2 ] ];
            this.stacks[ this.startSizeAndTopIndex[ stackNo ][ 2 ] ] = null;
            this.startSizeAndTopIndex[ stackNo ][ 2 ]--;
            return data;
        }
        System.out.println("Stack is already empty... could not pop any more...");
        return null;
    }

    public boolean isEmpty(int stackNo) {
        return this.startSizeAndTopIndex[ stackNo ][ 2 ] < this.startSizeAndTopIndex[ stackNo ][ 0 ];
    }

    public boolean isFull(int stackNo) {
        return this.startSizeAndTopIndex[ stackNo ][ 2 ] == (this.startSizeAndTopIndex[ stackNo ][ 0 ] + this.startSizeAndTopIndex[ stackNo ][ 1 ]);
    }

    public Integer peek(int stackNo) {
        return this.stacks[ this.startSizeAndTopIndex[ stackNo ][ 2 ] ];
    }
}