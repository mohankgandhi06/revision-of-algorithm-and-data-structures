package com.revision.imtiaz;

import java.util.LinkedList;

public class NHashTable<E> {
    public static void main(String[] args) {
        int size = 25;
        HashTable<String> table = new HashTable<>(size);
        table.insert("Metro Exodus");
        table.insert("Mekorama");
        table.insert("Assassin's Creed");
        table.insert("Metal Gear Solid");
        table.insert("Prince of Persia");
        table.insert("Harry Potter");
        table.insert("Lord of the Rings");
        table.insert("Fallout 4");
        table.insert("Project Zero");
        table.insert("Batman Dark Knight");
        table.show();
        String searchTerm = "Fallout 4";
        table.find(searchTerm);
        table.remove(searchTerm);
        table.show();
        int index = 14;
        LinkedList<String> list = table.get(index);
        if (list != null) {
            System.out.println("\nList: at #" + index);
            for (String string : list) {
                System.out.println(string);
            }
        } else {
            System.out.println("List is empty.");
        }
    }
}

class HashTable<E> {
    private LinkedList<E>[] table;
    private int size;
    private int hashValue;

    public HashTable(int size) {
        int value = getNextPrime(size);
        if (value != -1) {
            this.table = conversion(new Object[ value ]);
            this.hashValue = value;
            this.size = value;
        } else {
            this.table = conversion(new Object[ size ]);
            this.hashValue = size;
            this.size = size;
        }
    }

    private LinkedList<E>[] conversion(Object[] object) {
        LinkedList<E>[] table = new LinkedList[ object.length ];
        for (int i = 0; i < object.length; i++) {
            table[ i ] = (LinkedList<E>) object[ i ];
        }
        return table;
    }

    public int getNextPrime(int size) {
        for (int i = size; i < Integer.MAX_VALUE; i++) {
            boolean flag = true;
            for (int j = 2; j * j < i; j++) {
                if (i % j == 0) {//ideally if we are using j<=i then we need to
                    // add && i!=j but since we are having j <(i/2)
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public void insert(E data) {
        int index = getHashCode(data);
        if (this.table[ index ] == null) {
            LinkedList<E> list = new LinkedList<>();
            list.add(data);
            this.table[ index ] = list;
        } else {
            this.table[ index ].add(data);
        }
    }

    public void remove(E data) {
        int index = find(data);
        if (index != -1) {
            int in = this.table[ index ].indexOf(data);
            if (in != -1) {
                E removed = this.table[ index ].remove(in);
                System.out.println("Removed the element: " + removed + " at the index: " + index);
            }
        } else {
            System.out.println("Could not find the element.");
        }
    }

    public LinkedList<E> get(int index) {
        if (index > this.size) return null;
        return this.table[ index ];
    }

    public int find(E data) {
        int index = getHashCode(data);
        if (this.table[ index ] != null) {
            LinkedList<E> list = this.table[ index ];
            int in = list.indexOf(data);
            if (in != -1) {
                System.out.println(data + " found at index #" + index);
                return index;
            }
        }
        System.out.println("Could not find the element: " + data);
        return -1;
    }

    public void show() {
        for (int i = 0; i < this.table.length; i++) {
            System.out.println("Index #" + i + ": ");
            if (this.table[ i ] == null || this.table[ i ].size() == 0) {
                System.out.println();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (E item : this.table[ i ]) {
                    stringBuilder.append(item).append(", ");
                }
                if (stringBuilder.length() > 2) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 2);
                }
                System.out.println(stringBuilder + "\n");
            }
        }
    }

    private int getHashCode(E data) {
        int hashCode = data.hashCode();
        return Math.abs(hashCode % hashValue);
    }

    public int getHashValue() {
        return hashValue;
    }
}