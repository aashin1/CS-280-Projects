package assignments.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its left child,
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
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        
        return count(this.root);
    }
    /**
     * recursive step
     * @param node root of the subtree, could be null
     * @return numbers of nodes in subtree, 0 if empty
     */
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

    /**
     * recursively finds the largest number of children of any node in the subtree
     * @param node root of subtree
     * @return degree of subtree, 0 if empty
     */
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

    /**
     * recursively computes the height of the subtree rooted at node
     * @param node root of subtree, could be null
     * @return height of subtree, 0 if empty
     */
    
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
        return result.iterator(); // returns an iterator over the newly filled list
    }

    /**
     * recursively adds subtree's values to list in preorder (node, left, right)
     * @param node root of subtree, could be null
     * @param result list that collects the values
     */
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

    /**
     * recursively adds the subtree's values to the list in in-order (left, node, right)
     * @param node root of subtree, could be null
     * @param result list that collects values
     */
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

    /**
     * recursively add the subtree's values to the list in post-order
     * post order is left, right, node
     * @param node root of subtree
     * @param result list that collects the values
     */
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
     * inserts a value into the tree, value less than a node go to its left
     * @param value value to insert
     * @return boolean, true or false if we modified the tree
     */

    public boolean add(T value) {
        this.root = add(value, this.root);
        return true;
    }

    /**
     * recursively inserts a value into the subtree rooted at the given node
     * @param value value to insert
     * @param node root of the subtree
     * @return root of subtree after the insertion
     */
    
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
     * remove one instance of a value from the tree if it exists 
     * @param value the value to remove
     * @return true if value was found and removed, false if the tree was never modified
     */

    public boolean remove (T value) {
        boolean found = contains(value); // if it is in tree, removing will modify tree
        this.root = remove(value, this.root);
        return found;

    }   

    /**
     * recursively removes a value from the subtree rooted at the given node
     * node with two children is replaced by the smallest value in its right subtree
     * @param value value that will be removed
     * @param node the root of the subtree, could be
     * @return boolean, true or false if we modified the tree
     */

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

    /**
     * find node holding smallest value in subtree, which is rooted at the given node
     * @param node root of the subtree, could be null
     * @return the leftmost node (or null if subtree is empty)
     */

    private Node findMin(Node node) {
        if (node == null)
            return null;

        while (node.left != null)
            node = node.left;
        return node;
    }


    /**
     * checks if a value is present in the tree
     * @param value the value that is searched for
     * @return true if value is in tree, false if not
     */

    public boolean contains (T value) {

        return contains(value, this.root);
    }

    /**
     * recursively searches the subtree which holds a root at the given node for a value
     * @param value the value that is looked for
     * @param node root of the subtree (which could be null)
     * @return boolean, returns true if value is in the tree, false if it is not 
     */

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

        // empty tree. everything is either 0 or false

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assert tree.length() == 0; 
        assert tree.degree() == 0;
        assert tree.height() == 0;
        assert !tree.contains(4);
        assert !tree.remove(4); // removing something returns false


        // adds 7 different numbers. 4 is root

        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);

        // runs different traversal methods based on what was done by hand
        assert testTraversal(tree.preorder(), new int[]{4, 2, 1, 3, 6 ,5, 7});
        assert testTraversal(tree.inorder(), new int[]{1, 2, 3, 4, 5, 6, 7});
        assert testTraversal(tree.postorder(), new int[]{1, 3, 2, 5, 7, 6, 4});
        assert testTraversal(tree.levelorder(), new int[]{4, 2, 6, 1, 3, 5, 7});
        assert testTraversal(tree.iterator(), new int[]{1, 2, 3, 4, 5, 6, 7});
        
        // checks if numbers are in tree or not

        assert tree.contains(1);
        assert tree.contains(3);
        assert !tree.contains(8);
        assert !tree.contains(0);

        // removes a node with no children

        assert tree.remove(1);
        assert !tree.contains(1);
        assert tree.length() == 6;
        assert testTraversal(tree.inorder(), new int[]{2, 3, 4, 5, 6, 7});

        // removes node with two children -- 6 gets replaced by 7, the smallest value in its right subtree

        assert tree.remove(6);
        assert tree.length() == 5;
        assert testTraversal(tree.inorder(), new int[]{2, 3, 4, 5, 7});

        // removes root, which also bears two children

        assert tree.remove(4);
        assert tree.length() ==4;
        assert testTraversal(tree.inorder(), new int[]{2, 3, 5, 7});

        // removing number that is not in tree should return false and change nothing

        assert !tree.remove(10231);
        assert tree.length() == 4;

        // adding duplicate number

        tree.add(5);
        assert tree.length() == 5;
        assert testTraversal(tree.inorder(), new int[]{2, 3, 5, 5, 7});


        // if everything works, then this prints

        System.out.println("binarysearchtree passes all tests");
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