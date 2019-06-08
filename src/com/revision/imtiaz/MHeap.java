package com.revision.imtiaz;

public class MHeap<E> {
    public static void main(String[] args) {
        Heap<Integer> maxHeap = new Heap<>(20);
        maxHeap.insert(10);
        maxHeap.insert(15);
        maxHeap.insert(7);
        maxHeap.insert(4);
        maxHeap.insert(12);
        maxHeap.insert(18);
        maxHeap.insert(11);
        maxHeap.insert(12);
        maxHeap.insert(20);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.displayHeap();

        maxHeap.remove();
        maxHeap.displayHeap();

        maxHeap.remove();
        maxHeap.displayHeap();

        maxHeap.remove();
        maxHeap.displayHeap();

        maxHeap.remove();
        maxHeap.displayHeap();
    }
}

class Heap<E> {
    private int size;
    private E[] arr;
    private int currentIndex;

    public Heap(int size) {
        this.size = size;
        this.arr = (E[]) new Object[ size ];
        this.currentIndex = -1;
    }

    public boolean insert(E data) {
        if (isFull()) return false;
        this.arr[ ++this.currentIndex ] = data;
        moveToPosition(this.currentIndex);
        return true;
    }

    private void moveToPosition(int elementIndex) {
        if (elementIndex == 0) return;
        int index = elementIndex;
        int parentIndex = getParentIndex(index);
        while (parentIndex != -1 && compare(this.arr[ parentIndex ], this.arr[ index ]) < 0) {
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = getParentIndex(parentIndex);
        }
    }

    private int getParentIndex(int elementIndex) {
        if (elementIndex == 0) return -1;
        return Math.floorDiv(elementIndex - 1, 2);
    }

    private void swap(int from, int to) {
        E temp = this.arr[ from ];
        this.arr[ from ] = this.arr[ to ];
        this.arr[ to ] = temp;
    }

    public boolean remove() {
        if (isEmpty()) return false;
        this.arr[ 0 ] = this.arr[ this.currentIndex ];
        this.arr[ this.currentIndex ] = null;
        int index = 0;
        moveDownPosition(index);
        this.currentIndex--;
        return true;
    }

    private void moveDownPosition(int index) {
        int childIndex = findTheLargestChild(index);
        while (childIndex != -1) {
            swap(index, childIndex);
            index = childIndex;
            childIndex = findTheLargestChild(childIndex);
        }
    }

    private int findTheLargestChild(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        if (this.arr[ leftChildIndex ] != null && this.arr[ rightChildIndex ] != null) {
            if (compare(this.arr[ leftChildIndex ], this.arr[ rightChildIndex ]) >= 0) {
                if (compare(this.arr[ leftChildIndex ], this.arr[ index ]) > 0) {
                    return leftChildIndex;
                }
            } else if (compare(this.arr[ rightChildIndex ], this.arr[ index ]) > 0) {
                return rightChildIndex;
            }
        } else if (leftChildIndex < this.size && this.arr[ leftChildIndex ] != null && compare(this.arr[ leftChildIndex ], this.arr[ index ]) > 0) {
            return leftChildIndex;
        } else if (rightChildIndex < this.size && this.arr[ rightChildIndex ] != null && compare(this.arr[ rightChildIndex ], this.arr[ index ]) > 0) {
            return rightChildIndex;
        }
        return -1;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int compare(E obj1, E obj2) {
        Sort<E> helper = new Sort();
        return helper.compare(obj1, obj2);
    }

    public int getSize() {
        return this.currentIndex + 1;
    }

    public boolean isEmpty() {
        return this.size == -1;
    }

    public boolean isFull() {
        return this.currentIndex + 1 == this.size;
    }

    public void displayHeap() {
        System.out.println("Heap Array: ");
        for (int m = 0; m <= this.currentIndex; m++) {
            if (this.arr[ m ] != null) {
                System.out.print(this.arr[ m ] + " ");
            }
            System.out.println();
            int nBlanks = 32;
            int itemsPerRow = 1;
            int column = 0;
            int j = 0; // current item
            String dots = "...............................";
            System.out.println(dots + dots);
            while (this.currentIndex > 0) {
                if (column == 0) {
                    for (int k = 0; k < nBlanks; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.print(this.arr[ j ]);
                j++;
                if (j == this.currentIndex + 1) {
                    break;
                }
                column++;
                // end of row
                if (column == itemsPerRow) {
                    nBlanks = nBlanks / 2; // half the blanks
                    itemsPerRow = itemsPerRow * 2;     // twice the items
                    column = 0;
                    System.out.println();
                } else {
                    for (int k = 0; k < nBlanks * 2; k++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("\n" + dots + dots);
        }
    }
}