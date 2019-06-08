package com.revision.imtiaz;

import java.util.LinkedList;
import java.util.Queue;

public class LBinarySearchTree<E> {
    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("Minaj");
        tree.add("Mario");
        tree.add("Jason");
        tree.add("Kenny");
        tree.add("Rihanna");
        tree.add("Mamoa");
        tree.add("Liam");
        tree.add("Zebra");
        tree.add("Andy");
        tree.add("Jhonny");
        BinaryNode<String> minNode = tree.findMin();
        BinaryNode<String> maxNode = tree.findMax();
        String searchTerm = "Sandra";
        BinaryNode<String> findNode = tree.find(searchTerm);
        System.out.println("Left most node: " + (minNode != null ? minNode.getData() : "The tree seems to be empty. "));
        System.out.println("Right most node: " + (maxNode != null ? maxNode.getData() : "The tree seems to be empty. "));
        System.out.println("Right most node: " + (findNode != null ? findNode.getData() : "node with the data " + searchTerm + " is not present. "));

        /*tree.remove("Zebra");*/
        /*tree.remove("Rihanna");*/
        /*tree.remove("Mario");*/
        /*tree.remove("Minaj");*/
        /*tree.remove("Jhonny");*/
        tree.remove("Kenny");
        tree.show();
    }
}

class BinarySearchTree<E> {
    private BinaryNode<E> root;

    public boolean add(E data) {
        boolean isAdded = false;
        if (this.root == null) {
            this.root = new BinaryNode<>(data, null);
            isAdded = true;
        } else {
            BinaryNode<E> node = this.root;
            BinaryNode<E> parent;
            while (node != null) {
                parent = node;
                if (compare(node.getData(), data) >= 0) {
                    node = node.getLeft();
                    if (node == null) {
                        BinaryNode<E> newNode = new BinaryNode<E>(data, parent);
                        parent.setLeft(newNode);
                    }
                } else {
                    node = node.getRight();
                    if (node == null) {
                        BinaryNode<E> newNode = new BinaryNode<E>(data, parent);
                        parent.setRight(newNode);
                    }
                }
            }
        }
        return isAdded;
    }

    public boolean remove(E data) {
        if (this.root == null) return false;
        BinaryNode<E> node = this.root;
        boolean isLeft = false;
        while (node != null) {
            if (node.getData().equals(data)) {
                break;
            } else {
                if (compare(node.getData(), data) >= 0) {
                    isLeft = true;
                    node = node.getLeft();
                } else {
                    isLeft = false;
                    node = node.getRight();
                }
            }
        }
        if (node == null) return false;
        /* REMOVE a leaf node */
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.equals(this.root)) {
                this.root = null;
            } else if (isLeft) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        }
        /* REMOVE a node with one child */
        else if (node.getRight() == null) {
            if (node.equals(this.root)) {
                this.root = node.getLeft();
            } else if (isLeft) {
                node.getParent().setLeft(node.getLeft());
            } else {
                node.getParent().setRight(node.getLeft());
            }
            node.setParent(null);
        } else if (node.getLeft() == null) {
            if (node.equals(this.root)) {
                this.root = node.getRight();
            } else if (isLeft) {
                node.getParent().setLeft(node.getRight());
            } else {
                node.getParent().setRight(node.getRight());
            }
            node.setParent(null);
        }
        /* REMOVE a node with two children */
        else {
            BinaryNode<E> start = node.getRight();
            while (start.getLeft() != null) {
                start = start.getLeft();
            }
            if (start.getRight() != null) {
                start.getParent().setRight(start.getRight());
                start.setRight(null);
            } else {
                start.getParent().setLeft(null);
            }
            start.setParent(null);
            BinaryNode<E> newNode = new BinaryNode<>(start.getData(), null);
            if (node == this.root) {
                this.root = newNode;
            } else if (isLeft) {
                node.getParent().setLeft(newNode);
            } else {
                node.getParent().setRight(newNode);
            }
            newNode.setParent(node.getParent());
            newNode.setLeft(node.getLeft());
            newNode.setRight(node.getRight());
            node.setParent(null);
        }
        return true;
    }

    public BinaryNode<E> find(E data) {
        if (this.root == null) return null;
        BinaryNode<E> node = this.root;
        while (node != null) {
            if (node.getData().equals(data)) {
                return node;
            } else {
                if (compare(node.getData(), data) >= 0) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            }
        }
        return null;
    }

    public BinaryNode<E> findMin() {
        if (this.root == null) return null;
        BinaryNode<E> node = this.root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public BinaryNode<E> findMax() {
        if (this.root == null) return null;
        BinaryNode<E> node = this.root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public void show() {
        System.out.println("\nShowing Tree: ");
        Queue<Queue<BinaryNode<E>>> queue = new LinkedList<>();
        Queue<BinaryNode<E>> innerQueue = new LinkedList<>();
        innerQueue.offer(this.root);
        queue.offer(innerQueue);
        while (!queue.isEmpty()) {
            Queue<BinaryNode<E>> tempInnerQueue = queue.poll();
            Queue<BinaryNode<E>> pushInQueue = new LinkedList<>();
            while (!tempInnerQueue.isEmpty()) {
                BinaryNode<E> node = tempInnerQueue.poll();
                if (node != null) {
                    pushInQueue.offer(node.getLeft());
                    pushInQueue.offer(node.getRight());
                    System.out.print(node.getData() + " ");
                }
            }
            System.out.println();
            if (pushInQueue.size() > 0) {
                queue.offer(pushInQueue);
            }
        }
    }

    private int compare(E obj1, E obj2) {
        Sort<E> sortHelper = new Sort<>();
        return sortHelper.compare(obj1, obj2);
    }
}

class BinaryNode<E> {
    private E data;
    private BinaryNode<E> parent;
    private BinaryNode<E> left;
    private BinaryNode<E> right;
    private boolean visited;

    public BinaryNode(E data, BinaryNode<E> parent) {
        this.data = data;
        this.parent = parent;
    }

    public E getData() {
        return data;
    }

    public BinaryNode<E> getParent() {
        return parent;
    }

    public void setParent(BinaryNode<E> parent) {
        this.parent = parent;
    }

    public BinaryNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<E> left) {
        this.left = left;
    }

    public BinaryNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryNode<E> right) {
        this.right = right;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}