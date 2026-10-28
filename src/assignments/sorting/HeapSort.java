package assignments.sorting;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import assignments.datastructures.MinHeap;

public class HeapSort {

    public void sort(List<Integer> list) {

        // intialize a new minheap

        MinHeap heap = new MinHeap();

        // enqueue all items onto the new minheap
        for (int item : list) {
            heap.enqueue(item);
        }

        // dequeue all items from heap, smallest first, back into the list

        for (int i = 0; i < list.size(); i++) {
            list.set(i, heap.dequeue());
        }
    }

    public static void main(String[] args) {
        HeapSort sorter = new HeapSort();
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 3, 9, 2));
        sorter.sort(list);
        assert list.equals(Arrays.asList(1, 2, 3, 3, 5, 8, 9));
        System.out.println("heapsort passes all tests");
    }

}
