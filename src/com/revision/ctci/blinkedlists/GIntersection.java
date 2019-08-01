package com.revision.ctci.blinkedlists;

public class GIntersection {
    public static void main(String[] args) {
        GIntersection game = new GIntersection();
        /* Intersection List Two of Longer Length */
        LinkedList<Character> commonNode = new LinkedList<>(new Node<>('a'));
        commonNode.add('b');
        commonNode.add('c');
        commonNode.add('d');
        commonNode.add('e');
        commonNode.add('f');

        LinkedList<Character> listOne = new LinkedList<>(new Node<>('x'));
        listOne.add('y');
        listOne.add('z');
        listOne.add(commonNode.head);

        LinkedList<Character> listTwo = new LinkedList<>(new Node<>('l'));
        listTwo.add('m');
        listTwo.add('o');
        listTwo.add('p');
        listTwo.add('q');
        listTwo.add('r');
        listTwo.add(commonNode.head);
        game.solve(listOne, listTwo);

        /* No Intersection */
        System.out.println();
        listOne = new LinkedList<>(new Node<>('x'));
        listOne.add('y');
        listOne.add('z');

        listTwo = new LinkedList<>(new Node<>('l'));
        listTwo.add('m');
        listTwo.add('o');
        listTwo.add('p');
        listTwo.add('q');
        listTwo.add('r');
        game.solve(listOne, listTwo);

        /* No Intersection */
        System.out.println();
        listOne = new LinkedList<>(new Node<>('x'));
        listOne.add('y');
        listOne.add('z');
        listOne.add('a');
        listOne.add('b');
        listOne.add('c');

        listTwo = new LinkedList<>(new Node<>('l'));
        listTwo.add('m');
        listTwo.add('o');
        listTwo.add('p');
        listTwo.add('q');
        listTwo.add('r');
        listTwo.add('a');
        listTwo.add('b');
        listTwo.add('c');
        game.solve(listOne, listTwo);

        /* Intersection List One of Longer Length */
        System.out.println();
        commonNode = new LinkedList<>(new Node<>('a'));
        commonNode.add('b');
        commonNode.add('c');
        commonNode.add('d');
        commonNode.add('e');
        commonNode.add('f');

        listOne = new LinkedList<>(new Node<>('x'));
        listOne.add('y');
        listOne.add('z');
        listOne.add('p');
        listOne.add('q');
        listOne.add('r');
        listOne.add(commonNode.head);

        listTwo = new LinkedList<>(new Node<>('l'));
        listTwo.add('m');
        listTwo.add('o');
        listTwo.add(commonNode.head);
        game.solve(listOne, listTwo);
    }

    private void solve(LinkedList<Character> listOne, LinkedList<Character> listTwo) {
        ZUtilsLinkedList.show(listOne, "List One");
        ZUtilsLinkedList.show(listTwo, "List Two");
        Node<Character> intersection = findTheIntersection(listOne, listTwo);
        if (intersection == null) {
            System.out.println("No Intersection found!!!");
        } else {
            System.out.println("Intersecting Node is: " + intersection.data);
        }
    }

    private Node<Character> findTheIntersection(LinkedList<Character> listOne, LinkedList<Character> listTwo) {
        int listOneSize = listOne.size();
        int listTwoSize = listTwo.size();
        System.out.println("List One size(" + listOneSize + ")");
        System.out.println("List Two size(" + listTwoSize + ")");
        int common = Math.min(listOneSize, listTwoSize);
        listOneSize -= common;
        listTwoSize -= common;
        Node nodeOne = listOne.head;
        Node nodeTwo = listTwo.head;
        while (listOneSize > 0) {
            nodeOne = nodeOne.next;
            listOneSize--;
        }
        while (listTwoSize > 0) {
            nodeTwo = nodeTwo.next;
            listTwoSize--;
        }
        return findTheNode(nodeOne, nodeTwo);
    }

    private Node<Character> findTheNode(Node<Character> nodeOne, Node<Character> nodeTwo) {
        while (nodeOne != null) {//Since both the size will be equal we can any one for checking
            if (nodeOne == nodeTwo) return nodeOne;
            nodeOne = nodeOne.next;
            nodeTwo = nodeTwo.next;
        }
        return null;
    }
}