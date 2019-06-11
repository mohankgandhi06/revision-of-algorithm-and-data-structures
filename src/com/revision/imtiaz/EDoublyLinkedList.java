package com.revision.imtiaz;

public class EDoublyLinkedList {
    public static void main(String[] args) {
        DoublyLinkedList<String> names = new DoublyLinkedList<>();
        names.insert("Alex", 0);
        names.insert("John", 1);
        names.insert("Amanda", 2);
        names.insert("Andy", 1);
        names.insert("Switz", 4);
        names.insert("Mark", 6);
        names.insert("Mark", 3);
        names.insert("Jon", 0);
        names.show();
        System.out.println("Size: " + names.size());
        names.remove("Mark");
        names.remove("Alex");
        names.remove("Steve");
        names.show();
        System.out.println("Size: " + names.size());
    }
}

class DoublyLinkedList<E> {
    private DoublyLinkedNode<E> head;
    private DoublyLinkedNode<E> tail;
    private int size;

    public void insert(E data, int index) {
        if (this.size < index) {
            System.out.println("\nSize is less than the index given. Data: " + data + " index: " + index);
            return;
        }
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);
        int count = 0;
        DoublyLinkedNode<E> node = this.head;
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            while (count != index) {
                count++;
                if (count == index) break;
                node = node.getNext();
            }
            if (index == 0) {//Add before head
                newNode.setNext(node);
                node.setPrevious(newNode);
                this.head = newNode;
            } else if (index >= this.size - 1) {//Add after tail
                node.setNext(newNode);
                newNode.setPrevious(node);
                this.tail = newNode;
            } else {//Add in-between
                newNode.setNext(node.getNext());
                newNode.setPrevious(node);
                node.getNext().setPrevious(newNode);
                node.setNext(newNode);
            }
        }
        this.size++;
    }

    public void remove(E data) {
        DoublyLinkedNode<E> node = this.head;
        while (node != null && !node.getData().equals(data)) {
            node = node.getNext();
        }
        if (node == null) {
            System.out.println("\nCould not find the element to remove: Data: " + data);
            return;
        }
        if (node == this.head) {//Remove head and promote the next as head
            node.getNext().setPrevious(null);
            this.head = node.getNext();
        } else if (node == this.tail) {//Remove tail and promote the previous as tail
            node.getPrevious().setNext(null);
            this.tail = node.getPrevious();
        } else {
            node.getNext().setPrevious(node.getPrevious());
            node.getPrevious().setNext(node.getNext());
        }
        this.size--;
    }

    public void show() {
        System.out.println("\nList: ");
        DoublyLinkedNode<E> node = this.head;
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
        System.out.println("___________________________");
    }

    public int size() {
        return this.size;
    }
}

class DoublyLinkedNode<E> {
    private E data;
    private DoublyLinkedNode<E> previous;
    private DoublyLinkedNode<E> next;

    public DoublyLinkedNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public DoublyLinkedNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyLinkedNode<E> previous) {
        this.previous = previous;
    }

    public DoublyLinkedNode<E> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedNode<E> next) {
        this.next = next;
    }
}