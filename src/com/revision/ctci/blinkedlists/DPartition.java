package com.revision.ctci.blinkedlists;

import java.util.Stack;

public class DPartition {
    public static void main(String[] args) {
        DPartition game = new DPartition();
        LinkedList<Integer> linkedList = new LinkedList<>(new Node<>(3));
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(5);
        linkedList.add(10);
        linkedList.add(2);
        linkedList.add(1);
        int target = 5;
        game.solve(linkedList, target);

        System.out.println("_______________________________________________");
        linkedList = new LinkedList<>(new Node<>(3));
        linkedList.add(12);
        linkedList.add(8);
        linkedList.add(6);
        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(12);
        linkedList.add(3);
        linkedList.add(1);
        target = 5;
        game.solve(linkedList, target);

        System.out.println("_______________________________________________");
        linkedList = new LinkedList<>(new Node<>(13));
        linkedList.add(10);
        linkedList.add(14);
        linkedList.add(6);
        linkedList.add(15);
        linkedList.add(5);
        linkedList.add(2);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(4);
        linkedList.add(12);
        linkedList.add(3);
        linkedList.add(1);
        target = 8;
        game.solve(linkedList, target);

        System.out.println("_______________________________________________");
        linkedList = new LinkedList<>(new Node<>(3));
        linkedList.add(10);
        linkedList.add(14);
        linkedList.add(6);
        linkedList.add(15);
        linkedList.add(5);
        linkedList.add(2);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(4);
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(1);
        target = 8;
        game.solve(linkedList, target);
    }

    private void solve(LinkedList<Integer> linkedList, int target) {
        ZUtilsLinkedList.show(linkedList, "Linked List");
        System.out.println("Target: " + target+"\n");
        System.out.println("With Stack: ");
        solveWithStack(linkedList, target);
        /*ZUtilsLinkedList.show(linkedList, "\nLinked List");*/
        System.out.println("\nWith New Linked Lists: ");
        solveWithNewLinkedLists(linkedList, target);
        LinkedList<Integer> copy = clone(linkedList);
        /*ZUtilsLinkedList.show(copy, "\nLinked List");*/
        System.out.println("\nIn Place: ");
        solveInPlace(copy, target);
        /*ZUtilsLinkedList.show(linkedList, "\nLinked List");*/
        System.out.println("\nWith Head Recursion: ");
        solveWithHeadRecursion(linkedList, target);
    }

    private void solveWithStack(LinkedList<Integer> linkedList, int target) {
        Node<Integer> node = linkedList.head;
        Stack<Integer> smallerStack = new Stack<>();
        Stack<Integer> largerStack = new Stack<>();
        while (node != null) {
            if (node.data < target) smallerStack.add(node.data);
            else largerStack.add(node.data);
            node = node.next;
        }
        LinkedList<Integer> result = new LinkedList<>(new Node<>(smallerStack.pop()));
        while (!smallerStack.empty()) {
            result.add(new Node<>(smallerStack.pop()));
        }
        while (!largerStack.empty()) {
            result.add(new Node<>(largerStack.pop()));
        }
        ZUtilsLinkedList.show(result, "Result: ");
    }

    private void solveWithNewLinkedLists(LinkedList<Integer> linkedList, int target) {
        Node<Integer> node = linkedList.head;
        LinkedList<Integer> smallerList = new LinkedList<>();
        LinkedList<Integer> largerList = new LinkedList<>();
        while (node != null) {
            if (node.data < target) smallerList.add(node.data);
            else largerList.add(node.data);
            node = node.next;
        }
        smallerList.add(largerList.head);
        ZUtilsLinkedList.show(smallerList, "Result: ");
    }

    private void solveInPlace(LinkedList<Integer> linkedList, int target) {
        Node<Integer> runner = linkedList.head;
        Node<Integer> runnerPrevious = null;
        while (runner != null) {
            if (runner.data < target) {
                Node<Integer> node = linkedList.head;
                Node<Integer> nodePrevious = null;
                while (node.data < target && node != runner) {
                    nodePrevious = node;
                    node = node.next;
                }
                if (node != runner) {
                    Node<Integer> tempNodeNext = node.next;
                    runnerPrevious.next = node;
                    node.next = runner.next;
                    if (nodePrevious != null) {
                        nodePrevious.next = runner;
                    } else {
                        linkedList.head = runner;
                    }
                    runner.next = tempNodeNext;
                    /*nodePrevious = runnerPrevious;
                    node = runner;*/
                    continue;
                }
            }
            runnerPrevious = runner;
            runner = runner.next;
        }
        ZUtilsLinkedList.show(linkedList, "Result: ");
    }

    private void solveWithHeadRecursion(LinkedList<Integer> linkedList, int target) {
        Result<Integer> result = solveWithHeadRecursion(linkedList.head, target);
        ZUtilsLinkedList.show(result.head, "Result: ");
    }

    private Result<Integer> solveWithHeadRecursion(Node<Integer> node, int target) {
        /* BASE CASE */
        if (node.next == null) {
            return new Result<>(node, node);
        }
        /* HEAD RECURSION */
        Result<Integer> result = solveWithHeadRecursion(node.next, target);
        /* COMPARE AND REPLACE */
        if (node.data >= target) {//Append to Tail
            result.tail.next = node;
            node.next = null;
            result.tail = node;
        } else {//Append to Head
            node.next = result.head;
            result.head = node;
        }
        return result;
    }

    private LinkedList<Integer> clone(LinkedList<Integer> linkedList) {
        LinkedList<Integer> clone = new LinkedList<>();
        Node<Integer> node = linkedList.head;
        while (node != null) {
            clone.add(new Node<>(node.data));
            node = node.next;
        }
        return clone;
    }

    class Result<E> {
        public Node<E> head;
        public Node<E> tail;

        public Result(Node<E> head, Node<E> tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}