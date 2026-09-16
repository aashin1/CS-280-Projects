package assignments.datastructures;

import adt.List;

/// An extensible list backed by a chain of nodes.
/// 
/// The idea here is to wrap each datum in a larger structure, a *node*,
///  which also contains a pointer to the node containing the *next* element in the list.
/// This structure permits efficient insertion and deletion,
///  in the sense that it only requires rearranging pointers nearby where the change takes place.
/// 
/// However, this structure foregoes *random access*, i.e. easy access to arbitrary locations in the list.
/// In order to make any changes to a location in the middle of the list,
///  one must first traverse through the chain of nodes from the beginning of the list.
/// 
/// @param <T> the type of each element
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    /**
     * Initialize an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        // TODO implement this method
        return this.size;
    }
    
    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        


        Node cursor = head;
        for (int i = 0; i<index; i++) {
            cursor = cursor.link;
        }
        return cursor.data;
    }
    
    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        Node cursor = head;
        for (int i=0; i <index; i++) {
            cursor = cursor.link;
        }
        cursor.data = value;

    }
    
    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        // TODO implement this method
        Node cursor = head;
        while (cursor != null) {
            if (value.equals(cursor.data)) {
                return true;
            }
            cursor = cursor.link;
        }
        return false;
    }
    
    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;
        // TODO implement this method
        if (index ==0) {
            head = new Node(value, head);

        }
        else {
            Node cursor = head;
            for (int i = 0; i<index -1; i++) {
                cursor = cursor.link;
            }
            cursor.link = new Node(value, cursor.link);
        }
        size++;

    }
    
    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        T removed;
        if (index == 0) {
            removed = head.data;
            head = head.link;
        }
        else {
            Node cursor = head;
            for (int i = 0; i < index -1; i++) {
                cursor = cursor.link;
            }
            removed = cursor.link.data;
            cursor.link = cursor.link.link;
        }
        size--;
        return removed;
    }

    /**
     * An encapsulation of a value with a pointer, allowing us to chain to another value.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children.
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new LinkedList<>());
        System.out.println("LinkedList passes all tests.");
    }
}
