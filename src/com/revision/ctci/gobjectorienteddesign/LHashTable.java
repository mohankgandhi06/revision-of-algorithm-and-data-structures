package com.revision.ctci.gobjectorienteddesign;

import java.util.LinkedList;

public class LHashTable {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(25);
        hashTable.put("Aakash", 1);
        hashTable.put("Alex", 2);
        hashTable.put("Naren", 3);
        hashTable.put("John", 4);
        hashTable.put("John", 6);
        hashTable.show();
    }
}

class HashTable<K, V> {
    private LinkedList[] values;
    private int size;
    /* This prime is chosen to lessen the collision. if for example we are having 10 as the size and
     * when we are calculating the hash for multiples of 10 it will all be sharing the same position
     * and this can be avoided by using a hash value that is a prime and then again taking modulo of
     * the size to avoid any arrayIndexOutOfBounds */
    private int prime = 53;
    /* Hard Coded for now. Ideally we need to generate a prime number next of the
    table size */

    HashTable(int size) {
        this.size = size;
        this.values = new LinkedList[ size ];
        for (int i = 0; i < this.values.length; i++) {
            this.values[ i ] = new LinkedList();
        }
    }

    public boolean put(K key, V value) {
        if (key == null || value == null) return false;
        int index = getIndex(key);
        return add(this.values[ index ], key, value);
    }

    private boolean add(LinkedList linkedList, K key, V value) {
        if (linkedList != null) {
            for (int i = 0; i < linkedList.size(); i++) {
                //Since Generic Declaration is not allowed for an array we are casting here
                PlaceHolder<K, V> item = (PlaceHolder<K, V>) linkedList.get(i);
                if (item.key.equals(key) /*&& item.value.equals(value)*/) {
                    System.out.println("Duplicate Keys not allowed...");
                    return false;
                }
            }
        }
        PlaceHolder<K, V> placeHolder = new PlaceHolder<>(key, value);
        linkedList.add(placeHolder);
        return true;
    }

    public void show() {
        for (int i = 0; i < values.length; i++) {
            if (this.values[ i ] != null) {
                for (int j = 0; j < values[ i ].size(); j++) {
                    //Since Generic Declaration is not allowed for an array we are casting here
                    PlaceHolder<K, V> item = (PlaceHolder<K, V>) values[ i ].get(j);
                    System.out.println("Key: " + item.key + " Value: " + item.value);
                }
            }
        }
    }

    private int getIndex(K key) {
        int code = key.hashCode();
        return (code % this.prime) % this.size;
    }

    public int getSize() {
        return size;
    }
}

class PlaceHolder<K, V> {
    public K key;
    public V value;

    PlaceHolder(K key, V value) {
        this.key = key;
        this.value = value;
    }
}