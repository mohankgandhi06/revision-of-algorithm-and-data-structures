package com.revision.imtiaz;

public class CSinglyLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<String> names = new SinglyLinkedList<>();
        names.addFirst("Alex");
        names.addFirst("John");
        names.addLast("Amanda");
        names.add(2, "Andy");
        names.add(1, "Switz");
        names.add(0, "Ritz");
        names.show();
        names.removeFirst();
        names.removeLast();
        names.show();
        names.add(0, "Amanda");
        names.add(0, "Ritz");
        names.show();
        names.remove(0);
        names.remove(3);
        names.remove(0);
        names.show();
        System.out.println("Size: " + names.size());
    }
}

class SinglyLinkedList<E> {
    private SinglyLinkedNode<E> head;
    private int size;

    public void add(int index, E data) {
        if (this.size < index) {
            System.out.println("Index is higher. current size is " + size + " NOTE: index will be one lesser than the size");
            return;
        }
        if (this.size == 0) {
            SinglyLinkedNode<E> newNode = new SinglyLinkedNode<>(data);
            this.head = newNode;
        } else {
            int currentIndex = 0;
            SinglyLinkedNode<E> node = this.head;
            if (index == 0) {
                SinglyLinkedNode<E> newNode = new SinglyLinkedNode<>(data);
                newNode.setNext(node);
                this.head = newNode;
            } else {
                while (node != null) {
                    if (currentIndex == index - 1) {
                        SinglyLinkedNode<E> newNode = new SinglyLinkedNode<>(data);
                        newNode.setNext(node.getNext());
                        node.setNext(newNode);
                        break;
                    }
                    currentIndex++;
                    node = node.getNext();
                }
            }
        }
        this.size++;
    }

    public void addFirst(E data) {
        SinglyLinkedNode<E> node = new SinglyLinkedNode(data);
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

    public void addLast(E data) {
        if (this.size == 0) {
            this.head = new SinglyLinkedNode<>(data);
        } else {
            SinglyLinkedNode<E> node = this.head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(new SinglyLinkedNode<>(data));
        }
        this.size++;
    }

    public void remove(int index) {
        if (this.size < index - 1) {
            System.out.println("Index is higher. current size is " + size + " NOTE: index will be one lesser than the size");
            return;
        }
        if (this.size == 0) {
            System.out.println("The list is already empty");
            return;
        } else {
            int currentIndex = 0;
            SinglyLinkedNode<E> node = this.head;
            SinglyLinkedNode<E> previousNode = null;
            while (node != null) {
                if (currentIndex == index) {
                    if (previousNode == null) {
                        this.head = node.getNext();
                    } else {
                        previousNode.setNext(node.getNext());
                    }
                    break;
                }
                currentIndex++;
                previousNode = node;
                node = node.getNext();
            }
        }
        this.size--;
    }

    public void removeFirst() {
        this.head = this.head.getNext();
        this.size--;
    }

    public void removeLast() {
        SinglyLinkedNode<E> node = this.head;
        SinglyLinkedNode<E> previousNode = null;
        while (node.getNext() != null) {
            previousNode = node;
            node = node.getNext();
        }
        if (previousNode == null) {
            this.head = node.getNext();
        } else {
            previousNode.setNext(node.getNext());
        }
        this.size--;
    }

    public int size() {
        return this.size;
    }

    public void show() {
        SinglyLinkedNode<E> node = this.head;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}

class SinglyLinkedNode<E> {
    private E data;
    private SinglyLinkedNode<E> next;

    public SinglyLinkedNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public SinglyLinkedNode<E> getNext() {
        return next;
    }

    public void setNext(SinglyLinkedNode<E> next) {
        this.next = next;
    }
}