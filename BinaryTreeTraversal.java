package cs2321;

import cs2321util.ArrayList;
import net.datastructures.*;
/*
* Course: CS2321 Fall 2024
* Section: (sec01)
* Assignment: Tree
* Author: (Jesse Sergent)
* Email: (jtsergen@mtu.edu)
*/
public class BinaryTreeTraversal<E> {
 
    /*
     * Hints for students:
     * - Think about using recursion for traversals.
     * - You may find it useful to define your own private helper method 
     * that takes BinaryTree and a Position<E>.
     * - Remember that List is an interface; ArrayList implements List.
     * - For level-order, consider using a queue to process nodes level by level.
     */

  
    private static class ArrayQueue<E> implements net.datastructures.Queue<E> {
        
        private List<E> list; 
        // Instantiate the ArrayList from cs2321util
        public ArrayQueue() {
           
            list = new ArrayList<>();
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }
     // Add to the rear of the list 
        @Override
        public void enqueue(E e) {
            
            list.add(list.size(), e);
        }
     // Get from the front of the list (index 0)
        @Override
        public E first() {
            if (isEmpty()) return null;
            
            return list.get(0);
        }
     // Remove from the front of the list (index 0)
        @Override
        public E dequeue() {
            if (isEmpty()) return null;
            
            return list.remove(0);
        }
    }

    // Private Recursive Helper Methods 

    private void inOrderSubtree(BinaryTree<E> t, Position<E> p, List<E> snapshot) {
        if (p == null) {
            return;
        }

        inOrderSubtree(t, t.left(p), snapshot);
        snapshot.add(snapshot.size(), p.getElement());
        inOrderSubtree(t, t.right(p), snapshot);
    }

    private void preOrderSubtree(BinaryTree<E> t, Position<E> p, List<E> snapshot) {
        if (p == null) {
            return;
        }

        snapshot.add(snapshot.size(), p.getElement());
        preOrderSubtree(t, t.left(p), snapshot);
        preOrderSubtree(t, t.right(p), snapshot);
    }

    private void postOrderSubtree(BinaryTree<E> t, Position<E> p, List<E> snapshot) {
        if (p == null) {
            return;
        }

        postOrderSubtree(t, t.left(p), snapshot);
        postOrderSubtree(t, t.right(p), snapshot);
        snapshot.add(snapshot.size(), p.getElement());
    }

    // Public Traversal Methods 
    
    /**
     * Return the elements of tree T, including all nodes.
     * The returned list must follow in-order traversal.
     * @param t the binary tree to traverse
     * @return the list of elements in T in in-order sequence
     */
    public List<E> inOrderElements(BinaryTree<E> t) {
        List<E> snapshot = new ArrayList<>();
        Position<E> root = ((net.datastructures.Tree<E>) t).root();
        inOrderSubtree(t, root, snapshot);
        return snapshot;
    }
    
    
    /**
     * Return the elements of tree T, including all nodes.
     * The returned list must follow pre-order traversal.
     * @param t the binary tree to traverse
     * @return the list of elements in T in pre-order sequence
     */
    public List<E> preOrderElements(BinaryTree<E> t) {
        List<E> snapshot = new ArrayList<>();
        Position<E> root = ((net.datastructures.Tree<E>) t).root();
        preOrderSubtree(t, root, snapshot);
        return snapshot;
    }
    
    
    /**
     * Return the elements of tree T, including all nodes.
     * The returned list must follow post-order traversal.
     * @param t the binary tree to traverse
     * @return the list of elements in T in post-order sequence
     */
    public List<E> postOrderElements(BinaryTree<E> t) {
        List<E> snapshot = new ArrayList<>();
        Position<E> root = ((net.datastructures.Tree<E>) t).root();
        postOrderSubtree(t, root, snapshot);
        return snapshot;
    }
    
    /**
     * Return the elements of tree T, including all nodes.
     * The returned list must follow level-order traversal.
     * @param t the binary tree to traverse
     * @return the list of elements in T in level-order sequence
     */
    public List<E> levelOrderElements(BinaryTree<E> t) {
        List<E> snapshot = new ArrayList<>();
        Position<E> root = ((net.datastructures.Tree<E>) t).root();

        if (root == null) {
            return snapshot;
        }

        // Instantiates the inner ArrayQueue class
        Queue<Position<E>> queue = new ArrayQueue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Position<E> p = queue.dequeue();
            snapshot.add(snapshot.size(), p.getElement());

            Position<E> leftChild = t.left(p);
            if (leftChild != null) {
                queue.enqueue(leftChild);
            }

            Position<E> rightChild = t.right(p);
            if (rightChild != null) {
                queue.enqueue(rightChild);
            }
        }

        return snapshot;
    }
}