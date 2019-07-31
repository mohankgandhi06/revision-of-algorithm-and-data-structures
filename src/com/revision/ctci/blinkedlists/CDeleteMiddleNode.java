package com.revision.ctci.blinkedlists;

public class CDeleteMiddleNode {
    public static void main(String[] args) {
        CDeleteMiddleNode game = new CDeleteMiddleNode();
        LinkedList linkedList = new LinkedList(new Node(3));
        linkedList.add(7);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(10);
        linkedList.add(11);
        linkedList.add(4);
        linkedList.add(8);
        game.solve(linkedList, 1);
        System.out.println();
        game.solve(linkedList, 5);
        System.out.println();
        game.solve(linkedList, 4);
        System.out.println();
        game.solve(linkedList, 14);
    }

    private void solve(LinkedList linkedList, int deleteThisTerm) {
        ZUtilsLinkedList.show(linkedList, "Before Deleting");
        Node node = linkedList.head;
        while (node != null && !node.data.equals(deleteThisTerm)) {
            node = node.next;
        }
        if (node == null) {
            System.out.println("The data is not available");
            return;
        }
        deleteThis(node);
        ZUtilsLinkedList.show(linkedList, "After Deleting");
    }

    private void deleteThis(Node node) {
        System.out.println("Deleting this: " + node.data);
        node.data = node.next.data;
        node.next = node.next.next;
    }
}