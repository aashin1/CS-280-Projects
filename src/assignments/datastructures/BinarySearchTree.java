package assignments.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Manually construct a tree so we can test iterator methods.
     * 
     * NOTE: You will remove this method once we've learned how to add.
     * 
     * @param demo a specific sequence of numbers defined in the main method
     */
    public void fillForDay1Tests(T[] demo) {
        this.root = new Node(demo[0]);
        this.root.left = new Node(demo[1]);
        this.root.left.left = new Node(demo[2]);
        this.root.left.right = new Node(demo[3]);
        this.root.left.left.right = new Node(demo[4]);
        this.root.left.right.left = new Node(demo[5]);
        this.root.left.left.right.right = new Node(demo[6]);
        this.root.right = new Node(demo[7]);
        this.root.left.left.right.right.left = new Node(demo[8]);
        this.root.left.right.right = new Node(demo[9]);
        this.root.left.right.left.right = new Node(demo[10]);
        this.root.left.right.left.left = new Node(demo[11]);
        this.root.left.right.right.right = new Node(demo[12]);
        this.root.left.right.left.right.right = new Node(demo[13]);
        this.root.left.left.right.right.right = new Node(demo[14]);
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        
        return count(this.root);
    }
        // recursive step
    private int count(Node node) {
        if (node==null){
            return 0;
        }
        int leftCount = count(node.left);
        int rightCount = count(node.right);
        return 1 + leftCount + rightCount;
    }
    
    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        return degree(this.root);
    }

    private int degree(Node node) {
        if(node == null){
            return 0;
        }
        int children = 0; // count how many this node has 
        if (node.left != null) children++; // if it has a child to the left, count it
        if (node.right != null) children++; // child to the right, count it 

        return Math.max(children, Math.max(degree(node.left), degree(node.right))); // returns the largest degree between this node's, left, or right sub trees.

    }
    
    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        return height(this.root);

    }
    
    private int height(Node node) {
        if(node == null)
            return 0;
        
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);

    }
    
    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        List<T> result = new ArrayList<>(); // creates an empty list to store the values in with preorder
        preorder(this.root, result); // fills the list 
        return result.iterator(); // returns an interator over the newly filled list
    }

    private void preorder(Node node, List <T> result) {
       if (node == null) return;
       result.add(node.data); // visits the current node first
       preorder(node.left, result); // then the entirety of the left subtree
       preorder(node.right, result); // then the entire right subtree
    }
    
    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        List<T> result = new ArrayList<>(); // empty list to fill with values in order
        inorder(this.root, result);  // fills the list starting from the root recursively
        return result.iterator();
    }

    private void inorder(Node node, List<T> result){
        if(node==null) return;
        inorder(node.left, result); // left tree first
        result.add(node.data); // current node (in middle)
        inorder(node.right, result); // then right
    }
    
    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        List<T> result = new ArrayList<>();
        postorder(this.root, result);
        return result.iterator();
    }

    private void postorder(Node node, List<T> result){
        if (node==null) return; 
        postorder(node.left, result); // left subtree first
        postorder(node.right, result); //right subtree
        result.add(node.data); // visits current node last
    }
    

    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        List<T> result = new ArrayList<>(); // fills level by level
        if (this.root == null) {
            return result.iterator();
        }
        java.util.LinkedList<Node> queue = new java.util.LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            result.add(current.data);
            if (current.left !=null) queue.add(current.left);
            if (current.right !=null) queue.add(current.right);
        }
        return result.iterator();
    }
    
    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();

    }

    
    /**
     * 
     * @param value
     * @return boolean, true or false if we modified the tree
     */
    public boolean add(T value) {
        this.root = add(value, this.root);
        return true;
    }
    private Node add(T value, Node node) {

        if(node==null)
            return new Node(value);

        int compareResult = value.compareTo(node.data);

        if (compareResult<0)
            node.left = add(value, node.left);
        
        else 
            node.right = add(value, node.right);

        return node;

    }


    /**
     * 
     * @param value
     * @return boolean, true or false if we modified the tree
     */
    public boolean remove (T value) {
        boolean found = contains(value); // if it is in tree, removing will modify tree
        this.root = remove(value, this.root);
        return found;

    }   
    private Node remove(T value, Node node) {     
        
        if (node==null)
            return node;

        int compareResult = value.compareTo(node.data);

        if(compareResult<0)
            node.left = remove(value, node.left);

        else if(compareResult > 0)
            node.right = remove(value, node.right);
        
        else if (node.left != null && node.right != null)
        {
            node.data = findMin(node.right).data;
            node.right = remove(node.data, node.right);
        }

        else
            node = (node.left != null) ? node.left : node.right;

        return node;


    }

    private Node findMin(Node node) {
        if (node == null)
            return null;

        while (node.left != null)
            node = node.left;
        return node;
    }

    /**
     * 
     * @param value
     * @return boolean, returns true if value is in the tree, false if it is not 
     */
    public boolean contains (T value) {

        return contains(value, this.root);
    }

    private boolean contains(T value, Node node) {
        if (node == null)
        return false;

        int compareResult = value.compareTo(node.data);
        
        if(compareResult < 0)
            return contains(value, node.left);
        else if (compareResult > 0)
            return contains(value, node.right);
        else
            return true;

    }

    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new BinarySearchTree<>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.fillForDay1Tests(numbers);     // NOTE: To be replaced once we learn how to add.

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() == 6;

        // Check traversals.
        assert testTraversal(tree.preorder(),   new int[]{4, -4, -6, -6, -5, -6, -5, 0, -3, -4, -3, -3, 0, 3, 5});
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.postorder(),  new int[]{-6, -5, -5, -6, -6, -4, -3, -3, -3, 3, 0, 0, -4, 5, 4});
        assert testTraversal(tree.levelorder(), new int[]{4, -4, 5, -6, 0, -6, -3, 0, -5, -4, -3, 3, -6, -5, -3});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});

        System.out.println("BinarySearchTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}