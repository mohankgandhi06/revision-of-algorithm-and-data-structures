package com.revision.ctci.gobjectorienteddesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ICircularArray {
    public static void main(String[] args) {
        CircularArray<String> array = new CircularArray<>(new String[]{"Steven", "Mendoza", "Elano", "Bernard", "Mendy", "Roger",
                "Bonucci", "Messi", "Ronaldo", "Griezman", "Neymar", "Dembele"});
        System.out.println("Initial Array: ");
        array.show();
        System.out.println("[" + 5 + "]th element: " + array.get(5));
        System.out.println("[" + 8 + "]th element: " + array.get(8));
        System.out.println("[" + 10 + "]th element: " + array.get(10));
        System.out.println("[" + 11 + "]th element: " + array.get(11));
        System.out.println("[" + 3 + "]th element: " + array.get(3));
        System.out.println("[" + 0 + "]th element: " + array.get(0));

        int rotate = 5;
        array.rotate(rotate);
        System.out.println("Rotate by: " + rotate + " elements. \n\n" + "New Head: " + array.first());
        array.show();
        System.out.println("[" + 5 + "]th element: " + array.get(5));
        System.out.println("[" + 8 + "]th element: " + array.get(8));
        System.out.println("[" + 10 + "]th element: " + array.get(10));
        System.out.println("[" + 11 + "]th element: " + array.get(11));
        System.out.println("[" + 3 + "]th element: " + array.get(3));
        System.out.println("[" + 0 + "]th element: " + array.get(0));

        rotate = 2;
        array.rotate(rotate);
        System.out.println("Rotate by: " + rotate + " elements. \n\n" + "New Head: " + array.first());
        array.show();
        System.out.println("[" + 5 + "]th element: " + array.get(5));
        System.out.println("[" + 8 + "]th element: " + array.get(8));
        System.out.println("[" + 10 + "]th element: " + array.get(10));
        System.out.println("[" + 11 + "]th element: " + array.get(11));
        System.out.println("[" + 3 + "]th element: " + array.get(3));
        System.out.println("[" + 0 + "]th element: " + array.get(0));
    }
}

class CircularArray<E> implements Iterable<E> {
    private int size;
    private int start;
    public List<E> arr;

    CircularArray(E[] input) {
        this.arr = new ArrayList<>(size);
        this.arr.addAll(Arrays.asList(input));
        this.size = this.arr.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularArrayIterator<E>(this.arr, this.start);
    }

    //No of index position to shift it will be taken as index-1 as we start from 0
    void rotate(int startIndex) {
        this.start = startIndex > 0 ? (this.start + startIndex) % this.size : 0;

    }

    E get(int index) {//After rotated the values will start from the new index as zero
        //But since we are keeping the original order intact and just updating the index
        //We need to alter the get, put method
        int ind = (index + start) % this.size;
        return this.arr.get(ind);
    }

    E first() {
        return this.arr.get(start);
    }

    void show() {
        for (E item : this) {
            System.out.println(item);
        }
    }
}

class CircularArrayIterator<E> implements Iterator<E> {
    private List<E> arr;
    private int start;
    private int current;

    CircularArrayIterator(List<E> data, int start) {
        this.arr = data;
        this.start = start;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < this.arr.size();
    }

    @Override
    public E next() {
        int ind = (current + start) % this.arr.size();
        E data = this.arr.get(ind);
        current++;
        return data;
    }

    @Override
    public void remove() {
        /* IMPLEMENT IF NEEDED */
    }
}