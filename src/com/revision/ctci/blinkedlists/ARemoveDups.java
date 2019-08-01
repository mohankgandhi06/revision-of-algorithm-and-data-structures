package com.revision.ctci.blinkedlists;

import java.util.HashMap;
import java.util.Map;

public class ARemoveDups {
    public static void main(String[] args) {
        ARemoveDups game = new ARemoveDups();
        LinkedList<Integer> linkedList = new LinkedList<>(new Node<>(3));
        linkedList.add(7);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(1);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(10);
        linkedList.add(2);
        game.solve(linkedList);
    }

    private void solve(LinkedList<Integer> linkedList) {
        /* HashMap : BigO (n)
         * Without Data Structure: BigO (n^2) */
        LinkedList<Integer> duplicate = copy(linkedList);
        System.out.println("Using Hash Map: ");
        ZUtilsLinkedList.show(duplicate, "Before Removal: ");
        Map<Integer, Integer> hashMap = new HashMap<>();
        solveWithHashMap(duplicate, hashMap);
        ZUtilsLinkedList.show(duplicate, "After Removal: ");

        System.out.println("\nWithout Data Structure: ");
        ZUtilsLinkedList.show(linkedList, "Before Removal: ");
        solveWithoutAdditionalDataStructure(linkedList);
        ZUtilsLinkedList.show(linkedList, "After Removal: ");
    }

    private void solveWithHashMap(LinkedList<Integer> linkedList, Map<Integer, Integer> hashMap) {
        Node<Integer> node = linkedList.head;
        Node<Integer> previousNode = null;
        while (node != null) {
            if (!hashMap.containsKey(node.data)) {
                hashMap.put(node.data, node.data);
            } else {
                if (previousNode == null) {//Head Removal, but this scenario will never occur
                    linkedList.head = node.next;
                } else {
                    previousNode.next = node.next;
                    node = node.next;
                    continue;
                }
            }
            previousNode = node;
            node = node.next;
        }
    }

    private void solveWithoutAdditionalDataStructure(LinkedList<Integer> linkedList) {
        Node<Integer> outerNode = linkedList.head;
        while (outerNode.next != null) {
            Node<Integer> innerNode = outerNode.next;
            Node<Integer> previousNode = null;
            while (innerNode != null) {
                if (outerNode.data.equals(innerNode.data)) {
                    previousNode.next = innerNode.next;
                    innerNode = innerNode.next;
                    continue;
                }
                previousNode = innerNode;
                innerNode = innerNode.next;
            }
            outerNode = outerNode.next;
        }
    }

    private LinkedList<Integer> copy(LinkedList<Integer> linkedList) {
        LinkedList<Integer> duplicate = new LinkedList<>(new Node<>(linkedList.head.data));
        Node<Integer> node = linkedList.head;
        Node<Integer> copyNode = duplicate.head;
        while (node.next != null) {
            copyNode.next = new Node<>(node.next.data);
            node = node.next;
            copyNode = copyNode.next;
        }
        return duplicate;
    }
}

class LinkedList<E> {
    public Node<E> head;

    public LinkedList(Node<E> head) {
        this.head = head;
    }

    public void add(E data) {
        Node<E> node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(data);
    }

    public void add(Node<E> data) {
        Node<E> node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = data;
    }
}

class Node<E> {
    public E data;
    public Node<E> next;

    public Node(E data) {
        this.data = data;
    }
}