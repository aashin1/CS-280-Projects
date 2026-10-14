package assignments.datastructures;
import adt.PriorityQueue;


// 


public class PriorityDequeuer<T extends Comparable<T>> implements PriorityQueue<T> {



    private Node head;
    private int size;

    /**
     * intialize an empty prio queue
     */

    public PriorityDequeuer(){

        this.head = null;
        this.size = 0;
    }


    /**
     * check if queue has no items (for O(1))
     * @return true if there are no items
     */

    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * add an item to the queue in sorted position, which is done in o(n)
     * items equal to this one stay ahead of it
     * @param value the value to add
     */

    public void enqueue(T value) {
        if (this.head == null) {
            this.head = new Node(value, null);
        }

        else if (value.compareTo(this.head.data) < 0 ) {
            this.head = new Node(value, this.head);

        }
        else {

            Node cursor = this.head;
            boolean searching = true;
            while (searching) {
                if (cursor.link == null) {
                    searching = false;
                }
                else if (value.compareTo(cursor.link.data)<0){
                    searching = false;
                }
                else {
                    cursor = cursor.link;

                }
            }
            cursor.link = new Node(value, cursor.link);

        }
        this.size++;
    }




    /**
     * remove the smallest item from the queue, o(1))
     * pre-condition-- the queue is not empty
     * @return the smallest item that was in the queue
     */
    public T dequeue(){
        assert !this.isEmpty();
        T result = this.head.data;
        this.head = this.head.link;
        this.size--;
        return result;
    }

    /**
     * report the next item to be removed from the queue 
     * the queue must not be empty
     * @return smallest item in queue
     */

    public T peek(){
        assert !this.isEmpty();
        return this.head.data;
    }

    /**
     * encapsuates a value with a link to the next node in the chain
     */
    private class Node {
        T data;
        Node link;
    

    /**
     * intializes a node
     * @param data data value
     * @param link next node in the chain
     */
    Node(T data, Node link) {
        this.data = data;
        this.link = link;
    }
}

    public static void main(String[] args){
        PriorityQueue.validate(new PriorityDequeuer<>());
        System.out.println("prioritydequeuer passes all tests.");
    }


}