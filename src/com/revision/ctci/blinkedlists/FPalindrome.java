package com.revision.ctci.blinkedlists;

import java.util.Stack;

public class FPalindrome {
    public static void main(String[] args) {
        FPalindrome game = new FPalindrome();
        LinkedList<Character> string = new LinkedList<>(new Node<>('m'));
        string.add('a');
        string.add('d');
        string.add('a');
        string.add('m');
        game.solve(string);

        string = new LinkedList<>(new Node<>('z'));
        string.add('o');
        string.add('l');
        string.add('l');
        string.add('o');
        string.add('z');
        System.out.println();
        game.solve(string);

        string = new LinkedList<>(new Node<>('z'));
        string.add('o');
        string.add('y');
        string.add('l');
        string.add('o');
        string.add('y');
        string.add('o');
        string.add('z');
        System.out.println();
        game.solve(string);

        string = new LinkedList<>(new Node<>('c'));
        string.add('o');
        string.add('m');
        string.add('e');
        string.add('m');
        string.add('e');
        string.add('o');
        string.add('c');
        System.out.println();
        game.solve(string);

        string = new LinkedList<>(new Node<>('c'));
        string.add('o');
        string.add('m');
        string.add('e');
        string.add('m');
        string.add('e');
        string.add('m');
        string.add('o');
        string.add('c');
        System.out.println();
        game.solve(string);
    }

    private void solve(LinkedList<Character> string) {
        int length = findLength(string);//Time: BigO(n) Space: 1
        System.out.println("Length: " + length);
        Result result = buildStackForHalfOfTheLength(string, length);//Time: BigO(n/2) Space: n/2
        result.stack.forEach(character -> System.out.print(character + " "));
        System.out.println("\nStack Size: " + result.stack.size() + " Node: " + result.node.data);
        String message = palidromeCheck(result) ? "Palindrome" : "Not a Palindrome!!!";
        System.out.println(message);
    }

    private boolean palidromeCheck(Result result) {
        Node<Character> node = result.node;
        while (node != null) {
            if (node.data != result.stack.pop()) {
                return false;
            }
            node = node.next;
        }
        return true;
    }

    private Result buildStackForHalfOfTheLength(LinkedList<Character> string, int length) {
        Stack<Character> stack = new Stack<>();
        Node<Character> node = string.head;
        int half = length / 2;
        while (half > 0) {
            stack.add(node.data);
            node = node.next;
            half--;
        }
        return new Result(stack, length % 2 == 1 ? node.next : node);
    }

    private int findLength(LinkedList<Character> string) {
        Node<Character> node = string.head;
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }
}

class Result {
    public Stack<Character> stack;
    public Node<Character> node;

    public Result(Stack<Character> stack, Node<Character> node) {
        this.stack = stack;
        this.node = node;
    }
}