package com.revision.ctci.blinkedlists;

public class HLoopDetection {
    public static void main(String[] args) {
        HLoopDetection game = new HLoopDetection();
        LinkedList<Character> corruptedList = new LinkedList<>(new Node<>('A'));
        Node<Character> nodeB = new Node<>('B');
        Node<Character> nodeC = new Node<>('C');
        Node<Character> nodeD = new Node<>('D');
        Node<Character> nodeE = new Node<>('E');
        corruptedList.add(nodeB);
        corruptedList.add(nodeC);
        corruptedList.add(nodeD);
        corruptedList.add(nodeE);
        corruptedList.add(nodeC);
        /*ZUtilsLinkedList.show(corruptedList, "Corrupted: ");//It will cause an infinite loop */
        game.solve(corruptedList);

        System.out.println();
        corruptedList = new LinkedList<>(new Node<>('A'));
        nodeB = new Node<>('B');
        nodeC = new Node<>('C');
        nodeD = new Node<>('D');
        nodeE = new Node<>('E');
        Node<Character> nodeF = new Node<>('F');
        Node<Character> nodeG = new Node<>('G');
        Node<Character> nodeH = new Node<>('H');
        Node<Character> nodeI = new Node<>('I');
        Node<Character> nodeJ = new Node<>('J');
        corruptedList.add(nodeB);
        corruptedList.add(nodeC);
        corruptedList.add(nodeD);
        corruptedList.add(nodeE);
        corruptedList.add(nodeF);
        corruptedList.add(nodeG);
        corruptedList.add(nodeH);
        corruptedList.add(nodeI);
        corruptedList.add(nodeJ);
        corruptedList.add(nodeC);
        game.solve(corruptedList);

        System.out.println();
        corruptedList = new LinkedList<>(new Node<>('A'));
        nodeB = new Node<>('B');
        nodeC = new Node<>('C');
        nodeD = new Node<>('D');
        nodeE = new Node<>('E');
        nodeF = new Node<>('F');
        nodeG = new Node<>('G');
        nodeH = new Node<>('H');
        nodeI = new Node<>('I');
        nodeJ = new Node<>('J');
        corruptedList.add(nodeB);
        corruptedList.add(nodeC);
        corruptedList.add(nodeD);
        corruptedList.add(nodeE);
        corruptedList.add(nodeF);
        corruptedList.add(nodeG);
        corruptedList.add(nodeH);
        corruptedList.add(nodeI);
        corruptedList.add(nodeJ);
        corruptedList.add(nodeG);
        game.solve(corruptedList);

        System.out.println();
        corruptedList = new LinkedList<>(new Node<>('A'));
        nodeB = new Node<>('B');
        nodeC = new Node<>('C');
        nodeD = new Node<>('D');
        nodeE = new Node<>('E');
        nodeF = new Node<>('F');
        nodeG = new Node<>('G');
        nodeH = new Node<>('H');
        nodeI = new Node<>('I');
        nodeJ = new Node<>('J');
        corruptedList.add(nodeB);
        corruptedList.add(nodeC);
        corruptedList.add(nodeD);
        corruptedList.add(nodeE);
        corruptedList.add(nodeF);
        corruptedList.add(nodeG);
        corruptedList.add(nodeH);
        corruptedList.add(nodeI);
        corruptedList.add(nodeJ);
        corruptedList.add(corruptedList.head);
        game.solve(corruptedList);
    }

    private void solve(LinkedList<Character> corruptedList) {
        Node<Character> normal = corruptedList.head.next;
        Node<Character> runner = corruptedList.head.next.next;
        while (normal != runner) {//Until the two normal and runner meet let them jump 1 step and 2 step respectively
            normal = normal.next;
            runner = runner.next.next;
        }
        System.out.println("They meet: " + normal.data + ", " + runner.data);
        /* Now ask the normal runner to start from first again and the runner to run normally when they meet again
         * that node is the start of the corrupted node */
        normal = corruptedList.head;
        while (normal != runner) {
            normal = normal.next;
            runner = runner.next;
        }
        System.out.println("Start of the corrupt node: " + normal.data);
    }
}