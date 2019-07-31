package com.revision.ctci.blinkedlists;

public class BReturnKthToLast {
    public static void main(String[] args) {
        BReturnKthToLast game = new BReturnKthToLast();
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
        game.solve(linkedList, 0);
        game.solve(linkedList, 1);
        game.solve(linkedList, 2);
        game.solve(linkedList, 3);
        game.solve(linkedList, 4);
        game.solve(linkedList, 5);
        game.solve(linkedList, 6);
        game.solve(linkedList, 7);
        game.solve(linkedList, 8);
        game.solve(linkedList, 9);
        game.solve(linkedList, 10);
        game.solve(linkedList, 11);
        game.solve(linkedList, 12);
        game.solve(linkedList, -12);
    }

    private void solve(LinkedList linkedList, int kth) {
        if (kth < 0) {
            System.out.println("Kth parameter is wrong (-ve)");
            return;
        }
        Node runner = linkedList.head;
        int count = kth;
        while (count >= 0 && runner != null) {
            runner = runner.next;
            count--;
        }
        if (/*runner == null && */count >= 0) {
            System.out.println(kth + "th number from last: List is small");
            return;
        }
        Node node = linkedList.head;
        while (runner != null) {
            node = node.next;
            runner = runner.next;
        }
        System.out.println(kth + "th from last: " + node.data);
    }
}