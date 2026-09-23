package assignments.datastructures;
import java.util.Iterator;
import adt.List;

/// A list that is extendable, operates with a circular chain of nodes
/// 
/// Similar to a linked list, but the last node links back to the first node instead of to null
/// Store the last node, rather than the first. The first node is always one step away, at tail.link
/// Thus, we can insert at either the start or the end of the list without traversing the whole thing
/// 
/// @param <T> the type of each element
public class CircularLinkedList<T> implements List<T>, Iterable<T> {
    private Node tail;
    private int size;



    /**
     * intialize an empty circularlinkedlist
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    /**
     * compute the number of items in this list
     * @return the number of items
     */
    public int length() {
        return this.size;
    }

    /**
     * Fetch an item from the list.
     * @param index the location of the item 
     * @return the value stored at the location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;

        Node cursor = tail.link;
        for (int i = 0; i < index; i++) {
            cursor = cursor.link;
        }
        return cursor.data;
    }

    /**
     * change item in list
     * @param index the location of the item 
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;

        Node cursor = tail.link;
        for (int i = 0; i < index; i++) {
            cursor = cursor.link;
        }
        cursor.data = value;
    }

    /**
     * check if list contains a given value
     * @param value the value to look for
     * @return true only if the list contains the value
     */
    public boolean contains(T value) {
        if (this.size == 0) {
            return false;
        }

        Node cursor = tail.link;
        for (int i = 0; i < this.size; i++) {
            if (value.equals(cursor.data)) {
                return true;
            }
            cursor = cursor.link;
        }
        return false;
    }

    /**
     * insert item into list
     * @param index the location of where to put the item
     * @param value new value to put at location
     */
    public void insert(int index, T value) {
        assert 0<= index && index <= this.size;

        if (this.size ==0) {
            Node node = new Node(value, null);
            node.link = node;
            tail = node;
        }
        else {
            Node before = tail;
            if (index < this.size) {
                for (int i = 0; i < index; i++) {
                    before = before.link;
                }
            }

            before.link = new Node(value, before.link);
            if (index == this.size) {
                tail = before.link;
            }
        }
        size++;
    }

    /**
     * remove item from list
     * @param index the location to delete from
     * @return deleted value
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;

        Node before = tail;
        for (int i = 0; i < index; i++) {
            before = before.link;
        }
        Node removed = before.link;

        if (this.size == 1) {
            tail = null;
        }
        else {
            before.link = removed.link;
            if (removed == tail) {
                tail = before;
            }
        }
        size--;
        return removed.data;
    }


    /**
     * iterator over items in this list from front to back
     * @return a new iterator starting at the first item
     */
    public Iterator<T> iterator() {
        return new CircularIterator();
    }




    /**
     * basically encapsulates a value with a pointer, which allows the value to be chained to another value
     */
    private class Node {
        T data;
        Node link;
    
    /**
     * initialize a node
     * @param data data value
     * @param link next node in the chain
     */
    Node (T data, Node link) {
        this.data = data;
        this.link = link;
    }
    }
    /**
     * walk through the list's items in order, once around the circle
     */
    private class CircularIterator implements Iterator<T> { // private class because Node is private
        private Node current;
        private int visited;

        /**
         * start at the first node, or at nothing if the list is empty
         */

        CircularIterator() {
            if (size == 0) {
                current = null;
            }
            else {
                current = tail.link;
            }
            visited = 0;
        }

        /**
         * check if there are more items to visit
         * @return true iff next() can be called
         */
        public boolean hasNext() {
            return visited < size;
        }

        /**
         * return the current item and move to the next one
         * @return the next item in the list
         */
        public T next() {
            T value = current.data;
            current = current.link;
            visited++;
            return value;
        }
    }

    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();

        System.out.println("CircularLinkedList passes all tests.");
    }    

}
