package com.revision.ctci.blinkedlists;

public class ZUtilsLinkedList {
    public static void show(LinkedList linkedList, String mesage) {
        System.out.println(mesage);
        Node node = linkedList.head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void show(Node node, String mesage) {
        System.out.println(mesage);
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }
}