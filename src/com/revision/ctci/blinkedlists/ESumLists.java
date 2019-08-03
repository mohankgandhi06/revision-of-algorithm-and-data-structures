package com.revision.ctci.blinkedlists;

public class ESumLists {
    public static void main(String[] args) {
        ESumLists game = new ESumLists();
        LinkedList<Integer> inputOne = new LinkedList<>(new Node<>(7));
        inputOne.add(1);
        inputOne.add(6);
        LinkedList<Integer> inputTwo = new LinkedList<>(new Node<>(5));
        inputTwo.add(9);
        inputTwo.add(2);
        game.solve(inputOne, inputTwo);

        System.out.println();
        inputOne = new LinkedList<>(new Node<>(7));
        inputOne.add(8);
        inputOne.add(6);
        inputTwo = new LinkedList<>(new Node<>(5));
        inputTwo.add(9);
        inputTwo.add(8);
        game.solve(inputOne, inputTwo);

        System.out.println();
        inputOne = new LinkedList<>(new Node<>(7));
        inputOne.add(8);
        inputOne.add(6);
        inputTwo = new LinkedList<>(new Node<>(5));
        inputTwo.add(9);
        inputTwo.add(8);
        inputTwo.add(2);
        inputTwo.add(7);
        game.solve(inputOne, inputTwo);

        System.out.println();
        inputOne = new LinkedList<>(new Node<>(7));
        inputOne.add(8);
        inputOne.add(6);
        inputOne.add(9);
        inputOne.add(9);
        inputTwo = new LinkedList<>(new Node<>(5));
        inputTwo.add(9);
        inputTwo.add(8);

        game.solve(inputOne, inputTwo);
    }

    private void solve(LinkedList<Integer> inputOne, LinkedList<Integer> inputTwo) {
        ZUtilsLinkedList.show(inputOne, "Input One: ");
        ZUtilsLinkedList.show(inputTwo, "Input Two: ");
        System.out.println("Reversed Order");
        LinkedList<Integer> result = new LinkedList<>();
        solveWithTailRecursion(inputOne.head, inputTwo.head, result, 0);
        ZUtilsLinkedList.show(result, "Result: ");
        System.out.println("\nNormal Order");
        result = new LinkedList<>();
        int oneSize = inputOne.size();
        int twoSize = inputTwo.size();
        if (oneSize < twoSize) {
            LinkedList<Integer> temp = inputOne;
            inputOne = inputTwo;
            inputTwo = temp;
        }
        int startTwoAfter = Math.abs(oneSize - twoSize);
        /*System.out.println(startTwoAfter);*/
        int carry = solveWithHeadRecursion(inputOne.head, inputTwo.head, result, -startTwoAfter);
        if (carry != 0) {
            result.add(new Node<>(carry));
        }
        result = ZUtilsLinkedList.reverse(result);
        ZUtilsLinkedList.show(result, "Result");
        System.out.println("____________________________________________________________________");
    }

    private int solveWithHeadRecursion(Node<Integer> nodeOne, Node<Integer> nodeTwo, LinkedList<Integer> result, int startTwoAfter) {
        /* BASE CASE */
        if (nodeOne == null && nodeTwo == null) return 0;
        int carry = solveWithHeadRecursion(nodeOne != null ? nodeOne.next : null, (startTwoAfter + 1 > 0 && nodeTwo != null) ? nodeTwo.next : nodeTwo, result, startTwoAfter + 1);
        /* PERFORM THE CALCULATION AND CARRY */
        int total = (nodeOne != null ? nodeOne.data : 0) + (startTwoAfter >= 0 && nodeTwo != null ? nodeTwo.data : 0) + carry;
        int nodesValue = total % 10;
        /*System.out.println("Start After: " + startTwoAfter);
        System.out.println("Node One: " + (nodeOne != null ? nodeOne.data : 0) + " Node Two: " + (startTwoAfter >= 0 && nodeTwo != null ? nodeTwo.data : 0) + " Carry: " + carry);
        System.out.println("Total: " + total);*/
        carry = total / 10;
        result.add(new Node<>(nodesValue));
        return carry;
    }

    private void solveWithTailRecursion(Node<Integer> nodeOne, Node<Integer> nodeTwo, LinkedList<Integer> result, int carry) {
        /* BASE CASE */
        if (nodeOne == null && nodeTwo == null && carry == 0) return;
        /* PERFORM THE CALCULATION AND CARRY */
        int total = (nodeOne != null ? nodeOne.data : 0) + (nodeTwo != null ? nodeTwo.data : 0) + carry;
        int nodesValue = total % 10;
        carry = total / 10;
        result.add(new Node<>(nodesValue));
        solveWithTailRecursion(nodeOne != null ? nodeOne.next : null, nodeTwo != null ? nodeTwo.next : null, result, carry);
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
}