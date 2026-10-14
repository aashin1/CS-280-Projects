package assignments.datastructures;
import adt.PriorityQueue;

/**
 * PriorityEnqueuer, a prio queue that adds items as fast as possible, smallest removed first
 * enqueue puts the new item at the front, so it is o(1). dequeue and peek search the whole list for the smallest item, so they are o(n)
 * @param <T> type of each element
 */

public class PriorityEnqueuer<T extends Comparable<T>> implements PriorityQueue<T> {
    
    private Node head;
    private int size;

    /**
     * intialize an empty prio queue
     */
    public PriorityEnqueuer() {

        this.head = null;
        this.size = 0;
    }



    /**
     * check if queue has no items
     * @return true if there are no items
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * add an item to the queue
     * the item is placed at the with no sorting
     * @param value the value to add
     */

    public void enqueue(T value) {
        this.head = new Node(value, this.head);
        this.size++;
    }

    /**
     * remove the smallest 
     * pre-condition the queue is not empty
     * @return the smallest item that was in the queue
     */

    public T dequeue(){

        assert !this.isEmpty();
        
        Node smallest = this.head;
        Node beforeSmallest = null;
        Node before = this.head;
        Node cursor = this.head.link;

        while (cursor != null) {
            if (cursor.data.compareTo(smallest.data) < 0 ) {
               
                smallest = cursor; 
                beforeSmallest = before;

            }

            before = cursor;
            cursor = cursor.link;

        }

        if (beforeSmallest == null) {
            this.head = this.head.link;
        }

        else {
            beforeSmallest.link = smallest.link;
        }
        this.size--;
        return smallest.data;
    }

    /**
     * the queue is not empty, pre-condition
     * @return smallest item in the queue
     */

    public T peek(){
        assert !this.isEmpty();

        T smallest = this.head.data;
        Node cursor = this.head.link;
        while (cursor != null) {
            if (cursor.data.compareTo(smallest) < 0) {
                smallest = cursor.data;
            }
            cursor = cursor.link;   
        }

        return smallest;
    }

    /**
     * encapsulates a value with a link to next node in chain
     */

    private class Node {

        T data;
        Node link;

        /**
         * intitalize a node
         * @param data data value
         * @param link next node in the chain
         */
        Node (T data, Node link) {
            this.data = data;
            this.link = link;
        }

    }

    /**
     * @param args 
     */

    public static void main(String[] args) {
        PriorityQueue.validate(new PriorityEnqueuer<>());
        System.out.println("priorityenqueuer passes all tests.");
    }
}
