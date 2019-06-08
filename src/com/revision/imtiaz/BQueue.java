package com.revision.imtiaz;

public class BQueue {
    public static void main(String[] args) {
        Queue<String> names = new Queue<>();
        names.offer("Alex");
        names.offer("John");
        names.offer("Amanda");
        names.offer("Andy");
        names.offer("Switz");

        names.show();
        System.out.println("Peeking: " + names.peek());
        System.out.println("Size before poll: " + names.size());
        System.out.println("Polled: " + names.poll());
        System.out.println("Size after poll: " + names.size());
        System.out.println();

        names.show();
        names.offer("Ruth");
        names.offer("Diaz");
        System.out.println("Peeking: " + names.peek());
        System.out.println("Polled: " + names.poll());
        System.out.println("Polled: " + names.poll());
        System.out.println("Size after poll: " + names.size());
        names.show();
    }
}

class Queue<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public void offer(E data) {
        Node<E> node = new Node(data);
        if (size == 0) {
            this.first = node;
        } else {
            node.setPrevious(this.last);
            this.last.setNext(node);
        }
        this.last = node;
        this.size++;
    }

    public E poll() {
        if (size == 0) return null;
        E data = this.first.getData();
        this.first.getNext().setPrevious(null);
        this.first = this.first.getNext();
        this.size--;
        return data;
    }

    public E peek() {
        return this.first.getData();
    }

    public int size() {
        return this.size;
    }

    public void show() {
        Node<E> node = this.first;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println("\n");
    }
}

class Node<E> {
    private E data;
    private Node<E> previous;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}