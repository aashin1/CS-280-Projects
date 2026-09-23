package assignments.datastructures;
import java.util.Iterator;
import adt.List;
import adt.Stack;

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
public class LinkedList<T> implements List<T>, Iterable<T>, Stack<T> {
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
        
        return this.size;
    }
    
    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;

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


    public Iterator<T> iterator() {
        return new ListIterator();       
    }

    /**
     * check if stack has no items
     * @return true if there are no items
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * Adds an item to top of stack 
     * @param value the item to add
     */
    public void push(T value){
        this.insert(0, value);
    }


    /**
     * remove from top of stack
     * @return item that was returned
     */
    public T pop(){
        assert !this.isEmpty();
        return this.delete(0);
    }

    /**
     * look at item at top of stack
     * @return item that is looked at
     */
    public T peek() {
        assert !this.isEmpty();
        return this.at(0);
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

    private class ListIterator implements Iterator<T> {
        Node current;

        ListIterator() {
            current = head;
        }
    

    public boolean hasNext(){
        if (current == null) {
            return false;
         } else {
                return true;
            }
    }
    
    public T next() {
        T value = current.data;
        current = current.link;
        return value;
    }
}

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new LinkedList<>());
        Stack.validate(new LinkedList<>());
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();
 
        System.out.println("LinkedList passes all tests.");
    }
}

/**
 * iterator
 */
class LinkedListIterator<T> implements Iterator<T> {
    private LinkedList<T> list;
    private int cursor;
    public LinkedListIterator(LinkedList<T> list){
        this.list = list;
        this.cursor = 0;
    }

    /**
     * check if there are more items to visit
     * @return true if next can be called
     */

    public boolean hasNext(){
        return cursor < list.length();
    }
    /**
     * return current item and move to next
     * @return next item in linked list
     */
    public T next() {
        T value = list.at(cursor);
        cursor++;
        return value;
    }
}

