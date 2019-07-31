package com.revision.ctci.blinkedlists;

import java.util.HashMap;
import java.util.Map;

public class ARemoveDups {
    public static void main(String[] args) {
        ARemoveDups game = new ARemoveDups();
        LinkedList linkedList = new LinkedList(new Node(3));
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

    private void solve(LinkedList linkedList) {
        /* HashMap : BigO (n)
         * Without Data Structure: BigO (n^2) */
        LinkedList duplicate = copy(linkedList);
        System.out.println("Using Hash Map: ");
        ZUtilsLinkedList.show(duplicate, "Before Removal: ");
        Map<Integer, Integer> hashMap = new HashMap();
        solveWithHashMap(duplicate, hashMap);
        ZUtilsLinkedList.show(duplicate, "After Removal: ");

        System.out.println("\nWithout Data Structure: ");
        ZUtilsLinkedList.show(linkedList, "Before Removal: ");
        solveWithoutAdditionalDataStructure(linkedList);
        ZUtilsLinkedList.show(linkedList, "After Removal: ");
    }

    private void solveWithHashMap(LinkedList linkedList, Map<Integer, Integer> hashMap) {
        Node node = linkedList.head;
        Node previousNode = null;
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

    private void solveWithoutAdditionalDataStructure(LinkedList linkedList) {
        Node outerNode = linkedList.head;
        while (outerNode.next != null) {
            Node innerNode = outerNode.next;
            Node previousNode = null;
            while (innerNode != null) {
                if (outerNode.data == innerNode.data) {
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

    private LinkedList copy(LinkedList linkedList) {
        LinkedList duplicate = new LinkedList(new Node(linkedList.head.data));
        Node node = linkedList.head;
        Node copyNode = duplicate.head;
        while (node.next != null) {
            copyNode.next = new Node(node.next.data);
            node = node.next;
            copyNode = copyNode.next;
        }
        return duplicate;
    }
}

class LinkedList {
    public Node head;

    public LinkedList(Node head) {
        this.head = head;
    }

    public void add(int data) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(data);
    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}