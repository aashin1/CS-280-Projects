package assignments.datastructures;

import java.util.Vector;

/**
 * MinHeap backed by a Vector, smallest removed first
 */

public class MinHeap {

    private Vector<Integer> heap = new Vector<>();

    /**
     * index of the parent of a node
     * @param i index of the child
     * @return index of the parent
     */

    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * index of the left child of a node
     * @param i index of the parent
     * @return index of the left child
     */

    private int leftIndex(int i) {
        return (2 * i) + 1;
    }

    /**
     * index of the right child of a node
     * @param i index of the parent
     * @return index of the right child
     */

    private int rightIndex(int i) {
        return (2 * i) + 2;
    }

    /**
     * swap the values at the two indexes
     * @param a first index
     * @param b second index
     */

    private void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    /**
     * adds an element to the heap
     * element is placed at end, then percolated up until the heap order is correct
     * @param value the value to add
     */

    public void enqueue(int value) {
        heap.add(value);
        heapUp(heap.size() - 1);
    }

    /**
     * move an item up while it is smaller than its parent
     * @param i index of the item to move
     */

    private void heapUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * remove smallest element
     * last item replaces the root, then it is percolated down until the heap order is correct
     * @return smallest element, or -1 if heap is empty (must return an int not a boolean)
     */

    public int dequeue() {
        if (heap.isEmpty()) {
            return -1;
        }

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapDown(0);
        }
        return min;
    }

    /**
     * move an element down while it is larger than its smallest child 
     * @param i index of the item to move
     */

    private void heapDown(int i) {
        int n = heap.size();

        // node has children only if its left child is in bounds

        while (leftIndex(i) < n) {
            int smallest = leftIndex(i);

            // is right child smaller and does it exist? if so, use it

            if (rightIndex(i) < n && heap.get(rightIndex(i)) < heap.get(leftIndex(i))) {
                smallest = rightIndex(i);
            }


            if (heap.get(i) <= heap.get(smallest)) {
                break;
            }

            swap(i, smallest);
            i = smallest;

        }
    }

    /**
     * look at smallest item without removing it
     * @return smallest item, -1 if empty
     */

    public int peek() {
        return heap.isEmpty() ? -1 : heap.get(0); // "?" is like an if-else statement
    }

    /**
     * # of elements in heap
     * @return number of items
     */

    public int size() {
        return heap.size();
    }


    /**
     * test the heap
     * @param args 
     */
    public static void main(String[] args) {
        MinHeap h = new MinHeap();

        // empty heap

        assert h.size() == 0;
        assert h.peek() == -1;
        assert h.dequeue() == -1;

        // enqueue out of order

        int[] values = {5, 3, 8, 1, 4};
        for (int v : values) {
            h.enqueue(v);
        }
        assert h.size() == 5;
        assert h.peek() == 1;

        // dequeue should come out smallest first

        int[] expected = {1, 3, 4, 5, 8};
        for (int e : expected) {
            assert h.dequeue() == e;
        }
        assert h.size() == 0;

        // duplicates
        h.enqueue(2);
        h.enqueue(2);
        h.enqueue(1);
        assert h.dequeue() == 1;
        assert h.dequeue() == 2;
        assert h.dequeue() == 2;

        System.out.println("minheap passes all tests.");
    }

}
