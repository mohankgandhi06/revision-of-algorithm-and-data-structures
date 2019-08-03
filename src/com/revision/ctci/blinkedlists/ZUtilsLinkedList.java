package com.revision.ctci.blinkedlists;

import java.util.Stack;

public class ZUtilsLinkedList<E> {
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

    public static LinkedList reverse(LinkedList linkedList) {
        Stack stack = new Stack<>();
        Node node = linkedList.head;
        while (node != null) {
            stack.push(node.data);
            node = node.next;
        }
        LinkedList result = new LinkedList();
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
}