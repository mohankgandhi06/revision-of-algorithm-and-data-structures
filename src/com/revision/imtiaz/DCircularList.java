package com.revision.imtiaz;

public class DCircularList {
    public static void main(String[] args) {
        CircularList<String> circularList = new CircularList();
        circularList.insert("Alex");
        circularList.insert("John");
        circularList.insert("Amanda");
        circularList.insert("Andy");
        circularList.insert("Switz");
        System.out.println("Size: " + circularList.size());
        circularList.show();
        circularList.remove("Alex");
        circularList.remove("Alex");
        circularList.remove("Amanda");
        System.out.println("Size: " + circularList.size());
        /*circularList.remove("Andy");
        circularList.remove("John");*/
        circularList.remove("Switz");
        circularList.show();

        CircularList<Integer> circular = new CircularList();
        circular.insert(14);
        circular.insert(15);
        circular.insert(20);
        circular.insert(35);
        circular.insert(85);
        circular.show();
        circular.remove(20);
        circular.show();
        System.out.println("Size: " + circular.size());
    }
}

class CircularList<E> {
    private CircularNode<E> head;
    private CircularNode<E> tail;
    private int size;

    public void insert(E data) {
        CircularNode<E> newNode = new CircularNode<>(data);
        CircularNode<E> node = this.head;
        if (node == null) {//For the first insertion
            this.head = newNode;
            this.tail = newNode;
            this.head.setNext(this.tail);
            this.head.setPrevious(this.tail);
            this.tail.setPrevious(this.head);
            this.tail.setNext(this.head);
        } else {
            //Instead of the below we could take the tail and then
            // process the add process...
            int count = 0;
            CircularNode<E> previousNode = node;
            node = node.getNext();
            if (previousNode.equals(node)) {
                newNode.setNext(node);
                newNode.setPrevious(node);
                node.setPrevious(newNode);
                node.setNext(newNode);
            } else {
                while (!node.equals(this.head) && count != 1) {
                    previousNode = node;
                    node = node.getNext();
                    if (node.equals(this.head)) count++;
                }
                previousNode.setNext(newNode);
                node.setPrevious(newNode);
                newNode.setNext(node);
                newNode.setPrevious(previousNode);
            }
        }
        size++;
    }

    public void remove(E data) {
        CircularNode node = this.head;
        int count = 0;
        CircularNode<E> previousNode = node;
        node = node.getNext();
        if (previousNode.equals(node)) {
            this.head = null;
            this.tail = null;
        } else {
            while (!previousNode.getData().equals(data) && !node.getData().equals(data) && count != 1) {
                previousNode = node;
                node = node.getNext();
                if (node.equals(this.head)) count++;
            }
            if (count == 1) {
                System.out.println(data + " not found");
                return;
            }
            if (previousNode.getData().equals(data)) {
                if (previousNode == this.head) {
                    this.head = previousNode.getNext();
                } else if (previousNode == this.tail) {
                    this.tail = previousNode.getPrevious();
                }
                previousNode.getPrevious().setNext(node);
                node.setPrevious(previousNode.getPrevious());
            } else if (node.getData().equals(data)) {
                if (node == this.head) {
                    this.head = node.getNext();
                } else if (node == this.tail) {
                    this.tail = node.getPrevious();
                }
                node.getNext().setPrevious(previousNode);
                previousNode.setNext(node.getNext());
            }
        }
        size--;
    }

    public int size() {
        return this.size;
    }

    public void show() {
        if (this.size == 0) {
            System.out.println("List is empty. nothing to show");
            return;
        }
        CircularNode<E> node = this.head;
        System.out.print(node.getData() + " ");
        int count = 0;
        node = node.getNext();
        while (!node.equals(this.head) && count != 1) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
            if (node.equals(this.head)) count++;
        }
        System.out.println();
    }
}

class CircularNode<E> {
    private E data;
    private CircularNode<E> next;
    private CircularNode<E> previous;

    public CircularNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public CircularNode<E> getNext() {
        return next;
    }

    public void setNext(CircularNode<E> next) {
        this.next = next;
    }

    public CircularNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CircularNode<E> previous) {
        this.previous = previous;
    }
}